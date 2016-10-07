package fr.fscf.contacts.server.inject;

import com.google.inject.Module;

import java.lang.annotation.*;

/**
 * Annotation used to specify modules to load for JUnit tests.
 *
 * @author Denis
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface UseModules {

    /**
     * Module(s) classe(s) to initialize into injector.
     */
    Class<? extends Module>[] value();

}