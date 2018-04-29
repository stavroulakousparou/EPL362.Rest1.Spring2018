package HOM;

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
import javax.swing.table.DefaultTableModel;

import GUI.ConnectDB;
import GUI.loginPage;

import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;

/**
 * Summary of the recommendation given each month.
 * 
 */

public class RecommendationsMonth {

	private JFrame frame;
	private JTextField branch_txt;
	private JTable table;
	private String recomm = "";
	 
	private static Connection conn = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecomendationsMonth window = new RecomendationsMonth();
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
	public RecomendationsMonth() {
		initialize();
	}

	/**
	 * Initializes the contents of the frame. Takes the branch id and search in the
	 * database for this id. Then shows up the recommendations for this branch. When
	 * the user enter wrong id or the branch not exists, shows up a dialog box with
	 * a message.
	 * 
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 648, 386);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblRecomendations = new JLabel("Recomendations Per Month");
		lblRecomendations.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel label = new JLabel("Branch id: ");

		branch_txt = new JTextField();
		branch_txt.setColumns(10);

		JButton button = new JButton("Refresh");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				String branchID = branch_txt.getText();
				
				if (branchID.equals("")) {
					JOptionPane.showMessageDialog(frame, "Please enter a branch id!");
					return;
				}

				ConnectDB connection = new ConnectDB();
				conn = connection.getDBConnection();

				// If connection Failed
				if (conn == null) {
					System.out.println("Connection Failed");
				}

				boolean flag = false;
				
				String sqlReco = "SELECT * FROM Consultation WHERE BranchID='" + branchID + "'";
				PreparedStatement state = null;

				try {
					state = conn.prepareStatement(sqlReco);
					ResultSet res = state.executeQuery();

					
					// Get the strings from the database
					while (res.next()) {
						
						recomm = res.getString("Recommendation");

						if (recomm.length() == 0) {
							JOptionPane.showMessageDialog(frame, "No results!");
							flag = true;
						} else if (recomm.length() != 0){
							flag = true;
							model.addRow(new Object[] { recomm });
						} 

					}

					// when the user enter wrong 
					if(!flag) {
						JOptionPane.showMessageDialog(frame, "WRONG branch id! Enter again!");
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		// Go back button
		JButton goBackBtn = new JButton("GoBack");
		goBackBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				HOMViewpoint f = new HOMViewpoint();
				f.main(null);
			}
		});

		// Log out button
		JButton logoutBtn = new JButton("LogOut");
		logoutBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				loginPage lp = new loginPage();
				lp.main(null);
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addGap(27)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(467).addComponent(goBackBtn,
								GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(branch_txt, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addGap(57)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addGap(132)
								.addComponent(logoutBtn, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(45, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(214, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(scrollPane, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
								.addComponent(lblRecomendations, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(206)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap().addComponent(lblRecomendations).addGap(18)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE).addComponent(goBackBtn).addGap(1)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(4).addComponent(label))
						.addGroup(groupLayout.createSequentialGroup().addGap(1).addComponent(branch_txt,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(button)
						.addGroup(groupLayout.createSequentialGroup().addGap(17).addComponent(logoutBtn)))
				.addGap(52)));

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Recommendations" }));
		frame.getContentPane().setLayout(groupLayout);
	}

}
