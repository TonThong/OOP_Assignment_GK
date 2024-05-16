import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;

public class ReservationSystem {
    private ArrayList<Accommodation> accommodations;

    // Requirement 1: Load data
    public ReservationSystem(String accPath, String roomPath, String roomOfAccPath) {
        this.accommodations = loadAccommodations(accPath, roomPath, roomOfAccPath);
    }

    public ArrayList<Accommodation> getAccommodations() {
        return accommodations;
    }

    // Requirement 1
    public ArrayList<Accommodation> loadAccommodations(String accPath, String roomPath, String roomOfAccPath) {
        /* Code here */
        ArrayList<Accommodation> temp = new ArrayList<Accommodation>();
        ArrayList<String[]> accData = readFile(accPath);
        ArrayList<String[]> roomData =  readFile(roomPath);
        ArrayList<String[]> roomOfAccData =  readFile(roomOfAccPath);
        
        for (String[] acc :accData) {
            String tempTypeOfAcc;
            tempTypeOfAcc = acc[1];
            if (acc.length == 5) {
                tempTypeOfAcc = "Homestay";
            }else if(acc.length == 6) {
                tempTypeOfAcc = "Hotel";
            }else if(acc.length == 7) {
                tempTypeOfAcc = "Resort";
            }else if(acc.length == 11) {
                tempTypeOfAcc = "CruiseShip";
            }else if(acc.length == 10) {
                tempTypeOfAcc = "Villa";
            }

            switch (tempTypeOfAcc) {
                case "Homestay":
                    int idHomestay = Integer.parseInt(acc[0]);
                    float rateHomestay = Float.parseFloat(acc[4]);
                    CommonAccommodation tempHomestay =new Homestay(idHomestay, acc[1], acc[2], acc[3], rateHomestay);
                    tempHomestay = provideRoom(roomData, roomOfAccData, tempHomestay);
                    temp.add(tempHomestay);           
                    break; 
                case "Hotel":
                    int idHotel = Integer.parseInt(acc[0]);
                    int starHotel = Integer.parseInt(acc[4]);
                    float rateHotel = Float.parseFloat(acc[5]);
                    CommonAccommodation tempHotel = new Hotel(idHotel, acc[1], acc[2], acc[3], rateHotel, starHotel);
                    tempHotel = provideRoom(roomData, roomOfAccData, tempHotel);
                    temp.add(tempHotel);              
                    break; 
                case "Villa":
                    int idVilla = Integer.parseInt(acc[0]);
                    int maxOfPeopleVilla = Integer.parseInt(acc[8]);
                    int costVilla = Integer.parseInt(acc[9]);
                    boolean hasPoolVilla = false;
                    boolean serveWelcomeVilla = false;
                    boolean freeBreakfastVilla = false;
                    boolean hasGymVilla = false;
                    if(acc[4].compareTo("yes") == 0) hasPoolVilla = true;
                    if(acc[5].compareTo("yes") == 0) serveWelcomeVilla = true;
                    if(acc[6].compareTo("yes") == 0) freeBreakfastVilla = true;
                    if(acc[7].compareTo("yes") == 0) hasGymVilla = true;                   
                    temp.add(new Villa(idVilla, 
                                        acc[1], 
                                        acc[2], 
                                        acc[3], 
                                        hasPoolVilla, 
                                        serveWelcomeVilla,
                                        freeBreakfastVilla,
                                        hasGymVilla,
                                        maxOfPeopleVilla,
                                        costVilla));                
                    break;
                case "Resort":
                    int idResort = Integer.parseInt(acc[0]);
                    int starResort = Integer.parseInt(acc[4]);
                    float rateResort = Float.parseFloat(acc[6]);
                    boolean hasPoolResort = false;
                    if(acc[5].compareTo("yes") == 0) hasPoolResort = true;
                    CommonAccommodation tempResort = new Resort(idResort, acc[1], acc[2], acc[3], rateResort, starResort, hasPoolResort);
                    tempResort = provideRoom(roomData, roomOfAccData, tempResort);
                    temp.add(tempResort);                
                    break; 
                case "CruiseShip":
                    int idCruiseShip = Integer.parseInt(acc[0]);
                    int maxOfPeopleCruiseShip = Integer.parseInt(acc[9]);
                    int costCruiseShip = Integer.parseInt(acc[10]);
                    boolean hasPoolCruiseShip = false;
                    boolean serveWelcomeCruiseShip = false;
                    boolean freeBreakfastCruiseShip = false;
                    boolean hasGymCruiseShip = false;
                    boolean hasBarCruiseShip = false;
                    if(acc[4].compareTo("yes") == 0) hasPoolCruiseShip = true;
                    if(acc[5].compareTo("yes") == 0) serveWelcomeCruiseShip = true;
                    if(acc[6].compareTo("yes") == 0) freeBreakfastCruiseShip = true;
                    if(acc[7].compareTo("yes") == 0) hasGymCruiseShip = true;
                    if(acc[8].compareTo("yes") == 0) hasBarCruiseShip = true;
                    temp.add(new CruiseShip(idCruiseShip, 
                                        acc[1], 
                                        acc[2], 
                                        acc[3], 
                                        hasPoolCruiseShip, 
                                        serveWelcomeCruiseShip,
                                        freeBreakfastCruiseShip,
                                        hasGymCruiseShip,
                                        maxOfPeopleCruiseShip,
                                        costCruiseShip,
                                        hasBarCruiseShip));                
                    break;
                default:
                    break;
            }
        }
        return temp;
    }

    // Requirement 2
    public ArrayList<Accommodation> searchForRoom(String city, int numOfPeople) {
        /* Code here */
        ArrayList<Accommodation> temp = new ArrayList<Accommodation>();
        ArrayList<Accommodation> accommodationsList = this.getAccommodations();

        for (Accommodation acc : accommodationsList) {
            if(acc instanceof LuxuryAccommodation){
                if(acc.getCity().compareTo(city) == 0){
                    LuxuryAccommodation tempAcc =(LuxuryAccommodation)acc;
                    if (tempAcc.getMaxOfPeople() >= numOfPeople ){
                        temp.add(tempAcc);
                    }
                }
            }
        }

        for (Accommodation acc : accommodationsList) {
            if(acc instanceof CommonAccommodation){
                if(acc.getCity().compareTo(city) == 0){
                    CommonAccommodation tempAcc =(CommonAccommodation)acc;
                    ArrayList<Room> listRoom = tempAcc.getListRoom();
                    if ( listRoom != null){
                        for (Room room : listRoom) {
                            if (room.getMaxOfPeople() >= numOfPeople ){
                                temp.add(tempAcc);
                            }
                        }
                    }
                }
            }
        }

        return sortRequired2(temp);
    }

    // Requirement 3
    public ArrayList<Accommodation> searchForRoomByRange(String reservationPath, double priceFrom, double priceTo,
            Date checkin, Date checkout, String city, int numOfPeople) {
        /* Code here */
        ArrayList<Accommodation> tempList = searchForRoom(city, numOfPeople);
        ArrayList<String[]> resList = readFile(reservationPath);

        for(int j = 0 ; j < tempList.size() ; j++){
            if(tempList.get(j) instanceof LuxuryAccommodation){
                LuxuryAccommodation tempAcc =(LuxuryAccommodation)tempList.get(j);
                if(tempAcc.getMaxOfPeople()-numOfPeople>=2){
                    tempList.remove(j);
                    j--;
                    continue;
                }
                for(String[] res: resList){
                    if (res.length ==4){
                        // Integer idRes= Integer.parseInt(res[0]);
                        Integer idAcc= Integer.parseInt(res[1]);
                        Date resCheckin = new Date(Integer.parseInt(res[2]));
                        Date resCheckout =new Date(Integer.parseInt(res[3]));
                        if(idAcc == tempList.get(j).getId()){
                            if(
                            ((resCheckout.compareTo(checkin) <=0)||
                            (resCheckin.compareTo(checkout) >=0)) &&
                            (tempAcc.getCost()>= priceFrom)&&
                            (tempAcc.getCost()<= priceTo)&&
                            (tempAcc.getMaxOfPeople() - numOfPeople < 2)
                            ){
                                tempList.remove(j);
                                j--;
                                break;
                            }
                        }
                    }
                }
            }else{
                CommonAccommodation tempAcc =(CommonAccommodation)tempList.get(j);
                ArrayList<Room> tempRoomList = tempAcc.getListRoom();

                for (int i = 0; i < tempRoomList.size();i++) {
                    for (String[] res:resList){
                        if(res.length == 5){
                            Integer idAcc= Integer.parseInt(res[1]);
                            Integer idRoom= Integer.parseInt(res[2]);
                            Date resCheckin = new Date(Integer.parseInt(res[3]));
                            Date resCheckout =new Date(Integer.parseInt(res[4]));          
                            if(tempRoomList.get(i).getMaxOfPeople() - numOfPeople > 2){
                                tempRoomList.remove(i);
                                j--;
                                continue;
                            }
                            if(idAcc == tempAcc.getId()){
                                if(
                                (idRoom == tempRoomList.get(i).getRoomID()) &&
                                (!(resCheckout.compareTo(checkin) <=0)||
                                (resCheckin.compareTo(checkout) >=0)) &&
                                (tempRoomList.get(i).getCost()>= priceFrom)&&
                                (tempRoomList.get(i).getCost()<= priceTo)
                                ){
                                    tempRoomList.remove(i);
                                    j--;
                                    continue;
                                }
                            }
                        }
                    }
                }
            }
        }
        ArrayList<Accommodation> tempOutput= new ArrayList<Accommodation>();
        for (Accommodation acc : tempList){
            if(!tempOutput.contains(acc)){
                for(Accommodation tempAcc : emptyRoom(acc)){
                    tempOutput.add(tempAcc);
                }
            }
        }
        return sortRequired3(tempOutput);
    }

    // Requirement 4
    public ArrayList<Accommodation> searchInAdvance(String city, Integer numOfPeople, 
        String roomType, Boolean privatePool, Integer starQuality, Boolean freeBreakfast, Boolean privateBar) {
        /* Code here */
        ArrayList<Accommodation> tempAccList = searchForRoom(city, numOfPeople);
        Boolean tempPrivatePool = privatePool;
        Integer tempStarQuality = starQuality;
        Boolean tempFreeBreakfast = freeBreakfast;
        Boolean tempPrivateBar =privateBar;

        for(int i = 0 ; i < tempAccList.size();i++){
            privatePool = tempPrivatePool;
            starQuality = tempStarQuality;
            freeBreakfast = tempFreeBreakfast;
            privateBar = tempPrivateBar;
            if(CheckRequired(tempAccList.get(i), roomType, privatePool, starQuality, freeBreakfast, privateBar) == null){
                tempAccList.remove(i);
                i--;
                continue;
            }
            if(tempAccList.get(i) instanceof LuxuryAccommodation){
                LuxuryAccommodation temp = (LuxuryAccommodation)tempAccList.get(i);
                while(true){
                    if(privatePool == null){
                        privatePool = temp.isHasPool();
                    }
                    if(freeBreakfast == null){
                        freeBreakfast = temp.isFreeBreakfast();
                    }
                    if(privateBar == null){
                        CruiseShip tempCruiseShip = (CruiseShip)temp;
                        privateBar = tempCruiseShip.isHasBar();
                    }
                    break;
                }
            }else{
                CommonAccommodation temp = (CommonAccommodation)tempAccList.get(i);
                while(true){
                    if(temp instanceof Resort){
                        if(privatePool == null){
                            Resort tempResort = (Resort)tempAccList.get(i);
                            privatePool = tempResort.isHasPool();
                            starQuality = tempResort.getStar();
                        }
                    }
                    if(temp instanceof Hotel){
                        Hotel tempHotel = (Hotel)tempAccList.get(i);
                        starQuality = tempHotel.getStar();
                    }
                    break;
                }
                // for (Room tempRoom : temp.getListRoom()){
                for(int j = 0 ; j < temp.getListRoom().size();j++){
                    if(!roomType.equals(temp.getListRoom().get(j).getTypeOfRoom())){
                        temp.getListRoom().remove(j);
                        j--;
                    }
                    if(numOfPeople > temp.getListRoom().get(j).getMaxOfPeople()){
                        temp.getListRoom().remove(j);
                        j--;
                    }
                }
            }
            if(!isRequired(tempAccList.get(i), roomType, privatePool, starQuality, freeBreakfast, privateBar)){
                tempAccList.remove(i);
                i--;
                break;
            }
        }

        ArrayList<Accommodation> tempOutput= new ArrayList<Accommodation>();
        for (Accommodation acc : tempAccList){
            if(!tempOutput.contains(acc)){
                for(Accommodation tempAcc : emptyRoom(acc)){
                    tempOutput.add(tempAcc);
                }
            }
        }
        return tempOutput;
    }

    // Requirement 5
    public double performReservation(String reservationPath, Accommodation acc, Room room, Date checkin, Date checkout)
            throws Exception {
            
        /* Code here */
        ArrayList<Accommodation> accList = this.getAccommodations();
        ArrayList<String[]> resList =  readFile(reservationPath);
        double tempCost = 0.0;

        for (Accommodation accItem : accList){
            if(acc.getId() == accItem.getId()){
                acc =accItem;
                break;
            }
        }

        for(String[] res: resList){
            if(res.length == 5){
                CommonAccommodation tempAcc =(CommonAccommodation)acc;
                // Integer idRes= Integer.parseInt(res[0]);
                // Integer idAcc= Integer.parseInt(res[1]);
                Integer idRoom= Integer.parseInt(res[2]);
                Date resCheckin = new Date(Integer.parseInt(res[3]));
                Date resCheckout =new Date(Integer.parseInt(res[4]));

                ArrayList<Room> tempRoomList = tempAcc.getListRoom();
                
                for (Room tempRoom : tempRoomList) {
                    if(tempRoom.getRoomID() == room.getRoomID()){
                        tempCost = tempRoom.getCost();
                        if(
                        (idRoom == tempRoom.getRoomID()) &&
                        !((resCheckout.compareTo(checkin) <=0)||
                        (resCheckin.compareTo(checkout) >=0))
                        ){
                            throw new Exception("The room has already been booked during this time period");
                        }
                    }
                }
            }
        }
        
        String[] tempString = {String.valueOf(resList.size()+1),
                                String.valueOf(acc.getId()),
                                String.valueOf(room.getRoomID()),
                                String.valueOf(checkin.getTime()),
                                String.valueOf(checkout.getTime()),};

        resList.add(tempString);
        try {
            FileWriter writer = new FileWriter(reservationPath);
                for(String[] res : resList){
                    writer.write(String.join(",",res));
                    writer.write("\r\n");
            }

            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("Error.");
        } catch (Exception e) {
            System.out.println("Cannot write file");
        }

        long tempDay = diffBetweenDays(checkin.getTime(),checkout.getTime());
        double cost = tempCost * tempDay;
        double total = cost + cost*0.08;
        return total;
    }

    // Helper functions for req 5
    public long diffBetweenDays(long dateStart, long dateEnd) {
        Date date = new Date(dateStart * 1000);
        Date date1 = new Date(dateEnd * 1000);

        date = removeTime(date);
        date1 = removeTime(date1);

        long diff = Math.abs(date1.getTime() - date.getTime());
        long numOfDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        return numOfDays;
    }

    private Date removeTime(Date date) {
        long time = date.getTime();
        long timeWithoutTime = time - (time % (24 * 60 * 60 * 1000));
        return new Date(timeWithoutTime);
    }

    public ArrayList<String[]> readFile(String reservationPath){
        ArrayList<String[]> resList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(reservationPath))) {
            String tempLine;
            while ((tempLine = br.readLine()) != null) {
                String[] resInfo = tempLine.split(",");
                resList.add(resInfo);
            }
        } catch (IOException e) {e.printStackTrace();}
        return resList;
    }

    public CommonAccommodation provideRoom(ArrayList<String[]> roomData,ArrayList<String[]> roomOfAccData ,CommonAccommodation accommodation){
        for (String[] roomOfAcc : roomOfAccData){
            String id = roomOfAcc[0];
            String type = roomOfAcc[1];
            
            Integer idAccommodation = Integer.parseInt(id);
            Integer idOfAccommodation = accommodation.getId();
            if(idAccommodation.compareTo(idOfAccommodation) == 0){
                for(String[] room : roomData){
                    if (type.compareTo(room[0])==0){
                        int idRoom = Integer.parseInt(room[0]);
                        int numberOfFloor = Integer.parseInt(room[2]);
                        int numberOfBed = Integer.parseInt(room[4]);
                        int maxOfPeople = Integer.parseInt(room[5]);
                        double areaRoom = Double.parseDouble(room[6]);
                        double costRoom = Double.parseDouble(room[7]);
                        Room tempRoom = new Room(idRoom,
                                                room[1],
                                                numberOfFloor,
                                                room[3],
                                                numberOfBed,
                                                maxOfPeople,
                                                areaRoom,
                                                costRoom);
                        ArrayList<Room> listRoomExtra = accommodation.getListRoom() == null ? new ArrayList<Room>() : accommodation.getListRoom() ;   
                        listRoomExtra.add(tempRoom);

                        accommodation.setListRoom(listRoomExtra);
                    }
                }
            }
        }
        return accommodation;
    }

    public Accommodation CheckRequired(Accommodation accommodation,
    String roomType, Boolean privatePool, Integer starQuality, Boolean freeBreakfast, Boolean privateBar){
        if(accommodation instanceof LuxuryAccommodation){
            LuxuryAccommodation temp = (LuxuryAccommodation)accommodation;
            if(roomType != null){
                return null;
            }
            if(privateBar != null){
                if(!(temp instanceof CruiseShip)){
                    return null;
                }
            }
            if(starQuality != null){
                    return null;
            }
        }else{
            CommonAccommodation temp = (CommonAccommodation)accommodation;
            if(freeBreakfast != null){
                return null;
            }else if(privatePool != null){
                if(!(temp instanceof Resort)){
                    return null;
                }
            }else if(starQuality != null){
                if(!(temp instanceof Resort) || !(temp instanceof Hotel)){
                    return null;
                }
            }
        }
        return accommodation;
    }
    public boolean isRequired(Accommodation accommodation,
    String roomType, Boolean privatePool, Integer starQuality, Boolean freeBreakfast, Boolean privateBar){
        if(accommodation instanceof LuxuryAccommodation){
            LuxuryAccommodation temp = (LuxuryAccommodation)accommodation;
            if(privatePool != temp.isHasPool()){
                return false;
            }
            if(freeBreakfast != temp.isFreeBreakfast()){
                return false;
            }
            if(temp instanceof CruiseShip){
                CruiseShip tempCruise = (CruiseShip)temp;
                if(privateBar != tempCruise.isHasBar()){
                    return false;
                }
            }
        }else{
            CommonAccommodation temp = (CommonAccommodation)accommodation;
            if(roomType == null){
                if(temp instanceof Resort){
                    Resort tempResort = (Resort)temp;
                    if((privatePool != tempResort.isHasPool())||
                    (starQuality != tempResort.getStar())){
                        return false;
                    }
                }
                if(temp instanceof Hotel){
                    Hotel tempHotel = (Hotel)temp;
                    if(starQuality != tempHotel.getStar()){
                        return false;
                    }
                }
            }else{
                ArrayList<Room> tempRoomList =temp.getListRoom();
                for(Room tempRoom:tempRoomList){
                    if(roomType.compareTo(tempRoom.getTypeOfRoom()) == 0){
                        return true;
                    }
                }
            }
        }
        return true;
    }

    public ArrayList<Accommodation> emptyRoom(Accommodation acc){
        ArrayList<Accommodation> temp = new ArrayList<>();
        if(acc instanceof CommonAccommodation){
            CommonAccommodation tempAcc = (CommonAccommodation)acc;
            for (int i = 0 ; i < tempAcc.getListRoom().size();i++){
                temp.add(acc);
            }
        }else{
            temp.add(acc);
        }
        return temp;
    }
    public ArrayList<Accommodation> sortRequired2(ArrayList<Accommodation> accommodations){
        ArrayList<Accommodation> tempLuxury = new ArrayList<>();
        ArrayList<Accommodation> tempCommon = new ArrayList<>();
        ArrayList<String> nameLuxury = new ArrayList<>();
        ArrayList<String> nameCommon = new ArrayList<>();
        ArrayList<Accommodation> tempOutPut = new ArrayList<>();

        for (Accommodation acc:accommodations){
            if(acc instanceof LuxuryAccommodation){
                tempLuxury.add(acc);
            }
            if(acc instanceof CommonAccommodation){
                tempCommon.add(acc);
            }
        }

        for (Accommodation tempAcc : tempLuxury){
            nameLuxury.add(tempAcc.getName());
        }
        Collections.sort(nameLuxury, (o1, o2) -> o1.compareTo(o2));
        System.out.println(nameLuxury.toString());

        for (Accommodation tempAcc : tempCommon){
            nameCommon.add(tempAcc.getName());
        }
        Collections.sort(nameCommon, (o1, o2) -> o1.compareTo(o2));
        System.out.println(nameCommon.toString());

        for(String tempString : nameLuxury){
            for (Accommodation tempAcc : tempLuxury){
                if(tempAcc.getName().equals(tempString)){
                    tempOutPut.add(tempAcc);
                    break;
                }
            }
        }

        for(String tempString : nameCommon){
            for (Accommodation tempAcc : tempCommon){
                if(tempAcc.getName().equals(tempString)){
                    tempOutPut.add(tempAcc);
                    break;
                }
            }
        }

        return tempOutPut;
    }

    public ArrayList<Accommodation> sortRequired3(ArrayList<Accommodation> accommodations){
        ArrayList<String> name = new ArrayList<>();
        ArrayList<Accommodation> tempOutPut = new ArrayList<>();

        for (Accommodation tempAcc : accommodations){
            name.add(tempAcc.getName());
        }
        Collections.sort(name, (o1, o2) -> o2.compareTo(o1));
        System.out.println(name.toString());

        for(String tempString : name){
            for (Accommodation tempAcc : accommodations){
                if(tempAcc.getName().equals(tempString)){
                    tempOutPut.add(tempAcc);
                    break;
                }
            }
        }

        return tempOutPut;
    }

}
