/**
 * 
 * This file is part of the CarParkSimulator Project, written as 
 * part of the assessment for INB370, semester 1, 2014. 
 *
 * CarParkSimulator
 * asgn2Tests 
 * 29/04/2014
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2CarParks.CarPark;
import asgn2Exceptions.VehicleException;
import asgn2Exceptions.SimulationException;
import asgn2Simulators.Constants;
import asgn2Simulators.Simulator;
import asgn2Vehicles.MotorCycle;
import asgn2Vehicles.Car;

/**
 * @author Rebecca Zanchetta (n8300941)
 *
 */
public class CarParkTests {
	
	private final int MAX_CAR_SPACES = 100;
	private final int MAX_SMALL_CAR_SPACES = 30;
	private final int MAX_MOTORCYCLE_SPACES = 20;
	private final int MAX_QUEUE_SIZE = 10;
	
	private final int ARRIVAL_TIME = 20;
	private final int END_CARPARK_DEPARTURE_TIME = 18 * 60;
	private final int INTENDED_DURATION = 20;
	private final int DEPARTURE_TIME = ARRIVAL_TIME + INTENDED_DURATION;
	private final int EXIT_TIME = 30;
	
	private CarPark carpark;
	
	private final int ONE = 1;
	
	private final boolean SMALL = true;
	private final boolean NOT_SMALL = false;
	
	private final String VEH_ID = "ABC123";
	
	private Car car;
	
	private MotorCycle moto;
	
	private Simulator sim;

	/**
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Before
	public void setUp() throws VehicleException, SimulationException {
		this.carpark = new CarPark(MAX_CAR_SPACES, MAX_SMALL_CAR_SPACES, 
				MAX_MOTORCYCLE_SPACES, MAX_QUEUE_SIZE);
		
		this.car = new Car(VEH_ID, ONE, SMALL);
		
		this.moto = new MotorCycle(VEH_ID, ARRIVAL_TIME);
		
		this.sim = new Simulator();
	}
	
//////////////////////////////////////////////////////////////////////////
// CarPark Tests
//////////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////////
	// archiveDepartingVehicles tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=VehicleException.class)
	public void testDepartingVehicleParkedState() throws VehicleException, SimulationException {		
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.unparkVehicle(car, ARRIVAL_TIME + 10);
		
		carpark.archiveDepartingVehicles(DEPARTURE_TIME, false);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=VehicleException.class)
	public void testDepartingVehicleQueuedState() throws VehicleException, SimulationException {		
		carpark.enterQueue(car);		
		carpark.archiveDepartingVehicles(DEPARTURE_TIME, false);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testDepartingVehicleCorrectState() throws VehicleException, SimulationException {
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.archiveDepartingVehicles(DEPARTURE_TIME, false);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testDepartingVehicleNotInCarpark() throws SimulationException, VehicleException {	
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.unparkVehicle(car, ARRIVAL_TIME + 10);
		
		carpark.archiveDepartingVehicles(DEPARTURE_TIME, false);
	}

	//////////////////////////////////////////////////////////////////////////
	// archiveNewVehicle tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testArchiveQueuedVehicle() throws SimulationException, VehicleException {
		carpark.enterQueue(car);
		carpark.archiveNewVehicle(car);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testArchiveParkedVehicle() throws SimulationException, VehicleException {
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.archiveNewVehicle(car);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testArchiveNewVehicle() throws SimulationException {
		carpark.archiveNewVehicle(car);
	}
	
	//////////////////////////////////////////////////////////////////////////
	// archiveQueueFailures tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=VehicleException.class)
	public void testArchiveQueueFailuresParkedState() throws VehicleException, SimulationException {		
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.archiveQueueFailures(DEPARTURE_TIME + 1);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=VehicleException.class)
	public void testArchiveQueueFailuresUnparkedState() throws VehicleException, SimulationException {		
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.unparkVehicle(car, ARRIVAL_TIME + 10);
		carpark.archiveQueueFailures(DEPARTURE_TIME + 1);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testArchiveQueueFailuresCorrectState() throws VehicleException, SimulationException {
		carpark.enterQueue(car);
		carpark.archiveQueueFailures(DEPARTURE_TIME + 1);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=VehicleException.class)
	public void testArchiveQueueFailuresTimeViolated() throws VehicleException, SimulationException {
		carpark.enterQueue(car);
		carpark.archiveQueueFailures(ARRIVAL_TIME + Constants.MAXIMUM_QUEUE_TIME + 1);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testArchiveQueueFailuresTimingConstraints() throws VehicleException, SimulationException {
		carpark.enterQueue(car);
		carpark.archiveQueueFailures(ARRIVAL_TIME + Constants.MAXIMUM_QUEUE_TIME);
	}
	
	//////////////////////////////////////////////////////////////////////////
	// carParkEmpty tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testCarParkEmptyTrue() throws VehicleException {
		assertTrue(carpark.carParkEmpty());
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testCarParkEmptyFalse() throws VehicleException, SimulationException {
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		assertFalse(carpark.carParkEmpty());
	}
		
	//////////////////////////////////////////////////////////////////////////
	// carParkFull tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testCarParkFullTrue() throws VehicleException, SimulationException {		
		for (int i = 0; i < MAX_CAR_SPACES; i++) {
			Car car = new Car("C" + i, ARRIVAL_TIME + 1, NOT_SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME + 1, INTENDED_DURATION);
		}
		
		assertTrue(carpark.carParkFull());
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testCarParkFullFalse() throws VehicleException, SimulationException {
		for (int i = 0; i < (MAX_CAR_SPACES - 1); i++) {
			Car car = new Car("C" + i, ARRIVAL_TIME + 1, NOT_SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME + 1, INTENDED_DURATION);
		}
		
		assertFalse(carpark.carParkFull());
	}
	
	//////////////////////////////////////////////////////////////////////////
	// enterQueue tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testEnterQueue() throws VehicleException, SimulationException {
		carpark.enterQueue(car);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testEnterQueueFull() throws SimulationException, VehicleException {
		for (int i = 0; i < (MAX_QUEUE_SIZE + 1); i++) {
			Car car = new Car("C" + i, ARRIVAL_TIME + 1, NOT_SMALL);
			carpark.enterQueue(car);
		}
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testEnterQueueQueuedVehicle() throws SimulationException, VehicleException {
		carpark.enterQueue(car);
		carpark.enterQueue(car);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testEnterQueueParkedVehicle() throws SimulationException, VehicleException {
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.enterQueue(car);
	}

	//////////////////////////////////////////////////////////////////////////
	// exitQueue tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testExitQueue() throws VehicleException, SimulationException {
		carpark.enterQueue(car);
		carpark.exitQueue(car, EXIT_TIME);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testExitQueueNotQueuedVehicle() throws SimulationException, VehicleException {
		carpark.exitQueue(car, EXIT_TIME);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=VehicleException.class)
	public void testExitQueueParkedVehicle() throws VehicleException, SimulationException {
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.exitQueue(car, EXIT_TIME);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=VehicleException.class)
	public void testExitQueueTimingConstraints() throws VehicleException, SimulationException {
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.exitQueue(car, (END_CARPARK_DEPARTURE_TIME + 1));
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=VehicleException.class)
	public void testExitQueueExitTimeBeforeArrivalTime() throws VehicleException, SimulationException {
		carpark.parkVehicle(car, 20, INTENDED_DURATION);
		carpark.exitQueue(car, 19);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testExitQueueExitTimeEqualsArrivalTime() throws VehicleException, SimulationException {
		carpark.parkVehicle(car, 20, INTENDED_DURATION);
		carpark.exitQueue(car, 20);
	}

	//////////////////////////////////////////////////////////////////////////
	// getNumCars tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testGetNumCarsZero() throws VehicleException {
		assertEquals(carpark.getNumCars(), 0);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testGetNumCarsMotorbikeOnly() throws VehicleException, SimulationException {
		carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		assertEquals(carpark.getNumCars(), 0);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testGetNumCarsMultiple() throws VehicleException, SimulationException {
		
		// Add two big cars to the carpark
		for (int i = 0; i < 2; i++) {
			Car car = new Car("C" + i, ONE, NOT_SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		// Add two motorcycles to the carpark
		for (int i = 0; i < 2; i++) {
			MotorCycle moto = new MotorCycle("M" + i, ARRIVAL_TIME);
			carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		// Add a small car to the carpark
		carpark.parkVehicle(this.car, ARRIVAL_TIME, INTENDED_DURATION);
		
		assertEquals(carpark.getNumCars(), 3);
	}
	
	//////////////////////////////////////////////////////////////////////////
	// getNumMotorCycles tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testGetNumMotorCyclesZero() throws VehicleException {
		assertEquals(carpark.getNumMotorCycles(), 0);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testGetNumMotorCyclesCarOnly() throws VehicleException, SimulationException {
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		assertEquals(carpark.getNumMotorCycles(), 0);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testGetNumMotorCyclesMultiple() throws VehicleException, SimulationException {
		
		// Add two big cars to the carpark
		for (int i = 0; i < 2; i++) {
			Car car = new Car("C" + i, ONE, NOT_SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		// Add two motorcycles to the carpark
		for (int i = 0; i < 2; i++) {
			MotorCycle moto = new MotorCycle("M" + i, ARRIVAL_TIME);
			carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		// Add a small car to the carpark
		carpark.parkVehicle(this.car, ARRIVAL_TIME, INTENDED_DURATION);
		
		assertEquals(carpark.getNumMotorCycles(), 2);
	}
	
	//////////////////////////////////////////////////////////////////////////
	// getNumSmallCars tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testGetNumSmallCarsZero() throws VehicleException {
		assertEquals(carpark.getNumSmallCars(), 0);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testGetNumSmallCarsBigCarsOnly() throws VehicleException, SimulationException {
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		assertEquals(carpark.getNumSmallCars(), 0);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testGetNumSmallCarsMultiple() throws VehicleException, SimulationException {

		// Add two big cars to the carpark
		for (int i = 0; i < 2; i++) {
			Car car = new Car("C" + i, ONE, NOT_SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		// Add two motorcycles to the carpark
		for (int i = 0; i < 2; i++) {
			MotorCycle moto = new MotorCycle("M" + i, ARRIVAL_TIME);
			carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		// Add a small car to the carpark
		carpark.parkVehicle(this.car, ARRIVAL_TIME, INTENDED_DURATION);
		
		assertEquals(carpark.getNumSmallCars(), 1);
	}
	
	//////////////////////////////////////////////////////////////////////////
	// getNumVehiclesInQueue tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testGetNumVehiclesInQueueZero() throws VehicleException {
		assertEquals(carpark.numVehiclesInQueue(), 0);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testGetNumVehiclesInQueueMultiple() throws VehicleException, SimulationException {
		
		// Add three big cars to the queue
		for (int i = 0; i < 3; i++) {
			Car car = new Car("C" + i, ONE, NOT_SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		// Add two motorcycles to the queue
		for (int i = 0; i < 2; i++) {
			MotorCycle moto = new MotorCycle("M" + i, ARRIVAL_TIME);
			carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		assertEquals(carpark.numVehiclesInQueue(), 5);
	}
	
	//////////////////////////////////////////////////////////////////////////
	// parkVehicle tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testParkVehicleBigCar() throws VehicleException, SimulationException {
		for (int i = 0; i < MAX_CAR_SPACES; i++) {
			Car car = new Car("C" + i, ONE, NOT_SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testParkVehicleSmallCar() throws VehicleException, SimulationException {		
		for (int i = 0; i < (MAX_SMALL_CAR_SPACES + MAX_CAR_SPACES); i++) {
			Car car = new Car("C" + i, ONE, SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testParkVehicleMotorCycle() throws VehicleException, SimulationException {		
		for (int i = 0; i < (MAX_MOTORCYCLE_SPACES + MAX_SMALL_CAR_SPACES); i++) {
			MotorCycle moto = new MotorCycle("M" + i, ARRIVAL_TIME);
			carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		}
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testParkVehicleBigCarNoSpaces() throws SimulationException, VehicleException {		
		for (int i = 0; i < (MAX_CAR_SPACES + 1); i++) {
			Car car = new Car("C" + i, ONE, NOT_SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testParkVehicleSmallCarNoSpaces() throws SimulationException, VehicleException {		
		for (int i = 0; i < (MAX_SMALL_CAR_SPACES + MAX_CAR_SPACES + 1); i++) {
			Car car = new Car("C" + i, ONE, SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testParkVehicleMotorCycleNoSpaces() throws SimulationException, VehicleException {		
		for (int i = 0; i < (MAX_MOTORCYCLE_SPACES + MAX_SMALL_CAR_SPACES + 1); i++) {
			MotorCycle moto = new MotorCycle("M" + i, ARRIVAL_TIME);
			carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		}
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=SimulationException.class)
	public void testParkVehicleMultipleVehicles() throws SimulationException {
		// TODO
		// SET MAX SPACES TO SOMETHING MANAGEABLE
		// ADD CARS, SMALL CARS AND MOTORCYCLES MANUALLY
		// ENSURE SPACES FILL APPROPRIATELY
		// AND THAT SIMULATION EXCEPTION THROWN CORRECTLY
		//		
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=VehicleException.class)
	public void testParkVehicleAlreadyParked() throws VehicleException, SimulationException {
		carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
	}

	//////////////////////////////////////////////////////////////////////////
	// processQueue tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testProcessQueue() throws VehicleException, SimulationException {
		carpark.enterQueue(car);
		carpark.processQueue(ARRIVAL_TIME, sim);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testProcessQueueBigCarNoSpaces() throws SimulationException, VehicleException {
		for (int i = 0; i < (MAX_CAR_SPACES); i++) {
			Car car = new Car("C" + i, ONE, NOT_SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		carpark.enterQueue(car);
		carpark.processQueue(ARRIVAL_TIME, sim);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testProcessQueueSmallCarNoSpaces() throws SimulationException, VehicleException {
		for (int i = 0; i < (MAX_SMALL_CAR_SPACES + MAX_CAR_SPACES); i++) {
			Car car = new Car("C" + i, ONE, SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		carpark.enterQueue(car);
		carpark.processQueue(ARRIVAL_TIME, sim);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testProcessQueueMotorCycleNoSpaces() throws SimulationException, VehicleException {	
		for (int i = 0; i < (MAX_MOTORCYCLE_SPACES + MAX_SMALL_CAR_SPACES); i++) {
			MotorCycle moto = new MotorCycle("M" + i, ARRIVAL_TIME);
			carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		carpark.enterQueue(moto);
		carpark.processQueue(ARRIVAL_TIME, sim);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=SimulationException.class)
	public void testProcessQueueMultipleVehicles() throws SimulationException {
		// TODO:
		// SET MAX SPACES TO SOMETHING MANAGEABLE
		// ADD CARS, SMALL CARS AND MOTORCYCLES MANUALLY
		// ENSURE SPACES FILL APPROPRIATELY
		// AND THAT SIMULATION EXCEPTION THROWN CORRECTLY
		//		
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testProcessQueueCorrectState() throws VehicleException, SimulationException {
		carpark.enterQueue(car);
		carpark.processQueue(ARRIVAL_TIME, sim);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=VehicleException.class)
	public void testProcessQueueNewState() throws VehicleException, SimulationException {
		this.car = new Car(VEH_ID, ONE, SMALL);
		carpark.processQueue(ARRIVAL_TIME, sim);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=VehicleException.class)
	public void testProcessQueueParkedState() throws VehicleException, SimulationException {
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.processQueue(ARRIVAL_TIME, sim);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=VehicleException.class)
	public void testProcessQueueArchivedState() throws VehicleException, SimulationException {
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.unparkVehicle(car, DEPARTURE_TIME);
		
		carpark.processQueue(ARRIVAL_TIME, sim);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=VehicleException.class)
	public void testProcessQueueTimeConstraint() throws VehicleException, SimulationException {
		this.car = new Car(VEH_ID, 20, SMALL);
		carpark.enterQueue(car);		
		carpark.processQueue(10, sim);
	}
	
	//////////////////////////////////////////////////////////////////////////
	// queueEmpty tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testQueueEmptyTrue() throws VehicleException {
		assertTrue(carpark.queueEmpty());
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=VehicleException.class)
	public void testQueueEmptyFalse() throws VehicleException, SimulationException {
		carpark.enterQueue(car);
		assertFalse(carpark.queueEmpty());
	}
	
	//////////////////////////////////////////////////////////////////////////
	// queueFull tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testQueueFullTrue() throws VehicleException, SimulationException {
		for (int i = 0; i < MAX_QUEUE_SIZE; i++) {
			Car car = new Car("C" + i, ONE, SMALL);
			carpark.enterQueue(car);
		}
		assertTrue(carpark.queueFull());
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=VehicleException.class)
	public void testQueueFullFalse() throws VehicleException, SimulationException {
		carpark.enterQueue(car);
		assertFalse(carpark.queueFull());
	}
	
	//////////////////////////////////////////////////////////////////////////
	// spacesAvailable tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testSpacesAvailableTrueBigCar() throws VehicleException, SimulationException {
		this.car = new Car(VEH_ID, ONE, NOT_SMALL);
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		assertTrue(carpark.spacesAvailable(car));
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testSpacesAvailableFalseBigCar() throws VehicleException, SimulationException {
		for (int i = 0; i < MAX_CAR_SPACES; i++) {
			Car car = new Car("C" + i, ONE, NOT_SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
		assertFalse(carpark.spacesAvailable(car));
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testSpacesAvailableTrueSmallCar() throws VehicleException, SimulationException {
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		assertTrue(carpark.spacesAvailable(car));
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testSpacesAvailableFalseSmallCar() throws VehicleException, SimulationException {
		for (int i = 0; i < (MAX_SMALL_CAR_SPACES + MAX_CAR_SPACES); i++) {
			Car car = new Car("C" + i, ONE, SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
		assertFalse(carpark.spacesAvailable(car));
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testSpacesAvailableTrueMotorcycle() throws VehicleException, SimulationException {
		carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		assertTrue(carpark.spacesAvailable(moto));
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testSpacesAvailableFalseMotorcycle() throws VehicleException, SimulationException {
		for (int i = 0; i < (MAX_MOTORCYCLE_SPACES + MAX_SMALL_CAR_SPACES); i++) {
			MotorCycle moto = new MotorCycle("M" + i, ARRIVAL_TIME);
			carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		}
		assertFalse(carpark.spacesAvailable(moto));
	}
	
	//////////////////////////////////////////////////////////////////////////
	// tryProcessNewVehicle tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testTryProcessNewVehiclesBigCar() throws SimulationException, VehicleException {	
		for (int i = 0; i < MAX_CAR_SPACES; i++) {
			Car car = new Car("C" + i, ONE, NOT_SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
		carpark.tryProcessNewVehicles(ARRIVAL_TIME, sim);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testTryProcessNewVehiclesSmallCar() throws SimulationException, VehicleException {
		for (int i = 0; i < (MAX_SMALL_CAR_SPACES + MAX_CAR_SPACES); i++) {
			Car car = new Car("C" + i, ONE, SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
		carpark.tryProcessNewVehicles(ARRIVAL_TIME, sim);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testTryProcessNewVehiclesMotorcycle() throws SimulationException, VehicleException {
		for (int i = 0; i < (MAX_MOTORCYCLE_SPACES + MAX_SMALL_CAR_SPACES); i++) {
			MotorCycle moto = new MotorCycle("M" + i, ARRIVAL_TIME);
			carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		}
		carpark.tryProcessNewVehicles(ARRIVAL_TIME, sim);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=VehicleException.class)
	public void testTryProcessNewVehiclesVehicleCreationConstraints() throws VehicleException, SimulationException {
		//TODO
		// Vehicle creation constraints
		//
		carpark.tryProcessNewVehicles(ARRIVAL_TIME, sim);
	}
	
	//////////////////////////////////////////////////////////////////////////
	// unparkVehicle tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testUnparkVehicle() throws VehicleException, SimulationException {
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.unparkVehicle(car, DEPARTURE_TIME);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=VehicleException.class)
	public void testUnparkVehicleNotParked() throws VehicleException, SimulationException {
		carpark.unparkVehicle(car, DEPARTURE_TIME);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=VehicleException.class)
	public void testUnparkVehicleQueued() throws VehicleException, SimulationException {
		carpark.enterQueue(car);
		carpark.unparkVehicle(car, DEPARTURE_TIME);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=VehicleException.class)
	public void testUnparkVehicleTimingConstraints() throws VehicleException, SimulationException {
		carpark.parkVehicle(car, 20, INTENDED_DURATION);
		carpark.unparkVehicle(car, 10);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testUnparkVehicleNotInCarpark() throws SimulationException, VehicleException {
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.unparkVehicle(car, DEPARTURE_TIME);
		carpark.unparkVehicle(car, DEPARTURE_TIME);
	}

}
