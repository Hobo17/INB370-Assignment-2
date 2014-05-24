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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author hogan
 *
 */
public class CarParkTests {
	
	private final int MAX_CAR_SPACES = 100;
	private final MAX_SMALL_CAR_SPACES = 30;
	private final int MAX_MOTORCYCLES_SPACES = 20;
	private final int MAX_QUEUE_SIZE = 10;
	
	private CarPark carpark;

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
	 * @author 
	 */
	@Test(expected=VehicleException.class)
	public void testDepartingVehicleIncorrectState() throws VehicleException {
		
	}
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @author 
	 */
	@Test
	public void testDepartingVehicleCorrectState() throws VehicleException {
		
	}

	//////////////////////////////////////////////////////////////////////////
	// archiveNewVehicle tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @author 
	 */
	@Test
	public void testArchiveNewVehicle() {
		fail("Not yet implemented"); // TODO
	}
	
	//////////////////////////////////////////////////////////////////////////
	// archiveQueueFailures tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @author 
	 */
	@Test
	public void testArchiveQueueFailures() {
		fail("Not yet implemented"); // TODO
	}
	
	//////////////////////////////////////////////////////////////////////////
	// enterQueue tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @author 
	 */
	@Test
	public void testEnterQueue() {
		fail("Not yet implemented"); // TODO
	}

	//////////////////////////////////////////////////////////////////////////
	// exitQueue tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @author 
	 */
	@Test
	public void testExitQueue() {
		fail("Not yet implemented"); // TODO
	}

	//////////////////////////////////////////////////////////////////////////
	// parkVehicle tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @author 
	 */
	@Test
	public void testParkVehicle() {
		fail("Not yet implemented"); // TODO
	}

	//////////////////////////////////////////////////////////////////////////
	// processQueue tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @author 
	 */
	@Test
	public void testProcessQueue() {
		fail("Not yet implemented"); // TODO
	}
	
	//////////////////////////////////////////////////////////////////////////
	// tryProcessNewVehicle tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @author 
	 */
	@Test
	public void testTryProcessNewVehicles() {
		fail("Not yet implemented"); // TODO
	}
	
	//////////////////////////////////////////////////////////////////////////
	// unparkVehicle tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for 
	 * @throws asgn2.Exceptions.VehicleException
	 * @author 
	 */
	@Test
	public void testUnparkVehicle() {
		fail("Not yet implemented"); // TODO
	}

}
