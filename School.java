
/*

    Title: School Class 
    Authors:  Hassan Darky

*/
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class School {
    static Scanner scanner = new Scanner(System.in);
    static Student student;
    static Teacher entranceTeacher = new Teacher("Mrs. Pickering", 5, 1, 1);
    static Object iDcard = new Object("Id Card", 4);
    static Object sickNote = new Object("Fake Sicknote", 6);
    static Object rizz = new Object("rizz", 8);
    static Object rockPaperScissors = new Object("Rock Paper Scissors", 10);
    static Object unoReverse = new Object("Uno Reverse", 100);
    private static ArrayList<Object> randomObjects;

    public static void main(String[] args) {
        randomObjects = new ArrayList<>();
        randomObjects.add(sickNote);
        randomObjects.add(rizz);
        randomObjects.add(rockPaperScissors);
        randomObjects.add(unoReverse);

        start();

    }

    public static void start() {
        prinText("Welcome to the Late to School RPG Game!");
        prinText("\nEnter your name: ");
        String playerName = scanner.nextLine();
        student = new Student(playerName);
        student.addToBackpack(iDcard);
        prinText("Hello " + playerName + ". Here are your current stats:\n Strikes: " + student.getStrikes()
                + "\n Excuse level: " + student.getExcuseLevel() + "\n Objects: " + iDcard.getName());
        prinText(
                "\n\nYou are running late to school. Your goal is to get to class on time without losing all your strikes.");
        prinText("\nYou walk to the entrance and encounter " + entranceTeacher.getName()
                + " checking for ID cards.");
        entranceEncounter();
    }

    public static void entranceEncounter() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            prinText("\n\nWhat do you want to do?");
            prinText("\n    1. Make an excuse ");
            prinText("\n    2. Search for ID card in backpack ");
            prinText("\n    3. Run away ");

            String choice = scanner.nextLine();
            int input = checkInput(choice, 1, 3);

            switch (input) {
                case 0:
                    prinText("\nInvalid. Please enter a valid number between 1 to 3. ");

                case 1:
                    student.makeExcuse(entranceTeacher.getHealth(), entranceTeacher.getAttack(),
                            entranceTeacher.getDefeatReward());

                    break;
                case 2:
                    student.searchBackpack(entranceTeacher.getAttack(), entranceTeacher.getDefeatReward());
                    break;
                case 3:
                    student.runStartAway();
                    break;
            }
            break;
        }

        stageTwo();

    }

    public static void stageTwo() {
        prinText(
                "\n\nYou successfully passed the entrance. You walk for a bit and then spot something on the floor.\nYou decide to walk towards what you think is an object.");
        encounterObject();
        prinText("\n\nYou continue walking and encounter a new teacher.");
        Teacher newTeacher = new Teacher("Mr. Hager", 6, 2, 2);

        while (true) {
            prinText("\n\nWhat do you want to do?");
            prinText("\n    1. Make an excuse ");
            prinText("\n    2. Use an Object ");
            prinText("\n    3. Run away ");

            String choiceTwo = scanner.nextLine();
            int input = checkInput(choiceTwo, 1, 3);

            switch (input) {
                case 0:
                    prinText("\nInvalid. Please enter a valid number between 1 to 3. ");
                case 1:
                    student.makeExcuse(newTeacher.getHealth(), newTeacher.getAttack(), newTeacher.getDefeatReward());
                    break;
                case 2:
                    useObject();
                    break;
                case 3:
                    student.runAway(2);
                    break;
            }
            break;
        }
        scanner.close();
    }

    public static void useObject() {
        // Display objects in backpack
        prinText("\nChoose an object to use:");
        int index = 1;
        prinText("\n    0. Go back ");
        for (Object obj : student.backpack) {
            prinText("\n    " + index + ". " + obj.getName());
            index++;
        }

        int choice = Integer.parseInt(scanner.nextLine());
        if (choice < 0 || choice > student.backpack.size()) {
            prinText("Invalid choice. Please enter a number between 0 and " + student.backpack.size());
            useObject();
        } else if (choice == 0) {
            // Go back option selected, return to previous stage

        } else {
            Object selectedObject = student.backpack.get(choice - 1);
            if (selectedObject.getName().equalsIgnoreCase("ID Card")) {
                prinText("\nYou can't use the ID card here. Please pick another object.");
                useObject();
            } else if (selectedObject.getName().equalsIgnoreCase("Rock Paper Scissors")) {
                playRockPaperScissors();
            } else if (selectedObject.getName().equalsIgnoreCase("Uno Reverse")) {
                student.gainExcuseLevel(100); // Enhance excuse level to max
                prinText("\nYou used Uno Reverse. You pass freely!");
            } else {
                student.gainExcuseLevel(selectedObject.getExcuseBlocker());
                prinText("\nYou used " + selectedObject.getName() + ". Your excuse level increased by "
                        + selectedObject.getExcuseBlocker());
            }
        }
    }

    private static void encounterObject() {
        Random rand = new Random();
        int chance = rand.nextInt(100) + 1;
        if (chance <= 50) { // 50% chance of encountering an object
            int index = rand.nextInt(randomObjects.size());
            Object foundObject = randomObjects.get(index);
            randomObjects.remove(foundObject);
            student.addToBackpack(foundObject);
            prinText("\nYou found " + foundObject.getName() + " on the floor!\nHere are "
                    + "the stats of the object: ");
            foundObject.revealObject();

        } else {
            prinText("\nYou were delusional and there was nothing on the floor.");
        }
    }

    public static void playRockPaperScissors() {
        prinText("\nYou chose to play Rock Paper Scissors.");
        prinText("\nChoose your move (1. Rock, 2. Paper, 3. Scissors): ");
        int playerMove = Integer.parseInt(scanner.nextLine());
        int teacherMove = new Random().nextInt(3) + 1; // Random move for teacher (1 for Rock, 2 for Paper, 3 for
                                                       // Scissors)

        // Compare moves
        if (playerMove == teacherMove) {
            prinText("\nIt's a tie!");
        } else if ((playerMove == 1 && teacherMove == 3) || (playerMove == 2 && teacherMove == 1)
                || (playerMove == 3 && teacherMove == 2)) {
            prinText("\nYou win! Your excuse level remains the same.");
        } else {
            student.gainExcuseLevel(10); // Enhance excuse level if student loses
            prinText("\nYou lose! Your excuse level increased by 10.");
        }
    }

    // checks for valid input
    public static int checkInput(String input, int min, int max) {
        try {
            int number = Integer.parseInt(input);
            // Check if the number is within the specified range
            if (number >= min && number <= max) {
                return number;
            } else {
                return 0;
            }
        } catch (NumberFormatException e) {
            // Input is not an integer
            return 0;
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
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
