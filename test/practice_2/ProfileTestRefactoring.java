package practice_2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/* JUnit은 결정된 순서로 테스트 실행하지 않음 */
/* 모든 테스트는 다른 테스트 결과에 영향을 받지 않음 */
/* 각각 별도의 ProfileTest 인스턴스 생성 */

public class ProfileTestRefactoring {

    private Profile profile;
    private BooleanQuestion question;
    private Criteria criteria;

    @Before
    public void create() {
        System.out.println("creating");
        profile = new Profile("Bull Hockey, Inc.");
        question = new BooleanQuestion(1, "Got bonuses?");
        criteria = new Criteria();
    }

    @Test
    public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
        //Arrange 준비
        profile.add(new Answer(question, Bool.FALSE));
        criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.MustMatch));

        //Action 실행
        boolean matches = profile.matches(criteria);

        //Assert 단언
        assertFalse(matches);
    }

    @Test
    public void matchAnswersTrueForAnyDontCareCriteria() {
        //Arrange 준비
        profile.add(new Answer(question, Bool.FALSE));
        criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare));

        //Action 실행
        boolean matches = profile.matches(criteria);

        //Assert 단언
        assertTrue(matches);
    }
}