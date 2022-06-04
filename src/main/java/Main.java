import static boys.BoyListGenerator.getBoysList;
import static boys.BoysFilter.getFilteredBoys;

final class Main {

    public static void main(final String[] args) {
        System.out.println(getFilteredBoys(getBoysList()));
    }
}
