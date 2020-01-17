package pl.strzelecki.resumetracker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "resumes")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @ManyToOne
    private Employer employerId;

    @Column(name = "post_date")
    @NonNull
    private Date postDate;

    @Column(name = "responded")
    @NonNull
    private Boolean responded;

    @Column(name = "comment")
    private String comment;
}
