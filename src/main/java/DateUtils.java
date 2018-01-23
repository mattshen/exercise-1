import io.atlassian.fugue.Either;

import java.util.Objects;

/**
 * Just some utility methods.
 */
public class DateUtils {

    public static final String VALID_DATE_PATTERN =
            "^\\d{1,4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
    public static final Short[] nonLeapYearMonthLength =
            new Short[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public static final Short[] leapYearMonthLength =
            new Short[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 100 != 0 && year % 4 == 0);
    }

    public static boolean isValidDate(int year, short month, short day) {
        if (isLeapYear(year)) {
            return year > 0 && day <= leapYearMonthLength[month - 1];
        } else {
            return year > 0 && day <= nonLeapYearMonthLength[month - 1];
        }
    }

    /**
     * Calculates to days from date 0001-01-01 (inclusive).
     *
     * @param date
     * @return days from 0001-01-01 (inclusive)
     */
    public static Integer getDays(QDate date) {
        int days = date.getDay();
        for (int m = 1; m <= date.getMonth() - 1; m++) {
            days += DateUtils.isLeapYear(date.getYear()) ? leapYearMonthLength[m-1] : nonLeapYearMonthLength[m-1];
        }
        for (int y = 1; y <= date.getYear() - 1; y++) {
            days += DateUtils.isLeapYear(y) ? 366 : 365;
        }
        return days;
    }

    /**
     * Parse date in form, supports only "YYYY-MM-DD" format.
     * @param dateString date in string form
     * @return Optional<QDate>
     */
    public static Either<String, QDate> parse(String dateString) {
        if (Objects.nonNull(dateString) && dateString.matches(DateUtils.VALID_DATE_PATTERN)) {
            String[] dateParts = dateString.split("-");
            int year = Integer.valueOf(dateParts[0]);
            short month = Short.valueOf(dateParts[1]);
            short days = Short.valueOf(dateParts[2]);

            if (DateUtils.isValidDate(year, month, days)) {
                return Either.right(new QDate(year, month, days));
            } else {
                return Either.left(dateString + " is invalid");
            }
        } else {
            return Either.left(dateString + " is invalid");
        }
    }

}
