package practice_3;

import javax.naming.InsufficientResourcesException;

public class Account {
    int balance;
    String name;

    Account(String name) {
        this.name = name;
    }

    public void deposit(int dollars) {
        balance += dollars;
    }

    void withdraw(int dollars) throws InsufficientResourcesException {
        if(balance < dollars) {
            throw new InsufficientResourcesException("balance only " + balance);
        }
        balance -= dollars;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public boolean hasPositiveBalance() {
        return balance > 0;
    }
}
