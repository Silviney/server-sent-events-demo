package br.com.aptools.sse.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.aptools.sse.model.Patient;

@Service
public class PatientService {

	 private static final Logger LOGGER = LoggerFactory.getLogger(PatientService.class);
	 
	 private final List<Consumer<Patient>> listeners = new CopyOnWriteArrayList<>();


	    public void subscribe(Consumer<Patient> listener) {
	        listeners.add(listener);
	        LOGGER.info("New one added, total consumer: {}", listeners.size());
	    }

	    public void publish(Patient patient) {
	        LOGGER.info("Processing patient: {}", patient);
	        listeners.forEach(listener -> listener.accept(patient));
	    }
	 
}
