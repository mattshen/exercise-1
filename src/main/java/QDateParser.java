import java.util.Objects;
import java.util.Optional;

public class QDateParser {

    public static Optional<QDate> parse(String dateString) {
        if (Objects.nonNull(dateString) && dateString.matches(DateUtils.VALID_DATE_PATTERN)) {
            String[] dateParts = dateString.split("-");
            int year = Integer.valueOf(dateParts[0]);
            short month = Short.valueOf(dateParts[1]);
            short days = Short.valueOf(dateParts[2]);

            if (DateUtils.isValidDate(year, month, days)) {
                return Optional.of(new QDate(year, month, days));
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

}
