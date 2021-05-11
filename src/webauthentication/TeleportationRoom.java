package webauthentication;
import java.util.Random;
import java.util.ArrayList;
/**
 * Write a description of class TeleportationRoom here.
 *
 * @author Ross Friar
 * @version Refactored
 */
public class TeleportationRoom extends Room
{
    private String description;
    private Random rng;
    private Room[] arr;
    private ArrayList<Room> Rooms;
    /**
     * Constructor for objects of class TeleportationRoom
     * @param description
     * @param listOfRooms
     */
    public TeleportationRoom(String description, ArrayList <Room> listOfRooms)
    {
        super(description);
        rng= new Random();
        Rooms= listOfRooms;
    }

    /**
     * Returns a random Room to get teleported to
     * 
     * @param direction
     * @param numberOfRooms
     * @return nextRoom which is the next room the player gets sent to
     */
    public Room getExit(String direction, int numberOfRooms){
        int roomToGoTo=0;
        roomToGoTo=rng.nextInt(Rooms.size());
        Room nextRoom=Rooms.get(roomToGoTo);
        System.out.println("In laboratory");
        return nextRoom;
    }
}