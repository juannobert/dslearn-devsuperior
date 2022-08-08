package com.devsuperior.curso.dslearn.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.devsuperior.curso.dslearn.entities.enums.DeliverStatus;
@Entity
@Table(name = "tb_deliver")
public class Deliver implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String uri;
	
	private Instant moment;
	
	private DeliverStatus deliverStatus;
	
	private String feedback;
	
	private Integer CorrectCount;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "user_id"),
		@JoinColumn(name = "offer_id")
	})
	private Enrollment enrollment;
	
	@ManyToOne
	@JoinColumn(name = "lesson_id")
	private Lesson lesson;
	
	public Deliver() {
	}

	public Deliver(Long id, String uri, Instant moment, DeliverStatus deliverStatus, String feedback,
			Integer correctCount, Enrollment enrollment, Lesson lesson) {
		this.id = id;
		this.uri = uri;
		this.moment = moment;
		this.deliverStatus = deliverStatus;
		this.feedback = feedback;
		CorrectCount = correctCount;
		this.enrollment = enrollment;
		this.lesson = lesson;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public DeliverStatus getDeliverStatus() {
		return deliverStatus;
	}

	public void setDeliverStatus(DeliverStatus deliverStatus) {
		this.deliverStatus = deliverStatus;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Integer getCorrectCount() {
		return CorrectCount;
	}

	public void setCorrectCount(Integer correctCount) {
		CorrectCount = correctCount;
	}

	public Enrollment getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(Enrollment enrollment) {
		this.enrollment = enrollment;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deliver other = (Deliver) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
}
