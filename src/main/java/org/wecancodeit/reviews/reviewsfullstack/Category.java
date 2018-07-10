package org.wecancodeit.reviews.reviewsfullstack;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {

	@Id
	@GeneratedValue
	private long id;
	
	@OneToMany(mappedBy="category")
	private Collection<Review> reviews;
	
	private String name;
	
	public Category() {
		
	}

	public Category(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public Collection<Review> getReviews() {
		return reviews;
	}

}
