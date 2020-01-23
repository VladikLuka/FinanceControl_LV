package main.by.javatr.bean;

public final class Session {

    private static Session session;
    private static Account account;

    private static boolean isAdmin;

    public static Session getInstance(){

        if(session == null){
            session = new Session();
        }
        return session;
    }

    public static void delAccount(){
        account = null;
    }


    public static Account getAccount() {

        if(account == null){
            account = new Account();
        }

        return account;
    }

    public static Account checkAccount(){
        return account;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public static void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    private Session(){}

}
