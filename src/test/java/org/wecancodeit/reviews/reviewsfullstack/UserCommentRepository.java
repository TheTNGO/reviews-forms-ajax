package org.wecancodeit.reviews.reviewsfullstack;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface UserCommentRepository extends CrudRepository<UserComment, Long> {

	Collection<UserComment> findByReviewContains(Review review);

}

