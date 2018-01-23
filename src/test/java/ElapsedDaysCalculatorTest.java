import io.atlassian.fugue.Either;
import io.atlassian.fugue.Pair;
import org.junit.Assert;
import org.junit.Test;

public class ElapsedDaysCalculatorTest {

    @Test
    public void calculate() {
        Either<String, Pair<String, Integer>> result1 =
                new ElapsedDaysCalculator().withDate1("1983-06-02").withDate2("1983-06-22").calculate();
        Assert.assertEquals(20, (long)result1.getOrNull().right());

        Either<String, Pair<String, Integer>> result2 =
                new ElapsedDaysCalculator().withDate1("1984-07-04").withDate2("1984-12-25").calculate();
        Assert.assertEquals(174, (long)result2.getOrNull().right());

        Either<String, Pair<String, Integer>> result3 =
                new ElapsedDaysCalculator().withDate1("1989-01-03").withDate2("1983-08-03").calculate();
        Assert.assertEquals(-1980, (long)result3.getOrNull().right());
    }
}