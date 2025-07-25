package hr.abysalto.hiring.mid.user.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "buyer")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstName", nullable = false, length = 100)
    private String firstName;

    @Column(name = "lastName", nullable = false, length = 100)
    private String lastName;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @CreationTimestamp
    @Column(name = "createdAt", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    private Instant updatedAt;

}