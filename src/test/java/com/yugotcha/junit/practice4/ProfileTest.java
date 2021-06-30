package com.yugotcha.junit.practice4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    /* 3장에서의 테스트 코드를 이와 같이 합칠 수 있음 */
    // 이렇게 하면 테스트를 분리하여 메소드를 작성했을때 실행되는 반복적인 공통 초기화 부분(Before에 있던거)의 부담을 줄일 수 있음
    // 하지만 JUnit test 에서 주요 핵심 중 하나인 고립의 중요한 이점을 잃게 됨
    // 단언이 실패하면 즉시 error를 던져 테스트가 중단되므로 동작과 모든 케이스별로 나누어 이름을 직관적으로 작성해야함
    // 그렇게 하면 실패 시 어디에서 문제가 발생했는지 직관적으로 알기 쉽고 테스트 해독에 대한 시간도 줄일 수 있음

    //어떤 이름이 좋을까? 88-89p

    @Test
    public void matches() {
        Profile profile = new Profile("Bull Hockey, Inc.");
        Question question = new BooleanQuestion(1, "Got milk?");

        //mus-match 항목이 맞지 않으면 false
        profile.add(new Answer(question, Bool.FALSE));
        Criteria criteria = new Criteria();
        criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.MustMatch));

        assertFalse(profile.matches(criteria));

        //don't care 항목에 대해서는 true
        profile.add(new Answer(question, Bool.FALSE));
        criteria = new Criteria();
        criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare));

        assertTrue(profile.matches(criteria));
    }
}