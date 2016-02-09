package pl.mkorwel.xml.validator.parser

import pl.mkorwel.xml.validator.domain.Attribute
import pl.mkorwel.xml.validator.domain.Element
import spock.lang.Specification
import xml.validator.core.constrain.ValidationConstrain
import xml.validator.core.domain.ValidationResult
import xml.validator.core.factory.AttributeValidatorFactory
import xml.validator.core.factory.ElementValidatorFactory

/**
 * Created by mateusz on 08/02/16.
 */
class XmlValidatorFactorySpec extends Specification {
    ElementValidatorFactory elementValidatorFactory
    AttributeValidatorFactory attributeValidatorFactory
    XmlValidatorFactory factory

    def setup(){
        elementValidatorFactory = Mock(ElementValidatorFactory)
        attributeValidatorFactory = Mock(AttributeValidatorFactory)

        factory = new XmlValidatorFactory(elementValidatorFactory, attributeValidatorFactory)
    }

    def "should return empty list when no constraints found for attribute"() {
        given:
        attributeValidatorFactory.create(_, _) >> []

        when:
        List<ValidationResult> validateValue = factory.validateValue(Attribute.builder().build())

        then:
        validateValue.size() == 0
    }

    def "should validate attribute value"() {
        given:
        ValidationConstrain successfulValidation = Mock(ValidationConstrain)
        successfulValidation.validate(_) >> ValidationResult.successful()

        String faildMessage = "Faild"
        ValidationConstrain faildValidation = Mock(ValidationConstrain)
        faildValidation.validate(_) >> ValidationResult.faild(faildMessage)

        attributeValidatorFactory.create(_, _) >> [successfulValidation, faildValidation]

        when:
        List<ValidationResult> validateValue = factory.validateValue(Attribute.builder().build())

        then:
        validateValue.size() == 2
        validateValue.get(0).valid == true

        validateValue.get(1).valid == false
        validateValue.get(1).message == faildMessage
    }

    def "should return empty list when no constraints found for element"() {
        given:
        elementValidatorFactory.create(_) >> []

        when:
        List<ValidationResult> validateValue = factory.validateValue(Element.builder().build())

        then:
        validateValue.size() == 0
    }

    def "should validate element value"() {
        given:
        ValidationConstrain successfulValidation = Mock(ValidationConstrain)
        successfulValidation.validate(_) >> ValidationResult.successful()

        String faildMessage = "Faild"
        ValidationConstrain faildValidation = Mock(ValidationConstrain)
        faildValidation.validate(_) >> ValidationResult.faild(faildMessage)

        elementValidatorFactory.create(_) >> [successfulValidation, faildValidation]

        when:
        List<ValidationResult> validateValue = factory.validateValue(Element.builder().build())

        then:
        validateValue.size() == 2
        validateValue.get(0).valid == true

        validateValue.get(1).valid == false
        validateValue.get(1).message == faildMessage
    }
}
