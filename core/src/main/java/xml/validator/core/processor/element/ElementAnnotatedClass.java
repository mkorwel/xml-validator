package xml.validator.core.processor.element;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import xml.validator.core.annotation.ElementValidator;

import javax.lang.model.element.TypeElement;

/**
 * Created by mateusz on 07/02/16.
 */
@Getter
public class ElementAnnotatedClass {
	private final TypeElement annotatedClass;
	private final Key key;

	public ElementAnnotatedClass(TypeElement classElement) {
		this.annotatedClass = classElement;
		this.key = new Key(classElement.getAnnotation(ElementValidator.class).name());
	}

	@Getter
	@Accessors(fluent = true)
	@EqualsAndHashCode
	@RequiredArgsConstructor
	public class Key {
		private final String name;
	}
}
