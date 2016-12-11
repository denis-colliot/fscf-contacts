package fr.fscf.contacts.client.ui.view;

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
import fr.fscf.contacts.client.i18n.I18N;
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

    /**
     * Cell style names.
     */
    private static final String CELL_STYLE_NAMES = "cell-v-centered";

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
        nameCol.setCellStyleNames(CELL_STYLE_NAMES);
        cellTable.addColumn(nameCol, I18N.CONSTANTS.contacts_column_name());

        final TextColumn<ContactDTO> emailCol = new TextColumn<ContactDTO>() {

            @Override
            public String getValue(final ContactDTO contact) {
                return contact.getEmail();
            }
        };
        emailCol.setCellStyleNames(CELL_STYLE_NAMES);
        cellTable.addColumn(emailCol, I18N.CONSTANTS.contacts_column_email());

        final TextColumn<ContactDTO> phoneCol = new TextColumn<ContactDTO>() {

            @Override
            public String getValue(final ContactDTO contact) {
                return contact.getPhone();
            }
        };
        phoneCol.setCellStyleNames(CELL_STYLE_NAMES);
        cellTable.addColumn(phoneCol, I18N.CONSTANTS.contacts_column_phone());

        final TextColumn<ContactDTO> cityCol = new TextColumn<ContactDTO>() {

            @Override
            public String getValue(final ContactDTO contact) {
                return contact.getCity();
            }
        };
        cityCol.setCellStyleNames(CELL_STYLE_NAMES);
        cellTable.addColumn(cityCol, I18N.CONSTANTS.contacts_column_city());

        final TextColumn<ContactDTO> zipCodeCol = new TextColumn<ContactDTO>() {

            @Override
            public String getValue(final ContactDTO contact) {
                return contact.getZipCode();
            }
        };
        zipCodeCol.setCellStyleNames(CELL_STYLE_NAMES);
        cellTable.addColumn(zipCodeCol, I18N.CONSTANTS.contacts_column_zipCode());
        cellTable.setColumnWidth(zipCodeCol, 150, Style.Unit.PX);

        final Column<ContactDTO, String> editCol = new Column<ContactDTO, String>(new ButtonCell(
                IconType.PENCIL, ButtonType.PRIMARY, ButtonSize.SMALL)) {
            @Override
            public String getValue(ContactDTO contact) {
                return I18N.CONSTANTS.contacts_buttons_edit();
            }
        };
        editCol.setFieldUpdater((index, contact, value) ->
                eventBus.navigateRequest(Page.CONTACT.requestWith(RequestParameter.ID, contact.getId())));
        editCol.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        editCol.setCellStyleNames(CELL_STYLE_NAMES);
        cellTable.addColumn(editCol, I18N.CONSTANTS.contacts_column_actions());
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