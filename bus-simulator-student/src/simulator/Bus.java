package simulator;

public class Bus {
	public final int number;
	private final RoadMap roadMap;
	private int x;
	private int y;
	private String direction;
	
	public Bus(int number, RoadMap roadMap, int x, int y) {
		this.number = number;
		this.roadMap = roadMap;
		this.x = x;
		this.y = y;
		this.direction="Stopped";
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	/**
	 * Move the bus. Buses only move along the cardinal directions
	 * (north/south/east/west), not diagonally.
	 * 
	 * If the bus is stopped (that is, if it was just placed, or if it didn't
	 * move last time move() was called), then it should attempt to move north.
	 * If it cannot (no road, or off the map), then it should attempt south,
	 * then east, then west. If no move is available, it should stay in its
	 * current position.
	 * 
	 * If the bus is moving (that is, if it successfully moved the last time
	 * move() was called), then it should attempt to continue moving in the same
	 * direction.
	 * 
	 * If it cannot (no road, or off the map), then it should attempt to turn
	 * right. For example, if the bus was moving north, but there is no more
	 * road to the north, it should move east if possible.
	 * 
	 * If it cannot turn right, it should turn left. If it cannot turn left, it
	 * should reverse direction (that is, move backward, if possible). 
	 * If it cannot do any of these things, it should stay in its current position.
	 */
	//(0,1)
	public void move() {
		if (direction=="Stopped") {
			if(roadMap.isRoad(x,y-1)) {
				y--;
				direction="North";
			}
			else if(roadMap.isRoad(x,y+1)) {
				y++;
				direction="South";
			}
			else if(roadMap.isRoad(x+1,y)) {
				x++;
				direction="East";
			}
			else if(roadMap.isRoad(x-1,y)) {
				x--;
				direction="West";
			}
		}
		else if (direction=="North"){
			if(roadMap.isRoad(x,y-1)) {
					y--;
					direction="North";
			}
			else if(roadMap.isRoad(x+1,y)) {
					x++;
					direction="East";
			}
			else if(roadMap.isRoad(x-1,y)) {
					x--;
					direction="West";
			}
			else if(roadMap.isRoad(x,y+1)) {
					y++;
					direction="South";
			}
			else {
			direction="Stopped";
			}
					
		}
		
			else if (direction=="East") {
			if(roadMap.isRoad(x+1,y)) {
					x++;
					direction="East";
			}
			else if(roadMap.isRoad(x,y+1)) {
					y++;
					direction="South";
			}
			else if(roadMap.isRoad(x,y-1)) {
					y--;
					direction="North";
			}
			else if(roadMap.isRoad(x-1,y)) {
					x--;
					direction="West";
			}
			}
			
			else if (direction=="West") {
			if(roadMap.isRoad(x-1,y)) {
					x--;
					direction="West";
			}
			else if(roadMap.isRoad(x,y-1)) {
					y--;
					direction="North";
			}
			else if(roadMap.isRoad(x,y+1)) {
					y++;
					direction="South";
		}
			else if(roadMap.isRoad(x+1,y)) {
				x++;
				direction="East";
		}
			else {
				direction="Stopped";
			}
}
		
		else if (direction=="South") {
		if(roadMap.isRoad(x,y+1)) {
				y++;
				direction="South";
		}
		else if(roadMap.isRoad(x-1,y)) {
				x--;
				direction="West";
		}
		else if(roadMap.isRoad(x+1,y)) {
				x++;
				direction="East";
		}
		else if(roadMap.isRoad(x,y-1)) {
			y--;
			direction="North";
		}
		else {
			direction="Stopped";
		}
	}
		}
	}

		
			

