package LegalStaff;

/**
 * This class is responsible to let the user view all the cases.
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;

import GUI.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ViewAllCases {

	private JFrame frame;
	private JTextField client_txt;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAllCases window = new ViewAllCases();
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
	public ViewAllCases() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 716, 398);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblEnterClientId = new JLabel("Enter Client Id:");

		client_txt = new JTextField();
		client_txt.setColumns(10);

		JButton button = new JButton("Search");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				String clientID = client_txt.getText();
				if (clientID.equals("")) {
					JOptionPane.showMessageDialog(frame, "Enter a client id:");
					return;
				}

				// Establishing connection
				ConnectDB connection = new ConnectDB();
				Connection conn = connection.getDBConnection();

				// If connection Failed
				if (conn == null) {
					System.out.println("Connection Failed");
				}

				Statement stmt;
				Account account = Account.getUniqueInstance();

				try {
					stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM `Case` WHERE ClientID='" + clientID + "'");

					if (rs.next()) {
						do {
							model.addRow(new Object[] { rs.getString("CaseID"), rs.getString("Strategy"),
									rs.getString("Details"), rs.getString("Flagged_ml"), rs.getString("LawyerID") });
						} while (rs.next());
					} else
						JOptionPane.showMessageDialog(frame, "No results.");

				} catch (SQLException e1) {
					System.err.println("[!]Problem with requested statement");
					e1.printStackTrace();
				}

			}
		});

		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				LegalStaffViewpoint f = new LegalStaffViewpoint();
				f.main(null);
			}
		});

		JButton btnLogout = new JButton("Logout");
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				loginPage f = new loginPage();
				f.main(null);
			}
		});

		JLabel lblViewAllCases = new JLabel("View All cases Of A Client");
		lblViewAllCases.setFont(new Font("Arial", Font.BOLD, 16));

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(234)
							.addComponent(lblViewAllCases))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(117)
							.addComponent(lblEnterClientId)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(client_txt, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(88)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(69, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
					.addGap(42))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblViewAllCases)
					.addGap(17)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
					.addComponent(btnBack)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(client_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEnterClientId)
						.addComponent(btnLogout))
					.addGap(55))
		);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null }, },
				new String[] { "Case ID", "Strategy", "Details", "Is Money Laudring?", "Client ID", "Lawyer" }));
		frame.getContentPane().setLayout(groupLayout);
	}

}