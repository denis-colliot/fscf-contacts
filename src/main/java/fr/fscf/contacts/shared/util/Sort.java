package fr.fscf.contacts.shared.util;

import java.io.Serializable;
import java.util.Optional;

/**
 * Sort utility POJO.
 *
 * @author Denis
 */
public class Sort implements Serializable {

    public static Sort of(String column) {
        return of(column, null);
    }

    public static Sort of(String column, Order order) {
        if (ClientUtils.isBlank(column)) {
            throw new IllegalArgumentException("Sort column name must be provided");
        }
        return new Sort(column, Optional.ofNullable(order).orElse(Order.ASC));
    }

    private String column;
    private Order order;

    private Sort() {
        // RPC Serialization.
    }

    private Sort(String column, Order order) {
        this.column = column;
        this.order = order;
    }

    public String getColumn() {
        return column;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public enum Order {
        ASC, DESC;

        public static Order fromBoolean(final boolean order) {
            return order ? ASC : DESC;
        }
    }

}
