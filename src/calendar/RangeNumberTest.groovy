package calendar

import spock.lang.Specification

import static calendar.RangeNumber.convertAsString
import static calendar.RangeNumber.splitAndSortNumber

class RangeNumberTest extends Specification {

    def "positive test"() {
        given:
        List<Integer> listNumbers = splitAndSortNumber(number);

        when:
        String actual = convertAsString(listNumbers)

        then:
        assert expected == actual

        where:
        number | expected
        2      | "'2'"
        56     | "'5-6'"
        24     | "'2,4'"
        123    | "'1-3'"
        135    | "'1,3,5'"
        125    | "'1-2,5'"
        12367  | "'1-3,6-7'"
        134567 | "'1,3-7'"
    }

    def "negative test"() {
        when:
        List<Integer> listNumbers = splitAndSortNumber(number);
        convertAsString(listNumbers)

        then:
        IllegalArgumentException exception = thrown(IllegalArgumentException)
        assert exception.message == "digit must be in range 1-7, but was: $invalidDigit"

        where:
        number | invalidDigit
        183    | 8
        105    | 0
    }
}
