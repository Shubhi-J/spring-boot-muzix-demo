package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

// implementation for track service
@Service
public class TrackServiceImpl implements TrackService {

    // create object of trackRepository
    TrackRepository trackRepository;

    // parameterised constructor
    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository){
        this.trackRepository=trackRepository;
    }

    // method to save tracks
    @Override
    public Track saveTrack(Track track) {
        Track track1=trackRepository.save(track);
        return track1;
    }

    // method to get a track by id
    @Override
    public Track getTrackById(int id) {
        Optional<Track> track = trackRepository.findById(id);

        // if track is present then get that track
        if(track.isPresent()) {
            return track.get();
        } else {
            //throw new RecordNotFoundException("No employee record exist for given id");
            return null;
        }
     }

    // method to get all tracks
    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    // method to delete a track
    @Override
    public void deleteTrack(int id) {
        Optional<Track> track = trackRepository.findById(id);
        if(track.isPresent()) {
            trackRepository.deleteById(id);
        } else {
            //throw new RecordNotFoundException("No employee record exist for given id");

        }
    }
    // method to search a track by name
    @Override
    public Track searchTrackByName(String name) {
      return trackRepository.searchTrackByName(name);
    }
}
