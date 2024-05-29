package com.demo.food.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "Roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "role_name")
    private  String roleName;
    @Column(name = "create_date")
    private String createDate;

    @OneToMany(mappedBy = "roles")
    private Set<Users> listUsers;

    public Set<Users> getListUsers() {
        return listUsers;
    }

    public void setListUsers(Set<Users> listUsers) {
        this.listUsers = listUsers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
