package main.by.javatr.bean;

import java.io.Serializable;

public class Account implements Serializable {

    private static final long serialVersionUID = 1723763139833699804L;

    private static Account account;

    private static boolean isAdmin;

    private String login;
    private String password;
    private double balance;
    private double expenses;
    private double food;
    private double transport;
    private double entertainment;
    private double other;
    private boolean admin;
    private boolean ban;
    private char currentCur = '$';

    public static Account getInstance(){

        if(account == null) account = new Account();

        return account;
    }

    public static Account delInstance(){

        account = null;
        return null;
    }

    public static Account getAccount() {
        return account;
    }

    public Account(){

        login = null;
        password = null;
        balance = 0;
        expenses = 0;
        food = 0;
        transport = 0;
        entertainment = 0;
        other = 0;
        admin = false;

    }

    @Override
    public String toString() {
        return getClass().getName() +"@"+
                "{ login=" + login +
                ", password=" + password +
                ", balance=" + balance +
                ", expenses=" + expenses +
                ", food=" + food +
                ", transport=" + transport +
                ", entertainment=" + entertainment +
                ", other=" + other;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getFood() {
        return food;
    }

    public void setFood(double food) {
        this.food = food;
    }

    public double getTransport() {
        return transport;
    }

    public void setTransport(double transport) {
        this.transport = transport;
    }

    public double getEntertainment() {
        return entertainment;
    }

    public void setEntertainment(double entertainment) {
        this.entertainment = entertainment;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public double getOther() {
        return other;
    }

    public void setOther(double other) {
        this.other = other;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isBan() {
        return ban;
    }

    public void setBan(boolean ban) {
        this.ban = ban;
    }

    public char getCurrentCur() {
        return currentCur;
    }

    public void setCurrentCur(char currentCur) {
        this.currentCur = currentCur;
    }

    public static boolean isIsAdmin() {
        return isAdmin;
    }

    public static void setIsAdmin(boolean isAdmin) {
        Account.isAdmin = isAdmin;
    }
}
