import java.util.ArrayList;

public class CommonAccommodation extends Accommodation {
   /* Code here */
   protected ArrayList<Room> listRoom;
   protected float rate;

   public CommonAccommodation(int id, String name, String address, String city, ArrayList<Room> listRoom, float rate){
      super(id, name, address, city);
      this.listRoom= listRoom;
      this.rate= rate;
   }
   
   public CommonAccommodation(int id, String name, String address, String city, float rate){
      super(id, name, address, city);
      this.rate= rate;
   }

   public ArrayList<Room> getListRoom() {
       return listRoom;
   }

   public void setListRoom(ArrayList<Room> listRoom) {
       this.listRoom = listRoom;
   }

   public float getRate() {
       return rate;
   }

   public void setRate(float rate) {
       this.rate = rate;
   }

}
