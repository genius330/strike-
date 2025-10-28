package com.fitnessclub;

import java.time.LocalTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FitnessClub {
    private static final int GYM_CAPACITY = 50;
    private static final int POOL_CAPACITY = 20;
    private static final int GROUP_CLASS_CAPACITY = 10;

    private List<Subscription> gymVisitors = new ArrayList<>();
    private List<Subscription> poolVisitors = new ArrayList<>();
    private List<Subscription> groupClassVisitors = new ArrayList<>();

    private List<Subscription> allSubscriptions = new ArrayList<>();

    public void addSubscription(Subscription sub) {
        allSubscriptions.add(sub);
        System.out.println("Добавлен абонемент: " + sub);
    }

    public boolean enterZone(Subscription sub, String zone) {
        if (!isSubscriptionValid(sub)) {
            System.out.println("Абонемент истёк или недействителен.");
            return false;
        }

        LocalTime now = LocalTime.now();
        int hour = now.getHour();

        if (zone.equals("бассейн") && (hour < 8 || hour > 22)) {
            System.out.println("Бассейн работает с 8 до 22 часов. Сейчас " + hour + ":00.");
            return false;
        }

        if (zone.equals("групповые занятия") && (hour < 8 || hour > 16)) {
            System.out.println("Групповые занятия работают с 8 до 16 часов. Сейчас " + hour + ":00.");
            return false;
        }

        if (zone.equals("тренажерный зал") && (hour < 8 || hour > 22)) {
            System.out.println("Тренажерный зал работает с 8 до 22 часов. Сейчас " + hour + ":00.");
            return false;
        }

        if (zone.equals("тренажерный зал") && !sub.canAccessGym()) {
            System.out.println("У вас нет доступа в тренажерный зал.");
            return false;
        }

        if (zone.equals("бассейн") && !sub.canAccessPool()) {
            System.out.println("У вас нет доступа в бассейн.");
            return false;
        }

        if (zone.equals("групповые занятия") && !sub.canAccessGroupClasses()) {
            System.out.println("У вас нет доступа на групповые занятия.");
            return false;
        }

        if (zone.equals("тренажерный зал") && gymVisitors.size() >= GYM_CAPACITY) {
            System.out.println("Тренажерный зал переполнен!");
            return false;
        }

        if (zone.equals("бассейн") && poolVisitors.size() >= POOL_CAPACITY) {
            System.out.println("Бассейн переполнен!");
            return false;
        }

        if (zone.equals("групповые занятия") && groupClassVisitors.size() >= GROUP_CLASS_CAPACITY) {
            System.out.println("Групповые занятия переполнены!");
            return false;
        }

        switch (zone) {
            case "тренажерный зал":
                gymVisitors.add(sub);
                break;
            case "бассейн":
                poolVisitors.add(sub);
                break;
            case "групповые занятия":
                groupClassVisitors.add(sub);
                break;
        }

        System.out.println(sub.getOwner().getFirstName() + " вошёл(-ла) в " + zone + ".");
        return true;
    }

    public void leaveZone(Subscription sub, String zone) {
        switch (zone) {
            case "тренажерный зал":
                gymVisitors.remove(sub);
                break;
            case "бассейн":
                poolVisitors.remove(sub);
                break;
            case "групповые занятия":
                groupClassVisitors.remove(sub);
                break;
        }
        System.out.println(sub.getOwner().getFirstName() + " покинул(-а) " + zone + ".");
    }

    private boolean isSubscriptionValid(Subscription sub) {
        LocalDate today = LocalDate.now();
        return !today.isAfter(sub.getExpirationDate());
    }

    public void showCurrentVisitors() {
        System.out.println("\n=== ТЕКУЩИЕ ПОСЕТИТЕЛИ ===");
        System.out.println("Тренажерный зал: " + gymVisitors.size() + "/" + GYM_CAPACITY);
        System.out.println("Бассейн: " + poolVisitors.size() + "/" + POOL_CAPACITY);
        System.out.println("Групповые занятия: " + groupClassVisitors.size() + "/" + GROUP_CLASS_CAPACITY);
    }

    public static void main(String[] args) {
        FitnessClub club = new FitnessClub();

        Owner owner1 = new Owner("Иван", "Петров", 1990);
        Owner owner2 = new Owner("Мария", "Сидорова", 1985);

        Subscription sub1 = new SingleDaySubscription(owner1, LocalDate.now());
        Subscription sub2 = new DailySubscription(owner2, LocalDate.now(), LocalDate.now().plusDays(7));

        club.addSubscription(sub1);
        club.addSubscription(sub2);

        club.enterZone(sub1, "тренажерный зал");
        club.enterZone(sub1, "бассейн");
        club.enterZone(sub2, "групповые занятия");

        club.showCurrentVisitors();

        club.leaveZone(sub1, "тренажерный зал");

        club.closeClub();
    }

    public void closeClub() {
        System.out.println("\n--- КЛУБ ЗАКРЫВАЕТСЯ ---");
        gymVisitors.clear();
        poolVisitors.clear();
        groupClassVisitors.clear();
        System.out.println("Все клиенты покинули залы.");
    }
}