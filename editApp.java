package LegalStaff;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import GUI.loginPage;
import Receptionist.ReceptionistViewpoint;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class editApp {

	private JFrame frame;
	private JTextField date_txt;
	private JTextField case_txt;
	private JTextField client_txt;
	private JTextField lawyer_txt;
	private JTextField branch_txt;
	private JTextField recomandation_txt;
	private JTextField legalopinion_txt;
	private JTextField appID_txt;
	private JTextField time_txt;
	private static Connection conn = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					editApp window = new editApp();
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
	public editApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 406);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblEditAppointment = new JLabel("Edit Consultation");
		lblEditAppointment.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel label = new JLabel("Date");
		
		date_txt = new JTextField();
		date_txt.setColumns(10);
		
		JLabel label_1 = new JLabel("Time");
		
		JLabel label_2 = new JLabel("Case");
		
		case_txt = new JTextField();
		case_txt.setColumns(10);
		
		client_txt = new JTextField();
		client_txt.setColumns(10);
		
		JLabel label_3 = new JLabel("Client");
		
		JLabel label_4 = new JLabel("Lawyer");
		
		lawyer_txt = new JTextField();
		lawyer_txt.setColumns(10);
		
		JLabel label_5 = new JLabel("Branch");
		
		branch_txt = new JTextField();
		branch_txt.setColumns(10);
		
		JLabel label_6 = new JLabel("Recommendation");
		
		recomandation_txt = new JTextField();
		recomandation_txt.setColumns(10);
		
		JLabel label_7 = new JLabel("Legal Opinion");
		
		legalopinion_txt = new JTextField();
		legalopinion_txt.setColumns(10);
		
		JButton button = new JButton("Submit");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String recomandation = recomandation_txt.getText();
				String legalopinion = legalopinion_txt.getText();
			
				
				ConnectDB connection = new ConnectDB();
				conn = connection.getDBConnection();

				// If connection Failed
				if (conn == null) {
					System.out.println("Connection Failed");
				}
				
				PreparedStatement stmt= null;
				
			try {
				int	response = stmt.executeUpdate("UPDATE `Consultation` SET Completed=1, Recommendation='" + recomandation
							+ "', LegalOpinion='" + legalopinion + "' WHERE ConsultationID=" + appID_txt.getText());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			}
		});
		
		JButton button_1 = new JButton("Go Back");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				LegalStaffViewpoint f = new LegalStaffViewpoint();
				f.main(null);
			}
		});
		
		JButton button_2 = new JButton("LogOut");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				loginPage f = new loginPage();
				f.main(null);
			}
		});
		
		JLabel lblEnterAppointmentid = new JLabel("<html>Enter consultationID<br>\r\nto fill form:\r\n</htm>");
		
		appID_txt = new JTextField();
		appID_txt.setColumns(10);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String appID = appID_txt.getText();
				
				
				//check for empty text boxes
				if(appID.equals("")){
					JOptionPane.showMessageDialog(frame, "Enter an consultation id.");
					return;
				}
				Connection con;
				
				//request
				ConnectDB connection = new ConnectDB();
				conn = connection.getDBConnection();

				// If connection Failed
				if (conn == null) {
					System.out.println("Connection Failed");
				}
				
		
			}
		});
		
		time_txt = new JTextField();
		time_txt.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(154, Short.MAX_VALUE)
					.addComponent(lblEditAppointment)
					.addGap(148))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(82)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(label, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
										.addGap(77))
									.addComponent(label_2, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
									.addComponent(label_3, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
								.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(label_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(label_5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(label_7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(label_6, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(recomandation_txt, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED))
										.addComponent(branch_txt)
										.addComponent(legalopinion_txt)
										.addComponent(lawyer_txt, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(client_txt, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
										.addComponent(case_txt)
										.addComponent(time_txt)
										.addComponent(date_txt))
									.addGap(49)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(appID_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblEnterAppointmentid, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))))))
					.addGap(12))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEditAppointment)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(date_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(11)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(time_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(21)
									.addComponent(label_2))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addComponent(case_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(14)
									.addComponent(label_3))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(11)
									.addComponent(client_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(14)
									.addComponent(label_4))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(11)
									.addComponent(lawyer_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(14)
									.addComponent(label_5))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(11)
									.addComponent(branch_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(9)
									.addComponent(label_6))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(6)
									.addComponent(recomandation_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(14)
									.addComponent(label_7))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(11)
									.addComponent(legalopinion_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(69)
							.addComponent(lblEnterAppointmentid)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(appID_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnRefresh)))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(button_1)
							.addComponent(button))
						.addComponent(button_2))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost/LawOSREST/").build();
	}
}
