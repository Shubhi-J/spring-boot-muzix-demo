package com.stackroute.muzix;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {


    TrackService trackService;

    @Autowired
    public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }

    //Environment Variable is Accessed
    @Value("${spring.datasource.url}")
    String s;
    @Value("${seedTrack1.id}")
    int id1;
    @Value("${seedTrack1.name}")
    String name1;
    @Value("${seedTrack1.comment}")
    String comment;
    @Value("${seedTrack2.id}")
    int id2;
    @Value("${seedTrack2.name}")
    String name2;
    @Value("${seedTrack2.comment}")
    String comment2;

    @Override
    public void run(String... args) throws Exception {
        System.out.printf("Command Line Runner");
        Track track = new Track(id1,name1,comment);
        trackService.saveTrack(track);
        track = new Track();
        track.setId(id2);
        track.setFirstName(name2);
        track.setComment(comment2);
        trackService.saveTrack(track);
    }
}