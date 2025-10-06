
package AidTrack;

import java.io.Serializable;
import java.util.*;

public class Caller implements Serializable{

	private static final long serialVersionUID = 2L;
	private int callerID;
    private String name;
    private String phoneNumber;
    private String relationshipWithTheInjured;

    public Caller() {
        this("", "", "");
    }

    public Caller(String name, String phoneNumber, String relationshipWithTheInjured) {
        this.callerID = generateUniqueCallerID(); // Generate unique ID during instantiation
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.relationshipWithTheInjured = relationshipWithTheInjured;
    }
    //Generating a unique caller ID
    public static int generateUniqueCallerID() {
        int generateCallerID = (int) (Math.random() * 9000) + 1000;

        for (Caller caller : Incident.getAllCallers()) {
            if (generateCallerID == caller.getCallerID()) {
                return generateUniqueCallerID();
            }
        }
        return generateCallerID;
    }

    //This method will be called when we want to take the user's information
    public void settingCallerInformation() {
        Scanner input = new Scanner(System.in);

        System.out.print("\nWhat is the caller name? " + "\n> ");
        setName(input.nextLine());

        System.out.print("What is the relationship with the person who is injured? " + "\n> ");
        setRelationshipWithTheInjured(input.next());

        String userPhoneNumber;
        boolean invalid;
        do {
            System.out.print("What is the phone number? +97105 ");
            userPhoneNumber = input.next();
            invalid = userPhoneNumber.length() != 8;
            if (invalid) {
                System.out.println("\n\t!<---Invalid: You have to input exactly 8 digits--->!\n");
            }
        } while (invalid);

        setPhoneNumber(userPhoneNumber);

        // Generate and set the unique caller ID here
        this.callerID = generateUniqueCallerID();
        System.out.println("\n\tIMPORTANT: The generated caller ID: " + this.callerID + "\n");

        // Add the caller to the list with the updated callerID
        Incident.getAllCallers().add(this);
    }

    public int getCallerID() {
        return callerID;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getRelationshipWithTheInjured() {
        return this.relationshipWithTheInjured;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setRelationshipWithTheInjured(String relationshipWithTheInjured) {
        this.relationshipWithTheInjured = relationshipWithTheInjured;
    }

}
