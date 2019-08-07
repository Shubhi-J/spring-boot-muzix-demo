package com.stackroute.muzix.controller;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exception.GlobalExceptionHandler;
import com.stackroute.muzix.exception.TrackAlreadyExistException;
import com.stackroute.muzix.exception.TrackNotFoundException;
import com.stackroute.muzix.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// class level request mapping
@RequestMapping("api/v1")
public class TrackController extends GlobalExceptionHandler {

    // create instance variable of ResponseEntity
    private ResponseEntity responseEntity;
    
    // create object of TrackService
    private TrackService trackService;

    // parameterised constructor
    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    // method to save data with endpoint track
    @PostMapping("/track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistException{
          // save track
          trackService.saveTrack(track);
          responseEntity=new ResponseEntity<String>("Successfully saved", HttpStatus.CREATED);
        // return result
        return responseEntity;
    }

    // method to get all tracks with endpoint track
    @GetMapping("/track")
    public ResponseEntity<?> getAllTracks() {
        // return list of tracks
        return new ResponseEntity<List<Track > >(trackService.getAllTracks(),HttpStatus.OK);
    }

    // method to get a specific track with endpoint track/{id}
    @GetMapping("/track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable int id) throws TrackNotFoundException {
        // get track by id
            responseEntity= new ResponseEntity<Track>(trackService.getTrackById(id),HttpStatus.OK);
            // catch track not found exception
        return responseEntity;
    }

    // method to delete a track with endpoint track/{id}
    @DeleteMapping("/track/{id}")
    public void deleteTrack(@PathVariable int id) {
        trackService.deleteTrack(id);
    }

    // method to update a track with endpoint track
    @PutMapping("/track")
    public ResponseEntity<?> updateTrack(@RequestBody Track track){
            // update track
            trackService.updateTrack(track);
            responseEntity=new ResponseEntity<String>("Successfully updated", HttpStatus.OK);
        // return success message
        return responseEntity;
    }

    // method to get a specific track with endpoint trackByName/{name}
    @GetMapping("/trackByName/{name}")
    public ResponseEntity<?> searchTrackByName(@PathVariable String name) {
        // get track by name
        responseEntity= new ResponseEntity<Track>(trackService.searchTrackByName(name),HttpStatus.OK);
    return responseEntity;
    }
}
