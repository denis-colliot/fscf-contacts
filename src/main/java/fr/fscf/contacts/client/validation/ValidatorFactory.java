package fr.fscf.contacts.client.validation;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;
import fr.fscf.contacts.shared.dto.AssociationDTO;
import fr.fscf.contacts.shared.dto.LoginDTO;

import javax.validation.Validator;

public final class ValidatorFactory extends AbstractGwtValidatorFactory {

    /**
     * Validator marker for the Validation Sample project. Only the classes and groups listed
     * in the {@link GwtValidation} annotation can be validated.
     */
    @GwtValidation(value = {
            LoginDTO.class,
            AssociationDTO.class
    })
    public interface GwtValidator extends Validator {
    }

    @Override
    public AbstractGwtValidator createValidator() {
        return GWT.create(GwtValidator.class);
    }
}