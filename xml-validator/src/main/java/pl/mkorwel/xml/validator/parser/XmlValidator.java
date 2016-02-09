package pl.mkorwel.xml.validator.parser;

import pl.mkorwel.xml.validator.error.exception.XmlValidationException;
import pl.mkorwel.xml.validator.error.handler.DefaultXmlValidatorHandler;

/**
 * Created by mateusz on 08/02/16.
 */
public class XmlValidator {
	private final DefaultXmlValidatorHandler handler = new DefaultXmlValidatorHandler();
	private final XmlValidationParser parser = new XmlValidationParser();

	public void validate(String path) {
		try {
			parser.parse(path);
		} catch (XmlValidationException e) {
			handler.error(e);
		}
	}
}
