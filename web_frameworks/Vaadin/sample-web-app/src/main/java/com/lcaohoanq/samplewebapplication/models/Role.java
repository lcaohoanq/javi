package com.lcaohoanq.samplewebapplication.models;

import com.lcaohoanq.samplewebapplication.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
@Entity
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private UserRole userRole;

//    @OneToMany(mappedBy = "role")
//    private List<User> users;

    public Role(UserRole userRole) {
        this.userRole = userRole;
    }

    public static String MEMBER = "MEMBER";
    public static String STAFF = "STAFF";
    public static String BREEDER = "BREEDER";
    public static String MANAGER = "MANAGER";
}
