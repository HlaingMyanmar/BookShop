package sspd.bookshop.users;

public enum Users {


    ADMIN("Technical", "21101998"),
    CUSTOMER("Admin", "123"),
    GUEST("User", "1234");

    private final String username;
    private final String password;

    Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
