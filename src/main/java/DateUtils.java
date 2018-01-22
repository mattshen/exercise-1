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
            return day <= leapYearMonthLength[month - 1];
        } else {
            return day <= nonLeapYearMonthLength[month - 1];
        }
    }

    /**
     * Calculates to days from date 0001-01-01 (inclusive).
     *
     * @param date
     * @return days from 0001-01-01 (inclusive)
     *
     */
    public static Integer toDays(QDate date) {
        int days = date.getDay();
        for (int m = 1; m <= date.getMonth() - 1; m++) {
            days += isLeapYear(date.getYear()) ? leapYearMonthLength[m] : nonLeapYearMonthLength[m];
        }
        for (int y = 1; y <= date.getYear() - 1; y++) {
            days += isLeapYear(date.getYear()) ? 366 : 365;
        }
        return days;
    }

}
