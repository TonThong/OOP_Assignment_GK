import java.util.ArrayList;

public class Resort extends CommonAccommodation {
    /* Code here */
    private int star;
    private boolean hasPool;

    public Resort(int id, 
                String name, 
                String address, 
                String city, 
                ArrayList<Room> listRoom, 
                float rate, 
                int star, 
                boolean hasPool){
        super(id, name, address, city, listRoom, rate);
        this.star = star;
        this.hasPool = hasPool;    
    }

    public Resort(int id, 
                String name, 
                String address, 
                String city, 
                float rate, 
                int star, 
                boolean hasPool){
        super(id, name, address, city, rate);
        this.star = star;
        this.hasPool = hasPool;    
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public void setHasPool(boolean hasPool) {
        this.hasPool = hasPool;
    }

    public boolean isHasPool() {
        return hasPool;
    }

    @Override
    public String toString() {
        return "Resort ["+this.getId()+
        ", "+this.getName()+
        ", "+this.getStar()+
        ", "+this.getAddress()+
        ", "+this.isHasPool()+
        ", "+this.getCity()+"]";
    }

}
