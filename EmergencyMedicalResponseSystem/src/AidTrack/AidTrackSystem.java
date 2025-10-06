
package AidTrack;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AidTrackSystem {

	private static final BSTree incidentBST = new BSTree();
	private static final Scanner input = new Scanner(System.in);
	static MedicalTeam medicalTeam = new MedicalTeam("Abu Dhabi Emergency Team");

	public static void main(String[] args) throws NegativeUrgencyException, NegativeCallerIdException {
		System.out.println("Welcome to the AidTrack Emergency Response System");

		incidentBST.loadingSavedIncidents("Incidents.dat");
		incidentBST.loadResolvedIncidents("ResolvedIncidents.dat");
		incidentBST.loadSavedAmbulances(medicalTeam, "Ambulances.dat");
		
		//<******MY DEAR PROGRAMMER THIS IS AN IMPORTANT MESSAGE FOR YOU BEFORE RUNNING THIS CODE********>
		
		//FIRST: Uncomment on the ambulance objects down here, run the code, then stop running by typing E
		//SECOND: Comment on the created objects on ambulances
		//WHY: Because we created a file that saves those objects in it, so running the code one time is enough
		//and the objects of ambulances will be retreived from the file
		
//		Ambulance ambulance1SSMC = new Ambulance(medicalTeam, "Ambulance 1SSMC", 24.3451653, 54.5334734);
//		Ambulance ambulance2SSMC = new Ambulance(medicalTeam, "Ambulance 2SSMC", 24.312652, 54.603977);
//		Ambulance ambulance3SSMC = new Ambulance(medicalTeam, "Ambulance 3SSMC", 24.299253, 54.584717);
//		Ambulance ambulance4SSMC = new Ambulance(medicalTeam, "Ambulance 4SSMC", 24.3437599, 54.5720831);
//		Ambulance ambulance5SSMC = new Ambulance(medicalTeam, "Ambulance 5SSMC", 24.292653, 54.658383);
//
//		Ambulance ambulance1BMC = new Ambulance(medicalTeam, "Ambulance 1BMC", 24.3489795, 54.5252562);
//		Ambulance ambulance2BMC = new Ambulance(medicalTeam, "Ambulance 2BMC", 24.368714, 54.525293);
//		Ambulance ambulance3BMC = new Ambulance(medicalTeam, "Ambulance 3BMC", 24.350047, 54.526345);
//		Ambulance ambulance4BMC = new Ambulance(medicalTeam, "Ambulance 4BMC", 24.347202, 54.542525);
////
//		Ambulance ambulance1LLH = new Ambulance(medicalTeam, "Ambulance 1LLH ", 24.3781446, 54.4920475);
//		Ambulance ambulance2LLH = new Ambulance(medicalTeam, "Ambulance 2LLH ", 24.373281, 54.508105);
//		Ambulance ambulance3LLH = new Ambulance(medicalTeam, "Ambulance 3LLH ", 24.381444, 54.491274);
//		Ambulance ambulance4LLH = new Ambulance(medicalTeam, "Ambulance 4LLH ", 24.358156, 54.472293);
////
//		Ambulance ambulance1DAEH = new Ambulance(medicalTeam, "Ambulance 1DAEH", 24.4057565, 54.4764194);
//		Ambulance ambulance2DAEH = new Ambulance(medicalTeam, "Ambulance 2DAEH", 24.402368, 54.490507);
//		Ambulance ambulance3DAEH = new Ambulance(medicalTeam, "Ambulance 3DAEH", 24.407567, 54.471875);
//		Ambulance ambulance4DAEH = new Ambulance(medicalTeam, "Ambulance 4DAEH", 24.394686, 54.517901);
////
////		// Add ambulances to the medical team
//		medicalTeam.addAmbulance(ambulance1SSMC);
//		medicalTeam.addAmbulance(ambulance2SSMC);
//		medicalTeam.addAmbulance(ambulance3SSMC);
//		medicalTeam.addAmbulance(ambulance4SSMC);
//		medicalTeam.addAmbulance(ambulance5SSMC);
////
//		medicalTeam.addAmbulance(ambulance1BMC);
//		medicalTeam.addAmbulance(ambulance2BMC);
//		medicalTeam.addAmbulance(ambulance3BMC);
//		medicalTeam.addAmbulance(ambulance4BMC);
////
//		medicalTeam.addAmbulance(ambulance1LLH);
//		medicalTeam.addAmbulance(ambulance2LLH);
//		medicalTeam.addAmbulance(ambulance3LLH);
//		medicalTeam.addAmbulance(ambulance4LLH);
////
//		medicalTeam.addAmbulance(ambulance1DAEH);
//		medicalTeam.addAmbulance(ambulance2DAEH);
//		medicalTeam.addAmbulance(ambulance3DAEH);
//		medicalTeam.addAmbulance(ambulance4DAEH);
		//Comment till here after running the first time
//---------------------------------------------------------------------------------------
		
		boolean exit = false;
		while (!exit) {
			menu();
			char choice = input.next().toUpperCase().charAt(0);

			switch (choice) {
			case 'A':
				addIncident();
				incidentBST.saveActiveIncidents("Incidents.dat");
				break;
			case 'C':
				deleteResolvedIncidents();
				incidentBST.saveActiveIncidents("Incidents.dat");
				break;
			case 'F':
				findTheClosestAmbulance();
				incidentBST.saveActiveIncidents("Incidents.dat");
				incidentBST.saveAmbulances(medicalTeam, "Ambulances.dat");
				break;
			case 'U':
				updateIncident();
				incidentBST.saveActiveIncidents("Incidents.dat");
				break;
			case 'D':
				displayIncidents();
				break;
			case 'G':
				getAllResolvedIncidents();
				break;
			case 'I':
				displayIncidentReverseInorder();
				break;
			case 'M':
				displayAllAmbulances();
				break;
			case 'R':
				removingdata();
				incidentBST.saveActiveIncidents("Incidents.dat");
				break;
			case 'E':
				System.out.println("\n\tEXITING: Hope you have a safe track!");
				incidentBST.saveActiveIncidents("Incidents.dat");
				incidentBST.saveAmbulances(medicalTeam, "Ambulances.dat");

				exit = true;
				break;
			default:
				System.out.println("\n\tINVALID: Invalid choice!\n\n");
			}
		}
		input.close();
	}

	public static void menu() {
		System.out.println("""
				\n\n
				-------------------------------------------------------------------------
				AidTrack System Menu
				-------------------------------------------------------------------------
				(A) Add a new incident
				(F) Find the closest ambulance
				(C) Delete a resolved incident
				(U) Update an incident
				(D) Display all incidents
				(G) Get all resolved incidents
				(I) All Incidents in Reverse Inorder (from the highest urgency)
				(M) All Ambulances
				(R) Remove Irrelavant data 
				(E) Exit the system""");
		System.out.print("\nChoose an option " + "\n> ");
	}

	public static void addIncident() {
		// Example for adding an incident
		System.out.println("\n\n-------------------------------------------------------------------------");
		System.out.println("Add a new incident");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("Enter caller's details_______________________________________________|");

		// This will take the caller's name, relationship with the injured, and phone
		// number
		Caller newCaller = new Caller();
		newCaller.settingCallerInformation();

		System.out.print("\nType of Incident " + "\n> ");
		input.nextLine();
		String type;
		do {
			type = input.nextLine();
		} while (type == "");

		System.out.print("\nDescribe the incident " + "\n> ");
		String incidentDescription;

		do {
			incidentDescription = input.nextLine();
		} while (incidentDescription == "");

		System.out.print("\nUrgency Level (1-10) " + "\n> ");
		int urgency = -1;
		do {
			try {
				urgency = input.nextInt();
				if (urgency < 1 || urgency > 10) {
					throw new NegativeUrgencyException("NEGATIVE URGENCY EXCEPTION");
				}

			} catch (InputMismatchException | NegativeUrgencyException ex) {
				System.out.print("\n\tEXCEPTION: The urgency level must be a number!\n\nUrgency Level (1-10) \n> ");
				input.nextLine();
				urgency = -1;
			}
		} while (urgency < 1 || urgency > 10);

		// The user would specify the location of the incident
		System.out.print("\nLocation (specify the city or place name) " + "\n> ");
		input.nextLine();
		String location = input.nextLine().trim();

		System.out.println("\nMESSAGE: Wait for a while for finding the incident's location...");

		// Creating a new incident object
		Incident incident = new Incident(newCaller, type, urgency, location);
		incident.getIncidentDescription().add(incidentDescription);
		// Setting the coordinates of the specified location
		incident.setCoordinates(Locator.getLocationFromAddress(location));

		// Add incident to BST
		incidentBST.insert(incident);

		System.out.println("\n\tMESSAGE: Incident added successfully!\n\n");
	}

	public static void findTheClosestAmbulance() throws NegativeCallerIdException {
		System.out.println("\n\n-------------------------------------------------------------------------");
		System.out.println("Finding The Closest Ambulance for An Incident");
		System.out.println("-------------------------------------------------------------------------");

		System.out.print("\nEnter Caller ID to find the closest ambulance " + "\n> ");
		int callerID;
		try {
			callerID = input.nextInt();

			if (callerID < 1) {
				throw new NegativeCallerIdException("NEGATIVE CALLER ID");
			}

			Incident incident = incidentBST.search(callerID);

			// If the incident returned is not null
			try {
				if (incident != null) {
					if (incident.getAssignedAmbulance() == null) {
						// This method will find the available and closest ambulance to the incident
						// based on the location that they gave
						Ambulance closestAmbulance = medicalTeam.findClosestAmbulance(medicalTeam, incident);
						// When the ambulance is found, it will be assigned to the incident, meaning
						// that the ambulance is responsible for going there
						incident.setAssignedAmbulance(closestAmbulance);
						// Setting the ambulance to no available because they are now dealing with a
						// specific incident
						closestAmbulance.setNotAvailable();
						incident.markResolved();
						// Sorting the ambulances list so that the available ones come first
						medicalTeam.sortAmbulances();
						// Message that clarifies that an ambulance has been assigned
						System.out.println("\n\tAmbulance: " + closestAmbulance.getAmbulanceName()
								+ " is on their way to the incident's location!\n");
					} else {
						System.out.println("\n\tINVALID: This incident is already assigned to an ambulance!\n\n");
					}
				} else {
					// If the specified incident was not found
					System.out.println("\n\tINVALID: Incident not found with caller ID: " + callerID + "\n\n");
				}

			} catch (AmbulanceNotAvailableException ex) {
				System.out.println("\n\tEXCEPTION: No available ambulances \n\n");
			}
		} catch (NegativeCallerIdException | InputMismatchException ex) {
			System.out.println("\n\tEXCEPTION: You should input a positive number ID!\n");
			input.nextLine();
		}

	}

	// I need to check on this one
	public static void deleteResolvedIncidents() {
		// Typing in the caller ID
		System.out.println("\n-------------------------------------------------------------------------");
		System.out.println("Deleting a resolved incident");
		System.out.println("-------------------------------------------------------------------------");
		System.out.print("Enter the caller's ID " + "\n> ");
		// Taking the user input
		int callerID;
		try {
			callerID = input.nextInt();

			if (callerID < 1) {
				throw new NegativeCallerIdException("NEGATIVE CALLER ID");
			}

			// Calling a method that returns a boolean if the typed ID is found in the tree
			Incident incidentToDelete = incidentBST.search(callerID);

			if (incidentToDelete != null && incidentToDelete.getAssignedAmbulance() != null) {
				// Mark the incident as resolved
				incidentToDelete.markResolved();
				// The incident that would be deleted will be saved in an ArrayList
				Incident.getResolvedIncidents().add(incidentToDelete);
				// Deleting the incident from the BST
				incidentBST.delete(incidentToDelete);
				// Saving the deleted incidents in the file
				BSTree.saveResolvedIncidents("ResolvedIncidents.dat");
				// Printing out the message in the console
				System.out.println("\n\tMESSAGE: Incident deleted successfully!\n\n");
			} else {
				if (incidentToDelete.getAssignedAmbulance() == null) {
					System.out.println("\n\tINVALID: The incident is not resolved yet!!\n\n");
				} else {
					System.out.println("\n\tINVALID: Incident not found with caller ID: " + callerID + "\n\n");
				}
			}
			
		} catch (InputMismatchException | NegativeCallerIdException ex) {
			System.out.print("\n\tEXCEPTION: The ID must be a 4 digits positive number!\n\n");
			input.nextLine();
		}
	}

	public static void updateIncident() {
		System.out.println("\n-------------------------------------------------------------------------");
		System.out.println("Updating an Incident");
		System.out.println("-------------------------------------------------------------------------");

		System.out.print("Enter Caller ID to update\n> ");
		int callerID;
		try {
			callerID = input.nextInt();

			if (callerID < 1) {
				throw new NegativeCallerIdException("NEGATIVE CALLER ID");
			}
			// Searching for the incident using the caller ID
			Incident incident = incidentBST.search(callerID);
			// Calling the method for updating the incident
			incidentBST.update(incident);

		} catch (InputMismatchException | NegativeCallerIdException ex) {
			System.out.print("\n\tEXCEPTION: The ID must be a 4 digits positive number!\n\n");
			input.nextLine();
		}
	}

	public static void displayIncidents() throws NegativeUrgencyException {

		if (incidentBST.isEmpty()) {
			System.out.println("\n\tMESSAGE: There are no active incidents!\n\n");
		} else {
			System.out.println("\n\n<--- Displaying all incidents in the system --->\n");
			incidentBST.displayTree();
			System.out.println("\n---------------------------------------------\n");
			// Add
			// exception-----------------------------------------------------------------------------------------------------
			System.out.print("Which incident of urgency level do you want to see? " + "\n> ");

			try {

				int userInputUrgency = input.nextInt();
				if (userInputUrgency <= 0 || userInputUrgency>=11 ) {
					throw new NegativeUrgencyException("NEGATIVE URGENCY EXCEPTION");
				}
				incidentBST.displaySpecifiedIncidents(userInputUrgency);

			} catch (InputMismatchException | NegativeUrgencyException ex) {
				System.out.println("\n\tEXCEPTION: You should input a positive number!\n");
				input.nextLine();
			}
		}

	}

	public static void getAllResolvedIncidents() {
		System.out.println("\n\n-------------------------------------------------------------------------");
		System.out.println("Resolved Incidents");
		System.out.println("-------------------------------------------------------------------------");

		if (Incident.getResolvedIncidents().isEmpty()) {
			System.out.println("\n\tMESSAGE: There are no resolved incidents!\n\n");
		} else {
			for (int i = Incident.getResolvedIncidents().size() - 1; i >= 0; i--) {
				Incident incident = Incident.getResolvedIncidents().get(i);
				System.out.println("Urgency Level: " + incident.getUrgencyLevel());
				System.out.println("Caller ID: " + incident.getCaller().getCallerID());
				System.out.println("Caller Name: " + incident.getCaller().getName());
				System.out.println("Caller Phone No.: " + incident.getCaller().getPhoneNumber());
				System.out.println("Incident Type: " + incident.getIncidentType());
				System.out.println("Incident Location: " + incident.getLocation());
				System.out.println("Incident Description: " + incident.getIncidentDescription());
				System.out.println("Assigned Ambulance: " + ((incident.getAssignedAmbulance() != null)
						? incident.getAssignedAmbulance().getAmbulanceName()
						: "Not assigned"));
				System.out.println("Resolved: " + ((incident.isResolved()) ? "Resolved" : "Not resolved"));
				System.out.println("--------------------------------------------------------------");

			}
		}
	}

	public static void displayIncidentReverseInorder() {
		System.out.println("\n\n-------------------------------------------------------------------------");
		System.out.println("All Incidents in Reverse Inorder (from the highest urgency)");
		System.out.println("-------------------------------------------------------------------------");

		System.out.println();
		incidentBST.reverseInorder();
		System.out.println();

	}

	public static void displayAllAmbulances() {

		System.out.println("\n\n-------------------------------------------------------------------------");
		System.out.println("All Ambulances");
		System.out.println("-------------------------------------------------------------------------");

		if (medicalTeam.getAmbulances().isEmpty()) {
			System.out.println("\n\tMESSAGE: There are no ambulances!\n\n");
		} else {
			for (int i = 0; i < medicalTeam.getAmbulances().size(); i++) {
				Ambulance ambulance = medicalTeam.getAmbulances().get(i);
				System.out.println("Ambulance ID: " + ambulance.getAmbulanceID());
				System.out.println("Ambulance Name: " + ambulance.getAmbulanceName());
				System.out.println("Availability: " + ((ambulance.isAvailable()) ? "Available" : "Not Available"));
				System.out.println("Coordinates: " + ambulance.getLatitude() + ", " + ambulance.getLongitude());
				System.out.println("--------------------------------------------------------------");

			}
		}
	}
	
	public static void removingdata() {
		System.out.println("\n\tMESSAGE: All the data with emergency level less than 3 is deleted!\n");
		incidentBST.removeIrrelavantData();
	}
	


}
