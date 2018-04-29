package LRS;

/**
 * Edit Client
 * Legal records staff can edit the clients info
 * 
 * */
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

import GUI.Account;
import GUI.loginPage;
import LegalStaff.ConnectDB;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EditClient {

	private JFrame frame;
	private JTextField name_txt;
	private JTextField surname_txt;
	private JTextField id_txt;
	private JTextField risk_txt;
	private JTextField unwilling_txt;
	private JButton btnSubmit;
	private boolean exists = false;
	private boolean deleted = false;

	/**
	 * Launch the application.
	 */
	public static void main(String args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditClient window = new EditClient(args);
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
	public EditClient(String args) {
		initialize(args);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String args) {
		frame = new JFrame();
		frame.setBounds(100, 100, 580, 429);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblClientForm = new JLabel("Client Form");
		lblClientForm.setFont(new Font("Arial", Font.BOLD, 16));

		name_txt = new JTextField();
		name_txt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				btnSubmit.setVisible(true);
			}
		});
		name_txt.setColumns(10);

		JLabel label = new JLabel("Name");

		JLabel label_1 = new JLabel("Surname");

		surname_txt = new JTextField();
		surname_txt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				btnSubmit.setVisible(true);
			}
		});
		surname_txt.setColumns(10);

		JLabel label_2 = new JLabel("ID");

		id_txt = new JTextField();

		id_txt.setColumns(10);

		JLabel lblRisk = new JLabel("Risk Percentage");

		risk_txt = new JTextField();
		risk_txt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				btnSubmit.setVisible(true);
			}
		});
		risk_txt.setColumns(10);

		JLabel lblUnwilling = new JLabel("Unwilling Strategies");

		unwilling_txt = new JTextField();
		unwilling_txt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				btnSubmit.setVisible(true);
			}
		});
		unwilling_txt.setColumns(10);

		btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name = name_txt.getText();
				String surname = surname_txt.getText();
				String id = id_txt.getText();
				String unwillingness = risk_txt.getText();
				String comments = unwilling_txt.getText();

				// check for empty text boxes
				if (name.equals("") || surname.equals("") || id.equals("")) {
					JOptionPane.showMessageDialog(frame, "Fill all fields");
					return;
				}

				// Establishing connection
				ConnectDB connection = new ConnectDB();
				Connection conn = connection.getDBConnection();

				// If connection Failed
				if (conn == null) {
					System.out.println("Connection Failed");
				}

				PreparedStatement stmt;
				Account account = Account.getUniqueInstance();

				try {

					if (exists) {
						stmt = conn.prepareStatement("UPDATE `Client` SET `FirstName`='" + name_txt.getText()
								+ "', `LastName`='" + surname_txt.getText() + "',  `RiskPercentage`='"
								+ risk_txt.getText() + "', `Unwilling`='" + unwilling_txt.getText()
								+ "' WHERE ClientID = '" + args + "'");
						stmt.executeUpdate();
					}

					else {
						String sql = "INSERT INTO `Client`(`ClientID`, `FirstName`, `LastName`, `RiskPercentage`, `Unwilling`) VALUES ('"
								+ id_txt.getText() + "', '" + name_txt.getText() + "', '" + surname_txt.getText()
								+ "', '" + risk_txt.getText() + "', '" + unwilling_txt.getText() + "')";
						stmt = conn.prepareStatement(sql);
						stmt.executeUpdate();
					}

					JOptionPane.showMessageDialog(frame, "Submitted Succesfully");

				} catch (SQLException e1) {
					System.err.println("[!]Problem with requested statement");
					e1.printStackTrace();
				}

			}
		});
		btnSubmit.setVisible(false);

		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				LRSViewpoint f = new LRSViewpoint();
				f.main(null);
			}
		});

		JButton btnDelete = new JButton("Delete");

		JButton btnLogout = new JButton("Logout");
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				loginPage f = new loginPage();
				f.main(null);
			}
		});

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
			ResultSet rs = stmt.executeQuery("SELECT * FROM `Client` WHERE ClientID='" + args + "'");

			if (rs.next()) {
				exists = true;
				btnDelete.setVisible(true);
				do {
					name_txt.setText(rs.getString("FirstName"));
					surname_txt.setText(rs.getString("LastName"));
					id_txt.setText(rs.getString("ClientID"));
					risk_txt.setText(rs.getString("RiskPercentage"));
					unwilling_txt.setText(rs.getString("Unwilling"));

					if (rs.getString("Deleted").equals("1")) {
						deleted = true;
						name_txt.setEditable(false);
						surname_txt.setEditable(false);
						id_txt.setEditable(false);
						risk_txt.setEditable(false);
						unwilling_txt.setEditable(false);
					}
				} while (rs.next());
			} else {
				exists = false;
				btnDelete.setVisible(false);
				JOptionPane.showMessageDialog(frame,
						"This client does not exist! \n If you want to add him please fill the info.");
			}

		} catch (SQLException e1) {
			System.err.println("[!]Problem with requested statement");
			e1.printStackTrace();
		}

		JButton btnNewButton = new JButton("<html>View Clients<br>\r\nTrasactions</html>");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Establishing connection
				ConnectDB connection = new ConnectDB();
				Connection conn = connection.getDBConnection();

				// If connection Failed
				if (conn == null) {
					System.out.println("Connection Failed");
				}

				PreparedStatement stmt;
				Account account = Account.getUniqueInstance();

				try {

					if (exists) {
						stmt = conn
								.prepareStatement("UPDATE `Client` SET `Deleted`='1' WHERE ClientID = '" + args + "'");
						stmt.executeUpdate();

						JOptionPane.showMessageDialog(frame, "Deleted Succesfully");
					}

				} catch (SQLException e1) {
					System.err.println("[!]Problem with requested statement");
					e1.printStackTrace();
				}
			}
		});
		btnSubmit.setVisible(false);

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(35)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblRisk).addGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(label_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(label_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblUnwilling, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(45)
								.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addGap(43)
								.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(26).addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING)
								.addComponent(risk_txt, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addComponent(
										unwilling_txt, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblClientForm)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(id_txt, GroupLayout.PREFERRED_SIZE, 86,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(surname_txt, GroupLayout.PREFERRED_SIZE, 86,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(name_txt, GroupLayout.PREFERRED_SIZE, 86,
														GroupLayout.PREFERRED_SIZE))
										.addGap(97)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(btnDelete, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnNewButton, Alignment.LEADING,
														GroupLayout.PREFERRED_SIZE, 114, Short.MAX_VALUE))))))
				.addContainerGap()));
		groupLayout
				.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(33).addComponent(lblClientForm).addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout
												.createSequentialGroup().addComponent(label).addGap(
														24)
												.addComponent(label_1).addGap(24).addComponent(label_2).addGap(24)
												.addComponent(lblRisk, 0, 0, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(name_txt, GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(18)
																.addComponent(surname_txt, GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(18).addComponent(id_txt,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE))
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(btnDelete)
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE,
																		68, GroupLayout.PREFERRED_SIZE)))
												.addGap(18).addComponent(risk_txt, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGap(21)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(unwilling_txt, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblUnwilling))
								.addGap(52)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnLogout)
										.addComponent(btnSubmit).addComponent(btnBack))
								.addContainerGap(111, Short.MAX_VALUE)));
		frame.getContentPane().setLayout(groupLayout);
	}
}
