package com.stackroute.muzix.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exception.TrackAlreadyExistException;
import com.stackroute.muzix.exception.TrackNotFoundException;
import com.stackroute.muzix.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
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
    public Track saveTrack(Track track) throws TrackAlreadyExistException {
        if (trackRepository.existsById(track.getId())) {
            // throw track already exist exception
            throw new TrackAlreadyExistException("This track already exists");
        } else {
            // save the track
            Track track1 = trackRepository.save(track);
            if(track1==null){
                throw new TrackAlreadyExistException("This track already exists");
            }
            return track1;
        }
    }

    // method to update a track
    @Override
    public Track updateTrack(Track track) {
        // update a track
        Track track1=trackRepository.save(track);
        return track1;
    }

    // method to get a track by id
    @Override
    public Track getTrackById(int id) throws TrackNotFoundException {
        Optional<Track> track = trackRepository.findById(id);

        // if track is present then get that track
        if(track.isPresent()) {
            return track.get();
        } else {
            // if track is not present, then call the custom exception
            throw new TrackNotFoundException("This track does not exist");
            }
     }

    // method to get all tracks
    @Override
    public List<Track> getAllTracks() {
        // find all the tracks from database
        return trackRepository.findAll();
    }

    // method to delete a track
    @Override
    public void deleteTrack(int id) {
        Optional<Track> track = trackRepository.findById(id);
        // check if data is present in database
        if(track.isPresent()) {
            // if data is present, delete the record of given id
            trackRepository.deleteById(id);
        } else {
            //throw new RecordNotFoundException("No employee record exist for given id");

        }
    }
    // method to search a track by name
//    @Override
//    public Track searchTrackByName(String name){
//      // call method of repository for searching the track by name
//      return trackRepository.searchTrackByName(name);
//    }
    // method to search a top tracks
    @Override
    public List<Track> getTopTracks(){
        // create new RestTemplate
        RestTemplate restTemplate=new RestTemplate();
        // get data using the third party api last.fm by generating a unique api key
        ResponseEntity<String> result=restTemplate.getForEntity("http://ws.audioscrobbler.com/2.0/?method=chart.gettoptracks&api_key=f3c26820d3e3a856543824cf71da6ebb&format=json",String.class);

        try {
            // fetch the root node from the JSON
            JsonNode rootTrack = new ObjectMapper().readTree(result.getBody());
            // fetch the inner json from the nested json data
            JsonNode trackNode=rootTrack.path("tracks").path("track");

            // iterate over all elements present in data
            for(int index=0;index<trackNode.size();index++){
                Track track=new Track();
                // store the data from the data fetched from api
                track.setId(index+2);
                track.setName(trackNode.get(index).path("name").asText());
                track.setComment(trackNode.get(index).path("artist").path("name").asText());
                // save it in database
                trackRepository.save(track);
            }
            // catch exceptions if occur
        } catch (Exception e){
            e.printStackTrace();
        }
            // return all the saved data from the database
            return trackRepository.findAll();
    }

}
