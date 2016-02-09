package xml.validator.core.exception;

import lombok.Getter;

import javax.lang.model.element.Element;

/**
 * Created by mateusz on 07/02/16.
 */
@Getter
public class ProcessingException extends RuntimeException {
	private final Element element;

	public ProcessingException(Element element, String msg, Object... args) {
		super(String.format(msg, args));
		this.element = element;
	}
}
