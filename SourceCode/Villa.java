public class Villa extends LuxuryAccommodation {
    /* Code here */

    public Villa(int id, 
                String name, 
                String address, 
                String city,
                boolean hasPool,
                boolean serveWelcome,
                boolean freeBreakfast,
                boolean hasGym,
                int maxOfPeople,
                int cost){
        super(id, 
        name, 
        address, 
        city, 
        hasPool, 
        serveWelcome, 
        freeBreakfast, 
        hasGym, 
        maxOfPeople, 
        cost);
    }

    @Override
    public String toString() {
        return "Villa ["+this.getId()+", "+
        this.getName()+", "+
        this.getAddress()+", "+
        this.getCity()+", "+
        this.isHasPool()+", "+
        this.isServeWelcome()+", "+
        this.isFreeBreakfast()+", "+
        this.isHasGym()+", "+
        this.getMaxOfPeople()+", "+
        this.getCost()+"]";
    }
}
