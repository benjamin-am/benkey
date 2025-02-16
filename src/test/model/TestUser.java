package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestUser {
    private Account a1;
    private Account a2;
    private Account a3;
    private Account a4;
    private Account a5;
    private Website w1;
    private Website w2;
    private User u1;
    private List<Website> listWebsite;
    private List<String> listUser;

    @BeforeEach
    void runBefore() {
        w1 = new Website("test", "test.com");
        w2 = new Website("test1", "test.com1");
        a1 = new Account(w2, "a", "a");
        a2 = new Account(w1, "c", "d");
        a3 = new Account(w1, "f", "g");
        a4 = new Account(w1, "f", "ga2");
        a5 = new Account(w2, "fa", "ga2");
        u1 = new User("ben", "test");
    }

    @Test
    void testConstructor() {
        assertEquals("ben", u1.getUsername());
        assertEquals("test", u1.getPassword().getPassword());
    }

    @Test 
    void testSetUsername() {
        assertEquals("ben", u1.getUsername());
        u1.setUsername("not ben");
        assertEquals("not ben", u1.getUsername());
    }

    @Test 
    void testSetPassword() {
        assertEquals("test", u1.getPassword().getPassword());
        u1.setPassword("not been");
        assertEquals("not been", u1.getPassword().getPassword());
    }

    @Test 
    void testSetAccountList() {
        assertEquals(0, u1.getListOfAccounts().size());
        u1.addAccount(a1);
        assertEquals(1, u1.getListOfAccounts().size());
        assertEquals(a1, u1.getListOfAccounts().get(0));
        List<Account> accounts = new ArrayList<>();
        accounts.add(a2);
        accounts.add(a3);
        accounts.add(a4);
        accounts.add(a5);
        u1.setAccountList(accounts);
        assertEquals(4, u1.getListOfAccounts().size());
        assertEquals(a2, u1.getListOfAccounts().get(0));
        assertEquals(a3, u1.getListOfAccounts().get(1));
        assertEquals(a4, u1.getListOfAccounts().get(2));
        assertEquals(a5, u1.getListOfAccounts().get(3));
    }

    
    @Test
    void testGetListOfAccountsAndAddAccount() {
        assertTrue(u1.getListOfAccounts().isEmpty());
        u1.addAccount(a1);
        assertEquals(1, u1.getListOfAccounts().size());
        assertFalse(u1.getListOfAccounts().isEmpty());
        u1.addAccount(a2);
        u1.addAccount(a3);
        assertEquals(3, u1.getListOfAccounts().size());
        assertFalse(u1.getListOfAccounts().isEmpty());
        assertEquals(a1, u1.getListOfAccounts().get(0));
        assertEquals(a2, u1.getListOfAccounts().get(1));
        assertEquals(a3, u1.getListOfAccounts().get(2));
    }

    @Test
    void testRemoveAccount() {
        u1.addAccount(a1);
        assertEquals(1, u1.getListOfAccounts().size());
        assertFalse(u1.getListOfAccounts().isEmpty());
        u1.removeAccount(a1);
        assertEquals(0, u1.getListOfAccounts().size());
        assertTrue(u1.getListOfAccounts().isEmpty());

        u1.addAccount(a1);
        u1.addAccount(a2);
        u1.addAccount(a3);
        assertEquals(3, u1.getListOfAccounts().size());
        assertFalse(u1.getListOfAccounts().isEmpty());
        u1.removeAccount(a3);
        assertEquals(2, u1.getListOfAccounts().size());
        assertFalse(u1.getListOfAccounts().isEmpty());
    }

    @Test
    void testTotalAccounts() {
        assertEquals(0, u1.totalAccounts());
        u1.addAccount(a1);
        assertEquals(1, u1.totalAccounts());
        u1.addAccount(a2);
        u1.addAccount(a3);
        assertEquals(3, u1.totalAccounts());
    }

    @Test
    void testTotalWebsites() {
        assertEquals(0, u1.totalWebsites());
        u1.addAccount(a1);
        assertEquals(1, u1.totalWebsites());
        u1.addAccount(a2);
        assertEquals(2, u1.totalWebsites());
        u1.addAccount(a3);
        assertEquals(2, u1.totalWebsites());
    }

    @Test
    void testListAllWebsites() {
        u1.addAccount(a1);
        listWebsite = u1.listAllWebsites();
        assertEquals(1, listWebsite.size());
        assertFalse(listWebsite.isEmpty());
        u1.addAccount(a2);
        u1.addAccount(a3);
        listWebsite = u1.listAllWebsites();
        assertEquals(2, listWebsite.size());
        assertFalse(listWebsite.isEmpty());
        assertEquals(w2, listWebsite.get(0));
        assertEquals(w1, listWebsite.get(1));
    }

    @Test
    void testListAllUsernames() {
        u1.addAccount(a1);
        listUser = u1.listAllUsernames();
        assertEquals(1, listUser.size());
        assertEquals("a", listUser.get(0));
        u1.addAccount(a2);
        listUser = u1.listAllUsernames();
        assertEquals(2, listUser.size());
        assertEquals("a", listUser.get(0));
        assertEquals("c", listUser.get(1));

        u1.addAccount(a3);
        listUser = u1.listAllUsernames();
        assertEquals(3, listUser.size());
        assertEquals("a", listUser.get(0));
        assertEquals("c", listUser.get(1));
        assertEquals("f", listUser.get(2));

        u1.addAccount(a4);
        listUser = u1.listAllUsernames();
        assertEquals(3, listUser.size());
        assertEquals("a", listUser.get(0));
        assertEquals("c", listUser.get(1));
        assertEquals("f", listUser.get(2));
    }

    @Test 
    void testVerifyPassword() {
        assertFalse(u1.verifyPassword("AHHH"));
        assertTrue(u1.verifyPassword("test"));
        assertFalse(u1.verifyPassword("Test"));
    }

    @Test
    void testNumberOfAccountsOnWebsite() {
        u1.addAccount(a1);
        assertEquals(0, u1.numberOfAccountsOnWebsite(w1));
        assertEquals(1, u1.numberOfAccountsOnWebsite(w2));
        u1.addAccount(a2);
        assertEquals(1, u1.numberOfAccountsOnWebsite(w1));
        assertEquals(1, u1.numberOfAccountsOnWebsite(w2));
        u1.addAccount(a3);
        assertEquals(2, u1.numberOfAccountsOnWebsite(w1));
        assertEquals(1, u1.numberOfAccountsOnWebsite(w2));
        u1.addAccount(a4);
        assertEquals(3, u1.numberOfAccountsOnWebsite(w1));
        assertEquals(1, u1.numberOfAccountsOnWebsite(w2));
    }

    @Test
    void testFindAccountWebsiteAccountName() {
        u1.addAccount(a1);
        u1.addAccount(a2);
        u1.addAccount(a3);
        Account test = u1.findAccountWebsiteAccountName("a", "test1");
        assertEquals(a1, test);
        test = u1.findAccountWebsiteAccountName("no", "fail");
        assertNull(test);
    }

    @Test
    void testListAllPasswords() {
        List<String> test = u1.listAllPasswords();
        assertEquals(0, test.size());
        assertTrue(test.isEmpty());
        u1.addAccount(a1);
        u1.addAccount(a2);
        u1.addAccount(a3);
        u1.addAccount(a4);
        test = u1.listAllPasswords();
        assertEquals(4, test.size());
        u1.addAccount(a5);
        test = u1.listAllPasswords();
        assertEquals(4, test.size());
        assertEquals(a1.getPassword().getPassword(), test.get(0));
        assertEquals(a2.getPassword().getPassword(), test.get(1));
        assertEquals(a3.getPassword().getPassword(), test.get(2));
        assertEquals(a5.getPassword().getPassword(), test.get(3));
    }

}
