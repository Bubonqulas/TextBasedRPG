
/*
    Title: Student Class 
    Authors:  Hassan Darky
*/
import java.util.ArrayList;
import java.util.Random;

/**
 * The Student class represents a student character in the game.
 * Each student has a name, strikes, excuse level, and
 * backpack(ArrayList<OBjects>).
 */
public class Student {

    private String name;
    private int strikes;
    private int excuseLevel;
    public ArrayList<Object> backpack;

    // PROCESSING

    /**
     * Constructor to create a Student object with a given name.
     * 
     * @param name The name of the student.
     */
    public Student(String name) {

        this.name = name;
        this.strikes = 5;
        this.excuseLevel = 4;
        this.backpack = new ArrayList<>();
    }

    /**
     * Getter method for retreiving the excuse level of the student.
     * 
     * @return The excuse level of the student.
     */
    public int getExcuseLevel() {
        return excuseLevel;
    }

    /**
     * Getter method for retreiving the name of the student.
     * 
     * @return The name of the student.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for retreiving the number of strikes the student has.
     * 
     * @return The number of strikes the student has.
     */
    public int getStrikes() {
        return strikes;
    }

    /**
     * Method to increase the excuse level of the student.
     * 
     * @param gainExcuseLevels The amount to increase the excuse level by.
     */
    public void gainExcuseLevel(int gainExcuseLevels) {
        excuseLevel = excuseLevel + gainExcuseLevels;
    }

    /**
     * Method to decrease the number of strikes the student has.
     * If strikes fall to 0 or below, the student is marked absent and the program
     * exits.
     * 
     * @param strikesLost The number of strikes to subtract from the student's
     *                    total.
     */
    public void loseStrkes(int strikesLost) {
        strikes = strikes - strikesLost;
        if (strikes <= 0) {
            printText("\nYou are out of strikes and have been marked absent.\n\nYou LOSE\n\n");
            System.exit(0);
        }
    }

    /**
     * Method to add an object to the student's backpack.
     * 
     * @param item The object to add to the backpack.
     */
    public void addToBackpack(Object item) {
        backpack.add(item);
    }

    /**
     * Method to make an excuse to a teacher.
     * Randomly determines if the excuse is successful based on the teacher's health
     * and student's excuse level.
     * 
     * @param teacher The teacher to which the excuse is made.
     */
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

    /**
     * Check if a specific object is in the student's backpack.
     * 
     * @param objectName The name of the object to check.
     * @return true if the object is in the backpack, false otherwise.
     */
    public boolean checkBackpack(String objectName) {
        for (Object obj : backpack) {
            if (obj.getName().equalsIgnoreCase(objectName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Search the backpack for the ID card and update the excuse level.
     * 
     * @param teacher The teacher checking for the id.
     */
    public void searchBackpack(Teacher teacher) {
        if (checkBackpack("ID Card")) {
            gainExcuseLevel(teacher.getDefeatReward());
            printText(
                    "\nYou found your ID card! You're allowed to continue. Additionaly your excuse level increased to "
                            + getExcuseLevel());

        } else {
            loseStrkes(teacher.getAttack());
            printText("You couldn't find your ID card. You lose " + teacher.getAttack() + " striks(s). You have "
                    + getStrikes() + " strike(s) left.");
            printText("\n" + teacher.getName() + " says");
            teacher.rudeRemarks();

        }

    }

    /**
     * Run away from the teacher at the start of the game, losing all strikes in the
     * process.
     * 
     * @param teacher The teacher at the start of the game.
     * @param student The student running away.
     */
    public void runStartAway() {
        printText("\nYou decide to run away. You lose all your strikes. ");
        loseStrkes(5);

    }

    /**
     * Run away from an encounter with a teacher, losing strikes in the process.
     * 
     * @param teacher The teacher encountered bring run from.
     * @param student The student running away.
     */
    public void runAway(Teacher teacher, Student student) {
        loseStrkes(teacher.getAttack());
        printText("\nYour decide to run away. You lose " + teacher.getAttack() + " strike(s). You now have "
                + student.getStrikes() + " strike(s).");

        printText("\n" + teacher.getName() + " sounds pissed and says");
        teacher.rudeRemarks();

    }

    // OUTPUT

    /**
     * Method to print text with a typewriter effect.Made this method with the help
     * of stack overflow.
     * It basically takes your string that you want to print out and prints it
     * letter by letter for a cooler effect.
     * It goes through each charecter of the string through a loop and prints it out
     * and uses thread.sleep to wait between each charecter.
     * 
     * @param text The text to be printed.
     */
    public static void printText(String text) {
        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}