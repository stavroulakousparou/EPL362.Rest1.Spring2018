package lawos_Rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Legal Staff Service
 *
 */
@Path("/lawos/ls")
public class LegalStaff {

	/**
	 * Edit a specific Consultation given and update all the appropriate fields
	 * that are updatable.
	 * 
	 * @param ID
	 * @param recom
	 * @param legalop
	 * @return
	 */
	@Path("/edit/app")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String editConsultation(@FormParam("ConsultationID") String ID, @FormParam("Recommendation") String recom,
			@FormParam("LegalOpinion") String legalop) {

		Connection conn = null;
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			conn = DriverManager.getConnection(
					"jdbc:mysql://phpmyadmin.in.cs.ucy.ac.cy/statare?" + "user=statare&password=pv7VEVZcmfRLZArP");

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int response = 0;
		java.sql.Statement stmt;
		try {
			stmt = conn.createStatement();
			response = stmt.executeUpdate("UPDATE `Consultation` SET Completed=1, Recommendation='" + recom
					+ "', LegalOpinion='" + legalop + "' WHERE ConsultationID=" + ID);

		} catch (SQLException e) {
			System.err.println("[!]Problem with requested statement");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (response == 1)
			return "1";
		else
			return "0";
	}// end of edit an Consultation

	/**
	 * View current Consultation given by ConsultationID.
	 * 
	 * @param ID
	 * @return
	 */
	@Path("/view/app")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String viewConsultation(@FormParam("ConsultationID") String ID) {

		Connection conn = null;
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			conn = DriverManager.getConnection(
					"jdbc:mysql://phpmyadmin.in.cs.ucy.ac.cy/statare?" + "user=statare&password=pv7VEVZcmfRLZArP");

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response = null;
		java.sql.Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `Consultation` WHERE ConsultationID=" + ID);
			response = GeneralServices.parseJSON(rs);
			rs.close();
		} catch (SQLException e) {
			System.err.println("[!]Problem with requested statement");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}// end of view an Consultation

	/**
	 * Edit a specific Case record of a client by given CaseID given and all the
	 * appropriate fields that are updatable from the user in order to edit them
	 * correctly.
	 * 
	 * @param ID
	 * @param strategy
	 * @param details
	 * @param flagged_ml
	 * @return
	 */
	@Path("/edit/case")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String editCase(@FormParam("CaseID") String ID, @FormParam("Strategy") String strategy,
			@FormParam("Details") String details, @FormParam("Flagged_ml") String flagged_ml) {

		Connection conn = null;
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			conn = DriverManager.getConnection(
					"jdbc:mysql://phpmyadmin.in.cs.ucy.ac.cy/statare?" + "user=statare&password=pv7VEVZcmfRLZArP");

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int response = 0;
		java.sql.Statement stmt;
		try {
			stmt = conn.createStatement();
			response = stmt.executeUpdate("UPDATE `Case` SET Strategy='" + strategy + "', Details='" + details
					+ "', Flagged_ml=" + flagged_ml + " WHERE CaseID=" + ID);

		} catch (SQLException e) {
			System.err.println("[!]Problem with requested statement");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (response == 1)
			return "1";
		else
			return "0";
	}// end of edit a Case

	/**
	 * View a specific Case record of a client by given CaseID and all the
	 * appropriate fields that are stored.
	 * 
	 * @param ID
	 * @return
	 */
	@Path("/view/Case")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String viewCase(@FormParam("CaseID") String ID) {

		Connection conn = null;
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			conn = DriverManager.getConnection(
					"jdbc:mysql://phpmyadmin.in.cs.ucy.ac.cy/statare?" + "user=statare&password=pv7VEVZcmfRLZArP");

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response = null;
		java.sql.Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `Case` WHERE CaseID=" + ID);
			response = GeneralServices.parseJSON(rs);
			rs.close();
		} catch (SQLException e) {
			System.err.println("[!]Problem with requested statement");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}// end of edit a Case
	
	/**
	 * View a specific client's unwillingness given by ClientID.
	 * 
	 * @param ID
	 * @return
	 */
	@Path("/view/client/unw")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String viewClientUNW(@FormParam("ClientID") String ID) {

		Connection conn = null;
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			conn = DriverManager.getConnection(
					"jdbc:mysql://phpmyadmin.in.cs.ucy.ac.cy/statare?" + "user=statare&password=pv7VEVZcmfRLZArP");

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response = null;
		java.sql.Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Unwillingness FROM `Client` WHERE ID='" + ID + "'");
			response = GeneralServices.parseJSON(rs);
			rs.close();
		} catch (SQLException e) {
			System.err.println("[!]Problem with requested statement");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}// end of edit a Case

	/**
	 * Returns all Case records of a specific client given by ClientID.
	 * 
	 * @param ID
	 * @return
	 */
	@Path("/view/allCases")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String viewAllClientCases(@FormParam("ClientID") String ID) {

		Connection conn = null;
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			conn = DriverManager.getConnection(
					"jdbc:mysql://phpmyadmin.in.cs.ucy.ac.cy/statare?" + "user=statare&password=pv7VEVZcmfRLZArP");

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response = null;
		java.sql.Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `Case` WHERE Client='" + ID + "'");
			response = GeneralServices.parseJSON(rs);
			rs.close();
		} catch (SQLException e) {
			System.err.println("[!]Problem with requested statement");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}// end of view all Consultations of a client

	/**
	 * Adds a new Case record into the system by giving the appropriate fields
	 * that need to be entered.
	 * 
	 * @param Strategy
	 * @param details
	 * @param flagged_ml
	 * @param clientID
	 * @param lawyerID
	 * @return
	 */
	@Path("/add/Case")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String addCase(@FormParam("Strategy") String strategy, @FormParam("Details") String details,
			@FormParam("Flagged_ml") String flagged_ml, @FormParam("ClientID") String clientID,
			@FormParam("LawyerID") String lawyerID) {

		Connection conn = null;
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			conn = DriverManager.getConnection(
					"jdbc:mysql://phpmyadmin.in.cs.ucy.ac.cy/statare?" + "user=statare&password=pv7VEVZcmfRLZArP");

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int response = 0;
		java.sql.Statement stmt;
		try {
			stmt = conn.createStatement();
			response = stmt.executeUpdate(
					"INSERT INTO `Case`(`Strategy`, `Details`, `Flagged_ml`, `ClientID`, `LawyerID`) VALUES ('" + strategy
							+ "', '" + details + "', " + flagged_ml + ", '" + clientID + "', " + lawyerID + ");");
			// rs.close();
		} catch (SQLException e) {
			System.err.println("[!]Problem with requested statement");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (response == 1)
			return "1";
		else
			return "0";
	}

	/**
	 * Returns all the side effects as recommendations from the system for a
	 * specific strategy given.
	 * 
	 * @param strategy
	 * @return
	 */
	@Path("/view/strategy/sideeffects")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String viewSideEffectsByStrategy(@FormParam("Strategy") String strategy) {

		Connection conn = null;
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			conn = DriverManager.getConnection(
					"jdbc:mysql://phpmyadmin.in.cs.ucy.ac.cy/statare?" + "user=statare&password=pv7VEVZcmfRLZArP");

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response = null;
		java.sql.Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT DISTINCT SideEffect FROM `Strategy` WHERE StrategyID='" + strategy + "'");
			response = GeneralServices.parseJSON(rs);
			rs.close();
		} catch (SQLException e) {
			System.err.println("[!]Problem with requested statement");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}// end of viewSideEffectsByStrategy

	/**
	 * Returns 1 or 0 if the client given by ClientID is involved in money
	 * laundering or not.
	 * 
	 * @param ID
	 * @return
	 */
	@Path("/view/client/isml")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String isml(@FormParam("ClientID") String ID) {

		Connection conn = null;
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			conn = DriverManager.getConnection(
					"jdbc:mysql://phpmyadmin.in.cs.ucy.ac.cy/statare?" + "user=statare&password=pv7VEVZcmfRLZArP");

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response = null;
		java.sql.Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT COUNT(DISTINCT Flagged_ml) FROM `Case` WHERE ( Flagged_ml=1 AND ClientID='" + ID + "')");
			response = GeneralServices.parseJSON(rs);
			rs.close();
		} catch (SQLException e) {
			System.err.println("[!]Problem with requested statement");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
}// end of class
