package com.project.superfarm.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="marketadmin")
public class MarketAdmin implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="admin_id")
    private String admin_id;

    @Column(name="admin_pwd")
    private String admin_pwd;

    @Column(name="admin_name")
    private String admin_name;

    @Column(name="admin_authority")
    private String admin_authority;

    @Column(name="admin_phone")
    private Integer admin_phone;

    @Column(name="admin_business_num")
    private Integer admin_business_num;

    @Column(name="admin_regday")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date  admin_regday;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "admin_id",
            joinColumns = @JoinColumn(name = "admin_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Roles> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return admin_pwd;
    }

    @Override
    public String getUsername() {
        return admin_id;
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

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_pwd() {
        return admin_pwd;
    }

    public void setAdmin_pwd(String admin_pwd) {
        this.admin_pwd = admin_pwd;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_authority() {
        return admin_authority;
    }

    public void setAdmin_authority(String admin_authority) {
        this.admin_authority = admin_authority;
    }

    public Integer getAdmin_phone() {
        return admin_phone;
    }

    public void setAdmin_phone(Integer admin_phone) {
        this.admin_phone = admin_phone;
    }

    public Integer getAdmin_business_num() {
        return admin_business_num;
    }

    public void setAdmin_business_num(Integer admin_business_num) {
        this.admin_business_num = admin_business_num;
    }

    public Date getAdmin_regday() {
        return admin_regday;
    }

    public void setAdmin_regday(Date admin_regday) {
        this.admin_regday = admin_regday;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }
}
