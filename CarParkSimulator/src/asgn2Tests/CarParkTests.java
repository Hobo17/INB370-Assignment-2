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
	
	private CarPark carpark;
	
	private final int ZERO = 0;
	private final int NEG_ONE = -1;
	private final int ONE = 1;
	
	private final boolean SMALL = true;
	private final boolean NOT_SMALL = false;
	
	private final String VEH_ID = "ABC123";
	
	private Car car;

	/**
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Before
	public void setUp() throws VehicleException {
		this.carpark = new CarPark(MAX_CAR_SPACES, MAX_SMALL_CAR_SPACES, 
				MAX_MOTORCYCLE_SPACES, MAX_QUEUE_SIZE);
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
	public void testDepartingVehicleIncorrectState() throws VehicleException {
		Carpark carpark = new Carpark();
		Car car = new Car(VEH_ID, ONE, SMALL);
		
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		carpark.unparkVehicle(car, ARRIVAL_TIME + 10);
		assertFalse(Vehicle.isParked()); // required??
		
		carpark.archiveDepartingVehicles(DEPARTURE_TIME, false);
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testDepartingVehicleCorrectState() throws VehicleException {
		Carpark carpark = new Carpark();
		Car car = new Car(VEH_ID, ONE, SMALL);
		
		carpark.parkVehicle(car, ARRIVAL_TIME, INTENDED_DURATION);
		assertTrue(Vehicle.isParked()); // required??
		
		carpark.archiveDepartingVehicles(DEPARTURE_TIME, false);
	}

	//////////////////////////////////////////////////////////////////////////
	// archiveNewVehicle tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testArchiveNewVehicle() throws VehicleException {
		fail("Not yet implemented"); // TODO
	}
	
	//////////////////////////////////////////////////////////////////////////
	// archiveQueueFailures tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testArchiveQueueFailures() throws VehicleException {
		fail("Not yet implemented"); // TODO
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
		fail("Not yet implemented"); // TODO
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
		fail("Not yet implemented"); // TODO
	}

	//////////////////////////////////////////////////////////////////////////
	// parkVehicle tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testParkVehicle() throws VehicleException {
		fail("Not yet implemented"); // TODO
	}

	//////////////////////////////////////////////////////////////////////////
	// processQueue tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testProcessQueue() throws VehicleException {
		fail("Not yet implemented"); // TODO
	}
	
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
