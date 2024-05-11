import java.util.ArrayList;

public class Hotel extends CommonAccommodation {
    /* Code here */
    private int star;

    public Hotel(int id, String name, String address, String city, ArrayList<Room> listRoom, float rate, int star){
        super(id, name, address, city, listRoom, rate);
           this.star = star;
    }

    public Hotel(int id, String name, String address, String city, float rate, int star){
        super(id, name, address, city, rate);
        this.star = star;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    @Override
    public String toString() {
        return "Hotel ["+this.getId()+
        ", "+this.getStar()+
        ", "+this.getName()+
        ", "+this.getAddress()+
        ", "+this.getCity()+"]";
    }
}   
