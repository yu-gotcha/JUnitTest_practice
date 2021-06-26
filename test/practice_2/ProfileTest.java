package practice_2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/* JUnit은 결정된 순서로 테스트 실행하지 않음 */
/* 모든 테스트는 다른 테스트 결과에 영향을 받지 않음 */
/* 각각 별도의 ProfileTest 인스턴스 생성 */

public class ProfileTest {

    @Test
    public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
        //Arrange 준비
        Profile profile = new Profile("Bull Hockey, Inc.");
        Question question = new BooleanQuestion(1, "Got bonuses?");
        Answer profileAnswer = new Answer(question, Bool.FALSE);
        profile.add(profileAnswer);
        Criteria criteria = new Criteria();
        Answer criteriaAnswer = new Answer(question, Bool.TRUE);
        Criterion criterion = new Criterion(criteriaAnswer, Weight.MustMatch);
        criteria.add(criterion);

        //Action 실행
        boolean matches = profile.matches(criteria);

        //Assert 단언
        assertFalse(matches);
    }

    @Test
    public void matchAnswersTrueForAnyDontCareCriteria() {
        //Arrange 준비
        Profile profile = new Profile("Bull Hockey, Inc.");
        Question question = new BooleanQuestion(1, "Got milk?");
        Answer profileAnswer = new Answer(question, Bool.FALSE);
        profile.add(profileAnswer);
        Criteria criteria = new Criteria();
        Answer criteriaAnswer = new Answer(question, Bool.TRUE);
        Criterion criterion = new Criterion(criteriaAnswer, Weight.DontCare);
        criteria.add(criterion);

        //Action 실행
        boolean matches = profile.matches(criteria);

        //Assert 단언
        assertTrue(matches);
    }
}