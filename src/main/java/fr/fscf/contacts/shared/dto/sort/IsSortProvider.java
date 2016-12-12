package fr.fscf.contacts.shared.dto.sort;

import com.google.gwt.user.cellview.client.AbstractCellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.rpc.IsSerializable;
import fr.fscf.contacts.shared.util.ClientUtils;
import fr.fscf.contacts.shared.util.Sort;

import java.util.*;
import java.util.stream.Stream;

import static com.google.gwt.user.cellview.client.ColumnSortList.ColumnSortInfo;
import static fr.fscf.contacts.shared.util.Entities.*;

/**
 * Sort columns interface.<br/>
 * Each {@link IsSortProvider} instance represents a view column corresponding sort(s).
 */
public interface IsSortProvider extends IsSerializable {

    /**
     * Returns the given cell table current sort(s).
     *
     * @param sortProviderClass The sort provider enum class.
     * @param cellTable         The cell table.
     * @param <P>               The provider type.
     * @return The given cell table current sorts list.
     */
    static <P extends Enum<P> & IsSortProvider> List<Sort> fromTable(final Class<P> sortProviderClass,
                                                                     final AbstractCellTable<?> cellTable) {
        final Optional<IsSortProvider> sortProvider = Optional.ofNullable(cellTable)
                .map(AbstractCellTable::getColumnSortList)
                .filter(Objects::nonNull)
                .filter(sortList -> sortList.size() > 0)
                .map(sortList -> sortList.get(0))
                .filter(Objects::nonNull)
                .flatMap(sortInfo -> IsSortProvider.fromString(sortProviderClass, sortInfo));

        final List<Sort> sorts = new ArrayList<>(sortProvider
                .map(IsSortProvider::getSorts)
                .orElse(Collections.emptyList()));

        if (ClientUtils.isNotEmpty(sorts) && sortProvider.isPresent()
                && sortProvider.get().getSecuritySort() != null) {
            sorts.add(sortProvider.get().getSecuritySort());
        }

        return sorts;
    }

    /**
     * Returns the given column sort info corresponding {@link IsSortProvider} instance.
     *
     * @param sortProviderClass The sort provider enum class.
     * @param sortInfo          The column sort info.
     * @param <P>               The provider type.
     * @return The {@link IsSortProvider} instance for the given column sort info, or {@code Optional.empty()}.
     */
    static <P extends Enum<P> & IsSortProvider> Optional<IsSortProvider> fromString(final Class<P> sortProviderClass,
                                                                                    final ColumnSortInfo sortInfo) {
        try {

            final Optional<IsSortProvider> sortProvider = Optional.ofNullable(sortInfo)
                    .map(ColumnSortInfo::getColumn)
                    .map(Column::getDataStoreName)
                    .filter(ClientUtils::isNotBlank)
                    .map(dataStoreName -> Enum.valueOf(sortProviderClass, dataStoreName));

            // Apply sort order to each sort.
            sortProvider
                    .map(IsSortProvider::getSorts)
                    .map(Collection::stream)
                    .orElse(Stream.empty())
                    .filter(Objects::nonNull)
                    .forEach(sort -> sort.setOrder(Sort.Order.fromBoolean(sortInfo.isAscending())));

            return sortProvider;

        } catch (Exception e) {
            // Digest exception.
            return Optional.empty();
        }
    }

    /**
     * Returns the sort provider sorts list.
     *
     * @return The sort list.
     */
    List<Sort> getSorts();

    /**
     * Returns the security sort to ensure proper data order.<br/>
     * Default implementation returns {@code null} (no security sort).
     *
     * @return The security sort.
     */
    default Sort getSecuritySort() {
        return null;
    }

    /**
     * Contact sort providers.
     */
    enum ContactSort implements IsSortProvider {
        NAME(Sort.of(CONTACT_NAME), Sort.of(CONTACT_FIRST_NAME)),
        EMAIL(Sort.of(CONTACT_EMAIL)),
        PHONE(Sort.of(CONTACT_PHONE)),
        CITY(Sort.of(CONTACT_CITY)),
        ZIP_CODE(Sort.of(CONTACT_ZIP_CODE));

        private final List<Sort> sorts;

        ContactSort(final Sort... sorts) {
            this.sorts = ClientUtils.toList(sorts);
        }

        @Override
        public List<Sort> getSorts() {
            return sorts;
        }

        @Override
        public Sort getSecuritySort() {
            return Sort.of(CONTACT_ID);
        }
    }

}
