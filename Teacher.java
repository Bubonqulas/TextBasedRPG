/*

    Title: Teacher Class 
    Authors:  Hassan Darky

*/

import java.util.ArrayList;
import java.util.Random;

public class Teacher {

    private String name;
    private int health;
    private int attack;
    private int defeatReward;
    ArrayList<String> funnyRemarksList = new ArrayList<>();

    // CONSTRUCTOR

    Teacher(String name, int health, int attack, int defeatReward) {

        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defeatReward = defeatReward;
        funnyRemarksList.add("'Your an unreasonable Muppet!'");
        funnyRemarksList.add("'Your such a little nerd.'");
        funnyRemarksList.add("'Your running late, but at least you're running!'");
        funnyRemarksList.add("'The king/queen of procrastination arrives!'");
        funnyRemarksList.add("'Late again? That's becoming a habit!'");
        funnyRemarksList.add("'Your an goofball!'");
        funnyRemarksList.add("'Your such a little roublemaker.'");
        funnyRemarksList.add("'You failed your test!'");
        funnyRemarksList.add("'Your fly is down!'");
        funnyRemarksList.add("'Your remind them of Amber Heard'");

    }

    public String getName() {
        return name;

    }

    public int getAttack() {
        return attack;
    }

    public int getDefeatReward() {
        return defeatReward;
    }

    public int getHealth() {
        return health;
    }

    public void rudeRemarks() {
        Random rand = new Random();
        int index = rand.nextInt(funnyRemarksList.size());

        prinText(" " + funnyRemarksList.get(index));
        funnyRemarksList.remove(index);
        System.out.println(funnyRemarksList);
    }

    public static void prinText(String text) {
        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
