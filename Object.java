
/*
    Title: Object Class 
    Authors:  Hassan Darky
*/
import java.util.Random;
import java.util.Scanner;

/**
 * The Object class represents an object that the student can interact with
 * during the game.
 * Objects have various effects, such as enhancing the student's excuse level or
 * providing other benefits.
 */
public class Object {
    static Scanner scanner = new Scanner(System.in);
    private String name;
    private int excuseBlocker;

    // Processing
    /**
     * Constructor for the Object class.
     * 
     * @param name          The name of the object.
     * @param excuseBlocker The amount by the object enhances the student's
     *                      excuse level.
     */
    public Object(String name, int excuseBlocker) {

        this.name = name;
        this.excuseBlocker = excuseBlocker;
    }

    /**
     * Getter method for retrieving the name of the object.
     * 
     * @return The name of the object.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for retrieving the excuse blocker value of the object.
     * 
     * @return The excuse blocker value.
     */
    public int getExcuseBlocker() {
        return excuseBlocker;
    }

    /**
     * Method to reveal the details of the object, including its name and its excuse
     * blocker value.
     */
    public void revealObject() {

        prinText("\n\nObject name: " + name + "\nExcuse enhancer: " + excuseBlocker);

    }

    /**
     * Method to play a game of rock-paper-scissors against a teacher.
     * 
     * @param student The student playing the game.
     * @param teacher The teacher being challenged to the game.
     */
    public static void playRockPaperScissors(Student student, Teacher teacher) {
        int rpsInput = 0;
        prinText("\n\nYou challange " + teacher.getName() + " to a rock paper scissors duel.");
        prinText("\nChoose your move (1. Rock, 2. Paper, 3. Scissors): ");
        while (rpsInput < 1 || rpsInput > 3) {
            String choiceRPS = scanner.nextLine();
            rpsInput = checkInput(choiceRPS, 1, 3);
            if (rpsInput == 1 || rpsInput == 2 || rpsInput == 3) {
                break;
            } else if (rpsInput == 99) {
                prinText("\nInvalid. Please enter a valid number between 1 an 3. ");
            }
        }
        int teacherMove = new Random().nextInt(3) + 1;
        if (rpsInput == teacherMove) {
            prinText(
                    "\nIt's a tie! You are allowed to pass without losing a strike but " + teacher.getName() + " says");
            teacher.rudeRemarks();
        } else if ((rpsInput == 1 && teacherMove == 3) || (rpsInput == 2 && teacherMove == 1)
                || (rpsInput == 3 && teacherMove == 2)) {
            student.gainExcuseLevel(teacher.getDefeatReward());
            prinText("\nYou won the duel! Additionaly your excuse level increased to "
                    + student.getExcuseLevel() + " and your allowed to continue.");
        } else {
            student.loseStrkes(teacher.getAttack());
            prinText("\nYou lost the duel! You also lose " + teacher.getAttack() + " strike(s). You now have "
                    + student.getStrikes() + " strike(s) left.");
            prinText("\n" + teacher.getName() + "says");
            teacher.rudeRemarks();
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
