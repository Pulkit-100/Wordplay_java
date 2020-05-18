package me.pulkitkhurana.wordplay;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
//        System.out.println(Instant.now());
        Scanner input = new Scanner(System.in);
        String ch;
//        utils.introScreen();
        do{
            System.out.print("\n\n\n");
            System.out.print("\t 1. Play Game \n");
            System.out.print("\t 2. Add Words \n");
            System.out.print("\t 3. History \n");
            System.out.print("\t 4. LeaderBoard \n");
            System.out.print("\t 0. Exit \n\n");

            System.out.print("\t Enter Your Choice - ");
            ch = input.next();

            String name;

            switch (ch){
                case "0":
                {
                    System.out.print("\n\n Good Bye \n\n");
                    break;
                }
                case "1": {
                    System.out.print("\n\n\t Enter Player Id - ");
                    name = input.next();
                    Player player = Player.getPlayer(name);
                    String chh1;
                    System.out.print("\n\n\t Choose The Difficulty Level ");
                    System.out.print("\n\n\n");
                    System.out.print("\t 1. Easy \n");
                    System.out.print("\t 2. Medium \n");
                    System.out.print("\t 3. Hard \n\n\n");
                    System.out.print("\t Enter Your Choice - ");
                    chh1 = input.next();

                    int size, timing;
                    String diff;

                    switch (chh1){
                        case "1":
                        {
                            diff = "Easy";
                            size = 6;
                            timing = 100;
                            break;
                        }
                        case "2":
                        {
                            diff = "Medium";
                            size = 10;
                            timing = 90;
                            break;
                        }
                        case "3":
                        {
                            diff = "Hard";
                            size = 15;
                            timing = 75;
                            break;
                        }
                        default:
                        {
                            System.out.print("Arghh !! Wrong Input ");
                            continue;
                        }
                    }



                    String chh2;
                    String inputCategory;
                    System.out.print("\n\n\t Choose Category ");
                    System.out.print("\n\n\n");
                    System.out.print("\t 1. Celebrities \n");
                    System.out.print("\t 2. Singers \n");
                    System.out.print("\t 3. Sports \n");
                    System.out.print("\t 4. Random \n");
                    System.out.print("\t Enter Your Choice - ");
                    chh2 = input.next();
                    switch (chh2) {
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
                    Game game = new Game(timing,size,diff,inputCategory,player);
                    if(game.build()){
                        game.play();
                    }

                    break;
                }
                case "2": {
                    Word.addWord();
                    break;
                }
                case "3":{
                    Player.displayHistory();
                    break;
                }
                case "4":{
                    Player.displayLeaderBoard();
                    break;
                }
                default: {
                    System.out.print("Arghh !! Wrong Input ");
                    continue;
                }
            }
        }while (!ch.equals("0"));
    }
}
