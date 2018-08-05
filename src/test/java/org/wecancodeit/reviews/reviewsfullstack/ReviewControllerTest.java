package org.wecancodeit.reviews.reviewsfullstack;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.ui.Model;

public class ReviewControllerTest {

	@InjectMocks
	private ReviewController testController;

	@Mock
	private Model model;

	@Mock
	private Review review01;
	Long review01Id;

	@Mock
	private UserComment comment01;

	@Mock
	private UserComment comment02;

	@Mock
	private ReviewRepository reviewRepo;

	@Mock
	private UserCommentRepository commentRepo;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/* Tests */

	@Test
	public void shouldAddAllUserCommentsTomodel() {
		Collection<UserComment> allUserComments = Arrays.asList(comment01, comment02);
		when(commentRepo.findAll()).thenReturn(allUserComments);

		testController.findAllUserComments(model);
		verify(model).addAttribute("usercomments", allUserComments);
	}

	@Test
	public void shouldAddOneCommentToModel() throws UserCommentNotFoundException {

		long arbitraryCommentId = 1;
		when(commentRepo.findById(arbitraryCommentId)).thenReturn(Optional.of(comment01));

		testController.findOneUserComment(arbitraryCommentId, model);
		verify(model).addAttribute("usercomments", comment01);

	}

	@Test
	public void shouldAddAdditionalCoursesToModel() {
		
		Long arbitraryReviewId = 1L;
		String reviewName = "review name";

		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review01));

		String commentName = "new course";
		String commentDescription = "new course description";

		testController.addUserComment(commentName, commentDescription, arbitraryReviewId);

		UserComment newComment = new UserComment(commentName, commentDescription, review01);

		when(commentRepo.save(newComment)).thenReturn(newComment);
		

	}

}
