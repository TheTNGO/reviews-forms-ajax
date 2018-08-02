package org.wecancodeit.reviews.reviewsfullstack;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.Comment;
import org.wecancodeit.reviews.reviewsfullstack.Category;
import org.wecancodeit.reviews.reviewsfullstack.Review;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JPAMappingsTest {

	@Resource
	private ReviewRepository reviewRepo;

	@Resource
	private CategoryRepository categoryRepo;

	@Resource
	private UserCommentRepository userCommentRepo;

	@Resource
	private EntityManager entityManager;

	@Test
	public void shouldSaveAndLoadReviewInRepo() {
		Category category = categoryRepo.save(new Category("Name", "Test"));
		Review review = reviewRepo.save(new Review("Title", "Description", category));
		long reviewId = review.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Review> resultOptional = reviewRepo.findById(reviewId);
		Review result = resultOptional.get();
		assertThat(result.getTitle(), is("Title"));

	}

	@Test
	public void shouldSaveAndLoadCategoryInRepo() {
		Category category = categoryRepo.save(new Category("Cat Name", "Test"));
		long categoryId = category.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Category> resultOptional = categoryRepo.findById(categoryId);
		Category result = resultOptional.get();

		assertThat(result.getName(), is("Cat Name"));
	}

	@Test
	public void shouldEstablishReviewCategoryRelationship() {

		Category category = categoryRepo.save(new Category("Mice", "Test"));
		Review review1 = reviewRepo.save(new Review("Review 1", "Description", category));
		Review review2 = reviewRepo.save(new Review("Review 2", "Description", category));

		long categoryId = category.getId();
		long review1Id = review1.getId();
		long review2Id = review2.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Category> categoryOptional = categoryRepo.findById(categoryId);
		Optional<Review> reviewOptional1 = reviewRepo.findById(review1Id);
		Optional<Review> reviewOptional2 = reviewRepo.findById(review2Id);
		Category resultCategory = categoryOptional.get();
		Review resultReview1 = reviewOptional1.get();
		Review resultReview2 = reviewOptional2.get();

//		assertThat(resultReview1.getCategory().getName(), is("Mice"));
		assertThat(resultCategory.getReviews(), containsInAnyOrder(resultReview2, resultReview1));

	}

	/* Comments Tests */

	@Test
	public void shouldSaveAndLoadCommentInRepo() {

		UserComment comment = userCommentRepo.save(new UserComment("user", "comment"));
		long commentId = comment.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<UserComment> resultOptional = userCommentRepo.findById(commentId);
		comment = resultOptional.get();

		assertThat(comment.getUser(), is("user"));

	}

	@Test
	// Comment-Review: Many-To-One
	// Review-Comment: One-To-Many
	public void shouldEstablishReviewUserCommentRelationship() {
		
		Review review = reviewRepo.save(new Review("Review 1"));

		UserComment comment1 = userCommentRepo.save(new UserComment("user1", "comment", review));
		UserComment comment2 = userCommentRepo.save(new UserComment("user2", "comment", review));

		

		long comment1Id = comment1.getId();
		long comment2Id = comment2.getId();
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> reviewOptional = reviewRepo.findById(reviewId);
		Optional<UserComment> comment1Optional = userCommentRepo.findById(comment1Id);
		Optional<UserComment> comment2Optional = userCommentRepo.findById(comment2Id);
		
		Review reviewResult = reviewOptional.get();
		UserComment comment1Result = comment1Optional.get();
		UserComment comment2Result = comment2Optional.get();
		
		long reviewResultId = reviewResult.getId();	
		
		
		assertThat(comment1Result.getReview().getId(), is(reviewResultId));
//		assertThat(comment1.getId(), is(comment1Result.getId()));
		assertThat(reviewResult.getUserComments(), containsInAnyOrder(comment1, comment2));

		


	}

}
