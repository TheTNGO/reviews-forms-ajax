package org.wecancodeit.reviews.reviewsfullstack;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag {
	
	/* DB Related Fields */
	
	@Id
	@GeneratedValue
	private long id;
	
	// Foreign Keys
	
	@ManyToMany
	private Collection<Review> reviews;
	
	private String name;
	
	/* Constructors */
	
	// Production
	
	public Tag(String name, Review...reviews) {
		this.name = name;
		this.reviews = new HashSet<>(Arrays.asList(reviews));
		
	}
	
}
