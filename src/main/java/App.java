import java.util.Optional;

public class App {
    public static void main(String[] args) {
        //1. 1983-06-02 – 1983-06-22: 19 days
        //2. 1984-07-04 – 1984-12-25: 173 days
        //3. 989-01-03 – 1983-08-03: 1979 days
        Optional<QDate> date1 = QDateParser.parse("1983-06-02");
        Optional<QDate> date2 = QDateParser.parse("1983-06-22");
        System.out.println(DateUtils.toDays(date2.get()) - DateUtils.toDays(date1.get()));

        Optional<QDate> date3 = QDateParser.parse("2-2-28");
        System.out.println(DateUtils.toDays(date3.get()));
    }
}
