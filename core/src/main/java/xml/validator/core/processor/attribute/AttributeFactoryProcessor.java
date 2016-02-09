package xml.validator.core.processor.attribute;

import com.google.auto.service.AutoService;
import xml.validator.core.annotation.AttributeValidator;
import xml.validator.core.exception.ProcessingException;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mateusz on 07/02/16.
 */
@AutoService(Processor.class)
public class AttributeFactoryProcessor extends AbstractProcessor {
	private Filer filer;
	private Messager messager;
	private AttributeFactoryGenerator factoryClass;
	private AttributePrecondition preconditions;

	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		filer = processingEnv.getFiler();
		messager = processingEnv.getMessager();
		preconditions = new AttributePrecondition(processingEnv.getElementUtils());
	}

	@Override
	public Set<String> getSupportedAnnotationTypes() {
		Set<String> annotataions = new HashSet<>();
		annotataions.add(AttributeValidator.class.getCanonicalName());

		return annotataions;
	}

	@Override
	public SourceVersion getSupportedSourceVersion() {
		return SourceVersion.latestSupported();
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		try {
			prepareAndGenerate(roundEnv);
		} catch (ProcessingException e) {
			error(e.getMessage(), e.getElement());
		} catch (IOException e) {
			error(e.getMessage());
		}

		return true;
	}

	private void prepareAndGenerate(RoundEnvironment roundEnv) throws IOException {
		roundEnv.getElementsAnnotatedWith(AttributeValidator.class)
				.stream()
				.forEach(this::addClassesToFactory);

		generate();
		clean();
	}

	private void addClassesToFactory(Element annotatedElement) {
		AttributeAnnotatedClass annotatedClass = preconditions.validateAndWrap((TypeElement) annotatedElement);
		factoryClass = getOrCreateFactoryIfNotExists();
		factoryClass.add(annotatedClass);
	}

	private void generate() throws IOException {
		if (factoryClass != null) {
			factoryClass.generateCode(filer);
		}
	}

	private void clean() {
		factoryClass = null;
	}

	private AttributeFactoryGenerator getOrCreateFactoryIfNotExists() {
		if (factoryClass == null) {
			factoryClass = new AttributeFactoryGenerator();
		}

		return factoryClass;
	}

	private void error(String message, Element element) {
		messager.printMessage(Diagnostic.Kind.ERROR, message, element);
	}

	private void error(String message) {
		messager.printMessage(Diagnostic.Kind.ERROR, message);
	}
}
