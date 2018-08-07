package org.wecancodeit.reviews.reviewsfullstack;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	Category findByReviews(Review review);

}
