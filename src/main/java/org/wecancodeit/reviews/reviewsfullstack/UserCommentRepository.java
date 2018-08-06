package org.wecancodeit.reviews.reviewsfullstack;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCommentRepository extends CrudRepository<UserComment, Long> {

	Collection<UserComment> findByReview(Review review);

	Collection<UserComment> findByReviewId(long id);

}

