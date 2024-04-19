
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
    static Object sickNote = new Object("Fake Sicknote", 4);
    static Object rizz = new Object("rizz", 6);
    static Object rockPaperScissors = new Object("Rock Paper Scissors", 0);
    static Object unoReverse = new Object("Uno Reverse", 0);
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
        int input = 0;
        while (input < 1 || input > 3) {
            prinText("\n\nWhat do you want to do?");
            prinText("\n    1. Make an excuse ");
            prinText("\n    2. Search for ID card in backpack ");
            prinText("\n    3. Run away ");

            String choice = scanner.nextLine();
            input = checkInput(choice, 1, 3);
            if (input == 1) {
                student.makeExcuse(entranceTeacher);
                break;
            } else if (input == 2) {
                student.searchBackpack(entranceTeacher);
                break;
            } else if (input == 3) {
                student.runStartAway();
                break;
            } else if (input == 99) {
                prinText("\nInvalid. Please enter a valid number between 1 to 3. ");
            }
        }
        stageTwo();
    }

    public static void stageTwo() {
        prinText(
                "\n\nYou successfully passed the entrance. You walk for a bit and then spot something on the floor.\n Do you want to approach the object? (1. Yes, 2. No) ");
        int objectInput = 0;
        String choiceThree = scanner.nextLine();
        objectInput = checkInput(choiceThree, 1, 2);
        prinText("text     " + objectInput);
        while (objectInput < 1 || objectInput > 2) {
            prinText("text654    " + objectInput);
            if (objectInput == 1) {
                prinText("text444      " + objectInput);
                encounterObject();
                break;
            } else if (objectInput == 99) {
                prinText("\nInvalid. Please enter a valid number between 1 to 2. ");
            }
            prinText("text3      " + objectInput);

        }
        prinText("text2      " + objectInput);

        Teacher newTeacher = new Teacher("Mr. Hager", 12, 2, 2);
        prinText("\n\nYou continue walking and encounter " + newTeacher.getName() + ".");

        boolean complete = false;
        int input = 0;
        while (input < 1 || input > 3 || !complete) {

            prinText("\n\nWhat do you want to do?");
            prinText("\n    1. Make an excuse ");
            prinText("\n    2. Use an Object ");
            prinText("\n    3. Run away ");

            String choiceTwo = scanner.nextLine();
            input = checkInput(choiceTwo, 1, 3);
            if (input == 1) {
                student.makeExcuse(newTeacher);
                break;
            } else if (input == 2) {
                complete = useObject(complete, newTeacher);
            } else if (input == 3) {
                student.runAway(newTeacher);
                break;
            } else if (input == 99) {
                prinText("\nInvalid. Please enter a valid number between 1 to 3. ");
            }

        }
        encounterStaffConnector(newTeacher);
    }

    private static void encounterStaffConnector(Teacher prevteacher) {
        prinText("\n\nYou successfully passed the previous encounter with " + prevteacher.getName()
                + " and proceed further into the school building.");
        prinText(
                "\n\nAs you walk by the learning commons, you notice an open door to the staff connector.\nYou think you spot an object on the table. \nThere is a 70% chance you will get caught by another teacher but you go anyway.");
        encounterObject();
        Random rand = new Random();
        int chance = rand.nextInt(100) + 1;
        if (chance <= 70) {

            prinText("\n\nAs you try to leave the connector, you hear footsteps approaching.");
            Teacher staffTeacher = new Teacher("Mr. Mitchel", 13, 2, 2);
            prinText("\nYou encounter " + staffTeacher.getName() + ".");
            boolean complete = false;
            int input = 0;
            while (input < 1 || input > 2 || !complete) {
                prinText("\n\nWhat do you want to do?");
                prinText("\n    1. Make an excuse ");
                prinText("\n    2. Use an object");
                prinText("\n    3. Run away ");

                String choiceThree = scanner.nextLine();
                input = checkInput(choiceThree, 1, 3);
                if (input == 1) {
                    student.makeExcuse(staffTeacher);
                    break;
                } else if (input == 2) {
                    prinText("\nYou decide to use an object.");
                    complete = useObject(complete, staffTeacher);
                } else if (input == 3) {
                    student.runAway(staffTeacher);
                    break;
                } else if (input == 99) {
                    prinText("\nInvalid. Please enter a valid number between 1 to 3. ");
                }
            }
        } else {
            prinText("\n\nYou cautiously peek around and exit before you get caught.");
        }
    }

    public static boolean useObject(boolean complete, Teacher teacher) {
        while (true) {
            boolean secondComplete = false;
            // Display objects in backpack
            prinText("\nChoose an object to use:");
            int index = 1;
            prinText("\n    0. Go back ");
            for (Object obj : student.backpack) {
                prinText("\n    " + index + ". " + obj.getName() + " ");
                index++;
            }

            String choiceTwo = scanner.nextLine();
            int inputTwo = checkInput(choiceTwo, 0, randomObjects.size());
            if (inputTwo == 0) {

                complete = false;
                secondComplete = true;

            } else if (inputTwo == 99) {
                prinText("\nInvalid choice. Please enter a valid number between 0 and " + student.backpack.size()
                        + " or go back.");
            } else {
                Object selectedObject = student.backpack.get(inputTwo - 1);
                if (selectedObject.getName().equalsIgnoreCase("ID Card")) {
                    prinText("\nYou can't use the ID card here. Please pick another object or go back.");

                } else if (selectedObject.getName().equalsIgnoreCase("Rock Paper Scissors")) {
                    Object.playRockPaperScissors(student, teacher);
                    complete = true;
                } else if (selectedObject.getName().equalsIgnoreCase("Uno Reverse")) {
                    prinText("\n\nYou used the Uno Reverse card. You allowed to pass freely! ");
                    prinText(teacher.getName() + " says");
                    teacher.rudeRemarks();
                    complete = true;
                } else {
                    student.gainExcuseLevel(selectedObject.getExcuseBlocker());
                    prinText("\n\nYou used " + selectedObject.getName() + ". Your excuse level increased by "
                            + selectedObject.getExcuseBlocker());
                    complete = true;
                }
            }
            if (complete || secondComplete) {
                break;
            }
        }
        return complete;
    }

    private static void encounterObject() {
        Random rand = new Random();
        int chance = rand.nextInt(100) + 1;
        if (chance <= 50) { // 50% chance of encountering an object
            int index = rand.nextInt(randomObjects.size());
            Object foundObject = randomObjects.get(index);
            randomObjects.remove(foundObject);
            student.addToBackpack(foundObject);
            prinText("\n\nYou found " + foundObject.getName() + "!\nHere are "
                    + "the stats of the object: ");
            foundObject.revealObject();

        } else {
            prinText("\n\nYou were delusional and there was nothing.");
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
                return 99;
            }
        } catch (NumberFormatException e) {
            // Input is not an integer
            return 99;
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
