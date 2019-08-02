package com.stackroute.muzix.repository;

import com.stackroute.muzix.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TrackRepositoryTest {

    // autowire the trackRepository dependency
    @Autowired
    TrackRepository trackRepository;
    Track track;

    // setup method
    @Before
    public void setup(){
        track=new Track();
        // set properties to track
        track.setId(101);
        track.setName("jeena jeena");
        track.setComment("song");
    }

    // tearDown method
    @After
    public void tearDown(){
        trackRepository.deleteAll();
    }

    // test for saveTrack method
    @Test
    public void saveTrack() {
        // invoke save method of track repository
        trackRepository.save(track);
        // fetch the track of id, which is inserted
        Track fetchTrack = trackRepository.findById(track.getId()).get();
        // if id of both are same, then the test will succeed
        Assert.assertEquals(101,fetchTrack.getId());
    }

    // test for saveTrackFailure
    @Test
    public void testSaveTrackFailure(){
        Track testTrack = new Track(102,"john","Harry123");
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getId()).get();
        Assert.assertNotSame(testTrack,track);
    }

    // test to get all tracks
    @Test
    public void getAllTracks(){
        // save the first track
        trackRepository.save(track);
        Track testTrack = new Track(102,"john","Harry123");
        // save the second track
        trackRepository.save(testTrack);
        // calculate the number of tracks
        int testTrackSize= trackRepository.findAll().size();
        // if number of tracks is 2, then test will be passed
        Assert.assertEquals(2,testTrackSize);
    }

    // test to get a track of given id
    @Test
    public void getTrackById(){
        // save the track into the database
        trackRepository.save(track);
        Optional<Track> testTrack=trackRepository.findById(track.getId());
        // check if the fetched data is same as the inserted data
        Assert.assertEquals(track,testTrack.get());
    }

    // test to update a record
    @Test
    public void updateTrack(){
        // save a track
        trackRepository.save(track);
        // make changes in track of the same id
        trackRepository.save(new Track(101,"channa mereya","sad song"));
        Optional<Track> testTrack=trackRepository.findById(track.getId());
        // the name in updated record should not be same as the previous record
        Assert.assertNotEquals(track.getName(),testTrack.get().getName());
    }

    // test for delete method
    @Test
    public void deleteTrack(){
        // save a record
        trackRepository.save(track);
        // delete a record of given id
        trackRepository.deleteById(track.getId());
        Optional<Track> testTrack=trackRepository.findById(track.getId());
        // check that after deleting that record, the table should b empty
        Assert.assertEquals(Optional.empty(),testTrack);
    }
}





