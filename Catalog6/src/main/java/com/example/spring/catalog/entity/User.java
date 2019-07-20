package com.example.spring.catalog.entity;

import com.example.spring.catalog.model.Address;
import com.example.spring.catalog.model.Phone;
import com.example.spring.catalog.util.AddressesToStringConverter;
import com.example.spring.catalog.util.BooleanToStringConverter;
import com.example.spring.catalog.util.PhonesToStringConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
@DynamicInsert
public class User implements UserDetails, Serializable {

    @Id
    @Column(columnDefinition = "CHAR(36)", name = "id", updatable = false, nullable = false)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "title")
    private String title;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "email")
    private String email;

    @Column(name = "country_code")
    private String countCode;

    @Column(name = "website")
    private String website;

    @Column(name = "note")
    private String note;

    @Column(name = "addresses")
    @Convert(converter = AddressesToStringConverter.class)
    private Collection<Address> addresses;

    @Column(name = "phones")
    @Convert(converter = PhonesToStringConverter.class)
    private Collection<Phone> phones;

    @Column(name = "is_active")
    @Convert(converter = BooleanToStringConverter.class)
    private Boolean isActive;

    @Column(name = "activated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activated;

    @Column(name = "created")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "updated")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToOne(mappedBy = "user") // 이 변수이름에 해당하는 것은 매핑되는 머천트에 있어야 한다
    private Merchant merchant;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountCode() {
        return countCode;
    }

    public void setCountCode(String countCode) {
        this.countCode = countCode;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Collection<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Collection<Address> addresses) {
        this.addresses = addresses;
    }

    public Collection<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Collection<Phone> phones) {
        this.phones = phones;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Date getActivated() {
        return activated;
    }

    public void setActivated(Date activated) {
        this.activated = activated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : getRoles()) {
            authorities.addAll(role.getPrivileges());
        }
        return authorities;
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
}

