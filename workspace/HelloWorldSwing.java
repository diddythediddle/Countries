import javax.swing.*;        
// creates and displays a GUI with a "Hello World" label.
public class HelloWorldSwing {
    //Precondition: Swing must be running on the Event Dispatch Thread (EDT) for thread safety.
    //Postcondition: A JFrame containing a JLabel with "Hello World" text is displayed.
    private static void createAndShowGUI() {
    
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       
        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);

       
        frame.pack();
        frame.setVisible(true);
    }
    //Precondition: The program must be executed as a Java application.
    //Postcondition: Schedules the creation and display of the GUI.
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}