package practice_3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Customer {
    public List<Account> accounts = new ArrayList<>();

    public void add(Account account) {
        accounts.add(account);
    }

    public Iterator<Account> getAccounts() {
        return accounts.iterator();
    }
}
