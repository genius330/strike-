package com.fitnessclub;

import java.time.LocalDate;

public abstract class Subscription {
    protected Owner owner;
    protected LocalDate registrationDate;
    protected LocalDate expirationDate;

    public Subscription(Owner owner, LocalDate registrationDate, LocalDate expirationDate) {
        this.owner = owner;
        this.registrationDate = registrationDate;
        this.expirationDate = expirationDate;
    }

    public Owner getOwner() {
        return owner;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public abstract boolean canAccessGym();
    public abstract boolean canAccessPool();
    public abstract boolean canAccessGroupClasses();
    public abstract String getType();

    @Override
    public String toString() {
        return owner.toString() + " | Абонемент: " + getType() +
                " | Регистрация: " + registrationDate + " | Окончание: " + expirationDate;
    }
}