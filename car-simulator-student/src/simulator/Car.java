/*
 * Copyright 2017 Marc Liberatore.
 */

package simulator;

public class Car {
	public final int number;
	private final RoadMap roadMap;
	private int x;
	private int y;
	private String checkDirection;
	
	public Car(int number, RoadMap roadMap, int x, int y) {
		this.number = number;
		this.roadMap = roadMap;
		this.x = x;
		this.y = y;
		this.checkDirection="Not moving";
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
    
	
		
	
	/**
	 * Move the car. Cars only move along the cardinal directions
	 * (north/south/east/west), not diagonally.
	 * 
	 * If the car is stopped (that is, if it was just placed, or if it didn't
	 * move last time move() was called), then it should attempt to move north.
	 * If it cannot (no road, or off the map), then it should attempt east,
	 * then south, then west. If no move is available, it should stay in its
	 * current position.
	 * 
	 * If the car is moving (that is, if it successfully moved the last time
	 * move() was called), then it should attempt to continue moving in the same
	 * direction.
	 * 
	 * If it cannot (no road, or off the map), then it should attempt to turn
	 * right. For example, if the car was moving north, but there is no more
	 * road to the north, it should move east if possible.
	 * 
	 * If it cannot turn right, it should turn left. If it cannot turn left, it
	 * should reverse direction (that is, move backward, if possible). 
	 * If it cannot do any of these things, it should stay in its current position.
	 */
	
	
	
	
	public void move() {
	   if (checkDirection.equals("Not moving")) {
			if(roadMap.isRoad(getX(),getY()-1)) {
				y=y-1;
				checkDirection="North";
			}
			else if(roadMap.isRoad(getX()+1,getY())) {
				x=x+1;
				checkDirection="East";
			}
			else if(roadMap.isRoad(getX(),getY()+1)) {
				y=y+1;
				checkDirection="South";
			}
			else if(roadMap.isRoad(getX()-1,getY())) {
				x=x-1;
				checkDirection="West";
			}
		 
		}
		
	else if (checkDirection.equals("North")){
			if(roadMap.isRoad(getX(),getY()-1)) {
					y=y-1;
					checkDirection="North";
			}
			else if(roadMap.isRoad(getX()+1,getY())) {
					x=x+1;
					checkDirection="East";
			}
			else if(roadMap.isRoad(getX()-1,getY())) {
					x=x-1;
					checkDirection="West";
			}
			else if(roadMap.isRoad(getX(),getY()+1)) {
					y=y+1;
					checkDirection="South";
			}
			else {
				checkDirection="Not moving";
			}
					
		}
		
	else if (checkDirection.equals("East")) {
		if(roadMap.isRoad(x+1,y)) {
				x=x+1;
				checkDirection="East";
		}
			else if(roadMap.isRoad(getX(),getY()+1)) {
				y=y+1;
				checkDirection="South";
		}
			else if(roadMap.isRoad(getX(),getY()-1)) {
				y=y-1;
				checkDirection="North";
		}
			else if(roadMap.isRoad(getX()-1,getY())) {
				x=x-1;
				checkDirection="West";
			}
		}
		
	else if (checkDirection.equals("West")) {
		if(roadMap.isRoad(getX()-1,getY())) {
				x=x-1;
				checkDirection="West";
		}
			else if(roadMap.isRoad(getX(),getY()-1)) {
				y=y-1;
				checkDirection="North";
		}
			else if(roadMap.isRoad(getX(),getY()+1)) {
				y=y+1;
				checkDirection="South";
		}
			else if(roadMap.isRoad(getX()+1,getY())) {
				x=x+1;
				checkDirection="East";
		}
			else {
				checkDirection="Not moving";
		    }
     }
	
   else if (checkDirection.equals("South")) {
	   if(roadMap.isRoad(getX(),getY()+1)) {
		   		y=y+1;
		   		checkDirection="South";
	   }
	   		else if(roadMap.isRoad(getX()-1,getY())) {
	   			x=x-1;
	   			checkDirection="West";
	   }
	   		else if(roadMap.isRoad(getX()+1,getY())) {
	   			x=x+1;
	   			checkDirection="East";
	   }
	   		else if(roadMap.isRoad(getX(),getY()-1)) {
	   			y=y-1;
	   			checkDirection="North";
	   }
	   		else {
	   			checkDirection="Not moving";
	   		}
   		}
	}
}


			