package fr.fscf.contacts.client.ui.presenter;

import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.inject.ImplementedBy;
import fr.fscf.contacts.client.dispatch.CommandResultHandler;
import fr.fscf.contacts.client.inject.Injector;
import fr.fscf.contacts.client.navigation.Page;
import fr.fscf.contacts.client.navigation.PageRequest;
import fr.fscf.contacts.client.ui.presenter.base.AbstractPagePresenter;
import fr.fscf.contacts.client.ui.view.ContactsView;
import fr.fscf.contacts.client.ui.view.base.ViewInterface;
import fr.fscf.contacts.client.util.AsyncDataProvider;
import fr.fscf.contacts.shared.command.GetContactsCommand;
import fr.fscf.contacts.shared.command.result.ListResult;
import fr.fscf.contacts.shared.dto.ContactDTO;
import org.gwtbootstrap3.client.ui.Pagination;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

import javax.inject.Inject;

/**
 * Contacts presenter.
 *
 * @author Denis
 */
public class ContactsPresenter extends AbstractPagePresenter<ContactsPresenter.View> {

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
        cellTablePager.setPageSize(10);

        dataProvider = new AsyncDataProvider<ContactDTO>() {
            @Override
            protected void onRangeChanged(final HasData<ContactDTO> display) {

                final Range range = display.getVisibleRange();

                dispatch.execute(new GetContactsCommand(range, null), new CommandResultHandler<ListResult<ContactDTO>>() {
                    @Override
                    protected void onCommandSuccess(final ListResult<ContactDTO> result) {

                        dataProvider.updateRowCount(result.getSize(), true); // Fires a 'onRangeChanged' event.

                        // Push the data into the list.
                        view.getContactsTable().setRowData(range.getStart(), result.getList());

                        rebuildPagination();
                    }
                });
            }
        };
        dataProvider.addDataDisplay(view.getContactsTable());
    }

    @Override
    public void onPageRequest(final PageRequest request) {
        if (isInitialized()) {
            dataProvider.refresh(view.getContactsTable());
        }
    }

    private void rebuildPagination() {
        view.getPagination().rebuild(cellTablePager);
    }

    /**
     * View interface.
     */
    @ImplementedBy(ContactsView.class)
    public interface View extends ViewInterface {

        CellTable<ContactDTO> getContactsTable();

        Pagination getPagination();

    }
}
