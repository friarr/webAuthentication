package webauthentication;

import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author ro22_1
 */
public class GUI extends WindowAdapter {

    private JFrame GUI;
    private HashMap<String, String> userInfo;
    private String username;
    private String password;
    private fileUpload f;
    private ArrayList<User> users;
    private Game game;

    public void startGUI() {
        f = new fileUpload();
        fileUpload.upLoad();
        users = new ArrayList<>();
        users = f.getUsers();
        userInfo = f.getHashMap();
        GUI = new JFrame();
        GUI.addWindowListener(this);
        GUI.setSize(800, 600);
        GUI.setLayout(null);
        GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUI.getContentPane().setBackground(Color.BLACK);
        UIManager UI = new UIManager();
        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        GUI.setVisible(true);

        startLogin();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        int a = JOptionPane.showConfirmDialog(GUI, "Are you sure?");
        if (a == JOptionPane.YES_OPTION) {
            GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

    public void startLogin() {
        username = JOptionPane.showInputDialog(GUI, "Enter Username or type new user to create account.");
        if (usernameIsValid(username) && !username.equals("new user") && !username.equals(null)) {
            password = JOptionPane.showInputDialog(GUI, "Enter Password");
            checkPassword(username, password);
        } else if (username.equals("new user")) {
            createAccount();
        } else if (username == null) {
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(GUI, "Username/Password is not correct.", "Alert", JOptionPane.WARNING_MESSAGE);
            startLogin();
        }
    }

    public boolean usernameIsValid(String userName) {
        return userInfo.containsKey(userName);
    }

    private void checkPassword(String userName, String passWord) {
        if (userInfo.get(userName).equals(password)) {
            showInformation();
        } else {
            JOptionPane.showMessageDialog(GUI, "Usename/Password is not correct.", "Alert", JOptionPane.WARNING_MESSAGE);
            startLogin();
        }
    }

    private void showInformation() {
        int index = 0;
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername())) {
                index = i;
                break;
            }
        }
        JOptionPane.showMessageDialog(GUI, users.get(index).getFirstName() + " " + users.get(index).getLastName() + " has logged in successfully!" + "\n" + "Your security question and answer= " + users.get(index).getSecurityQuestion() + " " + users.get(index).getSecurityAnswer() + "\n" + "Your bio= " + users.get(index).getShortBio() + "\n" + "Email Address =  " + users.get(index).getEmailAddress() + "\n" + "Your favorite number= " + users.get(index).getFavoriteInt() + "\n" + "Play game in the terminal, and press ok when done, and you wish to logout.");
        String logout = JOptionPane.showInputDialog(GUI, "Type logout to logout.");
        if (logout.equals("logout")) {
            System.exit(0);
        }
        game = new Game();
        game.play();
        update();
        startLogin();
    }

    private void createAccount() {
        String firstName, lastName, newUsername, newPassword, emailAddress, shortBio, securityQuestion, securityAnswer, favoriteInt, favoriteColor;
        firstName = JOptionPane.showInputDialog(GUI, "Enter first name");
        lastName = JOptionPane.showInputDialog(GUI, "Enter last name");
        newUsername = JOptionPane.showInputDialog(GUI, "Enter username: no spaces or special characters");
        if (userInfo.containsKey(newUsername)) {
            JOptionPane.showMessageDialog(GUI, "Usename is taken, so enter new username after you press ok.", "Alert", JOptionPane.WARNING_MESSAGE);
            newUsername = JOptionPane.showInputDialog(GUI, "Enter username: no spaces or special characters");
        }
        newPassword = JOptionPane.showInputDialog(GUI, "Enter password: no spaces or special characters");
        emailAddress = JOptionPane.showInputDialog(GUI, "Enter email: use quoatation marks before and after.");
        shortBio = JOptionPane.showInputDialog(GUI, "Enter shortBio: use _ instead of spaces");
        securityQuestion = JOptionPane.showInputDialog(GUI, "Enter security question: use _ instead of spaces");
        securityAnswer = JOptionPane.showInputDialog(GUI, "Enter security question: use _ instead of spaces");
        favoriteInt = JOptionPane.showInputDialog(GUI, "Enter integer");
        int actualInt = Integer.parseInt(favoriteInt);
        favoriteColor = JOptionPane.showInputDialog(GUI, "Enter favorite color: one word");

        JOptionPane.showMessageDialog(GUI, "Thank you. Account has been created.", "Alert", JOptionPane.WARNING_MESSAGE);
        try {
            FileWriter fileWriter = new FileWriter("text.txt", true);
            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                bufferedWriter.newLine();
                bufferedWriter.write(firstName + " " + lastName + " " + newUsername + " " + newPassword + " " + emailAddress + " " + shortBio + " " + securityQuestion + " " + securityAnswer + " " + favoriteInt + " " + favoriteColor);
            }
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        update();
        startLogin();
    }

    private void update() {
        fileUpload.upLoad();
        users = f.getUsers();
        userInfo = f.getHashMap();
        username = "";
        password = "";
    }
}
