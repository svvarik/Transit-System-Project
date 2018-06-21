import java.util.ArrayList;


public class AdminUser {

    private String name;

    private String email;

    private ArrayList cards;

    public AdminUser(String name, String email) {
        this.name = name;
        this.email = email;
        this.cards = new ArrayList();
    }
}

