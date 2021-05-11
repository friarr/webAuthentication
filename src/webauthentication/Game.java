package webauthentication;

import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;

/**
 * This class is the main class of the "World of Zuul" application. "World of
 * Zuul" is a very simple, text based adventure game. Users can walk around some
 * scenery. That's all. It should really be extended to make it more
 * interesting!
 *
 * To play this game, create an instance of this class and call the "play"
 * method.
 *
 * This main class creates and initialises all the others: it creates all rooms,
 * creates the parser and starts the game. It also evaluates and executes the
 * commands that the parser returns.
 *
 * @author Ross Friar
 * @version Refactored
 */
public class Game {

    private Parser parser;
    private Room currentRoom;
    private HashMap<String, Item> backpack;
    private Room previousRoom;
    private Room mansion;
    private Room lobby;
    private Room laboratory;
    private boolean isHelicopterCalled;
    private boolean isWon;
    private Room forest;
    private Room meadow;
    private Room hill;
    private Room valley;
    private Room arsenal;
    private Room bathroom;
    private Room roof;
    private long timestamp;
    private Room beamer;
    private ArrayList<Room> listOfRooms;
    private Item radio, key, flare, mirror, rock, trees, snack, flowers, money, cloak;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        mansion = new Room("came across a mansion");
        lobby = new Room("in the lobby");
        forest = new Room("in a forest");
        meadow = new Room("in a meadow");
        hill = new Room("on top of a hill");
        valley = new Room("in a valley");
        arsenal = new Room("in the arsenal");
        bathroom = new Room("you are in a bathroom");
        roof = new Room("you are on the roof of the mansion");
        listOfRooms = new ArrayList<>();
        laboratory = new TeleportationRoom("in the science room", listOfRooms);
        radio = new Item("radio", "use on a high place to find next task", 79);
        key = new Item("key", "Do not or lose me, and use me to get to the mansion", 1);
        flare = new Item("flare", "Use to signal the helicopter", 49);
        mirror = new Item("mirror", "Use to signal the helicopter", 49);
        rock = new Item("rock", "cannot move", 101);
        trees = new Item("trees", "cannot move", 101);
        snack = new Item("snack", "feed to the hungry guy", 10);
        money = new Item("money", "give to the hungry guy", 10);
        flowers = new Item("flowers", "nice smelling roses", 15);
        cloak = new Item("clock", "enemy cannot see you while I am in backpack", 50);
        setExits();
        setItems();
        currentRoom = forest;
        previousRoom = forest;
        parser = new Parser();
        backpack = new HashMap<>();
        isHelicopterCalled = false;
        isWon = false;
        timestamp = System.currentTimeMillis();
        beamer = null;
        addRooms();
    }

    /**
     * Adds Rooms to Arraylist
     */
    private void addRooms() {
        listOfRooms.add(mansion);
        listOfRooms.add(lobby);
        listOfRooms.add(forest);
        listOfRooms.add(meadow);
        listOfRooms.add(hill);
        listOfRooms.add(valley);
        listOfRooms.add(arsenal);
        listOfRooms.add(bathroom);
        listOfRooms.add(roof);
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void setExits() {
        // initialise room exits
        forest.setExit("east", meadow);
        forest.setExit("south", hill);
        forest.setExit("north", mansion);
        meadow.setExit("west", forest);
        meadow.setExit("south", valley);
        hill.setExit("north", forest);
        hill.setExit("east", valley);
        valley.setExit("north", meadow);
        valley.setExit("west", hill);
        mansion.setExit("south", forest);
        lobby.setExit("west", laboratory);
        lobby.setExit("east", bathroom);
        lobby.setExit("north", roof);
        roof.setExit("south", lobby);
        bathroom.setExit("north", arsenal);
        bathroom.setExit("west", lobby);
        arsenal.setExit("south", bathroom);
        laboratory.setExit("north", bathroom);
    }

    /**
     * Sets items on the whole map
     */
    private void setItems() {
        valley.setItems("radio", radio);
        hill.setItems("key", key);
        arsenal.setItems("flare", flare);
        bathroom.setItems("mirror", mirror);
        hill.setItems("money", money);
        meadow.setItems("flowers", flowers);
        arsenal.setItems("cloak", cloak);
        valley.setItems("rock", rock);
        forest.setItems("trees", trees);
        meadow.setItems("snack", snack);
    }

    /**
     * Function to play the game.
     */
    public static void main() {
        Game g = new Game();
        g.play();
    }

    /**
     * Main play routine. Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
        boolean isFinished = false;
        while (!isFinished && timestamp - System.currentTimeMillis() + 600000 > 0) {
            Command command = parser.getCommand();
            isFinished = processCommand(command);
            if (isHelicopterCalled == true && isWon == true) {
                System.out.println("Congrats on beating the game!" + "/n" + "You had " + ((timestamp - System.currentTimeMillis() + 600000) / 1000) + " seconds left.");
                if (backpack.keySet().contains("flowers")) {
                    System.out.println("You're significant other was happy you brought flowers.");
                }
                isFinished = true;
            } else if (timestamp - System.currentTimeMillis() + 600000 <= 0) {
                System.out.println("Sorry, ran out of time. Better luck next time!");
                isFinished = true;
            }
        }
        System.out.println("Thank you for playing.  Good bye.");
        isFinished = true;
    }

    private int getWeightofBackpack() {
        Object[] copyOfBackpack = null;
        copyOfBackpack = backpack.values().toArray();
        int weightTotal = 0;
        for (Object copyOfBackpack1 : copyOfBackpack) {
            int temportaryWeight = ((Item) copyOfBackpack1).getItemWeight();
            weightTotal += temportaryWeight;
        }
        return weightTotal;
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to our game Cloudy Day!" + "\n" + "Our game is a fun adventure where you try to leave by a helicopter." + "\n" + "Type 'help' if you need help." + "\n");
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     *
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;
        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }
        String commandWord = command.getCommandWord();
        switch (commandWord) {
            case "help":
                printHelp();
                break;
            case "go":
                goRoom(command);
                break;
            case "quit":
                wantToQuit = quit(command);
                break;
            case "take":
                take(command);
                break;
            case "drop":
                drop(command);
                break;
            case "back":
                back(command);
                break;
            case "look":
                look(command);
                break;
            case "use":
                use(command);
                break;
            case "unlock":
                unlock(command);
                break;
            case "call":
                call(command);
                break;
            case "charge":
                charge(command);
                break;
            case "teleport":
                teleport(command);
                break;
            default:
                break;
        }
        // else command not recognised.
        return wantToQuit;
    }

    /**
     * Print out some help information. Here we print some stupid, cryptic
     * message and a list of the command words.
     */
    private void printHelp() {
        System.out.println("You are lost in a woods. You are alone. You wander" + "\n" + "around the woods to get in the mansion." + "\n" + "\n" + "You must use something to signal the helicopter" + "\n" + "You must call the helicopter and unlock the mansion." + "\n" + "Your command words are:");
        parser.showCommands();
    }

    /**
     * Try to in to one direction. If there is an exit, enter the new room,
     * otherwise print an error message.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }
        Room nextRoom = null;
        // Try to leave current room.
        if (currentRoom != laboratory) {
            String direction = command.getSecondWord();
            nextRoom = currentRoom.getExit(direction);
        } else {
            String direction = command.getSecondWord();
            nextRoom = ((TeleportationRoom) currentRoom).getExit(direction, 1);
        }
        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            previousRoom = currentRoom;
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /**
     * Trys to take an item and put it into a backpack.
     *
     * @param the item we wish to take
     */
    private void take(Command command) {
        if (currentRoom.getSet().contains(command.getSecondWord())) {
            if (this.getWeightofBackpack() + currentRoom.getValue(command.getSecondWord()).getItemWeight() < 101) {
                Item tItem = currentRoom.removeItem(currentRoom.getValue(command.getSecondWord()));
                backpack.put(command.getSecondWord(), tItem);
                System.out.println(command.getSecondWord() + " " + "has been added to backpack");
                System.out.println(currentRoom.getLongDescription());
            } else {
                System.out.println("Weight would exceed the maximum weight.");
            }
        } else {
            System.out.println("Item is not in room");
        }
    }

    /**
     * Drops item into current room from backpack.
     *
     * @param the item we wish to drop
     */
    private void drop(Command command) {
        if (backpack.keySet().contains(command.getSecondWord())) {
            Item tempItem = backpack.get(command.getSecondWord());
            backpack.remove(command.getSecondWord());
            currentRoom.addItem(tempItem);
            System.out.println(command.getSecondWord() + " " + "has been dropped from backpack");
            System.out.println(currentRoom.getLongDescription());
        } else {
            System.out.println("Item is not in backpack");
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see whether we
     * really quit the game.
     *
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * Takes you back to the room you were just at.
     *
     * @param it is just the command word back
     */
    private void back(Command command) {
        currentRoom = previousRoom;
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Checks to see what is in the backpack.
     *
     * @param it is just the command word look
     */
    private void look(Command command) {
        Set itemsInBackpack = backpack.keySet();
        System.out.println(itemsInBackpack);
        System.out.println(currentRoom.getLongDescription());

    }

    /**
     * Calls in the helicopter using the radio.
     *
     * @param the command word call and then the word helicopter
     * @return tells that the helicopter was called
     */
    private void call(Command command) {
        String message = "Cannot use item here";
        if (!backpack.keySet().contains("radio")) {
            System.out.println("item not in backpack");
        } else if (command.getSecondWord().equals("helicopter") && currentRoom == hill) {
            isHelicopterCalled = true;
            System.out.println("Helicopter is on its way. Get to the roof of the mansion.");
        } else if (command.getSecondWord().equals("helicopter") && currentRoom != hill) {
            System.out.println(message);
        }
    }

    /**
     * Command to use mirror or flare and possibly win the game.
     *
     * @param checks if you are using flare or mirror in the right spot
     * @return sees if you won game or not
     */
    private void use(Command command) {
        String message = "Cannot use item here";
        if (!command.hasSecondWord()) {
            System.out.println("use what?");
        } else if (!backpack.keySet().contains(command.getSecondWord())) {
            System.out.println("item not in backpack");
        } else if (command.getSecondWord().equals("mirror") && currentRoom == roof) {
            System.out.println("It is a cloudy day, and this will not work");
        } else if (command.getSecondWord().equals("mirror") && currentRoom != roof) {
            System.out.println(message);
        } else if (command.getSecondWord().equals("flare") && currentRoom == roof) {
            isWon = true;
        } else if (command.getSecondWord().equals("flare") && currentRoom != roof) {
            System.out.println(message);
        }
    }

    /**
     * Adds access to the mansion.
     *
     * @param just the command word plus the word mansion
     */
    private void unlock(Command command) {
        String message = "Cannot use item here";
        if (!backpack.keySet().contains("key")) {
            System.out.println("item not in backpack");
        } else if (command.getSecondWord().equals("mansion") && currentRoom == mansion) {
            mansion.setExit("north", lobby);
            lobby.setExit("south", mansion);
            System.out.println("you can now move in and out of the mansion");
            System.out.println(currentRoom.getLongDescription());
        } else if (command.getSecondWord().equals("mansion") && !currentRoom.equals("mansion")) {
            System.out.println(message);
        }
    }

    /**
     * Sets the room of the beamer.
     *
     * @param just the command word
     */
    private void charge(Command command) {
        beamer = currentRoom;
        System.out.println("Beamer has been charged.");
    }

    /**
     * Uses the beamer.
     *
     * @param just the command word
     */
    private void teleport(Command command) {
        currentRoom = beamer;
        System.out.println("You have been successfully teleported");
        System.out.println(currentRoom.getLongDescription());
    }

}
