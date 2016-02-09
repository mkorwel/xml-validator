package pl.mkorwel.xml.validator.error.exception;

import lombok.RequiredArgsConstructor;
import pl.mkorwel.xml.validator.domain.ValidationErrorMessage;

import java.util.Collections;
import java.util.List;

/**
 * Created by mateusz on 08/02/16.
 */
@RequiredArgsConstructor
public class XmlValidationException extends RuntimeException {
	private final List<ValidationErrorMessage> errorMessages;

	public List<ValidationErrorMessage> getErrorMessages() {
		return Collections.unmodifiableList(errorMessages);
	}
}
