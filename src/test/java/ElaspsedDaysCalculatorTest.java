import io.atlassian.fugue.Either;
import io.atlassian.fugue.Pair;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ElaspsedDaysCalculatorTest {

    @Test
    public void calculate() {
        Either<String, Pair<String, Integer>> resutl1 =
                new ElaspsedDaysCalculator().withDate1("1983-06-02").withDate2("1983-06-22").calculate();
        Assert.assertEquals(20, (long)resutl1.getOrNull().right());

        Either<String, Pair<String, Integer>> resutl2 =
                new ElaspsedDaysCalculator().withDate1("1984-07-04").withDate2("1984-12-25").calculate();
        Assert.assertEquals(174, (long)resutl2.getOrNull().right());

        Either<String, Pair<String, Integer>> resutl3 =
                new ElaspsedDaysCalculator().withDate1("1989-01-03").withDate2("1983-08-03").calculate();
        Assert.assertEquals(-1980, (long)resutl3.getOrNull().right());
    }
}