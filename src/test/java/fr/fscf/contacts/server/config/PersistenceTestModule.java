package fr.fscf.contacts.server.config;

import com.google.inject.persist.PersistService;
import fr.fscf.contacts.server.dao.UserDAO;
import fr.fscf.contacts.server.model.User;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created on 26/08/15.
 *
 * @author Denis
 */
public class PersistenceTestModule extends PersistenceModule {

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPersistencePropertiesFile() {
        return "persistence-test.properties";
    }

    @Override
    protected void onJpaModuleInstalled() {
        bind(TestDatabaseInitialization.class).asEagerSingleton();
    }

    @Singleton
    private static class TestDatabaseInitialization {

        @Inject
        public TestDatabaseInitialization(final PersistService service, final UserDAO userDAO) {

            service.start(); // CRUCIAL.

            final User johnDoe = new User();
            johnDoe.setName("Doe");
            johnDoe.setFirstName("John");
            johnDoe.setEmail("j.doe@email.com");
            johnDoe.setPassword("p4ssw0rd");
            johnDoe.setActive(true);

            userDAO.persist(johnDoe, null);
        }

    }
}
