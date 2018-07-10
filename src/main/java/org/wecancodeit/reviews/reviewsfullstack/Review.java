package org.wecancodeit.reviews.reviewsfullstack;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.junit.runners.ParentRunner;
import org.junit.runners.model.FrameworkMethod;

@Entity
public class Review {

	
	public Review() {
		
	}
	
	public Review(long id, String title, String description, Category category, String content, String date,
			String imageUrl) {
		this.id = id;
		this.title = title;
		this.date = date;
		this.category = category;

		this.description = description;
		this.content = content;
		this.imageUrl = imageUrl;
	}

	public Review(long id, String title, String description, String date) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.date = date;

	}
	
	// Conversion Constructor
	public Review(String title, String description, Category category) {
		this.title = title;
		this.description = description;
		this.category = category;
	}

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	private Category category;

	private String title;
	private String description;

	private String content;
	private String date;
	private String imageUrl;

	public long getId() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

//	public String getCategory() {
//		return this.category;
//	}

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

}
