package webauthentication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ro22_1
 */
public class fileUpload {

    private static ArrayList<User> users;
    private static HashMap<String, String> userPass;

    public static void main(String[] args) {
        // TODO code application logic here
        upLoad();
    }

    public static void upLoad() {
        try {
            ArrayList<User> tempUsers = new ArrayList<>();
            File userInformation = new File("text.txt");
            try (Scanner inputFile = new Scanner(userInformation)) {
                while (inputFile.hasNext()) {
                    String firstName = inputFile.next();
                    String lastName = inputFile.next();
                    String username = inputFile.next();
                    String password = inputFile.next();
                    String emailAddress = inputFile.next();
                    String shortBio = inputFile.next();
                    String securityQuestion = inputFile.next();
                    String securityAnswer = inputFile.next();
                    int favoriteInt = inputFile.nextInt();
                    String favoriteColor = inputFile.next();
                    User user = new User(firstName, lastName, username, password, emailAddress, shortBio, securityQuestion, securityAnswer, favoriteInt, favoriteColor);
                    tempUsers.add(user);
                }
                users = tempUsers;
                HashMap<String, String> tempuserPass = new HashMap<>();
                users.forEach((u) -> {
                    tempuserPass.put(u.getUsername(), u.getPassword());
                });
                userPass = tempuserPass;
            }
        } catch (FileNotFoundException e) {
            System.err.println("File was not found");
            System.exit(0);
        }
    }

    public HashMap<String, String> getHashMap() {
        return userPass;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

}
