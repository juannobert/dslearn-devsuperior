package com.devsuperior.curso.dslearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.curso.dslearn.entities.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
