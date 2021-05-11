package webauthentication;

import java.util.Set;
import java.util.HashMap;
// add items in the rooms as a hashmap

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. "World of Zuul" is a
 * very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game. It is connected
 * to other rooms via exits. For each existing exit, the room stores a reference
 * to the neighboring room.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Room {

    private String descriptionOfRoom;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private HashMap<String, Item> itemsInRoom;  // stores items of this room.

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "a kitchen" or "an open court yard".
     *
     * @param description The room's description.
     */
    public Room(String description) {
        descriptionOfRoom = description;
        exits = new HashMap<>();
        itemsInRoom = new HashMap<>();
    }

    /**
     * Define an exit from this room.
     *
     * @param direction The direction of the exit.
     * @param neighbor The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * Puts itema into a hashmap in the room.
     *
     * @param itemName
     * @param item
     */
    public void setItems(String itemName, Item item) {
        itemsInRoom.put(itemName, item);
    }

    /**
     * Returns the items in the room.
     *
     * @return the items in the room
     */
    public String getItems() {
        int i = 0;
        String copyOfItems = "";
        while (i < itemsInRoom.size()) {
            copyOfItems += itemsInRoom.get(0);
            i++;
        }
        return copyOfItems;
    }

    /**
     * @return The short description of the room (the one that was defined in
     * the constructor).
     */
    public String getShortDescription() {
        return descriptionOfRoom;
    }

    /**
     * Return a description of the room in the form: You are in the kitchen.
     * Exits: north west
     *
     * @return A long description of this room
     */
    public String getLongDescription() {
        return "You are " + descriptionOfRoom + ".\n" + getExitString() + ".\n" + getItemsInRoom();
    }

    /**
     * Return a string describing the room's exits, for example "Exits: north
     * west".
     *
     * @return Details of the room's exits.
     */
    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Returns a string of the items in the room.
     *
     * @return a string of the items in the room
     */
    private String getItemsInRoom() {
        String returnString = "Items:";
        Set<String> keys = itemsInRoom.keySet();
        for (String listItems : keys) {
            returnString += " " + listItems;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     *
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    /**
     * Returns the amount items in a room.
     *
     * @return the integer value of items in the room
     */
    public int getSize() {
        int total = itemsInRoom.size();
        return total;
    }

    /**
     * Returns a set of the items in the room.
     *
     * @return the set of items in the room
     */
    public Set getSet() {
        return itemsInRoom.keySet();
    }

    /**
     * Adds item to a room
     *
     * @param newItem
     */
    public void addItem(Item newItem) {
        itemsInRoom.put(newItem.getItemName(), newItem);
    }

    /**
     * Removes item from a room
     *
     * @param tItem
     * @return the item that was removed
     */
    public Item removeItem(Item tItem) {
        itemsInRoom.remove(tItem.getItemName());
        return tItem;
    }

    /**
     * Gets the item in the room.
     *
     * @param key
     * @return returns the item of the key
     */
    public Item getValue(String key) {
        Item value = itemsInRoom.get(key);
        return value;
    }

}
