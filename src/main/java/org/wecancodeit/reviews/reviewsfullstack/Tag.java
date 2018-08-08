package org.wecancodeit.reviews.reviewsfullstack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import static java.lang.String.format;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tag {

	/* DB Related Fields */

	@Id
	@GeneratedValue
	private long id;

	// Foreign Keys

	@JsonIgnore
	@ManyToMany
	private Collection<Review> reviews;

	private String name;

	/* Constructors */

	// Production

	public Tag(String name, Review... reviews) {
		this.name = name;
		this.reviews = new HashSet<>(Arrays.asList(reviews));
	}

	// JPA Constructor
	public Tag() {
	}

	/* Accessors */

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Collection<Review> getReviews() {
		return reviews;
	}

	public Collection<String> getReviewsUrls() {
		Collection<String> urls = new ArrayList<>();

		for (Review t : reviews) {
			urls.add(format("/review?id=%d", t.getId()));
		}

		return urls;

	}

	public String getTagUrl() {
		String url = format("/tag?id=%d", this.getId());
		return url;

	}
	
	public void addReview(Review review) {
		reviews.add(review);
	}

}
