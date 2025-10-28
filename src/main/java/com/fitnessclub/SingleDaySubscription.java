package com.fitnessclub;

import java.time.LocalDate;

public class SingleDaySubscription extends Subscription {

    public SingleDaySubscription(Owner owner, LocalDate registrationDate) {
        super(owner, registrationDate, registrationDate);
    }

    @Override
    public boolean canAccessGym() {
        return true;
    }

    @Override
    public boolean canAccessPool() {
        return true;
    }

    @Override
    public boolean canAccessGroupClasses() {
        return false;
    }

    @Override
    public String getType() {
        return "Разовый";
    }
}