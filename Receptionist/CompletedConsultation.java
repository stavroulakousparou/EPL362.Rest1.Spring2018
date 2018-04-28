package Receptionist;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
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

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;

public class CompletedConsultation {

	private JFrame frame;
	private JTable table;
	private JTable table_1;
	
	private Connection conn;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompletedConsultation window = new CompletedConsultation();
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
	public CompletedConsultation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 837, 423);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel label = new JLabel("Completed Consultations");
		label.setFont(new Font("Arial", Font.BOLD, 16));

		JList list = new JList();
	
		
		//GO BACK BUTTON
		JButton btnGoback = new JButton("GoBack");
		btnGoback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				ReceptionistViewpoint f = new ReceptionistViewpoint();
				f.main(null);
			}
		});

		
		//LOGOUT BUTTON
		JButton btnLogout = new JButton("LogOut");
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				loginPage f = new loginPage();
				f.main(null);
			}
		});

		
		
		JButton btnRefresh = new JButton("Refresh");
		
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});  
		
		table = new JTable();
		
		JScrollPane scrollPane = new JScrollPane();
		
		
		//GOBACK BUTTON
		JButton btnNewButton_1 = new JButton("GoBack");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				ReceptionistViewpoint f = new ReceptionistViewpoint();
				f.main(null);
			}
		});
		
		//LOGOUT BUTTON
		JButton btnLogout_1 = new JButton("LogOut");
		btnLogout_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				loginPage f = new loginPage();
				f.main(null);
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(308)
					.addComponent(label)
					.addContainerGap(319, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(57)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 695, GroupLayout.PREFERRED_SIZE)
					.addGap(140)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(782)
										.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
										.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
										.addGap(33))))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(502)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnGoback, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
								.addGap(200)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(41)
							.addComponent(table, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(87)
					//.addComponent(btnNewButton)
					.addGap(171)
					.addComponent(btnNewButton_1)
					.addGap(194)
					.addComponent(btnLogout_1)
					.addContainerGap(1038, Short.MAX_VALUE))
		);
	
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnRefresh)
							.addGap(17)
							.addComponent(btnGoback)
							.addGap(67)
							.addComponent(table, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
					.addGap(124)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						//.addComponent(btnNewButton)
						.addComponent(btnNewButton_1)
						.addComponent(btnLogout_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLogout)
					.addGap(20))
		);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		table_1.setFillsViewportHeight(true);
		table_1.setBorder(UIManager.getBorder("ComboBox.border"));
		table_1.setCellSelectionEnabled(true);
		table_1.setColumnSelectionAllowed(true);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"Consultation ID", "Date", "Time", "Client ID", "Legal Staff ID", "Case ID"
			}
		));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(85);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(55);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(83);
		frame.getContentPane().setLayout(groupLayout);
		
		DefaultTableModel model = (DefaultTableModel) table_1.getModel();
	    model.setRowCount(0);
	    
	    ConnectDB connection = new ConnectDB();
		conn = connection.getDBConnection();

        
		String sql = "Select * FROM `Consultation` WHERE `Completed`=1";
		PreparedStatement statement = null;
		ResultSet rs;
		try {
			
			// create and execute statement
			statement = conn.prepareStatement(sql);
			rs=statement.executeQuery();
			
			while (rs.next()) {
				Object o[]= { rs.getInt("ConsultationID"), 
						rs.getDate("Date"),
						rs.getString("Time"),
						rs.getString("ClientID"),
						rs.getString("LegalStaffID"),
						rs.getString("CaseID"),
						};
			model.addRow(o);

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}	
	}
}
