package xml.validator.core.domain;

import xml.validator.core.constrain.ValidationConstrain;

/**
 * Created by mateusz on 07/02/16.
 */
public class AlwaysSuccessValidator implements ValidationConstrain {
	@Override
	public ValidationResult validate(String value) {
		return ValidationResult.successful();
	}
}
