import java.awt.*;
import java.util.*;

/**
 * @author Tim Chau
 */
public class AnimatedClock {

   public static void main(String[] args) {

      String userInputStartTimeString;

      int startTimeHour = -1;
      int startTimeMinute = -1;
      int startTimeTotal = -1;

      int elapsedMinutesInt = -1;

      int endingTimeTotalInMinutes = -1;
      int endingTimeHour = -1;
      int endingTimeMinute = -1;
      String endingTimeString = "-1";

    //Get User Input for startTime

      System.out.print("Enter starting time in 24-hour format,");
      System.out.print(" e.g. 0:45 or 9:58 or 23:15: ");
      Scanner scanner1 = new Scanner(System.in);
      userInputStartTimeString = scanner1.nextLine();
      String[] stringArrayStartTime = userInputStartTimeString.split(":");
      for (int i = 0; i < stringArrayStartTime.length; i++) {

         startTimeHour = Integer.parseInt(stringArrayStartTime[0]);
         startTimeMinute = Integer.parseInt(stringArrayStartTime[1]);
         startTimeTotal = startTimeHour * 60 + startTimeMinute;
      }

    // Get elapsedMinutesInt from user
      System.out.print("Enter elapsed minutes as an integer, e.g. 15 or 149: ");

      elapsedMinutesInt = scanner1.nextInt();

    // Calculates end Time
      endingTimeTotalInMinutes = startTimeTotal + elapsedMinutesInt;
      endingTimeHour = endingTimeTotalInMinutes / 60;
      endingTimeMinute = endingTimeTotalInMinutes % 60;
      endingTimeString = endingTimeHour + ":" + endingTimeMinute;

      System.out.println("\nStart time: " + userInputStartTimeString);
      System.out.println("Elapsed minutes = " + elapsedMinutesInt);
      System.out.println("End time: " + endingTimeString);

    //Calculations for Clock Hands
    //First Get Angle of Hour Hand
      double angleHourHand = ((endingTimeHour * 360.0 / 12.0));
      angleHourHand = angleHourHand + (endingTimeMinute * .5);

      while (angleHourHand > 360) {
         angleHourHand = angleHourHand % 360;
      }

   //Then Get Angle of Minute Hand
      double angleMinuteHand = ((endingTimeMinute * 360.0 / 60.0));

   //  Find X and Y coordinates of Minute Hand.
      double minuteHandXCoord = -1;
      double minuteHandYCoord = -1;

      minuteHandXCoord = (225 * (Math.cos(Math.PI / 2.0 -
         angleMinuteHand * Math.PI / 180.0))) + 0;
      minuteHandYCoord = (225 * (Math.sin(Math.PI / 2.0 -
         angleMinuteHand * Math.PI / 180.0))) + 0;

   //  Find X and Y coordinates of Hour Hand vertex 1, 2 and 3
      double hourVert1X = -1;
      double hourVert1Y = -1;
      double hourVert2X = -1;
      double hourVert2Y = -1;
      double hourVert3X = -1;
      double hourVert3Y = -1;

      hourVert1X = (105.0 * (Math.cos(Math.PI / 2.0 -
         angleHourHand * Math.PI / 180.0))) + 0;
      hourVert1Y = (105.0 * (Math.sin(Math.PI / 2.0 -
         angleHourHand * Math.PI / 180.0))) + 0;
      hourVert2X = (-10.0 * Math.sin(Math.PI / 2.0 +
         angleHourHand * Math.PI / 180.0));
      hourVert2Y = (-10.0 * Math.cos(Math.PI / 2.0 +
         angleHourHand * Math.PI / 180.0));
      hourVert3X = (10.0 * Math.sin(Math.PI / 2.0 +
         angleHourHand * Math.PI / 180.0));
      hourVert3Y = (10.0 * Math.cos(Math.PI / 2.0 +
         angleHourHand * Math.PI / 180.0));

   //  Find X and Y coordinates of Minute Hand vertex 1, 2 and 3.
      double minVert1X = -1;
      double minVert1Y = -1;
      double minVert2X = -1;
      double minVert2Y = -1;
      double minVert3X = -1;
      double minVert3Y = -1;

      minVert1X = (175.0 * (Math.cos(Math.PI / 2.0 -
         angleMinuteHand * Math.PI / 180.0))) + 0;
      minVert1Y = (175.0 * (Math.sin(Math.PI / 2.0 -
         angleMinuteHand * Math.PI / 180.0))) + 0;
      minVert2X = (-10.0 * Math.sin(Math.PI / 2.0 +
         angleMinuteHand * Math.PI / 180.0));
      minVert2Y = (-10.0 * Math.cos(Math.PI / 2.0 +
         angleMinuteHand * Math.PI / 180.0));
      minVert3X = (10.0 * Math.sin(Math.PI / 2.0 +
         angleMinuteHand * Math.PI / 180.0));
      minVert3Y = (10.0 * Math.cos(Math.PI / 2.0 +
         angleMinuteHand * Math.PI / 180.0));

    //Drawing the Clock Circle, setting color, numbers.
      double radius = 225;
      double xcoord = 0.0;
      double ycoord = 0.0;

   // StdDraw.setCanvasSize(512, 512);
      StdDraw.setScale(-256, 256);
      StdDraw.clear(StdDraw.PRINCETON_ORANGE);
      StdDraw.circle(xcoord, ycoord, radius);
      Font font = new Font("Arial", Font.BOLD, 32);
      StdDraw.setFont(font);
      StdDraw.text(xcoord, 200, "12");
      StdDraw.text(xcoord, -200, "6");
      StdDraw.text(-200, ycoord, "9");
      StdDraw.text(200, ycoord, "3");

    //Drawing the Hands of the Clock.
      StdDraw.setPenColor(StdDraw.GREEN);
      StdDraw.filledTriangle(minVert1X, minVert1Y, minVert2X,
         minVert2Y, minVert3X, minVert3Y);
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.triangle(minVert1X, minVert1Y, minVert2X,
         minVert2Y, minVert3X, minVert3Y);
      StdDraw.setPenColor(StdDraw.MAGENTA);
      StdDraw.filledTriangle(hourVert1X, hourVert1Y, hourVert2X,
         hourVert2Y, hourVert3X, hourVert3Y);

      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.triangle(hourVert1X, hourVert1Y, hourVert2X,
         hourVert2Y, hourVert3X, hourVert3Y);
    // Center Dot on Clock. :(
      StdDraw.filledCircle(xcoord, ycoord, 12.0);


   }

}
