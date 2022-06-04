package boys;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class BoysFilter {

    public static Map<String, Long> getFilteredBoys(ArrayList<Boy> boys) {
        return boys.stream().filter(b -> b.getAge()>=18).map(b -> b.getName()).sorted().
                distinct().limit(4).collect(Collectors.toMap(item -> item,
                item -> boys.stream().filter(b -> b.getName() == item).count() - 1));
    }
}
