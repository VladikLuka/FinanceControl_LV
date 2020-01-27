package main.by.javatr.bean;

public class Session {

    private static Session session;
    private Account account;

    private boolean isLogin;

    public static Session getInstance(){

        if(session == null){
            session = new Session();
        }
        return session;
    }

    public void delAccount(){
        account = null;
    }


    public Account getAccount() {

        if(account == null){
            account = new Account();
        }

        return account;
    }

    public final boolean isLogin() {
        return isLogin;
    }

    public final void setLogin(boolean login) {
        isLogin = login;
    }

    private Session(){}

}
