
/*
 * Sample Menu driven program for Assignment 3: SP3-2019
 * 
 * Class BookingManagementSystem
 * 
 * Implements functionality for a basic Booking Management System where 
 * users interact with the system to make bookings, view invoice, itinerary, and 
 * update bookings 
 */

/*-----bugs-----
 * #user input requires validation for 
 * different data types to what is being asked for
 * #no other bugs known 
 * #produces incorrect totalcost price upon loading file
 */

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BookingManagementSystem
{

   private static Scanner sc = new Scanner(System.in);

   private static ArrayList<FlightBooking> fBList;
   private static ArrayList<HolidayBooking> hBList;
   private static FlightRecords fr;

   // main method controls menu
   public static void main(String args[]) throws BookingException
   {

      fBList = new ArrayList<FlightBooking>();
      hBList = new ArrayList<HolidayBooking>();
      fr = new FlightRecords();
      String selection;
      loadBookingsFB();
      loadBookingsHB();
      do
      {

         // display menu options
         System.out.println("   ***** Company XYZ Booking System *****");
         System.out.println("A. Booking a Flight");
         System.out.println("B. Booking for a Holiday");
         System.out.println("C. View Invoice");
         System.out.println("D. View Itinerary");
         System.out.println("E. View Bookings");
         System.out.println("F. Update Check-in Details");
         System.out.println("X. Exit the program");
         System.out.println();

         // prompt user to enter selection
         System.out.print("Enter selection: ");
         selection = sc.nextLine();

         System.out.println();

         // validate selection input length
         if (selection.length() != 1)
         {
            System.out.println("Error - invalid selection!");
         }
         else
         {

            // process user's selection
            switch (selection.toUpperCase())
            {

               case "A":
                  bookAFlight();
                  break;
               case "B":
                  bookAHoliday();
                  break;
               case "C":
                  viewInvoice();
                  break;
               case "D":
                  viewItinerary();
                  break;
               case "E":
                  viewBookings();
                  break;
               case "F":
                  updateCheckinDate();
                  break;

               case "X":
                  System.out.println("Exiting the program...");
                  break;

               default:
                  System.out.println("Error - invalid selection!");
            }
         }
         System.out.println();

      } while (!selection.equalsIgnoreCase("X"));

   }

   // optional search method not implemented
   private static Booking findABooking(String bId)
   {
      /*
       * Optional - you can implement code to search for a booking and return it here
       * (after which the relevant methods below can call this method when they need
       * to locate a specific booking).
       * 
       * NOTE: It is also acceptable to implement (repeat) your search code inside
       * each of the methods below which need to locate a specific booking.
       * 
       */

      return null;
   }

   // method handles booking a flight and throws exception if destination has no
   // flights
   private static void bookAFlight() throws BookingException
   {

      /*
       * Implement application level code to facilitate booking for a flight here.
       * 
       * Note that the array in which the collection of Booking objects is being
       * stored needs to be declared. A Scanner reference called 'sc' have been
       * declared at the top of the class and you can refer to it here inside this
       * method as required.
       */

      FlightRecords fr = new FlightRecords();

      System.out.println("Enter Destination");
      String flightDestination = sc.nextLine();

      // if no destination throw an exception
      if (fr.checkFlightDestination(flightDestination) == false)
      {
         throw new BookingException("No flights found to destination");
      }

      System.out.println("Enter a Departure Date(dd/mm/yyyy )");
      String depatureDate = sc.nextLine();

      System.out.println("Enter Amount of Passengers");
      int numberPassengers = Integer.parseInt(sc.nextLine());

      String[] passengerNames = new String[numberPassengers];
      int count = 1;

      // ask for passenger names based on amount of passengers
      while (count < passengerNames.length + 1)
      {
         System.out.println("Enter Name of passenger number: " + count);
         passengerNames[count - 1] = sc.nextLine();
         count++;
      }

      String flightNumber = fr.getFlightNumber(flightDestination);
      double totalCost = numberPassengers * fr.getFlightprice(flightDestination);
      FlightBooking fb = new FlightBooking(flightDestination, depatureDate,
                                           numberPassengers, flightNumber,
                                           passengerNames, totalCost,
                                           fr.getFlightprice(flightDestination));
      fBList.add(fb);
      saveBookingsFB();
      fb.printBookingDetails();

   }

   // controls loading Booking for FlightBooking class from file
   private static void loadBookingsFB()
   {
      String flightDestination;
      String flightNumber;
      String departureDate;
      String[] passengerNames;

      String flightCostPerPerson;
      String totalCost;
      String invoiceNumber;
      String bookingId;
      String numberPassengers;
      try
      {
         Scanner sc2 = new Scanner(new FileReader("FlightBookings.txt"));
         String inputLine;
         int count = 0;

         // set variables to array
         // elements after we split the loaded string
         while (sc2.hasNextLine())
         {

            inputLine = sc2.nextLine();
            String[] tempFBArray = inputLine.split(",");
            flightNumber = tempFBArray[0];
            flightDestination = tempFBArray[1];
            bookingId = tempFBArray[2];
            departureDate = tempFBArray[3];
            flightCostPerPerson = tempFBArray[4];
            invoiceNumber = tempFBArray[5];
            numberPassengers = tempFBArray[6];
            totalCost = tempFBArray[7];
            passengerNames = new String[tempFBArray.length - 8];
            int tempcount = 0;

            // method handles the array of passenger names
            for (int count2 = 8; count2 < tempFBArray.length; count2++)
            {
               passengerNames[tempcount] = tempFBArray[count2];
               tempcount++;
            }

            FlightBooking fb = new FlightBooking(flightDestination, departureDate,
                                                 Integer.parseInt(numberPassengers),
                                                 flightNumber,
                                                 passengerNames,
                                                 Double.parseDouble(totalCost),
                                                 Double.parseDouble(flightCostPerPerson),
                                                 Integer.parseInt(bookingId),
                                                 Integer.parseInt(invoiceNumber));

            fBList.add(fb);

            count++;
         }
         sc2.close();
      }
      // our catch creates a new file to prevent future exceptions or errors
      catch (IOException e)
      {
         File f = new File("FlightBookings.txt");

         try
         {
            f.createNewFile();

         }
         catch (Exception e1)
         {

            e1.printStackTrace();
         }
         // we must let user no that no files where loaded
         System.out.println("No file found creating new files...");
      }
   }

   // loads the holidaybooking class and sets array
   private static void loadBookingsHB()
   {
      String flightDestination;
      String flightNumber;
      String departureDate;
      String[] passengerNames;
      String flightCostPerPerson;
      String totalCost;
      String invoiceNumber;
      String bookingId;
      String numberPassengers;
      String accomCost;
      String accomDuration;
      try
      {
         Scanner sc2 = new Scanner(new FileReader("HolidayBookings.txt"));
         String inputLine;
         int count = 0;

         // while loop sets variables based on String.split array
         while (sc2.hasNextLine())
         {

            inputLine = sc2.nextLine();
            String[] tempHBArray = inputLine.split(",");
            flightNumber = tempHBArray[0];
            flightDestination = tempHBArray[1];
            bookingId = tempHBArray[2];
            departureDate = tempHBArray[3];
            flightCostPerPerson = tempHBArray[4];
            invoiceNumber = tempHBArray[5];
            numberPassengers = tempHBArray[6];
            totalCost = tempHBArray[7];
            accomCost = tempHBArray[8];
            accomDuration = tempHBArray[9];

            passengerNames = new String[tempHBArray.length - 10];
            int tempcount = 0;

            // load passengernames after we load other variables since this is the
            // only array involved
            // is is stored at end of files
            for (int count2 = 10; count2 < tempHBArray.length; count2++)
            {
               passengerNames[tempcount] = tempHBArray[count2];
               tempcount++;
            }
            HolidayBooking hb = new HolidayBooking(flightDestination, departureDate,
                                                   Integer.parseInt(numberPassengers),
                                                   flightNumber,
                                                   passengerNames,
                                                   Double.parseDouble(totalCost),
                                                   Double.parseDouble(flightCostPerPerson),
                                                   Integer.parseInt(bookingId),
                                                   Integer.parseInt(invoiceNumber),
                                                   Double.parseDouble(accomCost),
                                                   Integer.parseInt(accomDuration));
            hBList.add(hb);

            count++;
         }
         sc2.close();
      }
      // creates new file if none found to prevent future errors
      catch (IOException e)
      {
         File f = new File("HolidayBookings.txt");

         try
         {
            f.createNewFile();

         }
         catch (IOException e1)
         {

            e1.printStackTrace();
         }
         // notify user no file created so they know file is empty
         System.out.println("No file found creating new files...");
      }
   }

   // this method saves the flightbookings object
   // and is run everytime we book a new
   // flight
   // to ensure it is saved to file
   private static void saveBookingsFB()
   {

      try
      {

         PrintWriter pw2 = new PrintWriter(new FileWriter("FlightBookings.txt"));

         String[] tempPassNameArray;
         String tempName;
         String inputLine;
         int count = 0;

         // writing to file handled with while loop
         while (fBList.size() != count)
         {
            tempName = "";
            tempPassNameArray = fBList.get(count).getPassengerNames();

            // create array of passengers and store in a string
            for (int count2 = 0; count2 != tempPassNameArray.length; count2++)
            {

               tempName += tempPassNameArray[count2] + ",";

            }

            inputLine = fBList.get(count).getFlightNumber() + "," +
                        fBList.get(count).getDestination() + "," +
                        fBList.get(count).getBookingID() + "," +
                        fBList.get(count).getDepartureDate() + "," +
                        fBList.get(count).getFlightCostPerPerson() + "," +
                        fBList.get(count).getInvoiceNumber() + "," +
                        fBList.get(count).getPassengerNames().length + "," +
                        fBList.get(count).getTotalCost() + "," +
                        tempName;
            pw2.println(inputLine);
            count++;
            tempPassNameArray = null;

         }
         pw2.close();
      }
      catch (Exception e)
      {

         e.printStackTrace();

      }

   }

   // method handles savings the HolidayBooking objects and is run after booking a
   // holidayflight to ensure file is kept up to date
   private static void saveBookingsHB()
   {

      try
      {

         PrintWriter pw2 = new PrintWriter(new FileWriter("HolidayBookings.txt"));

         String[] tempPassNameArray;
         String tempName;
         String inputLine;
         int count = 0;

         // method handles writing data to a text file
         while (hBList.size() != count)
         {
            tempName = "";
            tempPassNameArray = hBList.get(count).getPassengerNames();

            // creates string for passenger names
            for (int count2 = 0; count2 != tempPassNameArray.length; count2++)
            {

               tempName += tempPassNameArray[count2] + ",";

            }

            inputLine = hBList.get(count).getFlightNumber() + "," +
                        hBList.get(count).getDestination() + "," +
                        hBList.get(count).getBookingID() + "," +
                        hBList.get(count).getDepartureDate() + "," +
                        hBList.get(count).getFlightCostPerPerson() + "," +
                        hBList.get(count).getInvoiceNumber() + "," +
                        hBList.get(count).getPassengerNames().length + "," +
                        hBList.get(count).getTotalCost() + "," +
                        hBList.get(count).getAccomCost() + "," +
                        hBList.get(count).getAccomDuration() + "," +
                        tempName;

            pw2.println(inputLine);
            count++;
            tempPassNameArray = null;

         }
         pw2.close();
      }
      catch (Exception e)
      {

         e.printStackTrace();

      }

   }

   // method handles booking a holiday flight
   // at this point the only additional features opposed to a flight booking is the
   // option for accomodation

   private static void bookAHoliday() throws BookingException
   {
      /*
       * Implement application level code to facilitate booking for a Holiday here.
       * 
       * Note that the array in which the collection of Booking objects is being
       * stored needs to be declared. A Scanner reference called 'sc' have been
       * declared at the top of the class and you can refer to it here inside this
       * method as required.
       */

      System.out.println("Enter Destination");
      String flightDestination = sc.nextLine();

      // check if our flight destination is valid otherwise throw an exception
      if (fr.checkFlightDestination(flightDestination) == false)
      {
         throw new BookingException();
      }

      System.out.println("Enter a Departure Date(dd/mm/yyyy )");
      String depatureDate = sc.nextLine();

      System.out.println("Enter Amount of Passengers");
      int numberPassengers = Integer.parseInt(sc.nextLine());

      String[] passengerNames = new String[numberPassengers];
      int count = 1;

      while (count < passengerNames.length + 1)
      {
         System.out.println("Enter Name of passenger number: " + count);
         passengerNames[count - 1] = sc.nextLine();
         count++;
      }

      System.out.println(Arrays.toString(passengerNames));
      String flightNumber = fr.getFlightNumber(flightDestination);
      System.out.println("is accommadation required");

      String accomRequired = sc.nextLine();

      if (accomRequired.equals("yes"))
      {
         System.out.println("Enter How Many Nights:");
         int accomDuration = Integer.parseInt(sc.nextLine());
         System.out.println("enter cost of accomodation:");
         double accomCost = Integer.parseInt(sc.nextLine());
         HolidayBooking hb = new HolidayBooking(flightDestination, depatureDate,
                                                numberPassengers, flightNumber,
                                                accomDuration, accomCost,
                                                passengerNames,
                                                fr.getFlightprice(flightDestination));
         hBList.add(hb);
         return;
      }

      HolidayBooking hb = new HolidayBooking(flightDestination, depatureDate,
                                             numberPassengers, flightNumber,
                                             passengerNames,
                                             fr.getFlightprice(flightDestination));
      hBList.add(hb);
      saveBookingsHB();
   }

   // method prints into console the users Itinery based off booking id
   private static void viewItinerary()
   {
      /*
       * Implement application level code to facilitate viewing of Itinerary here.
       * 
       * Note that the array in which the collection of Booking objects is being
       * stored needs to be declared. A Scanner reference called 'sc' have been
       * declared at the top of the class and you can refer to it here inside this
       * method as required.
       */

      System.out.println("enter booking ID:");
      int tempBookingID = sc.nextInt();
      boolean bookingFound = false;
      FlightBooking tempFB = null;
      HolidayBooking tempHB = null;
      int count;

      // for loop standard search function for Flight Bookings
      for (count = 0; count != fBList.size(); count++)
      {
         if (fBList.get(count).getBookingID() == tempBookingID)
         {
            bookingFound = true;
            tempFB = fBList.get(count);

         }
      }

      if (bookingFound == false)
      {
         // standard search function for holiday bookings
         // only executed if a booking is not found for flightbookings
         for (count = 0; count != hBList.size(); count++)
         {
            if (hBList.get(count).getBookingID() == tempBookingID)
            {

               tempHB = hBList.get(count);

            }
         }
      }

      if (tempFB != null)
      {
         String[] tempPassNames = tempFB.getPassengerNames();
         System.out.println("Booking id |  Flight Number  |" +
                            "  Departure Date |  Destination |  Passengers  |");
         System.out.printf("%-11s   %-14s    %-15s    %-16s %s ",
                           tempFB.getBookingID(),
                           tempFB.getFlightNumber(),
                           tempFB.getDepartureDate(),
                           tempFB.getDestination(),
                           tempPassNames[0]);

         {

            if (tempPassNames.length > 1)
            {
               for (count = 1; count < tempFB.getPassengerNames().length; count++)
               {
                  System.out.printf("\n%75s", tempPassNames[count]);

               }
            }

         }
      }

      else if (tempHB != null)
      {
         String[] tempPassNames = tempHB.getPassengerNames();
         System.out.println("Booking id |  Flight Number  | " +
                            " Departure Date |  Destination |  Passengers  |");

         System.out.printf("%-11s   %-14s    %-15s    %-16s  %s",
                           tempHB.getBookingID(),
                           tempHB.getFlightNumber(),
                           tempHB.getDepartureDate(),
                           tempHB.getDestination(),
                           tempPassNames[0]);

         if (tempPassNames.length > 1)
         {
            for (count = 1; count < tempHB.getPassengerNames().length; count++)
            {
               System.out.printf("\n%75s", tempPassNames[count]);

            }
         }
      }
      else
      {

         return;
      }
   }

   // method prints into console all booked booking with destination,flight number
   // and a booking number
   private static void viewBookings()
   {

      /*
       * Implement application level code to facilitate viewing of current bookings
       * in the system here.
       *
       * Note that the array in which the collection of Booking objects is being
       * stored needs to be declared. A Scanner reference called 'sc' have been
       * 
       * declared at the top of the class and you can refer to it here inside this
       * method as required.
       */

      System.out.println("Standard flight Bookings:");
      System.out.println("Flight Destination  |  Flight Number | Booking Number");
      int count;

      // for loop search funcion for flight bookings
      for (count = 0; count != fBList.size(); count++)
      {
         String tempDest = fBList.get(count).getDestination();
         System.out.printf("%-18s      %-18S %s",
                           tempDest, fr.getFlightNumber(tempDest),
                           fBList.get(count).getBookingID() + "\n");
      }

      System.out.println("\nHoliday flight Bookings:");
      System.out.println("Flight Destination  |  Flight Number | Booking Number");

      // for loop search function for holiday bookings
      for (count = 0; count != hBList.size(); count++)

      {
         String tempDest = hBList.get(count).getDestination();
         System.out.printf("%-18s      %-18S %s",
                           tempDest, fr.getFlightNumber(tempDest),
                           hBList.get(count).getBookingID() + "\n");
      }
   }

   // method allows the user to add days onto their accomodation duration
   private static void updateCheckinDate()
   {
      /*
       * Implement application level code to facilitate check-in updates ( changing
       * number of stay days) here.
       * 
       * Note that the array in which the collection of Booking objects is being
       * stored needs to be declared. A Scanner reference called 'sc' have been
       * declared at the top of the class and you can refer to it here inside this
       * method as required.
       */

      // Note: at this stage we allow change of check in dates by either extending or
      // shortening the days of stay
      HolidayBooking tempHB = null;

      System.out.println("Enter Booking Id:");
      int tempBookingID = sc.nextInt();

      int count;

      // for loop executes a search function
      for (count = 0; count != hBList.size(); count++)
      {
         if (hBList.get(count).getBookingID() == tempBookingID)
         {

            tempHB = hBList.get(count);

         }
      }

      System.out.println("Enter How Many Days to Extend Accomadation for:");
      int extraBookingDays = sc.nextInt();

      int newDuration = tempHB.getAccomDuration() + extraBookingDays;
      tempHB.setAccomDuration(newDuration);
      System.out.println("stay duration has been updated to " + newDuration +
                         " Days");
   }

   // method prints to console the users invoice based off booking ID
   private static void viewInvoice()
   {
      /*
       * Implement application level code to facilitate viewing of Invoice here.
       * 
       * Note that the array in which the collection of Booking objects is being
       * stored needs to be declared. A Scanner reference called 'sc' have been
       * declared at the top of the class and you can refer to it here inside this
       * method as required.
       */

      System.out.println("enter booking ID:");
      int tempBookingID = sc.nextInt();
      boolean bookingFound = false;
      FlightBooking tempFB = null;
      HolidayBooking tempHB = null;
      int count;

      // for loop to search for invoice by booking id
      for (count = 0; count != fBList.size(); count++)
      {
         if (fBList.get(count).getBookingID() == tempBookingID)
         {
            bookingFound = true;
            tempFB = fBList.get(count);

         }
      }

      if (bookingFound == false)
      {// for loop to search for invoice by booking id

         for (count = 0; count != hBList.size(); count++)
         {
            if (hBList.get(count).getBookingID() == tempBookingID)
            {

               tempHB = hBList.get(count);

            }
         }
      }

      if (tempFB != null)
      {
         String[] tempPassNames = tempFB.getPassengerNames();
         System.out
                  .println("Booking id | price per flight | Destination | Passengers | total cost |");
         System.out.printf("%-10s   %-15s    %-14.5s    %-12s %-10f ",
                           tempFB.getBookingID(),
                           fr.getFlightprice(tempFB.getDestination()),
                           tempFB.getDestination(),
                           tempPassNames[0],
                           tempFB.calculateBookingCost(fr));

         {

            if (tempPassNames.length > 1)
            {
               for (count = 1; count < tempFB.getPassengerNames().length; count++)
               {
                  System.out.printf("\n%55s", tempPassNames[count]);

               }
            }
         }

      }
      else if (tempHB != null)
      {
         String tempAccomCheck;
         String[] tempPassNames = tempHB.getPassengerNames();
         System.out
                  .println("Booking id | price per flight | Destination | Passengers | total cost (including accom)| ");
         System.out.printf("%-10s   %-15s    %-14.5s    %-12s %-10f ",
                           tempHB.getBookingID(),
                           fr.getFlightprice(tempHB.getDestination()),
                           tempHB.getDestination(),
                           tempPassNames[0],
                           tempHB.calculateBookingCost(fr));

         {

            if (tempPassNames.length > 1)
            {
               for (count = 1; count < tempHB.getPassengerNames().length; count++)
               {
                  System.out.printf("\n%55s", tempPassNames[count]);

               }
            }

            if (tempHB.getAccomCost() != 0 || tempHB.getAccomDuration() != 0)
            {
               tempAccomCheck = "yes";
            }
            else
            {
               tempAccomCheck = "no";
            }
         }
         System.out
                  .println("\n-------------------------------------------------------------------");

         if (tempAccomCheck.equals("yes"))
         {

            System.out
                     .println("Accomodation booked | Nights Booked |" +
                              " Price/Night | total cost (accom and flights)");
            System.out.printf("%-25s %-12s %-12s %s ",
                              tempAccomCheck,
                              tempHB.getAccomDuration(),
                              tempHB.getAccomCost(),
                              tempHB.getAccomCost() * tempHB.getAccomDuration());

            {

            }
         }
         else
         {

            System.out
                     .println("Accomodation booked | Nights Booked |" +
                              " Price/Night | total cost (accomadation)\n");
            System.out.printf("%s",
                              tempAccomCheck);

            {

            }

         }

      }
      else
      {

         return;
      }
   }
}
