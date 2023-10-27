package TextExcel;

import java.util.Scanner;

/**
*  Nat and Safiya's TextExcel Project
*/

public class TextExcel {

   // Spreadsheet rows and columns.
   private static final int ROW = 10;
   private static final int COL = 7;
   public static final Spreadsheet mySpreadsheet = new Spreadsheet(ROW, COL);

   public static void main(String args[]) {
   
      Scanner mainScanner = new Scanner(System.in);
      boolean quit = false;
   
      // Welcome message.
      System.out.println("Welcome to TextExcel!");
      System.out.println("You may type \"help\" if you need further directions or \"quit\" if you would like to close the program.");
         
      do {
         // Message that prompts user to enter input.
         System.out.print("Please enter a command.\n> ");
         String userInput = mainScanner.nextLine();
         // User input is parsed into command executor method.
         commandExecutor(userInput);
      
         // Ends program if "quit" is entered.
         if (userInput.contains("quit")) {
         
            quit = true;
            mainScanner.close();
         }
      } 
      while (quit == false);
   }
   
   /**
   *  Executes a command based on the user's input.
   *  @input - The user's input.
   */
   public static void commandExecutor(String input) {
   
      // Prints out instructions.
      if (input.contains("help")) {
      
         printInstructions(input);
      }
      // Assigns data to reference.
      else if (input.contains(" = ")) {
            
         String[] parts = input.split(" = ");
         String cellLocation = parts[0];
         String cellData = parts[1];
         
         int rowLocation = mySpreadsheet.getRowLocation(cellLocation);
         int colLocation = mySpreadsheet.getColLocation(cellLocation);
         
         mySpreadsheet.addToSpreadsheet(rowLocation, colLocation, cellData);
         
      }
      // Prints out the value of a cell.
      else if (mySpreadsheet.isCell(input)) {
         
         int rowLocation = mySpreadsheet.getRowLocation(input);
         int colLocation = mySpreadsheet.getColLocation(input);
         
         Cell myCell = new Cell(mySpreadsheet.getCellData(rowLocation, colLocation));
         System.out.println(input + " = " + myCell.toString());
      }
      else if (input.contains("sort")) {
         
         String cellLocation = input;
         mySpreadsheet.rangeEvaluation(cellLocation);
      }
      // Clears the spreadsheet.
      else if (input.contains("clear")) {
      
         // Clears entire spreadsheet.
         if (input.equals("clear")) {
         
            mySpreadsheet.clearSpreadsheet();
         }
         // Clears a range of the spreadsheet.
         else if (input.contains("-")) {
         
            String cellLocation = input;
            mySpreadsheet.rangeEvaluation(cellLocation);
         }
         // Clears specific cell of the spreadshe1et.
         else {
         
            String cellLocation = input.substring(6);
            
            int rowLocation = mySpreadsheet.getRowLocation(cellLocation);
            int colLocation = mySpreadsheet.getColLocation(cellLocation);
         
            mySpreadsheet.clearSpecificCell(rowLocation, colLocation);
         }
      }  
      
      // Prints out the spreadsheet.
      else if (input.equals("print")) {
         
         mySpreadsheet.printSpreadsheet();
      }
      // Prints out goodbye message.
      else if (input.equals("quit")) {
      
         System.out.println("Thank you for using TextExcel.");
      }
      
      // Prints error message if input is incorrectly entered.
      else {
         
         System.out.println("You have entered invalid input.");
      }
   }
   
   /**
   *  Prints out instructions for the user.
   */
   public static void printInstructions(String command) {
   
      if (command.equals("help")) {
         System.out.println("--------------------------------------------------------------------");
         System.out.println("| TextExcel Help                                                   |");
         System.out.println("| Welcome to TextExcel. Here are the list of commands.             |");
         System.out.println("|                                                                  |");
         System.out.println("| - quit                                                           |");
         System.out.println("| - help [command]                                                 |");
         System.out.println("| - print                                                          |");
         System.out.println("| - clear                                                          |");
         System.out.println("| - clear [cell]                                                   |");
         System.out.println("| - clear [range]                                                  |");
         System.out.println("| - [cell]                                                         |");
         System.out.println("| - [cell] = [number]                                              |");
         System.out.println("| - [cell] = \"[string]\"                                            |");
         System.out.println("| - [cell] = ( [formula] )                                         |");
         System.out.println("| - sorta [range]                                                  |");
         System.out.println("| - sortb [range]                                                  |");
         System.out.println("|                                                                  |");
         System.out.println("| For more specific help on each command, type \"help [command]\".   |");
         System.out.println("--------------------------------------------------------------------");
      }
      else if (command.equals("help help")) {
         System.out.println("--------------------------");
         System.out.println("| Prints the help guide. |");
         System.out.println("--------------------------");
      }
      else if (command.equals("help print")) {
         System.out.println("----------------------------------");
         System.out.println("| Prints the entire spreadsheet. |");
         System.out.println("----------------------------------");
      }
      else if (command.equals("help clear")) {
         System.out.println("----------------------------------");
         System.out.println("| Clears the entire spreadsheet. |");
         System.out.println("----------------------------------");
      }
      else if (command.equals("help clear [cell]")) {
         System.out.println("------------------------------");
         System.out.println("| Clears the specified cell. |");
         System.out.println("| Example: clear E7          |");
         System.out.println("------------------------------");
      }
      else if (command.equals("help clear [range]")) {
         System.out.println("----------------------------------------");
         System.out.println("| Clears the specified range of cells. |");
         System.out.println("| Example: clear B1 - D10              |");
         System.out.println("----------------------------------------");
      }
      else if (command.equals("help [cell]")) {
         System.out.println("---------------------------------------------");
         System.out.println("| Displays the value of the specified cell. |");
         System.out.println("| Example: G7                               |");
         System.out.println("---------------------------------------------");
      }
      else if (command.equals("help [cell] = [number]")) {
         System.out.println("--------------------------------------------------");
         System.out.println("| Assigns a numeric value to the specified cell. |");
         System.out.println("| Example: G3 = 37.1                             |");
         System.out.println("--------------------------------------------------");
      }
      else if (command.equals("help [cell] = \"[string]\"")) {
         System.out.println("--------------------------------------------------");
         System.out.println("| Assigns a string value to the speicified cell. |");
         System.out.println("| Example: E5 = \"hello\"                        |");
         System.out.println("--------------------------------------------------");
      }
      else if (command.equals("help [cell] = ( [formula] )")) {
         System.out.println("--------------------------------------------------------------------------------------------");
         System.out.println("| Assigns the formula value to the specified cell.                                         |");
         System.out.println("| Example: B4 = ( 2.0 * 5.1 )                                                              |");
         System.out.println("| Example: B5 = ( avg D1 - F7 ), The avg operator takes a cell range and computer the sum. |");
         System.out.println("| Example: B6 = ( sum D1 - F7 ), The sum operator takes a cell range and computes the sum. |");
         System.out.println("--------------------------------------------------------------------------------------------");
      }
      else if (command.equals("help sorta [range]")) {
         System.out.println("----------------------------------------------");
         System.out.println("| Sorts the cell range into ascending order. |");
         System.out.println("| Example: sorta B1 - B9                     |");
         System.out.println("----------------------------------------------");
      }
      else if (command.equals("help sortd [range]")) {
         System.out.println("-----------------------------------------------");
         System.out.println("| Sorts the cell range into descending order. |");
         System.out.println("| Example: sortd B1 - B9                      |");
         System.out.println("-----------------------------------------------");
      }
   }
}