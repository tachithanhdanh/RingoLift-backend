package com.gorgeous.ringolift.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

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

    // The createdAt and updatedAt fields are inherited from BaseEntity
}
