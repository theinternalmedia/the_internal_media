package com.tim.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "news")
public class News extends NewsAndNotify{

	private static final long serialVersionUID = -4407989052998829719L;

}
