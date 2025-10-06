
package AidTrack;

import java.io.Serializable;
import java.util.*;
/*The incident class will implement Serializable */
public class Incident implements Comparable<Incident>, Serializable {
	/*The serialVersionUID serves as a unique identifier for the Incident class, since we 
	 * want to save objects of Incident type.
	 * This ID helps Java recognize the serialized file during deserialization.
	 * If changes are made to the class like changing a method or a field, 
	 * Java may generate a new ID automatically. If this happens, 
	 * Java will consider the class as a different version, which can lead 
	 * to possible exceptions when deserializing old serialized objects.*/
	private static final long serialVersionUID = 1L;
	
    private Caller caller;
    private static ArrayList<Caller> allCallers = new ArrayList<>();
    private String incidentType;
    private ArrayList<String> incidentDescription;
    private int urgencyLevel;
    private boolean resolved = false;
    //This list will only add the incidents that were resolved, or marked as resolved
    //It will only save the deleted incidents from the BST
    private static List<Incident> resolvedIncidents = new ArrayList<>();
    private Ambulance assignedAmbulance = null;
    private String locationAddress; // Address of the incident
    private double[] coordinates; // Longitude and latitude of the incident
    

    //Constructor
    public Incident(Caller caller, String incidentType, int urgencyLevel, String locationAddress) {
        this.caller = caller;
        this.incidentType = incidentType;
        this.incidentDescription = new ArrayList<>();
        this.urgencyLevel = urgencyLevel;
        this.locationAddress = locationAddress;
    }
    

    
    //Getters------------------------------------------------------------------------
    public static ArrayList<Caller> getAllCallers() {
        return allCallers;
    }

    public Caller getCaller() {
        return caller;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public ArrayList<String> getIncidentDescription() {
        return incidentDescription;
    }

    public int getUrgencyLevel() {
        return urgencyLevel;
    }

    public String getLocation() {
        return locationAddress;
    }
    
    public boolean isResolved() {
        return resolved;
    }
    
    public double[] getCoordinates() {
        return coordinates; // Return latitude
    }
    
	public static List<Incident> getResolvedIncidents() {
		return resolvedIncidents;
	}
	
	public Ambulance getAssignedAmbulance() {
		return assignedAmbulance;
	}


    
    //Setters------------------------------------------------------------------------
    public void setCaller(Caller caller) {
        this.caller = caller;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }
    
    
    public void setIncidentDescription(ArrayList<String> incidentDescription) {
        this.incidentDescription = incidentDescription;
    }

    public void setUrgencyLevel(int urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }

    public void setLocation(String location) {
        this.locationAddress = location;
    }
    
    public void setCoordinates(double[] coordinates) {
    	this.coordinates = coordinates;
    }
    

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }
    
    public void markResolved(){
        this.resolved = true;
    }

	public static void setResolvedIncidents(List<Incident> resolvedIncidents) {
		Incident.resolvedIncidents = resolvedIncidents;
	}
	
	public void setAssignedAmbulance(Ambulance assignedAmbulance) {
		this.assignedAmbulance = assignedAmbulance;
	}
	
	
    @Override
    public int compareTo(Incident incident) {
        //the this.urgency which is the incident that we intend to delete
        if (this.urgencyLevel < incident.urgencyLevel) {
            return -1;
        } else if (this.urgencyLevel > incident.urgencyLevel) {
            return 1;
        } else {
            //if the urgency level is equal, then it will also return -1
            return 0;
        }
    }




  

}


