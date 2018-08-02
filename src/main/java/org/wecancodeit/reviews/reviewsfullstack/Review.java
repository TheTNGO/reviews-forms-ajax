package org.wecancodeit.reviews.reviewsfullstack;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.junit.runners.ParentRunner;
import org.junit.runners.model.FrameworkMethod;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	private Category category;
	
	@OneToMany(mappedBy = "review")
	private Collection<UserComment> userComments;

	private String title;
	private String description;
	private String productName;

	@Lob
	private String content;

	private String date;
	private String imageUrl;

	/* Constructors */

	// JPA Constructor
	public Review() {
	}

	// Production Constructors
	public Review(	String title, 
					String description, 
					Category category, 
					String content, 
					String date, 
					String imageUrl	) {

		this.title = title;
		this.date = date;
		this.category = category;

		this.description = description;
		this.content = content;
		this.imageUrl = imageUrl;
	}

	public Review(String title, String description, String date) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.date = date;

	}

	// Testing Constructors
	
	public Review(String title, String description, Category category) {
		this.title = title;
		this.description = description;
		this.category = category;
	}

	public Review(String string, String string2, String string3, String string4, String string5) {
	}
	
	public Review(String title, UserComment...userComments) {
		this.title = title;
		this.userComments = new HashSet<>(Arrays.asList(userComments));
	}

	/* Accessors */

	public long getId() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public String getContent() {
		return this.content;
	}

	public String getDate() {
		return this.date;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public String getDescription() {
		return this.description;
	}

	public Category getCategory() {
		return category;
	}

	public String getProductName() {
		return productName;
	}
	
	public Collection<UserComment> getUserComments() {
		return userComments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		return true;
	}


	

}
