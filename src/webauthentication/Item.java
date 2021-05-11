package webauthentication;

/**
 * Write a itemDescription of class Item here.
 *
 * @author Ross Friar
 * @version 22 October 2019
 */
public class Item {

    private String itemName;
    private String itemDescription;
    private int itemWeight;

    /**
     * Constructor for objects of class Item
     */
    public Item() {
        itemName = "";
        itemDescription = "";
        itemWeight = 0;
    }

    /**
     * Constructor with item description and weight
     *
     * @param name
     * @param description
     * @param weight
     */
    public Item(String name, String description, int weight) {
        itemName = name;
        itemDescription = description;
        itemWeight = weight;
    }

    /**
     * Give an item a name
     *
     * @param newItemName
     */
    public void setItemName(String newItemName) {
        itemName = newItemName;
    }

    /**
     * Sets the itemDescription of the item.
     *
     * @param newItemDescription
     */
    public void setItemDescription(String newItemDescription) {
        itemDescription = newItemDescription;
    }

    /**
     * Sets the itemWeight of the item.
     *
     * @param newItemWeight
     */
    public void setItemWeight(int newItemWeight) {
        itemWeight = newItemWeight;
    }

    /**
     * Gets the itemName of the item
     *
     * @return returns itemName of the item
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Gets the itemDescription of the item.
     *
     * @return returns the itemDescription of the item
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * Gets the itemWeight of the item.
     *
     * @return returns the itemWeight of item
     */
    public int getItemWeight() {
        return itemWeight;
    }
}
