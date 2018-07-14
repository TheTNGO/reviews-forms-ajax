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

	@OneToMany(mappedBy = "category")
	private Collection<Review> reviews;

	private String name;

	private String imageUrl;

	public Category() {

	}


	public Category(String name, String imageUrl) {
		this.name = name;
		this.imageUrl = imageUrl;
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

	public String getImageUrl() {
		return imageUrl;
	}

}
