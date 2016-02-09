package pl.mkorwel.xml.validator.application.sample.constrain;

import xml.validator.core.annotation.ElementValidator;
import xml.validator.core.constrain.ValidationConstrain;
import xml.validator.core.domain.ValidationResult;

/**
 * Created by mateusz on 07/02/16.
 */
@ElementValidator(name = "name")
public class NameLengthElementValidator implements ValidationConstrain {

	@Override
	public ValidationResult validate(String value) {
		return value.length() > 3 ? ValidationResult.successful() : ValidationResult.faild("Name " + value + " is too short.");
	}
}
