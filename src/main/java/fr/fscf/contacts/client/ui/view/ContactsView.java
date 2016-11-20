package fr.fscf.contacts.client.ui.view;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;
import fr.fscf.contacts.client.ui.presenter.ContactsPresenter;
import fr.fscf.contacts.client.ui.view.base.AbstractView;
import fr.fscf.contacts.shared.dto.ContactDTO;
import org.gwtbootstrap3.client.ui.Pagination;
import org.gwtbootstrap3.client.ui.constants.Alignment;
import org.gwtbootstrap3.client.ui.constants.ButtonSize;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

/**
 * Contacts view.
 *
 * @author Denis
 */
@Singleton
public class ContactsView extends AbstractView implements ContactsPresenter.View {

    /**
     * {@link UiBinder} interface adapted to {@link ContactsView}.
     */
    @UiTemplate("ContactsView.ui.xml")
    interface ViewUiBinder extends UiBinder<Widget, ContactsView> {
    }

    @UiField
    protected CellTable<ContactDTO> cellTable;

    @UiField
    protected Pagination pagination;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {
        final ViewUiBinder binder = GWT.create(ViewUiBinder.class);
        initWidget(binder.createAndBindUi(this));

        initTable();
    }

    private void initTable() {
        final TextColumn<ContactDTO> nameCol = new TextColumn<ContactDTO>() {

            @Override
            public String getValue(final ContactDTO contact) {
                return contact.getName();
            }
        };
        cellTable.addColumn(nameCol, "Nom"); // TODO i18n

        final TextColumn<ContactDTO> firstNameCol = new TextColumn<ContactDTO>() {

            @Override
            public String getValue(final ContactDTO contact) {
                return contact.getFirstName();
            }
        };
        cellTable.addColumn(firstNameCol, "Prénom"); // TODO i18n

        final TextColumn<ContactDTO> emailCol = new TextColumn<ContactDTO>() {

            @Override
            public String getValue(final ContactDTO contact) {
                return contact.getEmail();
            }
        };
        cellTable.addColumn(emailCol, "Email"); // TODO i18n

        final Column<ContactDTO, String> editCol = new Column<ContactDTO, String>(new ButtonCell(
                IconType.PENCIL, ButtonType.PRIMARY, ButtonSize.SMALL)) {
            @Override
            public String getValue(ContactDTO contact) {
                return "Modifier";
            }
        };
        editCol.setFieldUpdater(new FieldUpdater<ContactDTO, String>() {
            @Override
            public void update(int index, ContactDTO contact, String value) {
                Window.alert("TODO: clicked on contact #" + contact.getId());
            }
        });
        editCol.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        cellTable.addColumn(editCol, "Actions");
        cellTable.setColumnWidth(editCol, 120, Style.Unit.PX);
    }

    @Override
    public CellTable<ContactDTO> getContactsTable() {
        return cellTable;
    }

    @Override
    public Pagination getPagination() {
        return pagination;
    }
}