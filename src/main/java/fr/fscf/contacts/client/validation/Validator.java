package fr.fscf.contacts.client.validation;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.validation.client.impl.Validation;
import fr.fscf.contacts.client.ui.notification.N10N;
import fr.fscf.contacts.client.ui.view.base.IsBeanEditor;
import fr.fscf.contacts.shared.util.ClientUtils;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Set;

/**
 * Validator.
 *
 * @author Denis
 */
public final class Validator {

    private final javax.validation.Validator validator;

    public Validator() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * Validates the given {@code beanEditor} corresponding bean.
     *
     * @param beanEditor
     *         The bean editor.
     * @return {@code true} if the bean is valid, {@code false} otherwise.
     */
    @SuppressWarnings("unchecked")
    public boolean validate(final IsBeanEditor beanEditor) {

        final SimpleBeanEditorDriver driver = beanEditor.getDriver();
        final Object bean = driver.flush();

        final Set<ConstraintViolation<Object>> violations = validator.validate(bean);

        if (ClientUtils.isNotEmpty(violations)) {
            driver.setConstraintViolations(new ArrayList<>(violations));
            return false;

        } else if (driver.hasErrors()) {
            N10N.errorNotif("Le formulaire contient des erreurs"); // TODO i18n
            return false;

        } else {
            return true;
        }
    }

}
