package TextExcel;

public class FormulaCell extends Cell {

   public FormulaCell (String data) {
   
      super(data);
   }
   
   /**
   *  Formats the formula by solving.
   */
   public String getFormattedFormula() {
      
      String data = super.data;
      // Removes the parenthesis.
      data = data.replaceAll("[(]", "");
      data = data.replaceAll("[)]", "");
      
      // Splits data into operands.
      String[] operands = data.split("\\s[-+*/]\\s");
      // Splits data into operators.
      String[] operators = data.split("\\s(-?\\d+(\\.\\d+)?)\\s");
      
      // Sets result as first operand.
      double result = Double.parseDouble(operands[0]);
      
      // Solves formula accordingly.
      for (int i = 1; i < operators.length; i++) {
         
         if (operators[i].equals("-")) {
            
            result -= Double.parseDouble(operands[i]);
         }
         else if (operators[i].equals("+")) {
            
            result += Double.parseDouble(operands[i]);
         }
         else if (operators[i].equals("*")) {
            
            result *= Double.parseDouble(operands[i]);
         }
         else if (operators[i].equals("/")) {
            
            result /= Double.parseDouble(operands[i]);
         }
      }
      data = String.valueOf(result);
         
      return data;
   }
}