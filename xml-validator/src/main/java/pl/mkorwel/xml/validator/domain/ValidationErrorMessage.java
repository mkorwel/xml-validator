package pl.mkorwel.xml.validator.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Created by mateusz on 08/02/16.
 */
@Getter
@Builder
@Accessors(fluent = true)
public class ValidationErrorMessage {
	private final String message;
	private final ValidationPoint validationPoint;
}
