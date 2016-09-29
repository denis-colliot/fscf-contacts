package fr.fscf.contacts.server.dao;

import fr.fscf.contacts.server.config.PersistenceTestModule;
import fr.fscf.contacts.server.dao.base.EntityManagerProvider;
import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.jukito.TestScope;
import org.jukito.UseModules;
import org.junit.runner.RunWith;

/**
 * Abstract DAO test class initializing {@code Injector}.
 *
 * @author Denis
 */
@RunWith(JukitoRunner.class)
@UseModules({PersistenceTestModule.class, AbstractDAOTest.A.class})
public abstract class AbstractDAOTest extends EntityManagerProvider {

    public static class A extends JukitoModule {
        @Override
        protected void configureTest() {
            bind(UserDAO.class).in(TestScope.SINGLETON);
        }
    }

}