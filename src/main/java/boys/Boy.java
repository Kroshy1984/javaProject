package boys;

import java.util.Objects;

public class Boy {
    private final String name;
    private final int age;

    public Boy(final String name, final int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof Boy)) return false;
        Boy otherBoy = (Boy) other;
        return Objects.equals(otherBoy.getName(), name) && otherBoy.getAge() == age;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        int multiplier = 31;
        hash = hash * multiplier + name.hashCode();
        hash = hash * multiplier + Integer.valueOf(age).hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return name + "-" + age;
    }
}