package Receptionist;

/**
 * This class implements the new consultation form
 * The receptionist inserts a new consultation that is
 * added to the database
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import GUI.loginPage;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConsultationForm {

	private JFrame frame;
	private JTextField year_txt;
	private JTextField client_txt;
	private JTextField lawyer_txt;
	private JTextField case_txt;
	private JTextField branch_txt;
	private JTextField recomandation_txt;
	private JTextField legalopinion_txt;
	private Connection conn;
	private JTextField textField_dropin;

	/**
	 * Launch the application.
	 */
	public static void main(String args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultationForm window = new ConsultationForm(args);
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
	public ConsultationForm(String clientID) {
		initialize(clientID);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String clientID) {
		frame = new JFrame();
		frame.setBounds(100, 100, 537, 497);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblAppointmentForm = new JLabel("Consultation Form");

		JLabel lblDate = new JLabel("Date:");
		
		JComboBox cb_day = new JComboBox();
		cb_day.setModel(new DefaultComboBoxModel(
				new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16",
						"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
		cb_day.setToolTipText("");

		JComboBox cb_month = new JComboBox();
		cb_month.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05",
				"06", "07", "08", "09", "10", "11", "12" }));
		cb_month.setToolTipText("");

		year_txt = new JTextField();
		year_txt.setColumns(10);

		JLabel lblTime = new JLabel("Time");

		JComboBox cb_hour = new JComboBox();
		cb_hour.setModel(new DefaultComboBoxModel(
				new String[] { "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18" }));
		cb_hour.setToolTipText("");

		JComboBox cb_minutes = new JComboBox();
		cb_minutes.setModel(new DefaultComboBoxModel(new String[] { "00", "10", "20", "30", "40", "50" }));
		cb_minutes.setToolTipText("");

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String date = year_txt.getText() + "-" + (cb_month.getSelectedItem().toString()) + "-"
						+ cb_day.getSelectedItem().toString();
				String hour = cb_hour.getSelectedItem().toString() + ":" + cb_minutes.getSelectedItem().toString();
				String clientID = client_txt.getText();
				String lawyer = lawyer_txt.getText();
				String caseID = case_txt.getText();
				String branchID = branch_txt.getText();
				String recommendation = recomandation_txt.getText();
				String legalopinion = legalopinion_txt.getText();
				String dropin=textField_dropin.getText();

				// check for empty text boxes
				if (date.equals("") || hour.equals("") || clientID.equals("") || lawyer.equals("") || caseID.equals("")
						|| branchID.equals("")) {
					JOptionPane.showMessageDialog(frame, "Fill all fields");
					return; 
				}
				else if  (!dropin.equals("yes") && !dropin.equals("no")  ) {
						JOptionPane.showMessageDialog(frame, "Please write yes/no in dropin field");
						
					
				} else {

					// add_cons_db();
					ConnectDB connection = new ConnectDB();
					conn = connection.getDBConnection();

					String sql = "INSERT INTO `Consultation`(`Date`,`Time`,`CaseID`,`ClientID`,`BranchID`,`LegalStaffID`,"
							+ "`Recommendation`,`LegalOpinion`,`Drop_in`) VALUES (?,?,?,?,?,?,?,?,?)";
					PreparedStatement statement = null;
					try {

						// create and execute statement
						statement = conn.prepareStatement(sql);
						statement.setString(1, date);
						statement.setString(2, hour);
						statement.setString(3, caseID);
						statement.setString(4, clientID);
						statement.setString(5, branchID);
						statement.setString(6, lawyer);
						statement.setString(7, recommendation);
						statement.setString(8, legalopinion);
						statement.setString(9, dropin);

						statement.executeUpdate();

					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

		});

		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				ReceptionistViewpoint f = new ReceptionistViewpoint();
				f.main(null);
			}
		});

		JLabel lblNewLabel = new JLabel("ClientID");

		client_txt = new JTextField();
		client_txt.setColumns(10);
		if (clientID != null) {
			client_txt.setText(clientID);
			client_txt.setEditable(false);
		}

		JLabel lblNewLabel_1 = new JLabel("LawyerID");

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

		JLabel lblCase = new JLabel("CaseID");

		case_txt = new JTextField();
		case_txt.setColumns(10);

		JLabel lblBranch = new JLabel("Branch");

		branch_txt = new JTextField();
		branch_txt.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Recommendation");

		recomandation_txt = new JTextField();
		recomandation_txt.setColumns(10);

		JLabel lblLegalOp = new JLabel("Legal Opinion");

		legalopinion_txt = new JTextField();
		legalopinion_txt.setColumns(10);
		
		JLabel lblDay = new JLabel("Day");
		
		JLabel lblNewLabel_3 = new JLabel("Month");
		
		JLabel lblYear = new JLabel("Year");
		
		JLabel lblNewLabel_4 = new JLabel("Drop in (yes/no)");
		
		textField_dropin = new JTextField();
		textField_dropin.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(158)
							.addComponent(lblAppointmentForm))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(lblBranch)
								.addComponent(lblNewLabel_2)
								.addComponent(lblLegalOp)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblDate, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
									.addComponent(lblTime, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
									.addComponent(lblCase, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addGap(40)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(branch_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(legalopinion_txt, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(lawyer_txt, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
												.addComponent(client_txt, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
												.addComponent(case_txt, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
											.addGap(74)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNewLabel_4)
												.addComponent(textField_dropin, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)))
										.addComponent(recomandation_txt, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(btnSubmit)
											.addGap(18)
											.addComponent(btnGoBack))
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
											.addComponent(lblDay)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(cb_day, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(lblNewLabel_3)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(cb_month, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(cb_hour, 0, 104, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(cb_minutes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(55)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblYear, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(year_txt, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
										.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
									.addGap(142)))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAppointmentForm)
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblYear)
						.addComponent(year_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDay)
						.addComponent(cb_day, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3)
						.addComponent(cb_month, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTime, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(cb_hour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cb_minutes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCase)
								.addComponent(case_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(client_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(lawyer_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblBranch)
								.addComponent(branch_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_4)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_dropin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(36)))
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(recomandation_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLegalOp)
						.addComponent(legalopinion_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSubmit)
						.addComponent(btnGoBack)
						.addComponent(btnLogout))
					.addGap(26))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
