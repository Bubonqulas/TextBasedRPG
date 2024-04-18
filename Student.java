import java.util.ArrayList;
import java.util.Random;

public class Student {

    private String name;
    private int strikes;
    private int excuseLevel;
    private ArrayList<Object> backpack;

    // CONSTRUCTOR

    Student(String name) {

        this.name = name;
        this.strikes = 5;
        this.excuseLevel = 4;
        this.backpack = new ArrayList<>();
    }

    public int getExcuseLevel() {
        return excuseLevel;
    }

    public int getStrikes() {
        return strikes;
    }

    public void gainExcuseLevel(int gainExcuseLevels) {
        excuseLevel = excuseLevel + gainExcuseLevels;
    }

    public void loseStrkes(int strikesLost) {
        strikes = strikes - strikesLost;
        if (strikes <= 0) {
            printText("\nYou are out of strikes and have been marked absent.\n\nYou LOSE\n\n");
            System.exit(0);
        }
    }

    public void addToBackpack(Object item) {
        backpack.add(item);
    }

    public void makeExcuse(int chanceNumber, int striken, int defeatReward) {
        Random rand = new Random();
        int chance = rand.nextInt(chanceNumber) + 1;
        System.out.println(chance);
        if (chance > getExcuseLevel()) {
            loseStrkes(striken);
            printText("\nYour excuse fails. You lose a " + striken + " strike. You have "
                    + getStrikes() + " strikes left.");

        } else if (chance < getExcuseLevel()) {
            gainExcuseLevel(defeatReward);
            printText("\nYour excuse works! You're allowed to enter. Additionaly your excuse level increased to "
                    + getExcuseLevel());

        }

    }

    public boolean checkBackpack(String objectName) {
        for (Object obj : backpack) {
            if (obj.getName().equalsIgnoreCase(objectName)) {
                return true;
            }
        }
        return false;
    }

    public void searchBackpack(int attack, int defeatReward) {
        if (checkBackpack("ID Card")) {
            gainExcuseLevel(defeatReward);
            printText("\nYou found your ID card! You're allowed to enter. Additionaly your excuse level increased to "
                    + getExcuseLevel());

        } else {
            loseStrkes(attack);
            printText("You couldn't find your ID card. You lose " + attack + " strike. You have "
                    + getStrikes() + " strikes left.");

        }

    }

    public void runStartAway() {
        printText("\nYour decide to run away. You lose all your strikes.");
        loseStrkes(5);

    }

    public void runAway(int strikesL) {
        printText("\nYour decide to run away. You lose " + strikesL + " strikes.");
        loseStrkes(strikesL);

    }

    // Made this method with the help of stack overflow. It basically takes your
    // string that you want to print out and prints it letter by letter for a colol
    // effect.
    // It goes through each charecter of the string through a loop and prints it out
    // and uses thread.sleep to wait between each charecter.
    public static void printText(String text) {
        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}