import java.util.*;

/**
 * Will Analyze sample statistical data and utilize statistical analysis to
 * predict/best guess outcomes for subsequent data points. Established user
 * defined inputs, plotted data scores corresponding to traits and values
 * attributed to objects. Will apply predictive “nearest neighbor” algorithm to
 * data across traits such as radius corresponding with mileage, brand
 * corresponding with color or year or price.
 * Used weighted similarity formula to take all traits
 * into account to determine the most statistically similar sample
 * value to a custom user defined input dataset.
 *
 * @author Tim Chau;
 */

public class PredictionModelViaStatisticalDataset {

   static Random gen = new Random(12); //seed val, change if u want

   /**
    * Generate an integer in a specified range, with all values being. equally
    * likely.
    *
    * @param low
    * @param high
    * @return the value generated
    */
   public static int generateInt(int low, int high) {
      int randomIntFromLowToHigh = gen.nextInt(high + 1 - low) + low;
      return randomIntFromLowToHigh;
   }

   /**
    * generates a random year between 1972 and 2008.
    *
    * @return the generated number
    */
   public static int generateYear() {
      int randomYear = generateInt(1972, 2020);
      return randomYear;
   }

   /**
    * Generate a random mileage: split the possibilities into 5
    * ranges: [0, 17000),
    * [17000, 30000), [30000, 40000), [40000, 70000), [70000, 999999],
    * with each range being equally likely. Each mileage within a range is equally
    * likely.
    * @return the generated number
    */
   public static int generateMileage() {

      int oneOfFiveRanges = -1;
      oneOfFiveRanges = generateInt(1, 5);

      int randomMilage = -1;
      if (oneOfFiveRanges == 1) {
         randomMilage = generateInt(0, 17000);
      } else if (oneOfFiveRanges == 2) {
         randomMilage = generateInt(17000, 30000);
      } else if (oneOfFiveRanges == 3) {
         randomMilage = generateInt(30000, 40000);
      } else if (oneOfFiveRanges == 4) {
         randomMilage = generateInt(40000, 70000);
      } else if (oneOfFiveRanges == 5) {
         randomMilage = generateInt(70000, 999999);
      }
      return randomMilage;
   }

   /**
    * Generate the brand of a car based on given brand probabilities.
    *
    * @return the generated brand as a string
    */
   public static String generateBrand() {
       // now generate a random double, from 0.0 to 100.0, aka a percentage
      double start = 0.0;
      double end = 100.0;
      double randomDbl = gen.nextDouble();
      double randomPercent = start + (randomDbl * (end - start));

      double percent1Chevrolet = 13.0;
      double percent2Dodge = 19.0;
      double percent3Ford = 54.3;
      double percent4Nissan = 13.7;

      String brand = "-1";
      if (randomPercent < percent1Chevrolet) {
         brand = "Chevrolet";
      } else if (randomPercent < (percent2Dodge + percent1Chevrolet)) {
         brand = "Dodge";
      } else if (randomPercent < (percent3Ford + percent2Dodge +
              percent1Chevrolet)) {
         brand = "Ford";
      } else if (randomPercent < (percent4Nissan + percent3Ford +
              percent2Dodge + percent1Chevrolet)) {
         brand = "Nissan";
      }

      return brand;
   }

   /**
    * Generate a random car price based on the mileage and.
    * project-provided statistics.
    *
    * @param mileage the car's mileage in the range [0, 999999]
    * @return a price value in the range [1, 74000]
    */
   public static int generatePrice(int mileage) {

      int price = -1;
      if (mileage < 17000) {
         price = generateInt(1, 74000);
      } else if (mileage < 30000) {
         price = generateInt(1, 65000);
      } else if (mileage < 40000) {
         price = generateInt(1, 60000);
      } else if (mileage < 70000) {
         price = generateInt(1, 55000);
      } else if (mileage < 999999) {
         price = generateInt(1, 50000);
      }
      return price;
   }

   /**
    * This method returns dSquared.
    * @param yearA    userInput of the car's year
    * @param mileageA userInput of the car's mileage
    * @param brandA   userInput of the car's brand
    * @param yearB    the car's model year
    * @param mileageB the car's mileage
    * @param brandB   the car's brand
    * @return dSquared
    */
   public static double squaredDistance(int yearA, int mileageA,
           String brandA, int yearB, int mileageB, String brandB) {

      String inputBrand = brandA;
      double dSquared = -1;

      int brandDiff;
      if (inputBrand.equals(brandB)) {
         brandDiff = 0;
      } else {
         brandDiff = 1;
      }

      dSquared = Math.pow(((yearA - yearB) / 3.3), 2)
              + Math.pow(((mileageA - mileageB) / 50561), 2) + brandDiff;

      return dSquared;
   }

   /**
    * format: [xxxx]
    * year, mileage, brand, price.
    * @param number  the sample number
    * @param year    the car's model year
    * @param mileage the car's mileage
    * @param brand   the car's brand
    * @param price   the car's selling price
    * @return the composed string
    */
   public static String sampleText(int number, int year, int mileage,
           String brand, int price) {

      String sampleDataOutput = "[" + String.format("%04d", number) + "] "
         + year + ", " + mileage + ", " + brand + ", $" + price;
      return sampleDataOutput;
   }


   /**
    * This method sets pen color.
    *
    * @param brandParam the car's brand
    */
   public static void setColor(String brandParam) {
      if ("Ford".equals(brandParam)) {
         StdDraw.setPenColor(StdDraw.YELLOW);
      }
      if ("Chevrolet".equals(brandParam)) {
         StdDraw.setPenColor(StdDraw.RED);
      }
      if ("Nissan".equals(brandParam)) {
         StdDraw.setPenColor(StdDraw.BLUE);
      }
      if ("Dodge".equals(brandParam)) {
         StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
      }
   }

   /**
    * This method sets pen radius.
    *
    * @param mileageParam the car's mileage
    */
   public static void setPenRadius(int mileageParam) {
       // (Pen Radius represents mileage. Small mileage = small radius.
       // large radius )
      double penRadius = 0;
      if (mileageParam <= 17000) {
         penRadius = 0.015;
      } else if (mileageParam <= 30000) {
         penRadius = 0.02;
      } else if (mileageParam <= 40000) {
         penRadius = 0.03;
      } else if (mileageParam <= 70000) {
         penRadius = 0.04;
      } else if (mileageParam <= 999999) {
         penRadius = 0.06;
      }
      StdDraw.setPenRadius(penRadius);
   }

   /**
    * This method returns yearXCoordinate.
    *
    * @param yearParam the car's year
    * @return yearXCoordinate
    */
   public static double returnYearXCoordinate(int yearParam) {

      double yearXCoordinate = (yearParam - 1996) * (100.0 / 24.0);
      return yearXCoordinate;
   }

   /**
    * This method returns priceYCoordinate.
    *
    * @param priceParam the car's price
    * @return priceYCoordinate
    */
   public static double returnPriceYCoordinate(int priceParam) {
      double priceYCoordinate = (priceParam - 37000) * (100.0 / 37000.0);
      return priceYCoordinate;
   }

   /**
    * Used to plot and visualize the sets of sample data.
    *
    * @param number       the number of iterations of howMany
    * @param yearParam    the car's model year
    * @param mileageParam the car's mileage
    * @param brandParam   the car's brand
    * @param priceParam   the car's selling price
    * @return negative 1, plotSample will let us know if an error occurs
    */
   public static double plotSample(int number, int yearParam,
           int mileageParam, String brandParam, int priceParam) {

      setColor(brandParam);
      setPenRadius(mileageParam);

      // year ranges 1972 - 2020; Halfway is 1996, price ranges 1$ - $74,000
      double yearXCoordinate = returnYearXCoordinate(yearParam);
      double priceYCoordinate = returnPriceYCoordinate(priceParam);

      StdDraw.point(yearXCoordinate, priceYCoordinate);

      return -1;
   }

   /**
    * Plots a small red square around the user input points.
    *
    * @param yearParam  the car's model year
    * @param priceParam the car's selling price
    */
   public static void plotSquare(int yearParam, int priceParam) {

      double yearXCoordinate = returnYearXCoordinate(yearParam);
      double priceYCoordinate = returnPriceYCoordinate(priceParam);

      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.setPenRadius(.004);
      StdDraw.square(yearXCoordinate, priceYCoordinate, 11.1);
   }

   /**
    * Main method. BOOM.
    * @param args not used here
    *
    */
   public static void main(String[] args) {

       // create new scanner for console input
      Scanner scnr = new Scanner(System.in);

      // prompt for and store the data for the input car

      // input car year
      System.out.print("Enter query car's model year: ");
      int inputYear = scnr.nextInt();

      // input mileage
      System.out.print("Enter query car's mileage [1, 999999]: ");
      int inputMileage = scnr.nextInt();

      // input brand
      System.out.print("Enter query car's brand [Chevrolet, Dodge, Ford, "
              + "Nissan]: ");
      String inputBrand = scnr.next();

      // input's associated price (not collected as input; to be predicted)
      int closestMatchPrice = 74000;

      // get input for the number of samples to generate
      System.out.print("Enter number of samples to generate: ");
      int howMany = scnr.nextInt();



      // Setup Canvas
      StdDraw.setScale(-150, 150);
      StdDraw.clear(StdDraw.CYAN);

      // Variables initialized in "main" will be accessible throughout

      double dSquaredSmallest = 1000;
      closestMatchPrice = 74000;
      int closestMatchYear = -1;
      int closestMatchMileage = -1;
      String closestMatchBrand = "ERROR";
      int closestMatchIteration = -1;



      // this for loop will iterate the Text and Plot "howMany" times
      for (int i = 0001; i <= howMany; i++) {

         int year = generateYear();
         int mileage = generateMileage();
         String brand = generateBrand();
         int price = generatePrice(mileage);

         String iteratedText = sampleText(i, year, mileage, brand, price);
         System.out.println(iteratedText);
         plotSample(howMany, year, mileage, brand, price);

         double dSquared = squaredDistance(inputYear, inputMileage,
                 inputBrand, year, mileage, brand);

         if (dSquared < dSquaredSmallest) {
            dSquaredSmallest = dSquared;

            closestMatchPrice = price;

            closestMatchIteration = i;
            closestMatchMileage = mileage;
            closestMatchBrand = brand;
            closestMatchYear = year;

         }
      }
      // plots a single user input point, plotSquare plots square around it
      plotSample(howMany, inputYear, inputMileage, inputBrand,
              closestMatchPrice);
      plotSquare(inputYear, closestMatchPrice);

      System.out.println("\nInput car: [0000] " + inputYear + ", "
              + inputMileage + ", " + inputBrand + ", $"
              + closestMatchPrice);

      System.out.println("Closest match: " + "["
              + String.format("%04d", closestMatchIteration) + "] "
              + closestMatchYear + ", " + closestMatchMileage + ", "
              + closestMatchBrand + ", $" + closestMatchPrice);

   }

}
