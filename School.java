
/*
    Title: School Class 
    Authors:  Hassan Darky
*/
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents a the main class for a school RPG game where a student tries to
 * get to class on time
 * while encountering various challenges.
 */
public class School {
    static Scanner scanner = new Scanner(System.in);
    static Student student;
    static Teacher entranceTeacher = new Teacher("Mrs. Pickering", 6, 1, 1);
    static Object iDcard = new Object("Id Card", 4);
    static Object sickNote = new Object("Fake Sicknote", 4);
    static Object rizz = new Object("Rizz", 6);
    static Object rockPaperScissors = new Object("Rock Paper Scissors Duel", 0);
    static Object unoReverse = new Object("Uno Reverse Card", 0);
    private static ArrayList<Object> randomObjects;

    /**
     * The main method of the School class.
     * Initializes the game and starts it.
     * 
     * @param args
     */
    public static void main(String[] args) {
        randomObjects = new ArrayList<>();
        randomObjects.add(sickNote);
        randomObjects.add(rizz);
        randomObjects.add(rockPaperScissors);
        randomObjects.add(unoReverse);

        start();

    }

    // INPUT

    /**
     * Starts the game by initializing the player and displaying initial
     * information.
     */
    public static void start() {
        prinText("Welcome to the 'Late to School' RPG Game!");
        prinText("\nEnter your name: ");
        String playerName = scanner.nextLine();
        student = new Student(playerName);
        student.addToBackpack(iDcard);
        prinText("Hello " + playerName + ". Here are your current stats:\n Strikes: " + student.getStrikes()
                + "\n Excuse level: " + student.getExcuseLevel() + "\n Objects: ");
        for (Object obj : student.backpack) {
            prinText(obj.getName() + "\n    ~");
        }
        prinText(
                "\n\nYou are running late to school. Your goal is to get to class on time without losing all your strikes.");
        prinText("\nYou walk to the entrance and encounter " + entranceTeacher.getName()
                + " checking for ID cards.");
        entranceEncounter();
    }

    // PROCESSING

    /**
     * Method to handle the encounter at the school entrance with the teacher.
     */

    public static void entranceEncounter() {
        int input = 0;
        while (input < 1 || input > 3) {
            prinText("\n\nWhat do you want to do?");
            prinText("\n    1. Make an excuse (" + entranceTeacher.getName() + " excuse blocker: "
                    + entranceTeacher.getHealth() + ")");
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
                prinText("\nInvalid. Please enter a valid number between 1 and 3. ");
            }
        }
        stageTwo();
    }

    /**
     * Method to handle the second encounter and stage fo the game.
     */
    public static void stageTwo() {
        prinText(
                "\n\nYou continue walking after the entrance and then spot something on the floor.\n Do you want to approach the object? (1. Yes, 2. No) ");
        int objectInput = 0;
        while (objectInput < 1 || objectInput > 2) {
            String choiceThree = scanner.nextLine();
            objectInput = checkInput(choiceThree, 1, 2);
            if (objectInput == 1) {
                encounterObject();
                break;
            } else if (objectInput == 99) {
                prinText("\nInvalid. Please enter a valid number between 1 and 2. ");
            }
        }

        Teacher newTeacher = new Teacher("Mr. Hager", 14, 2, 2);
        prinText("\n\nYou continue walking and encounter " + newTeacher.getName() + ".");

        boolean complete = false;
        int input = 0;
        while (input < 1 || input > 3 || !complete) {

            prinText("\n\nWhat do you want to do?");
            prinText("\n    1. Make an excuse (" + newTeacher.getName() + " excuse blocker: " + newTeacher.getHealth()
                    + ")");
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
                student.runAway(newTeacher, student);
                break;
            } else if (input == 99) {
                prinText("\nInvalid. Please enter a valid number between 1 and 3. ");
            }

        }
        encounterStaffConnector(newTeacher);
    }

    /**
     * Method to handle the encounter at the staff connector and the third stage of
     * the game.
     * 
     * @param prevteacher The teacher encountered before reaching the staff
     *                    connector.
     */
    private static void encounterStaffConnector(Teacher prevteacher) {
        prinText("\n\nAfter your previous encounter with " + prevteacher.getName()
                + " you proceed further into the school building.");
        prinText(
                "\n\nAs you walk by the learning commons, you notice an open door to the staff connector.\nYou think you spot an object on the table. \nThere is a 70% chance you will get caught by another teacher but you go anyway.");
        encounterObject();
        Random rand = new Random();
        int chance = rand.nextInt(100) + 1;
        if (chance <= 70) {

            prinText("\n\nAs you try to leave the connector, you hear footsteps approaching.");
            Teacher staffTeacher = new Teacher("Mr. Mitchell", 16, 2, 2);
            prinText("\nYou encounter " + staffTeacher.getName() + ".");
            boolean complete = false;
            int input = 0;
            while (input < 1 || input > 2 || !complete) {
                prinText("\n\nWhat do you want to do?");
                prinText("\n    1. Make an excuse (" + staffTeacher.getName() + " excuse blocker: "
                        + staffTeacher.getHealth() + ")");
                prinText("\n    2. Use an object");
                prinText("\n    3. Run away ");

                String choiceThree = scanner.nextLine();
                input = checkInput(choiceThree, 1, 3);
                if (input == 1) {
                    student.makeExcuse(staffTeacher);
                    break;
                } else if (input == 2) {
                    prinText("\nYou decide to use an object. ");
                    complete = useObject(complete, staffTeacher);
                } else if (input == 3) {
                    student.runAway(staffTeacher, student);
                    break;
                } else if (input == 99) {
                    prinText("\nInvalid. Please enter a valid number between 1 and 3. ");
                }
            }
        } else {
            prinText("\n\nYou cautiously peek around and exit before you get caught.");
        }
        stageThree();
    }

    /**
     * Method for the third stage of the game.
     */
    public static void stageThree() {
        prinText(
                "\n\nYou continue walking after the staff connector. Determined to make it to class on time, you start running towards your classroom.");
        Student randStudent = new Student("Dilshaan");
        prinText("\n\nAs you sprint down the hallway, you notice " + randStudent.getName()
                + "  with something sticking out of their pocket.\nDo you attempt to pickpocket them...? (1. Yes, 2. No) ");
        int objectInput = 0;
        while (objectInput < 1 || objectInput > 2) {
            String choiceThree = scanner.nextLine();
            objectInput = checkInput(choiceThree, 1, 2);
            if (objectInput == 1) {
                encounterObject();
                break;
            } else if (objectInput == 99) {
                prinText("\nInvalid. Please enter a valid number between 1 and 2. ");
            }
        }
        Teacher mrCard = new Teacher("Mr. Card", 20, 4, 2);

        prinText("\n\nYou see your classroom door in the distance and continue sprinting towards it. Suddenly, a wild "
                + mrCard.getName() + " appears!");

        boolean complete = false;
        while (!complete) {
            prinText("\n\nWhat do you want to do?");
            prinText("\n    1. Make an excuse(" + mrCard.getName() + " excuse blocker: " + mrCard.getHealth() + ")");
            prinText("\n    2. Use an Object");
            prinText("\n    3. Run away");
            prinText("\n    4. Listen to " + mrCard.getName() + " ");

            String choiceThree = scanner.nextLine();
            int inputThree = checkInput(choiceThree, 1, 4);
            switch (inputThree) {
                case 1:
                    student.makeExcuse(mrCard);
                    complete = true;
                    break;
                case 2:
                    complete = useObject(complete, mrCard);
                    break;
                case 3:
                    student.runAway(mrCard, student);
                    complete = true;
                    break;
                case 4:
                    mrCard.listenToStudentDeal(student, mrCard);
                    complete = true;
                    break;
                case 99:
                    prinText("\nInvalid choice. Please enter a number between 1 and 4.");
            }
        }
        finalStage();
    }

    /**
     * Method for the final stage of the game.
     */
    public static void finalStage() {
        prinText(
                "\n\nYou continue walking from the last encounter and you finally make it to your classroom just in time!");
        prinText("\nCongratulations! You won! You made it to class on time without losing all your strikes.\n");

        prinText("\nFinal Stats:");
        prinText("\nStrikes: " + student.getStrikes());
        prinText("\nExcuse Level: " + student.getExcuseLevel());
        prinText("\nObjects in Backpack:\n    ~");
        for (Object obj : student.backpack) {
            prinText(obj.getName() + "\n    ~");
        }
        System.exit(0);
    }

    /**
     * Method to use an object during an encounter.
     *
     * @param complete Indicates if the action is complete for invalid input or
     *                 looping.
     * @param teacher  The teacher involved in the encounter.
     * @return True if the action is complete, otherwise false.
     */

    public static boolean useObject(boolean complete, Teacher teacher) {
        while (true) {
            boolean secondComplete = false;

            prinText("\nChoose an object to use:");
            int index = 1;
            prinText("\n    0. Go back ");
            for (Object obj : student.backpack) {
                prinText("\n    " + index + ". " + obj.getName() + " ");
                index++;
            }

            String choiceFour = scanner.nextLine();
            int inputFour = checkInput(choiceFour, 0, student.backpack.size());
            prinText("size of backpack");
            if (inputFour == 0) {

                complete = false;
                secondComplete = true;

            } else if (inputFour == 99) {
                prinText("\nInvalid choice. Please enter a valid number between 0 and " + student.backpack.size()
                        + " or go back.");
            } else {
                Object selectedObject = student.backpack.get(inputFour - 1);
                if (selectedObject.getName().equalsIgnoreCase("ID Card")) {
                    prinText("\nYou can't use the ID card here. Please pick another object or go back.");

                } else if (selectedObject.getName().equalsIgnoreCase("Rock Paper Scissors Duel")) {
                    Object.playRockPaperScissors(student, teacher);
                    student.backpack.remove(rockPaperScissors);
                    complete = true;
                } else if (selectedObject.getName().equalsIgnoreCase("Uno Reverse Card")) {
                    prinText("\n\nYou used the Uno Reverse card. You were allowed to pass freely! ");
                    prinText(teacher.getName() + " says");
                    teacher.rudeRemarks();
                    student.backpack.remove(unoReverse);
                    complete = true;
                } else {
                    student.gainExcuseLevel(selectedObject.getExcuseBlocker());
                    prinText("\n\nYou used " + selectedObject.getName() + " on " + teacher.getName()
                            + ". Your allowed to continue. Your excuse level increased by "
                            + selectedObject.getExcuseBlocker());
                    student.backpack.remove(selectedObject);
                    complete = true;
                }
            }
            if (complete || secondComplete) {
                break;
            }
        }
        return complete;
    }

    /**
     * Method for finding an object.
     */
    private static void encounterObject() {
        Random rand = new Random();
        int chance = rand.nextInt(100) + 1;
        if (chance <= 60) {
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
/**
 * 
 * This class is the Main class of a 'Late to school' RPG game. It contains
 * methods for each stage of the game and methods for using objects.
 * 
 * It Starts with taking user input for a name and creates a new student with
 * that name paramater. In the student class the student is preassgned values
 * for excuse level and strikes.It also add an id card object to the students
 * backpack arraylist.
 * 
 * It sets up various other objects, teachers, arraylist and scanner for game
 * functionality.
 * It accessing getters from the other three classes to display information
 * related with the teachers, student and objects.
 * 
 * Encapsulation: Encapsulation is the process of hiding and organizing your
 * data through methods. In my code each class organizes and hides all the data
 * or items. We simply use methods without having to see or know how our
 * commands are being performed.
 * The Teacher class encapsulates the attributes name, Health, Attack and Defeat
 * Reward by
 * declaring them as private variables. These variables can only
 * be accessed or modified through public getter and setter methods.
 * The getter methods (getName(), getAttack(), getDefeatReward()) allow other
 * classes to access the private variables.
 * 
 * Advanced Algorithmic Structure: The printText() and checkInput() method use
 * try-catch block to handle exceptions to user inputs. It ensures that if the
 * user inputs something unexpected, the program won't crash but will handle the
 * error. For the checkInput() method it would return an error code of 99 which
 * would have a section in an if statment or switch case that would prompt the
 * user to input a valid number again .
 * 
 * Flag/code: There is no current flag code but the built in debugger in vs code
 * was used. Additionaly print statments were put in place if the program was
 * outputing different then expected reults allowing us to narrow down where the
 * problem code was. The checkInput() method also handles unexpected results.
 */