package oksana.dvornitska.interview.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Table(name = "jobs")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "object_id", unique = true)
    Integer objectID;

    @Column(name = "url", nullable = false)
    String url = "NOT_FOUND";

    @Column(name = "title", nullable = false)
    String title = "NOT_FOUND";

    @Column(name = "organisation_url", nullable = false)
    String organisationUrl = "NOT_FOUND";

    @Column(name = "logo", nullable = false)
    String logo = "NOT_FOUND";

    @Column(name = "organisation_title", nullable = false)
    String organisationTitle = "NOT_FOUND";

    @Column(name = "labor_function", nullable = false)
    String laborFunction = "NOT_FOUND";

    @Column(name = "location", nullable = false)
    String location = "NOT_FOUND";

    @Column(name = "posted_date", nullable = false)
    String postedDate = "NOT_FOUND";

    @Column(name = "description", nullable = false)
    String description = "NOT_FOUND";

    @Column(name = "tags", nullable = false)
    String tags = "NOT_FOUND";

}
