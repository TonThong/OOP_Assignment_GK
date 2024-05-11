import java.util.ArrayList;

public class Homestay extends CommonAccommodation {
    /* Code here */
    public Homestay(int id, String name, String address, String city, ArrayList<Room> listRoom, float rate){
        super(id, name, address, city, listRoom, rate);
           
    }

    public Homestay(int id, String name, String address, String city, float rate){
        super(id, name, address, city, rate);
    }

    @Override
    public String toString() {
        return "Homestay ["+this.getId()+
        ", "+this.getName()+
        ", "+this.getAddress()+
        ", "+this.getRate()+
        ", "+this.getCity()+"]";
    }
}
