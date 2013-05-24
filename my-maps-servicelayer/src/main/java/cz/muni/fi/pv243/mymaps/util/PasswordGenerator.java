/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.util;

import java.util.Random;

/**
 *
 * @author Kuba
 */

public class PasswordGenerator {

    private String password = "";
    private Random generator = new Random();

    public PasswordGenerator() {
    }

    public String generatePassword(int length, boolean upperCase, boolean numbers) {

        boolean ok = false;

        while (ok == false) {
            password = "";
            boolean upper = false;
            boolean number = false;

            for (int i = 0; i < length; i++) {

                String letter = "";
                boolean correctChar = false;
                while (correctChar == false) {
                    int random;
                    char character;

                    random = generator.nextInt(127);

                    if (upperCase == true && random > 64 && random < 91) {
                        character = (char) random;
                        letter = Character.toString(character);
                        upper = true;
                        correctChar = true;
                    }

                    if (numbers == true && random > 47 && random < 58) {
                        character = (char) random;
                        letter = Character.toString(character);
                        number = true;
                        correctChar = true;
                    }

                    if (random > 96 && random < 123) {
                        character = (char) random;
                        letter = Character.toString(character);
                        correctChar = true;
                    }

                }
                password += letter;
            }

            if ((upperCase == true && upper == false) || (numbers == true && number == false) || password.length() != length) {
                ok = false;
            } else {
                ok = true;
            }
        }
        return password;
    }
}
