package Receptionist;

/**
 * This class implements 
 * The receptionist can see the uncompleted consultations and update a consultation
 * to completed or failed
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import GUI.loginPage;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

public class UncompletedConsultations {

	private JFrame frame;
	private JTable table;
	private Connection conn;
	private JTextField textField_complete;
	private JTextField textField_fail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UncompletedConsultations window = new UncompletedConsultations();
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
	public UncompletedConsultations() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 671, 444);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblUncompletedConsultations = new JLabel("Uncompleted Consultations");
		lblUncompletedConsultations.setFont(new Font("Arial", Font.BOLD, 16));

		JButton btnGoBack = new JButton("Go back");
		btnGoBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				ReceptionistViewpoint f = new ReceptionistViewpoint();
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

		JScrollPane scrollPane = new JScrollPane();

		JLabel lblAddCompletedConsultation = new JLabel("Update a consultation to completed:");

		textField_complete = new JTextField();
		textField_complete.setColumns(10);

		JButton btnUpdate_complete = new JButton("Update");

		btnUpdate_complete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String cons_comp = textField_complete.getText();
				if (cons_comp.equals("")) {
					JOptionPane.showMessageDialog(frame, "Enter a consultation id");
					return;
				}

				else {
					String sql = "Update `Consultation` SET `Completed`=1 WHERE `ConsultationID`=" + cons_comp;
					PreparedStatement statement = null;
					ResultSet rs;

					try {
						
						// create and execute statement
						statement = conn.prepareStatement(sql);
						statement.executeUpdate();
						
					} catch (SQLException ex) {
						ex.printStackTrace();
					}

					try {
						if (!conn.isClosed()) {
							conn.close();
						}
					} catch (Exception ex) {

					}
				}
				frame.setVisible(false);
				CompletedConsultation comp = new CompletedConsultation();
				comp.main(null);
			}
		});
		
		JLabel lblUpdateAConsultation = new JLabel("Update a consultation to failed:");
		

		textField_fail = new JTextField();
		textField_fail.setColumns(10);

		JButton button_update_fail = new JButton("Update");
		
		button_update_fail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String cons_fail = textField_fail.getText();
				if (cons_fail.equals("")) {
					JOptionPane.showMessageDialog(frame, "Enter a consultation id");
					return;
				}

				else {
					String sql = "Update `Consultation` SET `Failed`=1 WHERE `ConsultationID`=" + cons_fail;
					PreparedStatement statement = null;
					ResultSet rs;

					try {
						
						// create and execute statement
						statement = conn.prepareStatement(sql);
						statement.executeUpdate();
						
					} catch (SQLException ex) {
						ex.printStackTrace();
					}

					try {
						if (!conn.isClosed()) {
							conn.close();
						}
					} catch (Exception ex) {
					}
				}
				frame.setVisible(false);
				FailedConsultations comp = new FailedConsultations();
				comp.main(null);
			}
		});
		
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(204)
					.addComponent(lblUncompletedConsultations)
					.addContainerGap(254, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 617, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(48, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(textField_fail, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_update_fail, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 216, Short.MAX_VALUE)
									.addComponent(btnGoBack, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addGap(49))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblUpdateAConsultation, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(textField_complete, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnUpdate_complete)))
									.addContainerGap(424, Short.MAX_VALUE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblAddCompletedConsultation)
							.addContainerGap(424, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblUncompletedConsultations, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(lblAddCompletedConsultation)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnUpdate_complete)
						.addComponent(textField_complete, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addComponent(lblUpdateAConsultation)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnLogout)
								.addComponent(btnGoBack)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_fail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_update_fail))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Consultations ID", "Date", "Time", "Client ID", "Legal Staff", "Case id" }) {
			Class[] columnTypes = new Class[] { String.class, Object.class, Object.class, Object.class, Object.class,
					Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(94);
		table.getColumnModel().getColumn(1).setPreferredWidth(62);
		table.getColumnModel().getColumn(2).setPreferredWidth(61);
		table.getColumnModel().getColumn(3).setPreferredWidth(52);
		table.getColumnModel().getColumn(4).setPreferredWidth(63);
		table.getColumnModel().getColumn(5).setPreferredWidth(38);

		frame.getContentPane().setLayout(groupLayout);

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		ConnectDB connection = new ConnectDB();
		//establish connection
		conn = connection.getDBConnection();

		String sql = "Select * FROM `Consultation` WHERE `Completed`=0";
		PreparedStatement statement = null;
		ResultSet rs;
		try {
			
			// create and execute statement
			statement = conn.prepareStatement(sql);
			rs = statement.executeQuery();

			while (rs.next()) {
				Object o[] = { rs.getInt("ConsultationID"), rs.getDate("Date"), rs.getString("Time"),
						rs.getString("ClientID"), rs.getString("LegalStaffID"), rs.getString("CaseID"), };
				model.addRow(o);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
