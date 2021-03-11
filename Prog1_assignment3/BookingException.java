/*
 * this class handles a booking exception 
 * which is thrown when a flight detination does not match
 * 
 */
public class BookingException extends Exception
{

   // standard exception
   BookingException()
   {
      super();

   }

   // standard exception with a predefined error message
   BookingException(String message)
   {
      super(message);

   }
}
