package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


class Authorization {
    private Card card;
    private ArrayList<Card> cards = new ArrayList<>();
    private short lockCounter = 0;
    private final long DAY= 86400000;
    private Date currentDate;

    void start() {
        ArrayList<Card> cards = new ArrayList<>();
        try{
            FileInputStream fis = new FileInputStream("cards.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String cardsLine;
            String[] cardsSpl;
            while ((cardsLine = br.readLine()) != null) {
                cardsSpl = cardsLine.split(" ");
                cards.add(new Card(cardsSpl[0],cardsSpl[1],cardsSpl[2],cardsSpl[3],cardsSpl[4]));
            }
            this.cards.addAll(cards);
            ArrayList<String> cardsnumbers = new ArrayList<>();
            for (Card value : cards) {
                cardsnumbers.add(value.getCardNumber());
            }
            String cardNumber = inputCardNumber(cardsnumbers);
            for (Card c: cards) {
                if (c.getCardNumber().equals(cardNumber)) {
                    card = c;
                    break;
                }
            }
            inputPIN();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String inputCardNumber(ArrayList<String> cardsnumbers) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter card number: ");
        String cardNumber = in.nextLine();
        if (cardNumber.matches("\\d{4}(-)\\d{4}(-)\\d{4}(-)\\d{4}") && cardsnumbers.contains(cardNumber)) {
            return cardNumber;
        } else {
            System.out.println("INVALID INPUT! Try again!");
            inputCardNumber(cardsnumbers);
        }
        return null;
    }

    private void inputPIN () {
        currentDate = new Date();
        if (currentDate.getTime() - DAY > card.getLocktime().getTime()) {
            card.setIsLocked("notLocked");
        }
        if (card.getIsLocked().equals("locked")) {
            try {
                Main.exit(cards);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Scanner in = new Scanner(System.in);
        System.out.print("Enter PIN: ");
        short PIN;
        try {
            PIN = in.nextShort();
            if (PIN != card.getPIN_code()) {
                lockCounter++;
                if (lockCounter >= 3) {
                    card.setIsLocked("locked");
                    card.setLocktime(currentDate);
                    cards.set(cards.indexOf(card), card);
                    System.out.println("Your card is locked");
                    Main.exit(cards);
                }
                System.out.println("Incorrect PIN! Try again!");
                inputPIN();
            } else {
                Menu menu = new Menu(cards, card);
                menu.showMenu();
            }
        } catch (Exception e) {
            System.out.print("INVALID INPUT! Try again!");
        }
    }
}
