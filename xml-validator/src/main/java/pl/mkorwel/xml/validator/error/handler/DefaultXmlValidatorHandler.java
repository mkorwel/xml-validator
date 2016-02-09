package pl.mkorwel.xml.validator.error.handler;

import pl.mkorwel.xml.validator.domain.ValidationErrorMessage;
import pl.mkorwel.xml.validator.error.exception.XmlValidationException;

/**
 * Created by mateusz on 08/02/16.
 */
public class DefaultXmlValidatorHandler {
	public void error(XmlValidationException e) {
		e.getErrorMessages().forEach(this::printSingleMessage);
	}

	public void printSingleMessage(ValidationErrorMessage error){
		System.out.println("Error at " + error.validationPoint().pointName() + ": '" + error.message() + "'");
	}
}
