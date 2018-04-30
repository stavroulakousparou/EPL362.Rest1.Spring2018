package Receptionist;

/**
 * This class implements the View Client page
 * The receptionist can  discover the record for individual clients.
 *
 */
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import GUI.loginPage;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewClient {

	private JFrame frame;
	private JTextField name_txt;
	private JTextField surname_txt;
	private JTextField id_txt;
	private JTextField unwillingness_txt;
	private JTextField comments_txt;
	private Connection conn;

	/**
	 * Launch the application.
	 */
	public static void main(String clientID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewClient window = new ViewClient(clientID);
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
	public ViewClient(String cID) {
		initialize(cID);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String cID) {
		frame = new JFrame();
		frame.setBounds(100, 100, 463, 361);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblViewClient = new JLabel("View Client");
		lblViewClient.setFont(new Font("Arial", Font.BOLD, 16));
		JLabel lblNewLabel = new JLabel("Name");
		name_txt = new JTextField();
		name_txt.setColumns(10);

		JLabel lblSurname = new JLabel("Surname");

		surname_txt = new JTextField();
		surname_txt.setColumns(10);

		JLabel lblId = new JLabel("ID");

		id_txt = new JTextField();
		id_txt.setColumns(10);

		JLabel lblUnwillingnes = new JLabel("Risk Percentage");

		unwillingness_txt = new JTextField();
		unwillingness_txt.setColumns(10);

		JLabel lblComments = new JLabel("Unwilling Strategies");

		comments_txt = new JTextField();
		comments_txt.setColumns(10);

		ConnectDB connection = new ConnectDB();
		conn = connection.getDBConnection();

		String sql = "Select * FROM `Client` WHERE `ClientID`=" + cID;
		PreparedStatement statement = null;
		ResultSet rs;

		try {

			// create and execute statement
			statement = conn.prepareStatement(sql);
			rs = statement.executeQuery();

			if (rs.next()) {
				name_txt.setText(rs.getString("FirstName"));
				surname_txt.setText(rs.getString("LastName"));
				id_txt.setText(cID);
				unwillingness_txt.setText(rs.getString("RiskPercentage"));
				comments_txt.setText(rs.getString("Unwilling"));
				name_txt.setEditable(false);
				surname_txt.setEditable(false);
				id_txt.setEditable(false);
				unwillingness_txt.setEditable(false);
				comments_txt.setEditable(false);

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		JButton btnGoBack = new JButton("Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnGoBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				ReceptionistViewpoint f = new ReceptionistViewpoint();
				f.main(null);
			}
		});

		JButton btnCreateConsultation = new JButton("<html>Create <br>\r\nConsultation</html>");
		btnCreateConsultation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String id = id_txt.getText();
				frame.setVisible(false);
				ConsultationForm f = new ConsultationForm(id);
				f.main(id);
			}
		});

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				loginPage f = new loginPage();
				f.main(null);
			}
		});

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(2)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblSurname)
						.addComponent(lblNewLabel)
						.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblComments)
						.addComponent(lblUnwillingnes, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup().addGap(26)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblViewClient)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addComponent(id_txt, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(name_txt, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.UNRELATED))
										.addComponent(surname_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(151).addComponent(
										btnCreateConsultation, GroupLayout.PREFERRED_SIZE, 0,
										GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup().addGap(26)
										.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 83,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnGoBack))))
						.addGroup(groupLayout.createSequentialGroup().addGap(29)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(unwillingness_txt, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(comments_txt, GroupLayout.PREFERRED_SIZE, 195,
												GroupLayout.PREFERRED_SIZE))))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup().addGap(19)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
								.createSequentialGroup().addGap(43)
								.addComponent(name_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblSurname)
										.addComponent(surname_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup().addGap(29)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup().addGap(22).addComponent(
														btnCreateConsultation, GroupLayout.PREFERRED_SIZE, 60,
														GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createSequentialGroup().addGap(18)
														.addComponent(lblNewLabel)))))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(id_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblId))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(unwillingness_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUnwillingnes))
						.addGap(18).addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(comments_txt, GroupLayout.PREFERRED_SIZE, 48,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblComments)))
						.addGroup(groupLayout.createSequentialGroup().addGap(16)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnLogout)
										.addComponent(lblViewClient).addComponent(btnGoBack))))
				.addGap(263)));
		frame.getContentPane().setLayout(groupLayout);
	}

}
