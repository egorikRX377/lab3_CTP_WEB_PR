package org.example.action;

import org.example.dao.BankCardDao;
import org.example.dao.StudentDao;
import org.example.model.BankCard;
import org.example.model.Student;

import java.math.BigDecimal;
import java.sql.SQLException;

public class BankCardAction
{
    BankCardDao dao = new BankCardDao();
    int st;

    public void insert(BankCard bankCard) {
        st = dao.insert(bankCard);
        if (st == 1) {
            System.out.println("BankCard Inserted Successfully");
        } else if (st == -1) {
            System.out.println("BankCard Already exists");
        } else {
            System.out.println("Unable to Insert BankCard");
        }
    }

    public void delete(Long id) {
        st = dao.delete(id);
        if (st == 1) {
            System.out.println("BankCard Deleted Successfully");
        } else {
            System.out.println("No Record Found");
        }
    }

    public void fetchByCardNumber(String cardNumber) {
        BankCard bankCard = dao.fetchByCardNumber(cardNumber);

        if (bankCard == null) {
            System.out.println("No Record Found");
        } else {
            System.out.println("BankCard Details are :");
            System.out.println("ID: " + bankCard.getId());
            System.out.println(bankCard);
        }
    }

    public void transfer(String yourCardNumber, String RecCardNumber, BigDecimal value) throws SQLException {
        boolean flag = false;
        flag = dao.performTransaction(yourCardNumber, RecCardNumber, value);
         if (!flag)
         {
             System.out.println("Произошла ошибка выполнения транзакции!");
         }
         else
         {
             System.out.println("Транзакция прошла успешно!");
         }

    }
}
