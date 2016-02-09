package xml.validator.core.domain

import spock.lang.Specification

/**
 * Created by mateusz on 08/02/16.
 */
class ValidationResultSpec extends Specification {
    def "should return valid validation result"() {
        when:
        ValidationResult result = ValidationResult.successful()

        then:
        result.valid
    }

    def "should return invalid validation result"() {
        String message = "error"
        when:
        ValidationResult result = ValidationResult.faild(message)

        then:
        result.notValid
        result.message == message
    }
}
