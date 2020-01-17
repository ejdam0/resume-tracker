package pl.strzelecki.resumetracker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    @NonNull
    private String username;

    @Column(name = "pass")
    @NonNull
    private String pass;

    @Column(name = "enabled")
    @NonNull
    private int enabled;
}
