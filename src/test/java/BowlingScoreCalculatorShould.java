import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class BowlingScoreCalculatorShould {

    private BowlingScoreCalculator bowlingScoreCalculator = new BowlingScoreCalculator();

    public static Object[][] provideParameters() {
        return new Object[][]{
                new Object[]{"--|--|--|--|--|--|--|--|--|--||", 0},
                new Object[]{"36|--|--|--|--|--|--|--|--|--||", 9},
                new Object[]{"36|-6|12|53|27|--|3-|22|11|51||", 50},
                new Object[]{"3/|--|--|--|--|--|--|--|--|--||", 10},
                new Object[]{"3/|1-|--|--|--|--|--|--|--|--||", 12},
                new Object[]{"X|--|--|--|--|--|--|--|--|--||", 10},
                new Object[]{"X|1-|--|--|--|--|--|--|--|--||", 12},
                new Object[]{"X|14|--|--|--|--|--|--|--|--||", 20},
                new Object[]{"X|1/|--|--|--|--|--|--|--|--||", 30},
                new Object[]{"3/|X|--|--|--|--|--|--|--|--||", 30},
                new Object[]{"3/|X|33|--|--|--|--|--|--|--||", 42},
                new Object[]{"3/|X|3/|--|--|--|--|--|--|--||", 50},
                new Object[]{"X|X|--|--|--|--|--|--|--|--||", 30},
                new Object[]{"X|X|3-|--|--|--|--|--|--|--||", 39},
                new Object[]{"X|X|36|--|--|--|--|--|--|--||", 51},
                new Object[]{"X|X|X|--|--|--|--|--|--|--||", 60},
                new Object[]{"X|X|X|X|--|--|--|--|--|--||", 90},
                new Object[]{"X|5/|-1|1/|X|X|-3|13|4/|44||", 113},
                new Object[]{"--|--|--|--|--|--|--|--|--|6/||2", 12},
                new Object[]{"--|--|--|--|--|--|--|--|--|6/||X", 20},
                new Object[]{"--|--|--|--|--|--|--|--|--|X||12", 13}
        };
    }

    @Test
    @Parameters(method = "provideParameters")
    public void calculate_score(String game, int score) {
        assertThat(bowlingScoreCalculator.calculateScore(game), is(score));
    }
}
