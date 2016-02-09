package xml.validator.core.domain

import spock.lang.Specification

/**
 * Created by mateusz on 08/02/16.
 */
class AlwaysSuccessValidatorSpec extends Specification {
    def "should always return true when AlwaysSuccessValidator is invoked"() {
        expect:
        result = new AlwaysSuccessValidator().validate(value).isValid()

        where:
        result  || value
        null    || true
        ""      || true
        "value" || true
        "1"     || true
    }
}
