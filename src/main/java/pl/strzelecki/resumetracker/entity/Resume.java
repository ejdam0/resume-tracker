package pl.strzelecki.resumetracker.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import pl.strzelecki.resumetracker.constants.LocalDateDeserializer;
import pl.strzelecki.resumetracker.constants.LocalDateSerializer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "resumes")
@Data
@NoArgsConstructor
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    @NonNull
    private String title;

    @JoinColumn(name = "employer_id")
    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Employer employerId;

    @Column(name = "post_date")
    @NonNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate postDate;

    @Column(name = "responded")
    @NonNull
    private Boolean responded;

    @Column(name = "url")
    private String url;

    public Resume(String title, Employer employer, LocalDate postDate, Boolean responded, String url) {
        this.title = title;
        this.employerId = employer;
        this.postDate = postDate;
        this.responded = responded;
        this.url = url;
    }

}
