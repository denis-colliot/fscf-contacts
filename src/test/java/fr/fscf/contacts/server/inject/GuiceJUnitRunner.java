package fr.fscf.contacts.server.inject;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * Guice JUnit runner class.
 * </p>
 * <p>
 * Use this class as following :
 * </p>
 * <pre>
 * &#64;RunWith(GuiceJUnitRunner.class)
 * &#64;GuiceModules({ ComponentsTestModule.class, ServicesTestModule.class })
 * public class ServiceTest {
 *
 *  &#64;Inject private IService service;
 *
 *  &#64;Test public void testApp() {
 *      Assert.assertEquals("Hello World!", service.doSomething());
 *  }
 * }
 * </pre>
 *
 * @author Denis
 */
public final class GuiceJUnitRunner extends BlockJUnit4ClassRunner {

    /**
     * The test class.
     */
    private final Class<?> testClass;

    /**
     * Guice JUnit runner initialization.
     *
     * @param testClass The test class defining injector modules to initialize.
     * @throws InitializationError If an error occurs during injector creation.
     */
    public GuiceJUnitRunner(final Class<?> testClass) throws InitializationError {
        super(testClass);
        this.testClass = testClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object createTest() throws Exception {
        final Object test = super.createTest();

        // Initialize injector for current test.
        final Injector injector = createInjectorFor(getModulesFor(testClass));
        injector.injectMembers(test);

        return test;
    }

    /**
     * Creates the {@link Injector} instance for the given modules {@code classes}.
     *
     * @param classes The modules classes.
     * @return the created {@link Injector} instance.
     * @throws InitializationError If an error occurs during module(s) instantiation.
     */
    private Injector createInjectorFor(final List<Class<? extends Module>> classes) throws InitializationError {

        final Module[] modules = new Module[classes.size()];
        int index = 0;

        for (final Class<? extends Module> moduleClass : classes) {
            try {

                modules[index++] = moduleClass.newInstance();

            } catch (final InstantiationException | IllegalAccessException e) {
                throw new InitializationError(e);
            }
        }

        return Guice.createInjector(modules);
    }

    /**
     * Returns the modules classes defined in given {@code klass} {@link UseModules} annotation.
     *
     * @param klass The class defining a {@code GuiceModules} annotation.
     * @return The modules classes defined in given {@code klass} {@link UseModules} annotation.
     * @throws InitializationError If the {@code klass} does not define a {@link UseModules} annotation.
     */
    private List<Class<? extends Module>> getModulesFor(final Class<?> klass) throws InitializationError {

        final Optional<UseModules> annotation = Optional.ofNullable(klass.getAnnotation(UseModules.class));

        return annotation
                .map(UseModules::value)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

}