package xml.validator.core.constrain;

import xml.validator.core.domain.ValidationResult;

/**
 * Created by mateusz on 07/02/16.
 */
public interface ValidationConstrain {
	ValidationResult validate(String value);
}
