package com.gorgeous.ringolift.models;

import jakarta.persistence.*;
<<<<<<< HEAD
import lombok.*;
=======
import java.time.LocalDate;
>>>>>>> 06edbe28fa5d66891558ceda626a7e48fe4a204e
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
<<<<<<< HEAD
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
=======
>>>>>>> 06edbe28fa5d66891558ceda626a7e48fe4a204e
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
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
=======
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 255)
    private String username;

    @Column(name = "email", unique = true, nullable = false, length = 255)
    private String email;

    @Column(name = "first_name", length = 255)
    private String firstName;

    @Column(name = "last_name", length = 255)
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "picture", length = 255)
    private String picture;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "is_public")
    private Boolean isPublic = true;

    @Column(name = "google_id")
    private Long googleId;

    @Column(name = "access_token", length = 255)
    private String accessToken;

    @Column(name = "goal_id")
    private Long goalId;

    // Constructors

    public User() {
    }

    public User(String username, String email, String password, Gender gender) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    // Setter cho 'id' thường được loại bỏ nếu nó được tự động tạo
    // public void setId(Long id) {
    //     this.id = id;
    // }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Long getGoogleId() {
        return googleId;
    }

    public void setGoogleId(Long googleId) {
        this.googleId = googleId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getGoalId() {
        return goalId;
    }

    public void setGoalId(Long goalId) {
        this.goalId = goalId;
    }
>>>>>>> 06edbe28fa5d66891558ceda626a7e48fe4a204e
}
