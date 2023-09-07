package org.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="areas")
public class AreaOfExpertise {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String code;

    private String area;

    @ManyToMany(mappedBy = "areasOfExpertise")
    private List<User> users;

    public AreaOfExpertise() {
    }

    //    @JsonBackReference
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "YourClassName{" +
                "id='" + id + '\'' +
                ", code=" + code +
                ", area='" + area + '\'' +
                '}';
    }

}
