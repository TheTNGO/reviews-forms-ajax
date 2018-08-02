package org.wecancodeit.reviews.reviewsfullstack;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class UserComment {
	
	/* Fields */
	
	@Id
	@GeneratedValue
	private long id;
	
	/* Foreign Keys */
	
	@ManyToOne
	private Review review;
	
	
	/* Columns */
	
	private String user;
	
	@Lob
	private String content;
	
	/* Constructors */
	
	//JPA Constructor
	public UserComment() {}

	// Production Constructors
	public UserComment(String user, String content, Review review) {
		this.user = user;
		this.content = content;
		this.review = review;
	}
	
	
	// Test Constructors
	public UserComment(String user, String content) {
		this.user = user;
		this.content = content;
	}
	
	
	/* Accessors */
	
	public long getId() {
		return this.id;
	}
	
	public String getUser() {
		return this.user;
	}
	
	public String getContent() {
		return this.content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((review == null) ? 0 : review.hashCode());
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
		UserComment other = (UserComment) obj;
		if (review == null) {
			if (other.review != null)
				return false;
		} else if (!review.equals(other.review))
			return false;
		return true;
	}

	
	
	

}
