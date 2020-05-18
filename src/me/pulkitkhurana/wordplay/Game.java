package me.pulkitkhurana.wordplay;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Game extends Matrix {

    String diff;
    int timing;
    int size;
    int wordsSize;
    int marks = 0;
    String category;
    Player player;

    ArrayList<String> gameWords = null;

    Game(int timing, int size, String diff, String category, Player player) {
        this.diff = diff;
        this.timing = timing;
        this.size = size;
        this.category = category;
        this.wordsSize = size - 2;
        this.player = player;
//        gameWords = Word.getRandomWords(category,size);
    }


    boolean build() {
        boolean init = initialise();
        if (!init) {
            System.out.println("\n Game Build Failed \n");
            return false;
        }

        Instant start = Instant.now();

        int i = 0;
        String word = null;
        Random rand = new Random();

        while (i < wordsSize) {

            // If build takes too much time, start over
            if (Duration.between(start, Instant.now()).getSeconds() > 2) {
                System.out.println("Rebuilding The Game ");
                return build();
            }
            word = gameWords.get(i);
            if (word.length() >= size) {
                continue;
            }
            while (true) {   // Trying to fit the word in the game matrix randomly

                // If fitting takes too much time, start over
                if (Duration.between(start,Instant.now()).getSeconds() > 2) {
                    System.out.println("Rebuilding The Game ");
                    return build();
                }

                // Choosing Random Direction
                int d = rand.nextInt(2);
                boolean cont = false;
                if (d == 0) {  // Horizontal
//                    System.out.println("Trying Horizontal");
                    int row = rand.nextInt(size);
                    int col = rand.nextInt(size - word.length());

                    for (int j = 0; j < word.length(); j++) {
                        if (game.get(row).get(col + j) != null) {
                            cont = false;
                            break;
                        }
                        cont = true;
                    }

                    if (cont) {  // Space is there
                        for (int j = 0; j < word.length(); j++) {
                            game.get(row).set(col + j, word.charAt(j));
                        }
                        break;
                    }
                } else {  // Vertical
//                    System.out.println("Trying Vertical");
                    int row = rand.nextInt(size - word.length());
                    int col = rand.nextInt(size);

                    for (int j = 0; j < word.length(); j++) {
                        if (game.get(row + j).get(col) != null) {
                            cont = false;
                            break;
                        }
                        cont = true;
                    }

                    if (cont) {  // Space is there
                        for (int j = 0; j < word.length(); j++) {
                            game.get(row + j).set(col, word.charAt(j));
                        }
                        break;
                    }
                }
            }
            i++;
        }
        fill();
        return true;
    }

    private void fill() {
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (game.get(i).get(j) == null) {
                    game.get(i).set(j, (char) (65 + rand.nextInt(26)));
                }
            }
        }
    }

    @Override
    boolean initialise() {
        game.clear();
        HashSet<String> getWords = Word.getRandomWords(category, wordsSize);
        if (getWords == null) {
            System.out.println("\n At least " + wordsSize + " words required in " + category + " category \n\n");
            return false;
        }
        gameWords = new ArrayList<>(getWords);
        Collections.shuffle(gameWords);
        for (int i = 0; i < size; i++) {
            ArrayList<Character> temp = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                temp.add(null);
            }
            game.add(temp);
        }
//        System.out.println(gameWords);
        return true;
    }

    void display() {
        System.out.println("\n\n");
        System.out.print("\t\t   ");
        Character w;
        for (int i = 0; i < size; i++) {
            System.out.print((i + 1) + "  ");
        }
        System.out.print("\n\t\t");
        for (int i = 0; i < 3 * size; i++) {
            System.out.print("-");
        }
        System.out.print("\n");
        for (int i = 0; i < size; i++) {
            System.out.print("\t\t" + (i + 1) + "| ");
            for (int j = 0; j < size; j++) {
                w = game.get(i).get(j);
                if(w==null){
                    System.out.print("   ");
                }else {
                    System.out.print(game.get(i).get(j) + "  ");
                }
            }
            System.out.print("\n\n");
        }
    }

    void traverse() {
        System.out.print("\t\tWords to find \n\n\t\t");
        int pk;
        pk = wordsSize / 2;
        String word;
        for (int i = 0; i < wordsSize; i++) {
            if (i == pk) {
                System.out.print("\n\t\t");
            }
            word = gameWords.get(i);
            if(word==null){
                System.out.print("   ");
                continue;
            }
            System.out.print(gameWords.get(i) + "  ");
        }
        System.out.println("\n");
    }

    void play() {
        Instant start = Instant.now();
        this.marks = 0;
        int totalMarks = wordsSize;
        ArrayList<String> enteredWords = new ArrayList<>();
        while (Duration.between(start,Instant.now()).getSeconds() < timing) {
            if (!enteredWords.isEmpty()) {
                System.out.println("\n You have found " + marks + " correct Words\n");
                System.out.println("Words entered till now - "+ enteredWords);
            }
//            System.out.println(Duration.between(start,Instant.now()).getSeconds());
            int timeLeft = (int) (timing - Duration.between(start, Instant.now()).getSeconds());
            System.out.println("\nYou have " + timeLeft + " to finish.\n");
            this.display();
            this.traverse();

            String inp = take_input();
            timeLeft = (int) (timing - Duration.between(start, Instant.now()).getSeconds());
            if(timeLeft<0){
                System.out.println("\nTimes Up \n");
                break;
            }
            if (inp == null) {
                continue;
            }
            if (inp.equals("0")) {
                break;
            }
            enteredWords.add(inp);

        }

        addGameDetails();


//        Duration.between(Instant.now(),start).getSeconds()

    }

    String take_input() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n\nEnter 1 if you found a word ");
        System.out.println("Enter 2 if you end up finding words ");
        System.out.print("Enter your choice ");
        String ch = input.next();
        String inp;
        String enteredWord;
        int r1, r2, c1, c2;
        switch (ch) {
            case "1": {
                System.out.print("\n Enter the coordinates in the form r1,c1:r2,c2  ");
                inp = input.next();
                inp = inp.replace(':', ',');
                String[] coordinates = inp.split(",");
                try {
                    r1 = Integer.valueOf(coordinates[0]);
                    c1 = Integer.valueOf(coordinates[1]);
                    r2 = Integer.valueOf(coordinates[2]);
                    c2 = Integer.valueOf(coordinates[3]);
                } catch (Exception e) {
                    System.out.println("Coordinates Incorrectly Entered \n");
                    return null;
                }
                if (r1 > size || r1 < 1 || r2 > size || r2 < 1 || c1 > size || c1 < 1 || c2 > size || c2 < 1) {
                    System.out.println("Out of Bounds Coordinates \n");
                    return null;
                }

                enteredWord = makeWord(r1, r2, c1, c2);
                if (gameWords.contains(enteredWord)) {
                    System.out.println(enteredWord+" Found");
                    int index = gameWords.indexOf(enteredWord);
                    gameWords.set(index, null);
                    this.marks++;
                    change(r1, r2, c1, c2);
                }else{
                    System.out.println(enteredWord+" was not meant to be searched ");
                }
                return enteredWord;
            }
            case "2": {
                return "0";
            }
            default: {
                System.out.println("\n Arghhh !! Wrong Input \n");
                return null;
            }
        }
    }

    String makeWord(int r1, int r2, int c1, int c2) {
        String temp = "";
        if (r1 == r2) {
            for (int i = c1 - 1; i < c2; i++) {
                temp += game.get(r1 - 1).get(i);
            }
        } else if (c1 == c2) {
            for (int i = r1 - 1; i < r2; i++) {
                temp += game.get(i).get(c1 - 1);
            }
        } else {
            int i = r1 - 1;
            int j = c1 - 1;
            while (i < r2 || j < c2) {
                temp += game.get(i).get(j);
                i++;
                j++;
            }
        }
        return temp;
    }

    void change(int r1, int r2, int c1, int c2) {
        if (r1 == r2) {
            for (int i = c1 - 1; i < c2; i++) {
                game.get(r1 - 1).set(i, null);
            }
        } else if (c1 == c2) {
            for (int i = r1 - 1; i < r2; i++) {
                game.get(i).set(c1 - 1, null);
            }
        } else {
            int i = r1 - 1;
            int j = c1 - 1;
            while (i < r2 || j < c2) {
                game.get(i).set(j, null);
                i++;
                j++;
            }
        }
    }

    void addGameDetails(){
        double percentage;
        percentage = (marks*100.0)/wordsSize;
        System.out.println("\n\n");
        System.out.println("Player Id -  "+player.playerName);
        System.out.println("Total Correct word found - "+marks);
        System.out.println("Percentage Scored  - "+percentage);
        System.out.println("Difficulty Level - "+diff);
        System.out.println("Category - "+category);
        player.gamePlayed(marks,percentage,category,diff);
        System.out.println("\n");

    }
}
