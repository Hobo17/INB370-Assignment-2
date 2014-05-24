/**
 * 
 * This file is part of the CarParkSimulator Project, written as 
 * part of the assessment for INB370, semester 1, 2014. 
 *
 * CarParkSimulator
 * asgn2Vehicles 
 * 19/04/2014
 * 
 */
package asgn2Vehicles;

import java.util.ArrayList;
import java.util.List;

import asgn2Exceptions.VehicleException;
import asgn2Simulators.Constants;



/**
 * Vehicle is an abstract class specifying the basic state of a vehicle and the methods used to 
 * set and access that state. A vehicle is created upon arrival, at which point it must either 
 * enter the car park to take a vacant space or become part of the queue. If the queue is full, then 
 * the vehicle must leave and never enters the car park. The vehicle cannot be both parked and queued 
 * at once and both the constructor and the parking and queuing state transition methods must 
 * respect this constraint. 
 * 
 * Vehicles are created in a neutral state. If the vehicle is unable to park or queue, then no changes 
 * are needed if the vehicle leaves the carpark immediately.
 * Vehicles that remain and can't park enter a queued state via {@link #enterQueuedState() enterQueuedState} 
 * and leave the queued state via {@link #exitQueuedState(int) exitQueuedState}. 
 * Note that an exception is thrown if an attempt is made to join a queue when the vehicle is already 
 * in the queued state, or to leave a queue when it is not. 
 * 
 * Vehicles are parked using the {@link #enterParkedState(int, int) enterParkedState} method and depart using 
 * {@link #exitParkedState(int) exitParkedState}
 * 
 * Note again that exceptions are thrown if the state is inappropriate: vehicles cannot be parked or exit 
 * the car park from a queued state. 
 * 
 * The method javadoc below indicates the constraints on the time and other parameters. Other time parameters may 
 * vary from simulation to simulation and so are not constrained here.  
 * 
 * @author Brad
 *
 */
public abstract class Vehicle {
	
	private String vehID;
	private int exitTime;
	private int arrivalTime;
	private int departureTime;
	private int parkingTime;

	/**
	 * Contains the different states that the car can be in:
	 * N - New
	 * Q - Queued
	 * P - Parked
	 * A - Archived
	 */
	private enum State {
		N, Q, P, A
	}
	
	private List<String> previousStates;
	private State state;
		
	/**
	 * Vehicle Constructor 
	 * @param vehID String identification number or plate of the vehicle
	 * @param arrivalTime int time (minutes) at which the vehicle arrives and is 
	 *        either queued, given entry to the car park or forced to leave
	 * @throws VehicleException if arrivalTime is <= 0 
	 */
	public Vehicle(String vehID,int arrivalTime) throws VehicleException  {
		if(arrivalTime <= 0){
			throw new VehicleException ("The arrivalTime must be greater than 0. (" + arrivalTime + ")");
		}
		
		previousStates = new ArrayList<String>();
		
		this.vehID = vehID;
		this.arrivalTime = arrivalTime;
		this.state = State.N;
		this.previousStates.add("N");
	}

	/**
	 * Transition vehicle to parked state (mutator)
	 * Parking starts on arrival or on exit from the queue, but time is set here
	 * @param parkingTime int time (minutes) at which the vehicle was able to park
	 * @param intendedDuration int time (minutes) for which the vehicle is intended to remain in the car park.
	 *  	  Note that the parkingTime + intendedDuration yields the departureTime
	 * @throws VehicleException if the vehicle is already in a parked or queued state, if parkingTime < 0, 
	 *         or if intendedDuration is less than the minimum prescribed in asgnSimulators.Constants
	 */
	public void enterParkedState(int parkingTime, int intendedDuration) throws VehicleException {
		if(this.state == State.P || this.state == State.Q){
			throw new VehicleException("Vehicle must not be in a " + 
					(this.state == State.P ? "parked state already." : "queued state."));
		}
		else if(parkingTime < 0){
			throw new VehicleException("parkingTime must be greater than 0.");
		}
		else if(intendedDuration < Constants.MINIMUM_STAY){
			throw new VehicleException("intendedDuration must be greater than or equal to the minimum stay");
		}
		
		this.parkingTime = parkingTime;
		this.departureTime = parkingTime + intendedDuration;
		this.state = State.P;
		this.previousStates.add("P");
	}
	
	/**
	 * Transition vehicle to queued state (mutator) 
	 * Queuing formally starts on arrival and ceases with a call to {@link #exitQueuedState(int) exitQueuedState}
	 * @throws VehicleException if the vehicle is already in a queued or parked state
	 */
	public void enterQueuedState() throws VehicleException {
		if(this.state == State.P || this.state == State.Q){
			throw new VehicleException("Vehicle must not be in a " + 
					(this.state == State.Q ? "queued state already." : "parked state."));
		}
		
		this.state = State.Q;
		this.previousStates.add("Q");
	}
	
	/**
	 * Transition vehicle from parked state (mutator) 
	 * @param departureTime int holding the actual departure time 
	 * @throws VehicleException if the vehicle is not in a parked state, is in a queued 
	 * 		  state or if the revised departureTime < parkingTime
	 */
	public void exitParkedState(int departureTime) throws VehicleException {
		if(this.state != State.P){
			throw new VehicleException("Vehicle is not currently parked.");
		}
		else if(departureTime <= this.parkingTime){
			throw new VehicleException("departureTime must be greater than or equal to parkingTime.");
		}
		
		this.state = State.A;
		this.previousStates.add("A");
	}

	/**
	 * Transition vehicle from queued state (mutator) 
	 * Queuing formally starts on arrival with a call to {@link #enterQueuedState() enterQueuedState}
	 * Here we exit and set the time at which the vehicle left the queue
	 * @param exitTime int holding the time at which the vehicle left the queue 
	 * @throws VehicleException if the vehicle is in a parked state or not in a queued state, or if 
	 *  exitTime is not later than arrivalTime for this vehicle
	 */
	public void exitQueuedState(int exitTime) throws VehicleException {
		if(this.state != State.Q){
			throw new VehicleException("Vehicle is not currently queued.");
		}
		else if(exitTime <= arrivalTime){
			throw new VehicleException("exitTime must be greater than arrivalTime.");
		}
		
		this.exitTime = exitTime;
		this.state = State.N;
	}
	
	/**
	 * Simple getter for the arrival time 
	 * @return the arrivalTime
	 */
	public int getArrivalTime() {
		return this.arrivalTime;
	}
	
	/**
	 * Simple getter for the departure time from the car park
	 * Note: result may be 0 before parking, show intended departure 
	 * time while parked; and actual when archived
	 * @return the departureTime
	 */
	public int getDepartureTime() {
		return this.departureTime;
	}
	
	/**
	 * Simple getter for the parking time
	 * Note: result may be 0 before parking
	 * @return the parkingTime
	 */
	public int getParkingTime() {
		return this.parkingTime;
	}

	/**
	 * Simple getter for the vehicle ID
	 * @return the vehID
	 */
	public String getVehID() {
		return this.vehID;
	}

	/**
	 * Boolean status indicating whether vehicle is currently parked 
	 * @return true if the vehicle is in a parked state; false otherwise
	 */
	public boolean isParked() {
		return (this.state == State.P);
	}

	/**
	 * Boolean status indicating whether vehicle is currently queued
	 * @return true if vehicle is in a queued state, false otherwise 
	 */
	public boolean isQueued() {
		return (this.state == State.Q);
	}
	
	/**
	 * Boolean status indicating whether customer is satisfied or not
	 * Satisfied if they park; dissatisfied if turned away, or queuing for too long 
	 * Note that calls to this method may not reflect final status 
	 * @return true if satisfied, false if never in parked state or if queuing time exceeds max allowable 
	 */
	public boolean isSatisfied() {
		return(this.previousStates.contains("P") && (parkingTime - arrivalTime) <= Constants.MAXIMUM_QUEUE_TIME);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String output;
		
		output =    "Vehicle vehID: " + this.vehID;
		output += "\nArrival Time: " + this.getArrivalTime();
		
		// Determine queue information
		output += "\n" + (this.wasQueued() 
						? "Exit from Queue: " + this.exitTime + 
								"\nQueuing Time: " + String.valueOf(this.exitTime - this.arrivalTime)
						: "Vehicle was not queued");
		
		// Determine parking information
		output += "\n" + (this.wasParked() 
						? "Entry to Car Park: " + this.parkingTime + 
								"\nExit from Car Park: " + this.getDepartureTime() +
								"\nParking Time: "+ String.valueOf(this.getDepartureTime() - this.parkingTime)
						: "Vehicle was not parked");

		output += "\nCustomer was " + (this.isSatisfied() ? "" : "not ") + "satisfied";
		
		// Check to see if it is a car, if so check if it is small or not.
		output += (this instanceof Car) 
				? "\nCar can" + (((Car)this).isSmall() ? "" : "not") + " use small parking space"
				: "";
		
		return output;
	}

	/**
	 * Boolean status indicating whether vehicle was ever parked
	 * Will return false for vehicles in queue or turned away 
	 * @return true if vehicle was or is in a parked state, false otherwise 
	 */
	public boolean wasParked() {
		return (this.previousStates.contains("P"));
	}

	/**
	 * Boolean status indicating whether vehicle was ever queued
	 * @return true if vehicle was or is in a queued state, false otherwise 
	 */
	public boolean wasQueued() {
	return (this.previousStates.contains("Q"));
	}
}
