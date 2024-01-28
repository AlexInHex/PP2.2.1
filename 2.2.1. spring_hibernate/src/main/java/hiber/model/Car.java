package hiber.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;


@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "seria")
    private String seria;


    public Car() {}

    public Car(String model, String seria) {
        this.model = model;
        this.seria = seria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {return model;}

    public void setModel(String model) {this.model = model;}

    public String getSeria() {return seria;}

    public void setSeria(String seria) {this.seria = seria;}
}