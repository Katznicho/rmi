package RoomManangerImp;

import java.io.Serializable;

public class GuestDetails implements Serializable{
    public String guest_name;
    public String room_price;
    public String room_type;
    public static int total_guests = 0;

    public GuestDetails(String guest_name , String room_price , String room_type){
        this.guest_name = guest_name;
        this.room_price =  room_price;
        this.room_type = room_type;
    }
    
}
