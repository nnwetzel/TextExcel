package TextExcel;

public class StringCell extends Cell {

   public StringCell(String data) {
   
      super(data);
   }  
     
   /**
   *  Formats by the string by removing quotation marks.
   */
   public String getFormattedString() {
   
      data = super.data.substring(1, data.length() - 1);
      return data;
   }
}