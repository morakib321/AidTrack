<h1>AidTrack – Emergency Medical Response System</h1>

<p><strong>AidTrack</strong> is a Java-based system designed to manage and optimize emergency medical response operations. 
It handles incoming emergency calls, tracks ambulance and medical team availability, and uses a <strong>Binary Search Tree (BST)</strong> 
to organize and retrieve incident data efficiently. The project was developed for <strong>CSC301: Data Structures and Algorithms (Fall 2024)</strong>.</p>

<hr>

<h2> Objective</h2>
<p>
The goal of this project is to simulate a real-time emergency management system where data about emergency calls, 
locations, and available resources are dynamically updated and processed. The system ensures faster response times 
by ranking ambulances and medical teams based on their proximity and availability.
</p>

<hr>

<h2>Learning Outcomes</h2>
<ul>
  <li>Apply OOP principles such as inheritance, polymorphism, encapsulation, and abstraction in a structured Java application.</li>
  <li>Use a Binary Search Tree to efficiently insert, search, update, and delete emergency data.</li>
  <li>Handle real-time operations like logging calls, tracking response times, and managing resources.</li>
  <li>Develop analytical skills by evaluating how BSTs improve performance in handling large data sets.</li>
</ul>

<hr>

<h2>Project Structure</h2>

<pre>
AidTrack/
│
├── AbstractTree.java
├── AidTrackSystem.java
├── Ambulance.java
├── AmbulanceNotAvailableException.java
├── BSTree.java
├── Caller.java
├── Incident.java
├── Locator.java
├── MedicalTeam.java
├── NegativeCallerIdException.java
├── NegativeUrgencyException.java
├── Tree.java
└── TreeNode.java
</pre>

<p>Each file contributes to a specific part of the system — from handling medical team assignments to managing BST operations and incident tracking.</p>

<hr>

<h2>Setup Instructions</h2>

<ol>
  <li>Clone this repository:
    <pre><code>git clone https://github.com/morakib321/AidTrack.git</code></pre>
  </li>
  <li>Open the project in your Java IDE (IntelliJ IDEA, Eclipse, or VS Code).</li>
  <li>Before running the system:
    <ul>
      <li>Open the <strong>JSON Library</strong> folder inside the project.</li>
      <li>Follow the steps in the <strong>.txt</strong> instruction file to import the required JSON library into your project.</li>
      <li>This step is necessary to avoid errors in the <strong>Locator</strong> class.</li>
    </ul>
  </li>
  <li>Run the <code>AidTrackSystem.java</code> file to start the program.</li>
</ol>

<hr>

<h2>💡 System Features</h2>

<h3>1. Real-Time Data Management</h3>
<ul>
  <li>Logs emergency calls with caller details, incident types, and urgency levels.</li>
  <li>Inserts each call as a node in a Binary Search Tree for fast retrieval.</li>
  <li>Supports manual input, hardcoded initialization, or reading data from files.</li>
</ul>

<h3>2. Efficient Dispatching System</h3>
<ul>
  <li>Ranks ambulances and medical teams based on their proximity to the incident.</li>
  <li>Uses BST sorting to quickly dispatch the nearest available unit.</li>
  <li>Reverse in-order traversal can be used to extract performance reports.</li>
</ul>

<h3>3. Data Updates and Deletions</h3>
<ul>
  <li>Updates incident information dynamically (e.g., urgency level changes).</li>
  <li>Removes resolved or outdated calls from the BST to maintain accuracy.</li>
</ul>

<h3>4. Visualization and Analysis</h3>
<ul>
  <li>Tracks and displays response times for incidents.</li>
  <li>Identifies performance trends across different locations.</li>
</ul>

<hr>

<h2>Key Classes Explained</h2>
<ul>
  <li><strong>AidTrackSystem.java</strong> – Main class controlling user interaction and system flow.</li>
  <li><strong>Incident.java</strong> – Represents each emergency incident with location, urgency, and type.</li>
  <li><strong>Locator.java</strong> – Uses the OpenCage API to convert addresses to latitude and longitude.</li>
  <li><strong>Ambulance.java</strong> &amp; <strong>MedicalTeam.java</strong> – Manage resource details and availability.</li>
  <li><strong>BSTree.java</strong>, <strong>TreeNode.java</strong> – Core data structures for efficient storage and retrieval.</li>
  <li><strong>NegativeUrgencyException.java</strong> &amp; <strong>AmbulanceNotAvailableException.java</strong> – Custom exceptions for handling specific errors.</li>
</ul>

<hr>

<h2> Future Improvements</h2>
<ul>
  <li>Add a GUI for better visualization of emergency data and response routes.</li>
  <li>Implement real-time API integration for live ambulance tracking.</li>
  <li>Store incident history in a database for long-term analysis.</li>
  <li>Introduce AI-based prediction for resource optimization.</li>
</ul>

<hr>

