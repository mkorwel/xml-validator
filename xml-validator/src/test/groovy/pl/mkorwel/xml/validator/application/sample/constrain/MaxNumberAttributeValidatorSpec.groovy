package pl.mkorwel.xml.validator.application.sample.constrain

import spock.lang.Specification
import xml.validator.core.domain.ValidationResult

/**
 * Created by mateusz on 08/02/16.
 */
class MaxNumberAttributeValidatorSpec extends Specification {
    MaxNumberAttributeValidator validator

    def setup(){
        validator = new MaxNumberAttributeValidator()
    }

    def "should validation faild when number is too large"() {
        when:
        ValidationResult result = validator.validate("1000")

        then:
        result.isNotValid()
    }

    def "should validation faild when value is not number"() {
        when:
        ValidationResult result = validator.validate("not number")

        then:
        result.isNotValid()
    }

    def "should validation successful when number is valid"() {
        when:
        ValidationResult result = validator.validate("999")

        then:
        result.isValid()
    }
}
