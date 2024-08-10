package com.starforceps.starforceps.domain.organizer.dao;

import com.starforceps.starforceps.domain.organizer.domain.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Long> {
}
