package entity.order.parameters;

import entity.order.Order;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dimitry on 31.03.17.
 */
@Entity
@Table(name = "OrderTypes")
public class OrderType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "order_type_title", unique = true, nullable = false)
    private String orderTypeTitle;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "orderType")
    private Set<Order> orders = new HashSet<>();

    public OrderType() {
    }

    public OrderType(String orderTypeTitle) {
        this.orderTypeTitle = orderTypeTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderTypeTitle() {
        return orderTypeTitle;
    }

    public void setOrderTypeTitle(String orderTypeTitle) {
        this.orderTypeTitle = orderTypeTitle;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderType orderType = (OrderType) o;

        if (id != orderType.id) return false;
        return orderTypeTitle != null ? orderTypeTitle.equals(orderType.orderTypeTitle) : orderType.orderTypeTitle == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (orderTypeTitle != null ? orderTypeTitle.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderType{" +
                "id=" + id +
                ", orderTypeTitle='" + orderTypeTitle + '\'' +
                '}';
    }
}
