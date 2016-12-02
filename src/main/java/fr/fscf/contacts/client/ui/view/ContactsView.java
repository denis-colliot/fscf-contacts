package fr.fscf.contacts.client.ui.view;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;
import fr.fscf.contacts.client.event.bus.EventBus;
import fr.fscf.contacts.client.navigation.Page;
import fr.fscf.contacts.client.navigation.RequestParameter;
import fr.fscf.contacts.client.ui.presenter.ContactsPresenter;
import fr.fscf.contacts.client.ui.view.base.AbstractView;
import fr.fscf.contacts.client.ui.widget.Pagination;
import fr.fscf.contacts.shared.dto.ContactDTO;
import org.gwtbootstrap3.client.ui.constants.ButtonSize;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

import javax.inject.Inject;

/**
 * Contacts view.
 *
 * @author Denis
 */
@Singleton
public class ContactsView extends AbstractView implements ContactsPresenter.View {

    @UiField
    protected CellTable<ContactDTO> cellTable;

    @UiField
    protected Pagination pagination;

    @Inject
    private EventBus eventBus;

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
                return contact.toString();
            }
        };
        nameCol.setCellStyleNames("cell-v-centered");
        cellTable.addColumn(nameCol, "Nom"); // TODO i18n

        final TextColumn<ContactDTO> emailCol = new TextColumn<ContactDTO>() {

            @Override
            public String getValue(final ContactDTO contact) {
                return contact.getEmail();
            }
        };
        emailCol.setCellStyleNames("cell-v-centered");
        cellTable.addColumn(emailCol, "Email"); // TODO i18n

        final TextColumn<ContactDTO> phoneCol = new TextColumn<ContactDTO>() {

            @Override
            public String getValue(final ContactDTO contact) {
                return contact.getPhone();
            }
        };
        phoneCol.setCellStyleNames("cell-v-centered");
        cellTable.addColumn(phoneCol, "Téléphone"); // TODO i18n

        final TextColumn<ContactDTO> cityCol = new TextColumn<ContactDTO>() {

            @Override
            public String getValue(final ContactDTO contact) {
                return contact.getCity();
            }
        };
        cityCol.setCellStyleNames("cell-v-centered");
        cellTable.addColumn(cityCol, "Ville"); // TODO i18n

        final TextColumn<ContactDTO> zipCodeCol = new TextColumn<ContactDTO>() {

            @Override
            public String getValue(final ContactDTO contact) {
                return contact.getZipCode();
            }
        };
        zipCodeCol.setCellStyleNames("cell-v-centered");
        cellTable.addColumn(zipCodeCol, "Code Postal"); // TODO i18n

        final Column<ContactDTO, String> editCol = new Column<ContactDTO, String>(new ButtonCell(
                IconType.PENCIL, ButtonType.PRIMARY, ButtonSize.SMALL)) {
            @Override
            public String getValue(ContactDTO contact) {
                return "Modifier";
            } // TODO i18n
        };
        editCol.setFieldUpdater(new FieldUpdater<ContactDTO, String>() {
            @Override
            public void update(int index, ContactDTO contact, String value) {
                eventBus.navigateRequest(Page.CONTACT.requestWith(RequestParameter.ID, contact.getId()));
            }
        });
        editCol.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        editCol.setCellStyleNames("cell-v-centered");
        cellTable.addColumn(editCol, "Actions"); // TODO i18n
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

    /**
     * {@link UiBinder} interface adapted to {@link ContactsView}.
     */
    @UiTemplate("ContactsView.ui.xml")
    interface ViewUiBinder extends UiBinder<Widget, ContactsView> {
    }
}