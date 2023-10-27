# Text Excel

Welcome to Text Excel. This is a simple text-based spreadsheet software.

**Command Summary:**
| Command  | Example Input | Description
| ------------- | ------------- | ---------------------------------- | 
| quit | quit | Quits the spreadsheet application. |
| help | help | Prints the help guide. |
| print | print | Prints the entire spreadsheet spreadsheet. |
| clear | clear | Clears the entire spreadsheet. |
| clear [cell] | clear E7 | Clears the named cell. |
| clear [range] | clear B1 - D10 | Clears the specified range of cells. |
| [cell] | G7 | Displays the inputted value of the specified cell. |
| [cell] = [number] | G3 = 37.1 | Assigns a numeric value to the specified cell. |
| [cell] = "[string]" | E5 = "test" | Assigns a string value to the specified cell. |
| [cell] = ( [formula] ) | B4 = ( 2.0 * 5.1 ) | Assigns the formula value to the specified cell. |
| [cell] = ( [formula] ) | B5 = ( avg D1 - F7 ) | The **avg** operator takes a single cell range and computes the sum. |
| [cell] = ( [formula] ) | B6 = ( sum D1 - F7 ) | The **sum** operator takes a single cell range and computes the average. |
| sorta [range] | sorta B1 - B9 | Sorts the cell range into **ascending** order. |
| sortd [range] | sortd B1 - B9 | Sorts the cell range into **descending** order. |
