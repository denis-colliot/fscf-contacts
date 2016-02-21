package fr.fscf.contacts.server.security;

import fr.fscf.contacts.server.dao.AbstractDAOTest;
import org.junit.Test;

import javax.inject.Inject;

/**
 * Created by Denis on 25/04/15.
 */
public class PasswordHashTest extends AbstractDAOTest {

    @Inject
    private Authenticator authenticator;

    @Test
    public void find() {
        System.out.println(authenticator.hashPassword("password"));
        System.out.println(authenticator.hashPassword("password"));
        System.out.println(authenticator.hashPassword("password"));
    }

}
