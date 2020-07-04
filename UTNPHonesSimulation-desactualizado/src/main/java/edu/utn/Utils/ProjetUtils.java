package edu.utn.Utils;

import edu.utn.Model.Bill;
import edu.utn.Model.User;

import java.time.LocalDate;
import java.util.*;

public class ProjetUtils {

    public static List<User> generateUsers(){

        List<User> users = new ArrayList<>();
        User u1 = new User(1, "Abulzomi", "1234");
        User u2 = new User(2, "Sescribas", "1234");
        users.add(u1);
        users.add(u2);

        return users;
    }



    public static Map generateBillsForUsers(){




        List<User> users = generateUsers();
        Map<Integer, List<Bill>> billsPerUser = new Hashtable<>();

        for(User u: users ){

            List<Bill> bills = generateBillsForOneUser(u);
            billsPerUser.put(u.getId(),bills);


        }

        return billsPerUser;

    }


    private static String generateLine(){
        String line = "2235";
        for (int i = 0; i < 6 ; i++) {

            line += generateInteger()%10;
        }

        return line;
    }

    private static List<Bill> generateBillsForOneUser(User user){
        Integer year = 2019;
        Integer month = 1;
        List<Bill> billsOfUser = new ArrayList<>();
        String line = generateLine();

        for (int i = generateInteger()%12; i < 12 ; i++) {
            Bill newBill = generateBill(user, line,year,month);
            billsOfUser.add(newBill);
            month++;
        }


        return billsOfUser;
    }


    private static Bill generateBill(User user, String line, Integer year, Integer month){
        return Bill.builder()
                .user(user)
                .line(line)
                .date(LocalDate.of(year,month,1))
                .dateExpiration(LocalDate.of(year,month,15))
                .priceTotal(generateFloat()+50)
                .isPaid(new Random().nextBoolean())
                .quantityOfCalls(generateInteger()%15)
                .build();
    }


    private static Integer generateInteger(){

        Integer number = new Random().nextInt();
        if (number < 0){
            number = number*-1;
        }
        return number;
    }


    private static Float generateFloat(){

        Float number = new Random().nextFloat();
        if (number < 0){
            number = number*-1;
        }
        return number;
    }







}
