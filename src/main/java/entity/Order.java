package entity;

import java.time.LocalDate;
import java.util.Objects;

public class Order {
   private String id;
   private String date;

    public Order(String id, String date) {
        this.id = id;
        this.date = date;
    }

    public Order() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(date, order.date);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date);
    }

}
