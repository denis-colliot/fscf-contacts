package fr.fscf.contacts.server.config;

import com.google.inject.persist.PersistFilter;
import fr.fscf.contacts.client.security.SecureDispatchService;
import fr.fscf.contacts.server.config.init.InitializationFilter;
import fr.fscf.contacts.server.dispatch.SecureDispatchServlet;
import fr.fscf.contacts.server.security.AuthenticationFilter;
import fr.fscf.contacts.server.servlet.filter.CacheFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Module to serves the servlets.
 *
 * @author Denis
 */
public class ServletModule extends com.google.inject.servlet.ServletModule {

    /**
     * Servlet remote service endpoint.
     */
    public static final String ENDPOINT = "/fscf/";
    /**
     * Log.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ServletModule.class);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configureServlets() {

        LOG.info("Installing servlets module.");

        // Filters.
        filter("/*").through(PersistFilter.class);
        filter("/*").through(InitializationFilter.class);
        filter("/*").through(AuthenticationFilter.class);
        filter("/*").through(CacheFilter.class);

        // Servlets.
        serve(ENDPOINT + SecureDispatchService.REMOTE_SERVICE_RELATIVE_PATH).with(SecureDispatchServlet.class);
//		serve("/").with(SigmahHostController.class);
//		serve("/healthcheck").with(HealthCheckServlet.class);
//		serve(ENDPOINT + Servlet.FILE.getPathName()).with(FileServlet.class);
    }
}