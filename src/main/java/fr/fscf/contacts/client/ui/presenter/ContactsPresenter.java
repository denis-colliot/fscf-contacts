package fr.fscf.contacts.client.ui.presenter;

import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.*;
import com.google.inject.ImplementedBy;
import fr.fscf.contacts.client.inject.Injector;
import fr.fscf.contacts.client.navigation.Page;
import fr.fscf.contacts.client.navigation.PageRequest;
import fr.fscf.contacts.client.ui.presenter.base.AbstractPagePresenter;
import fr.fscf.contacts.client.ui.view.ContactsView;
import fr.fscf.contacts.client.ui.view.base.ViewInterface;
import fr.fscf.contacts.shared.dto.ContactDTO;
import org.gwtbootstrap3.client.ui.Pagination;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Contacts presenter.
 *
 * @author Denis
 */
public class ContactsPresenter extends AbstractPagePresenter<ContactsPresenter.View> {

    private static final List<ContactDTO> MOCK_DATA = new ArrayList<>();

    static {
        for (int i = 0; i < 100; i++) {
            final ContactDTO contactDTO = new ContactDTO();
            contactDTO.setId((long) i);
            contactDTO.setName("Name #" + i);
            contactDTO.setFirstName("Firstname #" + i);
            contactDTO.setEmail("contact_" + i + "@email.com");
            MOCK_DATA.add(contactDTO);
        }
    }

    /**
     * View interface.
     */
    @ImplementedBy(ContactsView.class)
    public interface View extends ViewInterface {

        CellTable<ContactDTO> getContactsTable();

        Pagination getPagination();

    }

    private SimplePager cellTablePager;
    private AsyncDataProvider<ContactDTO> dataProvider;

    @Inject
    protected ContactsPresenter(final View view, final Injector injector) {
        super(view, injector);
    }

    @Override
    public Page getPage() {
        return Page.CONTACTS;
    }

    @Override
    public void onBind() {
        cellTablePager = new SimplePager();
        cellTablePager.setDisplay(view.getContactsTable());

        dataProvider = new AsyncDataProvider<ContactDTO>() {
            @Override
            protected void onRangeChanged(final HasData<ContactDTO> display) {
                final Range range = display.getVisibleRange();
                final int start = range.getStart();
                final int length = range.getLength();

                dataProvider.updateRowCount(MOCK_DATA.size(), true); // Fires a 'onRangeChanged' event.

                // Create the data to push into the view. At this point, you could send
                // an asynchronous RPC request to a server.

                // Push the data into the list.
                view.getContactsTable().setRowData(start, MOCK_DATA.subList(start, start + length));

                rebuildTable();
            }
        };
        dataProvider.addDataDisplay(view.getContactsTable());
    }

    @Override
    public void onPageRequest(final PageRequest request) {
    }

    private void rebuildTable() {
        view.getPagination().rebuild(cellTablePager);
    }
}
