package oksana.dvornitska.interview.repositories;

import oksana.dvornitska.interview.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Integer> {

}
