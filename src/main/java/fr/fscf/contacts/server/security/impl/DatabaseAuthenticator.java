package fr.fscf.contacts.server.security.impl;

import com.google.inject.Inject;
import fr.fscf.contacts.server.dao.AuthenticationDAO;
import fr.fscf.contacts.server.dao.UserDAO;
import fr.fscf.contacts.server.model.Authentication;
import fr.fscf.contacts.server.model.User;
import fr.fscf.contacts.server.security.Authenticator;
import fr.fscf.contacts.shared.security.AuthenticationException;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Authenticator service database implementation.
 *
 * @author Denis
 */
public class DatabaseAuthenticator implements Authenticator {

    private static final char[] letters = {
            'a',
            'b',
            'c',
            'd',
            'e',
            'f',
            'g',
            'h',
            'i',
            'j',
            'k',
            'l',
            'm',
            'n',
            'p',
            'q',
            'r',
            's',
            't',
            'u',
            'v',
            'w',
            'x',
            'y',
            'z'
    };

    private static final char[] caps = {
            'A',
            'B',
            'C',
            'D',
            'E',
            'F',
            'G',
            'H',
            'I',
            'J',
            'K',
            'L',
            'M',
            'N',
            'P',
            'Q',
            'R',
            'S',
            'T',
            'U',
            'V',
            'W',
            'X',
            'Y',
            'Z'
    };

    private static final char[] numbers = {
            '1',
            '2',
            '3',
            '4',
            '5',
            '6',
            '7',
            '8',
            '9'
    };

    private static final char[] symbols = {
            '$',
            '+',
            '-',
            '=',
            '_',
            '!',
            '%',
            '@'
    };

    private static final char[][] alphabets = {
            letters,
            caps,
            numbers,
            symbols
    };

    /**
     * Injected {@link UserDAO}.
     */
    private final UserDAO userDAO;

    /**
     * Injected {@link AuthenticationDAO}.
     */
    private final AuthenticationDAO authenticationDAO;

    @Inject
    public DatabaseAuthenticator(final UserDAO userDAO, final AuthenticationDAO authenticationDAO) {
        this.userDAO = userDAO;
        this.authenticationDAO = authenticationDAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Authentication authenticate(final String login, final String password) throws AuthenticationException {

        final User user = userDAO.findByLogin(login);

        if (user == null) {
            throw new AuthenticationException();
        }

        if (BooleanUtils.isNotTrue(user.getActive())) {
            throw new AuthenticationException();
        }

        if (StringUtils.isBlank(user.getPassword()) || !BCrypt.checkpw(password, user.getPassword())) {
            throw new AuthenticationException();
        }

        return authenticationDAO.persist(new Authentication(user), user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String hashPassword(final String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String generatePassword() {

        final StringBuilder password = new StringBuilder();

        final int[] remainings = new int[]{
                4,
                2,
                1,
                1
        };
        int size = 8;

        while (size > 0) {
            int nextChar = -1;
            while (nextChar == -1) {
                int alphabet = (int) (Math.random() * remainings.length);
                if (remainings[alphabet] > 0) {
                    nextChar = alphabets[alphabet][(int) (Math.random() * alphabets[alphabet].length)];
                    remainings[alphabet]--;
                }
            }
            password.append((char) nextChar);

            size--;
        }

        return password.toString();
    }

}