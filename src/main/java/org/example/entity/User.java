package org.example.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="users")

@NamedEntityGraph(name = "User.roles",
        attributeNodes = {
                @NamedAttributeNode(value = "roles", subgraph = "roles")
        },
        subgraphs = @NamedSubgraph(name = "roles", attributeNodes = @NamedAttributeNode("rights"))
)
@NamedEntityGraph(name = "User.search",
        attributeNodes = {
//				@NamedAttributeNode("areasOfExpertise"),
                @NamedAttributeNode("projects"),
                @NamedAttributeNode("interests")
        }
)
@NamedQuery(
        name="User.searchUsers",
        query = "select u from User u " +
                "where " +
                "u.userName LIKE CONCAT('%', :searchTerm,'%') or " +
                "u.email LIKE CONCAT('%', :searchTerm,'%') or " +
                "u.firstName LIKE CONCAT('%', :searchTerm,'%') or " +
                "u.lastName LIKE CONCAT('%', :searchTerm,'%') " +
                "order by u.userName "
)
@NamedQuery(
        name="User.searchUsers123",
        query = "select u from User u " +
                "join u.areasOfExpertise a " +
                "where " +
                "a.area LIKE CONCAT('%', :searchTerm,'%')")


//@NamedNativeQuery(
//		name="User.searchByExpertise",
//		query = "select u0_ from user_has_areas ua0_ inner join User u0_ on ua0_.fk_user=u0_.id inner join AreaOfExpertise a0_ on a0_.id=ua0_.fk_areas where a0_.area LIKE CONCAT('%', :searchTerm,'%')", resultClass = User.class, hints "javax.persistence.loadgraph"))

@NamedQuery(name="User.searchUsers1", query = "select u from User u where u.firstName = 'Peter'")
//				+ "join u.areasOfExpertise a " +
//				"where " +
//				"a.area LIKE CONCAT('%', :searchTerm,'%'" +
//				+
//				"where " +
//				"u.userName LIKE CONCAT('%', :searchTerm,'%')" )

//				+ "join u.areas a where a.area LIKE CONCAT('%', :searchTerm,'%')")

//		select t from Tournament t where t.players.id = :id

public class User implements EntityWithId {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String userName;
    private String firstName;
    private String middleNames;
    private String lastName;
    private String title;
    private String qualification;
    private String position;
    private String colleges;
    private String email;
    private String imageURl;
    private String phoneNumber;
    private boolean active;
    private String password;
    private String biography;


    @ElementCollection
    private Set<String> interests;

    @ElementCollection
    private Set<String> projects;

    @ElementCollection
    private Set<String> currentStudentProjects;

    @ElementCollection
    private Set<String> pastStudentProjects;

    @ElementCollection
    private Set<String> publications;

    @ElementCollection
    private Set<String> relatedWebsites;

    @ManyToMany
    @JoinTable(
            name = "user_has_areas",
            joinColumns = { @JoinColumn(name = "fk_user") },
            inverseJoinColumns = { @JoinColumn(name = "fk_areas") }
    )
    private Set<AreaOfExpertise> areasOfExpertise = new HashSet<>();

    @Getter
    @ManyToMany
//            (fetch = javax.persistence.FetchType.EAGER)
    @JoinTable(
            name = "user_has_roles",
            joinColumns = { @JoinColumn(name = "fk_user") },
            inverseJoinColumns = { @JoinColumn(name = "fk_userrole") }
    )
    private Set<UserRole> roles = new HashSet<>();

    public List<String> getPublications() {
        return new ArrayList<>(publications);
    }

    public void setPublications(List<String> publications) {
        this.publications = new HashSet<>(publications);;
    }

    public String getColleges() {
        return colleges;
    }

    public void setColleges(String colleges) {
        this.colleges = colleges;
    }


    //	@JsonBackReference
    public Set<AreaOfExpertise> getAreasOfExpertises() {
        return areasOfExpertise;
    }

    public void setAreasOfExpertise(Set<AreaOfExpertise> areasOfExpertise) {
        this.areasOfExpertise = areasOfExpertise;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public void addRole(UserRole userRole) {
        this.roles.add(userRole);
    }

}


