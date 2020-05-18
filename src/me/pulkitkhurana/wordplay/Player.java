package me.pulkitkhurana.wordplay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Player {
    String playerName;
    static LinkedList<PlayedGame> playedGames = new LinkedList<>();

    Player(String name){
        playerName = name;
    }

    void gamePlayed(int marks, double perc, String category, String difficulty){
        PlayedGame p = new PlayedGame(marks, perc, category, difficulty,playerName);
        playedGames.addFirst(p);
    }

    static void displayHistory(){
        if (playedGames.isEmpty()){
            System.out.println("No History Available ");
            return;
        }
        System.out.println("HISTORY for only current session will be displayed \n");
        System.out.println("Name\tCategory\tDifficulty\tPercentage  \n");
        for(PlayedGame i : playedGames){
            System.out.println(i.name+"     "+i.category+"     "+i.difficulty+"      "+i.perc);
        }
    }

    public static void displayLeaderBoard(){
        if (playedGames.isEmpty()) {
            System.out.println("No LeaderBoard Available ");
            return;
        }
        ArrayList<PlayedGame> temp = new ArrayList<>(playedGames);
        Collections.sort(temp);
        System.out.println("S.No\tName\tCategory\tDifficulty\tPercentage  \n");
        int j =1;
        for(PlayedGame i : temp){
            System.out.println(j+"    "+i.name+"     "+i.category+"     "+i.difficulty+"      "+i.perc);
            j++;
        }
    }

    public static Player getPlayer(String name){
        return new Player(name);
    }
}


class PlayedGame implements Comparable{
    int marks;
    double perc;
    String category;
    String difficulty;
    String name;

    PlayedGame(int m, double p, String cat, String diff, String name){
        marks = m;
        perc = p;
        category = cat;
        difficulty = diff;
        this.name = name;
    }

    double getPerc(){
        return perc;
    }

    @Override
    public int compareTo(Object other){
        double perc2 = ((PlayedGame)other).getPerc();
        return (int)(perc2- this.perc); //Descending Order
    }
}
