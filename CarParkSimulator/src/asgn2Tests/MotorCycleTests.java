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
	private final int ONE = 1;

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
	public void motoArriveBeforeZero() throws VehicleException {
		this.moto = new MotorCycle(VEH_ID, NEG_ONE); 
	}

	/**
	 * Test method for bad construction of motorCycle
	 * with zero arrival time
	 * @throws asgn.Exceptions.VehicleException
	 * @author Brad
	 */
	@Test(expected=VehicleException.class)
	public void motoArriveAtZero() throws VehicleException {
		this.moto = new MotorCycle(VEH_ID, ZERO); 
	}

	/**
	 * Test method for good construction of motorCycle
	 * with one arrival time
	 * @throws asgn.Exceptions.VehicleException
	 * @author Bec
	 */
	@Test
	public void motoArriveAtOne() throws VehicleException {
		this.moto = new MotorCycle(VEH_ID, ONE); 
	}
