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
	
	
	/* All Reviews */
	@RequestMapping("/show-reviews")
	public String findAllReviews(Model model) { 
		model.addAttribute("reviews", reviewRepo.findAll());
		model.addAttribute("categories", categoryRepo.findAll());
		
		return "reviews";
	}
	
	/* Single Review */
	@RequestMapping("/review")
	public String findOneReview(@RequestParam(value="id")Long reviewId, Model model) throws ReviewNotFoundException {
		
		Optional<Review> review = reviewRepo.findById(reviewId);
		
		if(review.isPresent()) {
			model.addAttribute("review", review.get());
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
	public String findOneCategory(@RequestParam(value="id")Long categoryId, Model model) throws CategoryNotFoundException {
		
		Optional<Category> category = categoryRepo.findById(categoryId);
		
		if(category.isPresent()) {
			model.addAttribute("category", category.get());
			return "category";
		}
		
		throw new CategoryNotFoundException();
	}
	
	

}
