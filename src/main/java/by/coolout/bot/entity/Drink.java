package by.coolout.bot.entity;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "drinks")
public class Drink {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "volume")
    private Integer volume;
    @Column(name = "price")
    private Double price;

    @Override
    public String toString() {
        return "Drink{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", volume=" + volume +
                ", price=" + price +
                '}';
    }
}
