package com.gorgeous.ringolift.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.*;
import java.time.LocalDateTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDateTime dateOfBirth;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gender_id")
    private UserGender gender;

    @Column(name = "picture")
    private String picture;

    // Assuming Goal is another entity
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "goal_id")
    private Goal goal;

    @Column(name = "password")
    private String password;

    @Column(name = "is_public")
    private Boolean isPublic;

    @Column(name = "google_id")
    private String googleId;

    @Column(name = "access_token")
    private String accessToken;

    // The roles in the database cannot be null
    // otherwise, Hibernate cannot retrieve the user by ID
    // https://stackoverflow.com/questions/57795044/spring-data-jpa-mysql-findbyid-empty-unless-findall-called-before
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_" + this.role.getName().toUpperCase()));
        return authorityList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    // The createdAt and updatedAt fields are inherited from BaseEntity
}
