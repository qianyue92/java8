package test;

import dao.Pirate;

public class FilterByAge implements MyPredicate<Pirate> {

    @Override
    public boolean test(Pirate pirate) {
        return pirate.getAge() < 25;
    }
}
