package pl.mkorwel.xml.validator.application.sample.constrain

import spock.lang.Specification
import xml.validator.core.domain.ValidationResult

/**
 * Created by mateusz on 08/02/16.
 */
class SurnameLengthElementValidatorSpec extends Specification {
    SurnameLengthElementValidator validator

    def setup(){
        validator = new SurnameLengthElementValidator()
    }

    def "should validation faild when surname is too short"() {
        when:
        ValidationResult result = validator.validate("Ok")

        then:
        result.isNotValid()
    }

    def "should validation successful when surname is valid"() {
        when:
        ValidationResult result = validator.validate("Smith")

        then:
        result.isValid()
    }
}
