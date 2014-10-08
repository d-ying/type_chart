package typechart;

import java.io.IOException;
import java.util.Scanner;

/**
 * The main function of the program, contains all the prompts and parses the
 * user input to search for a given type.
 */
public class TypeChart {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner inputType = new Scanner(System.in);
        
        while (true) {
            
            try {
                
                TypeTable table = new TypeTable("table.csv");
                
                System.out.println("***************************************");
                System.out.println("Please enter the desired type (no caps),");
                System.out.println("seperate dual types with a comma and NO");
                System.out.println("space.");
                System.out.println("ex: type1,type2");
                System.out.println("Type done to exit the program.");
                System.out.println("***************************************");
                System.out.println("");
                
                String type = inputType.next();
                String[] types = type.split(",");
                
                if (type.equals("done") || type.equals("Done")) {
                    System.out.println("Thank you for using this program!");
                    break;
                }
                
                if (types.length == 1) {
                    table.find(types[0], "none");
                }
                
                if (types.length == 2) {
                    table.find(types[0], types[1]);
                }
                
                if (types.length > 2) {
                    System.out.println("");
                    System.out.println("***************************************");
                    System.out.println("Sorry this program only supports up to a");
                    System.out.println("maximum combination of 2 types.");
                    System.out.println("Please try again.");
                }
                
            } catch (IOException | NullPointerException e) {
                System.out.println("");
                System.out.println("***************************************");
                System.out.println("Sorry the program has encountered an");
                System.out.println("error, please check the text file to");
                System.out.println("ensure it has the proper file name");
                System.out.println("(table.txt), exists/is in the same folder");
                System.out.println("as the program, and that its contents are");
                System.out.println("formatted correctly (top row and leftmost");
                System.out.println("column are the types, all the values are");
                System.out.println("seperated with commas (no spaces), and the");
                System.out.println("top corner is an blank space).");
                System.out.println("***************************************");
                break;
            }
        }
    } 
}
