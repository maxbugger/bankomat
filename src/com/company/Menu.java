package com.company;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

class Menu {
    private Card card;
    private ArrayList<Card> cards = new ArrayList<>();

    void showMenu() {
        System.out.println("Select action:");
        System.out.println("1 - Check Balance");
        System.out.println("2 - Withdraw");
        System.out.println("3 - Recharge");
        System.out.println("0 - Exit");
        select();
    }

    private void select() {
        Scanner in = new Scanner(System.in);
        byte n = 0;
        try {
            n = in.nextByte();
        } catch (Exception e) {
            System.out.println("INVALID INPUT! Try again!");
            select();
        }
        switch (n) {
            case 1:
                checkBalance();
                return;
            case 2:
                withdraw();
                return;
            case 3:
                recharge();
                return;
            case 0:
                try {
                    Main.exit(cards);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            default:
                System.out.println("INVALID ACTION! Try again!");
                select();
        }
    }

    private void checkBalance() {
        System.out.println("Balance: " + card.getBalance());
        showMenu();
    }

    private void withdraw() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter amount of money you want to withdraw: ");
        int amount = 0;
        try {
            amount = in.nextInt();
        } catch (Exception e) {
            System.out.println("INVALID INPUT! Try again");
            withdraw();
        }
        if (amount < 0) {
            System.out.println("WRONG AMOUNT! Try again!");
            withdraw();
        } else if (amount > Integer.parseInt(card.getBalance())) {
            System.out.print("insufficient funds! Enter another amount");
            withdraw();
        } else {
            int balance = Integer.parseInt(card.getBalance()) - amount;
            card.setBalance(Integer.toString(balance));
            System.out.println("the operation was successful!");
            showMenu();
        }
    }

    private void recharge() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter amount of money you want to recharge: ");
        int amount = 0;
        try {
            amount = in.nextInt();
        } catch (Exception e) {
            System.out.println("INVALID INPUT! Try again");
            recharge();
        }
        if (amount < 0) {
            System.out.println("WRONG AMOUNT! Try again!");
            recharge();
        } else if (amount > 1000000) {
            System.out.print("You can top up the balance by no more than 1 000 000. Enter another amount.");
            withdraw();
        } else {
            int balance = amount + Integer.parseInt(card.getBalance());
            card.setBalance(Integer.toString(balance));
            System.out.println("the operation was successful!");
            showMenu();
        }
    }

    Menu(ArrayList<Card> cards, Card card) {
        this.cards.addAll(cards);
        this.card = card;
    }
}
