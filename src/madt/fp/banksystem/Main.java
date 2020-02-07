package madt.fp.banksystem;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;



/* helping code to read and write in java, taken code and modified
 * https://crunchify.com/java-saving-and-loading-data-from-a-file-simple-production-ready-utility-for-file-readwrite-operation/
 */

/* used code to color the console output
* https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
* */


public class Main {

    private static Gson gson = new Gson();
    private static Scanner sc;

    private static String records_file_location = "C:\\Users\\Jassie\\Desktop\\MADT.txt"; // Windows
    // MAC :- "/Users/MacStudent/Desktop/Java.txt";

    public static String empnme;

    public static long accnumber;
    public static int index;
    public static Client clientData = new Client();
    public static ArrayList<Client> listAlldata = new ArrayList<Client>();

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) {

        //Scanner object creation for input value
        sc = new Scanner(System.in);
        System.out.print(ANSI_GREEN + "***********************************************************************\n");
        System.out.print("****                                                               ****\n");
        System.out.print("***                   BANKING SYSTEM                                ***\n");
        System.out.print("****                                                               ****\n");
        System.out.print("***********************************************************************\n"+ ANSI_RESET);

        login(); // login skipped for testing

    }

    private static void log(String string) { // write to console
        System.out.println("\n" + string);
    }

    private static void login() { // login using bank employee id

        log("Welcome to MADT Bank, please login..\n\nEnter Employee id :- ");
        String logid = sc.next();
        String pass;

        if (logid.equals(Users.empids[0])) {
            empnme = Users.empnme[0];
            log("Enter Employee password :- ");
            pass = sc.next();
            if (!pass.equals(Users.emppas[0])) {
                log("Password is wrong,please relogin...");
                main(null);
                return;
            }

        } else if (logid.equals(Users.empids[1])) {
            empnme = Users.empnme[1];
            log("Enter Employee password :- ");
            pass = sc.next();
            if (!pass.equals(Users.emppas[1])) {
                log("Password is wrong,please relogin...");
                main(null);
                return;
            }

        } else if (logid.equals(Users.empids[2])) {
            empnme = Users.empnme[2];
            log("\nEnter Employee password :- ");
            pass = sc.next();
            if (!pass.equals(Users.emppas[2])) {
                log("Password is wrong,please relogin...");
                main(null);
                return;
            }

        } else {
            log("Employee doesn't exists in system, please contact administrator...");
            main(null);
            return;
        }

        startBanking();

    }

    public static void startBanking() { // main dashboard
        System.out.print(ANSI_CYAN+"***********************************************************************\n");
        System.out.print("Hi " + empnme + ", welcome to dashboard\n");
        System.out.print("Select Options\n");
        System.out.print("1. Open Existing Account\n");
        System.out.print("2. Open New Account\n");
        System.out.print("3. Exit \n");
        System.out.print("***********************************************************************\n" + ANSI_RESET);

        switch (sc.next()) {
            case "1": // open existing account for operations

                /*
                log("Enter valid Account Number:\n");
                // VALIDATION 1 :- only long is allowed add code to fix it
                long accNo = sc.nextLong();
                accnumber = accNo;
                existingAccount(accNo);*/

                do {
                    try {
                        log("Enter valid Account Number:\n");
                        // VALIDATION 1 :- only long is allowed add code to fix it
                        long accNo = sc.nextLong();
                        if (accNo > 0) {
                            accnumber = accNo;
                            existingAccount(accNo);
                            break;
                        }
                    } catch (Exception e) {
                    }
                    log("Error! Incorrect Input,try again...");
                    sc.nextLine();
                } while (true);

                break;
            case "2": // create new account
                log("Enter details for new account:\n");
                createNewAccount();

                break;
            case "3":
                // exit
                log("\nThank You for using Bank System, have a good day!\n");
                System.exit(0);

                break;

            default:
                log("\nPlease enter the valid option!\n");
                startBanking();
                return;
        }
    }

    public static void existingAccount(long accNo) { // open existing account

        boolean flag = false;

        ArrayList<Client> listAll = new ArrayList<>();
        listAll = ReadData();
        listAlldata = listAll;

        Client client = new Client();

        for (int i = 0; i < listAll.size(); i++) {
            if (accNo == listAll.get(i).getAccountNo()) {
                flag = true;
                client = listAll.get(i);
                clientData = client;
                index = i;
                break;
            }
        }

        if (flag == false) {
            log("Account Not found, please open a new account!"); // File doesn't exist, means no account is created yet
            startBanking();
            return;
        }

        System.out.print("\nHi, " + client.getFname() + " " + client.getLname() + " your Account details are:\n");
        System.out.print("Account Number : " + client.getAccountNo() + "\n");
        System.out.print("Customer ID : " + client.getCustomerID() + "\n");
        System.out.print("Account Type : " + client.getAccountType() + "\n");
        System.out.print("Balance : " + client.getAmount() + "\n");
        System.out.print("DOB : " + client.getDOB() + "\n");
        System.out.print("Mobile No : " + client.getMobileNo() + "\n");
        System.out.print("House No : " + client.getHouseNo() + "\n");
        System.out.print("Street Name : " + client.getStreetName() + "\n");
        System.out.print("City : " + client.getCityName() + "\n");
        System.out.print("Province : " + client.getProvinceName() + "\n");
        System.out.print("Postal Code : " + client.getPostal() + "\n");
        System.out.print("Country : " + client.getCountry() + "\n");
        System.out.print("Nominee Name " + client.getNomineeFName() + " " + client.getNomineeLName());

        // add code here for menu
        menu();

    }

    public static void menu() { // menu inside exisiting account
        System.out.print("\n\n***********************************************************************\n");
        System.out.print("Banking options...\n");
        System.out.print("1 Check Balance\n");
        System.out.print("2 Deposit\n");
        System.out.print("3 Withdraw\n");
        System.out.print("4 Transfer\n");
        System.out.print("5 Pay Bills\n");
        System.out.print("6 Update Details\n");
        System.out.print("7 Back to Dashboard\n");
        System.out.print("***********************************************************************\n");

        switch (sc.next()) {
            case "1":

                checkAmt();

                menu();
                break;

            case "2":

                do {
                    try {
                        log("\nEnter amount to deposit: \n");
                        // VALIDATION 2 :- only double is allowed add code to fix it
                        double deposit = sc.nextDouble();
                        if (deposit > 0) {
                            depositAmt(deposit);
                            break;
                        }
                    } catch (Exception e) {
                    }
                    log("Error! Incorrect Input,try again...");
                    sc.nextLine();
                } while (true);

                menu();
                break;

            case "3":

                do {
                    try {
                        log("\nEnter amount to withdraw: \n");
                        double withdraw = sc.nextDouble();
                        // VALIDATION 3 :- only double is allowed add code to fix it

                        if (withdraw > 0) {

                            if (clientData.getAmount() >= withdraw) {
                                withdrawAmt(withdraw);
                                break;
                            } else {
                                log("Unable to withdraw due to insufficient balance...");
                            }

                        }
                    } catch (Exception e) {
                    }
                    log("Error! Incorrect Input,try again...");
                    sc.nextLine();
                } while (true);

                menu();
                break;

            case "4":

                // VALIDATION 4 :- only long is allowed add code to fix it
                long acc = 0;
                do {
                    try {
                        log("\nEnter Account no to transfer: \n");
                        acc = sc.nextLong();
                        if (acc > 0) {
                            break;
                        }
                    } catch (Exception e) {
                    }
                    log("Error! Incorrect Input,try again...");
                    sc.nextLine();
                } while (true);

                // VALIDATION 5 :- only double is allowed add code to fix it
                do {
                    try {
                        log("\nEnter amount to Transfer: \n");
                        double trans = sc.nextDouble();
                        if (trans > 0) {
                            transfer(acc, trans);
                            break;
                        }
                    } catch (Exception e) {
                    }
                    log("Error! Incorrect Input,try again...");
                    sc.nextLine();
                } while (true);

                menu();
                break;

            case "5":

                payBills();

                menu();
                break;

            case "6":

                updateDetails();

                menu();
                break;

            case "7":

                startBanking();
                break;

            default:
                log("\nPlease enter the valid option!\n");
                menu();
                return;

        }

    }

    public static void checkAmt() { // check balance
        log("\nCurrent Balance: " + clientData.getAmount());
    }

    public static void depositAmt(double amount) { // deposit amount

        clientData.setAmount(clientData.getAmount() + amount); // update client model
        listAlldata.set(index, clientData); // update array list

        Records records = new Records();
        records.setData(listAlldata);

        WriteData(gson.toJson(records), clientData.getAccountNo() + "", false, 1);

    }

    public static void withdrawAmt(double amount) { // withdraw amount

        clientData.setAmount(clientData.getAmount() - amount);
        listAlldata.set(index, clientData);

        Records records = new Records();
        records.setData(listAlldata);

        WriteData(gson.toJson(records), clientData.getAccountNo() + "", false, 1);

    }

    public static void transfer(long acc, double amount) { // transfer money to other account

        // VALIDATION 6 :- check if account no exists to which we need to transfer and also check if sufficient balance to transfer

        if (clientData.getAmount() >= amount) {

            // add
            boolean isAccExist = false;
            Client client = new Client();
            for (int i = 0; i < listAlldata.size(); i++) {
                if (acc == listAlldata.get(i).getAccountNo()) {
                    isAccExist = true;
                    client = listAlldata.get(i);
                    client.setAmount(client.getAmount() + amount);
                    listAlldata.set(i, client);

                    break;
                }
            }

            if(!isAccExist) {
                log("Account Number does not exist, enter valid number..");
                return;
            }

            // deduct
            clientData.setAmount(clientData.getAmount() - amount);
            listAlldata.set(index, clientData);

        } else {
            log("Unable to tansfer due to insufficient balance...");
            return;
        }

        // update to file
        Records records = new Records();
        records.setData(listAlldata);

        WriteData(gson.toJson(records), clientData.getAccountNo() + "", false, 1);

    }

    public static void payBills() { // pay the bills using account

        System.out.print("Select Number to pay bill\n");
        System.out.print("1 Mobile Bill\n");
        System.out.print("2 Broadband Bill\n");
        System.out.print("3 Electricity Bill\n");
        System.out.print("4 Water Bill\n");
        System.out.print("5 Back to Menu\n");

        String acc; // sometimes characters exists in account no. so took string not int or long
        Double amt;
        UUID uuid;

        switch (sc.next()) {
            case "1":

                /////////////////////////////////// CONTINUE ETHO //////////////////////

                log("Enter Account No. For Mobile Bill:\n");
                acc = sc.next();

                // VALIDATION 7 :- only double is allowed add code to fix it
                // ALSO CHECK SUFFICIENT BALANCE TO PAY BIILS ? else call menu

                do {
                    try {
                        log("Enter Amount to pay for mobile:\n");
                        amt = sc.nextDouble();
                        if (amt > 0) {

                            if(clientData.getAmount()>= amt) {
                                withdrawAmt(amt);

                                uuid = UUID.randomUUID();
                                log("Bill Paid Successfully for mobile account no: " + acc + "\nReference no: " + uuid.toString());

                                break;
                            }else{
                                log("Unable to pay bill due to insufficient balance...");
                            }

                        }
                    } catch (Exception e) {
                    }
                    log("Error! Incorrect Input,try again...");
                    sc.nextLine();
                } while (true);

                break;
            case "2":

                log("Enter Account No. For Broadband Bill:\n");
                acc = sc.next();

                // VALIDATION 8 :- only double is allowed add code to fix it
                // ALSO CHECK SUFFICIENT BALANCE TO PAY BIILS ? else call menu

                do {
                    try {
                        log("Enter Amount to pay for broadband :\n");
                        amt = sc.nextDouble();
                        if (amt > 0) {

                            if(clientData.getAmount()>= amt) {
                                withdrawAmt(amt);

                                uuid = UUID.randomUUID();
                                log("Bill Paid Successfully for broadband account no: " + acc + "\nReference no: " + uuid.toString());

                                break;
                            }else{
                                log("Unable to pay bill due to insufficient balance...");
                            }

                        }
                    } catch (Exception e) {
                    }
                    log("Error! Incorrect Input,try again...");
                    sc.nextLine();
                } while (true);

                break;
            case "3":

                log("Enter Account No. For Electricity Bill:\n");
                acc = sc.next();

                // VALIDATION 9 :- only double is allowed add code to fix it
                // ALSO CHECK SUFFICIENT BALANCE TO PAY BIILS ? else call menu

                do {
                    try {
                        log("Enter Amount to pay for electricity :\n");
                        amt = sc.nextDouble();
                        if (amt > 0) {

                            if(clientData.getAmount()>= amt) {
                                withdrawAmt(amt);

                                uuid = UUID.randomUUID();
                                log("Bill Paid Successfully for electricity account no: " + acc + "\nReference no: " + uuid.toString());

                                break;
                            }else{
                                log("Unable to pay bill due to insufficient balance...");
                            }

                        }
                    } catch (Exception e) {
                    }
                    log("Error! Incorrect Input,try again...");
                    sc.nextLine();
                } while (true);

                break;
            case "4":

                log("Enter Account No. For Water Bill:\n");
                acc = sc.next();

                // VALIDATION 10 :- only double is allowed add code to fix it
                // ALSO CHECK SUFFICIENT BALANCE TO PAY BIILS ? else call menu

                do {
                    try {
                        log("Enter Amount to pay for water :\n");
                        amt = sc.nextDouble();
                        if (amt > 0) {

                            if(clientData.getAmount()>= amt) {
                                withdrawAmt(amt);

                                uuid = UUID.randomUUID();
                                log("Bill Paid Successfully for water account no: " + acc + "\nReference no: " + uuid.toString());

                                break;
                            }else{
                                log("Unable to pay bill due to insufficient balance...");
                            }

                        }
                    } catch (Exception e) {
                    }
                    log("Error! Incorrect Input,try again...");
                    sc.nextLine();
                } while (true);

                break;
            case "5":

                menu();
                break;

            default:
                log("\nPlease enter the valid option!\n");
                menu();
                return;
        }

    }

    public static void updateDetails() { // update details of client

        System.out.print("Select Number to update information\n");
        System.out.print("1 Mobile No.\n");
        System.out.print("2 House No.\n");
        System.out.print("3 Street Name\n");
        System.out.print("4 City Name\n");
        System.out.print("5 Province Name\n");
        System.out.print("6 Postal\n");
        System.out.print("7 Country\n");
        System.out.print("8 Nominee First Name\n");
        System.out.print("9 Nominee Last Name\n"); //nextline() isu=sue so taking next as input
        System.out.print("10 Back to Menu\n");

        switch (sc.next()) {
            case "1":

                // VALIDATION 11 :- only long and mob no. length add code to fix it
                do {
                    try {
                        log("Enter new Mobile no. to update\n");
                        long mob = sc.nextLong();
                        if (mob > 0 && (""+mob).length() >=10 && (""+mob).length() <= 14) {
                            clientData.setMobileNo(mob);
                            break;
                        }
                    } catch (Exception e) {
                    }
                    log("Error! Incorrect Input,try again...");
                    sc.nextLine();
                } while (true);

                break;
            case "2":


                // VALIDATION 12 :- only int add code to fix it

                do {
                    try {
                        log("Enter new House no. to update\n");
                        int hno = sc.nextInt();
                        if (hno > 0) {
                            clientData.setHouseNo(hno);
                            break;
                        }
                    } catch (Exception e) {
                    }
                    log("Error! Incorrect Input,try again...");
                    sc.nextLine();
                } while (true);

                break;
            case "3":

                log("Enter new Street name to update\n");
                String stname = sc.next();
                clientData.setStreetName(stname);

                break;

            case "4":

                log("Enter new City name to update\n");
                String ctname = sc.next();
                clientData.setCityName(ctname);

                break;

            case "5":

                log("Enter new Province name to update\n");
                String prname = sc.next();
                clientData.setProvinceName(prname);

                break;

            case "6":

                log("Enter new Postal to update\n");
                String ptname = sc.next();
                clientData.setPostal(ptname);

                break;

            case "7":

                log("Enter new Country name to update\n");
                String cnname = sc.next();
                clientData.setCountry(cnname);

                break;

            case "8":

                log("Enter new Nominee first name to update\n");
                String nfnname = sc.next();
                clientData.setNomineeFName(nfnname);

                break;

            case "9":

                log("Enter new Nominee last name to update\n");
                String nlnname = sc.next();
                clientData.setNomineeLName(nlnname);

                break;

            case "10":

                menu();
                return;

            default:
                log("\nPlease enter the valid option!\n");
                menu();
                return;

        }

        listAlldata.set(index, clientData); // update array list

        Records records = new Records();
        records.setData(listAlldata);

        WriteData(gson.toJson(records), clientData.getAccountNo() + "", false, 1);

    }

    public static void createNewAccount() { // create new account

        ArrayList<Client> records_list = new ArrayList<Client>();

        Client client = new Client();
        client.setAccountNo(generateAccountNo(9));

        System.out.print("Enter your First Name\n");
        client.setFname(sc.next());

        System.out.print("Enter your Last Name\n");
        client.setLname(sc.next());

        System.out.print("Select Number for your Gender\n");
        System.out.print("1 Male\n");
        System.out.print("2 Female\n");
        System.out.print("3 Other\n");

        switch (sc.next()) {
            case "1":

                client.setGender("Male");
                break;
            case "2":

                client.setGender("Female");
                break;
            case "3":

                client.setGender("Other");
                break;

            default:
                log("\nWrong option, taking Other as default!\n");
                client.setGender("Other");
                break;
        }

        System.out.print("Enter your Nominee's First Name\n");
        client.setNomineeFName(sc.next());

        System.out.print("Enter your Nominee's Last Name\n");
        client.setNomineeLName(sc.next());

        System.out.print("Select Number for your Account Type\n");
        System.out.print("1 Checking\n");
        System.out.print("2 Savings\n");

        switch (sc.next()) {
            case "1":
                client.setAccountType("Checking");
                break;
            case "2":
                client.setAccountType("Savings");
                break;
            default:
                log("\nWrong option, taking Checking as default!\n");
                client.setGender("Checking");
                break;
        }

        // VALIDATION 13 :- only double/Int also fine add code to fix it
        do {
            try {
                System.out.print("Enter your Initial Deposit Amount($)\n");
                double deposit = sc.nextDouble();
                if (deposit > 0) {
                    client.setAmount(deposit);
                    break;
                }
            } catch (Exception e) {
            }
            log("Error! Incorrect Input,try again...");
            sc.nextLine();
        } while (true);

        System.out.print("Enter Customer ID (Passport/Govt. ID)\n");
        client.setCustomerID(sc.next());

        System.out.print("Enter your DOB in format ddMMyyyy\n");
        client.setDOB(sc.next());

        // VALIDATION 14 :- only long add code to fix it
        do {
            try {
                System.out.print("Enter your Mobile Number\n");
                long mob = sc.nextLong();
                if (mob > 0 && (""+mob).length() >=10 && (""+mob).length() <= 14) {
                    client.setMobileNo(mob);
                    break;
                }
            } catch (Exception e) {
            }
            log("Error! Incorrect Input,try again...");
            sc.nextLine();
        } while (true);

        // VALIDATION 15 :- only int add code to fix it
        do {
            try {
                System.out.print("Enter your House Number\n");
                int hno = sc.nextInt();
                if (hno > 0) {
                    client.setHouseNo(hno);
                    break;
                }
            } catch (Exception e) {
            }
            log("Error! Incorrect Input,try again...");
            sc.nextLine();
        } while (true);

        System.out.print("Enter your Street Name\n");
        client.setStreetName(sc.next());

        System.out.print("Enter your City Name\n");
        client.setCityName(sc.next());

        System.out.print("Enter your Province Name\n");
        client.setProvinceName(sc.next());

        System.out.print("Enter your Postal Code\n");
        client.setPostal(sc.next());

        System.out.print("Enter your Country\n");
        client.setCountry(sc.next());

        records_list = ReadData(); // added fix

        records_list.add(client);

        Records records = new Records();
        records.setData(records_list);
        WriteData(gson.toJson(records), client.getAccountNo() + "", false, 0);

    }

    public static int generateAccountNo(int n) {  // generate account
        int m = (int) Math.pow(10, n - 1);
        return m + new Random().nextInt(9 * m);
    }

    private static void WriteData(String myData, String AccountNo, boolean append, int flag) { // write data to file

        File file = new File(records_file_location);

        if (!file.exists()) {
            try {
                File direc = new File(file.getParent());
                if (!direc.exists()) {
                    direc.mkdirs();
                }
                file.createNewFile();
            } catch (Exception e) {
                log("Excepton Occured: " + e.toString());
            }
        }

        try {

            FileWriter writer = new FileWriter(file.getAbsoluteFile(), append);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(myData.toString());
            bufferWriter.close();

            if (flag == 0) {
                System.out.print("Account Number is : " + AccountNo + "\n\n");
                System.out.print("\n**** Account is created successfully ****\n");
                startBanking();
            } else {
                System.out.print("Account Number is : " + AccountNo + "\n\n");
                System.out.print("\n**** Account is updated successfully ****\n");
            }

        } catch (IOException e) {
            log("Got an error while saving data to file: " + e.toString());
        }
    }

    public static ArrayList<Client> ReadData() { // read data from file
        ArrayList<Client> listAll = new ArrayList<Client>();

        File file = new File(records_file_location);
        if (!file.exists()) {
            log("No records file found, on creation of new account it will be created automatically else skipped:\n");
            return listAll;
        }
        try {
            InputStreamReader isReader = new InputStreamReader(new FileInputStream(file), "UTF-8");

            JsonReader myReader = new JsonReader(isReader);

            Records rec = gson.fromJson(myReader, Records.class);

            listAll.addAll(rec.getData());

        } catch (Exception e) {
            log("Error occurred: \n" + e.toString());
        }

        return listAll;
    }

}
