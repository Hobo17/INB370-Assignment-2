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
import asgn2Vehicles.Car;

/**
 * @author Brad Vose (n8280282)
 *
 */
public class CarTests {

	private final int NEG_ONE = -1;
	private final int ZERO = 0;
	private final int ONE = 1;
	
	private final String VEH_ID = "C23";
	private final int NORMAL_ARRIVE_TIME = 30;
	private final boolean IS_SMALL_CAR = false;

	private final int NORMAL_PARKING_TIME = NORMAL_ARRIVE_TIME + 10;
	private final int NORMAL_INTENDED_DURATION = 30;
	private final int NORMAL_EXIT_TIME = NORMAL_PARKING_TIME;
	private final int NORMAL_DEPARTURE_TIME = NORMAL_PARKING_TIME + NORMAL_INTENDED_DURATION;
	
	private Car car;
	
	/**
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Before
	public void setUp() throws VehicleException {
		car = new Car(VEH_ID, NORMAL_ARRIVE_TIME, IS_SMALL_CAR);
	}

	//////////////////////////////////////////////////////////////////////////
	// Construction tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for {@link asgn2Vehicles.Car#Car(java.lang.String, int, boolean)}
	 * with car arriving before 0
	 * @throws asgn2.Exceptions.VehicleException
	 * @author Bec
	 */
	@Test(expected=VehicleException.class)
	public void testCarArriveBelowZero() throws VehicleException {
		car = new Car(VEH_ID, NEG_ONE, IS_SMALL_CAR);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Car#Car(java.lang.String, int, boolean)}
	 * with car arriving at 0
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test(expected=VehicleException.class)
	public void testCarArriveEqualsZero() throws VehicleException {
		car = new Car(VEH_ID, ZERO, IS_SMALL_CAR);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Car#Car(java.lang.String, int, boolean)}
	 * with car arriving after 0
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testCarArriveAfterZero() throws VehicleException {
		car = new Car(VEH_ID, ONE, IS_SMALL_CAR);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Car#Car(java.lang.String, int, boolean)}
	 * with blank string as license plate in construction
	 */
	@Test
	public void testCarArriveNoPlates() throws VehicleException {
		car = new Car("", ONE, IS_SMALL_CAR);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Car#Car(java.lang.String, int, boolean)}.
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testCarValid() throws VehicleException {
		car = new Car(VEH_ID, NORMAL_ARRIVE_TIME, IS_SMALL_CAR);
	}

	//////////////////////////////////////////////////////////////////////////
	// toString tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for {@link asgn2Vehicles.Car#toString()}.
	 * @throws asgn2.Exceptions.VehicleException
	 */
	public void testToString() throws VehicleException {
		car.enterQueuedState();
		car.exitQueuedState(NORMAL_EXIT_TIME);
		car.enterParkedState(NORMAL_PARKING_TIME, NORMAL_INTENDED_DURATION);
		car.exitParkedState(NORMAL_DEPARTURE_TIME);
		assertEquals(car.toString(), "Vehicle vehID: " + car.getVehID() + 
				"\nArrival Time: " + car.getArrivalTime() + 
				"\nExit from Queue: " + car.getParkingTime() +
				"\nQueuing Time: " + (car.getParkingTime() - car.getArrivalTime()) +
				"\nEntry to Car Park: " + car.getParkingTime() +
				"\nExit from Car Park: " + car.getDepartureTime() +
				"\nParking Time: " + (car.getDepartureTime() - car.getParkingTime()) +
				"\nCustomer was satisfied");
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Car#toString()}.
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testToStringSmallCar() throws VehicleException {
		car = new Car(VEH_ID, NORMAL_ARRIVE_TIME, true);
		
		car.enterQueuedState();
		car.exitQueuedState(NORMAL_EXIT_TIME);
		car.enterParkedState(NORMAL_PARKING_TIME, NORMAL_INTENDED_DURATION);
		car.exitParkedState(NORMAL_DEPARTURE_TIME);
		assertEquals(car.toString(), "Vehicle vehID: " + car.getVehID() + 
				"\nArrival Time: " + car.getArrivalTime() + 
				"\nExit from Queue: " + car.getParkingTime() +
				"\nQueuing Time: " + (car.getParkingTime() - car.getArrivalTime()) +
				"\nEntry to Car Park: " + car.getParkingTime() +
				"\nExit from Car Park: " + car.getDepartureTime() +
				"\nParking Time: " + (car.getDepartureTime() - car.getParkingTime()) +
				"\nCustomer was satisfied" + 
				"\nCar can use small parking space");
	}
	
	//////////////////////////////////////////////////////////////////////////
	// isSmall tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for {@link asgn2Vehicles.Car#isSmall()}.
	 */
	@Test
	public void testIsSmallTrue() {
		assertFalse(car.isSmall());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Car#isSmall()}.
	 * @throws asgn2.Exceptions.VehicleException
	 */
	@Test
	public void testIsSmallFalse() throws VehicleException {
		car = new Car(VEH_ID, NORMAL_ARRIVE_TIME, true);
		assertTrue(car.isSmall());
	}

}
