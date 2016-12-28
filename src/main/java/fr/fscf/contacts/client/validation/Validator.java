package fr.fscf.contacts.client.validation;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.validation.client.impl.Validation;
import fr.fscf.contacts.client.i18n.I18N;
import fr.fscf.contacts.client.ui.notification.N10N;
import fr.fscf.contacts.client.ui.view.base.IsBeanEditor;
import fr.fscf.contacts.shared.util.ClientUtils;

import javax.validation.ConstraintViolation;
import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.HashSet;
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
     * @param beanEditor The bean editor.
     * @param groups     The additional validation group(s) ({@code null} values are ignored).
     *                   Note that {@link Default} group is automatically included.
     * @return {@code true} if the bean is valid, {@code false} otherwise.
     */
    @SuppressWarnings("unchecked")
    public boolean validate(final IsBeanEditor beanEditor, final Class<?>... groups) {

        final SimpleBeanEditorDriver driver = beanEditor.getDriver();
        final Object bean = driver.flush();

        final Set<Class<?>> groupSet = new HashSet<>();
        for (final Class<?> group : groups) {
            if (group != null) {
                groupSet.add(group);
            }
        }
        groupSet.add(Default.class);
        final Set<ConstraintViolation<Object>> violations = validator.validate(bean,
                groupSet.toArray(new Class<?>[groupSet.size()]));

        if (ClientUtils.isNotEmpty(violations)) {
            driver.setConstraintViolations(new ArrayList<>(violations));
            return false;

        } else if (driver.hasErrors()) {
            N10N.errorNotif(I18N.VALIDATION.global_validation_hasError());
            return false;

        } else {
            return true;
        }
    }

}
