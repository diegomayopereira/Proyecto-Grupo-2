package com.chatbot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//Creación de la entity de Log que persiste en la base de datos la navegación del usuario
@Entity
@Table(name = "log")
public class Log {

	// Id autogenerado como PK
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	// Fecha del log
	@Column(name = "date")
	private Date date;

	// Detalles del log
	@Column(name = "details")
	private String Details;

	// Usuario del que se registra la navegación
	@Column(name = "username")
	private String username;

	// Url a la que se accede
	@Column(name = "url")
	private String url;

	// Constructores
	public Log() {
	}

	public Log(Date date, String details, String username, String url) {
		super();

		this.date = date;
		Details = details;
		this.username = username;
		this.url = url;
	}

	// Métodos Set y Get
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDetails() {
		return Details;
	}

	public void setDetails(String details) {
		Details = details;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}