package TextExcel;

public class Spreadsheet {
   // Variables for our chart. They are spearate strings so that we can make the chart however long or wide we want. 
   private static final String HEADER ="            |      A     |      B     |      C     |      D     |      E     |     F      |      G     |";  
   private static final String HORIZONTAL_SEPARATOR = "------------+";  
   private static final String VERTICAL_SEPARATOR = "|";
   
   int row;
   int col;
   
   /**
   * Makes the array to store spreadsheet data.
   */
   public static String[][] spreadsheetArray;
   
   public Spreadsheet(int row, int col) {
   
      this.row = row;
      this.col = col;
      
      spreadsheetArray = new String[row][col];
   }
      
   /**
   *  Returns the row location as an integer. (e.g., A1's row location is 1.)
   *  @location - The location of the cell.
   */
   public int getRowLocation(String location) {
   
      int rowLocation = 0;
      
      // Sets the row location and column location.
      if (location.length() == 3) {
         
         rowLocation = Integer.valueOf(location.charAt(1) + location.charAt(2) - 88);
      }
      else {
         
         rowLocation = location.charAt(1) - 49;
      }
      return rowLocation;
   }
   
   /**
   *  Returns the row location as an integer. (e.g., A1's column location is 1.)
   *  @location - The location of the cell.
   */
   public int getColLocation(String location) {
   
      int colLocation = 0;
      
      // Sets the row location and column location.
      if (location.length() == 3) {
         
         colLocation = location.charAt(0) - 65;
      }
      else {
         
         colLocation = location.charAt(0) - 65;
      }
      return colLocation;
   }
   
   /**
   *  Returns true if the location is an actual cell. Returns false if otherwise.
   *  @location - The location being checked.
   */
   public boolean isCell(String location) {
      
      if (Character.isLetter(location.trim().charAt(0)) && Character.isDigit(location.trim().charAt(1))
      && getRowLocation(location.trim()) < row && getColLocation(location.trim()) < col
      && !location.contains("\"")) {
         
         return true;
      }
      
      return false;
   }
   
   /**
   *  Returns a references without parenthesis.
   *  @input - The input.
   */
   public String removeParenthesis(String input) {
      
      if (input != null) {
      
         if (input.substring(input.length() - 1).equals(")") || input.substring(0, 1).equals("(")) {
         
            input = input.trim();
            input = input.replaceAll("[()]", "");
         }
         input = input.trim();
      }
      return input;
   }

   /**
   *  Returns the data without any references and with data in the references's place.
   *  @references - The equation that contains references.
   */
   public String referenceToData(String references) {
      
      String data = "";
      
      if (references != null) {
      
         String[] parts = references.split(" ");
         
         for (int i = 0; i < parts.length; i++) {
         
            if (isCell(parts[i])) {
            
               int rowLocation = getRowLocation(parts[i]);
               int colLocation = getColLocation(parts[i]);
            
               String temp = spreadsheetArray[rowLocation][colLocation];
               // Uses recursion to ensure that there are no cell references that have not been accurately converted.
               if (isCell(removeParenthesis(temp))) {
               
                  temp = referenceToData(temp);
               }
               temp = formatData(temp);
            
               parts[i] = temp;
            }
         }
         // Assembles array into string.
         for (String partOfData : parts) {
         
            data += partOfData + " ";
         }
         
         data = data.trim();
      }
      return data;
   }

   /**
   *  Returns cell temp based on location.
   *  @rowLocation - The row's location.
   *  @colLocation - The column's location.
   */
   public String getCellData(int rowLocation, int colLocation) {
            
      return spreadsheetArray[rowLocation][colLocation]; 
   }   
   
   /**
   *  Clears the temp of a specific cell.
   *  @location - The location of the cell.
   */
   public void clearSpecificCell(int rowLocation, int colLocation) {
   
      // Clears the temp for that location.
      spreadsheetArray[rowLocation][colLocation] = null;
   }
   
   /**
   *  Clears all the temp of the spreadsheet.
   */
   public void clearSpreadsheet() {
   
      for (int r = 0; r < row; r++) {
      
         for (int c = 0; c < col; c++) {
         
            spreadsheetArray[r][c] = null;
         }
      }
   }
   
   /**
   *  Clears a range of cells.
   *  @topLeftCell - The top left cell of the rectangular range.
   *  @bottomRightCell - The bottom right cell of the rectangular range.
   */
   public void clearRange(String topLeftCell, String bottomRightCell) {
   
      for (int r = getRowLocation(topLeftCell); r < getRowLocation(bottomRightCell) + 1; r++) {
      
         for (int c = getColLocation(topLeftCell); c < getColLocation(bottomRightCell) + 1; c++) {
            
            clearSpecificCell(r, c);
         }
      }
   }
   
   /**
   *  Returns the sum or average of a range.
   *  @sum - If true, the sum is being evaluated.
   *  @average - If true, the average is being evaluated.
   *  @topLeftCell - The top left cell of the rectangular range.
   *  @bottomRightCell - The bottom right cell of the rectangular range.
   */
   public double getSumOrAvg(boolean sum, boolean average, String topLeftCell, String bottomRightCell) {
   
      int count = 0;
      double result = 0.0;
   
      for (int r = getRowLocation(topLeftCell); r < getRowLocation(bottomRightCell) + 1; r++) {
      
         for (int c = getColLocation(topLeftCell); c < getColLocation(bottomRightCell) + 1; c++) {
         
            String temp = referenceToData(getCellData(r, c));
            Cell myCell = new Cell(temp);
            temp = formatData(temp);
            
            // Adds each cell to result.
            result += Double.parseDouble(temp);
            // Counts amount of cells being evaluated.
            count++;
         }
      }
      // Calculates average.
      if (average && !sum) {
      
         result /= count;
      }
      return result;
   }
   
   /**
   *  Sorts a range of cells by ascending or descending order.
   *  @ascending - If true, sort cells by ascending order.
   *  @descending - If true, sort cells by descending order.
   *  @topLeftCell - The top left cell of the rectangular range.
   *  @bottomRightCell - The bottom right cell of the rectangular range.
   */
   public void sort (boolean ascending, boolean descending, String topLeftCell, String bottomRightCell) {
   
      for (int r = getRowLocation(topLeftCell); r < getRowLocation(bottomRightCell) + 1; r++) {
      
         for(int c = getColLocation(topLeftCell); c < getColLocation(bottomRightCell) + 1; c++) {
         
            for(int i = getRowLocation(topLeftCell); i < getRowLocation(bottomRightCell) + 1; i++) {
            
               for(int j = getColLocation(topLeftCell); j < getColLocation(bottomRightCell) + 1; j++) {
                  
                  if (ascending) {
                  
                     if (Double.parseDouble(spreadsheetArray[r][c]) < Double.parseDouble(spreadsheetArray[i][j])) {
                     
                        // Swaps.
                        String temp = spreadsheetArray[r][c];
                        spreadsheetArray[r][c] = spreadsheetArray[i][j];
                        spreadsheetArray[i][j] = temp;
                     }
                  }
                  else if (descending) {
                  
                     if (Double.parseDouble(spreadsheetArray[r][c]) > Double.parseDouble(spreadsheetArray[i][j])) {
                     
                        // Swaps.
                        String temp = spreadsheetArray[r][c];
                        spreadsheetArray[r][c] = spreadsheetArray[i][j];
                        spreadsheetArray[i][j] = temp;
                     }
                  }
               }
            }
         }
      }
   }
   
   /**
   *  Returns the data after evaluating either sum, average, or after clearing the range of cells.
   *  @data - The data being evaluated.
   */
   public String rangeEvaluation(String data) {
   
      int count = 0;
      double result = 0.0;
      
      boolean sum = false;
      boolean avg = false;
      boolean clear = false;
      boolean sorta = false;
      boolean sortd = false;
      
      // Detects which evaluation is to be completed.
      if (data.contains("sum")) {
      
         data = data.replaceAll("sum", "");
         sum = true;
      }
      else if (data.contains("avg")) {
      
         data = data.replaceAll("avg", "");
         avg = true;
      }
      else if (data.contains("sort")) {
      
         if (data.contains("sorta")) {
         
            data = data.replaceAll("sorta", ""); 
            sorta = true;
         }
         else if (data.contains("sortd")) {
         
            data = data.replaceAll("sortd", "");
            sortd = true;
         }
      }
      else if (data.contains("clear")) {
      
         data = data.replaceAll("clear", "");
         clear = true;
      }
      // Splits data into top left cell and bottom right cell locations.
      String[] parts = data.split("-");
      String topLeftCell = removeParenthesis(parts[0]);
      String bottomRightCell = removeParenthesis(parts[1]);
      
      if (sum || avg) {
      
         data = String.valueOf(getSumOrAvg(sum, avg, topLeftCell, bottomRightCell));
      }
      else if (sorta || sortd) {
      
         sort(sorta, sortd, topLeftCell, bottomRightCell);
      }
      else if (clear) {
      
         clearRange(topLeftCell, bottomRightCell);
         // data = null;
      }
   
      return data;
   }
      
   /**
   *  Formats the data properly (by solving, converting to double, removing quotations) for the spreadsheet.
   */
   public String formatData(String data) {
   
      // If the cell is empty, return a space.
      if (data == null) {
      
         data = "            ";
      }
      // Checks if input is a formula.
      else if (data.substring(0, 1).equals("(") && data.substring(data.length() - 1).equals(")")) {
         
         FormulaCell myFormulaCell = new FormulaCell(data);
         data = myFormulaCell.getFormattedFormula();
      }
      // Checks if input is a string.
      else if (data.substring(0, 1).equals("\"") && data.substring(data.length() - 1).equals("\"")) {
      
         StringCell myStringCell = new StringCell(data);
         data = myStringCell.getFormattedString();
      }
      // Checks if input is a number.
      else {
         try {
            NumberCell myNumberCell = new NumberCell(data);
            data = myNumberCell.getFormattedNumber();
         }
         catch (Exception e) {
            data = null;
         }
      }
      return data;
   }

   /**
   *  Adds temp to the spreadsheet based on location.
   *  @rowLocation - The row location of the cell.
   *  @colLocation - The column location of the cell.
   *  @temp - The temp being added to the cell.
   */
   public void addToSpreadsheet(int rowLocation, int colLocation, String data) {
   
      // Adds the temp to the spreadsheet array.
      spreadsheetArray[rowLocation][colLocation] = data;
   }
   
   /**
   *  Creates and prints a spreadsheet based on row and column.
   */
   public void printSpreadsheet() {
   
      // Prints header.
      System.out.println(HEADER);
      System.out.println(HORIZONTAL_SEPARATOR + HORIZONTAL_SEPARATOR.repeat(col));
   
      // Prints cells.
      for (int r = 0; r < row; r++) {
      
         String dataRow = "";
         String separatorRow = "";
         String rowNumber = Integer.toString(r + 1);
         
         Cell rowNumbers = new Cell(rowNumber);
      
         dataRow += rowNumbers.formatCell() + VERTICAL_SEPARATOR;
         separatorRow += HORIZONTAL_SEPARATOR;
      
         for (int c = 0; c < col; c++) {
         
            String data = getCellData(r, c);
            
            if (data != null && !data.contains("\"")) {
            
               if (data.contains ("-") && data.contains("sum ") || data.contains("avg ")) {
               
                  // Sets the data to the sum or average of the range of cells.
                  data = rangeEvaluation(data);
                  data = formatData(data);
               }
               else {
               
                  // Sets the data by converting the references into data.
                  data = referenceToData(data);
                  data = formatData(data);
               }
            }
            
            data = formatData(data);
            Cell myCell = new Cell(data);
            data = myCell.formatCell();
            
            dataRow += data + VERTICAL_SEPARATOR;
            separatorRow += HORIZONTAL_SEPARATOR;
         }
         System.out.println(dataRow);
         System.out.println(separatorRow);
      }
   }
}