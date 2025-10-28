package com.fitnessclub;

import java.time.LocalDate;

public class DailySubscription extends Subscription {

    public DailySubscription(Owner owner, LocalDate registrationDate, LocalDate expirationDate) {
        super(owner, registrationDate, expirationDate);
    }

    @Override
    public boolean canAccessGym() {
        return true;
    }

    @Override
    public boolean canAccessPool() {
        return false;
    }

    @Override
    public boolean canAccessGroupClasses() {
        return true;
    }

    @Override
    public String getType() {
        return "Дневной";
    }
}