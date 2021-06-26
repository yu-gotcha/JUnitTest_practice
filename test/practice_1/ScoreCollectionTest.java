package practice_1;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class ScoreCollectionTest {

    @Test
    public void answersArithmeticMeanOfTwoNumbers() {
        //준비
        ScoreCollection collection = new ScoreCollection();
        collection.add(()->5);
        collection.add(()->7);

        //실행
        int actualResult = collection.arithmeticMean();

        //단언
        MatcherAssert.assertThat(actualResult, equalTo(6));
    }
}