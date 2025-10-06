

package AidTrack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class Locator {
	// We got the API key from the website OpenCage
	private static final String API_KEY = "678e7f5ce9b94db3a4036a8d4a8a090e";

	// Method to get latitude and longitude from an address
	public static double[] getLocationFromAddress(String address) {
		try {
			// Constructing the URL with the key API
			String urlString = "https://api.opencagedata.com/geocode/v1/json?q=" + address.replace(" ", "%20") + "&key="
					+ API_KEY;
			// This line opens the connection to the link that we just constructed
			HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
			/*
			 * Setting the method request to "GET", meaning that we are requesting the data
			 * from the server and we need to get the results from the request
			 */
			connection.setRequestMethod("GET");

			// Create a BufferedReader to read text from the server's response...
			// connection.getInputStream() gets the raw data from the connection, which could
			// be in bytes...
			// InputStreamReader converts that raw data into characters...
			// BufferedReader makes reading the characters more efficient and allows us to
			// read lines of text easily
			/*
			 * The request will look like this { "results": [ { "components": { "country":
			 * "United Arab Emirates", "state": "Abu Dhabi", "city": "Abu Dhabi",
			 * "postcode": "00000", // Note: some locations may not have a postcode "road":
			 * "Corniche Rd", "house_number": "123" }, "formatted":
			 * "123 Corniche Rd, Abu Dhabi, United Arab Emirates", "geometry": { "lat":
			 * 24.4539, "lng": 54.3773 } } ], "status": { "code": 200, "message": "OK" },
			 * "documentation": "https://opencagedata.com/api" }
			 * 
			 */
			BufferedReader inputReader = new BufferedReader(new InputStreamReader(connection.getInputStream())); // Read
																													// the
																													// response
			String inputLine;
			// Build the response content
			StringBuilder content = new StringBuilder();

			/*
			 * The following loop reads the response line by line from the BufferedReader
			 * until there are no more lines to read... Because when we make an API request,
			 * the response we receive would be in a structured format which contains the
			 * results -> components (the country, city, road, etc...), geometry (logitude
			 * and latitude) , status (which contains message which is the result of the
			 * request)
			 */
			while ((inputLine = inputReader.readLine()) != null) {
				// Each response line will be appended to the StringBuilder "content"
				content.append(inputLine);
			}
			inputReader.close(); // Close the input stream

			/*
			 * Within the request, there is a section called status, which contains a
			 * message this message shows the result of the request, if it was OK, meaning
			 * that the request got accepted then we will go within the if condition to get
			 * the latitude and longitude. Otherwise if the request was not "OK" it means
			 * that the request did not get accepted
			 */
			/*
			 * content.toString() converts the StringBuilder content into a String
			 * containing the response from the API request
			 */
			JSONObject json = new JSONObject(content.toString());

			// Retrieve the "status" object first, then check the "code" inside it
			JSONObject status = json.getJSONObject("status");
			int statusCode = status.getInt("code");

			// Check if the status code is 200 (OK)
			if (statusCode == 200) {
				// Getting the latitude from the request under the results section (from
				// geometry section specifically)
				double latitude = json.getJSONArray("results").getJSONObject(0).getJSONObject("geometry")
						.getDouble("lat");
				// Getting the longitude from the request under the results section (from
				// geometry section specifically)
				double longitude = json.getJSONArray("results").getJSONObject(0).getJSONObject("geometry")
						.getDouble("lng");
				// Returning the coordinates in an array of double type
				return new double[] { latitude, longitude };
			}

		} catch (Exception e) {
			e.printStackTrace(); // Print any exceptions
			return new double[]{24.5021494,54.5973569};//Return any random coordinates
		}
		return null;
		
	}

	public static double calculateDistance(double ambulanceLat, double ambulanceLon, double incidentLat,
			double incidentLon) {
		final int R = 6371; // Radius of the Earth in kilometers
		// Calculating the difference between the latitude of the ambulance and incident
		// location
		// And converting them to radians, because we will be using sin and cos, and
		// these two functions
		// in Java they only deal with values in radius
		double latDistance = Math.toRadians(incidentLat - ambulanceLat);
		// Calculating the difference between the longitude of the ambulance and the
		// incident locations
		// Then converting them to radians, since we will use the sin and cos
		// And these functions only deal with radius values
		double lonDistance = Math.toRadians(incidentLon - ambulanceLon);

		// Using Haversine Formula
		/*
		 * When we calculate distances on a sphere, we are dealing with angular
		 * distances (because the sphere is not in 2D). For this, we use the Haversine
		 * formula, which works with a "half-chord" concept to represent the distance in
		 * a way that accounts for Earth’s curvature.
		 */
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(ambulanceLat))
				* Math.cos(Math.toRadians(incidentLat)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		// Converts a into an angle, which represents the angular distance between the
		// points in radians.
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		// convert the angular distance into a physical distance on Earth’s surface
		return R * c;
	}
}
