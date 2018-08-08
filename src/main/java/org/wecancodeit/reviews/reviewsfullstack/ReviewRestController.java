package org.wecancodeit.reviews.reviewsfullstack;

import java.util.Collection;
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
	public String addTagToReview(@PathVariable(value = "id") long reviewId, @RequestParam(value = "name") String name) {

		Optional<Review> reviewOptional = reviewRepo.findById(reviewId);
		Review review = reviewOptional.get();

		Tag tagSearchResult = tagRepo.findByNameIgnoreCaseLike(name);

		if (!(tagSearchResult == null)) {

			Collection<Review> reviewSearchResults = reviewRepo.findByTagsId(tagSearchResult.getId());
			for (Review result : reviewSearchResults) {
				if (result.equals(review)) {
					return "Tag found. Review found. Doing nothing (supposedly).";
				}
			}
			
			tagSearchResult.addReview(review);
			tagRepo.save(tagSearchResult);
			return "Tag found. Review NOT found. Adding review to Tag. Tag Saved.";

		} else {
			tagSearchResult = new Tag(name, review);
			tagRepo.save(tagSearchResult);
			return "Tag NOT found. Creating new tag and adding review to it. Tag Saved.";
		}

	}

}
