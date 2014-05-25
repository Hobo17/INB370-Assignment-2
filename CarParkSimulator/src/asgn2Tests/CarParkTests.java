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

import asgn2Exceptions.VehicleException;
import asgn2Exceptions.SimulationException;
import asgn2Simulators.Constants;
import asgn2Vehicles.MotorCycle;
import asgn2Vehicles.Car;

/**
 * @author Bec
 *
 */
public class CarParkTests {
	
	private final int MAX_CAR_SPACES = 100;
	private final int MAX_SMALL_CAR_SPACES = 30;
	private final int MAX_MOTORCYCLES_SPACES = 20;
	private final int MAX_QUEUE_SIZE = 10;
	
	private final int ARRIVAL_TIME = 20;
	private final int END_CARPARK_DEPARTURE_TIME = 18 * 60;
	private final int INTENDED_DURATION = 20;
	private final int SIM_TIME = 20;
	private final int DEPARTURE_TIME = ARRIVAL_TIME + INTENDED_DURATION;
	private final int EXIT_TIME = 30;
	
	private CarPark carpark;
	
	private final int ZERO = 0;
	private final int NEG_ONE = -1;
	private final int ONE = 1;
	
	private final boolean SMALL = true;
	private final boolean NOT_SMALL = false;
	
	private final String VEH_ID = "ABC123";
	
	private Car car;
	
	private MotorCycle moto;

	/**
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Before
	public void setUp() throws VehicleException {
		this.carpark = new CarPark(MAX_CAR_SPACES, MAX_SMALL_CAR_SPACES, 
				MAX_MOTORCYCLE_SPACES, MAX_QUEUE_SIZE);
		
		this.car = new Car(VEH_ID, ONE, SMALL);
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
	 */
	@Test(expected=VehicleException.class)
	public void testDepartingVehicleParkedState() throws VehicleException {		
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.unparkVehicle(car, ARRIVAL_TIME + 10);
		
		carpark.archiveDepartingVehicles(DEPARTURE_TIME, false);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test(expected=VehicleException.class)
	public void testDepartingVehicleQueuedState() throws VehicleException {		
		carpark.enterQueue(car);		
		carpark.archiveDepartingVehicles(DEPARTURE_TIME, false);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testDepartingVehicleCorrectState() throws VehicleException {
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.archiveDepartingVehicles(DEPARTURE_TIME, false);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=SimulationException.class)
	public void testDepartingVehicleNotInCarpark() throws SimulationException {	
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.unparkVehicle(car, ARRIVAL + 10);
		
		carpark.archiveDepartingVehicles(DEPARTURE_TIME, false);
	}

	//////////////////////////////////////////////////////////////////////////
	// archiveNewVehicle tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=SimulationException.class)
	public void testArchiveQueuedVehicle() throws SimulationException {
		carpark.enterQueue(car);
		carpark.archiveNewVehicle(car);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=SimulationException.class)
	public void testArchiveParkedVehicle() throws SimulationException {
		carpark.parVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
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
	 */
	@Test(expected=VehicleException.class)
	public void testArchiveQueueFailuresParkedState() throws VehicleException {		
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.archiveQueueFailures(DEPARTURE_TIME + 1);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test(expected=VehicleException.class)
	public void testArchiveQueueFailuresUnparkedState() throws VehicleException {		
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED DURATION)
		carpark.unparkVehicle(car, ARRIVAL_TIME + 10)
		carpark.archiveQueueFailures(DEPARTURE_TIME + 1);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testArchiveQueueFailuresCorrectState() throws VehicleException {
		carpark.enterQueue(car);
		carpark.archiveQueueFailures(DEPARTURE_TIME + 1);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test(expected=VehicleException.class)
	public void testArchiveQueueFailuresTimeViolated() throws VehicleException {
		carpark.enterQueue(car);
		carpark.archiveQueueFailures(ARRIVAL_TIME + MAX_QUEUE_TIME + 1);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testArchiveQueueFailuresTimingConstraints() throws VehicleException {
		carpark.enterQueue(car);
		carpark.archiveQueueFailures(ARRIVAL_TIME + MAX_QUEUE_TIME);
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
		assertTrue(carpark.carParkEmpty);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testCarParkEmptyFalse() throws VehicleException {
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED DURATION);
		assertFalse(carpark.carParkEmpty);
	}
		
	//////////////////////////////////////////////////////////////////////////
	// carParkFull tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testCarParkFullTrue() throws VehicleException {		
		for (int i = 0; i < MAX_CAR_SPACES; i++) {
			this.car = new Car("C" + i, ARRIVAL_TIME + 1, NOT_SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME + 1, INTENDED DURATION);
		}
		
		assertTrue(carpark.carparkFull);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testCarParkFullFalse() throws VehicleException {
		for (int i = 0; i < (MAX_CAR_SPACE - 1); i++) {
			this.car = new Car("C" + i, ARRIVAL_TIME + 1, NOT_SMALL);
			carpark.parkVehicle(car, ARRIVAL_TIME + 1, INTENDED DURATION);
		}
		
		assertFalse(carpark.carparkFull);
	}
	
	//////////////////////////////////////////////////////////////////////////
	// enterQueue tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testEnterQueue() throws VehicleException {
		carpark.enterQueue(car);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=SimulationException.class)
	public void testEnterQueueFull() throws SimulationException {
		for (i = 0; i < (MAX_QUEUE_SIZE + 1); i++) {
			carpark.enterQueue(car);
		}
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=SimulationException.class)
	public void testEnterQueueQueuedVehicle() throws SimulationException {
		carpark.enterQueue(car);
		carpark.enterQueue(car);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=SimulationException.class)
	public void testEnterQueueParkedVehicle() throws SimulationException {
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.enterQueue(car);
	}

	//////////////////////////////////////////////////////////////////////////
	// exitQueue tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testExitQueue() throws VehicleException {
		carpark.enterQueue(car);
		carpark.exitQueue(car, EXIT_TIME);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=SimulationException.class)
	public void testExitQueueNotQueuedVehicle() throws SimulationException {
		carpark.exitQueue(car, EXIT_TIME);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test(expected=VehicleException.class)
	public void testExitQueueParkedVehicle() throws VehicleException {
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.exitQueue(car, EXIT_TIME);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test(expected=VehicleException.class)
	public void testExitQueueTimingConstraints() throws VehicleException {
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.exitQueue(car, (END_CARPARK_DEPARTURE_TIME + 1));
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test(expected=VehicleException.class)
	public void testExitQueueExitTimeBeforeArrivalTime() throws VehicleException {
		carpark.parkVehicle(car, 20, INTENDED_DURATION);
		carpark.exitQueue(car, 19));
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testExitQueueExitTimeEqualsArrivalTime() throws VehicleException {
		carpark.parkVehicle(car, 20, INTENDED_DURATION);
		carpark.exitQueue(car, 20));
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
		assertEquals(carpark.getNumCars, 0);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testGetNumCarsMotorbikeOnly() throws VehicleException {
		carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		assertEquals(carpark.getNumCars, 0);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testGetNumCarsMultiple() throws VehicleException {
		this.car = new Car(VEH_ID, ONE, NOT_SMALL);
		
		carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.parkVehicle(this.car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		
		assertEquals(carpark.getNumCars, 3);
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
		assertEquals(carpark.getNumMotorCycles, 0);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testGetNumMotorCyclesCarOnly() throws VehicleException {
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		assertEquals(carpark.getNumMotorCycles, 0);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testGetNumMotorCyclesMultiple() throws VehicleException {
		this.car = new Car(VEH_ID, ONE, NOT_SMALL);
		
		carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.parkVehicle(this.car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		
		assertEquals(carpark.getNumMotorCycles, 2);
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
		assertEquals(carpark.getNumSmallCars, 0);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testGetNumSmallCarsBigCarsOnly() throws VehicleException {
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		assertEquals(carpark.getNumSmallCars, 0);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testGetNumSmallCarsMultiple() throws VehicleException {
		this.car = new Car(VEH_ID, ONE, NOT_SMALL);
		
		carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.parkVehicle(this.car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		
		assertEquals(carpark.getNumSmallCars, 1);
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
		assertEquals(carpark.getNumVehiclesInQueue, 0);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testGetNumVehiclesInQueueMultiple() throws VehicleException {
		this.car = new Car(VEH_ID, ONE, NOT_SMALL);
		
		carpark.enterQueue(moto, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.enterQueue(moto, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.enterQueue(this.car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.enterQueue(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.enterQueue(car, ARRIVAL_TIME, INTENDED_DURATION);
		
		assertEquals(carpark.getNumVehiclesInQueue, 5);
	}
	
	//////////////////////////////////////////////////////////////////////////
	// parkVehicle tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testParkVehicleBigCar() throws VehicleException {
		this.car = new Car(VEH_ID, ONE, NOT_SMALL)
		
		for (i = 0; i < MAX_CAR_SPACES; i++) {
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testParkVehicleSmallCar() throws VehicleException {		
		for (i = 0; i < (MAX_SMALL_CAR_SPACES + MAX_CAR_SPACES); i++) {
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testParkVehicleMotorCycle() throws VehicleException {		
		for (i = 0; i < (MAX_MOTORCYCLES_SPACES + MAX_SMALL_CAR_SPACES); i++) {
			carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		}
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=SimulationException.class)
	public void testParkVehicleBigCarNoSpaces() throws SimulationException {
		this.car = new Car(VEH_ID, ONE, NOT_SMALL)
		
		for (i = 0; i < (MAX_CAR_SPACES + 1); i++) {
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=SimulationException.class)
	public void testParkVehicleSmallCarNoSpaces() throws SimulationException {		
		for (i = 0; i < (MAX_SMALL_CAR_SPACES + MAX_CAR_SPACES + 1); i++) {
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=SimulationException.class)
	public void testParkVehicleMotorCycleNoSpaces() throws SimulationException {		
		for (i = 0; i < (MAX_MOTORCYCLES_SPACES + MAX_SMALL_CAR_SPACES + 1); i++) {
			carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		}
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=SimulationException.class)
	public void testParkVehicleMultipleVehicles() throws SimulationException {
		//
		// SET MAX SPACES TO SOMETHING MANAGEABLE
		// ADD CARS, SMALL CARS AND MOTORCYCLES MANUALLY
		// ENSURE SPACES FILL APPROPRIATELY
		// AND THAT SIMULATION EXCEPTION THROWN CORRECTLY
		//		
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test(expected=VehicleException.class)
	public void testParkVehicleAlreadyParked() throws VehicleException {
		carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION)
		carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
	}

	//////////////////////////////////////////////////////////////////////////
	// processQueue tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test
	public void testProcessQueue() throws VehicleException {
		carpark.enterQueue(car);
		carpark.processQueue(ARRIVAL_TIME, sim)
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=SimulationException.class)
	public void testProcessQueueBigCarNoSpaces() throws SimulationException {
		this.car = new Car(VEH_ID, ONE, NOT_SMALL)
		
		for (i = 0; i < (MAX_CAR_SPACES); i++) {
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		carpark.enterQueue(car);
		carpark.processQueue(ARRIVAL_TIME, sim)
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=SimulationException.class)
	public void testProcessQueueSmallCarNoSpaces() throws SimulationException {	
		for (i = 0; i < (MAX_SMALL_CAR_SPACES + MAX_CAR_SPACES); i++) {
			carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		carpark.enterQueue(car);
		carpark.processQueue(ARRIVAL_TIME, sim)
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=SimulationException.class)
	public void testProcessQueueMotorCycleNoSpaces() throws SimulationException {	
		for (i = 0; i < (MAX_MOTORCYCLES_SPACES + MAX_SMALL_CAR_SPACES); i++) {
			carpark.parkVehicle(moto, ARRIVAL_TIME, INTENDED_DURATION);
		}
		
		carpark.enterQueue(moto);
		carpark.processQueue(ARRIVAL_TIME, sim)
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.SimulationException
	 */
	@Test(expected=SimulationException.class)
	public void testProcessQueueMultipleVehicles() throws SimulationException {
		//
		// SET MAX SPACES TO SOMETHING MANAGEABLE
		// ADD CARS, SMALL CARS AND MOTORCYCLES MANUALLY
		// ENSURE SPACES FILL APPROPRIATELY
		// AND THAT SIMULATION EXCEPTION THROWN CORRECTLY
		//		
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testProcessQueueCorrectState() throws VehicleException {
			
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test(expected=VehicleException.class)
	public void testProcessQueueNewState() throws VehicleException {
			
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test(expected=VehicleException.class)
	public void testProcessQueueParkedState() throws VehicleException {
			
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test(expected=VehicleException.class)
	public void testProcessQueueArchivedState() throws VehicleException {
			
	}
	
	//////////////////////////////////////////////////////////////////////////
	// queueEmpty tests
	//////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////
	// queueFull tests
	//////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////
	// spacesAvailable tests
	//////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////
	// tryProcessNewVehicle tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testTryProcessNewVehicles() throws VehicleException {
		fail("Not yet implemented"); // TODO
	}
	
	//////////////////////////////////////////////////////////////////////////
	// unparkVehicle tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testUnparkVehicle() throws VehicleException {
		fail("Not yet implemented"); // TODO
	}

}
