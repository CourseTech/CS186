/*
 * Copyright 2017 Marc Liberatore.
 * Modified 2018 David Wemhoener
 */

package scheduler;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a student.
 * 
 * @author liberato
 *
 */
public class Student {
	private String name;
	private int id;
	private int maxCourses;
	private List<Course>preferences;
	private List<Course>schedule;
	/**
	 * 
	 * Instantiates a new Student object. The student's maximum course load must be greater
	 * than zero, and the preferences list must contain at least one course.
	 * 
	 * The preference list is copied into this Student object.
	 * 
	 * @param name        the student's name
	 * @param int         the student's id
	 * @param maxCourses  the maximum number of courses that can be on this student's schedule
	 * @param preferences the student's ordered list of preferred courses
	 * @param maxCourse 
	 * @throws IllegalArgumentException thrown if the maxCourses or preferences are invalid
	 */
	public Student(String name, int id, int maxCourses, List<Course> preferences ) throws IllegalArgumentException {
		if(maxCourses<=0)
			throw new IllegalArgumentException();
		if(preferences.isEmpty())
			throw new IllegalArgumentException();
	this.name=name;
	this.id=id;
	this.maxCourses=maxCourses;	
	this.preferences=preferences;	
	this.schedule=new ArrayList<Course>();
	
	}
	
	/**
	 * 
	 * @return the student's name
	 */
	public String getName() {
		return name;
	}
	
	public void remove(Course c) {
		this.schedule.remove(c);
	}
	
	public void removeCourse(Course a) {
		if(!schedule.isEmpty())
		schedule.remove(a);
	}
	
    /**
     * 
     * @return the student's id
     */
    public int getID() {
        return id;
    }
	
	/**
	 * 
	 * @return the student's max course load
	 */
	public int getMaxCourses() {
		return maxCourses;
	}
	
	public void addCourse(Course course) {
		if(schedule.size()<maxCourses)
		schedule.add(course);
	}
	
	
	/**
	 * Returns the student's list of course preferences, ordered from most- to least-desired.
	 * 
	 * This returned object does not share state with the internal state of the Student.
	 * 
	 * @return the student's preference list
	 */
	public List<Course> getPreferences() {
		return new ArrayList<>(preferences);
	}
	
	
	
	/**
	 * Returns the student's current schedule.
	 * 
	 * This returned object does not share state with the internal state of the Student.
     *
	 * @return the student's schedule
	 */
	public List<Course> getSchedule() {
		return new ArrayList<>(schedule);
	}
}
