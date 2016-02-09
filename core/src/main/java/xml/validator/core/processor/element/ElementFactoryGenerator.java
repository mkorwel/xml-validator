package xml.validator.core.processor.element;

import com.squareup.javapoet.*;
import xml.validator.core.constrain.ValidationConstrain;
import xml.validator.core.domain.AlwaysSuccessValidator;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.util.*;

/**
 * Created by mateusz on 07/02/16.
 */
public class ElementFactoryGenerator {
	private final static String PACKAGE = "xml.validator.core.factory";
	private final static String CLASS_NAME = "ElementValidatorFactory";

	private Map<ElementAnnotatedClass.Key, List<ElementAnnotatedClass>> annotatedClasses = new LinkedHashMap();

	public void add(ElementAnnotatedClass annotatedClass) {
		if (!annotatedClasses.containsKey(annotatedClass.getKey())) {
			annotatedClasses.put(annotatedClass.getKey(), new LinkedList<>());
		}

		annotatedClasses.get(annotatedClass.getKey()).add(annotatedClass);
	}

	public void generateCode(Filer filer) throws IOException {
		TypeName genericListOfValidationConstrain = ParameterizedTypeName.get(List.class, ValidationConstrain.class);

		MethodSpec.Builder method = MethodSpec.methodBuilder("create")
				.addModifiers(Modifier.PUBLIC)
				.addParameter(String.class, "name")
				.returns(genericListOfValidationConstrain);

		method.beginControlFlow("if (name == null)")
				.addStatement("throw new IllegalArgumentException($S)", "name is null!")
				.endControlFlow();


		for (ElementAnnotatedClass.Key key : annotatedClasses.keySet()) {
			List<ElementAnnotatedClass> classes = annotatedClasses.get(key);
			method.beginControlFlow("if ($S.equals(name))", key.name())
					.addStatement("$T result = new $T<>()", genericListOfValidationConstrain, ArrayList.class);

			for (ElementAnnotatedClass item : classes) {
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
