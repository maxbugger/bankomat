package com.company;

import java.util.Date;

class Card {
    private String cardNumber;
    private String balance;
    private short pin_code;
    private String isLocked;
    private Date locktime;

    String getCardNumber() {
        return cardNumber;
    }

    String getBalance() {
        return balance;
    }

    short getPIN_code() {
        return pin_code;
    }

    void setBalance(String balance) {
        this.balance = balance;
    }

    void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

    String getIsLocked() {
        return isLocked;
    }

    Card(String cardNumber, String balance, String pin_code, String isLocked, String locktime) {
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.pin_code = Short.parseShort(pin_code);
        if (isLocked.equals("locked")) {
            this.isLocked = isLocked;
        } else {
            this.isLocked = "notLocked";
        }
        this.locktime = new Date(Long.parseLong(locktime));
    }

    Date getLocktime() {
        return locktime;
    }

    void setLocktime(Date currentDate) {
        this.locktime = currentDate;
    }
}