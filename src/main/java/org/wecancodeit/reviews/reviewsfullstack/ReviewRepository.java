package org.wecancodeit.reviews.reviewsfullstack;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.wecancodeit.reviews.reviewsfullstack.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long>  {

	Collection<Review> findAllByCategory(Category category);

	Review findByTitle(String reviewTitle);

	Collection<Review> findByTagsId(Long tagId);  // this one should be how its done; finding correct conditional for REST controller
	
//	Review findByTagsId(Long tagId); // comes up with error, but is effective

	
}
