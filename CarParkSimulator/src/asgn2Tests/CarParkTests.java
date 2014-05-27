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
		
		this.car = new Car(VEH_ID, ARRIVAL_TIME, SMALL);
		
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
	 * Test method for archiveDepartingVehicle with an unparked car
	 * to make sure it's not picked up
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testDepartingVehicleUnparkedState() throws VehicleException, SimulationException {		
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.unparkVehicle(car, ARRIVAL_TIME + 10);
		
		carpark.archiveDepartingVehicles(DEPARTURE_TIME, false);
	}
	
	/**
	 * Test method for archiveDepartingVehicle with a queued vehicle
	 * to make sure it's not picked up
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testDepartingVehicleQueuedState() throws VehicleException, SimulationException {		
		carpark.enterQueue(car);		
		carpark.archiveDepartingVehicles(DEPARTURE_TIME, false);
	}
	
	/**
	 * Test method for archiveDepartingVehicle with a car in the correct state,
	 * ie parked
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testDepartingVehicleCorrectState() throws VehicleException, SimulationException {
				carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		
		int numCars = carpark.getNumCars();
		carpark.archiveDepartingVehicles(DEPARTURE_TIME, false);
		
		// Check that the vehicle has been archived
		assertEquals(numCars - 1, carpark.getNumCars());
	}
	
	/**
	 * Test method for archiveDepartingVehicle with a car 
	 * that is not in the carpark to make sure it's not picked up
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test
	public void testDepartingVehicleNotInCarpark() throws SimulationException, VehicleException {	
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.unparkVehicle(car, ARRIVAL_TIME + 10);
		
		carpark.archiveDepartingVehicles(DEPARTURE_TIME, false);
	}

	//////////////////////////////////////////////////////////////////////////
	// archiveNewVehicle tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for archiveNewVehicle with a queued vehicle
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testArchiveQueuedVehicle() throws SimulationException, VehicleException {
		carpark.enterQueue(car);
		carpark.archiveNewVehicle(car);
	}
	
	/**
	 * Test method for archiveNewVehicle with a parked car
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testArchiveParkedVehicle() throws SimulationException, VehicleException {
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.archiveNewVehicle(car);
	}
	
	/**
	 * Test method for archiveNewVehicle using a new vehicle, 
	 * ie in the correct state
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
	 * Test method for archiveQueueFailures with a parked car
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=VehicleException.class)
	public void testArchiveQueueFailuresParkedState() throws VehicleException, SimulationException {		
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.archiveQueueFailures(DEPARTURE_TIME + 1);
	}
	
	/**
	 * Test method for archiveQueueFailures with an unparked car
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testArchiveQueueFailuresUnparkedState() throws VehicleException, SimulationException {		
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.unparkVehicle(car, ARRIVAL_TIME + 10);
		
		carpark.archiveQueueFailures(DEPARTURE_TIME + 1);
	}
	
	/**
	 * Test method for archiveQueueFailures with a car in the correct state,
	 * ie queued
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testArchiveQueueFailuresCorrectState() throws VehicleException, SimulationException {
		carpark.enterQueue(car);
		carpark.archiveQueueFailures(DEPARTURE_TIME + 1);
	}
	
	/**
	 * Test method for archiveQueueFailures when the car has been waiting
	 * longer than the maximum queue time
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=VehicleException.class)
	public void testArchiveQueueFailuresTimeViolated() throws VehicleException, SimulationException {
		carpark.enterQueue(car);
		carpark.archiveQueueFailures(ARRIVAL_TIME + Constants.MAXIMUM_QUEUE_TIME + 1);
	}
	
	/**
	 * Test method for archiveQueueFailures when the maximum queue time
	 * has been reached
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
	 * Test method for carParkEmpty when car park is empty
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testCarParkEmptyTrue() throws VehicleException {
		assertTrue(carpark.carParkEmpty());
	}
	
	/**
	 * Test method for carParkEmpty when carpark is not empty
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
	 * Test method for carParkFull when carpark is full
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testCarParkFullTrue() throws VehicleException, SimulationException {	
		
		// Fill all car spaces with small cars
		for (int i = 0; i < MAX_CAR_SPACES; i++) {
			Car car = new Car("C" + i, ARRIVAL_TIME + 1, SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME + 1, INTENDED_DURATION);
		}
		
		// Fill all motorcycle spaces with motorcycles
		for (int i = 0; i < MAX_MOTORCYCLE_SPACES; i++) {
			MotorCycle moto = new MotorCycle(VEH_ID, ARRIVAL_TIME);
			carpark.parkVehicle(moto, ARRIVAL_TIME + 1, INTENDED_DURATION);
		}
		
		// Assert that the carpark is full
		assertTrue(carpark.carParkFull());
	}
	
	/**
	 * Test method for carParkFull when carpark is not full
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testCarParkFullFalse() throws VehicleException, SimulationException {
		
		// Fil all but one car spaces with small cars
		for (int i = 0; i < (MAX_CAR_SPACES - MAX_SMALL_CAR_SPACES - 1); i++) {
			Car car = new Car("C" + i, ARRIVAL_TIME, NOT_SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME + 1, INTENDED_DURATION);
		}
		
		// Fill all motorcycle spaces with motorcycles
		for (int i = 0; i < MAX_MOTORCYCLE_SPACES; i++) {
			MotorCycle moto = new MotorCycle("MC" + i, ARRIVAL_TIME);
			carpark.parkVehicle(moto, ARRIVAL_TIME + 1, INTENDED_DURATION);
		}
		
		// Assert that the carpark is not full
		assertFalse(carpark.carParkFull());
	}
	
	//////////////////////////////////////////////////////////////////////////
	// enterQueue tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for enterQueue with a new car when the queue is empty
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testEnterQueue() throws VehicleException, SimulationException {
		carpark.enterQueue(car);
	}
	
	/**
	 * Test method for enterQueue with a new car when the queue is full
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testEnterQueueFull() throws SimulationException, VehicleException {
		
		// Attempt to overfill the queue
		for (int i = 0; i < (MAX_QUEUE_SIZE + 1); i++) {
			Car car = new Car("C" + i, ARRIVAL_TIME + 1, NOT_SMALL);
			carpark.enterQueue(car);
		}
	}
	
	/**
	 * Test method for enterQueue with a car that is already queued
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=VehicleException.class)
	public void testEnterQueueQueuedVehicle() throws SimulationException, VehicleException {
		carpark.enterQueue(car);
		carpark.enterQueue(car);
	}
	
	/**
	 * Test method for enterQueue with a parked car
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
	 * Test method for exitQueue with a car in the correct state
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testExitQueue() throws VehicleException, SimulationException {
		carpark.enterQueue(car);
		carpark.exitQueue(car, EXIT_TIME);
	}
	
	/**
	 * Test method for exitQueue with a car that is not in the correct state,
	 * ie it has not been queued
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testExitQueueNotQueuedVehicle() throws SimulationException, VehicleException {
		carpark.exitQueue(car, EXIT_TIME);
	}
	
	/**
	 * Test method for exitQueue with a parked car
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=SimulationException.class)
	public void testExitQueueParkedVehicle() throws VehicleException, SimulationException {
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.exitQueue(car, EXIT_TIME);
	}
	
	/**
	 * Test method for exitQueue when the time is after the end carpark time
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testExitQueueTimingConstraints() throws VehicleException, SimulationException {
		carpark.enterQueue(car);
		carpark.exitQueue(car, END_CARPARK_DEPARTURE_TIME + 1);
	}
	
	/**
	 * Test method for exitQueue with a time that is before the arrival time
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=VehicleException.class)
	public void testExitQueueExitTimeBeforeArrivalTime() throws VehicleException, SimulationException {
		carpark.enterQueue(car);
		carpark.exitQueue(car, 19);
	}
	
	/**
	 * Test method for exitQueue with a time that is the same as the arrival time
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testExitQueueExitTimeEqualsArrivalTime() throws VehicleException, SimulationException {
		carpark.enterQueue(car);
		carpark.exitQueue(car, ARRIVAL_TIME - 1);
	}

	//////////////////////////////////////////////////////////////////////////
	// getNumCars tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for getNumCars when the carpark is empty
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testGetNumCarsZero() throws VehicleException {
		assertEquals(carpark.getNumCars(), 0);
	}
	
	/**
	 * Test method for getNumCars when there are no cars and
	 * only one motorcycle in the carpark
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testGetNumCarsMotorbikeOnly() throws VehicleException, SimulationException {
		carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		assertEquals(carpark.getNumCars(), 0);
	}
	
	/**
	 * Test method for getNumCars when there are multiple types of vehicles
	 * in the carpark
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
		
		// Add one small car to the carpark
		carpark.parkVehicle(this.car, ARRIVAL_TIME, INTENDED_DURATION);
		
		assertEquals(carpark.getNumCars(), 3);
	}
	
	//////////////////////////////////////////////////////////////////////////
	// getNumMotorCycles tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for getNumMotorCycles when the carpark is empty
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testGetNumMotorCyclesZero() throws VehicleException {
		assertEquals(carpark.getNumMotorCycles(), 0);
	}
	
	/**
	 * Test method for getNumMotorCycles when there are no motorcycles 
	 * and only one car in the carpark
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testGetNumMotorCyclesCarOnly() throws VehicleException, SimulationException {
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		assertEquals(carpark.getNumMotorCycles(), 0);
	}
	
	/**
	 * Test method for getNumMotorCycles when there are multiple types
	 * of vehicles in the carpark
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
		
		// Add one small car to the carpark
		carpark.parkVehicle(this.car, ARRIVAL_TIME, INTENDED_DURATION);
		
		assertEquals(carpark.getNumMotorCycles(), 2);
	}
	
	//////////////////////////////////////////////////////////////////////////
	// getNumSmallCars tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for getNumSmallCars when the carpark is empty
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testGetNumSmallCarsZero() throws VehicleException {
		assertEquals(carpark.getNumSmallCars(), 0);
	}
	
	/**
	 * Test method for getNumSmallCars when there is only one big car
	 * and no small cars in the carpark
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testGetNumSmallCarsBigCarsOnly() throws VehicleException, SimulationException {
		Car car = new Car(VEH_ID, ONE, NOT_SMALL);
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		
		assertEquals(carpark.getNumSmallCars(), 0);
	}
	
	/**
	 * Test method for getNumSmallCars when there are multiples types
	 * of vehicles in the carpark
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
		
		// Add one small car to the carpark
		carpark.parkVehicle(this.car, ARRIVAL_TIME, INTENDED_DURATION);
		
		assertEquals(carpark.getNumSmallCars(), 1);
	}
	
	//////////////////////////////////////////////////////////////////////////
	// getNumVehiclesInQueue tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for getNumVehiclesInQueue when the queue is empty
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testGetNumVehiclesInQueueZero() throws VehicleException {
		assertEquals(carpark.numVehiclesInQueue(), 0);
	}
	
	/**
	 * Test method for getNumVehiclesInQueue when there are multiple
	 * types of vehicles in the queue
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testGetNumVehiclesInQueueMultiple() throws VehicleException, SimulationException {
		
		// Add three big cars to the queue
		for (int i = 0; i < 3; i++) {
			Car car = new Car("C" + i, ONE, NOT_SMALL);
			carpark.enterQueue(car);
		}
		
		// Add two motorcycles to the queue
		for (int i = 0; i < 2; i++) {
			MotorCycle moto = new MotorCycle("M" + i, ARRIVAL_TIME);
			carpark.enterQueue(moto);
		}
		
		assertEquals(5, carpark.numVehiclesInQueue());
	}
	
	//////////////////////////////////////////////////////////////////////////
	// parkVehicle tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for parkVehicle with a big car
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testParkVehicleBigCar() throws VehicleException, SimulationException {
		
		// Fill all big car spaces with big cars
		for (int i = 0; i < (MAX_CAR_SPACES - MAX_SMALL_CAR_SPACES); i++) {
			Car car = new Car("C" + i, ONE, NOT_SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
	}
	
	/**
	 * Test method for parkVehicle with a small car
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testParkVehicleSmallCar() throws VehicleException, SimulationException {
		
		// Fill all car spaces (big and small) will small cars
		for (int i = 0; i < MAX_CAR_SPACES; i++) {
			Car car = new Car("C" + i, ARRIVAL_TIME, SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
	}
	
	/**
	 * Test method for parkVehicle with a motorcycle
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testParkVehicleMotorCycle() throws VehicleException, SimulationException {
		
		// Fill all motorcycle and small car spaces with motorcycles
		for (int i = 0; i < (MAX_MOTORCYCLE_SPACES + MAX_SMALL_CAR_SPACES); i++) {
			MotorCycle moto = new MotorCycle("M" + i, ARRIVAL_TIME);
			carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		}
	}
	
	/**
	 * Test method for parkVehicle with a big car when there are no spaces
	 * for a big car
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testParkVehicleBigCarNoSpaces() throws SimulationException, VehicleException {
		
		// Attempt to overfill the big car spaces
		for (int i = 0; i < (MAX_CAR_SPACES - MAX_SMALL_CAR_SPACES + 1); i++) {
			Car car = new Car("C" + i, ONE, NOT_SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
	}
	
	/**
	 * Test method for parkVehicle for a small car when there are no spaces
	 * for a small car
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testParkVehicleSmallCarNoSpaces() throws SimulationException, VehicleException {
		
		// Attempt to overfill the car spaces with small cars
		for (int i = 0; i < (MAX_CAR_SPACES + 1); i++) {
			Car car = new Car("C" + i, ONE, SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
	}
	
	/**
	 * Test method for parkVehicle for a motorcycle when there are no spaces
	 * for a motorcycle
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testParkVehicleMotorCycleNoSpaces() throws SimulationException, VehicleException {
		
		// Attempt to overfil the motorcycle and small car spaces with mtoorcycles
		for (int i = 0; i < (MAX_MOTORCYCLE_SPACES + MAX_SMALL_CAR_SPACES + 1); i++) {
			MotorCycle moto = new MotorCycle("M" + i, ARRIVAL_TIME);
			carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		}
	}
	
	/**
	 * Test method for parkVehicle when there are multiple types of vehicles
	 * and no suitable spaces are available
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testParkVehicleMultipleVehicles() throws SimulationException, VehicleException {
		
		// Fill all car spaces with small cars
		for (int i = 0; i < Constants.DEFAULT_MAX_CAR_SPACES; i++) {
			Car car = new Car("C" + i, ONE, SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		// Fill all motorcycle spaces with motorcycles
		for (int i = 0; i < Constants.DEFAULT_MAX_MOTORCYCLE_SPACES; i++) {
			this.moto = new MotorCycle("M" + i, ARRIVAL_TIME);
			carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		// Attempt to park another car
		carpark.parkVehicle(this.car, ARRIVAL_TIME, INTENDED_DURATION);

	}
	
	/**
	 * Test method for parkVehicle when all small car spaces are full
	 * and another small car attempts to park
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws VehicleException 
	 */
	@Test
	public void testParkVehicleSmallCarsFull() throws SimulationException, VehicleException {
		
		// Fill all small car spaces with small cars
		for (int i = 0; i < Constants.DEFAULT_MAX_SMALL_CAR_SPACES; i++) {
			Car car = new Car("C" + i, ONE, SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		// Attempt to park another small car
		carpark.parkVehicle(this.car, ARRIVAL_TIME, INTENDED_DURATION);

	}
	
	/**
	 * Test method for parkVehicle when all motorcycle spaces are full
	 * and another motorcycle attempts to park
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws VehicleException 
	 */
	@Test
	public void testParkVehicleMotorcyclesFull() throws SimulationException, VehicleException {
		
		// Fill all motorcycle spaces with motorcycles
		for (int i = 0; i < Constants.DEFAULT_MAX_MOTORCYCLE_SPACES; i++) {
			this.moto = new MotorCycle("M" + i, ARRIVAL_TIME);
			carpark.parkVehicle(this.moto, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		// Attempt to park another motorcycle
		this.moto = new MotorCycle("M123", ARRIVAL_TIME);
		carpark.parkVehicle(this.moto, ARRIVAL_TIME, INTENDED_DURATION);
	}
	
	/**
	 * Test method for parkVehicle when the same car attempts to park twice
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
	 * Test method for processQueue when a car is in the queue
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testProcessQueue() throws VehicleException, SimulationException {
		carpark.enterQueue(car);
		carpark.processQueue(ARRIVAL_TIME + 1, sim);
	}
	
	/**
	 * Test method for processQueue when the next car is a big car and
	 * all big car spaces are full
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testProcessQueueBigCarNoSpaces() throws SimulationException, VehicleException {
		
		// Fill all big car spaces with big cars
		for (int i = 0; i < (MAX_CAR_SPACES - MAX_SMALL_CAR_SPACES); i++) {
			Car car = new Car("C" + i, ONE, NOT_SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		// Attempt to process the queue when the next car is a big car
		Car car = new Car("C123", ONE, NOT_SMALL);
		carpark.enterQueue(car);
		carpark.processQueue(ARRIVAL_TIME, sim);
	}
	
	/**
	 * Test method for processQueue when the next car is a small car and
	 * all car spaces are full
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testProcessQueueSmallCarNoSpaces() throws SimulationException, VehicleException {
		
		// Fill all car spaces with small cars
		for (int i = 0; i < (MAX_CAR_SPACES); i++) {
			Car car = new Car("C" + i, ONE, SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		// Attempt to proess the queue when the next car is a small car
		carpark.enterQueue(car);
		carpark.processQueue(ARRIVAL_TIME, sim);
	}
	
	/**
	 * Test method for processQueue when the next vehicle is a motorcycle
	 * and all motorcycle spaces but small cars are not
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test
	public void testProcessQueueMCFull() throws SimulationException, VehicleException {	
		
		// Fill all motorcycle and small car spaces with motorcycles
		for (int i = 0; i < (MAX_MOTORCYCLE_SPACES + 1); i++) {
			MotorCycle moto = new MotorCycle("M" + i, ARRIVAL_TIME);
			carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		// Attempt to process the queue when the next vehicle is a motorcycle
		carpark.enterQueue(moto);
		carpark.processQueue(ARRIVAL_TIME + 1, sim);
	}
	
	/**
	 * Test method for processQueue when the next vehicle is a motorcycle
	 * and all motorcycle spaces and small car spaces are full
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testProcessQueueMCFullSCFull() throws SimulationException, VehicleException {	
		
		// Fill all motorcycle and small car spaces with motorcycles
		for (int i = 0; i < (MAX_MOTORCYCLE_SPACES + MAX_SMALL_CAR_SPACES + 1); i++) {
			MotorCycle moto = new MotorCycle("M" + i, ARRIVAL_TIME);
			carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		// Attempt to process the queue when the next vehicle is a motorcycle
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
	 * Test method for processQueue with a car in the correct state,
	 * ie queued
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testProcessQueueCorrectState() throws VehicleException, SimulationException {
		carpark.enterQueue(car);
		carpark.processQueue(ARRIVAL_TIME + 1, sim);
	}
	
	/**
	 * Test method for processQueue with a new car to make sure it's not picked up
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testProcessQueueNewState() throws VehicleException, SimulationException {
		this.car = new Car(VEH_ID, ONE, SMALL);
		carpark.processQueue(ARRIVAL_TIME + 1, sim);
	}
	
	/**
	 * Test method for processQueue with a parked car to make sure it's not picked up
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testProcessQueueParkedState() throws VehicleException, SimulationException {
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.processQueue(ARRIVAL_TIME + 1, sim);
	}
	
	/**
	 * Test method for processQueue with an archived car to make sure it's not picked up
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testProcessQueueArchivedState() throws VehicleException, SimulationException {
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.unparkVehicle(car, DEPARTURE_TIME);
				
		carpark.processQueue(DEPARTURE_TIME, sim);
	}
	
	/**
	 * Test method for processQueue when the time is before the arrival time
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
	 * Test method for queueEmpty when the queue is empty
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testQueueEmptyTrue() throws VehicleException {
		assertTrue(carpark.queueEmpty());
	}
	
	/**
	 * Test method for queueEmpty when the queue is not empty
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
	 * Test method for queueFull when the queue is full
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testQueueFullTrue() throws VehicleException, SimulationException {
		
		// Fill the queue with small cars
		for (int i = 0; i < MAX_QUEUE_SIZE; i++) {
			Car car = new Car("C" + i, ONE, SMALL);
			carpark.enterQueue(car);
		}
		assertTrue(carpark.queueFull());
	}
	
	/**
	 * Test method for queueFull when the queue is not full
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testQueueFullFalse() throws VehicleException, SimulationException {
		carpark.enterQueue(car);
		assertFalse(carpark.queueFull());
	}
	
	//////////////////////////////////////////////////////////////////////////
	// spacesAvailable tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for spacesAvailable with a big car when there are 
	 * spaces available for a big car
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
	 * Test method for spacesAvailable with  big car when there are
	 * no spaces available for a big car
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testSpacesAvailableFalseBigCar() throws VehicleException, SimulationException {
		
		// Fill the big car spaces with big cars
		for (int i = 0; i < (MAX_CAR_SPACES - MAX_SMALL_CAR_SPACES); i++) {
			Car car = new Car("C" + i, ONE, NOT_SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		Car car = new Car("C", ONE, NOT_SMALL);
		assertFalse(carpark.spacesAvailable(car));
	}
	
	/**
	 * Test method for spacesAvailable wih a small car when there are
	 * spaces available for a small car
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testSpacesAvailableTrueSmallCar() throws VehicleException, SimulationException {
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		assertTrue(carpark.spacesAvailable(car));
	}
	
	/**
	 * Test method for spacesAvailable wth a small car when all
	 * small car spaces are taken, but there are still big car
	 * spaces left
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testSpacesAvailableTrueSmallCar2() throws VehicleException, SimulationException {
		
		// Overfill the small car spaces
		for (int i = 0; i < (MAX_SMALL_CAR_SPACES + 3); i++) {
			Car car = new Car("C" + i, ONE, SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		assertTrue(carpark.spacesAvailable(car));
	}
	
	/**
	 * Test method for spacesAvailable wth a small car when there are
	 * no spaces available for a small car,
	 * ie all small car and big car spaces are taken
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testSpacesAvailableFalseSmallCar() throws VehicleException, SimulationException {
		
		// Fill all car spaces with small cars
		for (int i = 0; i < (MAX_CAR_SPACES); i++) {
			Car car = new Car("C" + i, ONE, SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		assertFalse(carpark.spacesAvailable(car));
	}
	
	/**
	 * Test method for spacesAvailable with a small car when the
	 * small cars have overflowed into the big car spaces, and the
	 * remaining big car spaces are flled with big cars
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testSpacesAvailableFalseSmallCar2() throws VehicleException, SimulationException {
		
		// Overfill the small car spaces
		for (int i = 0; i < (MAX_SMALL_CAR_SPACES + 2); i++) {
			Car car = new Car("C" + i, ONE, SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		// Fill the remaining car spaces with big cars
		for (int i = 0; i < (MAX_CAR_SPACES - MAX_SMALL_CAR_SPACES - 2); i++) {
			Car car = new Car("C" + i, ONE, NOT_SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		// Assert that there are no spaces available for small cars
		Car car = new Car("C123", ONE, SMALL);
		assertFalse(carpark.spacesAvailable(car));
	}
	
	/**
	 * Test method for spacesAvailable with a motorcycle when there are
	 * spaces available for a motorcycle
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testSpacesAvailableTrueMotorcycle() throws VehicleException, SimulationException {
		carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		assertTrue(carpark.spacesAvailable(moto));
	}
	
	/**
	 * Test method for spacesAvailable wth a motorcycle when all
	 * motorcycle spaces are taken, but there are still small car
	 * spaces left
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testSpacesAvailableTrueMotorcycle2() throws VehicleException, SimulationException {
		
		// Overfill the motorcycle spaces
		for (int i = 0; i < (MAX_MOTORCYCLE_SPACES + 3); i++) {
			MotorCycle moto = new MotorCycle("M" + i, ARRIVAL_TIME);
			carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		assertTrue(carpark.spacesAvailable(moto));
	}
	
	/**
	 * Test method for spacesAvailable with a motorcycle when there are
	 * no spaces available available for a motorcycle,
	 * ie all motorcycle and small car spaces are taken
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testSpacesAvailableFalseMotorcycle() throws VehicleException, SimulationException {
		
		// Fill all motorcycle and small car spaces with motorcycles
		for (int i = 0; i < (MAX_MOTORCYCLE_SPACES + MAX_SMALL_CAR_SPACES); i++) {
			MotorCycle moto = new MotorCycle("M" + i, ARRIVAL_TIME);
			carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		assertFalse(carpark.spacesAvailable(moto));
	}
	
	/**
	 * Test method for spacesAvailable with a motorcycle when the
	 * motorcycles have overflowed into the small car spaces, and the
	 * remaining small car spaces are flled with small cars
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testSpacesAvailableFalseMotorcycle2() throws VehicleException, SimulationException {
		
		// Overfill the motorcycle spaces
		for (int i = 0; i < (MAX_MOTORCYCLE_SPACES + 2); i++) {
			MotorCycle moto = new MotorCycle("M" + i, ARRIVAL_TIME);
			carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		// Fill the remaining small car spaces, and overflow into
		// the big car spaces
		for (int i = 0; i < (MAX_SMALL_CAR_SPACES); i++) {
			Car car = new Car("C" + i, ONE, SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		// Assert that there are no spaces available for motorcycles
		assertFalse(carpark.spacesAvailable(moto));
	}
	
	//////////////////////////////////////////////////////////////////////////
	// tryProcessNewVehicle tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for tryProcessNewVehicle with a big car when there are
	 * o spaces available for a big car
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testTryProcessNewVehiclesBigCar() throws SimulationException, VehicleException {	
		
		// Fill the big car spaces with big cars
		for (int i = 0; i < (MAX_CAR_SPACES - MAX_SMALL_CAR_SPACES); i++) {
			Car car = new Car("C" + i, ONE, NOT_SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		carpark.tryProcessNewVehicles(ARRIVAL_TIME, sim);
	}
	
	/**
	 * Test method for tryProcessNewVehicle with a small car when there are
	 * no spaces available for a small car,
	 * ie all small car and big car spaces are taken
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testTryProcessNewVehiclesSmallCar() throws SimulationException, VehicleException {
		
		// Fill all car spaces with small cars
		for (int i = 0; i < (MAX_CAR_SPACES); i++) {
			Car car = new Car("C" + i, ONE, SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		carpark.tryProcessNewVehicles(ARRIVAL_TIME, sim);
	}
	
	/**
	 * Test method for tryProcessNewVehicle with a motorcycle when there are
	 * no spaces available for a mototcycle,
	 * ie all motorcycle and small car spaces are taken
	 * @throws asgn2.Exceptions.SimulationException
	 * @throws asgn2.Exceptions.VehicleException 
	 */
	@Test(expected=SimulationException.class)
	public void testTryProcessNewVehiclesMotorcycle() throws SimulationException, VehicleException {
		
		// Fill all motorcycle and small car spaces with motorcycles
		for (int i = 0; i < (MAX_MOTORCYCLE_SPACES + MAX_SMALL_CAR_SPACES); i++) {
			MotorCycle moto = new MotorCycle("M" + i, ARRIVAL_TIME);
			carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		carpark.tryProcessNewVehicles(ARRIVAL_TIME, sim);
	}
	
	//////////////////////////////////////////////////////////////////////////
	// unparkVehicle tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for unparkVehicle with a vehicle in the correct state,
	 * ie parked
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testUnparkVehicle() throws VehicleException, SimulationException {
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.unparkVehicle(car, DEPARTURE_TIME);
	}
	
	/**
	 * Test method for unparkVehicle with a vehicle that has not been parked
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=VehicleException.class)
	public void testUnparkVehicleNotParked() throws VehicleException, SimulationException {
		carpark.unparkVehicle(car, DEPARTURE_TIME);
	}
	
	/**
	 * Test method for unparkVehicle with a queued vehicle
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=VehicleException.class)
	public void testUnparkVehicleQueued() throws VehicleException, SimulationException {
		carpark.enterQueue(car);
		carpark.unparkVehicle(car, DEPARTURE_TIME);
	}
	
	/**
	 * Test method for unparkVehicle when the time is before the arrival time
	 * @throws asgn2.Exceptions.VehicleException
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=VehicleException.class)
	public void testUnparkVehicleTimingConstraints() throws VehicleException, SimulationException {
		carpark.parkVehicle(car, 20, INTENDED_DURATION);
		carpark.unparkVehicle(car, 10);
	}
	
	/**
	 * Test method for unparkVehicle with a vehicle that has already been unparked
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
