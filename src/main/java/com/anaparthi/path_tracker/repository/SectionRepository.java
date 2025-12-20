package com.anaparthi.path_tracker.repository;

import com.anaparthi.path_tracker.domain.Section;
import com.anaparthi.path_tracker.domain.SectionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {

}