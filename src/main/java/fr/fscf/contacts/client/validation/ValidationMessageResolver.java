package fr.fscf.contacts.client.validation;

import com.google.gwt.validation.client.AbstractValidationMessageResolver;
import com.google.gwt.validation.client.UserValidationMessagesResolver;
import fr.fscf.contacts.client.i18n.I18N;

/**
 * Validation message resolver custom implementation.
 *
 * @author Denis
 */
public class ValidationMessageResolver extends AbstractValidationMessageResolver implements UserValidationMessagesResolver {

    protected ValidationMessageResolver() {
        super(I18N.VALIDATION);
    }

}
