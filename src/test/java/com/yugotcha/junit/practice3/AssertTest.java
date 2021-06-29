package com.yugotcha.junit.practice3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.number.IsCloseTo.*;

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

    /* 중요한 hamcrest matcher 살펴보기 */

    // 자바 배열 객체 비교
    @Disabled("This test gonna be fail.")
    @Test
    public void comparesArrayFailing() {
        assertThat(new String[] {"a", "b", "c"}, equalTo(new String[] {"a", "b"}));
    }

    @Test
    public void comparesArrayPassing() {
        assertThat(new String[] {"a", "b"}, equalTo(new String[] {"a", "b"}));
    }

    // 자바 컬렉션 객체 비교
    @Disabled("This test gonna be fail.")
    @Test
    public void comparesCollectionsFailing() {
        assertThat(Arrays.asList(new String[] {"a"}),
                equalTo(Arrays.asList(new String[] {"a", "ab"})));
    }

    @Test
    public void comparesCollectionsPassing() {
        assertThat(Arrays.asList(new String[] {"a"}),
                equalTo(Arrays.asList(new String[] {"a"})));
    }

    // 다양한 matcher 사용해보기
    @Test
    public void variousMatcherTests() {
        Account account = new Account("my big fat acct");
        assertThat(account.getName(), is(equalTo("my big fat acct")));

        assertThat(account.getName(), allOf(startsWith("my"), endsWith("acct")));

        assertThat(account.getName(), anyOf(startsWith("my"), endsWith("loot")));

        assertThat(account.getName(), not(equalTo("plunderings")));

        assertThat(account.getName(), is(not(nullValue())));
        assertThat(account.getName(), is(notNullValue()));

        assertThat(account.getName(), isA(String.class));

        assertThat(account.getName(), is(notNullValue())); // not helpful
        assertThat(account.getName(), equalTo("my big fat acct")); //이름이 일치할거인지 물어볼거면 null 검사가 필요 없음 -> 중복됨
    }

    @Test
    @SuppressWarnings("unchecked") // 무점검(형변환, 메서드 호출 등)경고 억제 어노테이션
    public void items() {
        List<String> names = new ArrayList<>();
        names.add("Moe");
        names.add("Larry");
        names.add("Curly");

        assertThat(names, hasItem("Curly"));

        assertThat(names, hasItems("Curly", "Moe"));

        assertThat(names, hasItem(endsWith("y")));

        assertThat(names, hasItems(endsWith("y"), startsWith("C"))); //warning!

        assertThat(names, not(everyItem(endsWith("y"))));
    }

    /* 부동소수점 비교 */

    @Test
    public void isFloatEquals() {
        //assertThat(2.32*3, equalTo(6.96));
        //허용 오차 지정해줘야 함
        assertTrue(Math.abs((2.32*3) - 6.96) < 0.0005);
        //더 직관적으로 사용하기
        assertThat(2.32*3, closeTo(6.96, 0.0005));
    }
}
