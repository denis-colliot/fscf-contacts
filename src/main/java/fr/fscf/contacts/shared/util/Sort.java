package fr.fscf.contacts.shared.util;

import java.io.Serializable;

/**
 * Sort utility POJO.
 *
 * @author Denis
 */
public class Sort implements Serializable {

    private String column;
    private Order order;

    public Sort() {
        // RPC Serialization.
    }

    public Sort(String column, Order order) {
        this.column = column;
        this.order = order;
    }

    public String getColumn() {
        return column;
    }

    public Order getOrder() {
        return order;
    }

    enum Order {
        ASC, DESC
    }

}
