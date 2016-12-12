package fr.fscf.contacts.client.ui.view;

import com.google.gwt.cell.client.CompositeCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.HasCell;
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
import fr.fscf.contacts.client.ui.notification.N10N;
import fr.fscf.contacts.client.ui.presenter.ContactsPresenter;
import fr.fscf.contacts.client.ui.view.base.AbstractView;
import fr.fscf.contacts.client.ui.widget.Pagination;
import fr.fscf.contacts.shared.dto.ContactDTO;
import fr.fscf.contacts.shared.dto.sort.IsSortProvider;
import org.gwtbootstrap3.client.ui.constants.ButtonSize;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

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
        // Name column.
        final TextColumn<ContactDTO> nameCol = new TextColumn<ContactDTO>() {

            @Override
            public String getValue(final ContactDTO contact) {
                return contact.toString();
            }
        };
        nameCol.setCellStyleNames(CELL_STYLE_NAMES);
        nameCol.setSortable(true);
        nameCol.setDataStoreName(IsSortProvider.ContactSort.NAME.name());
        cellTable.addColumn(nameCol, I18N.CONSTANTS.contacts_column_name());

        // Email column.
        final TextColumn<ContactDTO> emailCol = new TextColumn<ContactDTO>() {

            @Override
            public String getValue(final ContactDTO contact) {
                return contact.getEmail();
            }
        };
        emailCol.setCellStyleNames(CELL_STYLE_NAMES);
        emailCol.setSortable(true);
        emailCol.setDataStoreName(IsSortProvider.ContactSort.EMAIL.name());
        cellTable.addColumn(emailCol, I18N.CONSTANTS.contacts_column_email());

        // Phone column.
        final TextColumn<ContactDTO> phoneCol = new TextColumn<ContactDTO>() {

            @Override
            public String getValue(final ContactDTO contact) {
                return contact.getPhone();
            }
        };
        phoneCol.setCellStyleNames(CELL_STYLE_NAMES);
        phoneCol.setSortable(true);
        phoneCol.setDataStoreName(IsSortProvider.ContactSort.PHONE.name());
        cellTable.addColumn(phoneCol, I18N.CONSTANTS.contacts_column_phone());

        // City column.
        final TextColumn<ContactDTO> cityCol = new TextColumn<ContactDTO>() {

            @Override
            public String getValue(final ContactDTO contact) {
                return contact.getCity();
            }
        };
        cityCol.setCellStyleNames(CELL_STYLE_NAMES);
        cityCol.setSortable(true);
        cityCol.setDataStoreName(IsSortProvider.ContactSort.CITY.name());
        cellTable.addColumn(cityCol, I18N.CONSTANTS.contacts_column_city());

        // Zip Code column.
        final TextColumn<ContactDTO> zipCodeCol = new TextColumn<ContactDTO>() {

            @Override
            public String getValue(final ContactDTO contact) {
                return contact.getZipCode();
            }
        };
        zipCodeCol.setCellStyleNames(CELL_STYLE_NAMES);
        zipCodeCol.setSortable(true);
        zipCodeCol.setDataStoreName(IsSortProvider.ContactSort.ZIP_CODE.name());
        cellTable.addColumn(zipCodeCol, I18N.CONSTANTS.contacts_column_zipCode());
        cellTable.setColumnWidth(zipCodeCol, 150, Style.Unit.PX);

        // Actions column.
        final List<HasCell<ContactDTO, ?>> cells = Arrays.asList(
                new ContactActionCell(new ButtonCell(IconType.PENCIL, ButtonType.PRIMARY, ButtonSize.SMALL),
                        (index, contact, value) ->
                                eventBus.navigateRequest(Page.CONTACT.requestWith(RequestParameter.ID, contact.getId()))),
                new ContactActionCell(new ButtonCell(IconType.EYE, ButtonType.INFO, ButtonSize.SMALL),
                        (index, contact, value) -> N10N.info("TODO : " + contact.getId())) // TODO
        );
        final Column<ContactDTO, ContactDTO> editCol = new Column<ContactDTO, ContactDTO>(new CompositeCell<>(cells)) {
            @Override
            public ContactDTO getValue(ContactDTO contact) {
                return contact;
            }
        };
        editCol.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        editCol.setCellStyleNames(CELL_STYLE_NAMES);
        cellTable.addColumn(editCol, I18N.CONSTANTS.contacts_column_actions());
        cellTable.setColumnWidth(editCol, 100, Style.Unit.PX);

        // Default table sort.
        cellTable.getColumnSortList().push(nameCol);
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

    private static class ContactActionCell implements HasCell<ContactDTO, String> {

        private final ButtonCell buttonCell;
        private final FieldUpdater<ContactDTO, String> fieldUpdater;

        private ContactActionCell(final ButtonCell buttonCell,
                                  final FieldUpdater<ContactDTO, String> fieldUpdater) {
            this.buttonCell = buttonCell;
            this.fieldUpdater = fieldUpdater;
        }

        @Override
        public ButtonCell getCell() {
            return buttonCell;
        }

        @Override
        public FieldUpdater<ContactDTO, String> getFieldUpdater() {
            return fieldUpdater;
        }

        @Override
        public String getValue(ContactDTO contact) {
            return null;
        }
    }
}