package pl.mkorwel.xml.validator.parser;

import pl.mkorwel.xml.validator.domain.Attribute;
import pl.mkorwel.xml.validator.domain.Element;
import xml.validator.core.constrain.ValidationConstrain;
import xml.validator.core.domain.ValidationResult;
import xml.validator.core.factory.AttributeValidatorFactory;
import xml.validator.core.factory.ElementValidatorFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mateusz on 07/02/16.
 */
class XmlValidatorFactory {
	private final ElementValidatorFactory elementValidatorFactory;
	private final AttributeValidatorFactory attributeValidatorFactory;

	XmlValidatorFactory(ElementValidatorFactory elementValidatorFactory, AttributeValidatorFactory attributeValidatorFactory) {
		this.elementValidatorFactory = elementValidatorFactory;
		this.attributeValidatorFactory = attributeValidatorFactory;
	}

	List<ValidationResult> validateValue(Element element) {
		return runValidationConstrains(element.value(), elementValidatorFactory.create(element.name()));
	}

	List<ValidationResult> validateValue(Attribute attribute) {
		return runValidationConstrains(attribute.value(), attributeValidatorFactory.create(attribute.elementName(), attribute.name()));

	}

	private List<ValidationResult> runValidationConstrains(String value, List<ValidationConstrain> xmlValidations) {
		return xmlValidations.stream()
				.map(x -> x.validate(value))
				.collect(Collectors.toList());
	}
}
