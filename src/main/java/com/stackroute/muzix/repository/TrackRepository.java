package com.stackroute.muzix.repository;

import com.stackroute.muzix.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// repository for tracks
@Repository
public interface TrackRepository extends JpaRepository<Track,Integer> {
    // query to search a track by name
    @Query("FROM Track t WHERE t.name = ?1")
    Track searchTrackByName(String name);
}
