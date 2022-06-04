package boys;

import java.util.ArrayList;

public class BoyListGenerator {

    public static ArrayList<Boy> getBoysList() {
        return new ArrayList<>() {{
            add(new Boy("Nickolay", 68));
            add(new Boy("Pyotr", 53));
            add(new Boy("Boris", 25));
            add(new Boy("Mihail", 19));
            add(new Boy("Alexey", 6));
            add(new Boy("Nickolay", 86));
            add(new Boy("Pyotr", 35));
            add(new Boy("Mihail", 111));
            add(new Boy("Alexey", 22));
            add(new Boy("Mihail", 1));
            add(new Boy("Yakov", 30));
        }};
    }
}
