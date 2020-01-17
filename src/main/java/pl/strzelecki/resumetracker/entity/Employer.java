package pl.strzelecki.resumetracker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "employers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "street")
    @NonNull
    private String street;

    @Column(name = "city")
    @NonNull
    private String city;
}
