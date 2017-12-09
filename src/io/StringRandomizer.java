/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jet
 */
public class StringRandomizer {

    public static char getRandomChar() {

        int randomNum = ThreadLocalRandom.current().nextInt(0, 3 + 1);

        switch (randomNum) {

            case 0:
                return 'a';
            case 1:
                return 'c';
            case 2:
                return 't';
            case 3:
                return 'g';

        }

        return 'a';
    }

    public static String getRandomString(int numChars) {

        String output = "";

        for (int i = 0; i < numChars; i++) {

            output = output + getRandomChar();

        }

        return output + "$";
    }

    public static void createFile(String fname, int chars) {
        try {
            FileWriter fileWriter = new FileWriter(fname);
            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(StringRandomizer.getRandomString(chars));
            bufferedWriter.close();

        } catch (IOException ex) {
            Logger.getLogger(StringRandomizer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String readFile(String fname) {
        String all = "";
        String line;
        FileReader fileReader;
        try {
            fileReader = new FileReader(fname);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                all = all + line;
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(StringRandomizer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StringRandomizer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Input size "+ all.length());
        return all;

    }

    public static void writeOutput(String fname,String stringInput ,int[] indices) {
        try {
            FileWriter fileWriter = new FileWriter(fname);
            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
                    
            for(int i=0; i< indices.length; i++){
                bufferedWriter.write( stringInput.substring(indices[i])+"\r\n");
                
            }
            
            
            bufferedWriter.close();

        } catch (IOException ex) {
            Logger.getLogger(StringRandomizer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
