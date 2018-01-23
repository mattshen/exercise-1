import io.atlassian.fugue.Either;
import io.atlassian.fugue.Eithers;
import io.atlassian.fugue.Iterables;
import io.atlassian.fugue.Pair;

import java.text.MessageFormat;

/**
 * Simply a builder.
 */
public class ElaspsedDaysCalculator {

    private String date1;

    private String date2;

    public ElaspsedDaysCalculator() {
    }

    public ElaspsedDaysCalculator withDate1(String date1) {
        this.date1 = date1;
        return this;
    }

    public ElaspsedDaysCalculator withDate2(String date2) {
        this.date2 = date2;
        return this;
    }

    public Either<String, Pair<String, Integer>> calculate() {

        Either<String, Integer> date1Days = DateUtils.parse(this.date1).map(DateUtils::getDays);
        Either<String, Integer> date2Days = DateUtils.parse(this.date2).map(DateUtils::getDays);

        if (date1Days.isRight() && date2Days.isRight()) {
            return Either.right(elapsedDays(date2Days.getOrNull() - date1Days.getOrNull()));
        } else {
            return Either.left(
                    String.join("; ", Eithers.filterLeft(Iterables.iterable(date1Days, date2Days)))
            );
        }
    }

    private String formatResult(String first, String second, Integer elapsedDays) {
        return MessageFormat.format("{0} - {1}: {2} days", first, second, elapsedDays);
    }

    /**
     * Look after the outcome
     * @param diff
     * @return
     */
    private Pair<String, Integer> elapsedDays(Integer diff) {
         if (diff == 0) {
            return Pair.pair(formatResult(date1, date2, 0), 0);
        } else {
            return Pair.pair(formatResult(date1, date2, Math.abs(diff) -1), diff);
        }
    }


}
