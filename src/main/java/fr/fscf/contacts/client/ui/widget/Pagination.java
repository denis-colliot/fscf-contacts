package fr.fscf.contacts.client.ui.widget;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import org.gwtbootstrap3.client.ui.AnchorListItem;

/**
 * Custom pagination based on {@link org.gwtbootstrap3.client.ui.Pagination} implementation.
 *
 * @author Denis
 */
public class Pagination extends org.gwtbootstrap3.client.ui.Pagination {

    private int displayedPagesNumber = 5;

    @Override
    public void rebuild(final SimplePager pager) {
        clear();

        final int displayLimitNum = getDisplayedPagesNumber();
        final int lineNum = pager.getPage() / displayLimitNum;
        int nowNum = pager.getPage() / displayLimitNum;
        int startPagerNum = nowNum * displayLimitNum;
        int endPagerNum = nowNum * displayLimitNum + displayLimitNum;

        if (nowNum * displayLimitNum + displayLimitNum > pager.getPageCount()) {
            endPagerNum = pager.getPageCount();
        }

        if (pager.getPageCount() < displayLimitNum) {
            endPagerNum = pager.getPageCount();
        }

        if (pager.getPageCount() == 0) {
            return;
        }

        for (; startPagerNum < endPagerNum; startPagerNum++) {
            final int display = startPagerNum + 1;
            final AnchorListItem page = new AnchorListItem(String.valueOf(display));
            page.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(final ClickEvent event) {
                    pager.setPage(display - 1);
                }
            });

            if (startPagerNum == pager.getPage()) {
                page.setActive(true);
            }

            add(page);
        }

        final AnchorListItem prev = addPreviousLink();
        prev.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                pager.setPageStart((lineNum - 1) * pager.getPageSize() * displayLimitNum);
            }
        });
        prev.setEnabled(pager.hasPreviousPage());

        final AnchorListItem next = addNextLink();
        next.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                pager.setPageStart((lineNum + 1) * pager.getPageSize() * displayLimitNum);
            }
        });
        next.setEnabled(pager.hasNextPage());
    }

    public int getDisplayedPagesNumber() {
        return displayedPagesNumber;
    }

    public void setDisplayedPagesNumber(int displayedPagesNumber) {
        this.displayedPagesNumber = displayedPagesNumber;
    }

}