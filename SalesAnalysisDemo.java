import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * The SalesAnalysisDemo class asks the user for the sales data file,
 * makes sure it exists, and then uses a SalesAnalysis object to process
 * the file and display the results.
 */
public class SalesAnalysisDemo
{
   /**
    * Main method that runs the program.
    *
    * @param args command-line arguments (not used)
    * @throws IOException if the sales data file cannot be opened
    */
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);

      System.out.print("Enter the path to the SalesData.txt file: ");
      String fileName = keyboard.nextLine();
      File file = new File(fileName);

      // Keep asking until the user enters a file that exists
      while (!file.exists())
      {
         System.out.println("The file " + fileName + " was not found.");
         System.out.print("Enter the path to the SalesData.txt file: ");
         fileName = keyboard.nextLine();
         file = new File(fileName);
      }

      SalesAnalysis sales = new SalesAnalysis(fileName);
      sales.processFile();
      sales.writeOutput();

      keyboard.close();
   }
}