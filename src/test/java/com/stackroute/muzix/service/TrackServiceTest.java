package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exception.TrackAlreadyExistException;
import com.stackroute.muzix.exception.TrackNotFoundException;
import com.stackroute.muzix.repository.TrackRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.exceptions.ExceptionIncludingMockitoWarnings;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class TrackServiceTest {
    Track track;

    //Create a mock for TrackRepository
    @Mock
    TrackRepository trackRepository;

    //Inject the mocks as dependencies into TrackServiceImpl
    @InjectMocks
    TrackServiceImpl trackService;
    List<Track> list= null;

    // setup method
    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setId(101);
        // set the properties of a track
        track.setName("Jenny");
        track.setComment("song from ajab prem ki gajab kahani");
        list = new ArrayList<>();
        list.add(track);
    }

    // test for saveTrack
    @Test
    public void saveTrackTestSuccess() throws TrackAlreadyExistException {
        // when the repository method is invoked for saveTrack, it should return a track
        when(trackRepository.save((Track) any())).thenReturn(track);
        Track savedTrack = trackService.saveTrack(track);
        Assert.assertEquals(track,savedTrack);

        //verify here verifies that userRepository save method is only called once
        verify(trackRepository,times(1)).save(track);

    }

    // test for saveTrack when a track already exists
    @Test(expected = TrackAlreadyExistException.class)
    public void saveTrackTestFailure() throws TrackAlreadyExistException {
        // when the repository method is invoked for saveTrack, it should return null when there is an exceptionn
        when(trackRepository.save((Track) any())).thenReturn(null);

        Track savedTrack = trackService.saveTrack(track);

        System.out.println("savedTrack" + savedTrack);
        Assert.assertEquals(track,savedTrack);

       /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
       userService.saveUser(user);*/
    }

    // test for getting the list of all tracks
    @Test
    public void getAllTracks(){
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> tracklist = trackService.getAllTracks();
        // check if the supplied list of tracks and get tracks are same
        Assert.assertEquals(list,tracklist);
    }

    // test for getting a track by id
    @Test
    public void getTrackByIdSuccess() throws TrackNotFoundException {
        //stubbing the mock to return a track when findById method is invoked
        when(trackRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(track));
        Track getTrack=trackService.getTrackById(track.getId());
        Assert.assertEquals(track,getTrack);
        // verify that the repository method is invoked only once
        verify(trackRepository,times(1)).findById(anyInt());
    }

    // test for updating a track
    @Test
    public void updateTrack() throws Exception{
        //stubbing the mock to return a track when save method is invoked
        when(trackRepository.save(any())).thenReturn(track);
        Track savedTrack= trackService.updateTrack(track);
        Assert.assertEquals(track,savedTrack);
        // verify that the repository method is invoked only once
        verify(trackRepository,times(1)).save(track);
    }

    // test for deleting a track
    @Test
    public void deleteTrack() throws Exception {
        //stubbing the mock to return a track when findById method is invoked
        when(trackRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(track));
        trackService.deleteTrack(track.getId());
        // verify that the repository method is invoked only once
        verify(trackRepository,times(1)).deleteById(track.getId());
    }
}
