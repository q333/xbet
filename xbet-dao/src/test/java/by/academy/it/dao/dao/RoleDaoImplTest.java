package by.academy.it.dao.dao;

import by.academy.it.dao.RoleDao;
import by.academy.it.dao.factory.DaoFactory;
import by.academy.it.entity.Role;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoleDaoImplTest {

    private RoleDao roleDao = DaoFactory.getInstance().getRoleDao();

    @Test
    public void create() throws Exception {
        Role role = new Role();
        role.setId(3);
        role.setRole("tester");
        roleDao.create(role);
        Role role1 = roleDao.findById(3);
        assertEquals("tester", role1.getRole());
    }

    @Test
    public void findById() throws Exception {
        Role role = roleDao.findById(2);
        assertNotNull(role);
    }

    @Test
    public void delete() throws Exception {
        Role role = new Role();
        role.setId(3);
        role.setRole("tester");
        roleDao.delete(role);
    }

}