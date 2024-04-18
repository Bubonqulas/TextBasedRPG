/*

    Title: Object Class 
    Authors:  Hassan Darky

*/
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
