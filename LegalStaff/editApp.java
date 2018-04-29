package LegalStaff;

/**
 * A legal staff edit a consultation.
 * 
 * */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import GUI.loginPage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

		JButton button_1 = new JButton("Back");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				LegalStaffViewpoint f = new LegalStaffViewpoint();
				f.main(null);
			}
		});

		JButton button_2 = new JButton("Logout");
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

		JButton btnRefresh = new JButton("Search");
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String appID = appID_txt.getText();

				// check for empty text boxes
				if (appID.equals("")) {
					JOptionPane.showMessageDialog(frame, "Enter an consultation id.");
					return;
				}
				Connection conn;

				// request
				ConnectDB connection = new ConnectDB();
				conn = connection.getDBConnection();

				// If connection Failed
				if (conn == null) {
					System.out.println("Connection Failed");
				}

				Statement stmt;

				try {
					stmt = conn.createStatement();
					ResultSet rs = stmt
							.executeQuery("SELECT * FROM `Consultation` WHERE ConsultationID='" + appID + "'");

					if (rs.next()) {
						do {
							lawyer_txt.setText(rs.getString("LegalStaffID"));
							client_txt.setText(rs.getString("ClientID"));
							branch_txt.setText(rs.getString("BranchID"));
							case_txt.setText(rs.getString("CaseID"));
							date_txt.setText(rs.getString("Date"));
							time_txt.setText(rs.getString("Time"));
							legalopinion_txt.setText(rs.getString("LegalOpinion"));
							recomandation_txt.setText(rs.getString("Recommendation"));
							lawyer_txt.setEditable(false);
							client_txt.setEditable(false);
							branch_txt.setEditable(false);
							case_txt.setEditable(false);
							date_txt.setEditable(false);
							time_txt.setEditable(false);
						} while (rs.next());
					} else {
						JOptionPane.showMessageDialog(frame, "Wrong appointment ID.");
					}
				} catch (SQLException e1) {
					System.err.println("[!]Problem with requested statement");
					e1.printStackTrace();
				}

			}
		});

		time_txt = new JTextField();
		time_txt.setColumns(10);
		
		JButton button = new JButton("Submit");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(192, Short.MAX_VALUE)
					.addComponent(lblEditAppointment)
					.addGap(148))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(label, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
													.addGap(77))
												.addComponent(label_2, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
												.addComponent(label_3, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
												.addComponent(label_4, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
												.addComponent(label_5, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
												.addComponent(label_6, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
											.addGap(18))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(label_7, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
											.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
														.addComponent(lawyer_txt)
														.addComponent(client_txt, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
														.addComponent(case_txt)
														.addComponent(time_txt)
														.addComponent(date_txt))
													.addComponent(branch_txt, 173, 173, 173))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
													.addComponent(lblEnterAppointmentid, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
													.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
														.addComponent(appID_txt, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(legalopinion_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)))
										.addComponent(recomandation_txt, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(149)
									.addComponent(button, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
									.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)))
					.addGap(12))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblEditAppointment)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(date_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
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
									.addGap(14)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_4)
										.addComponent(lawyer_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(14)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_5)
										.addComponent(branch_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(69)
									.addComponent(lblEnterAppointmentid)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(appID_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnRefresh)
									.addGap(9)))
							.addGap(9)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_6)
								.addComponent(recomandation_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_7)
								.addComponent(legalopinion_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(315)
							.addComponent(button_1)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_2)
						.addComponent(button))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}