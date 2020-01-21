package randomNumbers;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Main
 * Holds the GUI properties and handles the random number generating.
 * @author Andrew Ferruzza
 *
 */
public class MainGUI extends JFrame {

	private JPanel contentPane;
	private JTextField lowerTxt;
	private JTextField upperTxt;
	Limits r = new Limits();
	Random ran = new Random();
	int count = 0;
	int lowerSize;
	int upperSize;
	ArrayList<Integer> arr = new ArrayList<Integer>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setTitle("Random Number Generator (No Duplicates)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 423, 161);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 407, 122);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		
		JLabel lblLowerBound = new JLabel("Lower Bound:");		
		lblLowerBound.setHorizontalAlignment(SwingConstants.CENTER);
		lblLowerBound.setBounds(10, 11, 124, 24);
		mainPanel.add(lblLowerBound);
		
		JLabel lblUpperBound = new JLabel("Upper Bound:");	
		lblUpperBound.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpperBound.setBounds(273, 11, 124, 24);
		mainPanel.add(lblUpperBound);
		
		lowerTxt = new JTextField();
		lowerTxt.setHorizontalAlignment(SwingConstants.CENTER);
		lowerTxt.setBounds(40, 32, 64, 41);
		mainPanel.add(lowerTxt);
		lowerTxt.setColumns(10);
		
		upperTxt = new JTextField();
		upperTxt.setHorizontalAlignment(SwingConstants.CENTER);
		upperTxt.setColumns(10);
		upperTxt.setBounds(300, 32, 69, 41);
		mainPanel.add(upperTxt);
		
		JButton generateBtn = new JButton("Generate!");
		generateBtn.setBounds(161, 32, 89, 23);
		mainPanel.add(generateBtn);
		
		JLabel displayRandom = new JLabel("");
		displayRandom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		displayRandom.setHorizontalAlignment(SwingConstants.CENTER);
		displayRandom.setBounds(161, 66, 89, 45);
		mainPanel.add(displayRandom);
		
		JLabel displayLower = new JLabel("");
		displayLower.setHorizontalAlignment(SwingConstants.CENTER);
		displayLower.setBounds(94, 16, 46, 14);
		mainPanel.add(displayLower);
		
		JLabel displayHigher = new JLabel("");
		displayHigher.setHorizontalAlignment(SwingConstants.TRAILING);
		displayHigher.setBounds(351, 16, 46, 14);
		mainPanel.add(displayHigher);
		
		/**
		 * Adds action listener for the Generate button. 
		 * Essentially returns a number to the user and stores it in a list.
		 * While searching the list, if the number is already in there, generate another one that isn't.
		 * Repeat this until all possible numbers in the range (inclusive) are returned.
		 */
		
		generateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					r.setLower(Integer.parseInt(lowerTxt.getText()));
					lowerSize = r.getLower();
					displayLower.setText(Integer.toString(r.getLower()));
					lowerTxt.setEditable(false);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Non-integer input set to 0 by default.");
					r.setLower(Integer.parseInt("0"));
					lowerSize = r.getLower();
					displayLower.setText(Integer.toString(0));
					lowerTxt.setEditable(false);
				}
				try {
					r.setUpper(Integer.parseInt(upperTxt.getText()));
					upperSize = r.getUpper();
					displayHigher.setText(Integer.toString(r.getUpper()));
					upperTxt.setEditable(false);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Non-integer input set to 0 by default.");
					r.setUpper(Integer.parseInt("0"));
					upperSize = r.getUpper();
					displayHigher.setText(Integer.toString(0));
					upperTxt.setEditable(false);
				}
					
				if (lowerSize > upperSize) {
					JOptionPane.showMessageDialog(null, "The upper bound must be larger than the lower bound.");
				}
		
				int size = upperSize - lowerSize + 1;
				while (arr.size() < size) {
					int n = ran.nextInt((upperSize + 1) - lowerSize) + lowerSize;
					if (!(arr.contains(n))) {
						arr.add(n);
					}
					else {
						continue;
					}
				}	
				displayRandom.setText(Integer.toString(arr.get(count)));
				count = count + 1;
				
				/**
				 * Reset the entire program instead of constantly closing after numbers are generated.
				 */
				
				if (count == size) {
					JOptionPane.showMessageDialog(null, "All numbers generated in range.");
					arr.clear();
					lowerSize = 0;
					upperSize = 0;
					count = 0;
					lowerTxt.setText("");
					upperTxt.setText("");
					lblLowerBound.setText("Lower Bound: ");
					lblUpperBound.setText("Upper Bound: ");
					displayLower.setText("");
					displayHigher.setText("");
					displayRandom.setText("");
					lowerTxt.setEditable(true);
					upperTxt.setEditable(true);
				}
			}
		});
				
			
		 
	}
}
