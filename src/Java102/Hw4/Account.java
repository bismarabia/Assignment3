package Java102.Hw4;

/*
* Name: Rabia Abismail
* ID: 140201209
* Project on Github:
* */

public class Account {

    public static void main(String[] arg){
        Account a = new Account("5678", "1234", 1000);
        System.out.println("Key : "+a.getKey()+" , the pin is : "+a.getPin());
        System.out.println("Not logged in withdrawal = "+a.withdrawFunds(20));
        System.out.println("Login attempt with encrypted PIN value: "+a.logIn(a.getAcctNum(), a.getPin()));
        System.out.println("Not logged in withdrawal = "+a.withdrawFunds(20));
        System.out.println("Login attempt with wrong order: "+a.logIn("1234", "5678"));
        System.out.println("Not logged in withdrawal = "+a.withdrawFunds(20));
        System.out.println("Login attempt with correct Pin : "+a.logIn("5678", "1234"));
        System.out.println("Negative withdrawal = "+a.withdrawFunds(-20));
        System.out.println("too much withdrawal = "+a.withdrawFunds(1200));
        System.out.println("Valid withdrawal = "+a.withdrawFunds(20));
        a.deposit(300);
        a.logOut();
        a.deposit(100);
        System.out.println("Final balance : "+ a.getBalance());

    }

    private String accNum, pin;
    private double balance;
    private boolean loggedIn;
    private int key;

    public Account(String accNum, String pin, double balance) {
        this.accNum = accNum;
        this.balance = balance;
        this.key = (int) (Math.random()*9)+1;
        setPin(pin);
        this.loggedIn = false;

    }

    public String getAcctNum() {
        return accNum;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public int getKey() {
        return key;
    }

    // encrypting this.pin by adding key to each of its number
    public void setPin(String pin) {
        this.pin = "";
        for (int i=0; i<pin.length(); i++)
            this.pin += String.valueOf(Integer.parseInt(String.valueOf(pin.charAt(i))) + getKey());
    }

    // logging in
    public boolean logIn(String accNum, String pin) {
        loggedIn = false;
        if (getAcctNum().equals(accNum)) {
            String tmp = this.pin;          // save the value of this.pin into tmp
            setPin(pin);                    // setPin(pin) will encrypt this.pin based on key and pin
            if (tmp.equals(getPin())) {
                loggedIn = true;
            }
            this.pin = tmp;                 // give the value back to this.pin
        }
        return loggedIn;
    }

    // logout
    public void logOut(){
        this.loggedIn = false;
    }

    // withdraw money
    public double withdrawFunds(double amount){
        if (!loggedIn)
            return -3;
        if (amount<0)
            return -2;
        if (amount>getBalance())
            return -1;
        this.balance -= amount;
        return this.balance;
    }

    // deposit money
    public void deposit(double amount){
        // the user should be logged in and the amount should be valid
        if (loggedIn && amount>=0)
            this.balance += amount;
    }
}
