package ru.skillbox;

public class Elevator {
    private int currentFloor = 1;
    private int minFloor = 0;
    private int maxFloor = 0;

    public Elevator(int minFloor, int maxFloor){
        this.minFloor = minFloor;
        this.maxFloor =maxFloor;
    }

    public int getCurrentFloor(){
        return currentFloor;
    }

    public void moveDown(){
        currentFloor = currentFloor > minFloor ? currentFloor - 1 : currentFloor;
        System.out.println("Current floor: " + getCurrentFloor());
    }

    public void moveUp(){
        currentFloor = currentFloor < maxFloor ? currentFloor + 1 : currentFloor;
        System.out.println("Current floor: " + currentFloor);
    }

    public void move(int floor){
        boolean getError = false;
        if (floor > maxFloor || floor < minFloor){
            getError = true;
            System.out.println("Error");
        }

        if (floor > currentFloor && !getError){
            for (int i = currentFloor; i < floor; i += 1){
                moveUp();
            }
        } else if (floor < currentFloor && !getError) {
            for (int i = currentFloor; i > floor; i -= 1){
                moveDown();
            }
        } else if (!getError){
            System.out.println("You are already on the " + floor + "th floor");
        }
    }
}
