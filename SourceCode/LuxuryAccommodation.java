public class LuxuryAccommodation extends Accommodation {
    /* Code here */
    protected boolean hasPool;
    protected boolean serveWelcome;
    protected boolean freeBreakfast;
    protected boolean hasGym;
    protected int maxOfPeople;
    protected int cost;

    public LuxuryAccommodation(int id, String name, String address, String city){
        super(id, name, address, city);
    }

    public LuxuryAccommodation(int id, 
                            String name, 
                            String address, 
                            String city,
                            boolean hasPool,
                            boolean serveWelcome,
                            boolean freeBreakfast,
                            boolean hasGym,
                            int maxOfPeople,
                            int cost){
        super(id, name, address, city);
        this.hasPool = hasPool;
        this.serveWelcome = serveWelcome;
        this.freeBreakfast = freeBreakfast;
        this.hasGym = hasGym;
        this.maxOfPeople = maxOfPeople;
        this.cost = cost;
    }

    public boolean isHasPool() {
        return hasPool;
    }

    public void setHasPool(boolean hasPool) {
        this.hasPool = hasPool;
    }

    public boolean isFreeBreakfast() {
        return freeBreakfast;
    }

    public void setFreeBreakfast(boolean freeBreakfast) {
        this.freeBreakfast = freeBreakfast;
    }

    public boolean isHasGym() {
        return hasGym;
    }

    public void setHasGym(boolean hasGym) {
        this.hasGym = hasGym;
    }

    public boolean isServeWelcome() {
        return serveWelcome;
    }

    public void setServeWelcome(boolean serveWelcome) {
        this.serveWelcome = serveWelcome;
    }
    
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    
    public int getMaxOfPeople() {
        return maxOfPeople;
    }

    public void setMaxOfPeople(int maxOfPeople) {
        this.maxOfPeople = maxOfPeople;
    }

}
