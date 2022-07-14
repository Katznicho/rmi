package RoomManangerImp;

import java.io.Serializable;

public class RoomDetails implements Serializable{
   public String room_type;
   public String room_price;
   public  int booked_rooms ; 
   public int total_rooms;
   public int available_rooms;
   public RoomDetails(String room_type ,  String room_price ,int total_rooms , int available_rooms, int booked_rooms){
    this.room_type = room_type;
    this.room_price = room_price;
    this.total_rooms = total_rooms;
    this.available_rooms =  available_rooms;
    this.booked_rooms = booked_rooms;
    
   }
}
