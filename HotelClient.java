

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import RoomManager.RoomManager;
import RoomManangerImp.GuestDetails;
import RoomManangerImp.RoomDetails;

public class HotelClient {
  private static RoomManager room_manager;
  private static String name;
  //"localhost/hotelserver";

  public static void get_rooms(){
    try {
         room_manager =  (RoomManager)Naming.lookup(name); 
        RoomDetails[] res = room_manager.rooms_list();
        System.out.println();
        System.out.println("\tBelow is a list of available rooms");
        System.out.println("====================================================================================");
        for (int i = 0; i < res.length; i++) {
            if(res[i].available_rooms>0){
                String room_word = res[i].available_rooms>1?"rooms":"room";
                
                System.out.println("  " + res[i].available_rooms + " " +     room_word  + " of Type " + res[i].room_type + " are available  for  " +res[i].room_price);
            }

        }
        System.out.println("===============================================================================");
        System.out.println();

      } catch (Exception e) {
       
       //System.out.println("Trouble " + e);
       failed_connection();
      }
  }

  public static void book_room(String [] args){
    
    try {
        room_manager =  (RoomManager)Naming.lookup(name); 
        RoomDetails[] res = room_manager.rooms_list();
        String formatted_arg = args[2];
         Boolean found = false;
        for (int i = 0; i < res.length;i++) {
            String room_string = res[i].room_type;
            if(room_string.equalsIgnoreCase(formatted_arg)){
                found = true;
                if(res[i].available_rooms>0){
                    System.out.println();
                    System.out.println("\t======Booking Process======");
                    System.out.println();
                    String details = room_manager.book_room(res[i].room_type, res[i].room_price, args[3], i);
                    System.out.println();
                    System.out.println(details);
                    //get_guests();
                    System.out.println();
                    break;
                    
                }
                else{
                    System.out.println();
                    System.out.println("===============================================================");
                    System.out.println();
                    System.out.println();
                    System.out.println("\tDear " + args[3] + ", " + "We are sorry, but all rooms of type " + res[i].room_type + " are currently booked.");
                    get_rooms();
                    
                    

                }
            }

            // else{
            //   continue;
            // }
            
        }

        if(!found){
            System.out.println();
            System.out.println("===============================================================");
            System.out.println();
            System.out.println("\tDear Client we dont have rooms of  Type " + args[2] );
            System.out.println();
            get_rooms();
        }

    } catch (Exception e) {
        //System.out.println("Trouble " +e);
        failed_connection();
    }
  }
  public static void failed_connection(){
    System.out.println();
    System.out.println("\tDear Client we could not connect to the remote server !! Please ensure that your passing localhost as the server address");
    System.out.println();
    System.out.println("\tPlease Take a look at how to use the command line again\t");
    usage();
    System.out.println();

  }
  public static void get_guests(){
    try {
        room_manager =  (RoomManager)Naming.lookup(name); 
        int total_guests =  room_manager.get_total_guest();
        if(total_guests ==0){
            System.out.println();
            System.out.println(" \tThere are no available bookings Yet");
             System.out.println("=============================================");
            System.out.println("\t Please see usage below to book ");
            usage();

        }
        else{
            ArrayList<GuestDetails> res =  room_manager.guests_list();
            System.out.println();
            System.out.println("\tGuest Name\t\tRoom Type\t\tRoom Price");
            System.out.println("========================================================================================================");
            for (int i = 0; i < res.size(); i++) { 
                System.out.println(" "+res.get(i).guest_name + "\t\t" + res.get(i).room_type + "\t\t" + res.get(i).room_price);
            }
            System.out.println("=======================================================================================================");
            System.out.println();
        }


      } catch (Exception e) {
       
       //System.out.println("Trouble" + e.getMessage());
       failed_connection();
      }
  }

  
   public static void usage(){
    System.out.println();
    System.out.println("===================================================================================================");
    System.out.println();
    System.out.println("\tDear Client Welcome to the Hotel Reservation System\t");
    System.out.println();
    System.out.println(" \tYou can use the command line as follows");
    System.out.println();
    System.out.println(" java HotelClient list <server-address> ==> To Show a list of available rooms in price range");
    System.out.println(" java HotelClient book <server-address> <room type> <guest name> ==>To book a room of a certain type");
    System.out.println(" java HotelClient guests <server-address> ==>To Show a list of registered guests");
    System.out.println(" java HotelClient revenue <server-address> ==>To print revenue breakdown");
    System.out.println();
    System.out.println("  Note: To pass arguments inform of a string please use double quotes forexample \"Katende Nicholas\"");
    System.out.println("  Note: The server runs on port localhost");
    System.out.println();
    System.out.println("========================================================================================================");
    System.out.println();
   }


   public static void sorry(){
    System.out.println();
    System.out.println("\tDear Client we didnot understand your request");
    System.out.println();
    System.out.println("\tPlease Take a look at how to use the command line again\t");
    usage();
    System.out.println();
   }

   public static void missingParameters(){
     System.out.println();
     System.out.println("\tOops   Very few arguments passed for booking");
     System.out.println();
     System.out.println("\tPlease Take a look at how to use the command line again");
    usage();
    System.out.println();
   }

   public static void getOptions(int num, String []args) throws RemoteException, MalformedURLException, NotBoundException{
    

    switch (num) {
        case 0:
            usage();
            break;
        case 2:
            name= args[1];
            if(args[0].equalsIgnoreCase("list")){
                get_rooms();
            }
            else if(args[0].equalsIgnoreCase("guests")){
               get_guests();
            }
            else if(args[0].equalsIgnoreCase("book") && args.length<4 ){
                missingParameters();
            }
            else if(args[0].equalsIgnoreCase("revenue")){
                check_revenue_generated();
            }
            else{
              sorry();
            }
            break;
        case 4:
            name= args[1];
            if(args[0].equalsIgnoreCase("book")){
                book_room(args);
            }
            else{
                sorry();
            }

            break;
        default:
             sorry();
            break;
    }
   }

   public static void check_revenue_generated(){
     try {
        room_manager =  (RoomManager)Naming.lookup(name); 
        int total_guests =  room_manager.get_total_guest();
        if(total_guests ==0){
            System.out.println();
            System.out.println("\tNo Revenue Generated Yet !! Please book to generate revenue");
             System.out.println();
            System.out.println(" \tPlease see usage below to book ");
            System.out.println(); 
            usage();

        }
        else{

            RoomDetails [] revenue = room_manager.revenue();
            int total_amount = 0;
            System.out.println();
            System.out.println("\tRoom Type\tBooked Rooms\tRoom Price\t\t\tTotal Room Amount");
            System.out.println("=================================================================================================");
            System.out.println();
            for (int i = 0; i < revenue.length; i++) {
                if(revenue[i].booked_rooms ==0){
                    continue;
                }
                else{
                    
                    int room_total  =  Integer.parseInt(revenue[i].room_price.split(",",2)[0].concat("000"));
                    total_amount += room_total*revenue[i].booked_rooms;
                  System.out.println("\t"+revenue[i].room_type + " \t\t" + revenue[i].booked_rooms +" \t\t"+ revenue[i].room_price + " \t\t\t" + revenue[i].booked_rooms*room_total );  
                }
            }
            System.out.println();
            System.out.println("\tTotal Revenue Amount " + "\t\t\t\t"+total_amount);
            System.out.println("====================================================================================================");
            

        }
     } catch (Exception e) {
        
        failed_connection();
     }

   }

    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException  {
       
        getOptions(args.length, args);
    
      
    }
    
}
