package LegalStaff;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import GUI.ConnectDB;
import GUI.loginPage;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddCase {

	private JFrame frame;
	private JTextField details_txt;
	private JTextField client_txt;
	private JTextField lawyer_txt;
	
	private static Connection conn = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCase window = new AddCase();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddCase() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 370);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblCaseForm = new JLabel("Case Form");
		lblCaseForm.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel lblStrategy = new JLabel("Strategy");
		
		JComboBox cb_strategy = new JComboBox();
		cb_strategy.setModel(new DefaultComboBoxModel(new String[] {"Strategy1", "Strategy2", "Strategy3", "Strategy4", "Strategy5"}));
		
		JLabel lblDetails = new JLabel("Details");
		
		details_txt = new JTextField();
		details_txt.setColumns(10);
		
		JLabel lblIsMoneyLaudring = new JLabel("Is Money Laundring");
		
		JComboBox cb_ml = new JComboBox();
		cb_ml.setModel(new DefaultComboBoxModel(new String[] {"0", "1"}));
		
		JLabel lblClient = new JLabel("Client");
		
		client_txt = new JTextField();
		client_txt.setColumns(10);
		
		JLabel lblLawyer = new JLabel("Lawyer");
		
		lawyer_txt = new JTextField();
		lawyer_txt.setColumns(10);
		
		JButton btnLogout = new JButton("LogOut");
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				loginPage f = new loginPage();
				f.main(null);
			}
		});
		
		JButton btnGoback = new JButton("GoBack");
		btnGoback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				LegalStaffViewpoint f = new LegalStaffViewpoint();
				f.main(null);
			}
		});
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String strategy = cb_strategy.getSelectedItem().toString();
				String details = details_txt.getText();
				String ml = cb_ml.getSelectedItem().toString();
				String clientID = client_txt.getText();
				String lawyer = lawyer_txt.getText();
				
				
				//check for empty text boxes
				if(strategy.equals("") || details.equals("") || ml.equals("") || clientID.equals("") || lawyer.equals("")){
					JOptionPane.showMessageDialog(frame, "Fill all fields");
					return;
				}

				
				ConnectDB connection = new ConnectDB();
				conn = connection.getDBConnection();

				// If connection Failed
				if (conn == null) {
					System.out.println("Connection Failed");
				}
				
				String sql = "INSERT INTO `Case`(`Strategy`, `Details`, `Flagged_ml`, `ClientID`, `LawyerID`) VALUES ('" + strategy
						+ "', '" + details + "', " + ml + ", '" + clientID + "', " + lawyer + ")";
		
				PreparedStatement statement = null;
				
				
				try {
					 statement = conn.prepareStatement(sql);	 
                     statement.executeUpdate();

                     JOptionPane.showMessageDialog(frame, "Saved!");
                     
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
				
				
				/*
				
				//request 1
				ClientConfig config = new ClientConfig();
				Client client = ClientBuilder.newClient(config);
				WebTarget target = client.target(getBaseURI());
				Form form = new Form();
				form.param("Strategy", strategy);
				form.param("Details", details);
				form.param("Flagged_ml", ml);
				form.param("ClientID", clientID);
				form.param("LawyerID", lawyer);
				
				String res2 = target.path("rest").path("lawos").path("ls").path("add").path("Case").request()
						.accept(MediaType.APPLICATION_JSON)
						.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);
				
				if (res2.equals("1")){
					JOptionPane.showMessageDialog(frame, "Case Inserted Successfully");
					//request 2
					config = new ClientConfig();
					client = ClientBuilder.newClient(config);
					target = client.target(getBaseURI());
					form = new Form();
					form.param("ClientID", clientID);
					
					
					res2 = target.path("rest").path("lawos").path("ls").path("view").path("client").path("isml").request()
							.accept(MediaType.APPLICATION_JSON)
							.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);
					
					JSONObject json = null;
					try {
						json = new JSONObject(res2);
						JSONArray arr = json.getJSONArray("results_array");
						if(arr.getJSONObject(0).getString("COUNT(DISTINCT Flagged_ml)").equals("1")){
							JOptionPane.showMessageDialog(frame, "This client involved in money laudring!","Dialog",JOptionPane.WARNING_MESSAGE);
						}
						
					} catch (JSONException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					//request 3
					config = new ClientConfig();
					client = ClientBuilder.newClient(config);
					target = client.target(getBaseURI());
					form = new Form();
					form.param("ClientID", clientID);
					
					
					res2 = target.path("rest").path("lawos").path("ls").path("view").path("client").path("unw").request()
							.accept(MediaType.APPLICATION_JSON)
							.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);
					
					json = null;
					try {
						json = new JSONObject(res2);
						JSONArray arr = json.getJSONArray("results_array");
						if(!arr.getJSONObject(0).getString("Unwillingness").equals("null") && !arr.getJSONObject(0).getString("Unwillingness").equals("")){
							JOptionPane.showMessageDialog(frame, "Unwillingness:\n" + arr.getJSONObject(0).getString("Unwillingness"),"Dialog",JOptionPane.WARNING_MESSAGE);
						}
						
					} catch (JSONException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
				}
				else{
					JOptionPane.showMessageDialog(frame, "Wrong Insert");
				}
				
				*/
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStrategy)
						.addComponent(lblIsMoneyLaudring)
						.addComponent(lblDetails)
						.addComponent(lblClient)
						.addComponent(lblLawyer))
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lawyer_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(client_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cb_ml, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cb_strategy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCaseForm)
						.addComponent(details_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(212, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(53, Short.MAX_VALUE)
					.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addGap(45)
					.addComponent(btnGoback, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addGap(43)
					.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addGap(35))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(lblCaseForm)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStrategy)
						.addComponent(cb_strategy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDetails)
							.addGap(21))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(details_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)))
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblIsMoneyLaudring)
						.addComponent(cb_ml, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblClient)
						.addComponent(client_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLawyer)
						.addComponent(lawyer_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogout)
						.addComponent(btnGoback)
						.addComponent(btnSubmit))
					.addContainerGap(44, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost/LawOSREST/").build();
	}

}
