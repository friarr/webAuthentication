package webauthentication;

/**
 *
 * @author ro22_1
 */
public class User {
    private String firstName;
    private String lastName;
    private final String username;
    private String password;
    private String emailAddress;
    private String shortBio;
    private String securityQuestion;
    private String securityAnswer;
    private int favoriteInt;
    private String favoriteColor;
    
    public User(String firstName, String lastName, String username, String password, String emailAddress, String shortBio, String securityQuestion, String securityAnswer, int favoriteInt, String favoriteColor){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.shortBio = shortBio;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.favoriteInt = favoriteInt;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getEmailAddress(){
        return emailAddress;
    }
    public String getShortBio(){
        return shortBio;
    }
    public String getSecurityQuestion(){
        return securityQuestion;
    }
    public String getSecurityAnswer(){
        return securityAnswer;
    }
    public int getFavoriteInt(){
        return favoriteInt;
    }
    public String getFavoriteColor(){
        return favoriteColor;
    }
    public void setShortBio(String NEW){
        this.shortBio = NEW;
    }
    public void setSecurityQuestion(String NEW){
        this.securityQuestion = NEW;
    }
    public void setSecurityAnswer(String NEW){
        this.securityAnswer = NEW;
    }
    public void setPassword(String NEW){
        this.password = NEW;
    }
}
