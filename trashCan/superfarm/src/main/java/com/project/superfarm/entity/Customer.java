package com.project.superfarm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="customer",uniqueConstraints = {
                      @UniqueConstraint(columnNames = "c_id")
                                            })
public class Customer implements UserDetails , Serializable {


    @Id
    @Column(name = "c_num",nullable = false,updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성을 Database에 위임한다.
    private Long c_num;

    @Column(name="c_id",nullable = false,updatable = false)
    private String cid;

    @Column(name = "c_pwd")
    private String c_pwd;

    @Column(name ="c_name")
    private String c_name;

    @Column(name="c_birth")
    private String c_birth;

    @Column(name="c_gender")
    private String c_gender;

    @Column(name ="c_regeday")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date c_regdate;


    @Column(name = "c_point")
    private int c_point;


    @Transient
    private CustomerContact contact;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "customer_roles",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Roles> roles;

    @OneToOne
    public void setPrivilege(Roles roles){

        this.roles.add(roles);

    }


    // Privilege에서 c_id에 대한 권한만 가지고 온다. 그게 끝 한개 일수도 두개 일수도 있어서 collection에 넣어 준다.
    @JsonIgnore
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
        return getC_pwd();
    }

    @Override
    public String getUsername() {
        return getCid();
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


    public Long getC_num() {
        return c_num;
    }

    public void setC_num(Long c_num) {
        this.c_num = c_num;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getC_pwd() {
        return c_pwd;
    }

    public void setC_pwd(String c_pwd) {
        this.c_pwd = c_pwd;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_birth() {
        return c_birth;
    }

    public void setC_birth(String c_birth) {
        this.c_birth = c_birth;
    }

    public String getC_gender() {
        return c_gender;
    }

    public void setC_gender(String c_gender) {
        this.c_gender = c_gender;
    }

    public Date getC_regdate() {
        return c_regdate;
    }

    public void setC_regdate(Date c_regdate) {
        this.c_regdate = c_regdate;
    }

    public int getC_point() {
        return c_point;
    }

    public void setC_point(int c_point) {
        this.c_point = c_point;
    }

    public CustomerContact getContact() {
        return contact;
    }

    public void setContact(CustomerContact contact) {
        this.contact = contact;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }
}
