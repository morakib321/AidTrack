
package AidTrack;

import java.io.Serializable;

import java.util.*;

public class MedicalTeam implements Serializable {

	//Unique ID given to the class, it is important to serializing and deserializing in a file
	private static final long serialVersionUID = 5L;
	private int medicalTeamID;	
	private String medicalTeamName;
	private List<Ambulance> ambulances = new ArrayList<>();

	public MedicalTeam(String medicalTeamName) {
		// Generating the medical team ID
		this.medicalTeamID = (int) (Math.random() * 9000) + 1000;
		// Medical team name
		this.medicalTeamName = medicalTeamName;
	}

	//Setters-----------------------------------------------------------
	public void setMedicalTeamName(String medicalTeamName) {
		this.medicalTeamName = medicalTeamName;
	}

	public void setAmbulances(List<Ambulance> ambulances) {
		this.ambulances = ambulances;
	}

	//Getters-------------------------------------------------------------
	public String getMedicalTeamName() {
		return medicalTeamName;
	}
	
	public int getMedicalTeamID() {
		return medicalTeamID;
	}

	public List<Ambulance> getAmbulances() {
		// Sorting the list of ambulances based on their availability
		// The ones that are available will be coming first
		sortAmbulances();
		return ambulances;
	}

	public void addAmbulance(Ambulance ambulance) {
		ambulances.add(ambulance);
		sortAmbulances();
	}

	public void sortAmbulances() {
	    // Sorting ambulances using collections.sort and comparator 
	    Collections.sort(ambulances, new Comparator<Ambulance>() {
	        
	        public int compare(Ambulance amb1, Ambulance amb2) {
	            // keeping available ambulance in top and unavailable at the bottom
	            return Boolean.compare(amb2.isAvailable(), amb1.isAvailable());
	        }
	    });
	}

	//find closest ambulance to incident
	public Ambulance findClosestAmbulance(MedicalTeam medicalTeam, Incident incident)
			throws AmbulanceNotAvailableException {

		// Getting the logitude and latitude of the incident
		double incidentLat = incident.getCoordinates()[0];
		double incidentLon = incident.getCoordinates()[1];
		// Initializing closestAmbulance
		Ambulance closestAmbulance = null;
		// Initializing closestDistance
		double closestDistance = Double.MAX_VALUE;

		// Iterate through all ambulances and calculate distances
		for (Ambulance ambulance : medicalTeam.getAmbulances()) { 
																			
			// Calling the calculate distance method to calculate the distance
			double distance = Locator.calculateDistance(ambulance.getLatitude(), 
															   	 ambulance.getLongitude(), 
															   	 incidentLat, incidentLon);

			// Checking if this ambulance is closer AND available
			if ((distance < closestDistance) && ambulance.isAvailable()) {
				closestDistance = distance;
				closestAmbulance = ambulance;
			}

		}

		// If no ambulances were found for the incident
		if (closestAmbulance == null) {
			// Then we through exception for not available ambulances
			throw new AmbulanceNotAvailableException("\n\tEXCEPTION: No available ambulances!\n");
		}

		// Return the closest ambulance
		return closestAmbulance;
	}

}
