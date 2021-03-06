package LegalStaff;

/**
 * Edit case
 * This class implements the edit case functionality
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
import GUI.loginPage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EditCase {

	private JFrame frame;
	private JTextField details_txt;
	private JTextField client_txt;
	private JTextField lawyer_txt;
	private JTextField strategy_txt;
	private JTextField ml_txt;
	private JTextField case_txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditCase window = new EditCase();
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
	public EditCase() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 352);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel label = new JLabel("Case Form");
		label.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel label_1 = new JLabel("Strategy");

		JLabel label_2 = new JLabel("Details");

		JLabel label_3 = new JLabel("Is Money Laudring");

		JLabel label_4 = new JLabel("Client");

		JLabel label_5 = new JLabel("Lawyer");

		details_txt = new JTextField();
		details_txt.setColumns(10);

		client_txt = new JTextField();
		client_txt.setColumns(10);

		lawyer_txt = new JTextField();
		lawyer_txt.setColumns(10);

		JButton button = new JButton("Submit");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String details = details_txt.getText();
				String strategy = strategy_txt.getText();
				String ml = ml_txt.getText();
				String clientID = client_txt.getText();
				String lawyer = lawyer_txt.getText();
				String caseID = case_txt.getText();

				Connection conn;
				ConnectDB connection = new ConnectDB();
				conn = connection.getDBConnection();

				// If connection Failed
				if (conn == null) {
					System.out.println("Connection Failed");
				}

				String s = "UPDATE `Case` SET Strategy='" + strategy + "', Details='" + details + "', Flagged_ml=" + ml
						+ " WHERE CaseID=" + caseID;
				PreparedStatement statement = null;

				try {
					statement = conn.prepareStatement(s);
					int res2 = statement.executeUpdate();

					if (res2 == 1) {
						JOptionPane.showMessageDialog(frame, "Case Edited Successfully");
					} else {
						JOptionPane.showMessageDialog(frame, "Wrong Insert");
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

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

		strategy_txt = new JTextField();
		strategy_txt.setColumns(10);

		ml_txt = new JTextField();
		ml_txt.setColumns(10);

		JLabel lblEnterCaseId = new JLabel("Enter Case ID:");

		case_txt = new JTextField();
		case_txt.setColumns(10);

		JButton btnRefresh = new JButton("Search");
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

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
					ResultSet rs = statement.executeQuery("SELECT * FROM `Case` WHERE CaseID='" + case_txt.getText() + "'");

					if (rs.next()) {

						do {
							details_txt.setText(rs.getString("Details"));
							strategy_txt.setText(rs.getString("Strategy"));
							case_txt.setText(rs.getString("CaseID"));
							case_txt.setEditable(false);
							ml_txt.setText(rs.getString("Flagged_ml"));
							ml_txt.setEditable(false);
							client_txt.setText(rs.getString("ClientID"));
							client_txt.setEditable(false);
							lawyer_txt.setText(rs.getString("LawyerID"));
							lawyer_txt.setEditable(false);
						} while (rs.next());
					} else {
						JOptionPane.showMessageDialog(frame, "No results.");
						return;
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup().addGap(120)
										.addComponent(label, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup().addGap(36).addGroup(groupLayout
										.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
												.createParallelGroup(Alignment.LEADING, false)
												.addComponent(label_3, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
												.addComponent(label_1, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(label_2, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(label_4, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(label_5, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
												.addGroup(groupLayout
														.createParallelGroup(Alignment.LEADING)
														.addComponent(strategy_txt, GroupLayout.PREFERRED_SIZE, 86,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(groupLayout.createSequentialGroup().addGap(3)
																.addGroup(groupLayout
																		.createParallelGroup(Alignment.LEADING)
																		.addGroup(groupLayout
																				.createParallelGroup(Alignment.TRAILING,
																						false)
																				.addComponent(ml_txt, Alignment.LEADING,
																						0, 0, Short.MAX_VALUE)
																				.addComponent(
																						details_txt, Alignment.LEADING,
																						GroupLayout.PREFERRED_SIZE, 86,
																						GroupLayout.PREFERRED_SIZE))
																		.addComponent(client_txt,
																				GroupLayout.PREFERRED_SIZE, 86,
																				GroupLayout.PREFERRED_SIZE)))
														.addGroup(groupLayout.createSequentialGroup().addGap(3)
																.addGroup(groupLayout
																		.createParallelGroup(Alignment.TRAILING)
																		.addComponent(button_1,
																				GroupLayout.PREFERRED_SIZE, 86,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(lawyer_txt,
																				GroupLayout.PREFERRED_SIZE, 86,
																				GroupLayout.PREFERRED_SIZE)))))
										.addComponent(button, GroupLayout.PREFERRED_SIZE, 86,
												GroupLayout.PREFERRED_SIZE))))
						.addGroup(
								groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup().addGap(35).addComponent(button_2,
												GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup().addGap(55)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
														.addComponent(case_txt)
														.addComponent(btnRefresh, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(lblEnterCaseId, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
						.addGap(43)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addGroup(groupLayout
								.createParallelGroup(Alignment.BASELINE)
								.addComponent(strategy_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1).addComponent(lblEnterCaseId))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
								.createSequentialGroup().addGap(29)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(details_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(label_2))
								.addGap(27)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(ml_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(label_3))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(client_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(label_4))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lawyer_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(label_5)))
								.addGroup(groupLayout.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(case_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnRefresh)))
						.addGap(31).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(button)
								.addComponent(button_2).addComponent(button_1))
						.addGap(21)));
		frame.getContentPane().setLayout(groupLayout);
	}

}