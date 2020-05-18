package me.pulkitkhurana.wordplay;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;




public class Word implements Serializable {
    String word;
    String category;
    Word(String word, String category){
        this.word = word;
        this.category = category;
    }

    @Override
    public String toString(){
        return word+" ("+category+") ";
    }


    public static void addWord(){
        Scanner input = new Scanner(System.in);
        String inputWord;
        String inputCategory;
        String choice;
        do {
            System.out.print("\n\n\t Choose Category ");
            System.out.print("\n\n\n");
            System.out.print("\t 1. Celebrities \n");
            System.out.print("\t 2. Singers \n");
            System.out.print("\t 3. Sports \n");
            System.out.print("\t 4. Random \n");
            choice = input.next();

            switch (choice) {
                case "1": {
                    inputCategory = "Celebrities";
                    break;
                }
                case "2": {
                    inputCategory = "Singers";
                    break;
                }
                case "3": {
                    inputCategory = "Sports";
                    break;
                }
                case "4": {
                    inputCategory = "Random";
                    break;
                }
                default: {
                    System.out.print("Arghh !! Wrong Input ");
                    continue;
                }
            }
            System.out.print("\n\n Enter Word - ");
            inputWord = input.next();

            boolean check = checkWord(inputWord);

            if (check==false){
                System.out.println("\nA valid word can only contain letters !!\n\n");
            }else{
                insertWord(new Word(inputWord.toUpperCase(),inputCategory));
            }

            System.out.print("\n Enter Y to enter more words ");
            choice = input.next();
        }while(choice.equals("Y"));
    }

    private static boolean checkWord(String inputWord){
        // Check if it contains alphabets only
        char [] chars = inputWord.toCharArray();
        for (char c:chars){
            if(!Character.isLetter(c)){
                return false;
            }
        }
        return true;
    }

    private static void insertWord(Word x){
        // File Handling
        if (alreadyExists(x)){
            System.out.println("\nWord "+ x.toString() + " already in the database \n");
            return;
        }
        try {
            File fl = new File("Words.txt");
            if (fl.exists()){
                FileOutputStream f = new FileOutputStream(fl, true);
                AppendingObjectOutputStream o = new AppendingObjectOutputStream(f);
                o.writeObject(x);
                o.close();
                f.close();
            }
            else{
                FileOutputStream f = new FileOutputStream(fl);
                ObjectOutputStream o = new ObjectOutputStream(f);
                o.writeObject(x);
                o.close();
                f.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println("\nWord "+ x.toString() + " Accepted \n");
    }

    private static boolean alreadyExists(Word x){
        boolean exists = false;
        try {
            File fl = new File("Words.txt");
            if (!fl.exists()) {
                return exists;
            }
            FileInputStream fi = new FileInputStream(fl);
            ObjectInputStream oi = new ObjectInputStream(fi);
            Word p = null;
            try {
                p = (Word) oi.readObject();
                while (p != null) {
//                    System.out.println(p);
                    if (p.word.equals(x.word) && p.category.equals(x.category)) {
                        exists = true;
                        break;
                    }
                    p = (Word) oi.readObject();
                }
            }catch (EOFException e){}
            oi.close();
            fi.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return exists;
    }


    public static HashSet<String> getRandomWords(String category, int quantity){
        // File Handling
        ArrayList<String> wordList = new ArrayList<>();
        try{
            File fl = new File("Words.txt");
            if (!fl.exists()){
                return null;
            }
            FileInputStream fi = new FileInputStream(fl);
            ObjectInputStream oi = new ObjectInputStream(fi);
            Word p = null;
            try {
                p = (Word) oi.readObject();
                while (p != null) {
                    if (p.category.equalsIgnoreCase(category)){
                        wordList.add(p.word);
                    }
                    p = (Word) oi.readObject();
                }
            }catch (EOFException e){}
            oi.close();
            fi.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
//        System.out.println(wordList);
        Random rand = new Random();
        HashSet<String> selectedWords = new HashSet<>();
        int size = wordList.size();
        if (size<quantity){
            return null;
        }
        int index = rand.nextInt(size);
        while(selectedWords.size()!=quantity){
            selectedWords.add(wordList.get(index));
            index = rand.nextInt(size);
        }
        return selectedWords;
    }
}
