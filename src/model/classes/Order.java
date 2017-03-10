package model.classes;

/**
 * Created by Dimitry on 10.03.17.
 */
public class Order {
    private String id;
    private String orderNum;
    private String orderType;
    private String orderDate;
    private String orderComment;

    public Order() {
    }


    public Order(String id, String orderNum, String orderType, String orderDate, String orderComment) {
        this.id = id;
        this.orderNum = orderNum;
        this.orderType = orderType;
        this.orderDate = orderDate;
        this.orderComment = orderComment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
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

        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (orderNum != null ? !orderNum.equals(order.orderNum) : order.orderNum != null) return false;
        if (orderType != null ? !orderType.equals(order.orderType) : order.orderType != null) return false;
        if (orderDate != null ? !orderDate.equals(order.orderDate) : order.orderDate != null) return false;
        return orderComment != null ? orderComment.equals(order.orderComment) : order.orderComment == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (orderNum != null ? orderNum.hashCode() : 0);
        result = 31 * result + (orderType != null ? orderType.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (orderComment != null ? orderComment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", orderType='" + orderType + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", orderComment='" + orderComment + '\'' +
                '}';
    }
}
