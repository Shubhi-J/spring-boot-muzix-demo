package com.stackroute.muzix;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MuzixApplication extends SpringBootServletInitializer implements CommandLineRunner {

	TrackService trackService;

	@Autowired
	public void setTrackService(TrackService trackService) {
		this.trackService = trackService;
	}

	// for command runner
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MuzixApplication.class);
	}

	// main method to run application
	public static void main(String[] args) {
		SpringApplication.run(MuzixApplication.class, args);
	}

	// save info
	@Override
	public void run(String... args) throws Exception {
		// logger.info("Application Started (command runner)!!");
		trackService.saveTrack(new Track(1,"piya","my favorite song"));
	}
}
