package RoomManager;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import RoomManangerImp.GuestDetails;
import RoomManangerImp.RoomDetails;

public interface RoomManager extends Remote{

    //create a method to book a room
    public String book_room(String room_type,String room_price, String guest_name, int room_pos) throws RemoteException;

    //show a list of available room prices
    public RoomDetails[] rooms_list() throws RemoteException;

    //list the names of all registered guests
    public ArrayList<GuestDetails> guests_list() throws RemoteException;

    public RoomDetails[] revenue() throws RemoteException;

    public int get_total_guest()throws RemoteException;





}
