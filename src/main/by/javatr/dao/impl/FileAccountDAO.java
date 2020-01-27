package main.by.javatr.dao.impl;

import main.by.javatr.bean.Account;
import main.by.javatr.bean.Session;
import main.by.javatr.dao.AccountDAO;
import main.by.javatr.dao.DAOException.DAOException;
import org.apache.log4j.Logger;

import java.io.*;

public class FileAccountDAO implements AccountDAO {

    File file = new File("DB.txt");
    File file2 = new File("DB2.txt");

    String[] str;
    String line;

    private static Logger log = Logger.getLogger(FileAccountDAO.class.getName());

    @Override
    public boolean add(Account account) throws DAOException {

        log.info("DAO layer add");
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));) {
            writer.write(account.getLogin() + " " + account.getPassword()+ " " + account.getBalance() + " " + account.getExpenses() + " " + account.getTransport() + " " + account.getFood() + " " + account.getEntertainment() + " " + account.getOther() + " " + account.isAdmin() + " " + account.isBan() + " "+ account.getCurrentCur() + "\n");
        } catch (IOException e) {
            throw new DAOException("IOException", e);
        }
        return true;
    }

    @Override
    public Account update(Account account) throws DAOException {

        log.info("DAO layer update");

        try (BufferedReader reader = new BufferedReader(new FileReader(file)); BufferedWriter writer = new BufferedWriter(new FileWriter(file2))){

            while ((line = reader.readLine()) != null) {

                str = line.split(" ");
                if (account.getLogin().equals(str[0]) && account.getPassword().equals(str[1])) {

                    writer.write(account.getLogin() + " " + account.getPassword() + " " + account.getBalance() + " " + account.getExpenses() + " " + account.getTransport() + " " + account.getFood() + " " + account.getEntertainment() + " " + account.getOther() + " " + account.isAdmin() + " " + account.isBan() +" "+ account.getCurrentCur());
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }

        }catch (FileNotFoundException e) {
            throw new DAOException("File not found");
        } catch (IOException e) {
            throw new DAOException("IOException", e);
        }finally{
            file.delete();
            file2.renameTo(file);
        }
        return account;
    }

    @Override
    public boolean find(Account account) throws DAOException {

        log.info("DAO layer find");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))){

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
                    return true;
                }

            }

            return false;
        }catch (FileNotFoundException e){
            throw new DAOException("File not found exception", e);
        } catch (IOException e) {
            throw new DAOException("I/O exception", e);
        }
    }

    @Override
    public boolean findByLogin(String login) throws DAOException {

        log.info("DAO layer find by login");

        try(BufferedReader reader = new BufferedReader(new FileReader(file))){

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
        }
        return false;
    }


    @Override
    public boolean delete(Account account) throws DAOException {
        log.info("DAO layer delete");
        try(BufferedReader reader = new BufferedReader(new FileReader(file));BufferedWriter writer = new BufferedWriter(new FileWriter(file2))){

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
        return true;
        } catch (FileNotFoundException e) {
            throw new DAOException("File not found",e);
        } catch (IOException e) {
            throw new DAOException("IOException", e);
        }finally {
            file.delete();
            file2.renameTo(file);
        }

    }

    @Override
    public Account get(Account account) throws DAOException {
        log.info("DAO layer get");

        try (BufferedReader reader = new BufferedReader(new FileReader(file));){

            while ((line = reader.readLine()) != null) {

                str = line.split(" ");
                if (account.getLogin().equals(str[0])) {
                    account.setPassword(str[1]);
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
        }
    }
}
