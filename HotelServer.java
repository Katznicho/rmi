import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

import RoomManangerImp.RoomManagerImp;

public class HotelServer {
    private static String name = "localhost";
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        try {
            RoomManagerImp room_manager_imp =  new RoomManagerImp();
            Naming.rebind(name, room_manager_imp);
            System.out.println();
            System.out.println("============================================");
            System.out.println("Server ready!! Lets work on something amazing");
            System.out.println("==============================================");
            System.out.println();
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Trouble " + e.getMessage());


        }
    }
    
}
