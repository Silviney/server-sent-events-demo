package br.com.aptools.sse.model;

import java.time.LocalDateTime;

public class Patient {

	private String name;
	private String medicalSpecialty;
	private LocalDateTime updateDate = LocalDateTime.now();
	
	public Patient() {
	}

	public Patient(String name, String medicalSpecialty, LocalDateTime updateDate) {
		this.name = name;
		this.medicalSpecialty = medicalSpecialty;
		this.updateDate = updateDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMedicalSpecialty() {
		return medicalSpecialty;
	}

	public void setMedicalSpecialty(String medicalSpecialty) {
		this.medicalSpecialty = medicalSpecialty;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "Patient [name=" + name + ", medicalSpecialty=" + medicalSpecialty + ", updateDate=" + updateDate + "]";
	}
	
}
