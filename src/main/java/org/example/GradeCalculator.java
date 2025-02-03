package org.example;
import java.io.*;
import java.util.Scanner;

public class GradeCalculator{

    //declaring the final value of grade
    public static final double MIN_GRADE = 50;
    public static final String FILE_DIR = "target/records";

    public static void main(String[] args) {

        //declaring variables
        String name;
        String subject;
        double[] grades = new double[3];
        String[] terms = {"Prelim", "Midterm", "Finals"};

        Scanner gaby = new Scanner(System.in);

        //prompt the user to enter name
        System.out.print("Enter name: ");
        name = gaby.nextLine();

        //prompt the user to enter subject
        System.out.print("Enter subject: ");
        subject = gaby.nextLine();

        //gather grades for each term
        for (int i = 0; i < grades.length; i++) {
            System.out.print("Enter grade for " + terms[i] + ": ");

            //to handle invalid grade input
            try {
                grades[i] = gaby.nextDouble();
            } catch (Exception e) {
                System.out.println("Invalid grade");
                gaby.nextLine();
                i--;
            }

            //if statement to check if the entered grade is lower than 50.
            if (grades[i] < MIN_GRADE) {
                System.out.println("Invalid grade");
                i--;
            }
        }

        //format the result
        String result = String.format(""" 
                Name: %s 
                Subject: %s 
                Prelim: %.2f 
                Midterm: %.2f 
                Finals: %.2f 
                Final Rating: %.2f 
                """, name, subject, grades[0], grades[1], grades[2], getFinalRating(grades));

        //display the result
        System.out.println("Grade Computation Results");
        System.out.println(result);

        // Writing the result to a file
        writeToFile(name, result);
        System.out.println("File saved.");

        // Reading all files to display stored records
        System.out.println("\n---Reading Files---");
        readAllFiles();
    }

    //compute for the final rating
    public static double getFinalRating(double[] termGrades) {
        return termGrades[0] * .3 + termGrades[1] * .3 + termGrades[2] * .4;
    }

    //to write the data in a file
    public static void writeToFile(String fileName, String data) {
        File folder = new File(FILE_DIR);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File file = new File(folder, fileName);
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(data);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }


    //reads and displays all files in the specified directory
    public static void readAllFiles() {
        File folder = new File(FILE_DIR);
        File[] studentGradeFiles = folder.listFiles();
        if (studentGradeFiles != null) {
            for (File studentGradeFile : studentGradeFiles) {
                if (studentGradeFile.isFile()) {
                    readFile(studentGradeFile);
                }
            }
        }
    }

    //reads and displays the contents of a file
    public static void readFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            System.out.println("--------");
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}