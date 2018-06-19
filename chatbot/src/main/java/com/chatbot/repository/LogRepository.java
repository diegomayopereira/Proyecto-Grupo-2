package com.chatbot.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatbot.entity.Log;

// Creación del Repositorio para que los logs persisten en la BD a través de Jpa
@Repository("logRepository")
public interface LogRepository extends JpaRepository<Log, Serializable> {

}
