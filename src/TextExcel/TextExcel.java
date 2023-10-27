package TextExcel;

import java.util.Scanner;

/**
*  Nat Wetzel's TextExcel
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
      
         printGuide(input);
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
   *  Formats the help guide instructions into a box.
   *  @instructions - The instructions.
   */
   public static void formatGuide(String guide) {
      int highestLength = 0;
      String[] guideArray = guide.split("\n");

      for (int i = 0; guideArray.length > i; i++) {
         if (guideArray[i].length() > highestLength) {
            highestLength = guideArray[i].length();
         }
      }
      System.out.print("+");
      for (int i = -2; highestLength > i; i++) {
         System.out.print("-");
      }
      System.out.println("+");

      for (int i = 0; guideArray.length > i; i++) {
         String spacing = "";
         for (int j = 0; (highestLength - guideArray[i].length()) > j; j++) {
            spacing = spacing + " ";
         }
         System.out.println("| " + guideArray[i] + spacing + " |");
         spacing = "";
      }

      System.out.print("+");
      for (int i = -2; highestLength > i; i++) {
         System.out.print("-");
      }
      System.out.println("+");
   }
   
   /**
   *  Prints out instructions for the user.
   */
   public static void printGuide(String command) {
   
      if (command.equals("help")) {
         String help = "Welcome to TextExcel. Here are the list of commands.\n\n1. quit\n2. help [command]\n3. print\n4. clear\n5. clear [cell]\n6. clear [range]\n7. [cell]\n8. [cell] = [number]\n9. [cell] = \"[string]\"\n10. [cell] = ( [formula] )\n11. sorta [range]\n12. sortd [range]\n\nFor more specific help on each command, type \"help [number]\" or \"help [command]\".";
         formatGuide(help);
      }
      else if (command.equals("help 1")) {
         String helpQuit = "Quits the spreadsheet application.";
         formatGuide(helpQuit);
      }
      else if (command.equals("help 2") | command.equals("help help [command]")) {
         String helpHelp = "Prints specific help on each command.";
         formatGuide(helpHelp);
      }
      else if (command.equals("help 3") | command.equals("help print")) {
         String helpPrint = "Prints the entire spreadsheet.";
         formatGuide(helpPrint);
      }
      else if (command.equals("help 4") | command.equals("help clear")) {
         String helpClear = "Clears the entire spreadsheet.";
         formatGuide(helpClear);
      }
      else if (command.equals("help 5") | command.equals("help clear [cell]")) {
         String helpClearCell = "Clears the specified cell.\n\nExample: clear E7";
         formatGuide(helpClearCell);
      }
      else if (command.equals("help 6") | command.equals("help clear [range]")) {
         String helpClearRange = "Clears the specified range of cells.\n\nExample: clear B1 - D10";
         formatGuide(helpClearRange);
      }
      else if (command.equals("help 7") | command.equals("help [cell]")) {
         String helpCell = "Displays the value of the specified cell.\n\nExample: G7";
         formatGuide(helpCell);
      }
      else if (command.equals("help 8") | command.equals("help [cell] = [number]")) {
         String helpCellNumber = "Assigns a numeric value to the specified cell.\n\nExample: G3 = 37.1";
         formatGuide(helpCellNumber);
      }
      else if (command.equals("help 9") | command.equals("help [cell] = \"[string]\"")) {
         String helpCellString = "Assigns a string value to the specified cell.\n\nExample: E5 = \"hello\"";
         formatGuide(helpCellString);
      }
      else if (command.equals("help 10") | command.equals("help [cell] = ( [formula] )")) {
         String helpCellFormula = "Assigns the formula value to the specified cell.\n\nExample: B4 = ( 2.0 * 5.1 )\nExample: B5 = ( avg D1 - F7 ), The avg operator takes a cell range and computer the sum.\nExample: B6 = ( sum D1 - F7 ), The sum operator takes a cell range and computes the sum.";
         formatGuide(helpCellFormula);
      }
      else if (command.equals("help 11") | command.equals("help sorta [range]")) {
         String helpSortaRange = "Sorts the cell range into ascending order.\n\nExample: sorta B1 - B9";
         formatGuide(helpSortaRange);
      }
      else if (command.equals("help 12") | command.equals("help sortd [range]")) {
         String helpSortdRange = "Sorts the cell range into descending order.\n\nExample: sortd B1 - B9";
         formatGuide(helpSortdRange);
      }
   }
}