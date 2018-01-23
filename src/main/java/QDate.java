/**
 * Simple model to represent a date with only year, month and day.
 */
public class QDate {

    private final int year;
    private final short month;
    private final short day;

    public QDate(int year, short month, short day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public short getMonth() {
        return month;
    }

    public short getDay() {
        return day;
    }

    @Override
    public String toString() {
        return year + "-" + month + "-" + day;
    }

}
