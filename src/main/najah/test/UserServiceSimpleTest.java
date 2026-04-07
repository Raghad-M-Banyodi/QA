package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import main.najah.code.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("UserService Tests")
class UserServiceSimpleTest {
UserService userService ;
	@BeforeEach
	void setUp()  {
        userService = new UserService();
        System.out.println("setUp userService");
	}
    @Test
    @DisplayName("valid email test")
    void validEmailTest()
    {
        assertAll("Email validation",
                ()->assertTrue(userService.isValidEmail("test@example.com")),
                ()->assertFalse(userService.isValidEmail("invalidEmail")) );
    }
    @ParameterizedTest
    @CsvSource({
            "admin,1234,true",
            "admin,wrong,false",
            "user,1234,false"
    })
    @DisplayName("Parameterized authenticate test")
    void testAuthenticate(String username, String password, boolean expected) {
        assertEquals(expected, userService.authenticate(username, password));
    }
    @Test
    @Timeout(1)
    @DisplayName("Timeout test")
    void testTimeout()
    {
        assertTrue(userService.isValidEmail("timeout@test.com"));
    }
    @Test
    @Disabled("Fails intentionally - fix authentication logic")
    @DisplayName("Intentional failing test")
    void testIntentionalFail()
    {
        assertTrue(userService.authenticate("admin","wrongPassword"));
    }





}
