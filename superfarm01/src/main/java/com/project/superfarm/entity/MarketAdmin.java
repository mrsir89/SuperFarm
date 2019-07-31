package com.project.superfarm.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "marketadmin", uniqueConstraints = {@UniqueConstraint(columnNames = "admin_id")})
public class MarketAdmin implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_num", nullable = false, updatable = false)
    private Long adminNum;

    @Column(name="admin_id", nullable = false, updatable = false)
    private String adminid;

    @Column(name="admin_pwd")
    private String adminpwd;

    @Column(name="admin_name")
    private String admin_name;

    @Column(name="admin_phone")
    private int admin_phone;

    @Column(name="admin_business_num")
    private int admin_business_num;

    @Column(name="admin_regday")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date  admin_regday;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "admin_roles",
            joinColumns = @JoinColumn(name = "admin_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Roles> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        for (Roles role : getRoles()) {
            authorities.addAll(role.getPrivileges());
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return adminpwd;
    }

    @Override
    public String getUsername() {
        return adminid;
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

    public Long getAdminNum() {
        return adminNum;
    }

    public void setAdminNum(Long adminNum) {
        this.adminNum = adminNum;
    }

    public String getAdminid() {
        return adminid;
    }

    public void setAdminid(String adminid) {
        this.adminid = adminid;
    }

    public String getAdminpwd() {
        return adminpwd;
    }

    public void setAdminpwd(String adminpwd) {
        this.adminpwd = adminpwd;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
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
