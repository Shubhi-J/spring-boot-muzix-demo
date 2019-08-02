package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exception.TrackAlreadyExistException;
import com.stackroute.muzix.exception.TrackNotFoundException;

import java.util.List;

// interface for trackService
public interface TrackService {

    // method to save tracks
    public Track saveTrack (Track track) throws TrackAlreadyExistException;

    // method to update a track
    public Track updateTrack(Track track);

    // method to get tracks by id
    public Track getTrackById(int id) throws TrackNotFoundException;

    // method to get all tracks
    public List<Track> getAllTracks();

    // method to delete a track of given id
    public void deleteTrack(int id);

    // method to search a track by name
    public Track searchTrackByName(String name);
}
