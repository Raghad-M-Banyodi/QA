package main.najah.test;

import main.najah.code.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Calculator Tests")
@TestMethodOrder(OrderAnnotation.class)
@Execution(ExecutionMode.CONCURRENT)
public class CalculatorTest {

    Calculator calc;
    @BeforeAll
    static void beforeAll() {
        System.out.println("Before All");
    }
    @AfterAll
    static void afterAll() {
        System.out.println("After All Tests");
    }


	@BeforeEach
	void setUp()  {
        calc = new Calculator();
        System.out.println("setUp before test");

	}
    @AfterEach
    void tearDown() {
        System.out.println("test finished");
    }
    @Test
    @Order(1)
    @DisplayName("test add with valid numbers")
     void testAdd(){
        int result=calc.add(1,2,3);
        assertEquals(6,result);
        assertNotEquals(5,result);
    }
    @ParameterizedTest
    @CsvSource({
            "2,3,5",
            "6,1,7",
            "10,0,10"

    })
    @DisplayName("Parameterized add test")
    void testAddParameterized(int a,int b,int expected){
        assertEquals(expected,calc.add(a,b));
    }

    @Test
    @Order(2)
    @DisplayName("Test divide valid")
    void TestDivideValid(){
        assertEquals(10,calc.divide(20,2));
    }
    @Test
    @DisplayName("Test divide by zero")
    void testDivideByZero(){
        assertThrows(ArithmeticException.class, () -> calc.divide(10,0));
    }
    @Test
    @Order(3)
    @DisplayName("Test factorial valid")
    void testFactorial(){
        assertEquals(120,calc.factorial(5));
    }
    @Test
    @DisplayName("Test factorial negative")
    void testFactorialNegative(){
        assertThrows(IllegalArgumentException.class, () -> calc.factorial(-1));

    }
    @Test
    @Timeout(2)
    @DisplayName("timeout test")
    void testTimeout(){
        calc.add(1,2);
    }
    @Test
    @Disabled("Fails intentionally - fix by correcting expected value")
    @DisplayName("Intentional failing test")
    void failingTest() {
        assertEquals(10, calc.add(2, 2));
    }

}
