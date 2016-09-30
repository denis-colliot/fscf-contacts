package fr.fscf.contacts.server.dao;

import fr.fscf.contacts.server.config.GuiceJUnitRunner;
import fr.fscf.contacts.server.config.PersistenceTestModule;
import fr.fscf.contacts.server.config.UseModules;
import fr.fscf.contacts.server.dao.base.EntityManagerProvider;
import org.junit.runner.RunWith;

/**
 * Abstract DAO test class initializing {@code Injector}.
 *
 * @author Denis
 */
@RunWith(GuiceJUnitRunner.class)
@UseModules(PersistenceTestModule.class)
public abstract class AbstractDAOTest extends EntityManagerProvider {

}