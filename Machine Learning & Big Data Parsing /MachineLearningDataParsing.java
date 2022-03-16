import java.util.*;
import java.io.*;

/**
 *
 * Code's Purpose: to practice machine learning techniques to parse
 * thousands of movie reviews from users on social media platforms
 * and create a weighted algorithm of machine learning techniques,
 * similarity, and pattern recognition.
 * @author Tim Chau;
 *
 */
public class MachineLearningDataParsing {

    /**
     * Will print the menu and prompt the user with.
     * 4 choices. Some choices correspond to a method.
     * @param delta
     * @param fileName
     * @param scnr
     * @throws IOException
     */
   public static void printMenu(double delta, String fileName, Scanner scnr)
      throws IOException {

      final String GETFILE = "enter input filename: ";
      final String GETWORD = "enter word to score --> ";
      final String GETREVIEW = "enter review line --> ";
      final String GETDELTA = "enter new delta [0,1] --> ";
      final String NOCOMPUTE = "review score can't be computed";
      final String DOWN = "thumbs down";
      final String UP = "thumbs up";
      final String BYE = "goodbye";
      final String INVALID = "invalid option, try again";
      final String GETCHOICE = "enter choice by number --> ";
      final String OPTION1 = "1. quit program";
      final String OPTION2 = "2. word scores";
      final String OPTION3 = "3. full review";
      final String OPTION4 = "4. cutoff delta";



      System.out.println("");
      System.out.println(OPTION1);
      System.out.println(OPTION2);
      System.out.println(OPTION3);
      System.out.println(OPTION4);
      System.out.print(GETCHOICE);



      int userChoice = scnr.nextInt();

      if (userChoice == 1) {
         System.out.println(BYE);
         System.exit(0);
      }
      else if (userChoice == 2) {
         System.out.print(GETWORD);
         scnr.nextLine(); // consumes any problematic spaces
         String userWord = scnr.nextLine();

         analyzeWord(userWord, fileName);

         printMenu(delta, fileName, scnr);
      }
      else if (userChoice == 3) {
         System.out.print(GETREVIEW);
         scnr.nextLine(); // consumes any problematic spaces
         String userReviewLine = scnr.nextLine();

         double fullReviewScore = getFullReviewScore(fileName, userReviewLine);
         System.out.println("full review score is " +
            String.format("%.5f", fullReviewScore));
         printReviewSentiment(fullReviewScore, delta);

         printMenu(delta, fileName, scnr);
      }
      else if (userChoice == 4) {
         System.out.print(GETDELTA);
         double userDelta = scnr.nextDouble();

         if ((userDelta > 1) || (userDelta < 0)) {
            System.out.println("You entered a delta that was " +
               "not between 0 and 1");
            System.out.println("Delta will not be changed. " +
               "Delta remains: " + delta);
            userDelta = delta;
         }
         else {
            delta = userDelta;
         }
         printMenu(delta, fileName, scnr);
      }
      else {
         System.out.println(INVALID);
         System.out.println("");
         printMenu(delta, fileName, scnr);
      }

   }


    /**
     * Will get the word from the user and calculate.
     * it's score, and print how many reviews it is
     * in and return it's score.
     * @param userWord is self explanatory
     * @param fileName
     * @throws IOException
     * @return avgScoreForThoseReviews
     */
   public static double analyzeWord(String userWord, String fileName)
      throws IOException {


      int numReviewsWordAppears = 0; // initialized
      numReviewsWordAppears = getNumberOfAppearances(fileName, userWord);
      System.out.println(userWord.toLowerCase() + " appears in " +
         numReviewsWordAppears + " reviews");

      double avgScoreForThoseReviews = 4.0; // initialized
      avgScoreForThoseReviews = getAvgScoreForThoseReviews(fileName,
         userWord, numReviewsWordAppears);
      if (numReviewsWordAppears > 0) {
         System.out.println("average score for those reviews is "
            + String.format("%.5f", avgScoreForThoseReviews));
      }

      writeTheFile(fileName, userWord);

      return avgScoreForThoseReviews;
   }



        /**
         * Will return numberOfAppearances.
         * @param fileName
         * @param userWord
         * @throws IOException
         * @return numberOfAppearances
         */
   public static int getNumberOfAppearances(String fileName, String userWord)
      throws IOException {

      int numberOfAppearances = 0;
      FileInputStream fileInStream = new FileInputStream(fileName);

      Scanner inFS = new Scanner(fileInStream);

      while (inFS.hasNextLine()) {
         if (inFS.nextLine().toLowerCase().contains(userWord.toLowerCase())) {
            numberOfAppearances = numberOfAppearances + 1;
         }
      }

      fileInStream.close();

      return numberOfAppearances;

   }



        /**
         * Will return avgScore.
         * @param fileName
         * @param userWord
         * @param numberOfAppearances
         * @throws IOException
         * @return avgScore
         */
   public static double getAvgScoreForThoseReviews(String fileName,
      String userWord, int numberOfAppearances) throws IOException {

      double avgScore = -1.0;
      double totalScore = 0;

      FileInputStream fileInStream = new FileInputStream(fileName);
      Scanner inFS = new Scanner(fileInStream);

      while (inFS.hasNext()) {
         String next = inFS.next();

         if (inFS.nextLine().toLowerCase().contains(userWord.toLowerCase())) {

            int currentLineScore = Integer.parseInt(next);
            totalScore = totalScore + currentLineScore;

         }
      }

      fileInStream.close();

      avgScore = totalScore / numberOfAppearances;
      return avgScore;
   }



        /**
         * Will write the file.txt.
         * @param fileName
         * @param userWord
         * @throws IOException
         */
   public static void writeTheFile(String fileName, String userWord)
      throws IOException {

      FileInputStream fileInStream = new FileInputStream(fileName);
      Scanner inFS = new Scanner(fileInStream);

      FileOutputStream fileOutStream =
         new FileOutputStream(userWord.toLowerCase() + ".txt");
      PrintWriter aboveFOS = new PrintWriter(fileOutStream);

            // HOURS_SPENT_ON_THIS: 5:45
      while (inFS.hasNextLine()) {
         String previousLineHolder = inFS.nextLine();
         String lowercaseLine = previousLineHolder.toLowerCase();
         String userWordLowerCase = userWord.toLowerCase();

         if (lowercaseLine.contains(userWordLowerCase)) {
            aboveFOS.println(previousLineHolder);
         }
      }

      aboveFOS.flush();
      fileOutStream.close();
      fileInStream.close();

   }



    /**
     * Will get the review line, calculate and return the.
     * full review score
     * @param fileName
     * @param userReviewLine
     * @throws IOException
     * @return fullReviewScore
     */
   public static double getFullReviewScore(String fileName,
      String userReviewLine) throws IOException {

      double fullReviewScore = 4.1;


      String lineString = userReviewLine;
      Scanner linescan = new Scanner(lineString);
      String userWord = "";

      double totalScore = 0;
      int i = 0;

      while (linescan.hasNext()) {
         userWord = linescan.next();

         double addedScore = wordScore2(fileName, userWord);
         totalScore = totalScore + addedScore;

         if (addedScore == 0) {
            i = i + 0;
         }
         else {
            i++; // this increments i only if the word shows up in the line
         }
      }

      fullReviewScore = totalScore / (i);

      return fullReviewScore;
   }



        /**
         * Will get the avg Score for that individual word.
         * (which was parsed from the user's line)
         * @param fileName
         * @param userWord
         * @throws IOException
         * @return avgScore
         */
   public static double wordScore2(String fileName,
      String userWord) throws IOException {

      double avgScore = -1.0;
      double totalScore = 0;

      FileInputStream fileInStream = new FileInputStream(fileName);
      Scanner inFS = new Scanner(fileInStream);

      int i = 0;
      while (inFS.hasNext()) {
         String next = inFS.next();

         if (inFS.nextLine().toLowerCase().contains(userWord.toLowerCase())) {

            int currentLineScore = Integer.parseInt(next);
            totalScore = totalScore + currentLineScore;
            i++;
         }
      }

      fileInStream.close();

      if (i == 0) { //make sure i isn't zero before division to avoid NaN error
         avgScore = 0; //The neutral value
      }
      else {
         avgScore = totalScore / (i);
      }

      return avgScore;
   }



    /**
     * Will print the Review Sentiment.
     * thumbs up or thumbs down or nothing (if neutral)
     * or a nocompute message.
     * @param fullReviewScore
     * @param delta
     */
   public static void printReviewSentiment(double fullReviewScore,
      double delta) {

      final String NOCOMPUTE = "review score can't be computed";
      final String DOWN = "thumbs down";
      final String UP = "thumbs up";

      String reviewSentiment = "ERROR";
      if ((fullReviewScore > 2 + delta) && (fullReviewScore <= 4)) {
         reviewSentiment = "Positive";
         if ("Positive".equals(reviewSentiment)) {
            System.out.println(UP);
         }
      }
      else if ((fullReviewScore >= (2 - delta)) &&
         (fullReviewScore <= (2 + delta))) {

         reviewSentiment = "Neutral";
         if ("Neutral".equals(reviewSentiment)) {
            System.out.println(""); //Do nothing
         }
      }
      else if ((fullReviewScore < 2 - delta) && (fullReviewScore >= 0)) {
         reviewSentiment = "Negative";
         if ("Negative".equals(reviewSentiment)) {
            System.out.println(DOWN);
         }
      }
      else {
         System.out.println(NOCOMPUTE);
      }


   }



    /**
     * Will get user input for fileName.
     * @param scnr
     * @return fileName
     */
   public static String setFileName(Scanner scnr) {
      final String GETFILE = "enter input filename: ";
      System.out.print(GETFILE);

      String fileName = null;

      fileName = scnr.nextLine();

      return fileName;

   }



    /**
     * Main method where execution begins.
     * @param args not used
     * @throws IOException
     */
   public static void main(String[] args) throws IOException {

      Scanner scnr = new Scanner(System.in);


      String fileName = null;
      double delta = 0;

      fileName = setFileName(scnr);

      printMenu(delta, fileName, scnr);


      scnr.close();

   }
}
