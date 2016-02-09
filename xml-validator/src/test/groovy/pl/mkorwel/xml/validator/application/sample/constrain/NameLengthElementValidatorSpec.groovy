package pl.mkorwel.xml.validator.application.sample.constrain

import spock.lang.Specification
import xml.validator.core.domain.ValidationResult

/**
 * Created by mateusz on 08/02/16.
 */
class NameLengthElementValidatorSpec extends Specification {
    NameLengthElementValidator validator

    def setup(){
        validator = new NameLengthElementValidator()
    }

    def "should validation faild when name is too short"() {
        when:
        ValidationResult result = validator.validate("Ann")

        then:
        result.isNotValid()
    }

    def "should validation successful when name is valid"() {
        when:
        ValidationResult result = validator.validate("Anna")

        then:
        result.isValid()
    }
}
