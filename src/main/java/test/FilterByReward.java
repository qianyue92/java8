package test;

import dao.Pirate;

public class FilterByReward implements MyPredicate<Pirate>{

    @Override
    public boolean test(Pirate pirate) {
        return pirate.getReward() < 20000;
    }
}
