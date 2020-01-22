package main.by.javatr.dao.impl;

import main.by.javatr.bean.Account;
import main.by.javatr.dao.AccountDAO;
import main.by.javatr.dao.DAOException.DAOException;

import java.io.*;

public class FileAccountDAO implements AccountDAO {

    File file = new File("123DB.txt");
    File file2 = new File("DB2.txt");

    String[] str;
    String line;

    BufferedReader reader;
    BufferedWriter writer;

    @Override
    public boolean add(Account account) throws DAOException {

        try {
            writer = new BufferedWriter(new FileWriter(file,true));
            writer.write(account.getLogin() + " " + account.getPassword()+ " " + account.getBalance() + " " + account.getExpenses() + " " + account.getTransport() + " " + account.getFood() + " " + account.getEntertainment() + " " + account.getOther() + " " + account.isAdmin() + " " + account.isBan() + " "+ account.getCurrentCur() + "\n");
        } catch (IOException e) {
            throw new DAOException("IOException", e);
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new DAOException("IOException", e);
            }
        }

        return true;
    }

    @Override
    public Account update(Account account) throws DAOException {

        try {
            reader = new BufferedReader(new FileReader(file));
            writer = new BufferedWriter(new FileWriter(file2));

            while ((line = reader.readLine()) != null) {

                str = line.split(" ");
                if (account.getLogin().equals(str[0]) && account.getPassword().equals(str[1])) {

                    writer.write(account.getLogin() + " " + account.getPassword() + " " + account.getBalance() + " " + account.getExpenses() + " " + account.getTransport() + " " + account.getFood() + " " + account.getEntertainment() + " " + account.getOther() + " " + account.isAdmin() + " " + account.isBan() +" "+ account.getCurrentCur());
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }

            reader.close();
            writer.close();

            file.delete();
            file2.renameTo(file);


        }catch (FileNotFoundException e) {
            throw new DAOException("message");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                throw new DAOException("message");
            }
        }


        return account;
    }

    @Override
    public Account find(Account account) throws DAOException {

        try {

            reader = new BufferedReader(new FileReader(file));

            while ((line = reader.readLine()) != null) {

                str = line.split(" ");
                if (account.getLogin().equals(str[0]) && account.getPassword().equals(str[1])) {
                    account.setBalance(Double.parseDouble(str[2]));
                    account.setExpenses(Double.parseDouble(str[3]));
                    account.setTransport(Double.parseDouble(str[4]));
                    account.setFood(Double.parseDouble(str[5]));
                    account.setEntertainment(Double.parseDouble(str[6]));
                    account.setOther(Double.parseDouble(str[7]));
                    account.setAdmin(Boolean.parseBoolean(str[8]));
                    account.setBan(Boolean.parseBoolean(str[9]));
                    account.setCurrentCur(str[10].charAt(0));
                    break;

                }

            }
            return account;
        }catch (FileNotFoundException e){
            throw new DAOException("File not found exception", e);
        } catch (IOException e) {
            throw new DAOException("I/O exception", e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new DAOException("I/O exception", e);
            }
        }
    }

    @Override
    public boolean findByLogin(String login) throws DAOException {


        try{
        reader = new BufferedReader(new FileReader(file));

        while((line = reader.readLine()) != null){

            str = line.split(" ");
            if(login.equals(str[0])){
                return true;
            }
        }

        }catch (FileNotFoundException e){
            throw new DAOException("File not found exception", e);
        } catch (IOException e) {
            throw new DAOException("I/O exception", e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new DAOException("I/O exception", e);
            }
        }
        return false;
    }


    @Override
    public boolean delete(Account account) throws DAOException {
        try{
        reader = new BufferedReader(new FileReader(file));
        writer = new BufferedWriter(new FileWriter(file2));
        String[] str;
        String line;
        while((line = reader.readLine()) != null){

            str = line.split(" ");
            if(account.getLogin().equals(str[0])){
                continue;
            }
            writer.write(line);
            writer.newLine();

        }
        reader.close();
        writer.close();

        file.delete();
        file2.renameTo(file);

        return true;
        }catch (FileNotFoundException e){
            throw new DAOException("File not found exception",e);
        } catch (IOException e) {
            throw new DAOException("I/O exception",e);
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                throw new DAOException("I/O exception",e);
            }
        }
    }

    @Override
    public Account get(Account account) throws DAOException {
        try {

            reader = new BufferedReader(new FileReader(file));

            while ((line = reader.readLine()) != null) {

                str = line.split(" ");
                if (account.getLogin().equals(str[0])) {
                    account.setPassword(str[2]);
                    account.setExpenses(Double.parseDouble(str[3]));
                    account.setBalance(Double.parseDouble(str[2]));
                    account.setTransport(Double.parseDouble(str[4]));
                    account.setFood(Double.parseDouble(str[5]));
                    account.setEntertainment(Double.parseDouble(str[6]));
                    account.setOther(Double.parseDouble(str[7]));
                    account.setAdmin(Boolean.parseBoolean(str[8]));
                    account.setBan(Boolean.parseBoolean(str[9]));
                    account.setCurrentCur(str[10].charAt(0));
                    break;

                }

            }
            return account;
        }catch (FileNotFoundException e){
            throw new DAOException("File not found exception", e);
        } catch (IOException e) {
            throw new DAOException("I/O exception", e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new DAOException("I/O exception", e);
            }
        }
    }
}
