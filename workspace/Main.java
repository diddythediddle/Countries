// Aditya Patil, Period 1
// This program displays information about countries using a GUI. 
// Users can review details, quiz themselves, and cycle through the country list.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Main {

    private Country[] countryArray = new Country[10]; // Array to hold Country objects
    private int index = 0; // Current index of the displayed country

    private JFrame jFrame = new JFrame("Countries");
    private ImageIcon img;
    private JLabel imageLabel;
    private JLabel outputLabel;
    private JTextField input;

    public static void main(String[] args) {
        Main gui = new Main();
        gui.loadCountries();
        gui.showCountry();
    }

    // Loads country data from a CSV file and populates the countryArray
    public void loadCountries() {
        String basePath = System.getProperty("user.dir"); // Cross-platform file path
        File file = new File(basePath + File.separator + "workspace" + File.separator + "countries-data.csv");
        Scanner scan = null;

        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.getAbsolutePath());
            return;
        }

        for (int i = 0; i < countryArray.length; i++) {
            if (scan.hasNextLine()) {
                String input = scan.nextLine();
                String[] data = input.split(",");

                // Validate CSV input
                if (data.length < 4) {
                    System.out.println("Invalid data: " + Arrays.toString(data));
                    continue;
                }

                System.out.println("Read in " + data[2]);
                countryArray[i] = new Country(data[0], data[1], data[2], data[3]);
            }
        }
        scan.close();
    }

    // Displays the current country's image in the GUI
    public void showCountry() {
        if (index < 0 || index >= countryArray.length || countryArray[index] == null) {
            System.out.println("Invalid index or uninitialized country array.");
            return;
        }

        Country currentCountry = countryArray[index];
        String imagefile = currentCountry.getImg();

        System.out.println("Printing image in showCountry: " + imagefile);
        img = new ImageIcon(System.getProperty("user.dir") + File.separator + "workspace" + File.separator + imagefile);
        imageLabel.setIcon(img);
    }

    // Handles the Next button click, cycling through countries
    public void nextButtonClick() {
        index++;
        if (index >= countryArray.length) {
            index = 0; // Wrap around to the first country
        }

        resetOutputLabel();
        showCountry();
    }

    // Handles the Review button click, displaying country details
    public void reviewButtonClick() {
        if (countryArray[index] == null) {
            System.out.println("No country data available.");
            return;
        }

        Country currentCountry = countryArray[index];
        String details = currentCountry.toString();
        System.out.println(details);
        outputLabel.setText(details);
    }

    // Handles the Quiz button click, checking the user's answer
    public void quizButtonClick() {
        if (countryArray[index] == null) {
            System.out.println("No country data available.");
            return;
        }

        resetOutputLabel();
        Country currentCountry = countryArray[index];

        System.out.println("What country is this?");
        outputLabel.setText("What country is this?");

        String userAnswer = input.getText().trim();

        if (userAnswer.equalsIgnoreCase(currentCountry.getName())) {
            System.out.println("Correct!");
            outputLabel.setText("Correct!");
        } else {
            System.out.println("Incorrect. The correct answer is " + currentCountry.getName() + ".");
            outputLabel.setText("Incorrect. The correct answer is " + currentCountry.getName() + ".");
        }

        input.setText("");
    }

    // Resets the output label to a blank state
    private void resetOutputLabel() {
        outputLabel.setText("");
    }

    // Constructor to initialize the GUI
    public Main() {
        jFrame.setLayout(new FlowLayout());
        jFrame.setSize(500, 360);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Buttons
        JButton reviewButton = new JButton("Review");
        JButton quizButton = new JButton("Quiz");
        JButton nextButton = new JButton("Next");

        // Add buttons to the frame
        jFrame.add(reviewButton);
        jFrame.add(quizButton);
        jFrame.add(nextButton);

        System.out.println("Initializing GUI with default world map image.");

        // Default image
        img = new ImageIcon(System.getProperty("user.dir") + File.separator + "workspace" + File.separator + "worldmap.jpg");
        imageLabel = new JLabel(img);

        // Output label
        outputLabel = new JLabel();

        // Input field
        input = new JTextField(20);

        // Add components to the frame
        jFrame.add(imageLabel);
        jFrame.add(outputLabel);
        jFrame.add(input);

        jFrame.setVisible(true);

        // Action Listeners
        reviewButton.addActionListener(e -> reviewButtonClick());
        quizButton.addActionListener(e -> quizButtonClick());
        nextButton.addActionListener(e -> nextButtonClick());
    }
}
