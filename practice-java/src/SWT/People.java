package SWT;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class People {
	PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	String firstName;
	String lastName;
	int age;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		String old = this.firstName;
		this.firstName = firstName;
		pcs.firePropertyChange("firstName", old, firstName);
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		String old = this.lastName;
		this.lastName = lastName;
		pcs.firePropertyChange("lastName", old, lastName);
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		int old = this.age;
		this.age = age;
		pcs.firePropertyChange("lastName", old, age);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl){
		pcs.addPropertyChangeListener(pcl);
	}
	public void removePropertyChangeListener(PropertyChangeListener pcl){
		pcs.removePropertyChangeListener(pcl);
	}
	public void addPropertyChangeListener(String propName, PropertyChangeListener pcl){
		pcs.addPropertyChangeListener(propName, pcl);
	}
	public void removePropertyChangeListener(String propName, PropertyChangeListener pcl){
		pcs.removePropertyChangeListener(propName, pcl);
	}
}
