package entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Dimitry on 17.03.17.
 */
@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    @ManyToMany(mappedBy = "orders")
    private List<Student> students = new ArrayList<>();

    @Column(name = "order_number", unique = true, nullable = false)
    private String orderNumber;

    @Column(name = "order_date", nullable = false)
    private String orderDate;

    @Column(name = "order_type", nullable = false)
    private String orderType;

    @Column(name = "order_comment")
    private String orderComment;

    public Order() {
    }

    public Order(String orderNumber, String orderDate, String orderType, String orderComment) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.orderType = orderType;
        this.orderComment = orderComment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderComment() {
        return orderComment;
    }

    public void setOrderComment(String orderComment) {
        this.orderComment = orderComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (students != null ? !students.equals(order.students) : order.students != null) return false;
        if (orderNumber != null ? !orderNumber.equals(order.orderNumber) : order.orderNumber != null) return false;
        if (orderDate != null ? !orderDate.equals(order.orderDate) : order.orderDate != null) return false;
        if (orderType != null ? !orderType.equals(order.orderType) : order.orderType != null) return false;
        return orderComment != null ? orderComment.equals(order.orderComment) : order.orderComment == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (students != null ? students.hashCode() : 0);
        result = 31 * result + (orderNumber != null ? orderNumber.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (orderType != null ? orderType.hashCode() : 0);
        result = 31 * result + (orderComment != null ? orderComment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", students=" + students +
                ", orderNumber='" + orderNumber + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", orderType='" + orderType + '\'' +
                ", orderComment='" + orderComment + '\'' +
                '}';
    }
}