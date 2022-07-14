package RoomManangerImp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import RoomManager.RoomManager;

public class RoomManagerImp extends UnicastRemoteObject implements RoomManager{
   
    //create details about rooms
    RoomDetails[] roomDetails = new RoomDetails[5];
    ArrayList<GuestDetails> guestDetails =  new ArrayList<GuestDetails>();

    public RoomManagerImp() throws RemoteException {
        super();
        roomDetails[0] =  new RoomDetails("0", "550,000 UGX per night", 10,
         10,    0);
        roomDetails[1] =  new RoomDetails("1", "75,000 UGX per night", 20, 20, 0);
        roomDetails[2] =  new RoomDetails("2", "80,000 UGX per night", 5, 5, 0);
        roomDetails[3] =  new RoomDetails("3", "150,000 UGX per night", 3, 3, 0);
        roomDetails[4] =  new RoomDetails("4", "230,000 UGX per night", 2, 2, 0);
    }

     @Override
    public String book_room(String room_type,String room_price, String guest_name, int room_pos){
         GuestDetails.total_guests +=1;
         guestDetails.add(new GuestDetails(guest_name, room_price, room_type));
         roomDetails[room_pos].available_rooms =  roomDetails[room_pos].available_rooms-1;
         roomDetails[room_pos].booked_rooms =  roomDetails[room_pos].booked_rooms+1;
        return " " + guest_name +  " has booked a room of Type " + room_type + " at a price of " + room_price;
    
    }
    @Override
    public RoomDetails[] rooms_list(){
        return roomDetails;
    }
    

    @Override
    public ArrayList<GuestDetails> guests_list(){
        return guestDetails;
        
    }
    @Override
    public RoomDetails[] revenue(){
        return roomDetails;
    }

    @Override
    public int get_total_guest(){
        return GuestDetails.total_guests;
    }
    

    
}
