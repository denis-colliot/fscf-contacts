package fr.fscf.contacts.server.inject;

import com.google.inject.AbstractModule;
import fr.fscf.contacts.server.mapper.BeanMapper;
import fr.fscf.contacts.server.mapper.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

/**
 * Module de configuration du service de mapping de bean.
 *
 * @author Denis
 */
class MapperModule extends AbstractModule {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MapperModule.class);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure() {

        LOGGER.info("Bean mapping module initialization.");

        final org.dozer.DozerBeanMapper mapper = new org.dozer.DozerBeanMapper();
        //mapper.setMappingFiles(Collections.singletonList("dozer.xml"));

        bind(BeanMapper.class).to(DozerBeanMapper.class).in(Singleton.class);
        bind(org.dozer.Mapper.class).toInstance(mapper);
    }

}
