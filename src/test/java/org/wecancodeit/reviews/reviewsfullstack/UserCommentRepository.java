package org.wecancodeit.reviews.reviewsfullstack;

import org.springframework.data.repository.CrudRepository;

public interface UserCommentRepository extends CrudRepository<UserComment, Long> {

}
