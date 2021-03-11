
/*
 * Abstract class for handling flight bookings
 * flightbooking and holiday booking class inherit
 * from here to reduce code reuse due to polymorphic principles
 * 
 * 
 * 
 */

import java.util.Random;

public abstract class Booking
{

   private String flightDestination;
   private String flightNumber;
   private String departureDate;
   private String passengerNames[];

   private double flightCostPerPerson;
   private double totalCost;

   private int invoiceNumber;
   private int bookingId;
   private int numberPassengers;

   // constructor for booking, generates booking id and invoice number
   public Booking()
   {
      generateBookingId();
      generateInvoiceNumber();

   }

   // generate invoice number
   protected void generateInvoiceNumber()
   {
      Random rnd = new Random();
      this.invoiceNumber = rnd.nextInt(2147483647);

   }

   // generates booking id
   protected void generateBookingId()
   {
      Random rnd = new Random();
      this.bookingId = rnd.nextInt(2147483647);

   }

   // getter for flight destination
   protected String getDestination()
   {
      return this.flightDestination;
   }

   // setter for flight destination
   protected void setDestination(String destination)
   {
      flightDestination = destination;
   }

   // getter for flightnumber
   protected String getFlightNumber()
   {
      return this.flightNumber;
   }

   // setter for flightnumber
   protected void setFlightNumber(String flightNumber)
   {
      this.flightNumber = flightNumber;
   }

   // getter for departure date
   protected String getDepartureDate()
   {
      return this.departureDate;
   }

   // setter for departuredate
   protected void setDepartureDate(String departureDate)
   {
      this.departureDate = departureDate;
   }

   // getter for flightcostperperson
   protected double getFlightCostPerPerson()
   {
      return this.flightCostPerPerson;
   }

   // setter for flightcostperperson
   protected void setFlightCostPerPerson(double flightCostPerPerson)
   {
      this.flightCostPerPerson = flightCostPerPerson;
   }

   // getter for totalcost
   protected double getTotalCost()
   {
      return this.totalCost;
   }

   // setter for total cost
   protected void setTotalCost(double totalCost)
   {
      this.totalCost = totalCost;
   }

   // getter for invoice number
   protected int getInvoiceNumber()
   {
      return this.invoiceNumber;
   }

   // setter for invoicenumber
   protected void setInvoiceNumber(int invoiceNumber)
   {
      this.invoiceNumber = invoiceNumber;
   }

   // getter for bookingID
   protected int getBookingID()
   {
      return this.bookingId;
   }

   // setter for bookingID
   protected void setBookingID(int bookingID)
   {
      this.bookingId = bookingID;
   }

   // getter for number of passengers
   protected int getNumberPassengers()
   {
      return this.numberPassengers;
   }

   // setter for number of passengers
   protected void setNumberPassengers(int numberPassengers)
   {
      this.numberPassengers = numberPassengers;
   }

   // parent method to calculate booking costs
   protected double calculateBookingCost(FlightRecords fr)
   {

      double totalCost =
               fr.getFlightprice(this.getDestination()) * this.getNumberPassengers();
      return totalCost;

   }

   // parent method to print booking details
   protected void printBookingDetails()
   {

      System.out.printf(" Booking ID: %s \n Destination: %s \n " +
                        "Number of passengers: %d \n " +
                        "Flight Number: %s \n Departure Date: %s \n",
                        this.getBookingID(), this.getDestination(),
                        this.getNumberPassengers(), this.getFlightNumber(),
                        this.getDepartureDate());
   }

   // getter for passengerNames array
   protected String[] getPassengerNames()
   {
      return this.passengerNames;
   }

   // setter for passengernames
   protected void setPassengerNames(String passengerNames[])
   {
      this.passengerNames = passengerNames;
   }

}
