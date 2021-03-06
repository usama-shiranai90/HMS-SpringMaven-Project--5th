package org.HMS;


import org.junit.After;
import org.junit.Before;


public class ProxyDesignPatternTest {

    private int userid;
    private String password;

    @Before
    public void setupData() {
        userid = 1003;
        password = "admin123";
    }

/*    @Test
    public void isLoginTrueWithValidCredential() {
        final boolean result;
        DatabaseByPass byPass = new ProxyByPass();
        result = byPass.authentication(userid, password);
        assertEquals("UserID is correct",userid, byPass.getUserID());
        assertEquals("password is correct",password, byPass.getPassword());
        assertTrue(result);

    }*/

    @After
    public void changeSetupData(){
        userid = 11111;
        password="shah101";
    }

/*    @Test
    public void isLoginFailWithWrongCredential(){
        changeSetupData();
        final boolean result;
        DatabaseByPass byPass = new ProxyByPass();
        result = byPass.authentication(userid, password);
        assertEquals(userid, byPass.getUserID());
        assertEquals(password, byPass.getPassword());
        assertFalse(result);
    }*/



}
