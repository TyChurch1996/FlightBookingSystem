/**
 * A representation of the existing Flight records, which your program will need
 * to work with.
 * 
 * This class provides a functional interface which allows for the Booking
 * Management System developer to interact with a simulated record in the
 * following ways:
 * 
 * retrieve the Flight destination and Flight numbers required to instantiate
 * the FlightBooking and HodidayBooking Objects
 * 
 * retrieve Flight costs
 *
 * 
 * Note that you are not permitted to change or add to the code below, rather
 * you need to determine how to create an instance of this record and invoke the
 * methods it provides as needed when implementing your Booking Management
 * System functionality.
 */

public class FlightRecords {

	// Fixed array storing Flight numbers in the FlightRecords

	private final String[] flightNumber = { "QF10", "TG02", "VA01", "JQ12", "EQ56", "SA102", "EM79", "BI421", "MH128",
			"ET52" };

	// Fixed array storing Flight Destinations in the FlightRecords

	private final String[] flightDestinations = { "Darwin", "Perth", "Sydney", "Brisbane", "Auckland", " Christchurch",
			"Hobart", "Bandar", "Kuching", "Bali" };

	// Fixed array storing fares per person in the FlightRecords

	private final double[] flightCosts = { 466, 649, 161, 206, 589, 486, 230, 813, 872, 421 };

	public boolean checkFlightDestination(String dest) {

		int index = getFlightIndex(dest);

		// No flights to the destination "dest"

		if (index < 0)
			return false;

		else
			return true;

	}

	
	/**
	 * Retrieves the Flight number for the specified destination
	 * 
	 * @param dest
	 *            The destination for which the Flight number is being retrieved.
	 * 
	 * @return The Flight number for  the specified  destination 
	 *          was found, or "Unassigned" if the Flight to the specified destination was not
	 *         found.
	 */
	
	public String getFlightNumber(String dest) {

		int index = getFlightIndex(dest);

		if (index == -1)
			return "Unassigned";
		else
			return flightNumber[index];

	}
	
	/**
	 * Retrieves the Flight's price for the specified destination
	 * 
	 * @param dest
	 *            The destination of the Flight for which the price is being retrieved.
	 * 
	 * @return The price for the corresponding Flight if the specified Flight destination 
	 *          was found, or -1 if the Flight to the specified destination was not
	 *         found.
	 */

	public double getFlightprice(String dest) {

		int index = getFlightIndex(dest);

		if (index == -1)
			return -1;
		else
			return flightCosts[index];

	}
	/**
	 * Converts the specified destination to an index within the arrays of
	 * Flight information.
	 * 
	 * @param destination
	 *            the destination for which an index position is required.
	 * 
	 * @return index position for corresponding Flight details if the destination
	 *         was found, or -1 if specified destination was not found.
	 * 
	 */
	
	private int getFlightIndex(String dest) {
		switch (dest.toUpperCase()) {
		case "DARWIN":
			return 0;
		case "PERTH":
			return 1;
		case "SYDNEY":
			return 2;
		case "BRISBANE":
			return 3;
		case "AUCKLAND":
			return 4;
		case "CHRISTCHURCH":
			return 5;
		case "HOBART":
			return 6;
		case "BANDAR":
			return 7;
		case "KUCHING":
			return 8;
		case "BALI":
			return 9;

		default:
			return -1;
		}
	}

}
