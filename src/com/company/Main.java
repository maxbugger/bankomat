package com.company;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Authorization authorization = new Authorization();
        authorization.start();
    }

    static void exit(ArrayList<Card> cards) throws IOException {
        FileOutputStream fos = new FileOutputStream("cards.txt");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        for(Card c: cards) {
            bw.write(c.getCardNumber() + " " + c.getBalance() + " " + c.getPIN_code() + " " + c.getIsLocked() + " " + c.getLocktime().getTime());
            bw.newLine();
        }
        bw.close();
        fos.close();
        System.exit(0);
    }
}
