package main.najah.test;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import static org.junit.jupiter.api.Assertions.assertTrue;


@Suite
@SelectClasses({
        CalculatorTest.class,
        ProductTest.class,
        UserServiceSimpleTest.class,
        TestRecipeBook.class
})

public class allTestSuite {
    @Test
    void dummyTest() {
        assertTrue(true);
    }
}
