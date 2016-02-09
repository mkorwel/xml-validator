package pl.mkorwel.xml.validator.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import pl.mkorwel.xml.validator.domain.Attribute;
import pl.mkorwel.xml.validator.domain.Element;
import pl.mkorwel.xml.validator.domain.ValidationErrorMessage;
import pl.mkorwel.xml.validator.domain.ValidationPoint;
import pl.mkorwel.xml.validator.error.exception.XmlValidationException;
import xml.validator.core.domain.ValidationResult;
import xml.validator.core.factory.AttributeValidatorFactory;
import xml.validator.core.factory.ElementValidatorFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by mateusz on 07/02/16.
 */
class ValidationHandler extends DefaultHandler {
	private final Stack<String> elements = new Stack();
	private final XmlValidatorFactory xmlValidator = new XmlValidatorFactory(new ElementValidatorFactory(), new AttributeValidatorFactory());
	private final List<ValidationErrorMessage> results = new LinkedList<>();

	@Override
	public void startElement(String namespaceURI, String localName, String qualifiedName, Attributes att) throws SAXException {
		super.startElement(namespaceURI, localName, qualifiedName, att);

		elements.push(qualifiedName);

		for (int index = 0; index < att.getLength(); index++) {

			Attribute attribute = Attribute.builder()
					.elementName(elements.lastElement())
					.name(att.getQName(index))
					.value(att.getValue(index))
					.build();

			saveValidationResults(xmlValidator.validateValue(attribute), attribute);
		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		Element element = Element.builder()
				.name(elements.lastElement())
				.value(new String(ch, start, length).trim())
				.build();

		saveValidationResults(xmlValidator.validateValue(element), element);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);

		elements.pop();

		if (elements.isEmpty()) {
			throwExceptionIfAnyMessageIsRegistered();
		}
	}

	private void throwExceptionIfAnyMessageIsRegistered() {
		if(!results.isEmpty()){
			throw new XmlValidationException(results);
		}
	}

	private void saveValidationResults(List<ValidationResult> localResults, ValidationPoint validationPoint) {
		localResults.stream()
				.filter(ValidationResult::isNotValid)
				.map(x -> createMessage(validationPoint, x))
				.forEach(results::add);
	}

	private ValidationErrorMessage createMessage(ValidationPoint validationPoint, ValidationResult x) {
		return ValidationErrorMessage.builder()
				.message(x.getMessage())
				.validationPoint(validationPoint)
				.build();
	}
}