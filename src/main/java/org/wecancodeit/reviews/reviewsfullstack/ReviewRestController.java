package org.wecancodeit.reviews.reviewsfullstack;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews-json")
public class ReviewRestController {

	@Resource
	TagRepository tagRepo;

	@Resource
	ReviewRepository reviewRepo;

	@RequestMapping("")
	public Iterable<Review> findAllReviews() {
		return reviewRepo.findAll();
	}

	// May not be necessary
	@RequestMapping("/{id}")
	public Optional<Review> findOneReview(@PathVariable long id) {
		return reviewRepo.findById(id);
	}

	@PostMapping("/{id}/add-tag")
	public void addTagToReview(@PathVariable(value = "id") long reviewId, @RequestParam(value = "name") String name) {

		Optional<Review> reviewOptional = reviewRepo.findById(reviewId);
		Review review = reviewOptional.get();

		Tag tagToAdd = new Tag(name, review);
		tagRepo.save(tagToAdd);

	}

}
