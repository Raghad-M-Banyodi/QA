package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import main.najah.code.Product;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Duration;

@DisplayName("Product class Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductTest {
    Product p;
    @BeforeAll
    static void beforeAll() {
        System.out.println("Starting product test");
    }
    @AfterAll
    static void afterAll() {
        System.out.println("Ending product test");
    }



	@BeforeEach
	void setUp() {
        p=new Product("Laptop",1000);
        System.out.println("setUp complete");

	}
    @AfterEach
    void tearDown() {
        System.out.println("test finished");
    }
    @Test
    @Order(1)
    @DisplayName("Test product  creation with valid price")
    void testProductCreation() {
        assertAll("product creation",
                ()->assertEquals("Laptop",p.getName()),
                ()->assertEquals(1000,p.getPrice()) );

    }

	@Test
    @Order(2)
    @DisplayName("Test Product Creation with Negative Price Throws Exception")
    void testInvalidProductCreation() {
         Exception exception= assertThrows(IllegalArgumentException.class, ()->{ new Product("phone",-1000);});
        assertEquals("Price must be non-negative", exception.getMessage());
    }
    @ParameterizedTest
    @CsvSource({
            "10,900",
            "25,750",
            "0,1000"

    })
    @DisplayName("Test Apply Discount with valid value")
    void testApplyDiscount(double discount,double expectedFinalPrice) {
        p.applyDiscount(discount);
        assertEquals(expectedFinalPrice,p.getFinalPrice());

    }
    @Test
    @DisplayName("est applyDiscount with Invalid Values Throws Exception")
    void testInvalidApplyDiscount() {
        assertAll(
                ()->assertThrows(IllegalArgumentException.class,()->p.applyDiscount(-5)),
                ()->assertThrows(IllegalArgumentException.class,()->p.applyDiscount(60))

                );
    }
    @Test
    @Timeout(1)
    @DisplayName("Timeout Test for get final price")
    void testGetFinalPrice() {
        assertTimeout(Duration.ofSeconds(1), () -> {p.applyDiscount(20);
        double price=p.getFinalPrice();
        assertEquals(800,price);});
        }

    @Test
    @Disabled("Intentionally failing test. Fix by setting correct discount")
    @DisplayName("Disabled Test")
    void testDisabled() {
        p.applyDiscount(10);
        assertEquals(1000,p.getFinalPrice());
    }
}
