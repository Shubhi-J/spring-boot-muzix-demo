package com.stackroute.muzix.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exception.GlobalExceptionHandler;
import com.stackroute.muzix.exception.TrackAlreadyExistException;
import com.stackroute.muzix.exception.TrackNotFoundException;
import com.stackroute.muzix.service.TrackService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TrackControllerTest {

    // autowire the MockMvc class
    @Autowired
    MockMvc mockMvc;
    Track track;

    // mocking the TrackService for test
    @MockBean
    TrackService trackService;

    // injecting the mock in TrackController for test
    @InjectMocks
    TrackController trackController;

    List<Track> list;

    // setup method
    @Before
    public void setup(){
        // initialize the mock
        MockitoAnnotations.initMocks(this);
        mockMvc= MockMvcBuilders.standaloneSetup(trackController).setControllerAdvice(GlobalExceptionHandler.class).build();
        track=new Track();
        // set properties of Track
        track.setId(2);
        track.setName("jeena jeena");
        track.setComment("song");
        ArrayList<Track> trackList=new ArrayList<Track>();
        trackList.add(track);
    }

    // test for save track success
    @Test
    public void saveTrack() throws Exception{
        // when we call the save method of service, it will return track
        when(trackService.saveTrack(any())).thenReturn(track);
        // post the data on given endpoint to save
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                // the content type of post data is json
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                // the status returned should be isCreated
                .andExpect(MockMvcResultMatchers.status().isCreated())
                // print
                .andDo(MockMvcResultHandlers.print());
    }

    // test for saveTrack failure
    @Test
    public void saveTrackFailure() throws Exception{
        // when there is a track already exist, it will throw the exception
        when(trackService.saveTrack(any())).thenThrow(TrackAlreadyExistException.class);
        // post data on given url
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
        // content type of data is json
        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
        // the status should be returned as isConflict
        .andExpect(MockMvcResultMatchers.status().isConflict())
        // print
        .andDo(MockMvcResultHandlers.print());
        }

    // test for get all tracks
    @Test
    public void getAllTracks() throws Exception{
        // save the track
        trackService.saveTrack(any());
        // after saving a track, service will return a list of tracks
        when(trackService.getAllTracks()).thenReturn(list);
        // get the data from the given endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track"))
        // the status should be isOk
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    // test for getting a track by given id
    @Test
    public void getTrackByIdSuccess() throws Exception {
        // when service method is called, it will return a track for that id
        when(trackService.getTrackById(anyInt())).thenReturn(track);
        // get the data from the given endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/1"))
        // the status should be isOk
        .andExpect(MockMvcResultMatchers.status().isOk());
        // verify that getTrackById is invoked only once
        verify(trackService,times(1)).getTrackById(anyInt());
    }

    // test for updating a track
    @Test
    public void updateTrack() throws Exception {
        // when service method for update is invoked, then return null
        when(trackService.updateTrack(any())).thenReturn(null);
        // get the data from the given endpoint
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk());
        // verify that updateTrack is invoked only once
        verify(trackService,times(1)).updateTrack(any());
    }

    // test for deleting a track
    @Test
    public void deleteTrack() throws Exception{
     // perform delete for the given endpoint
     mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/track/1"))
     .andExpect(MockMvcResultMatchers.status().isOk());
     // deleteTrack of service should be invoked only once
     verify(trackService,times(1)).deleteTrack(anyInt());
    }

    // convert object as jsonString
    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
