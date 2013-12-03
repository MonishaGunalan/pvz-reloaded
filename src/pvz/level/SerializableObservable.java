package pvz.level;
/**
 * @author Tianming Zhuang
 * 100875151
 *
 * This class is an implementation of the Observable
 * class that supports serialization.
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;
import java.io.Serializable;

import pvz.level.Square;

public abstract class SerializableObservable 
	extends Observable
	implements Serializable {
	/** 
	 * The list of observers
	 */
	private ArrayList<Observer> observers;
	private boolean hasChanged;

	/**
	 **Construct an Observable with zero Observers.
	 */
	public SerializableObservable() {
		observers = new ArrayList<Observer>();
		hasChanged = false;
	}

	/** 
	 * Adds an observer to the set 	of observers for this object, provided
	 * that it is not the same as some observer already in the set.
	 */
	public void addObserver(Observer o) { 
		observers.add(o);
	}

	/**
	 * Indicates that this object has no longer changed, or that it has already 
	 * notified all of its observers of its most recent change, so that the 
	 * hasChanged method will now return false.
	 */
	protected void clearChanged() {
		hasChanged = false;
	}

	/**
	 * Returns the number of observers of this Observable object.
	 */
	public int countObservers() {
		return observers.size();
	}

	/**
	 * Deletes an observer from the set of observers of this object.
	 */
	public void deleteObserver(Observer o) {
		observers.remove(o);
	}

	/**
	 * Clears the observer list so that this object no longer has any observers.
	 */
	public void deleteObservers() {
		observers.clear();
	}

	/**
	 * Tests if this object has changed.
	 */
	public boolean hasChanged() {
		return hasChanged;
	}

	/**
	 * If this objectject has changed, as indicated by the hasChanged method,
	 * then notify all of its observers and then call the clearChanged method 
	 * to indicate that this object has no longer changed.
	 */
	public void notifyObservers() {
		this.notifyObservers(null);
	}

	/**
	 * If this object has changed, as indicated by the hasChanged method, then 
	 * notify all of its observers and then call the clearChanged method to indicate 
	 * that this object has no longer changed.
	 */
	public void notifyObservers(Object arg) {
		// Creates temporary list of observers to 
		// snapshot current state
		ArrayList<Observer> tempObservers;

		synchronized(this) {
			// Break if there have been no changed
			if (!hasChanged) return;
			// Copy list into temp
			tempObservers = (ArrayList<Observer>)observers.clone();
			// Clear changed flag
			clearChanged();
		}

		for (Observer observer : tempObservers) {
			observer.update(this, arg);
		}
	}

	/**
	 * Marks this Observable object as having been changed; the hasChanged 
	 * method will now return true.
	 */
	protected void setChanged() {
		this.hasChanged = true;
	}
}
