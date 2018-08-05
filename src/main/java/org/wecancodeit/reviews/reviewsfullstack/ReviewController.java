package org.wecancodeit.reviews.reviewsfullstack;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wecancodeit.reviews.reviewsfullstack.Review;
import org.wecancodeit.reviews.reviewsfullstack.ReviewNotFoundException;

@Controller
public class ReviewController {

	@Resource
	ReviewRepository reviewRepo;

	@Resource
	CategoryRepository categoryRepo;

	@Resource
	UserCommentRepository userCommentRepo;

	/* All Reviews */
	@RequestMapping("/show-reviews")
	public String findAllReviews(Model model) {
		model.addAttribute("reviews", reviewRepo.findAll());
		model.addAttribute("categories", categoryRepo.findAll());

		return "reviews";
	}

	/* Single Review */
	// Access comments through ${review}
	@RequestMapping("/review")
	public String findOneReview(@RequestParam(value = "id") Long reviewId, Model model) throws ReviewNotFoundException {

		Optional<Review> review = reviewRepo.findById(reviewId);
		Review reviewOptionalResult = review.get();

		if (review.isPresent()) {
			model.addAttribute("review", reviewOptionalResult);
			return "review";
		}

		throw new ReviewNotFoundException();
	}

	/* All Categories */
	@RequestMapping("/show-categories")
	public String findAllCategories(Model model) {
		model.addAttribute("categories", categoryRepo.findAll());
		return "categories";
	}

	/* Single Category */
	@RequestMapping("/category")
	public String findOneCategory(@RequestParam(value = "id") Long categoryId, Model model)
			throws CategoryNotFoundException {

		Optional<Category> category = categoryRepo.findById(categoryId);

		if (category.isPresent()) {
			model.addAttribute("category", category.get());
			return "category";
		}

		throw new CategoryNotFoundException();
	}

	/* User Comments */

	@RequestMapping("/usercomment")
	public String findOneUserComment(@RequestParam(value = "id") long userCommentId, Model model)
			throws UserCommentNotFoundException {
		Optional<UserComment> userComment = userCommentRepo.findById(userCommentId);

		if (userComment.isPresent()) {
			model.addAttribute("usercomments", userComment.get());
			return "usercomment";
		}

		throw new UserCommentNotFoundException();
	}

	@RequestMapping("/show-usercomments")
	public String findAllUserComments(Model model) {

		model.addAttribute("usercomments", userCommentRepo.findAll());
		return "usercomments";

	}
	
	@RequestMapping("/add-comment")
	public String addUserComment(String commentUser, String commentContent, Long reviewId) {
				
		Optional<Review> reviewOptional = reviewRepo.findById(reviewId);
		Review review = reviewOptional.get();
					
		if(!reviewOptional.isPresent()) {
			review = new Review("");
			reviewRepo.save(review);
		}
				
		// How do I grab the current review's Id?
		
		UserComment newUserComment = new UserComment(commentUser, commentContent, review);
		userCommentRepo.save(newUserComment);
		return "redirect:/review?id=" + reviewId;
		
	}

}
