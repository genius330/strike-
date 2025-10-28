package com.fitnessclub;

import java.time.LocalDate;

public class FullSubscription extends Subscription {

    public FullSubscription(Owner owner, LocalDate registrationDate, LocalDate expirationDate) {
        super(owner, registrationDate, expirationDate);
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
        return true;
    }

    @Override
    public String getType() {
        return "Полный";
    }
}