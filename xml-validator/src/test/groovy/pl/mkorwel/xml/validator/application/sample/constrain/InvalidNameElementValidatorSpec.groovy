package pl.mkorwel.xml.validator.application.sample.constrain

import spock.lang.Specification
import xml.validator.core.domain.ValidationResult

/**
 * Created by mateusz on 08/02/16.
 */
class InvalidNameElementValidatorSpec extends Specification {
    InvalidNameElementValidator validator

    def setup(){
        validator = new InvalidNameElementValidator()
    }

    def "should validation faild when name is invalid"() {
        when:
        ValidationResult result = validator.validate("John")

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
