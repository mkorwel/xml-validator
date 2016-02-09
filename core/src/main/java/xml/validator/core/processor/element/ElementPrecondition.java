package xml.validator.core.processor.element;

import xml.validator.core.annotation.ElementValidator;
import xml.validator.core.exception.ProcessingException;
import xml.validator.core.processor.BasePrecondition;

import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * Created by mateusz on 08/02/16.
 */
public class ElementPrecondition extends BasePrecondition {
	public ElementPrecondition(Elements elementUtils) {
		super(elementUtils, ElementValidator.class);
	}

	public ElementAnnotatedClass validateAndWrap(TypeElement classElement) {
		isNameIsNotEmpty(classElement);
		isValidClass(classElement);

		return new ElementAnnotatedClass(classElement);
	}

	private void isNameIsNotEmpty(TypeElement classElement) {
		String name = classElement.getAnnotation(ElementValidator.class).name();
		if (name == null || name.isEmpty()) {
			throw new ProcessingException(classElement,
					"name() in @%s for class %s is null or empty! that's not allowed",
					annotation.getSimpleName(), getClassName(classElement));
		}
	}
}
