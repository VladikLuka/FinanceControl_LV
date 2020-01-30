package main.by.javatr.dao.impl;

import main.by.javatr.bean.Account;
import main.by.javatr.dao.AccountDAO;
import main.by.javatr.dao.exception.DAOException;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileAccountDAO implements AccountDAO {

    private static Logger log = Logger.getLogger(FileAccountDAO.class.getName());

    File file = new File("DB.txt");
    File file2 = new File("DB2.txt");

    String[] str;
    String line;


    @Override
    public boolean add(Account account) throws DAOException {
        log.info("DAO layer add");

        FileAccountDAO fileAccountDAO = new FileAccountDAO();

        int id = fileAccountDAO.getLastId() + 1;
        account.setId(id);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));) {

            writer.write(account.getId() + " " +account.getLogin() + " " + account.getPassword() + " " + account.getBalance() + " " + account.getExpenses() + " " + account.getTransport() + " " + account.getFood() + " " + account.getEntertainment() + " " + account.getOther() + " " + account.isAdmin() + " " + account.isBan() + " " + account.getCurrentCur() + "\n");
        } catch (IOException e) {
            throw new DAOException("IOException", e);
        }
        return true;
    }

    @Override
    public Account update(Account account) throws DAOException {

        log.info("DAO layer update");

        try (BufferedReader reader = new BufferedReader(new FileReader(file)); BufferedWriter writer = new BufferedWriter(new FileWriter(file2))) {

            while ((line = reader.readLine()) != null) {

                str = line.split(" ");
                if (account.getLogin().equals(str[1]) && account.getPassword().equals(str[2])) {

                    writer.write(account.getId() + " " + account.getLogin() + " " + account.getPassword() + " " + account.getBalance() + " " + account.getExpenses() + " " + account.getTransport() + " " + account.getFood() + " " + account.getEntertainment() + " " + account.getOther() + " " + account.isAdmin() + " " + account.isBan() + " " + account.getCurrentCur());
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }

        } catch (FileNotFoundException e) {
            throw new DAOException("File not found");
        } catch (IOException e) {
            throw new DAOException("IOException", e);
        } finally {
            file.delete();
            file2.renameTo(file);
        }
        return account;
    }

    @Override
    public boolean find(Account account) throws DAOException {

        log.info("DAO layer find");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            while ((line = reader.readLine()) != null) {

                str = line.split(" ");
                if (account.getLogin().equals(str[1]) && account.getPassword().equals(str[2])) {
                    account.setId(Integer.parseInt(str[0]));
                    account.setBalance(Double.parseDouble(str[3]));
                    account.setExpenses(Double.parseDouble(str[4]));
                    account.setTransport(Double.parseDouble(str[5]));
                    account.setFood(Double.parseDouble(str[6]));
                    account.setEntertainment(Double.parseDouble(str[7]));
                    account.setOther(Double.parseDouble(str[8]));
                    account.setAdmin(Boolean.parseBoolean(str[9]));
                    account.setBan(Boolean.parseBoolean(str[10]));
                    account.setCurrentCur(str[11].charAt(0));
                    return true;
                }

            }

            return false;
        } catch (FileNotFoundException e) {
            throw new DAOException("File not found exception", e);
        } catch (IOException e) {
            throw new DAOException("I/O exception", e);
        }
    }

    @Override
    public boolean findByLogin(String login) throws DAOException {

        log.info("DAO layer find by login");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            while ((line = reader.readLine()) != null) {

                str = line.split(" ");
                if (login.equals(str[1])) {
                    return true;
                }
            }

        } catch (FileNotFoundException e) {
            throw new DAOException("File not found exception", e);
        } catch (IOException e) {
            throw new DAOException("I/O exception", e);
        }
        return false;
    }


    @Override
    public boolean delete(Account account) throws DAOException {
        log.info("DAO layer delete");
        try (BufferedReader reader = new BufferedReader(new FileReader(file)); BufferedWriter writer = new BufferedWriter(new FileWriter(file2))) {

            String[] str;
            String line;
            while ((line = reader.readLine()) != null) {

                str = line.split(" ");
                if (account.getLogin().equals(str[1])) {
                    continue;
                }
                writer.write(line);
                writer.newLine();

            }
            return true;
        } catch (FileNotFoundException e) {
            throw new DAOException("File not found", e);
        } catch (IOException e) {
            throw new DAOException("IOException", e);
        } finally {
            file.delete();
            file2.renameTo(file);
        }

    }

    @Override
    public Account get(Account account) throws DAOException {
        log.info("DAO layer get");

        try (BufferedReader reader = new BufferedReader(new FileReader(file));) {

            while ((line = reader.readLine()) != null) {

                str = line.split(" ");
                if (account.getLogin().equals(str[1])) {
                    account.setId(Integer.parseInt(str[0]));
                    account.setPassword(str[2]);
                    setAccount(account);
                    break;

                }

            }
            return account;
        } catch (FileNotFoundException e) {
            throw new DAOException("File not found exception", e);
        } catch (IOException e) {
            throw new DAOException("I/O exception", e);
        }
    }

    @Override
    public List<Account> getAll() throws DAOException {
        log.info("DAO layer getALL");

        List<Account> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file));) {

            while ((line = reader.readLine()) != null) {
                Account account = new Account();
                str = line.split(" ");

                account.setId(Integer.parseInt(str[0]));
                account.setLogin(str[1]);
                account.setPassword(str[2]);

                setAccount(account);
                list.add(account);
            }

            } catch (IOException e) {
            throw new DAOException("IOException",e);
        }

        return list;
    }

    @Override
    public Account getAccountById(int id) throws DAOException {
        log.info("getAccountByID");

        try (BufferedReader reader = new BufferedReader(new FileReader(file));) {

            while ((line = reader.readLine()) != null) {
                str = line.split(" ");
                if(Integer.parseInt(str[0]) == id){
                    Account account = new Account();
                    account.setId(Integer.parseInt(str[0]));
                    account.setLogin(str[1]);
                    account.setPassword(str[2]);
                    setAccount(account);
                    return account;
                }
            }


        } catch (IOException e) {
            throw new DAOException("IOException", e);
        }

        return null;
    }


    private int getLastId() throws DAOException {

        String arr = "-1";
        try(RandomAccessFile raf = new RandomAccessFile(file, "r")){
            String result = null;
            long length = file.length();
            while(result == null || result.length() == 0){
                raf.seek(length--);
                raf.readLine();
                result = raf.readLine();

            }

            arr = result.split(" ")[0];
        } catch (FileNotFoundException e) {
            throw new DAOException("File not fount exception", e);
        } catch (IOException e) {
            throw new DAOException("IOException",e);
        }

        return Integer.parseInt(arr);
    }

    private void setAccount(Account account){
        account.setExpenses(Double.parseDouble(str[4]));
        account.setBalance(Double.parseDouble(str[3]));
        account.setTransport(Double.parseDouble(str[5]));
        account.setFood(Double.parseDouble(str[6]));
        account.setEntertainment(Double.parseDouble(str[7]));
        account.setOther(Double.parseDouble(str[8]));
        account.setAdmin(Boolean.parseBoolean(str[9]));
        account.setBan(Boolean.parseBoolean(str[10]));
        account.setCurrentCur(str[11].charAt(0));
    }
}