
/*

    Title: Student Class 
    Authors:  Hassan Darky

*/
import java.util.ArrayList;
import java.util.Random;

public class Student {

    private String name;
    private int strikes;
    private int excuseLevel;
    public ArrayList<Object> backpack;

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

    public void makeExcuse(Teacher teacher) {
        Random rand = new Random();
        int chance = rand.nextInt(teacher.getHealth()) + 1;
        if (chance > getExcuseLevel()) {
            loseStrkes(teacher.getAttack());
            printText("\nYour excuse fails. You lose a " + teacher.getAttack() + " strike. You have "
                    + getStrikes() + " strikes left.");
            printText("\n" + teacher.getName() + " says");
            teacher.rudeRemarks();

        } else if (chance < getExcuseLevel()) {
            gainExcuseLevel(teacher.getDefeatReward());
            printText("\nYour excuse works! You're allowed to continue. Additionaly your excuse level increased to "
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

    public void searchBackpack(Teacher teacher) {
        if (checkBackpack("ID Card")) {
            gainExcuseLevel(teacher.getDefeatReward());
            printText(
                    "\nYou found your ID card! You're allowed to continue. Additionaly your excuse level increased to "
                            + getExcuseLevel());

        } else {
            loseStrkes(teacher.getAttack());
            printText("You couldn't find your ID card. You lose " + teacher.getAttack() + " strike. You have "
                    + getStrikes() + " strikes left.");
            printText("\n" + teacher.getName() + " says");
            teacher.rudeRemarks();

        }

    }

    public void runStartAway() {
        printText("\nYour decide to run away. You lose all your strikes.");
        loseStrkes(5);

    }

    public void runAway(Teacher teacher) {
        printText("\nYour decide to run away. You lose " + teacher.getAttack() + " strikes.");
        loseStrkes(teacher.getAttack());
        printText("\n" + teacher.getName() + " says");
        teacher.rudeRemarks();

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
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}