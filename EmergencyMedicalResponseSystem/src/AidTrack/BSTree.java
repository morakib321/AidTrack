
package AidTrack;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.IOException;

import java.util.*;

public class BSTree implements Tree<Incident> {

	private TreeNode root;
	private int size = 0;

	public void saveActiveIncidents(String filename) {
		// The ObjectOutputStream writes objects in the file as a stream of bytes
		// specifically
		// Here we are creating an object of ObjectOutputStream
		// -------------------------------------------------------
		// The FileOutputStream is a class in Java which is used to write the byte
		// streams in a file
		// Here we created an object of FileOutputStream that will open a file and
		// writes the data we want byte by byte
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
			/*
			 * Here in the try(), what happens inside the brackets is that we create the
			 * object ObjectOutputStream and it will be closed automatically for preventing
			 * the file resources to get leaked
			 */
			// Calling a method to save the node in the file as a stream
			saveIncidentData(root, outputStream);
			// Capturing IOException since creating an ObjectOutputStream or a
			// FileOutputStream object can throw IOException
		} catch (IOException ex) {
			// Printing out the exception
			System.out.println(
					"\n\tEXCEPTION 1: An IOException occured when creating ObjectOutputStream or FileOutputStream object!!\n");
			ex.printStackTrace();
		}
	}

	private void saveIncidentData(TreeNode node, ObjectOutputStream outputStream) throws IOException {
		// If the current node is not, then return, meaning it will do nothing
		if (node == null)
			return;
		// Takes the output stream we created from the saveActiveIncidents method, and
		// will write the whole data (ArrayList) of an Incident object
		outputStream.writeObject(node);
		// Doing recursion by calling the method, but it will go to the right node
		saveIncidentData(node.right, outputStream);
		// Doing recursion by calling the method, but it will go to the left node
		saveIncidentData(node.left, outputStream);
	}

	// This method will retrieve the written objects in the file
	public void loadingSavedIncidents(String filename) {
		// Creating a ObjectInputStream object for reading from the file
		// And in the parameter, it has FileInputStream
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
			// This will keep on running until we reach the end of the file
			while (true) {
				// Try-catch block sine the method readObject() could throw a IOException or
				// ClassNotFoundException exception
				try {
					// The readObject() would first check the class name and its version ID if they
					// match the class the implements Serializable (which is the BSTree
					// class)
					TreeNode incidentNode = (TreeNode) inputStream.readObject();
					// When reading it, it will be inserted to the BST, and then will move to the
					// next object and reads from it and insert it
					for (Incident incident : incidentNode.data)
						insert(incident);
					// This process will happen UNTIL readObject() reaches the end of the file
					// in that case, it will throw either IOException or ClassNotFoundException
					// exception
				} catch (IOException | ClassNotFoundException ex) {
					// When the exception gets catch, meaning we reached the end of the file
					// We will break from the while loop, therefore we successfully retrieved the
					// objects of Incidents to the BST
					// Retrieving the data is called loading
					break;
				}
			}
			// Here we catch the exception when creating the ObjectInputStream or
			// FileInputStream objects
		} catch (IOException e) {
//			System.out.println(
//					"\n\tEXCEPTION 2: An IOException occured when creating ObjectOutputStream or FileOutputStream object!!\n");
		}
	}

	// Method to save the resolved incidents to a file
	public static void saveResolvedIncidents(String filename) {
		// Creating the ObjectOutputStream that takes FileOutputStream as an object with
		// the file name
		// This will save the data as a stream, or bytes
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
			// We created the object inside the brackets because it would close it
			// automatically
			// This is helpful for preventing data leakage
			// The next line it writes the data of resolved incidents in the file
			outputStream.writeObject(Incident.getResolvedIncidents());
			// Printing out a message to see if it actually works
//			System.out.println("\n\tMESSAGE: Resolved incidents saved successfully!!\n");
			// Catching IOException if it was thrown from creating ObjectOutputStream or
			// FileOutputStream object
		} catch (IOException e) {
			System.err.println("\n\tEXCEPTION: Error saving resolved incidents: " + e.getMessage() + "\n");
		}
	}

	// @SuppressWarnings("unchecked") tells the compiler to ignore warnings about
	// the unsafe type cast we are doing. This is because when we read an object
	// from the file, the compiler can't be sure it's the right type (like
	// List<Incident>).
	// By using this annotation, we are saying we know what we're doing and we will
	// make sure that the cast is safe. But if the object is not the type
	// we expect, it can cause an exception.
	@SuppressWarnings("unchecked")
	public void loadResolvedIncidents(String filename) {
		// Creating object of ObjectInputStream for reading from the file
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
			// Creating an object of list to read object from the file of resolved incidents
			// list
			List<Incident> resolvedIncidents = (List<Incident>) inputStream.readObject();
			// Taking what it has been read and set it to the list of resolved incidents
			Incident.setResolvedIncidents(resolvedIncidents);
			// A message to check if it worked or not
			System.out.println("\n\tMESSAGE: Resolved incidents loaded successfully!!\n");
		} catch (IOException | ClassNotFoundException e) {
//			System.err.println("\n\tEXCEPTION: Error loading resolved incidents: " + e.getMessage());
		}
	}

	public void saveAmbulances(MedicalTeam medicalTeam, String filename) {
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {

			outputStream.writeObject(medicalTeam.getAmbulances());

		} catch (IOException e) {
			System.out.println("\n\tEXCEPTION: Error loading resolved incidents: " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public void loadSavedAmbulances(MedicalTeam medicalTeam, String filename) {
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {

			try {
				List<Ambulance> ambulancesList = (List<Ambulance>) inputStream.readObject();
				medicalTeam.setAmbulances(ambulancesList);
			} catch (IOException | ClassNotFoundException ex) {
				return;
			}

		} catch (IOException e) {
//			System.out.println("\n\tEXCEPTION: Error loading ambulances: " + e.getMessage());
		}
	}

	@Override
	public Iterator<Incident> iterator() {
		ArrayList<Incident> incidents = new ArrayList<>();
		inorderTraversal(root, incidents);
		return incidents.iterator();
	}

	// I NEED TO CHECK ON THIS OE AGAIN
	private void inorderTraversal(TreeNode current, ArrayList<Incident> incidents) {
		if (current != null) {
			// We would start from the right most as it is the most urgent incident
			inorderTraversal(current.right, incidents);
			incidents.addAll(current.data);
			inorderTraversal(current.left, incidents);
		}
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/*
	 * Insert Method
	 */
	@Override
	public boolean insert(Incident node) {
		if (root == null) {
			root = new TreeNode(node);
			size++;
			return true;
		} else {
			return insert(root, node);
		}
	}

	private boolean insert(TreeNode current, Incident node) {
		// Comparing urgency level
		if (node.compareTo(current.data.get(0)) < 0) {
			// Move left if new node has a lower urgency level
			if (current.left == null) {
				current.left = new TreeNode(node);
				size++;
				return true;
			} else {
				return insert(current.left, node);
			}
		} else if (node.compareTo(current.data.get(0)) > 0) {
			// Move right if new node has a higher urgency level
			if (current.right == null) {
				current.right = new TreeNode(node);
				size++;
				return true;
			} else {
				return insert(current.right, node);
			}
		} else {
			// if same
			current.data.add(node);
			return true;
		}
	}

	public void displaySpecifiedIncidents(int urgencyLevel) {
		TreeNode incidentNode = displaySpecifiedIncidents(root, urgencyLevel);

		if (incidentNode != null) {
			System.out.println("\nAll Incidents of Urgency Level: " + urgencyLevel);
			System.out.println("--------------------------------------------------------------");
			for (Incident incident : incidentNode.data) {
				System.out.println("Caller ID: " + incident.getCaller().getCallerID());
				System.out.println("Caller Name: " + incident.getCaller().getName());
				System.out.println("Caller Phone No.: +97105" + incident.getCaller().getPhoneNumber());
				System.out.println("Incident Type: " + incident.getIncidentType());
				System.out.println("Incident Location: " + incident.getLocation());
				System.out.println(" Incident Description: " + incident.getIncidentDescription());
				if (incident.getCoordinates() != null) {
					System.out.println("Incident Coordinates: " + incident.getCoordinates()[0] + ", "
							+ incident.getCoordinates()[1]);

				}
				System.out.println("Assigned Ambulance: " + ((incident.getAssignedAmbulance() != null)
						? incident.getAssignedAmbulance().getAmbulanceName()
						: "Not assigned"));
				System.out.println("Resolved: " + ((incident.isResolved()) ? "Resolved" : "Not resolved"));
				System.out.println("--------------------------------------------------------------");
			}
		} else {
			System.out.println("\n\tINVALID: There is no " + urgencyLevel + " urgency level in this tree!\n");
		}
	}

	private TreeNode displaySpecifiedIncidents(TreeNode current, int urgencyLevel) {

		if (current != null) {
			// If the user typed in the correct urgency level and it is found in the tree
			if (urgencyLevel == current.data.get(0).getUrgencyLevel()) {
				return current;
			}
			// If the value of the urgency level is less than the one we currently have
			if (urgencyLevel < current.data.get(0).getUrgencyLevel()) {
				return displaySpecifiedIncidents(current.left, urgencyLevel);
			}
			// If the value of urgency level is more than the one we currently have
			return displaySpecifiedIncidents(current.right, urgencyLevel);
		}

		return null;
	}

	@Override
	public void update(Incident updatingIncident) {
		update(root, updatingIncident);
	}

	private void update(TreeNode current, Incident updatingIncident) {
		if (current == null) {
			System.out.println("\n\tINVALID: There are no active incidents!\n");
			return;
		}

		// Check if incident exists
		if (updatingIncident != null) {
			// When found, ask user
			Scanner scanner = new Scanner(System.in);
			boolean continueUpdating = true;

			while (continueUpdating) {
				System.out.println("\n\n-------------------------------------------------------------------------");
				System.out.println("Updating");
				System.out.println("-------------------------------------------------------------------------");

				System.out.println("What would you like to update?");
				System.out.println("A. Urgency Level");
				System.out.println("B. Incident Description");
				System.out.println("C. Exit");

				System.out.print("Enter your choice (A, B, or C)\n> ");
				char choice = scanner.next().toUpperCase().charAt(0);

				switch (choice) {
				case 'A':
					System.out.println("\n\n-------------------------------------------------------------------------");
					System.out.println("Updating the Urgency Level");
					System.out.println("-------------------------------------------------------------------------");

					// ADD EXCEPTION
					int newUrgencyLevel;
					do {
						try {
							System.out.print("Enter the New Urgency Level\n> ");
							newUrgencyLevel = scanner.nextInt();
							if (newUrgencyLevel < 1 || newUrgencyLevel > 10) {
								throw new IllegalArgumentException();
							}

						} catch (InputMismatchException | IllegalArgumentException ex) {
							System.out.println("\n\tEXCEPTION: Urgency level out of range!\n");
							scanner.nextLine();
							newUrgencyLevel = -1;
						}

					} while (newUrgencyLevel < 1 || newUrgencyLevel > 10);

					if (newUrgencyLevel != updatingIncident.getUrgencyLevel()) {
						// Retrieve existing details
						Caller caller = updatingIncident.getCaller();
						String incidentType = updatingIncident.getIncidentType();
						ArrayList<String> incidentDescription = updatingIncident.getIncidentDescription();
						String location = updatingIncident.getLocation();
						boolean resolved = updatingIncident.isResolved();
						double[] coordinates = updatingIncident.getCoordinates();
						Ambulance ambulance = updatingIncident.getAssignedAmbulance();

						// Create a new incident with the updated urgency level
						Incident newIncident = new Incident(caller, incidentType, newUrgencyLevel,
								location);

						newIncident.setIncidentDescription(incidentDescription);
						newIncident.setResolved(resolved);
						newIncident.setCoordinates(coordinates);
						newIncident.setAssignedAmbulance(ambulance);

						// Delete the old incident
						delete(updatingIncident);

						// Insert the new incident
						insert(newIncident);

						System.out.println("\n\tMESSAGE: Urgency Level updated successfully!\n");
					} else {
						System.out.println("\n\tINVALID: The new urgency level is the same as the current one!\n");
					}
					continueUpdating = false;

					break;

				case 'B':
					System.out.println("\n\n-------------------------------------------------------------------------");
					System.out.println("Updating the Incident's Description");
					System.out.println("-------------------------------------------------------------------------");

					System.out.print("Add Another Incident Description\n> ");
					scanner.nextLine();
					String newDescription = scanner.nextLine();
					updatingIncident.getIncidentDescription().add(newDescription);
					System.out.println("\n\tMESSAGE: Incident Description updated!\n");
					continueUpdating = false;

					break;

				case 'C':
					continueUpdating = false;
					System.out.println("\n\tMESSAGE: Exiting update!\n");
					break;

				default:
					System.out.println("\n\tINVALID: Please select A, B, or C!\n");
				}
			}
			// Return true when the node is found and updated

		}

	}

	@Override
	public void reverseInorder() {
		reverseInorder(root);
	}

	private void reverseInorder(TreeNode current) {
		if (current != null) {
			reverseInorder(current.right);
			for (Incident incident : current.data) {
				System.out.println("Urgency Level: " + incident.getUrgencyLevel());
				System.out.println("Caller ID: " + incident.getCaller().getCallerID());
				System.out.println("Caller Name: " + incident.getCaller().getName());
				System.out.println("Caller Phone No.: +97105" + incident.getCaller().getPhoneNumber());
				System.out.println("Incident Type: " + incident.getIncidentType());
				System.out.println("Incident Location: " + incident.getLocation());
				System.out.println(" Incident Description: " + incident.getIncidentDescription());
				if (incident.getCoordinates() != null) {
					System.out.println("Incident Coordinates: " + incident.getCoordinates()[0] + ", "
							+ incident.getCoordinates()[1]);
				}
				System.out.println("Assigned Ambulance: " + ((incident.getAssignedAmbulance() != null)
						? incident.getAssignedAmbulance().getAmbulanceName()
						: "Not assigned"));
				System.out.println("Resolved: " + ((incident.isResolved()) ? "Resolved" : "Not resolved"));
				System.out.println("--------------------------------------------------------------");
			}
			reverseInorder(current.left);
		}
	}

	@Override
	public void postorder() {
		postorder(root);
	}

	private void postorder(TreeNode current) {
		if (current != null) {
			postorder(current.left);
			postorder(current.right);
			for (Incident incident : current.data) {
				System.out.print(incident + " | ");
			}

		}
	}

	@Override
	public void preorder() {
		preorder(root);
	}

	private void preorder(TreeNode current) {
		if (current != null) {
			for (Incident incident : current.data) {
				System.out.print(incident + " | ");
			}
			preorder(current.left);
			preorder(current.right);
		}
	}

	@Override
	public boolean delete(Incident incident) {
		return delete(root, incident);
	}

	protected boolean delete(TreeNode current, Incident incident) {
		// the current parent is null since we are starting from the root
		TreeNode parent = null;
		// Initialize the boolean found as false
		boolean found = false;
		// Initialize the index of the found node to be -1
		int indexOfNode = -1;

		// If the current is null, we return found
		if (current == null) {
			return found;
		} else {
			// Finding the node by comparing the urgency level between the nodes
			while (!found && current != null) {
				if (incident.compareTo(current.data.get(0)) < 0) {
					// if the urgency level is less than the one we need
					parent = current;
					current = current.left; // we go to the left node
				} else if (incident.compareTo(current.data.get(0)) > 0) {
					// If the urgency level is more than the one we need
					parent = current;
					current = current.right; // we go to the right node
				} else {
					// If the urgency level was equal to the current node
					for (int i = 0; i < current.data.size() && !found; i++) {
						// Since each node holds an ArrayList of incidents, we will find the specified
						// incident based on the caller ID
						if (incident.getCaller().getCallerID() == current.data.get(i).getCaller().getCallerID()) {
							// If it was found then "found" is set to true
							found = true;
							// Recording the index of the found incident
							indexOfNode = i;
						}
					}
				}
			}

			// If the incident was found
			if (found) {

				if (current.data.size() == 1) { // if the node's ArrayList has only one incident
					if (current.isLeaf()) {// Node is a leaf
						if (parent == null) {
							root = null; // Deleting the root
							// If the node is a left child
						} else if (parent.left == current) {
							// Then it will become null
							parent.left = null;
						} else {
							// Otherwise if it was a right child, it will become null
							parent.right = null;
						}
						// If the node that should be deleted does not have a left child but has a right
					} else if (current.hasRight()) {
						// If the parent is null aka the current is the root
						if (parent == null) {
							// Then the right child of the current will become the root
							root = current.right;
							// If the current is a left child
						} else if (parent.left == current) {
							// Then the left child of the parent will be the right child of the current
							parent.left = current.right;
						} else {
							// Else if the current is the right child of the parent, then the new right
							// child of the parent will become the right of the current
							parent.right = current.right;
						}
						// If the current node has a left child but not a right child
					} else if (current.hasLeft()) {
						// If the current node is a root
						if (parent == null) {
							// Then the left child of it will become the root
							root = current.left;
							// If the current is the left child of its parent
						} else if (parent.left == current) {
							// The left child of the parent will be the left child of the current
							parent.left = current.left;
						} else {
							// Otherwise, the right child of the parent will be the left child of the
							// current
							parent.right = current.left;
						}
						// Else, if the node has 2 children
					} else {
						// the parent of right most will be the current node
						TreeNode parentOfRightMost = current;
						// the rightmost will be the left of the parent of right most
						TreeNode rightMost = current.left;

						// Keep searching on the right most by going to the right of rightmost
						while (rightMost.right != null) {
							parentOfRightMost = rightMost;
							rightMost = rightMost.right;
						}

						// When the rightmost is found... the current node of incidents will be replaced
						// with the rightmost incidents
						current.data = rightMost.data;

						// Since the rightmost took another position, we have to eliminate its old
						// position
						// If the rightmost is the parent's right child
						if (parentOfRightMost.right == rightMost) {
							// Then the parent's right child will point on the left child of rightmost
							parentOfRightMost.right = rightMost.left;
							// Otherwise... the left child of the parent of right most will be the
							// rightmost's left
						} else {
							parentOfRightMost.left = rightMost.left;
						}
					}
					size--; // Decrementing the BST
				} else {
					// if the size of the list of the node is more than 1
					current.data.remove(indexOfNode);
				}
			}
		}
		return found; // Returns found
	}

	@Override
	public Incident search(int callerID) {
		return search(root, callerID);
	}

	private Incident search(TreeNode current, int callerID) {
		if (current == null) {
			return null;
		}

		// Check each incident in the node's data list
		for (Incident incident : current.data) {
			if (incident.getCaller().getCallerID() == callerID) {
				return incident;
			}
		}

		Incident foundIncident = search(current.left, callerID);
		if (foundIncident == null) {
			foundIncident = search(current.right, callerID);
		}
		return foundIncident;
	}

	public void displayTree() {
		displayTree("", this.root, true);
	}

	public void displayTree(String prefix, TreeNode current, boolean isRight) {
		if (current != null && !current.data.isEmpty()) {
			displayTree(prefix + (isRight ? "│   " : "    "), current.right, true);
			System.out.println(prefix + (isRight ? "├── " : " └──") + "Urgency: "
					+ current.data.get(0).getUrgencyLevel() + " (" + current.data.size() + " incidents)");
			displayTree(prefix + (isRight ? "│  " : "   "), current.left, false);
		}
	}

	public void displayIncidentsWithUrgencyLevel(int urgencyLevel) {
		displayIncidentsWithUrgencyLevel(urgencyLevel, root);
	}

	// Fix this
	public void displayIncidentsWithUrgencyLevel(int urgencyLevel, TreeNode current) {
		int currentUrgencyLevel = current.data.get(0).getUrgencyLevel();
		if (urgencyLevel == currentUrgencyLevel) {
			System.out.println(current.data);
		}
		
		

	}
	 // Method to remove nodes with urgency level <= 3
    public void removeIrrelavantData() {
        root = removeIrrelavantData(root);
    }

    // Helper method to recursively remove nodes
    private TreeNode removeIrrelavantData(TreeNode current) {
    	
        if (current == null) {
            return null;
        }
        
        // Remove left and right subtrees if needed
        current.left = removeIrrelavantData(current.left);
        current.right = removeIrrelavantData(current.right);

        // Check if the current node's urgency level is <= 3
        if (current.data.get(0).getUrgencyLevel() <= 3) {
            // Return the appropriate subtree or null if both are removed
            return (current.left != null) ? current.left : current.right;
        }

        return current;
    }

}
