public class Room {
    /* Code here */
    private int roomID;
    private String roomName;
    private int numberOfFloor;
    private String typeOfRoom;
    private int numberOfBed;
    private int maxOfPeople;
    private double area;
    private double cost;

    public Room(int roomID,
                String roomName,
                int numberOfFloor,
                String typeOfRoom,
                int numberOfBed,
                int maxOfPeople,
                double area,
                double cost){
        this.roomID = roomID;
        this.roomName = roomName;
        this.numberOfFloor = numberOfFloor;
        this.typeOfRoom = typeOfRoom;
        this.numberOfBed = numberOfBed;
        this.maxOfPeople = maxOfPeople;
        this.area = area;
        this.cost = cost;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getMaxOfPeople() {
        return maxOfPeople;
    }

    public void setMaxOfPeople(int maxOfPeople) {
        this.maxOfPeople = maxOfPeople;
    }

    public int getNumberOfBed() {
        return numberOfBed;
    }

    public void setNumberOfBed(int numberOfBed) {
        this.numberOfBed = numberOfBed;
    }

    public int getNumberOfFloor() {
        return numberOfFloor;
    }

    public void setNumberOfFloor(int numberOfFloor) {
        this.numberOfFloor = numberOfFloor;
    }
    
    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getTypeOfRoom() {
        return typeOfRoom;
    }

    public void setTypeOfRoom(String typeOfRoom) {
        this.typeOfRoom = typeOfRoom;
    }

    @Override
    public String toString() {
        return "Room ["+this.getRoomID()+
        ", "+this.getRoomName()+
        ", "+this.getNumberOfFloor()+
        ", "+this.getTypeOfRoom()+
        ", "+this.getNumberOfBed()+
        ", "+this.getMaxOfPeople()+
        ", "+this.getArea()+
        ", "+this.getCost()+"]";
    }
}
