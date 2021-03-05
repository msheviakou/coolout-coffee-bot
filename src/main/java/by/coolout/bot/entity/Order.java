package by.coolout.bot.entity;

import by.coolout.bot.statics.Syrups;
import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "drink_id")
    private Drink drink;
    private Syrups syrup;
    private String time;
    private String wishes;
    private String place;
    private Double cost;
    private String username;

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", drink=" + drink +
                ", syrup=" + syrup +
                ", time='" + time + '\'' +
                ", wishes='" + wishes + '\'' +
                ", place='" + place + '\'' +
                ", cost=" + cost +
                ", username='" + username + '\'' +
                '}';
    }
}
