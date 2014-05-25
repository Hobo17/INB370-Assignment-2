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

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.VehicleException;
import asgn2Simulators.Constants;
import asgn2Vehicles.MotorCycle;

/**
 * @author Brad
 *
 */
public class MotorCycleTests {

	private final int NEG_ONE = -1;
	private final int ZERO = 0;
	private final int ONE = 1;
	
	private final String VEH_ID = "MC22";
	private final int NORMAL_ARRIVE_TIME = 30;
	private final int NORMAL_PARKING_TIME = NORMAL_ARRIVE_TIME + 10;
	private final int NORMAL_INTENDED_DURATION = 30;
	private final int NORMAL_EXIT_TIME = NORMAL_PARKING_TIME;
	private final int NORMAL_DEPARTURE_TIME = NORMAL_PARKING_TIME + NORMAL_INTENDED_DURATION;
	
	private MotorCycle moto;
	
	/**
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Before
	public void setUp() throws VehicleException {
		moto = new MotorCycle(VEH_ID, NORMAL_ARRIVE_TIME);
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
	 */
	@Test(expected=VehicleException.class)
	public void testMotoArriveBeforeZero() throws VehicleException {
		moto = new MotorCycle(VEH_ID, NEG_ONE); 
	}

	/**
	 * Test method for construction of 
	 * {@link asgn2Vehicles.MotorCycle#MotorCycle(java.lang.String, int)}
	 * with arrive time at 0
	 * @throws asgn.Exceptions.VehicleException
	 */
	@Test(expected=VehicleException.class)
	public void testMotoArriveEqualsZero() throws VehicleException {
		moto = new MotorCycle(VEH_ID, ZERO); 
	}
	
	/**
	 * Test method for construction of 
	 * {@link asgn2Vehicles.MotorCycle#MotorCycle(java.lang.String, int)}
	 * with arrive time at 1
	 * @throws asgn.Exceptions.VehicleException
	 * @author Bec
	 */
	@Test
	public void motoArriveAtOne() throws VehicleException {
		this.moto = new MotorCycle(VEH_ID, ONE); 
	}

	/**
	 * Test method for {@link asgn2Vehicles.MotorCycle#MotorCycle(java.lang.String, int)}
	 * with blank string as license plate in construction
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testMotoNoPlates() throws VehicleException {
		moto = new MotorCycle("", NORMAL_ARRIVE_TIME);
	}

	/**
	 * Test method for {@link asgn2Vehicles.MotorCycle#MotorCycle(java.lang.String, int)}
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testMotoValid() throws VehicleException {
		moto = new MotorCycle(VEH_ID, NORMAL_ARRIVE_TIME);
	}

//////////////////////////////////////////////////////////////////////////
//Generic Vehicle Tests
//////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////
	// getVehID method tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getVehID()}.
	 */
	@Test
	public void testGetVehID() {
		assertEquals(moto.getVehID(), VEH_ID);
	}
	
	//////////////////////////////////////////////////////////////////////////
	// getArrivalTime method tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getArrivalTime()}.
	 */
	@Test
	public void testGetArrivalTime() {
		assertEquals(moto.getArrivalTime(), NORMAL_ARRIVE_TIME);
	}

	//////////////////////////////////////////////////////////////////////////
	// enterQueuedState method tests
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterQueuedState()}.
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test(expected=VehicleException.class)
	public void testEnterQueuedStateQueued() throws VehicleException {
		moto.enterQueuedState();
		moto.enterQueuedState();
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterQueuedState()}.
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test(expected=VehicleException.class)
	public void testEnterQueuedStateParked() throws VehicleException {
		moto.enterParkedState(NORMAL_ARRIVE_TIME, 30);
		moto.enterQueuedState();
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterQueuedState()}.
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testEnterQueuedStateValid() throws VehicleException {
		moto.enterQueuedState();
		assertTrue(moto.isQueued());
	}

	//////////////////////////////////////////////////////////////////////////
	// exitQueuedState method tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitQueuedState(int)}
	 * when car is parked.
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test(expected=VehicleException.class)
	public void testExitQueuedStateParked() throws VehicleException {
		moto.enterParkedState(NORMAL_ARRIVE_TIME, NORMAL_INTENDED_DURATION);
		moto.exitQueuedState(NORMAL_ARRIVE_TIME + NORMAL_INTENDED_DURATION);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitQueuedState(int)}
	 * when car is not queued or parked.
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test(expected=VehicleException.class)
	public void testExitQueuedStateNotQueued() throws VehicleException {
		moto.exitQueuedState(NORMAL_ARRIVE_TIME + NORMAL_INTENDED_DURATION);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitQueuedState(int)}
	 * when exitTime = arriveTime
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test(expected=VehicleException.class)
	public void testExitQueuedStateTimeEquals() throws VehicleException {
		moto.enterQueuedState();
		moto.exitQueuedState(NORMAL_ARRIVE_TIME);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitQueuedState(int)}
	 * when exitTime < arriveTime
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testExitQueuedStateTimeOver() throws VehicleException {
		moto.enterQueuedState();
		moto.exitQueuedState(NORMAL_ARRIVE_TIME + 1);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitQueuedState(int)}.
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testExitQueuedStateValid() throws VehicleException {
		moto.enterQueuedState();
		moto.exitQueuedState(NORMAL_ARRIVE_TIME + NORMAL_INTENDED_DURATION);
		assertFalse(moto.isQueued());
	}
	
	//////////////////////////////////////////////////////////////////////////
	// enterParkedState method tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterParkedState(int, int)}.
	 * when car is already parked
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test(expected=VehicleException.class)
	public void testEnterParkedStateParked() throws VehicleException {
		moto.enterParkedState(NORMAL_PARKING_TIME, NORMAL_INTENDED_DURATION);
		moto.enterParkedState(NORMAL_PARKING_TIME, NORMAL_INTENDED_DURATION);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterParkedState(int, int)}.
	 * when car is already queued
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test(expected=VehicleException.class)
	public void testEnterParkedStateQueued() throws VehicleException {
		moto.enterQueuedState();
		moto.enterParkedState(NORMAL_PARKING_TIME, NORMAL_INTENDED_DURATION);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterParkedState(int, int)}.
	 * when parking time is below 0
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test(expected=VehicleException.class)
	public void testEnterParkedStateTimeBelowZero() throws VehicleException {
		moto.enterParkedState(NEG_ONE, NORMAL_INTENDED_DURATION);
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterParkedState(int, int)}.
	 * when parking time is zero
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testEnterParkedStateTimeZero() throws VehicleException {
		moto.enterParkedState(ZERO, NORMAL_INTENDED_DURATION);
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterParkedState(int, int)}.
	 * when intended duration is under the minimum
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test(expected=VehicleException.class)
	public void testEnterParkedStateDurationUnderMin() throws VehicleException {
		moto.enterParkedState(NORMAL_PARKING_TIME, Constants.MINIMUM_STAY - 1);
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterParkedState(int, int)}.
	 * when intended duration equals the minimum
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testEnterParkedStateDurationEqualsMin() throws VehicleException {
		moto.enterParkedState(NORMAL_PARKING_TIME, Constants.MINIMUM_STAY);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterParkedState(int, int)}.
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testEnterParkedStateValid() throws VehicleException {
		moto.enterParkedState(NORMAL_PARKING_TIME, NORMAL_INTENDED_DURATION);
		assertTrue(moto.isParked());
	}

	//////////////////////////////////////////////////////////////////////////
	// exitParkedState method tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitParkedState()}.
	 * when not parked or queued
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test(expected=VehicleException.class)
	public void testExitParkedStateNotParked() throws VehicleException {
		moto.exitParkedState(NORMAL_DEPARTURE_TIME);
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitParkedState()}.
	 * when queued
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test(expected=VehicleException.class)
	public void testExitParkedStateQueued() throws VehicleException {
		moto.enterQueuedState();
		moto.exitParkedState(NORMAL_DEPARTURE_TIME);
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitParkedState()}.
	 * when exitTime <= arrivalTime
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test(expected=VehicleException.class)
	public void testExitParkedStateTimeNotOver() throws VehicleException {
		moto.enterParkedState(NORMAL_PARKING_TIME, NORMAL_INTENDED_DURATION);
		moto.exitParkedState(NORMAL_PARKING_TIME);
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitParkedState()}.
	 * when exitTime > arrivalTime
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testExitParkedStateTimeOver() throws VehicleException {
		moto.enterParkedState(NORMAL_PARKING_TIME, NORMAL_INTENDED_DURATION);
		moto.exitParkedState(NORMAL_PARKING_TIME + 1);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitParkedState()}.
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testExitParkedStateValid() throws VehicleException {
		moto.enterParkedState(NORMAL_PARKING_TIME, NORMAL_INTENDED_DURATION);
		moto.exitParkedState(NORMAL_DEPARTURE_TIME);
	}

	//////////////////////////////////////////////////////////////////////////
	// isParked method tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isParked()}.
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testIsParkedTrue() throws VehicleException {
		moto.enterParkedState(NORMAL_PARKING_TIME, NORMAL_INTENDED_DURATION);
		assertTrue(moto.isParked());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isParked()}.
	 */
	@Test
	public void testIsParkedFalse() {
		assertFalse(moto.isParked());
	}

	//////////////////////////////////////////////////////////////////////////
	// isQueued method tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isQueued()}.
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testIsQueuedTrue() throws VehicleException {
		moto.enterQueuedState();
		assertTrue(moto.isQueued());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isQueued()}.
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testIsQueuedFalse() throws VehicleException {
		assertFalse(moto.isQueued());
	}

	//////////////////////////////////////////////////////////////////////////
	// getParkingTime method tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getParkingTime()}.
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testGetParkingTime() throws VehicleException {
		moto.enterParkedState(NORMAL_PARKING_TIME, NORMAL_INTENDED_DURATION);
		assertEquals(moto.getParkingTime(), NORMAL_PARKING_TIME);
	}

	//////////////////////////////////////////////////////////////////////////
	// getDepartureTime method tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getDepartureTime()}.
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testGetDepartureTime() throws VehicleException {
		moto.enterParkedState(NORMAL_PARKING_TIME, NORMAL_INTENDED_DURATION);
		assertEquals(moto.getDepartureTime(), NORMAL_PARKING_TIME + NORMAL_INTENDED_DURATION);
	}

	//////////////////////////////////////////////////////////////////////////
	// wasQueued method tests
	//////////////////////////////////////////////////////////////////////////

	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#wasParked()}.
	 * with only being parked and not queued
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testWasQueuedParked() throws VehicleException {
		moto.enterParkedState(NORMAL_PARKING_TIME, NORMAL_INTENDED_DURATION);
		moto.exitParkedState(NORMAL_DEPARTURE_TIME);
		assertFalse(moto.wasQueued());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#wasParked()}.
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testWasQueuedTrue() throws VehicleException {
		moto.enterQueuedState();
		moto.exitQueuedState(NORMAL_EXIT_TIME);
		assertTrue(moto.wasQueued());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#wasQueued()}.
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testWasQueuedFalse() throws VehicleException {
		assertFalse(moto.wasQueued());
	}

	//////////////////////////////////////////////////////////////////////////
	// wasParked method tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#wasParked()}.
	 * with only being queued and not parked
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testWasParkedQueued() throws VehicleException {
		moto.enterQueuedState();
		moto.exitQueuedState(NORMAL_EXIT_TIME);
		assertFalse(moto.wasParked());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#wasParked()}.
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testWasParkedTrue() throws VehicleException {
		moto.enterParkedState(NORMAL_PARKING_TIME, NORMAL_INTENDED_DURATION);
		moto.exitParkedState(NORMAL_DEPARTURE_TIME);
		assertTrue(moto.wasParked());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#wasParked()}.
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testWasParkedFalse() throws VehicleException {
		assertFalse(moto.wasParked());
	}

	//////////////////////////////////////////////////////////////////////////
	// isSatisfied method tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isSatisfied()}
	 * when not having parked
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testIsSatisfiedNotParked() throws VehicleException {
		assertFalse(moto.isSatisfied());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isSatisfied()}
	 * when having waited too long
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testIsSatisfiedTimeEqualsMax() throws VehicleException {
		int parkTime = NORMAL_ARRIVE_TIME + Constants.MAXIMUM_QUEUE_TIME;

		moto.enterQueuedState();
		moto.exitQueuedState(parkTime);
		moto.enterParkedState(parkTime, Constants.MINIMUM_STAY);
		assertTrue(moto.isSatisfied());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isSatisfied()}
	 * when having waited too long
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testIsSatisfiedTimeOverMax() throws VehicleException {
		int parkTime = NORMAL_ARRIVE_TIME + Constants.MAXIMUM_QUEUE_TIME + 1;

		moto.enterQueuedState();
		moto.exitQueuedState(parkTime);
		moto.enterParkedState(parkTime, Constants.MINIMUM_STAY);
		assertFalse(moto.isSatisfied());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isSatisfied()}.
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testIsSatisfiedTrue() throws VehicleException {
		moto.enterParkedState(NORMAL_PARKING_TIME, Constants.MINIMUM_STAY);
		assertTrue(moto.isSatisfied());
	}

	//////////////////////////////////////////////////////////////////////////
	// toString method tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#toString()}.
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testToString() throws VehicleException {
		moto.enterQueuedState();
		moto.exitQueuedState(NORMAL_EXIT_TIME);
		moto.enterParkedState(NORMAL_PARKING_TIME, NORMAL_INTENDED_DURATION);
		moto.exitParkedState(NORMAL_DEPARTURE_TIME);
		assertEquals(moto.toString(), "Vehicle vehID: " + moto.getVehID() + 
				"\nArrival Time: " + moto.getArrivalTime() + 
				"\nExit from Queue: " + moto.getParkingTime() +
				"\nQueuing Time: " + (moto.getParkingTime() - moto.getArrivalTime()) +
				"\nEntry to Car Park: " + moto.getParkingTime() +
				"\nExit from Car Park: " + moto.getDepartureTime() +
				"\nParking Time: " + (moto.getDepartureTime() - moto.getParkingTime()) +
				"\nCustomer was satisfied");
	}

}
