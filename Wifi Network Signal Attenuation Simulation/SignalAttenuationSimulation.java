import java.util.*;
import java.io.*;

/**
 * Modeled attenuating signal strength of wireless routers through various walls, materials and distances
 * And visualized data graphically using dimensional array object logging distance,
 * by attenuation rates and the Free Space Path Loss formula
 * @author Tim Chau;
 */
public class SignalAttenuationSimulation {

   public static void main(String[] args) throws IOException {

      final double EPS = .0001;
      final String PROMPT1 = "Enter name of grid data file: ";
      final String ERROR = "ERROR: invalid input file";

      Scanner kb = new Scanner(System.in);
      System.out.print(PROMPT1);
      String name = kb.nextLine();

      FileInputStream infile = new FileInputStream(name);
      Scanner scanFile = new Scanner(infile);
      double size = scanFile.nextDouble();
      int rows = scanFile.nextInt();
      int cols = scanFile.nextInt();
      scanFile.nextLine(); // skip the character by the end of the line

      FileOutputStream outstream = new FileOutputStream("signals.txt");
      PrintWriter outfile = new PrintWriter(outstream);

      Cell[][] grid = new Cell[rows][cols];
      Cell[][] old = new Cell[rows][cols];
      initialize(grid);
      initialize(old);

      int routerRow;
      int routerCol;
      final double ROUTER = 23;


      read(grid, scanFile);
      if (! isValid(grid)) {
         System.out.println(ERROR);
      } else {
         // continue
         System.out.print("Enter router row and column: ");
         routerRow = kb.nextInt();
         routerCol = kb.nextInt();
         grid[routerRow][routerCol].setSignal(ROUTER);

         setAllDirections(grid, routerRow, routerCol);
         setAllDistances(grid, routerRow, routerCol, size);

         while (! equivalent(grid, old, EPS)) {
            copy(grid, old);
            iterate(grid, old, routerRow, routerCol);
            printAll(grid, outfile);
            outfile.println();
         }
      }

      double minSignal = findMinSignal(grid);
      System.out.println("minimum signal strength: " + minSignal +
                     " occurs in these cells: ");
      printMinCellCoordinates(grid, minSignal);

      outstream.flush();
      outfile.close();
   }



   /** Set the direction from the router position to every other cell
    *  in the grid. (Do not change the direction of the router cell.)
    *  @param grid the grid of cells to manage
    *  @param routerRow the row position of the router cell in the grid
    *  @param routerCol the column position of the router cell in the grid
    */
   public static void setAllDirections(Cell[][] grid, int routerRow,
                                       int routerCol) {

      String direction;
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            direction = direction(routerRow, routerCol, i, j);
            grid[i][j].setDirection(direction);
         }
         //System.out.println(""); //For Testing
      }

   }


   /** Set the distance from the router position to every other cell
    *  in the grid. (Do not change the distance of the router cell.)
    *  @param grid the grid of cells to manage
    *  @param routerRow the row position of the router cell in the grid
    *  @param routerCol the column position of the router cell in the grid
    *  @param size the size of each cell
    */
   public static void setAllDistances(Cell[][] grid, int routerRow,
                                       int routerCol, double size) {
      double distance = 97477237.0;
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            distance = size * Math.sqrt(((routerRow - i)
               * (routerRow - i)) + ((routerCol - j) * (routerCol - j)));
            grid[i][j].setDistance(distance);
            //System.out.print(grid[i][j].getDistance() + "   ");  //For Testing
         }
         //System.out.println(""); //For Testing
      }
   }


   /** Iterate over the grid, updating the signal strength and
    *  attenuation rate of each cell based on the old values of
    *  the relevant neighbor cells.
    *  @param current the updated values of each cell
    *  @param previous the old values of each cell
    *  @param routerRow the row position of the router's cell
    *  @param routerCol the column position of the router's cell
    */
   public static void iterate(Cell[][] current, Cell[][] previous,
                              int routerRow, int routerCol) {

      double signalStrength = 0;
      double distance = 0;
      int newAttenuation = 0;

      for (int row = 0; row < current.length; row++) {
         for (int column = 0; column < current[row].length; column++) {
            distance = previous[row][column].getDistance();
            newAttenuation = Math.abs(attenRate(previous, row, column));

            // signalStrength = signalStrengthatRouter - (fspl + newAttenRate)
            signalStrength = 23 - (Math.abs(fspl(distance, 5))
               + Math.abs(newAttenuation));

            current[row][column].setRate(newAttenuation);
            current[row][column].setSignal(signalStrength);

            current[routerRow][routerCol].setSignal(23); //redundancy failsafe
         }
      }
      //current[routerRow][routerCol].setSignal(23); //redudancy failsafe
   }


   /** Calculate the signal transmission free space path loss (FSPL).
    *  @param distance the distance from the source to the receiver
    *  @param frequency the frequency of the transmission
    *  @return the fspl ratio
    */
   public static double fspl(double distance, double frequency) {
   //From Prompt: FSPL(dB) = 20log10(d) + 20log10(f) + 92.45
      double myFSPL = -1000;
      myFSPL = 20 * Math.log10(distance) + 20 * Math.log10(frequency) + 92.45;
      return myFSPL;
   }


   /** Calculate the attenuation rate of a cell based on the
    *  attenuation of its relevant neighbor(s).
    *  @param prev the grid of cells from prior iteration
    *  @param row the row of the current cell
    *  @param col the column of the current cell
    *  @return the new attenuation rate of that cell
    */
   public static int attenRate(Cell[][] prev, int row, int col) {

      int attenRate = 0;
      int attenRelevantNeighbor = 0;
      int attenOfRelvantWallMaterial = 0;
      String direction = prev[row][col].getDirection();
      //Direction of Current Cell Relative to router.

      if ("N".equals(direction)) {
         attenRelevantNeighbor = prev[row + 1][col].getRate();
         attenOfRelvantWallMaterial = attenuation(prev[row][col].getSouth());
      }
      if ("E".equals(direction)) {
         attenRelevantNeighbor = prev[row][col - 1].getRate();
         attenOfRelvantWallMaterial = attenuation(prev[row][col].getWest());
      }
      if ("S".equals(direction)) {
         attenRelevantNeighbor = prev[row - 1][col].getRate();
         attenOfRelvantWallMaterial = attenuation(prev[row][col].getNorth());
      }
      if ("W".equals(direction)) {
         attenRelevantNeighbor = prev[row][col + 1].getRate();
         attenOfRelvantWallMaterial = attenuation(prev[row][col].getEast());
      }

      if ("--".equals(direction)) { //for case of router cell
         attenRate = 0;
         return attenRate;
      }

      attenRate = attenRelevantNeighbor + attenOfRelvantWallMaterial;


      //Diagonals Case
      if (("NE".equals(direction)) || ("SE".equals(direction))) {
         attenRate = attenRateHelperNESE(prev, row, col,
            attenRate, attenRelevantNeighbor,
            attenOfRelvantWallMaterial, direction);
      }
      else if (("NW".equals(direction)) || ("SW".equals(direction))) {

         attenRate = attenRateHelperNWSW(prev, row, col,
            attenRate, attenRelevantNeighbor,
            attenOfRelvantWallMaterial, direction);
      }



      return attenRate;
   }


   /** Helper Method that deals NE/SE diagonal attenuation.
    *  @param prev the grid of cells from prior iteration
    *  @param row the row of the current cell
    *  @param col the column of the current cell
    *  @param attenRate value passed from Previous param
    *  @param attenRelevantNeighbor self explainatory
    *  @param attenOfRelvantWallMaterial self explainatory
    *  @param direction direction cell relative to router
    *  @return the new attenuation rate of that cell
    */
   public static int attenRateHelperNESE(Cell[][] prev, int row, int col,
      int attenRate, int attenRelevantNeighbor, int attenOfRelvantWallMaterial,
         String direction) {

      if ("NE".equals(direction)) {

         int diagonalNeighborAtten1 = prev[row + 1][col].getRate();
         //South neighbor cell Attenuation
         int neighborWall1 = attenuation(prev[row][col].getSouth());
         //South wall Attenrate

         int diagonalNeighborAtten2 = prev[row][col - 1].getRate();
         //West neighbor cell Attenuation
         int neighborWall2 = attenuation(prev[row][col].getWest());
         //West wall Attenrate

         if ((diagonalNeighborAtten1 + neighborWall1
            >= diagonalNeighborAtten2 + neighborWall2)) {
            attenOfRelvantWallMaterial = neighborWall1;
            attenRelevantNeighbor = diagonalNeighborAtten1;
         }
         else if ((diagonalNeighborAtten1 + neighborWall1
            <= diagonalNeighborAtten2 + neighborWall2)) {
         //<= catches case where diagonal neighbor cells have equal attenuation
            attenOfRelvantWallMaterial = neighborWall2;
            attenRelevantNeighbor = diagonalNeighborAtten2;
         }
      }
      if ("SE".equals(direction)) {

         int diagonalNeighborAtten1 = prev[row - 1][col].getRate();
         //North neighbor Cell Atten
         int neighborWall1 = attenuation(prev[row][col].getNorth());
         //North wall Attenrate

         int diagonalNeighborAtten2 = prev[row][col - 1].getRate();
         //West neighbor cell Attenuation
         int neighborWall2 = attenuation(prev[row][col].getWest());
         //West wall Attenrate

         if ((diagonalNeighborAtten1 + neighborWall1)
            >= (diagonalNeighborAtten2 + neighborWall2)) {
            attenOfRelvantWallMaterial = neighborWall1;
            attenRelevantNeighbor = diagonalNeighborAtten1;
         }
         else if ((diagonalNeighborAtten1 + neighborWall1)
            <= (diagonalNeighborAtten2 + neighborWall2)) {
            attenOfRelvantWallMaterial = neighborWall2;
            attenRelevantNeighbor = diagonalNeighborAtten2;
         }

      }

      attenRate = attenRelevantNeighbor + attenOfRelvantWallMaterial;
      return attenRate;
   }



   /** Helper Method that deals NW/SW diagonal attenuation.
    *  @param prev the grid of cells from prior iteration
    *  @param row the row of the current cell
    *  @param col the column of the current cell
    *  @param attenRate value passed from Previous param
    *  @param attenRelevantNeighbor self explainatory
    *  @param attenOfRelvantWallMaterial self explainatory
    *  @param direction direction cell relative to router
    *  @return the new attenuation rate of that cell
    */
   public static int attenRateHelperNWSW(Cell[][] prev, int row, int col,
      int attenRate, int attenRelevantNeighbor, int attenOfRelvantWallMaterial,
       String direction) {


      if ("NW".equals(direction)) {
         int diagonalNeighborAtten1 = prev[row + 1][col].getRate();
       //South neighbor cell Attenuation
         int neighborWall1 = attenuation(prev[row][col].getSouth());
       //South wall Attenrate

         int diagonalNeighborAtten2 = prev[row][col + 1].getRate();
       //East Neighbor Cell Atten
         int neighborWall2 = attenuation(prev[row][col].getEast());
       //East wall Attenrate

         if ((diagonalNeighborAtten1 + neighborWall1)
            >= (diagonalNeighborAtten2 + neighborWall2)) {

            attenOfRelvantWallMaterial = neighborWall1;
            attenRelevantNeighbor = diagonalNeighborAtten1;
         }
         else if ((diagonalNeighborAtten1 + neighborWall1)
            <= (diagonalNeighborAtten2 + neighborWall2)) {

            attenOfRelvantWallMaterial = neighborWall2;
            attenRelevantNeighbor = diagonalNeighborAtten2;

         }

      }
      if ("SW".equals(direction)) {
         int diagonalNeighborAtten1 = prev[row - 1][col].getRate();
       //North neighbor Cell Atten
         int neighborWall1 = attenuation(prev[row][col].getNorth());
       //North wall Attenrate

         int diagonalNeighborAtten2 = prev[row][col + 1].getRate();
       //East Neighbor Cell Atten
         int neighborWall2 = attenuation(prev[row][col].getEast());
       //East Wall Attenrate

         if ((diagonalNeighborAtten1 + neighborWall1)
            >= (diagonalNeighborAtten2 + neighborWall2)) {
            attenOfRelvantWallMaterial = neighborWall1;
            attenRelevantNeighbor = diagonalNeighborAtten1;
         }
         else if ((diagonalNeighborAtten1 + neighborWall1)
            <= (diagonalNeighborAtten2 + neighborWall2)) {
            attenOfRelvantWallMaterial = neighborWall2;
            attenRelevantNeighbor = diagonalNeighborAtten2;

         }

      }

      attenRate = attenRelevantNeighbor + attenOfRelvantWallMaterial;
      return attenRate;
   }


   /**
    * Will return the Larger Int.
    * @param x self explainatory.
    * @param y self explainatory.
    * @return largerInt the larger of two.
    */
   public static int returnLargerInt(int x, int y) {
      int largerInt = -1;

      if (x > y) {
         largerInt = x;
      }
      else if (y > x) {
         largerInt = y;
      }
      else if (x == y) {
         largerInt = x;
      }
      return largerInt;
   }




   /** Find the direction between the router cell and the current cell.
    *  For example, if (r0,c0) is (2,3) and (r1,c1) is (0,3), this
    *  method returns the string "N" to denote that the current cell
    *  is north of the router cell.
    *  @param r0 the router row
    *  @param c0 the router column
    *  @param r1 the current cell row
    *  @param c1 the current cell column
    *  @return a string direction heading (N, E, S, W, NE, SE, SW, NW)
    */
   public static String direction(int r0, int c0, int r1, int c1) {
      String direction = "--";

      int deltaRows = r1 - r0;
      int deltaColumns = c1 - c0;

      if (!(Math.abs(deltaRows) == Math.abs(deltaColumns))) {
      // NOT ON THE DIAGONAL
         if ((r1 < r0) && (Math.abs(deltaRows) > Math.abs(deltaColumns))) {
            direction = "N";
         }
         if ((r1 > r0) && (Math.abs(deltaRows) > Math.abs(deltaColumns))) {
            direction = "S";
         }
         if ((c1 > c0) && (Math.abs(deltaRows) < Math.abs(deltaColumns))) {
            direction = "E";
         }
         if ((c1 < c0) && (Math.abs(deltaRows) < Math.abs(deltaColumns))) {
            direction = "W";
         }
      }

      if (Math.abs(deltaRows) == Math.abs(deltaColumns)) { //OnDIAGorROUTER
         direction = directionHelper(r0, c0, r1, c1, direction);
      }

      return direction;
   }

   /** Helper Method for Direction; Will give diagonal cases.
    *  @param r0 the router row
    *  @param c0 the router column
    *  @param r1 the current cell row
    *  @param c1 the current cell column
    *  @param direction self explainatory
    *  @return diagonal direction ( NE, SE, SW, NW)
    */
   public static String directionHelper(int r0, int c0, int r1, int c1,
      String direction) {

      if ((r1 < r0) && (c1 > c0)) {
            //If North = True and If East = True and Diagonal is already true.
         direction = "NE";
      }
      else if ((r1 < r0) && (c1 < c0)) {
         direction = "NW";
      }
      else if ((r1 > r0) && (c1 > c0)) {
         direction = "SE";
      }
      else if ((r1 > r0) && (c1 < c0)) {
         direction = "SW";
      }
      else if ((r1 == r0) && (c1 == c0)) {
         direction = "--";
      }
      return direction;

   }

   /**
    *  @param grid1 the first grid
    *  @param grid2 the second grid
    *  @param epsilon the difference cutoff that makes two values "equivalent"
    *  @return true if the grids are the same sizes, and the signal values
    *   are all within (<=) epsilon of each other; false otherwise
    */
   public static boolean equivalent(Cell[][] grid1, Cell[][] grid2,
                                    double epsilon) {
      boolean returnTF = false;

      outer: for (int row = 0; row < grid1.length; row++) {
         for (int column = 0; column < grid1[row].length; column++) {
            double signalGrid1 = grid1[row][column].getSignal();
            double signalGrid2 = grid2[row][column].getSignal();

            //if same and true, continue checking
            if ((Math.abs(signalGrid1 - signalGrid2)) < (epsilon)) {
               returnTF = true;
               continue;
            }
            else {
               returnTF = false;
               break outer;
            }

         }
      }

      return returnTF;
   }


   /**
    *
    * @param grid is the grid whose Cells must be updated with the input data
    * @param scnr is the Scanner to use to read the rest of the file
    * @throws IOException if file can not be read
    */
   public static void read(Cell[][] grid, Scanner scnr) throws IOException {

      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[0].length; j++) {

            grid[i][j].setWalls(scnr.next());  //Works
            //System.out.print(grid[i][j].getEast());  // for testing
         }
      }


   }


   /**
    *
    * @param grid the grid to check
    * @return true if valid (consistent), false otherwise
    */
   public static boolean isValid(Cell[][] grid) {

      //boolean eastWestTF = true;
      //boolean northSouthTF = true;
      boolean returnTF = false;
      outer: for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[0].length; j++) {

            //grid[0].length = 5   grid.length = 3
            if (j < grid[0].length - 1) {
               //eastWestTF
               if (grid[i][j].getEast() != grid[i][j + 1].getWest()) {
                  returnTF = false;
                  break outer;
               }
            }
            if (i < grid.length - 1) {
               //northSouthTF
               if (grid[i][j].getSouth() != grid[i + 1][j].getNorth()) {
                  returnTF = false;
                  break outer;
               }
            }
            else {
               returnTF = true;
            }
         }
      }

      return returnTF;
   }


   /**
    *  @param grid the grid of cells to search
    *  @return the minimum signal value
    */
   public static double findMinSignal(Cell[][] grid) {

      double lowestSignalValue = 999999;
      for (int row = 0; row < grid.length; row++) {
         for (int column = 0; column < grid[row].length; column++) {

            double currentSignal = grid[row][column].getSignal();

            if (currentSignal < lowestSignalValue) {
               lowestSignalValue = currentSignal;
            }

         }
      }

      return lowestSignalValue;
   }


   /**
    *  @param grid the collection of cells
    *  @param minSignal the minimum signal strength
    */
   public static void printMinCellCoordinates(Cell[][] grid, double minSignal) {

      int coordinateRowi = 44444444;
      int coordinateColumnj = 55555555;
      for (int row = 0; row < grid.length; row++) {
         for (int column = 0; column < grid[row].length; column++) {

            double currentSignal = grid[row][column].getSignal();
            if (currentSignal == minSignal) {
               coordinateRowi = row;
               coordinateColumnj = column;
            }
         }
      }
      System.out.print("(" + coordinateRowi + ", " + coordinateColumnj + ")");

   }

   /**
    *  @param wall the material type
    *  @return the attenuation rating
    */
   public static int attenuation(char wall) {
   // THIS METHOD IS COMPLETE - DO NOT CHANGE IT
      switch (wall) {
         case 'b':
            return 22;
         case 'c':
            return 6;
         case 'd':
            return 4;
         case 'g':
            return 20;
         case 'w':
            return 6;
         case 'n':
            return 0;
         default:
            System.out.println("ERROR: invalid wall type");
      }
      return -1;
   }


   /**
    *  @param from the original grid
    *  @param to the copy grid
    */
   public static void copy(Cell[][] from, Cell[][] to) {
   // THIS METHOD IS COMPLETE - DO NOT CHANGE IT
      for (int i = 0; i < from.length; i++) {
         for (int j = 0; j < from[0].length; j++) {
            to[i][j] = from[i][j].makeCopy();
         }
      }
   }

   /**
    *  @param grid the array to initialize
    */
   public static void initialize(Cell[][] grid) {
   // THIS METHOD IS COMPLETE - DO NOT CHANGE IT
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[0].length; j++) {
            grid[i][j] = new Cell();
         }
      }
   }

   /**
    *  @param grid the signal grid to display
    *  @param pout the output location
    */
   public static void printAll(Cell[][] grid, PrintWriter pout) {
   // THIS METHOD IS COMPLETE - DO NOT CHANGE IT
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[0].length; j++) {
            pout.print(grid[i][j].toString() + " ");
         }
         pout.println();
      }
   }
}
