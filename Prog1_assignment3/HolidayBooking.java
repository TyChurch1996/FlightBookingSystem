/*
 * method inherits from booking class
 * overrides some methods and handles
 * storing of HolidayBooking objects
 * additional option of accomadaion cost and duration
 * 
 */

public class HolidayBooking extends Booking
{
   private double accomCost;
   private int accomDuration;

   // constructor for creating a new object
   public HolidayBooking(String flightDestination, String depatureDate,
                         int numberPassengers, String flightNumber,
                         int accomDuration, double accomCost,
                         String[] passengerNames, double flightCostPerPerson)
   {

      super.setDestination(flightDestination);
      super.setDepartureDate(depatureDate);
      super.setNumberPassengers(numberPassengers);
      super.setFlightNumber(flightNumber);
      super.setPassengerNames(passengerNames);
      this.setAccomCost(accomCost);
      this.setAccomDuration(accomDuration);
      super.setTotalCost(flightCostPerPerson * passengerNames.length +
                         accomCost * accomDuration);

      printBookingDetails();
   }

   // constructor fir creating a new object and
   // setting booking id and invoice number
   public HolidayBooking(String flightDestination, String departureDate,
                         int numberPassengers,
                         String flightNumber, String[] passengerNames,
                         double totalCost, double flightCostPerPerson, int bookingId,
                         int invoiceNumber, double accomCost, int accomDuration)
   {
      FlightRecords fr = new FlightRecords();

      super.setDestination(flightDestination);
      super.setTotalCost(totalCost);
      super.setDepartureDate(departureDate);
      super.setNumberPassengers(numberPassengers);
      super.setFlightNumber(flightNumber);
      super.setFlightCostPerPerson(flightCostPerPerson);
      super.setPassengerNames(passengerNames);
      super.setBookingID(bookingId);
      super.setInvoiceNumber(invoiceNumber);
      super.setTotalCost(totalCost);
   }

   // constructor for creating a holidaybooking without accomadation
   public HolidayBooking(String flightDestination, String depatureDate,
                         int numberPassengers, String flightNumber,
                         String[] passengerNames, double flightCostPerPerson)
   {

      super.setDestination(flightDestination);
      super.setDepartureDate(depatureDate);
      super.setNumberPassengers(numberPassengers);
      super.setFlightNumber(flightNumber);
      super.setPassengerNames(passengerNames);
      super.setTotalCost(flightCostPerPerson * passengerNames.length);
      printBookingDetails();
   }

   // constructor for referencing methods
   public HolidayBooking()
   {

   }

   // getter for accomadation cost
   protected double getAccomCost()
   {
      return accomCost;
   }

   // setter for accomadation cost
   protected void setAccomCost(double accomCost)
   {
      this.accomCost = accomCost;
   }

   // getter for accomadation duration
   protected int getAccomDuration()
   {
      return accomDuration;
   }

   // setter for accomodation duration
   protected void setAccomDuration(int accomDuration)
   {
      this.accomDuration = accomDuration;
   }

   // overriding method of superclass
   protected double calculateBookingCost(FlightRecords fr)
   {
      double totalCost;
      if (this.getNumberPassengers() > 0)
      {
         totalCost = 0;
         if (this.accomCost == 0 || this.accomDuration == 0)
         {

            totalCost = super.calculateBookingCost(fr);

         }
         else
         {
            totalCost =
                     fr.getFlightprice(this.getDestination()) *
                        this.getNumberPassengers();
            totalCost += this.accomCost * this.accomDuration;

         }
      }
      else
      {
         totalCost =
                  fr.getFlightprice(this.getDestination());
      }
      return totalCost;
   }

   // overriding method of superclass
   protected void printBookingDetails()
   {
      if (this.accomCost == 0 || this.accomDuration == 0)
      {

         super.printBookingDetails();
      }
      else
      {
         System.out.printf(" Booking ID: %s \n Destination: %s \n " +
                           "Number of passengers: %d \n " +
                           "Flight Number: %s \n Departure Date: %s \n Accomadation duration: %d \n Accomadation Cost per night: $ %f \n Total Accomadation Cost: $ %f",
                           this.getBookingID(), this.getDestination(),
                           this.getNumberPassengers(), this.getFlightNumber(),
                           this.getDepartureDate(), this.getAccomDuration(),
                           this.getAccomCost(),
                           this.getAccomCost() * this.accomDuration);

      }
   }
}
