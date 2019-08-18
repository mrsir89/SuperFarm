package com.project.superfarm.entity.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.superfarm.model.CustomerEdit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name="users")
@DynamicUpdate
@DynamicInsert
@Setter @Getter @ToString
public class Users<T extends Object> implements Serializable , UserDetails {

    @Id
    @Column(name="user_num",updatable = false,insertable = false,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNum;

    @Column(name = "user_id",updatable = false,nullable = false)
    private String userId;

    @Column(name ="user_password",nullable = false)
    private String userPassword;

    @Column(name="user_name",nullable = false)
    private String userName;

    @Column(name="user_type")
    private String userType;

    @Column(name="user_regday",nullable = true)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date userRegday;

    @Column(name="user_email")
    private String userEmail;


    @Column(name="user_last_connect")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date userLastConnect;


    @Transient
    private T position;

    public T getPosition () {

        return position;
    }

    public void setPosition(T position) {
        this.position = position;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name="user_num"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Roles> roles;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (Roles role : getRoles()) {
            authorities.addAll(role.getPrivileges());
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Transient
    public void setEditUser(CustomerEdit customerEdit){
        this.userNum = customerEdit.getUserNum();
        this.userId = customerEdit.getUserId();
        this.userPassword = customerEdit.getUserPassword();
        this.userEmail = customerEdit.getUserEmail();

    }
}
