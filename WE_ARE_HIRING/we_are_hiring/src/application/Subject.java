package application;
import java.util.*;
interface Subject {
	List<Observer> observers = new ArrayList<Observer>();
	public void addObserver(Observer observer);
	public void removeObserver(Observer observer);
	public void notifyAllObservers();//daca toate locurile sunt ocupate -> update() = sterg de pe lista observers
	//+ sterg requesturi (manager -> delete all requests)
	//in funcite de notify update poate face mai multe lucruri?
	//1. sterge o companie care are locurile ocupate
	//2. daca observer s-a angajat sterge dorintele - > asta fac in addEMployee
}
