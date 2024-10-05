package org.example.start;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import org.example.action.BankCardAction;
import org.example.action.StudentAction;
import org.example.model.BankCard;
import org.example.model.Student;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Scanner;


public class MainClass {

    StudentAction action = new StudentAction();
    BankCardAction bankCardAction = new BankCardAction();

    public static void main(String[] args) throws SQLException {
        String DbOperation = "EXIT(0), ADD(1), UPDATE(2), DELETE(3), FETCHBYID(4), FETCHBYEMAIL(5), FETCHBYMOBNO(6), FETCHBYNAME(7),"
                + "FETCHBYCITY(8), FETCHBYSALRANGE(9), FETCHBYDOB(10), FETCHBYDOJRANGE(11), FETCHALL(12), ADDBANKCARD(13), FETCHBYCARDNUMBER(14)";

        MainClass mainclass = new MainClass();
        int value = 0;
        do {
            System.out.println("valid operations are");

            System.out.println(DbOperation);
            System.out.println("Enter valid operation number 0-15");
            Scanner scanner = new Scanner(System.in);
            value = scanner.nextInt();

            switch (value) {
                case 1:
                    mainclass.addStudent();
                    break;
                case 2:
                    mainclass.updateStudent();
                    mainclass.fetchAllStudent();
                    break;
                case 3:
                    mainclass.deleteStudent();
                    break;
                case 4:
                    mainclass.fetchStudentById();
                    break;
                case 5:
                    mainclass.fetchStudentByEmail();
                    break;
                case 6:
                    mainclass.fetchStudentByMobileNo();
                    break;
                case 7:
                    mainclass.searchStudentByName();
                    break;
                case 8:
                    mainclass.fetchStudentByCity();
                    break;
                case 9:
                    mainclass.fetchStudentBySalaryRange();
                    break;
                case 10:
                    mainclass.fetchStudentByDob();
                    break;
                case 11:
                    mainclass.fetchStudentByDOjRange();
                    break;
                case 12:
                    mainclass.fetchAllStudent();
                    break;
                case 13:
                    mainclass.addBankCard();
                    break;
                case 14:
                     mainclass.fetchBankCardByCardNumber();
                     break;
                case 15:
                    mainclass.transferByCardNumber();
                case 0:
                    System.out.println("Exiting code");
                    break;
                default:
                    System.out.println("Not a valid entry");
            }
        } while (value != 0);

    }

    public void addStudent() {
        Scanner insert = new Scanner(System.in);
        Student student = new Student();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.println("Enter First Name:");
        student.setFname(insert.next());

        System.out.println("Enter Last Name:");
        student.setLname(insert.next());

        System.out.println("Enter Address:");
        insert.nextLine(); // Чистим буфер после ввода next()
        student.setAddress(insert.nextLine());

        System.out.println("Enter Mobile Number:");
        student.setMobileNo(insert.next());

        System.out.println("Enter Mail Id:");
        student.setMailId(insert.next());

        System.out.println("Enter City:");
        student.setCity(insert.next());

        System.out.println("Enter Designation:");
        student.setDesignation(insert.next());

        // Ввод даты рождения (DOB)
        System.out.println("Enter Dob (yyyy-mm-dd):");
        LocalDate dob = null;
        while (dob == null) {
            try {
                dob = LocalDate.parse(insert.next(), formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format. Please enter date in the format yyyy-mm-dd.");
            }
        }
        student.setDob(Date.valueOf(dob));

        // Ввод даты присоединения (DOJ)
        System.out.println("Enter Doj (yyyy-mm-dd):");
        LocalDate doj = null;
        while (doj == null) {
            try {
                doj = LocalDate.parse(insert.next(), formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format. Please enter date in the format yyyy-mm-dd.");
            }
        }
        student.setDoj(Date.valueOf(doj));

        // Ввод зарплаты
        System.out.println("Enter Salary:");
        BigDecimal salary = null;
        while (salary == null) {
            try {
                salary = new BigDecimal(insert.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid format. Please enter a valid decimal number for salary.");
            }
        }
        student.setSalary(salary);

        // Выполнение действия с объектом Student
        action.insert(student);
    }

    public void updateStudent() {
        Student student = new Student();
        Scanner insert = new Scanner(System.in);
        System.out.println("Enter Student Id");
        student.setId(insert.nextLong());
        System.out.println("Enter First Name");
        student.setFname(insert.next());
        System.out.println("Enter Last Name");
        student.setLname(insert.next());
        System.out.println("Enter Address");
        student.setAddress(insert.next());
        System.out.println("Enter Mobile Number");
        student.setMobileNo(insert.next());
        System.out.println("Enter Mail Id");
        student.setMailId(insert.next());
        System.out.println("Enter City");
        student.setCity(insert.next());
        System.out.println("Enter Designation");
        student.setDesignation(insert.next());
        System.out.println("Enter Dob (yyyy-mm-dd)");
        student.setDob(Date.valueOf(insert.next()));
        System.out.println("Enter Doj  (yyyy-mm-dd)");
        student.setDoj(Date.valueOf(insert.next()));
        System.out.println("Enter Salary");
        student.setSalary(insert.nextBigDecimal());
        action.update(student);
    }

    public void deleteStudent() {
        Scanner insert = new Scanner(System.in);
        System.out.println("Enter Student Id");
        long id = insert.nextLong();
        action.delete(id);
    }

    public void fetchStudentById() {
        Scanner insert = new Scanner(System.in);
        System.out.println("Enter Student Id");
        long id = insert.nextLong();
        action.fetchById(id);
    }

    public void fetchStudentByEmail() {
        Scanner insert = new Scanner(System.in);
        System.out.println("Enter Student Mail Id");
        String mailId = insert.next();
        action.fetchByEmailId(mailId);
    }

    public void fetchStudentByMobileNo() {
        Scanner insert = new Scanner(System.in);
        System.out.println("Enter Student Mobile Number");
        String mobileNo = insert.next();
        action.fetchByMobileNo(mobileNo);
    }
    public void searchStudentByName() {
        Scanner insert = new Scanner(System.in);
        System.out.println("Enter Student Name");
        String name = insert.next();
        action.searchByName(name);
    }

    public void fetchStudentByCity() {
        Scanner insert = new Scanner(System.in);
        System.out.println("Enter Student City");
        String city = insert.next();
        action.fetchByCity(city);
    }

    public void fetchStudentBySalaryRange() {
        Scanner insert = new Scanner(System.in);
        System.out.println("Enter Salary Start Range");
        BigDecimal salaryRange1 = insert.nextBigDecimal();
        System.out.println("Enter Salary End Range");
        BigDecimal salaryRange2 = insert.nextBigDecimal();

        action.fetchBySalaryRange(salaryRange1, salaryRange2);
    }

    public void fetchStudentByDob() {
        Scanner insert = new Scanner(System.in);
        System.out.println("Enter Date of Birth (yyyy-mm-dd)");
        String dob = insert.next();
        action.fetchByDob(Date.valueOf(dob));
    }

    public void fetchStudentByDOjRange() {
        Scanner insert = new Scanner(System.in);
        System.out.println("Enter Start Date of Joining (yyyy-mm-dd)");
        String dob1 = insert.next();
        System.out.println("Enter End Date of Joining (yyyy-mm-dd)");
        String dob2 = insert.next();
        action.fetchByRangeDoj(Date.valueOf(dob1), Date.valueOf(dob2));
    }

    public void fetchAllStudent() {
        action.fetchAll();
    }

    public void addBankCard()
    {
        Scanner insert = new Scanner(System.in);
        BankCard bankCard = new BankCard();

        System.out.println("Enter Card Number:");
        bankCard.setCardNumber(insert.nextLine());

        System.out.println("Enter CVV:");
        bankCard.setCVV(insert.nextInt());

        System.out.println("Enter Balance:");
        bankCard.setBalance(insert.nextBigDecimal());

        bankCardAction.insert(bankCard);
    }

    public void fetchBankCardByCardNumber()
    {
        Scanner insert = new Scanner(System.in);
        System.out.println("Enter Card Number:");
        String cardNumber = insert.nextLine();

        bankCardAction.fetchByCardNumber(cardNumber);
    }

    public void transferByCardNumber() throws SQLException {
        Scanner insert = new Scanner(System.in);
        System.out.println("Введите ваш номер карты:");
        String yourCardNumber = insert.nextLine();
        System.out.println("Введите номер карты получателя:");
        String RecCardNumber = insert.nextLine();
        System.out.println("Введите сумму перевода:");
        BigDecimal value = insert.nextBigDecimal();

        bankCardAction.transfer(yourCardNumber, RecCardNumber, value);
    }
}
