package calendar;

import java.util.*;
import java.util.stream.Collectors;

public class RangeNumber {

    public static String convertAsString(List<Integer> list) {
        if (list.size() == 1) {
            return "'" + list.get(0).toString() + "'";
        }

        StringBuilder builder = new StringBuilder("'");
        int startNumber = 0;
        int endNumber = 0;

        for (int i = 0; i < list.size(); i++) {
            endNumber = range(list, startNumber);

            if (startNumber == endNumber) {
                builder.append(list.get(endNumber));
                startNumber = i + 1;

                if (list.size() - 1 > endNumber) {
                    builder.append(",");
                }

            } else {
                builder.append(list.get(startNumber)).append("-").append(list.get(endNumber));
                startNumber = endNumber + 1;

                if (list.size() - 1 > endNumber) {
                    builder.append(",");
                }
            }

            i = endNumber;
        }

        return builder.append("'").toString();
    }

    private static int range(List<Integer> list, int startIndex) {
        int count = startIndex;

        for (int i = startIndex; i <= list.size(); i++) {
            if (list.size() - 1 != i && list.get(i) + 1 == list.get(i + 1)) {
                count++;
            } else {
                return count;
            }
        }
        return 0;
    }

    public static List<Integer> splitAndSortNumber(Integer number) {
        String numberAsString = number.toString();

        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < numberAsString.length(); i++) {
            Integer digit = Integer.decode(String.valueOf(numberAsString.charAt(i)));
            if (digit > 7 || digit < 1) {
                throw new IllegalArgumentException("digit must be in range 1-7, but was: " + digit);
            }

            numbers.add(digit);
        }

        return numbers.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}
