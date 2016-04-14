package com.pwp.restapi.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="STRINGSETS")
public class Stringsets {

	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="stringset")
	@NotEmpty(message="Set must not be empty.")
	private String stringset;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStringset() {
		return stringset;
	}

	public void setStringset(String stringset) {
		this.stringset = stringset;
	}

	
	
}