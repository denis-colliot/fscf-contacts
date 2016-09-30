package fr.fscf.contacts.server.config;

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
    protected void configure() {
        super.configure();

        bind(TestDatabaseInitialization.class).asEagerSingleton();
    }

}
