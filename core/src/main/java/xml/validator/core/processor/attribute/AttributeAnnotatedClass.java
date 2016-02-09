package xml.validator.core.processor.attribute;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import xml.validator.core.annotation.AttributeValidator;

import javax.lang.model.element.TypeElement;

/**
 * Created by mateusz on 07/02/16.
 */
@Getter
public class AttributeAnnotatedClass {
	private final TypeElement annotatedClass;
	private final Key key;

	public AttributeAnnotatedClass(TypeElement classElement) {
		AttributeValidator annotation = classElement.getAnnotation(AttributeValidator.class);

		this.annotatedClass = classElement;
		this.key = new Key(annotation.elementName(), annotation.name());
	}

	@Getter
	@Accessors(fluent = true)
	@EqualsAndHashCode
	@RequiredArgsConstructor
	public class Key {
		private final String elementName;
		private final String name;
	}
}
