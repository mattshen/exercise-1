import io.atlassian.fugue.Either;
import io.atlassian.fugue.Pair;

public class App {

    public static void main(String[] args) {
        if (args == null || args.length != 2) {
            printInvalidInputMessage();
            printHelp();
        } else {
            String date1 = args[0];
            String date2 = args[1];
            Either<String, Pair<String, Integer>> result =
                    new ElapsedDaysCalculator()
                            .withDate1(date1)
                            .withDate2(date2)
                            .calculate();
            if (result.isLeft()) {
                System.out.println(result.left().get());
            } else {
                System.out.println(result.getOrNull().left());
            }
        }

        System.exit(0);
    }

    private static void printInvalidInputMessage() {
        System.out.println("Invalid Input");
    }

    private static void printHelp() {
        System.out.println("Usage: [command] date1 date2");
    }

}
