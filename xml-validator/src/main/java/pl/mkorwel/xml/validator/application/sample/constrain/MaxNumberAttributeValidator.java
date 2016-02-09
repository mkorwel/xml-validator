package pl.mkorwel.xml.validator.application.sample.constrain;

import xml.validator.core.annotation.AttributeValidator;
import xml.validator.core.constrain.ValidationConstrain;
import xml.validator.core.domain.ValidationResult;

/**
 * Created by mateusz on 07/02/16.
 */
@AttributeValidator(elementName = "person", name = "number")
public class MaxNumberAttributeValidator implements ValidationConstrain {

	@Override
	public ValidationResult validate(String value) {
		int numericValue;
		try {
			 numericValue = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return  ValidationResult.faild("Value " + value + " is not a number.");
		}

		if(numericValue > 999){
			return ValidationResult.faild("Invalid number: " + value + ". Max number is 999.");
		}

		return ValidationResult.successful();
	}
}
