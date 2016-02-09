package xml.validator.core.processor.attribute;

import com.squareup.javapoet.*;
import xml.validator.core.constrain.ValidationConstrain;
import xml.validator.core.domain.AlwaysSuccessValidator;
import xml.validator.core.exception.ProcessingException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.util.*;

/**
 * Created by mateusz on 07/02/16.
 */
public class AttributeFactoryGenerator {
	private final static String PACKAGE = "xml.validator.core.factory";
	private final static String CLASS_NAME = "AttributeValidatorFactory";

	private final Map<AttributeAnnotatedClass.Key, List<AttributeAnnotatedClass>> annotatedClasses = new LinkedHashMap<>();

	public void add(AttributeAnnotatedClass annotatedClass) {
		if (!annotatedClasses.containsKey(annotatedClass.getKey())) {
			annotatedClasses.put(annotatedClass.getKey(), new LinkedList<>());
		}

		annotatedClasses.get(annotatedClass.getKey()).add(annotatedClass);
	}

	public void generateCode(Filer filer) throws IOException {
		TypeName genericListOfValidationConstrain = ParameterizedTypeName.get(List.class, ValidationConstrain.class);

		MethodSpec.Builder method = MethodSpec.methodBuilder("create")
				.addModifiers(Modifier.PUBLIC)
				.addParameter(String.class, "elementName")
				.addParameter(String.class, "name")
				.returns(genericListOfValidationConstrain);

		method.beginControlFlow("if (elementName == null)")
				.addStatement("throw new IllegalArgumentException($S)", "elementName is null!")
				.endControlFlow();

		method.beginControlFlow("if (name == null)")
				.addStatement("throw new IllegalArgumentException($S)", "name is null!")
				.endControlFlow();


		for (AttributeAnnotatedClass.Key key : annotatedClasses.keySet()) {
			List<AttributeAnnotatedClass> classes = annotatedClasses.get(key);
			method.beginControlFlow("if ($S.equals(elementName) && $S.equals(name))", key.elementName(), key.name())
					.addStatement("$T result = new $T<>()", genericListOfValidationConstrain, ArrayList.class);

			for (AttributeAnnotatedClass item : classes) {
				method.addStatement("result.add(new $T())", item.getAnnotatedClass());
			}

			method.addStatement("return result")
					.endControlFlow();
		}

		method.addStatement("$T result = new $T<>()", genericListOfValidationConstrain, ArrayList.class)
				.addStatement("result.add(new $T())", AlwaysSuccessValidator.class)
				.addStatement("return result");

		TypeSpec typeSpec = TypeSpec.classBuilder(CLASS_NAME)
				.addModifiers(Modifier.PUBLIC)
				.addMethod(method.build())
				.build();

		JavaFile.builder(PACKAGE, typeSpec).build().writeTo(filer);
	}
}
