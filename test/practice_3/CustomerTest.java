package practice_3;

import  org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

//정적 임포트
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


public class CustomerTest {

    private Account account;

    @Before
    public void createAccount() {
        account =  new Account("an account name");
    }

    @Test
    public void hasPositiveBalance() {
        account.deposit(50);
        assertTrue(account.hasPositiveBalance());
    }

    @Test
    public void depositIncreaseBalance() {
        int initialBalance = account.getBalance();
        account.deposit(100);
        assertTrue(account.getBalance() > initialBalance);
        MatcherAssert.assertThat(account.getBalance(), equalTo(100));
    }

    @Test
    public void depositIncreasesBalance_hamcrestAssertTrue(){
        account.deposit(50);
        MatcherAssert.assertThat(String.valueOf(account.getBalance() > 0), true);
    }



}