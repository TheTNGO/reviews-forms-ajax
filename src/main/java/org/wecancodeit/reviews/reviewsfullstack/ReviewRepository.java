package org.wecancodeit.reviews.reviewsfullstack;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.wecancodeit.reviews.reviewsfullstack.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long>  {

	Collection<Review> findAllByCategory(Category category);

	Review findByTitle(String reviewTitle);

	
}
