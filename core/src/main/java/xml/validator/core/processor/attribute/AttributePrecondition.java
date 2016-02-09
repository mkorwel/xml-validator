package xml.validator.core.processor.attribute;

import xml.validator.core.annotation.AttributeValidator;
import xml.validator.core.exception.ProcessingException;
import xml.validator.core.processor.BasePrecondition;

import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * Created by mateusz on 08/02/16.
 */
public class AttributePrecondition extends BasePrecondition {
	public AttributePrecondition(Elements elementUtils) {
		super(elementUtils, AttributeValidator.class);
	}

	public AttributeAnnotatedClass validateAndWrap(TypeElement classElement) {
		isNameIsNotEmpty(classElement);
		isElementNameIsNotEmpty(classElement);
		isValidClass(classElement);

		return new AttributeAnnotatedClass(classElement);
	}

	private void isElementNameIsNotEmpty(TypeElement classElement) {
		String elementName = classElement.getAnnotation(AttributeValidator.class).elementName();
		if (elementName == null || elementName.isEmpty()) {
			throw new ProcessingException(classElement,
					"elementName() in @%s for class %s is null or empty! that's not allowed",
					AttributeValidator.class.getSimpleName(), getClassName(classElement));
		}
	}

	private void isNameIsNotEmpty(TypeElement classElement) {
		String name = classElement.getAnnotation(AttributeValidator.class).name();
		if (name == null || name.isEmpty()) {
			throw new ProcessingException(classElement,
					"name() in @%s for class %s is null or empty! that's not allowed",
					AttributeValidator.class.getSimpleName(), getClassName(classElement));
		}
	}
}
