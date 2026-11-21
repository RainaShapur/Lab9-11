import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * The SalesAnalysis class reads a file of daily sales figures, one week per
 * line with seven comma-separated values, totals each week, and prints a
 * weekly and monthly summary.
 */
public class SalesAnalysis
{
   /** Number of weeks of sales data in a month. */
   public static final int WEEKS_IN_MONTH = 4;

   private double[] weeklyTotal;   // total sales for each week
   private int lineNumber = 0;     // index of the next week to fill
   private String inputFile;       // name/path of the sales data file

   /**
    * Constructor that stores the input file name and creates the
    * weeklyTotal array sized to WEEKS_IN_MONTH.
    *
    * @param inputFile the name or path of the sales data file
    */
   public SalesAnalysis(String inputFile)
   {
      this.inputFile = inputFile;
      weeklyTotal = new double[WEEKS_IN_MONTH];
   }

   /**
    * Opens the input file, reads it line by line, splits each line on
    * commas, and passes the resulting array to setArrayElement().
    *
    * @throws IOException if the input file cannot be found or opened
    */
   public void processFile() throws IOException
   {
      File file = new File(inputFile);
      Scanner inputFileScanner = new Scanner(file);

      while (inputFileScanner.hasNextLine())
      {
         String line = inputFileScanner.nextLine();
         String[] dailySales = line.split(",");
         setArrayElement(dailySales);
      }

      inputFileScanner.close();
   }

   /**
    * Converts each daily sales value in the array to a double, totals them,
    * and stores the total in weeklyTotal for the current week.
    *
    * @param inArray the seven daily sales values for one week, as Strings
    */
   private void setArrayElement(String[] inArray)
   {
      double total = 0.0;

      for (String value : inArray)
      {
         total += Double.parseDouble(value);
      }

      weeklyTotal[lineNumber] = total;
      lineNumber++;
   }

   /**
    * Prints each week's total and average daily sales, followed by the
    * total sales for all weeks, the average weekly sales, and the weeks
    * with the highest and lowest sales.
    */
   public void writeOutput()
   {
      double totalSales = 0.0;
      double dailySales;
      double max = weeklyTotal[0];
      double min = weeklyTotal[0];
      int maxWeek = 0;
      int minWeek = 0;

      for (int i = 0; i < weeklyTotal.length; i++)
      {
         totalSales += weeklyTotal[i];

         if (weeklyTotal[i] > max)
         {
            max = weeklyTotal[i];
            maxWeek = i;
         }
         if (weeklyTotal[i] < min)
         {
            min = weeklyTotal[i];
            minWeek = i;
         }

         dailySales = weeklyTotal[i] / 7;

         System.out.println();
         System.out.println("Week" + (i + 1) + " Info");
         System.out.printf("Total Sales: $%,.2f%n", weeklyTotal[i]);
         System.out.printf("Avg Daily Sales for Week: $%,.2f%n", dailySales);
      }

      System.out.println();
      System.out.printf("Total Sales for all Weeks: $%,.2f%n", totalSales);
      System.out.printf("Avg Weekly Sales: $%,.2f%n", totalSales / WEEKS_IN_MONTH);
      System.out.println("Week" + (maxWeek + 1) + " had the highest amount of sales");
      System.out.println("Week" + (minWeek + 1) + " had the lowest amount of sales");
   }
}