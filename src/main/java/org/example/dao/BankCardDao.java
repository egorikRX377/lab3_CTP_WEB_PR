package org.example.dao;

import org.example.model.BankCard;
import org.example.model.Student;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankCardDao
{
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int st;

    public int insert(BankCard bankCard) {
        con = ConnectionFactory.getConnection();
        try
        {
            String query = "insert into bankcard (cardNumber, CVV, balance) values (?,?,?)";
            ps = con.prepareStatement(query);
            ps.setString(1, bankCard.getCardNumber());
            ps.setInt(2, bankCard.getCVV());
            ps.setBigDecimal(3, bankCard.getBalance());
            st = ps.executeUpdate();
            System.out.println("inserted bank cards" + st);
        }
        catch (Exception e) {
            st = -2;
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return st;
    }

    public int delete(long id) {
        con = ConnectionFactory.getConnection();
        try {
            String query = "delete from bankcard where id=? ";
            ps = con.prepareStatement(query);
            ps.setLong(1, id);
            st = ps.executeUpdate();
            System.out.println("deleted bankcard " + st);
        } catch (Exception e) {
            st = -2;
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return st;
    }

    public BankCard fetchByCardNumber(String cardNumber) {
        BankCard bankCard = null;
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM bankcard WHERE cardNumber=?")) {

            ps.setString(1, cardNumber);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {  // Если запись найдена
                    bankCard = new BankCard();
                    bankCard.setId(rs.getLong("id"));
                    System.out.println(rs.getLong("id"));
                    bankCard.setCardNumber(rs.getString("cardNumber"));
                    bankCard.setCVV(rs.getInt("CVV"));
                    bankCard.setBalance(rs.getBigDecimal("balance"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bankCard;
    }

    public boolean performTransaction(String yourCardNumber, String recCardNumber, BigDecimal value) throws SQLException {
        BankCard bankCard = null;
        BankCard rBankCard = new BankCard();
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM bankcard WHERE cardNumber=?")) {

            ps.setString(1, yourCardNumber);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    bankCard = new BankCard();
                    bankCard.setId(rs.getLong("id"));
                    bankCard.setCardNumber(rs.getString("cardNumber"));
                    bankCard.setCVV(rs.getInt("CVV"));
                    bankCard.setBalance(rs.getBigDecimal("balance"));
                } else {
                    return false;
                }
            }

            ps.setString(1, recCardNumber);
            try (ResultSet rst = ps.executeQuery()) {
                if (rst.next()) {
                    rBankCard.setId(rst.getLong("id"));
                    rBankCard.setCardNumber(rst.getString("cardNumber"));
                    rBankCard.setCVV(rst.getInt("CVV"));
                    rBankCard.setBalance(rst.getBigDecimal("balance"));
                } else {
                    return false;
                }
            }

            con.setAutoCommit(false);

            try (PreparedStatement updateStmt = con.prepareStatement("UPDATE bankcard SET balance=? WHERE cardNumber=?")) {
                bankCard.setBalance(bankCard.getBalance().subtract(value));
                updateStmt.setBigDecimal(1, bankCard.getBalance());
                updateStmt.setString(2, yourCardNumber);

                updateStmt.addBatch();

                rBankCard.setBalance(rBankCard.getBalance().add(value));
                updateStmt.setBigDecimal(1, rBankCard.getBalance());
                updateStmt.setString(2, recCardNumber);
                updateStmt.addBatch();

                updateStmt.executeBatch();
                con.commit();
                return true;

            } catch (SQLException e) {
                if (con != null) {
                    con.rollback();
                }
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            return false;
        }
    }
}


