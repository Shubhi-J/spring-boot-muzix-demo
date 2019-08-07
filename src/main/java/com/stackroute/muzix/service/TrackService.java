package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Track;

import java.util.List;

// interface for trackService
public interface TrackService {

    // method to save tracks
    public Track saveTrack(Track track);

    // method to get tracks by id
    public Track getTrackById(int id);

    // method to get all tracks
    public List<Track> getAllTracks();

    // method to delete a track of given id
    public Track deleteTrack(int id);

}
