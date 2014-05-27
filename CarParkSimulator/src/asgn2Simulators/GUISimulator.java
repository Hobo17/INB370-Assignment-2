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

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import java.awt.TextArea;

import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JMenuBar;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JTextArea;

import asgn2CarParks.CarPark;
import asgn2Exceptions.SimulationException;
import asgn2Exceptions.VehicleException;

/**
 * @authors Rebecca Zanchetta (n8300941) &
 * 			Brad Vose (n8280282)
 *
 */
public class GUISimulator extends javax.swing.JFrame {

	private JPanel contentPane;
	private JTextField maxCarSpacesField;
	private JTextField maxSmallCarSpacesField;
	private JTextField maxMotoSpacesField;
	private JTextField maxQueueSizeField;
	private JLabel lblSizeParametres;
	private JLabel lblRngProbabilities;
	private JLabel lblSimulationSeed;
	private JLabel lblCarProbability;
	private JLabel lblSmallCarProbability;
	private JLabel lblMotorcycleProbability;
	private JLabel lblSdIntendedStay;
	private JLabel lblMeanIntendedStay;
	private JTextField seedField;
	private JTextField carProbField;
	private JTextField smallCarProbField;
	private JTextField motoProbField;
	private JTextField intendedStayMeanField;
	private JTextField intendedStaySDField;
	private JSeparator separator_1;
	private JMenuBar menuBar_2;
	
	private TextArea textArea = new TextArea();
	private ChartPanel simulationGraph;
	
	private CarPark carPark;
	private Simulator sim;
	private Log log;

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		CarPark cp = new CarPark();
		Simulator s = null;
		Log l = null; 
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				if(args.length == 9){
					try {
						GUISimulator frame = new GUISimulator(
								Integer.parseInt(args[0]), 
								Integer.parseInt(args[1]), 
								Integer.parseInt(args[2]), 
								Integer.parseInt(args[3]), 
								Integer.parseInt(args[4]), 
								Double.parseDouble(args[5]), 
								Double.parseDouble(args[6]), 
								Double.parseDouble(args[7]), 
								Double.parseDouble(args[8])
							);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else{
					try {
						GUISimulator frame = new GUISimulator();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	public GUISimulator() {
		this(Constants.DEFAULT_MAX_CAR_SPACES,
				Constants.DEFAULT_MAX_SMALL_CAR_SPACES,
				Constants.DEFAULT_MAX_MOTORCYCLE_SPACES,
				Constants.DEFAULT_MAX_QUEUE_SIZE,
				Constants.DEFAULT_SEED,
				Constants.DEFAULT_CAR_PROB,
				Constants.DEFAULT_SMALL_CAR_PROB,
				Constants.DEFAULT_MOTORCYCLE_PROB,
				Constants.DEFAULT_INTENDED_STAY_MEAN);
	}

	/**
	 * Create the frame.
	 */
	public GUISimulator(int maxCarSpaces,
						int maxSmallCarSpaces,
						int maxMotoSpaces,
						int maxQueueLength,
						int seed,
						double carProb,
						double smallCarProb,
						double motoProb,
						double intendedStayMean) {
	
		
		simulationGraph = new ChartPanel();
		
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
		
		maxCarSpacesField = new JTextField();
		maxCarSpacesField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(Integer.parseInt(maxCarSpacesField.getText()) < 0){
					textArea.append("\nMax Car Spaces cannot be below 0");
					maxCarSpacesField.setText("0");
				}
			}
		});
		maxCarSpacesField.setText(String.valueOf(maxCarSpaces));
		maxCarSpacesField.setBounds(267, 78, 86, 20);
		contentPane.add(maxCarSpacesField);
		maxCarSpacesField.setColumns(10);
		
		maxSmallCarSpacesField = new JTextField();
		maxSmallCarSpacesField.setText(String.valueOf(maxSmallCarSpaces));
		maxSmallCarSpacesField.setBounds(267, 104, 86, 20);
		contentPane.add(maxSmallCarSpacesField);
		maxSmallCarSpacesField.setColumns(10);
		maxSmallCarSpacesField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(Integer.parseInt(maxSmallCarSpacesField.getText()) < 0 || 
						Integer.parseInt(maxSmallCarSpacesField.getText())  > Integer.parseInt(maxCarSpacesField.getText())){
					textArea.append("\nMax Small Car Spaces cannot be below 0 or more than total car spaces");
					maxSmallCarSpacesField.setText("0");
				}
			}
		});
		
		maxMotoSpacesField = new JTextField();
		maxMotoSpacesField.setText(String.valueOf(maxMotoSpaces));
		maxMotoSpacesField.setBounds(267, 130, 86, 20);
		contentPane.add(maxMotoSpacesField);
		maxMotoSpacesField.setColumns(10);
		maxMotoSpacesField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(Integer.parseInt(maxMotoSpacesField.getText()) < 0){
					textArea.append("\nMax MotorCycle Spaces cannot be below 0");
					maxMotoSpacesField.setText("0");
				}
			}
		});
		
		maxQueueSizeField = new JTextField();
		maxQueueSizeField.setText(String.valueOf(maxQueueLength));
		maxQueueSizeField.setBounds(267, 156, 86, 20);
		contentPane.add(maxQueueSizeField);
		maxQueueSizeField.setColumns(10);
		maxQueueSizeField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(Integer.parseInt(maxQueueSizeField.getText()) < 0){
					textArea.append("\nMax Queue length cannot be below 0");
					maxQueueSizeField.setText("0");
				}
			}
		});
		
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
		
		seedField = new JTextField();
		seedField.setText(String.valueOf(seed));
		seedField.setColumns(10);
		seedField.setBounds(267, 215, 86, 20);
		contentPane.add(seedField);
		
		carProbField = new JTextField();
		carProbField.setText(String.valueOf(carProb));
		carProbField.setColumns(10);
		carProbField.setBounds(267, 241, 86, 20);
		contentPane.add(carProbField);
		carProbField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(Double.parseDouble(carProbField.getText()) < 0 ||
						Double.parseDouble(carProbField.getText()) > 1){
					textArea.append("\nProbability must be between 0 and 1");
					carProbField.setText("0");
				}
			}
		});
		
		smallCarProbField = new JTextField();
		smallCarProbField.setText(String.valueOf(smallCarProb));
		smallCarProbField.setColumns(10);
		smallCarProbField.setBounds(267, 267, 86, 20);
		contentPane.add(smallCarProbField);
		smallCarProbField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(Double.parseDouble(smallCarProbField.getText()) < 0 ||
						Double.parseDouble(smallCarProbField.getText()) > 1){
					textArea.append("\nProbability must be between 0 and 1");
					smallCarProbField.setText("0");
				}
			}
		});
		
		motoProbField = new JTextField();
		motoProbField.setText(String.valueOf(motoProb));
		motoProbField.setColumns(10);
		motoProbField.setBounds(267, 293, 86, 20);
		contentPane.add(motoProbField);
		motoProbField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(Double.parseDouble(motoProbField.getText()) < 0 ||
						Double.parseDouble(motoProbField.getText()) > 1){
					textArea.append("\nProbability must be between 0 and 1");
					motoProbField.setText("0");
				}
			}
		});
		
		intendedStayMeanField = new JTextField();
		intendedStayMeanField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				intendedStaySDField.setText(String.format("%.2f", Double.parseDouble(intendedStayMeanField.getText())/3));
			}
		});
		intendedStayMeanField.setText(String.valueOf(intendedStayMean));
		intendedStayMeanField.setColumns(10);
		intendedStayMeanField.setBounds(267, 319, 86, 20);
		contentPane.add(intendedStayMeanField);
		
		intendedStaySDField = new JTextField();
		intendedStaySDField.setEditable(false);
		intendedStaySDField.setColumns(10);
		intendedStaySDField.setBounds(267, 345, 86, 20);
		intendedStaySDField.setText(String.format("%.2f", Double.parseDouble(intendedStayMeanField.getText())/3));
		contentPane.add(intendedStaySDField);
		
		
		
		JTabbedPane tabbedPane = new javax.swing.JTabbedPane();
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(10, 380, 525, 163);
		contentPane.add(tabbedPane);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.setForeground(new Color(0, 0, 0));
		textArea.setEditable(false);
		tabbedPane.addTab("  Log  ", textArea);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar_1.setForeground(new Color(0, 0, 0));
		tabbedPane.addTab("  Simulation Graph  ", simulationGraph);
		
		menuBar_2 = new JMenuBar();
		menuBar_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar_2.setForeground(new Color(0, 0, 0));
		tabbedPane.addTab("  Summary Report  ", null, menuBar_2, null);
		
		
		
		JButton btnRunSimulation = new JButton("Run Simulation");
		btnRunSimulation.setBackground(Color.WHITE);
		btnRunSimulation.setFont(new Font("Tahoma", Font.ITALIC, 20));
		btnRunSimulation.setBounds(150, 548, 240, 34);
		contentPane.add(btnRunSimulation);		btnRunSimulation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				launchSimulation(
					Integer.parseInt(maxCarSpacesField.getText()), 
					Integer.parseInt(maxSmallCarSpacesField.getText()), 
					Integer.parseInt(maxMotoSpacesField.getText()), 
					Integer.parseInt(maxQueueSizeField.getText()), 
					Integer.parseInt(seedField.getText()), 
					Double.parseDouble(carProbField.getText()), 
					Double.parseDouble(smallCarProbField.getText()), 
					Double.parseDouble(motoProbField.getText()), 
					Double.parseDouble(intendedStayMeanField.getText()));
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 180, 505, 2);
		contentPane.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(20, 373, 505, 2);
		contentPane.add(separator_1);
	}
	
	/**
	 * Method to run the simulation from start to finish. Exceptions are propagated upwards from Vehicle,
	 * Simulation and Log objects as necessary 
	 * @throws VehicleException if Vehicle creation or operation constraints violated 
	 * @throws SimulationException if Simulation constraints are violated 
	 * @throws IOException on logging failures
	 */
	public void runSimulation() throws VehicleException, SimulationException, IOException {
		this.log.initialEntry(this.carPark,this.sim);
		for (int time=0; time<=Constants.CLOSING_TIME; time++) {
			//queue elements exceed max waiting time
			if (!this.carPark.queueEmpty()) {
				this.carPark.archiveQueueFailures(time);
			}
			//vehicles whose time has expired
			if (!this.carPark.carParkEmpty()) {
				//force exit at closing time, otherwise normal
				boolean force = (time == Constants.CLOSING_TIME);
				this.carPark.archiveDepartingVehicles(time, force);
			}
			//attempt to clear the queue 
			if (!this.carPark.carParkFull()) {
				this.carPark.processQueue(time,this.sim);
			}
			// new vehicles from minute 1 until the last hour
			if (newVehiclesAllowed(time)) { 
				this.carPark.tryProcessNewVehicles(time,this.sim);
			}
			//Log progress 
			this.log.logEntry(time,this.carPark);
			this.textArea.append(this.carPark.getStatus(time));
			
			//this.simulationGraph.addDataForGivenTimePoint(time, this.carPark.getNumCars(), 
			//		this.carPark.getNumSmallCars(), this.carPark.getNumMotorCycles());
		}
		this.log.finalise(this.carPark);
		//this.simulationGraph.generateFinalChartFromData();
	}

	/**
	 * Helper method to determine if new vehicles are permitted
	 * @param time int holding current simulation time
	 * @return true if new vehicles permitted, false if not allowed due to simulation constraints. 
	 */
	private boolean newVehiclesAllowed(int time) {
		boolean allowed = (time >=1);
		return allowed && (time <= (Constants.CLOSING_TIME - 60));
	}
	
	private void launchSimulation(	int maxCarSpaces,
									int maxSmallCarSpaces,
									int maxMotoSpaces,
									int maxQueueSize,
									int seed,
									double carProb,
									double smallCarProb,
									double motoProb,
									double intendedStayMean){
		carPark = new CarPark(maxCarSpaces, maxSmallCarSpaces,
				maxMotoSpaces, maxQueueSize);

		try{
			sim = new Simulator(seed, intendedStayMean, intendedStayMean/3, carProb, smallCarProb, motoProb);
			log = new Log();
		} catch (IOException | SimulationException e1) {
			e1.printStackTrace();
			System.exit(-1);
		}
		
	    try {
	        runSimulation();
	    } catch (Exception e) {
	    	e.printStackTrace();
	        textArea.append(e.getMessage());
	    }
	
	}
}
