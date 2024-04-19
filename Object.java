/*

    Title: Object Class 
    Authors:  Hassan Darky

*/

import java.util.Random;
import java.util.Scanner;

public class Object {

    private String name;
    private int excuseBlocker;

    // CONSTRUCTOR

    Object(String name, int excuseBlocker) {

        this.name = name;
        this.excuseBlocker = excuseBlocker;
    }

    public String getName() {
        return name;
    }

    public int getExcuseBlocker() {
        return excuseBlocker;
    }

    public void revealObject() {

        prinText("\n\nObject name: " + name + "\nExcuse enhancer: " + excuseBlocker);

    }

    public static void playRockPaperScissors(Student student, Teacher teacher) {
        Scanner scanner = new Scanner(System.in);
        prinText("\n\nYou challange " + teacher.getName() + " to a rock paper scissors duel.");
        prinText("\nChoose your move (1. Rock, 2. Paper, 3. Scissors): ");
        int playerMove = Integer.parseInt(scanner.nextLine()); // wasnt sure how to implent error cheking without using
                                                               // try catch or bringing the checkInput method so found
                                                               // this on a w3 school article
        int teacherMove = new Random().nextInt(3) + 1; // Random move for teacher (1 for Rock, 2 for Paper, 3 for
                                                       // Scissors)

        // Compare moves
        if (playerMove == teacherMove) {
            prinText(
                    "\nIt's a tie! You are allowed to pass without losing a strike but " + teacher.getName() + " says");
            teacher.rudeRemarks();
        } else if ((playerMove == 1 && teacherMove == 3) || (playerMove == 2 && teacherMove == 1)
                || (playerMove == 3 && teacherMove == 2)) {
            student.gainExcuseLevel(teacher.getDefeatReward());
            prinText("\nYou won the duel! Additionaly your excuse level increased to "
                    + student.getExcuseLevel() + " and your allowed to continue.");
        } else {
            student.loseStrkes(teacher.getAttack());
            prinText("\nYou lost the duel! You also lose " + teacher.getAttack() + " strike(s). You now have "
                    + student.getStrikes() + " strikes left.");
            prinText("\n" + teacher.getName() + "says");
            teacher.rudeRemarks();
        }
    }

    // Made this method with the help of stack overflow. It basically takes your
    // string that you want to print out and prints it letter by letter for a colol
    // effect.
    // It goes through each charecter of the string through a loop and prints it out
    // and uses thread.sleep to wait between each charecter.
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
