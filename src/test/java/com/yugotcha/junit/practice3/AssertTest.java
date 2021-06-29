package com.yugotcha.junit.practice3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssertTest {

    private Account account;

    @BeforeEach
    public void createAccount() {
        account =  new Account("an account name");
    }

    /* assertTrue 와 assertThat */
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
        assertThat(account.getBalance(), equalTo(100));
    }

    @Test
    public void depositIncreasesBalance_hamcrestAssertTrue(){
        account.deposit(50);
        assertThat(String.valueOf(account.getBalance() > 0), true); // 복잡하게 사용하는거 권장하지 않음
        assertTrue((account.getBalance())>0); // 차라리 이런 식으로 간단하게 사용하는것을 권장함
    }
}
