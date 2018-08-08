package org.wecancodeit.reviews.reviewsfullstack;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {

	Tag findByNameIgnoreCaseLike(String name);

	Tag findByReviewsId(long id);

}
