package LegalStaff;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IsClientML {

	private JFrame frame;
	private JTextField client_txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IsClientML window = new IsClientML();
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
	public IsClientML() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblCheckIfA = new JLabel("Check If A Client Had A Money Laudring Case");
		lblCheckIfA.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel lblClientId = new JLabel("Client ID:");

		client_txt = new JTextField();
		client_txt.setColumns(10);

		JButton btnNewButton = new JButton("GO");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String ClientID = client_txt.getText();
				if (ClientID.equals("")) {
					JOptionPane.showMessageDialog(frame, "Enter a strategy:");
					return;
				}

				Connection conn = null;
				ConnectDB connection = new ConnectDB();
				conn = connection.getDBConnection();

				// If connection Failed
				if (conn == null) {
					System.out.println("Connection Failed");
				}

				Statement statement = null;

				try {
					statement = conn.createStatement();
					ResultSet rs = statement.executeQuery(
							"SELECT COUNT(DISTINCT Flagged_ml) FROM `Case` WHERE ( Flagged_ml=1 AND ClientID='" + ClientID + "')");

					if (rs.getString("COUNT(DISTINCT Flagged_ml)").equals("1")) {
						JOptionPane.showMessageDialog(frame, "This client involved in money laundring!");
					} else {
						JOptionPane.showMessageDialog(frame, "This client didn't involve in money laundring!");
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				
			}
		});

		JButton btnNewButton_1 = new JButton("GoBack");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				LegalStaffViewpoint f = new LegalStaffViewpoint();
				f.main(null);
			}
		});

		JButton btnNewButton_2 = new JButton("LogOut");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				loginPage f = new loginPage();
				f.main(null);
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(19).addComponent(btnNewButton,
										GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING,
										groupLayout.createSequentialGroup().addContainerGap(84, Short.MAX_VALUE)
												.addComponent(lblClientId).addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(61).addComponent(client_txt,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup().addGap(28)
										.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 105,
												GroupLayout.PREFERRED_SIZE)
										.addGap(34).addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 107,
												GroupLayout.PREFERRED_SIZE)))
						.addGap(10)).addGroup(groupLayout.createSequentialGroup().addGap(41).addComponent(lblCheckIfA)))
				.addContainerGap(21, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap().addComponent(lblCheckIfA).addGap(40)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblClientId).addComponent(
						client_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(84).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton)
						.addComponent(btnNewButton_1).addComponent(btnNewButton_2))
				.addGap(64)));
		frame.getContentPane().setLayout(groupLayout);
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost/LawOSREST/").build();
	}

}
