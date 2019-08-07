package com.stackroute.muzix.controller;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// class level request mapping
@RequestMapping("api/v1")
public class TrackController {

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
    public ResponseEntity<?> saveTrack(@RequestBody Track track){
      try{
          // save track
          trackService.saveTrack(track);
          responseEntity=new ResponseEntity<String>("Successfully saved", HttpStatus.CREATED);
      } catch (Exception e){
          responseEntity=new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
      }
        // return success message
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
    public ResponseEntity<?> getTrackById(@PathVariable int id) {
        // get track by id
        return new ResponseEntity<Track>(trackService.getTrackById(id),HttpStatus.OK);
    }

    // method to delete a track with endpoint track/{id}
    @DeleteMapping("/track/{id}")
    public void deleteTrack(@PathVariable int id) {
        return trackService.deleteTrack(id);
    }

    // method to update a track with endpoint track
    @PutMapping("/track")
    public ResponseEntity<?> updateTrack(@RequestBody Track track){
        try{
            // update track
            trackService.saveTrack(track);
            responseEntity=new ResponseEntity<String>("Successfully updated", HttpStatus.OK);
        } catch (Exception e){
            responseEntity=new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        // return success message
        return responseEntity;
    }
}
