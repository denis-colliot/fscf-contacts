package fr.fscf.contacts.server.config.init;

import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.*;
import java.io.IOException;

/**
 * Filter only used to trigger application initialization on startup.<br/>
 * This filter should be set after {@code PersistFilter}.
 */
@Singleton
public final class InitializationFilter implements Filter {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(InitializationFilter.class);

    @Inject
    private DatabaseInitialization databaseInitialization;

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("Application initialization.");
        databaseInitialization.init();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        // Nothing to do here.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain)
            throws IOException, ServletException {
        // Nothing to do here.
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
