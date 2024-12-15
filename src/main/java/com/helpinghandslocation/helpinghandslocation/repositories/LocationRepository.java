package com.helpinghandslocation.helpinghandslocation.repositories;

import com.helpinghandslocation.helpinghandslocation.models.Location;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends JpaRepository<Location, Long> {
    @Query("SELECT l FROM Location l JOIN l.tags t WHERE t.id IN :tagIds GROUP BY l.id HAVING COUNT(t.id) = (SELECT COUNT(*) FROM Tag t2 WHERE t2.id IN :tagIds)")
    List<Location> findByTagsIdInAll(@Param("tagIds")List<Long> tagIds);
}
