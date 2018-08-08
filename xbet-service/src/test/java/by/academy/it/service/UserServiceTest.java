package by.academy.it.service;

import by.academy.it.entity.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    private UserService userService = UserService.getInstance();

    @Test
    public void createUser() throws Exception {
        User user = new User();
        user.setLogin("test");
        user.setPassword("test");
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmail("test");
        user.setBalance(111);
        user.setRole(2);
        userService.createUser(user);
        assertTrue(userService.isExistingLogin("test"));
        userService.deleteUser(user);
    }

    @Test
    public void updateUser() throws Exception {
        User user = userService.updateUserBalance("user", 100);
        assertNotNull(user);
    }

    @Test
    public void isExistingLogin() throws Exception {
        assertTrue(userService.isExistingLogin("admin"));
    }

    @Test
    public void isPasswordCorrectForLogin() throws Exception {
        assertTrue(userService.isPasswordCorrectForLogin("admin", "Password1"));
    }

    @Test
    public void findUser() throws Exception {
        assertEquals("admin", userService.findUserByLogin("admin").getLogin());
    }

}