package com.yugotcha.junit.practice3;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import javax.naming.InsufficientResourcesException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class ExceptionTest {

    private Account account;

    @BeforeEach
    public void createAccount() {
        account =  new Account("an account name");
    }


    //Old fashioned try/catch & fail
    /*
    @Tag("JUnit version 4")
    @Test(expected=InsufficientFundsException.class)
    public void throwsWhenWithdrawingTooMuch() {
        account.withdraw(100);
    }
    */


    @Test
    public void throwsWhenWithdrawTooMuch() {
        InsufficientFundsException exception =
                assertThrows(InsufficientFundsException.class, ()-> account.withdraw(100));
    }

    @Test
    public void throwsWhenWithdrawingTooMuchTry() {
        try {
            account.withdraw(100);
            fail();
        }
        catch (InsufficientFundsException expected) {
            assertThat(expected.getMessage(), equalTo("balance only 0"));
        }
    }

    //new ExpectedException
    /*
    @Test
    public void exceptionRule() {
        thrown.expect(InsufficientFundsException.class);
        thrown.expectMessage("balance only 0");

        account.withdraw(100);
    }
    */


    @Test
    public void exceptionRule() {
        InsufficientFundsException exception =
                assertThrows(InsufficientFundsException.class, ()-> account.withdraw(100),
                        "balance only 0");
    }
}
