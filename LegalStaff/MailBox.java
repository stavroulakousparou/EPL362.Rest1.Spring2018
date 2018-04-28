package LegalStaff;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import GUI.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MailBox {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MailBox window = new MailBox();
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
	public MailBox() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 708, 422);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblMailBox = new JLabel("MailBox");
		lblMailBox.setFont(new Font("Arial", Font.BOLD, 16));

		JButton btnNewButton = new JButton("View Unreaded");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);

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
					ResultSet rs = stmt
							.executeQuery("SELECT * FROM `Email` WHERE (`To`=" + account.getId() + " AND IsRead=0)");

					if (rs.next()) {
						do {
							model.addRow(new Object[] { rs.getString("EmailID"), rs.getString("Title"),
									rs.getString("From"), rs.getString("To") });
						} while (rs.next());
					} else
						JOptionPane.showMessageDialog(frame, "No results.");

				} catch (SQLException e1) {
					System.err.println("[!]Problem with requested statement");
					e1.printStackTrace();
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();

		JButton btnViewReaded = new JButton("View Readed");
		btnViewReaded.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);

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
					ResultSet rs = stmt
							.executeQuery("SELECT * FROM `Email` WHERE (`To`=" + account.getId() + " AND IsRead=1)");

					if (rs.next()) {
						do {
							model.addRow(new Object[] { rs.getString("EmailID"), rs.getString("Title"),
									rs.getString("From"), rs.getString("To") });
						} while (rs.next());
					} else
						JOptionPane.showMessageDialog(frame, "No results.");

				} catch (SQLException e1) {
					System.err.println("[!]Problem with requested statement");
					e1.printStackTrace();
				}
			}
		});

		JButton btnViewSended = new JButton("View Sent");
		btnViewSended.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);

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
					ResultSet rs = stmt.executeQuery("SELECT * FROM `Email` WHERE `From`=" + account.getId());

					if (rs.next()) {
						do {
							model.addRow(new Object[] { rs.getString("EmailID"), rs.getString("Title"),
									rs.getString("From"), rs.getString("To") });
						} while (rs.next());
					} else
						JOptionPane.showMessageDialog(frame, "No results.");

				} catch (SQLException e1) {
					System.err.println("[!]Problem with requested statement");
					e1.printStackTrace();
				}
			}
		});

		JButton btnNewButton_1 = new JButton("Send Email");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				SendEmail f = new SendEmail();
				f.main(null);
			}
		});

		JButton btnNewButton_2 = new JButton("GoBack");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setVisible(false);
				LegalStaffViewpoint f = new LegalStaffViewpoint();
				f.main(null);
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(303).addComponent(lblMailBox))
						.addGroup(groupLayout.createSequentialGroup().addGap(45).addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING, false)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 587, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup().addComponent(btnNewButton).addGap(39)
										.addComponent(btnViewReaded).addGap(34)
										.addComponent(btnViewSended, GroupLayout.PREFERRED_SIZE, 105,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 96,
												GroupLayout.PREFERRED_SIZE)
										.addGap(20)))))
				.addContainerGap(76, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup().addContainerGap(269, Short.MAX_VALUE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
						.addGap(261)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap().addComponent(lblMailBox).addGap(10)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE).addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton)
						.addComponent(btnNewButton_2).addComponent(btnViewSended)
						.addComponent(btnViewReaded, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
				.addGap(37).addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
				.addGap(22)));

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int i = table.getSelectedRow();
				Object x = model.getValueAt(i, 0);
				JOptionPane.showMessageDialog(frame, model.getValueAt(i, 2).toString(),
						model.getValueAt(i, 1).toString(), JOptionPane.PLAIN_MESSAGE);

				// Establishing connection
				ConnectDB connection = new ConnectDB();
				Connection conn = connection.getDBConnection();

				// If connection Failed
				if (conn == null) {
					System.out.println("Connection Failed");
				}

				Statement stmt;

				try {
					stmt = conn.createStatement();
					int result =  stmt.executeUpdate("UPDATE `Email` SET IsRead=1 WHERE EmailID=" + model.getValueAt(i, 0).toString());

					if (result == 1)
						JOptionPane.showMessageDialog(frame, "Email marked as read");
					else
						JOptionPane.showMessageDialog(frame, "Email didn't mark as read!");

				} catch (SQLException e1) {
					System.err.println("[!]Problem with requested statement");
					e1.printStackTrace();
				}
			}
		});

		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null }, },
				new String[] { "EmailID", "Title", "Body", "From", "To" }));
		frame.getContentPane().setLayout(groupLayout);
	}
}
