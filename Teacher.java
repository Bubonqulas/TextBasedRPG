
/*
    Title: Teacher Class 
    Authors:  Hassan Darky
*/
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The Teacher class represents a teacher character in the game.
 * Each teacher has a name, health, attack power, and a list of funny remarks.
 */
public class Teacher {

    private String name;
    private int health;
    private int attack;
    private int defeatReward;
    ArrayList<String> funnyRemarksList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    // PROCESSING

    /**
     * Constructor for Teacher class
     * 
     * @param name         The name of the teacher.
     * @param health       The chance/excuse of the teacher.
     * @param attackThe    damage done by the teacher.
     * @param defeatReward The reward for defeating the teacher.
     */
    public Teacher(String name, int health, int attack, int defeatReward) {

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
        funnyRemarksList.add("'Your such a little troublemaker.'");
        funnyRemarksList.add("'You failed your test!'");
        funnyRemarksList.add("'Your fly is down!'");
        funnyRemarksList.add("'Your remind them of Amber Heard'");

    }

    /**
     * Getter method for retreiving the name of the teacher.
     * 
     * @return The name of the teacher.
     */
    public String getName() {
        return name;

    }

    /**
     * Getter method for retreiving the attack power of the teacher.
     * 
     * @return The attack power of the teacher.
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Getter method for retreiving the reward given to the student upon defeating
     * the teacher.
     * 
     * @return The defeat reward.
     */
    public int getDefeatReward() {
        return defeatReward;
    }

    /**
     * Getter method for retreiving the health of the teacher.
     * 
     * @return The health of the teacher.
     */
    public int getHealth() {
        return health;
    }

    // OUTPUT

    /**
     * Display a random rude remark from the teacher.
     */
    public void rudeRemarks() {
        Random rand = new Random();
        int index = rand.nextInt(funnyRemarksList.size());

        prinText(" " + funnyRemarksList.get(index));
        funnyRemarksList.remove(index);
    }

    /**
     * A deal offered to the student by the teacher.
     * 
     * @param student The student object.
     * @param teacher The teacher object.
     */
    public void listenToStudentDeal(Student student, Teacher teacher) {

        int dealInput = 0;
        prinText("\n\nListen here, " + student.getName()
                + ". I'll make you a deal. If you promise to never be late again, I'll let you pass this time without any consequences. What do you say?");
        prinText("\n    1. Accept the deal");
        prinText("\n    2. Reject the deal ");
        while (dealInput < 1 || dealInput > 2) {
            String choiceDeal = scanner.nextLine();
            dealInput = checkInput(choiceDeal, 1, 3);
            if (dealInput == 1 || dealInput == 2 || dealInput == 3) {
                break;
            } else if (dealInput == 99) {
                prinText("\nInvalid. Please enter a valid number between 1 and 2. ");
            }
        }
        if (dealInput == 1) {
            prinText("\nYou shake hands with Mr. Card and promise to never be late again.");
            prinText("\nMr. Card nods and lets you pass without any further trouble.");
        } else if (dealInput == 2) {
            prinText("\nYou decide not to accept Mr. Card's deal and choose to face the consequences.");
            prinText("\nMr. Card sighs disappointedly but doesn't stop you as you continue towards your classroom.");
            student.loseStrkes(teacher.getAttack());
            prinText("\nYou lose " + teacher.getAttack() + " strike(s). You have " + student.getStrikes()
                    + " strike(s) left.");
        }
    }

    /**
     * Method to check if an input falls within a specified range.
     * 
     * @param input The input to be checked.
     * @param min   The minimum value of the range.
     * @param max   The maximum value of the range.
     * @return The input if it falls within the specified range, otherwise return
     *         99.
     */
    public static int checkInput(String input, int min, int max) {
        try {
            int number = Integer.parseInt(input);
            if (number >= min && number <= max) {
                return number;
            } else {
                return 99;
            }
        } catch (NumberFormatException e) {
            return 99;
        }
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
    public static void prinText(String text) {
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
