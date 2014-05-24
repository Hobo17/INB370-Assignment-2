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

/**
 * @author hogan
 *
 */
public class CarTests {
	
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
		this.car = new Car(VEH_ID, ZERO, NOT_SMALL);
	}
	
//////////////////////////////////////////////////////////////////////////
// Car Tests
//////////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////////
	// Construction tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for bad construction of car
	 * with negative arrival time
	 * @throws asgn2.Exceptions.VehicleException
	 * @author Bec
	 */
	@Test(expected=VehicleException.class)
	public void carArriveBeforeZero() throws VehicleException {
		this.car = new Car(VEH_ID, NEG_ONE, NOT_SMALL); 
	}

	/**
	 * Test method for bad construction of car
	 * with zero arrival time
	 * @throws asgn.Exceptions.VehicleException
	 * @author Bec
	 */
	@Test(expected=VehicleException.class)
	public void carArriveAtZero() throws VehicleException {
		this.car = new Car(VEH_ID, ZERO, NOT_SMALL); 
	}

	/**
	 * Test method for good construction of car
	 * with one arrival time
	 * @throws asgn.Exceptions.VehicleException
	 * @author Bec
	 */
	@Test
	public void carArriveAtOne() throws VehicleException {
		this.car = new Car(VEH_ID, ONE, NOT_SMALL); 
	}
	
	//////////////////////////////////////////////////////////////////////////
	// isSmall tests
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Test method for construction of a small car
	 * @throws asgn2.Exceptions.VehicleException
	 * @author Bec
	 */
	@Test
	public void testIsSmall() throws VehicleException {
		this.car = new Car(VEH_ID, ONE, SMALL);
	}
	
	/**
	 * Test method for construction of a normal car
	 * @throws asgn2.Exceptions.VehicleException
	 * @author Bec
	 */
	@Test
	public void testIsNotSmall() throws VehicleException {
		this.car = new Car(VEH_ID, ONE, NOT_SMALL);
	}

}
