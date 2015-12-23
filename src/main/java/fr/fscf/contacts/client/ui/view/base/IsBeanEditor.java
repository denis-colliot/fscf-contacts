package fr.fscf.contacts.client.ui.view.base;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;

/**
 * Created on 23/12/15.
 *
 * @author Denis
 */
public interface IsBeanEditor<T> extends Editor<T> {

    SimpleBeanEditorDriver<T, ?> getDriver();

}
