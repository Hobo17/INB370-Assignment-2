/**
 * 
 * This file is part of the CarParkSimulator Project, written as 
 * part of the assessment for INB370, semester 1, 2014. 
 *
 * CarParkSimulator
 * asgn2Simulators 
 * 20/04/2014
 * 
 */
package asgn2Simulators;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JScrollBar;
import javax.swing.JMenuBar;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @authors Rebecca Zanchetta (n8300941) &
 * 			Brad Vose (n8280282)
 *
 */
public class GUISimulator extends JFrame {

	private JPanel contentPane;
	private JTextField DEFAULT_MAX_CAR_SPACES;
	private JTextField DEFAULT_MAX_SMALL_CAR_SPACES;
	private JTextField DEFAULT_MAX_MOTORCYCLE_SPACES;
	private JTextField DEFAULT_MAX_QUEUE_SIZE;
	private JLabel lblSizeParametres;
	private JLabel lblRngProbabilities;
	private JLabel lblSimulationSeed;
	private JLabel lblCarProbability;
	private JLabel lblSmallCarProbability;
	private JLabel lblMotorcycleProbability;
	private JLabel lblSdIntendedStay;
	private JLabel lblMeanIntendedStay;
	private JTextField DEFAULT_SEED;
	private JTextField DEFAULT_CAR_PROB;
	private JTextField DEFAULT_SMALL_CAR_PROB;
	private JTextField DEFAULT_MOTORCYCLE_PROB;
	private JTextField DEFAULT_INTENDED_STAY_MEAN;
	private JTextField DEFAULT_INTENDED_STAY_SD;
	private JSeparator separator_1;
	private JMenuBar menuBar_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUISimulator frame = new GUISimulator();
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
	public GUISimulator() {
		setTitle("Car Park Simulation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 633);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMaximumCarSpaces = new JLabel("Maximum car spaces:");
		lblMaximumCarSpaces.setBounds(69, 81, 167, 14);
		contentPane.add(lblMaximumCarSpaces);
		
		JLabel lblMaximumSmallCar = new JLabel("Maximum small car spaces:");
		lblMaximumSmallCar.setBounds(69, 107, 167, 14);
		contentPane.add(lblMaximumSmallCar);
		
		JLabel lblMaximumMotorcycleSpaces = new JLabel("Maximum motorcycle spaces:");
		lblMaximumMotorcycleSpaces.setBounds(69, 133, 180, 14);
		contentPane.add(lblMaximumMotorcycleSpaces);
		
		JLabel lblMaximumQueueLength = new JLabel("Maximum queue length:");
		lblMaximumQueueLength.setBounds(69, 159, 167, 14);
		contentPane.add(lblMaximumQueueLength);
		
		JLabel lblCarParkSimulation = new JLabel("Car Park Simulation");
		lblCarParkSimulation.setBackground(Color.WHITE);
		lblCarParkSimulation.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblCarParkSimulation.setBounds(30, 16, 206, 26);
		contentPane.add(lblCarParkSimulation);
		
		DEFAULT_MAX_CAR_SPACES = new JTextField();
		DEFAULT_MAX_CAR_SPACES.setText("100");
		DEFAULT_MAX_CAR_SPACES.setBounds(267, 78, 86, 20);
		contentPane.add(DEFAULT_MAX_CAR_SPACES);
		DEFAULT_MAX_CAR_SPACES.setColumns(10);
		
		DEFAULT_MAX_SMALL_CAR_SPACES = new JTextField();
		DEFAULT_MAX_SMALL_CAR_SPACES.setText("30");
		DEFAULT_MAX_SMALL_CAR_SPACES.setBounds(267, 104, 86, 20);
		contentPane.add(DEFAULT_MAX_SMALL_CAR_SPACES);
		DEFAULT_MAX_SMALL_CAR_SPACES.setColumns(10);
		
		DEFAULT_MAX_MOTORCYCLE_SPACES = new JTextField();
		DEFAULT_MAX_MOTORCYCLE_SPACES.setText("20");
		DEFAULT_MAX_MOTORCYCLE_SPACES.setBounds(267, 130, 86, 20);
		contentPane.add(DEFAULT_MAX_MOTORCYCLE_SPACES);
		DEFAULT_MAX_MOTORCYCLE_SPACES.setColumns(10);
		
		DEFAULT_MAX_QUEUE_SIZE = new JTextField();
		DEFAULT_MAX_QUEUE_SIZE.setText("10");
		DEFAULT_MAX_QUEUE_SIZE.setBounds(267, 156, 86, 20);
		contentPane.add(DEFAULT_MAX_QUEUE_SIZE);
		DEFAULT_MAX_QUEUE_SIZE.setColumns(10);
		
		lblSizeParametres = new JLabel("Size Parameters:");
		lblSizeParametres.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblSizeParametres.setBounds(40, 54, 167, 14);
		contentPane.add(lblSizeParametres);
		
		lblRngProbabilities = new JLabel("RNG & Probabilities:");
		lblRngProbabilities.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblRngProbabilities.setBounds(40, 190, 167, 14);
		contentPane.add(lblRngProbabilities);
		
		lblSimulationSeed = new JLabel("Simulation seed:");
		lblSimulationSeed.setBounds(69, 218, 167, 14);
		contentPane.add(lblSimulationSeed);
		
		lblCarProbability = new JLabel("Car probability:");
		lblCarProbability.setBounds(69, 244, 167, 14);
		contentPane.add(lblCarProbability);
		
		lblSmallCarProbability = new JLabel("Small car probability:");
		lblSmallCarProbability.setBounds(69, 270, 167, 14);
		contentPane.add(lblSmallCarProbability);
		
		lblMotorcycleProbability = new JLabel("Motorcycle probability:");
		lblMotorcycleProbability.setBounds(69, 296, 167, 14);
		contentPane.add(lblMotorcycleProbability);
		
		lblSdIntendedStay = new JLabel("SD of intended stay:");
		lblSdIntendedStay.setBounds(69, 348, 167, 14);
		contentPane.add(lblSdIntendedStay);
		
		lblMeanIntendedStay = new JLabel("Mean of intended stay:");
		lblMeanIntendedStay.setBounds(69, 322, 167, 14);
		contentPane.add(lblMeanIntendedStay);
		
		DEFAULT_SEED = new JTextField();
		DEFAULT_SEED.setText("100");
		DEFAULT_SEED.setColumns(10);
		DEFAULT_SEED.setBounds(267, 215, 86, 20);
		contentPane.add(DEFAULT_SEED);
		
		DEFAULT_CAR_PROB = new JTextField();
		DEFAULT_CAR_PROB.setText("1.00");
		DEFAULT_CAR_PROB.setColumns(10);
		DEFAULT_CAR_PROB.setBounds(267, 241, 86, 20);
		contentPane.add(DEFAULT_CAR_PROB);
		
		DEFAULT_SMALL_CAR_PROB = new JTextField();
		DEFAULT_SMALL_CAR_PROB.setText("0.20");
		DEFAULT_SMALL_CAR_PROB.setColumns(10);
		DEFAULT_SMALL_CAR_PROB.setBounds(267, 267, 86, 20);
		contentPane.add(DEFAULT_SMALL_CAR_PROB);
		
		DEFAULT_MOTORCYCLE_PROB = new JTextField();
		DEFAULT_MOTORCYCLE_PROB.setText("0.05");
		DEFAULT_MOTORCYCLE_PROB.setColumns(10);
		DEFAULT_MOTORCYCLE_PROB.setBounds(267, 293, 86, 20);
		contentPane.add(DEFAULT_MOTORCYCLE_PROB);
		
		DEFAULT_INTENDED_STAY_MEAN = new JTextField();
		DEFAULT_INTENDED_STAY_MEAN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				DEFAULT_INTENDED_STAY_SD.setText(String.valueOf(Double.parseDouble(DEFAULT_INTENDED_STAY_MEAN.getText())/3));
			}
		});
		DEFAULT_INTENDED_STAY_MEAN.setText("120.0");
		DEFAULT_INTENDED_STAY_MEAN.setColumns(10);
		DEFAULT_INTENDED_STAY_MEAN.setBounds(267, 319, 86, 20);
		contentPane.add(DEFAULT_INTENDED_STAY_MEAN);
		
		DEFAULT_INTENDED_STAY_SD = new JTextField();
		DEFAULT_INTENDED_STAY_SD.setEditable(false);
		DEFAULT_INTENDED_STAY_SD.setColumns(10);
		DEFAULT_INTENDED_STAY_SD.setBounds(267, 345, 86, 20);
		contentPane.add(DEFAULT_INTENDED_STAY_SD);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(10, 380, 525, 163);
		contentPane.add(tabbedPane);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.setForeground(new Color(0, 0, 0));
		tabbedPane.addTab("  Log  ", null, menuBar, null);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar_1.setForeground(new Color(0, 0, 0));
		tabbedPane.addTab("  Simulation Graph  ", null, menuBar_1, null);
		
		menuBar_2 = new JMenuBar();
		menuBar_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar_2.setForeground(new Color(0, 0, 0));
		tabbedPane.addTab("  Summary Report  ", null, menuBar_2, null);
		
		JButton btnRunSimulation = new JButton("Run Simulation");
		btnRunSimulation.setBackground(Color.WHITE);
		btnRunSimulation.setFont(new Font("Tahoma", Font.ITALIC, 20));
		btnRunSimulation.setBounds(150, 548, 240, 34);
		contentPane.add(btnRunSimulation);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 180, 505, 2);
		contentPane.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(20, 373, 505, 2);
		contentPane.add(separator_1);
	}
}
