/**
 * 
 * This file is part of the CarParkSimulator Project, written as 
 * part of the assessment for INB370, semester 1, 2014. 
 *
 * CarParkSimulator
 * asgn2Tests 
 * 22/04/2014
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.VehicleException;
import asgn2Vehicles.MotorCycle;

/**
 * @author hogan
 *
 */
public class MotorCycleTests {
	
	private final int ZERO = 0;
	private final int NEG_ONE = -1;

	private final String VEH_ID = "ABC123";
	
	private MotorCycle moto;
	
	/**
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Before
	public void setUp() throws VehicleException {
		this.moto = new MotorCycle(VEH_ID, ZERO);
	}

//////////////////////////////////////////////////////////////////////////
// MotorCycle Tests
//////////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////////
	// Construction tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for bad construction of motorcycle
	 * with negative arrival time
	 * @throws asgn2.Exceptions.VehicleException
	 * @author Brad
	 */
	@Test(expected=VehicleException.class)
	public void motoArriveBeforeZero() throws VehicleException{
		this.moto = new MotorCycle(VEH_ID, NEG_ONE); 
	}

	/**
	 * Test method for good construction of MotorCycle
	 * @throws asgn.Exceptions.VehicleException
	 * @author Brad
	 */
	@Test
	public void motoArriveAtZero() throws VehicleException{
		this.moto = new MotorCycle(VEH_ID, ZERO); 
	}

	/**
	 * Test method for {@link asgn2Vehicles.MotorCycle#MotorCycle(java.lang.String, int)}.
	 */
	@Test
	public void testMotorCycle() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#Vehicle(java.lang.String, int)}.
	 */
	@Test
	public void testVehicle() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getVehID()}.
	 */
	@Test
	public void testGetVehID() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getArrivalTime()}.
	 */
	@Test
	public void testGetArrivalTime() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterQueuedState()}.
	 */
	@Test
	public void testEnterQueuedState() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitQueuedState(int)}.
	 */
	@Test
	public void testExitQueuedState() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterParkedState(int, int)}.
	 */
	@Test
	public void testEnterParkedState() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitParkedState(int)}.
	 */
	@Test
	public void testExitParkedStateInt() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitParkedState()}.
	 */
	@Test
	public void testExitParkedState() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isParked()}.
	 */
	@Test
	public void testIsParked() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isQueued()}.
	 */
	@Test
	public void testIsQueued() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getParkingTime()}.
	 */
	@Test
	public void testGetParkingTime() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getDepartureTime()}.
	 */
	@Test
	public void testGetDepartureTime() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#wasQueued()}.
	 */
	@Test
	public void testWasQueued() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#wasParked()}.
	 */
	@Test
	public void testWasParked() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isSatisfied()}.
	 */
	@Test
	public void testIsSatisfied() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#toString()}.
	 */
	@Test
	public void testToString() {
		fail("Not yet implemented"); // TODO
	}

}
