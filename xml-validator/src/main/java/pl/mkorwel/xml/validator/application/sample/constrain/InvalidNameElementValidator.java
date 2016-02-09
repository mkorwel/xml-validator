package pl.mkorwel.xml.validator.application.sample.constrain;

import xml.validator.core.annotation.ElementValidator;
import xml.validator.core.constrain.ValidationConstrain;
import xml.validator.core.domain.ValidationResult;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mateusz on 07/02/16.
 */
@ElementValidator(name = "name")
public class InvalidNameElementValidator implements ValidationConstrain {
	private static final List<String> INVALID_NAME = Arrays.asList("John");

	@Override
	public ValidationResult validate(String value) {
		return INVALID_NAME.contains(value) ? ValidationResult.faild("Name " + value + " is invalid.") : ValidationResult.successful();
	}
}
