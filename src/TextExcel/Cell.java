package TextExcel;

public class Cell {

   String data = "";

   public Cell(String data) {
   
      this.data = data;
   }
      
   /**
   *  Formats the cell spacing properly for the spreadsheet.
   */
   public String formatCell() {
      
      int cellSpaceLength = 12;
      String cellData = "";
      String firstSpace = "";
      String finalSpace = "";
      String spacedData = "";
      
      if (data != null) {
      
         cellData = data.replace("", "");
      }
      // If the cell is empty, return a space.
      if (data == null) {
      
         spacedData = "            ";
      }
      // If the data's length exceeds 12, return the first 12 characters and ">".
      else if (cellData.length() > 12) {
      
         spacedData = cellData.substring(0, 11) + ">";
      }
      // Aligns the data in the cell if the data's length is odd.
      else if ((cellSpaceLength - cellData.length()) % 2 == 0) {
      
         for (int i = 0; i < (cellSpaceLength - cellData.length()) / 2; i++) {
         
            firstSpace += " ";
            finalSpace += " ";
         }
         spacedData = firstSpace + cellData + finalSpace; 
      }
      // Aligns the data in the cell if the data's length is odd.
      else if ((cellSpaceLength - cellData.length()) % 2 != 0) {
      
         for (int i = 0; i < (cellSpaceLength - cellData.length()) / 2; i++) {
         
            firstSpace += " ";
            finalSpace += " ";
         }
         
         finalSpace += " ";
         spacedData = firstSpace + cellData + finalSpace;
      }
      return spacedData;
   }
   
   /**
   *  Returns the value of the object as a string.
   */
   public String toString() {
   
      if (data == null) {
      
         data = "<empty>";
      }
      return data;
   }
}