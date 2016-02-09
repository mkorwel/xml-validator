package xml.validator.core.processor;

import lombok.RequiredArgsConstructor;
import xml.validator.core.constrain.ValidationConstrain;
import xml.validator.core.exception.ProcessingException;

import javax.lang.model.element.*;
import javax.lang.model.util.Elements;
import java.lang.annotation.Annotation;

/**
 * Created by mateusz on 08/02/16.
 */
@RequiredArgsConstructor
public class BasePrecondition {
	private final Elements elementUtils;
	protected final Class<? extends Annotation> annotation;

	public void isValidClass(TypeElement classElement) {
		isClassElement(classElement);
		isPublic(classElement);
		isNotAbstract(classElement);
		isImplementValidationConstrainInterface(classElement);
		isContainsNoParamConstructor(classElement);
	}

	private void isContainsNoParamConstructor(TypeElement classElement) {
		for (Element enclosed : classElement.getEnclosedElements()) {
			if (enclosed.getKind() == ElementKind.CONSTRUCTOR) {
				ExecutableElement constructorElement = (ExecutableElement) enclosed;
				if (constructorElement.getParameters().size() == 0 && constructorElement.getModifiers().contains(Modifier.PUBLIC)) {
					return;
				}
			}
		}

		throw new ProcessingException(classElement, "The class %s must provide an public empty default constructor",
				getClassName(classElement));
	}

	private void isImplementValidationConstrainInterface(TypeElement classElement) {
		TypeElement superClassElement = elementUtils.getTypeElement(ValidationConstrain.class.getName());
		if (!classElement.getInterfaces().contains(superClassElement.asType())) {
			throw new ProcessingException(classElement,
					"The class %s annotated with @%s must implement the interface %s",
					getClassName(classElement), annotation.getSimpleName(),
					ValidationConstrain.class.getName());
		}
	}

	private void isNotAbstract(TypeElement classElement) {
		if (classElement.getModifiers().contains(Modifier.ABSTRACT)) {
			throw new ProcessingException(classElement,
					"The class %s is abstract. You can't annotate abstract classes with @%",
					getClassName(classElement), annotation.getSimpleName());
		}
	}

	private void isPublic(TypeElement classElement) {
		if (!classElement.getModifiers().contains(Modifier.PUBLIC)) {
			throw new ProcessingException(classElement, "The class %s is not public.",
					getClassName(classElement));
		}
	}

	private void isClassElement(TypeElement classElement) {
		if (classElement.getKind() != ElementKind.CLASS) {
			throw new ProcessingException(classElement, "Only classes can be annotated with " + annotation.getSimpleName());
		}
	}

	protected String getClassName(TypeElement classElement) {
		return classElement.getQualifiedName().toString();
	}
}
