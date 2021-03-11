/*
 * method inherits from booking class
 * overrides some methods and handles
 *  storing of flightbooking objects
 * 
 */
public class FlightBooking extends Booking
{
   // constructor for creating new objects
   public FlightBooking(String flightDestination, String depatureDate,
                        int numberPassengers, String flightNumber,
                        String[] passengerNames, double totalCost,
                        double flightCostPerPerson)
   {

      super.setDestination(flightDestination);
      super.setTotalCost(totalCost);
      super.setDepartureDate(depatureDate);
      super.setNumberPassengers(numberPassengers);
      super.setFlightNumber(flightNumber);
      super.setFlightCostPerPerson(flightCostPerPerson);
      super.setPassengerNames(passengerNames);

   }

   // constructor for referencing methods within class
   // called by default if not declared
   public FlightBooking()
   {

   }

   // constructor for creating a new object while loading booking id and invoice
   // number (prevents generation of new identification number from super class)
   public FlightBooking(String flightDestination, String departureDate,
                        int numberPassengers,
                        String flightNumber, String[] passengerNames,
                        double totalCost, double flightCostPerPerson,
                        int bookingId,
                        int invoiceNumber)
   {
      super.setDestination(flightDestination);

      super.setTotalCost(totalCost);
      super.setDepartureDate(departureDate);
      super.setNumberPassengers(numberPassengers);
      super.setFlightNumber(flightNumber);
      super.setFlightCostPerPerson(flightCostPerPerson);
      super.setPassengerNames(passengerNames);
      super.setBookingID(bookingId);
      super.setInvoiceNumber(invoiceNumber);

   }

   // overriding method of superclass
   protected double calculateBookingCost(FlightRecords fr)
   {
      double totalCost = 0;
      if (this.getNumberPassengers() > 1)
      {
         totalCost =
                  fr.getFlightprice(this.getDestination()) *
                     this.getNumberPassengers();

      }
      else
      {
         totalCost =
                  fr.getFlightprice(this.getDestination());
      }
      return totalCost;
   }

   // overriding method of superclass
   // alternatively we could just use super method for this
   protected void printBookingDetails()
   {
      System.out.printf(" Booking ID: %s \n Destination: %s \n " +
                        "Number of passengers: %d \n " +
                        "Flight Number: %s \n Departure Date: %s \n",
                        this.getBookingID(), this.getDestination(),
                        this.getNumberPassengers(), this.getFlightNumber(),
                        this.getDepartureDate());
   }

}
