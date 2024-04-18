import java.util.Scanner;

public class School {
    static Scanner scanner = new Scanner(System.in);
    static Student student;
    static Teacher entranceTeacher = new Teacher("Mrs. Pickering", 5, 1, 1);
    static Object iDcard = new Object("Id Card", 4);

    public static void main(String[] args) {
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
        scanner.close();
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
