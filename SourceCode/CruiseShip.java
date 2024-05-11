public class CruiseShip extends LuxuryAccommodation {
    /* Code here */
    private boolean hasBar;

    public CruiseShip(int id, 
                    String name, 
                    String address, 
                    String city,
                    boolean hasPool,
                    boolean serveWelcome,
                    boolean freeBreakfast,
                    boolean hasGym,
                    int maxOfPeople,
                    int cost, 
                    boolean hasBar){
        super(id, name, address, city, hasPool, serveWelcome, freeBreakfast, hasGym, maxOfPeople, cost);
        this.hasBar = hasBar;
    }

    public boolean isHasBar() {
        return hasBar;
    }

    public void setHasBar(boolean hasBar) {
        this.hasBar = hasBar;
    }

    @Override
    public String toString() {
        return "CruiseShip ["+this.getId()+", "+
        this.getName()+", "+
        this.getAddress()+", "+
        this.getCity()+", "+
        this.isHasBar()+", "+
        this.isHasPool()+", "+
        this.isServeWelcome()+", "+
        this.isFreeBreakfast()+", "+
        this.isHasGym()+"]";
    }
}
