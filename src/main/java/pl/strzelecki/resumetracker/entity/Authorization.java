package pl.strzelecki.resumetracker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "authorizations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authorization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "user_id")
    @NonNull
    @ManyToOne
    private User userId;

    @Column(name = "authorization_type")
    @NonNull
    private String authorizationType;
}
