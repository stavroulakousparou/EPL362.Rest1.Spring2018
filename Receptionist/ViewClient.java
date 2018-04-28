package Receptionist;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
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

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewClient {

	private JFrame frame;
	private JTextField name_txt;
	private JTextField surname_txt;
	private JTextField id_txt;
	private JTextField unwillingness_txt;
	private JTextField comments_txt;
	private Connection conn;

	/**
	 * Launch the application.
	 */
	public static void main(String clientID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewClient window = new ViewClient(clientID);
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
	public ViewClient(String cID) {
		initialize(cID);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String cID) {
		frame = new JFrame();
		frame.setBounds(100, 100, 422, 373);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblViewClient = new JLabel("View Client");
		lblViewClient.setFont(new Font("Arial", Font.BOLD, 16));
		JLabel lblNewLabel = new JLabel("Name");
		name_txt = new JTextField();
		name_txt.setColumns(10);

		JLabel lblSurname = new JLabel("Surname");

		surname_txt = new JTextField();
		surname_txt.setColumns(10);

		JLabel lblId = new JLabel("ID");

		id_txt = new JTextField();
		id_txt.setColumns(10);

		JLabel lblUnwillingnes = new JLabel("Unwillingness");

		unwillingness_txt = new JTextField();
		unwillingness_txt.setColumns(10);

		JLabel lblComments = new JLabel("Comments");

		comments_txt = new JTextField();
		comments_txt.setColumns(10);

		ConnectDB connection = new ConnectDB();
		conn = connection.getDBConnection();

		String sql = "Select * FROM `Client` WHERE `ClientID`=" + cID;
		String sql2 = "Select * FROM `Consultation` WHERE `ClientID`=" + cID;
		PreparedStatement statement = null;
		ResultSet rs;

		PreparedStatement statement2 = null;
		ResultSet rs2;

		try {

			// create and execute statement
			statement = conn.prepareStatement(sql);
			rs = statement.executeQuery();

			if (rs.next()) {
				name_txt.setText(rs.getString("FirstName"));
				surname_txt.setText(rs.getString("LastName"));
				id_txt.setText(cID);
				unwillingness_txt.setText(rs.getString("RiskPercentage"));
				
			}

			statement2 = conn.prepareStatement(sql2);
			rs2 = statement2.executeQuery();

			while (rs2.next()) {
				comments_txt.setText(comments_txt.getText() + "," + rs2.getString("Comments"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		JButton btnGoBack = new JButton("Go Back");

		btnGoBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				ReceptionistViewpoint f = new ReceptionistViewpoint();
				f.main(null);
			}
		});

		JButton btnCreateConsultation = new JButton("<html>Create <br>\r\nConsultation</html>");
		btnCreateConsultation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String id = id_txt.getText();
				frame.setVisible(false);
				ConsultationForm f = new ConsultationForm(id);
				f.main(id);
			}
		});

		JButton btnLogout = new JButton("LogOut");
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				loginPage f = new loginPage();
				f.main(null);
			}
		});

		JButton btnsearchForStrategies = new JButton("<html>Search For <br>\r\nStrategies</html>");
		btnsearchForStrategies.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id = id_txt.getText();
				if (id.equals("")) {
					JOptionPane.showMessageDialog(frame, "Fill the client id field");
					return;
				}	
			}
		});

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(31)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(lblSurname)
								.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblComments)
								.addComponent(lblUnwillingnes, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(id_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(unwillingness_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(surname_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(name_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnCreateConsultation, 0, 0, Short.MAX_VALUE)
										.addComponent(btnsearchForStrategies, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
									.addGap(30))
								.addComponent(comments_txt, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(73)
							.addComponent(btnGoBack)
							.addGap(18)
							.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(153)
							.addComponent(lblViewClient)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(43)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(name_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSurname)
								.addComponent(surname_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addComponent(lblId))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addComponent(id_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUnwillingnes)
								.addComponent(unwillingness_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(47)
							.addComponent(btnCreateConsultation, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnsearchForStrategies, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblComments)
						.addComponent(comments_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGoBack)
						.addComponent(btnLogout))
					.addGap(36))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(lblViewClient)
					.addContainerGap(316, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}


}
