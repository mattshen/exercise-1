import io.atlassian.fugue.Either;
import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {

    @Test
    public void isLeapYear() {
        Assert.assertTrue(DateUtils.isLeapYear(4));
        Assert.assertTrue(DateUtils.isLeapYear(2000));
        Assert.assertFalse(DateUtils.isLeapYear(1900));
    }

    @Test
    public void isValidDate() {
        Assert.assertFalse(DateUtils.isValidDate(-1, (short) 1, (short) 1));
        Assert.assertFalse(DateUtils.isValidDate(1900, (short) 2, (short) 31));
        Assert.assertFalse(DateUtils.isValidDate(1900, (short) 2, (short) 29));
        Assert.assertTrue(DateUtils.isValidDate(1900, (short) 12, (short) 31));
        Assert.assertTrue(DateUtils.isValidDate(1996, (short) 2, (short) 29));
    }

    @Test
    public void getDays() {
        Integer days1 = DateUtils.getDays(new QDate(1, (short) 1, (short) 1));
        Assert.assertEquals(1L, (long) days1);

        Integer days2 = DateUtils.getDays(new QDate(2, (short) 1, (short) 1));
        Assert.assertEquals(366, (long) days2);

        Integer days3 = DateUtils.getDays(new QDate(2, (short) 12, (short) 31));
        Assert.assertEquals(730, (long) days3);

        Integer days4 = DateUtils.getDays(new QDate(5, (short) 1, (short) 1));
        Assert.assertEquals(1462, (long) days4);
    }

    @Test
    public void parse() {
        Either<String, QDate> result1 = DateUtils.parse("1900-91-91");
        Assert.assertTrue(result1.isLeft());

        Either<String, QDate> result2 = DateUtils.parse("1900-01-01");
        Assert.assertTrue(result2.isRight());

        Either<String, QDate> result3 = DateUtils.parse("-1-01-01");
        Assert.assertTrue(result3.isLeft());

        Either<String, QDate> result4 = DateUtils.parse("1-01-01-1");
        Assert.assertTrue(result4.isLeft());
    }
}