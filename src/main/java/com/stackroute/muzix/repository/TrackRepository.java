package com.stackroute.muzix.repository;

import com.stackroute.muzix.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// repository for tracks
@Repository
public interface TrackRepository  extends MongoRepository<Track,Integer> {
    // query to search a track by name
//    @Query("FROM Track t WHERE t.name = ?1")
//    Track searchTrackByName(String name);
}
