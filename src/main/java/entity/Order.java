package entity;

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
    private String number;

    @Column(name = "order_date", nullable = false)
    private String date;

    @Column(name = "order_type", nullable = false)
    private String type;

    @Column(name = "order_comment")
    private String comment;

    public Order() {
    }

    public Order(List<Student> students, String number, String date, String type, String comment) {
        this.students = students;
        this.number = number;
        this.date = date;
        this.type = type;
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (students != null ? !students.equals(order.students) : order.students != null) return false;
        if (number != null ? !number.equals(order.number) : order.number != null) return false;
        if (date != null ? !date.equals(order.date) : order.date != null) return false;
        if (type != null ? !type.equals(order.type) : order.type != null) return false;
        return comment != null ? comment.equals(order.comment) : order.comment == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (students != null ? students.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", students=" + students +
                ", number='" + number + '\'' +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
