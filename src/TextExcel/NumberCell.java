package TextExcel;

public class NumberCell extends Cell{

   public NumberCell (String data) {
   
      super(data);
   }
   /**
   *  Formats the number by ensuring that is a double.
   */
   public String getFormattedNumber() {
   
      double temp = Double.parseDouble(super.data);
      data = String.valueOf(temp);
      
      return data;
   }
}