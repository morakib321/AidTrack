
package AidTrack;

import java.io.Serializable;

public class Ambulance implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	private MedicalTeam medicalTeam;
    private int ambulanceID;
    private String ambulanceName;
    private double latitude; // Latitude of the ambulance's location
    private double longitude; // Longitude of the ambulance's location
    private boolean available;

    public Ambulance(MedicalTeam medicalTeam, String ambulanceName, double latitude, double longitude) {
        //Calling a method to generate a unique ID
    	this.medicalTeam = medicalTeam;
        this.ambulanceID = generateAmbulanceID();
        this.ambulanceName = ambulanceName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.available = true;
    }

    
    //Getters--------------------------------------------------------------------- 
    public int getAmbulanceID() {
        return ambulanceID;
    }

     public double getLatitude() {
        return latitude;
    }
     
     public double getLongitude() {
         return longitude; 
     }
     
     public boolean isAvailable() {
         return available;
     }
     
     public String getAmbulanceName() {
    	 return ambulanceName;
     }
     
     
    //Setters--------------------------------------------------------------------- 
    public void setLatitude(double latitude) {
        this.latitude = latitude; 
    }
  
    public void setLongitude(double longitude) {
        this.longitude = longitude; 
    }

    public void setAvailable() {
        available = true;
    }
    public void setNotAvailable() {
        available = false;
    }

    
    public int generateAmbulanceID() {
        //Geenrating an id using math random
        int id = (int) (Math.random() * 9000) + 1000;
        //Iterating through each object in the ambulance list of the medical team
        for (Ambulance ambulance : medicalTeam.getAmbulances()) {
            //if the id generated is the same as an id that is found in one of the ambulances
            if (ambulance.getAmbulanceID() == id) {
                //it will return the method again to regenerate the id (recursion)
                return generateAmbulanceID();
            }
        }
        //Return the ID
        return id;
    }
    
    @Override 
    public String toString() {
    	return "Ambulance: " + ambulanceName + "\nID: " + ambulanceID + "\nAvailable: " + ambulanceID + "\n-------------------";
    }

}
