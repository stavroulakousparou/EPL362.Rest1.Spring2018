package Receptionist;

/**
 * This class implements the Receptionist's Viewpoint
 * The receptionist can add and delete a consultation, can see the completed, 
 * failed and uncompleted consultations and can search for any client to view
 * his information
 */
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import GUI.loginPage;

public class ReceptionistViewpoint {

	private JFrame frame;
	private JTextField clientsearch_txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReceptionistViewpoint window = new ReceptionistViewpoint();
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
	public ReceptionistViewpoint() {
		initialize();
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		JButton btnNewButton = new JButton("New Consultation\r\n");
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setVisible(false);
				ConsultationForm f2 = new ConsultationForm(null);
				f2.main(null);
			}
		});
		
		JLabel lblReceptionist = new JLabel("Receptionist");
		lblReceptionist.setFont(new Font("Arial", Font.BOLD, 17));
		
		JButton btnViewConsultations = new JButton("View Completed Consultations");
		btnViewConsultations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				CompletedConsultation f = new CompletedConsultation();
				f.main(null);
			}
		});
		
		clientsearch_txt = new JTextField();
		clientsearch_txt.setToolTipText("");
		clientsearch_txt.setColumns(10);
		
		JButton btnViewUncompletedConsultations = new JButton("View Uncompleted Consultations");
		btnViewUncompletedConsultations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				UncompletedConsultations f = new UncompletedConsultations();
				f.main(null);
			}
		});
		
		JButton btn_Search = new JButton("Search");
		
		btn_Search.addMouseListener(new MouseAdapter() {
			
			
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String clientID = clientsearch_txt.getText();
				if(clientID.equals("")){
					JOptionPane.showMessageDialog(frame,"Enter a client id");
					return;
				}
				frame.setVisible(false);
				ViewClient f = new ViewClient(clientID);
				f.main(clientID);
			}
		});
				btn_Search.setSelectedIcon(null);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				loginPage f = new loginPage();
				f.main(null);
			}
		});
		
		JButton btnDeleteConsultations = new JButton("Delete Consultation");
		btnDeleteConsultations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				DeleteConsultation f = new DeleteConsultation();
				f.main(null);
			}
		});
		
		JButton btnViewFailedConsultations = new JButton("View Failed Consultations");
		btnViewFailedConsultations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				FailedConsultations f = new FailedConsultations();
				f.main(null);
			}
		});
		
		JLabel lblSearchForClient = new JLabel("Search for client");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(82, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnViewConsultations, GroupLayout.PREFERRED_SIZE, 194, Short.MAX_VALUE)
						.addComponent(btnViewUncompletedConsultations, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnViewFailedConsultations, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnDeleteConsultations, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSearchForClient)
							.addGap(18)
							.addComponent(clientsearch_txt, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)))
					.addGap(116))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(358)
					.addComponent(btn_Search, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(22, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(164)
					.addComponent(lblReceptionist)
					.addContainerGap(186, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(275, Short.MAX_VALUE)
					.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addGap(71))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addComponent(lblReceptionist)
					.addGap(18)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnDeleteConsultations)
					.addGap(14)
					.addComponent(btnViewConsultations)
					.addGap(18)
					.addComponent(btnViewUncompletedConsultations)
					.addGap(18)
					.addComponent(btnViewFailedConsultations)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btn_Search, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblSearchForClient)
							.addComponent(clientsearch_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
					.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(35))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
