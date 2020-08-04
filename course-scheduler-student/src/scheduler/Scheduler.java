/*
 * Copyright 2017 Marc Liberatore.
 * Modified 2018 David Wemhoener.
 */

package scheduler;

import java.util.List;
import java.util.ArrayList;

public class Scheduler {
	private List<Course>Scheduler;
	private List<Student>Newstudent;
	/**
	 * Instantiates a new, empty scheduler.
	 */
	public Scheduler() {
	this.Scheduler=new ArrayList<Course>();
	this.Newstudent=new ArrayList<Student>();
	}
	
	/**
	 * Adds a course to the scheduler.
	 * 
	 * @param course  the course to be added
	 */
	public void addCourse(Course course) {
	Scheduler.add(course);
	}
	
	/** 
	 * Returns the list of courses that this scheduler knows about.
	 * 
	 * This returned object does not share state with the internal state of the Scheduler.
	 *
	 * @return the list of courses
	 */
	public List<Course> getCourses() {
		return new ArrayList<>(Scheduler);
	}
	
	/**
	 * Adds a student to the scheduler.
	 * 
	 * @param student the student to add
	 */
	public void addStudent(Student student) {
	Newstudent.add(student);
	}
	
	/**
	 * Returns a list of the students this scheduler knows about.
	 * 
	 * This returned object does not share state with the internal state of the Scheduler.
	 * @return
	 */
	public List<Student> getStudents() {	
		return new ArrayList<>(Newstudent);
	}
	
	public void assign(Course course,Student student) {
		student.addCourse(course);
	    course.addStudent(student);
	  }
		
	private boolean canAssign(Course course,Student student) {
		boolean can=false;
		if((Scheduler.contains(course)) && !(course.getRoster().contains(student)) && !(student.getSchedule().contains(course)) && (course.getRoster().size()-course.getCapacity()!=0)){
			can=true;
			}
		return can;
	}
	
	
	/**
	 * Assigns all students to courses in the following manner:
	 * 
	 * For a given student, check their list of preferred courses. Add them to the course that:
	 * 	 - exists in the scheduler's list of courses
	 *   - the student most prefers (that is, comes first in their preference list)
	 *   - the student is not already enrolled in
	 *   - and is not full (in other words, at capacity)
	 * Adds courses to the *end* of the student's current list of classes. Adds students to 
	 * the *end* of the course's roster.
	 *   
	 * Repeat this process for each student, one-by-one; each student will now have one course,
	 * usually (but not always) their most preferred course.
	 * 
	 * Then repeat this whole process (adding one course per student, when possible, proceeding
	 * round-robin among students), until there is nothing left to do: Students might 
	 * all be at their maximum number of courses, or there may be no available seats in courses 
	 * that students want.
	 */
	public void assignAll() {
		Course temp;
		Student stu;
		int lastCourse;
		int full=0;
		
		while(full<=Newstudent.size()) {
			for(Student i: Newstudent) {
				stu=i;
				lastCourse=stu.getPreferences().size()-1;
				if(stu.getSchedule().size()>=stu.getMaxCourses()) {
					full++;
					continue;
				}
			   for(int n=0;n<stu.getPreferences().size();n++) {
						temp=stu.getPreferences().get(n);
						   if(n==lastCourse) { 
							   if(!(canAssign(temp,stu))) {
								   full++;
								   break;
							   }
							   else {
								   assign(temp,stu);
								   full++;
								   break;
							   }
						   }
						   else if(!(canAssign(temp,stu))) {
							   continue;
						   }
						   else if(canAssign(temp,stu)) {
							   assign(temp,stu);
							   break;
						   }
				  }
			}
		}
	}
								
	
	/**
	 * Drops a student from a course.
	 * 
	 * @param student
	 * @param course 
	 * @throws IllegalArgumentException  if either the student or the course are not known to this scheduler
	 */
	public void drop(Student student, Course course) throws IllegalArgumentException {
		if(!Scheduler.contains(course))
			throw new IllegalArgumentException();
		if(!Newstudent.contains(student))
			throw new IllegalArgumentException();
	    if(!course.getRoster().isEmpty() && !student.getSchedule().isEmpty()) {
	    	course.removeStudent(student);
	    	student.removeCourse(course);
	    } 
	 }

	

	
    /**
     * Calculates the number of students interested in a course
     * 
     * @param course
     * @throws IllegalArgumentException  if the course is not known to this scheduler
     * @return
     */
    public int interestLevel(Course course) throws IllegalArgumentException{
       int count=0;
    	if(!Scheduler.contains(course)) 
    	   throw new IllegalArgumentException();
         if(!Newstudent.isEmpty()) {
    	for(Student a: Newstudent) {
    	   if(a.getPreferences().contains(course)) 
    		   count++;
    	   }
    	}
       return count;
    }
}
