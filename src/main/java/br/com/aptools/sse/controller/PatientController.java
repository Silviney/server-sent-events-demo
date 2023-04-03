package br.com.aptools.sse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.aptools.sse.model.Patient;
import br.com.aptools.sse.service.PatientService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api")
public class PatientController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);
	
	private final PatientService processor;
	
	public PatientController(PatientService processor) {
        this.processor = processor;
    }
	
	  @PostMapping("/patient")
	    @ResponseStatus(HttpStatus.CREATED)
	    public Mono<Patient> send(@RequestBody Patient patient) {
	        LOGGER.info("Received '{}'", patient);
	        processor.publish(patient);
	        return Mono.just(patient);
	    }

	    @GetMapping(path = "/patient", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	    public Flux<ServerSentEvent<Object>> consumer() {
	        return Flux.create(sink -> processor.subscribe(sink::next)).map(
	        		patient -> ServerSentEvent.builder().data(patient).event("notification").build());
	    }
	
	
}
