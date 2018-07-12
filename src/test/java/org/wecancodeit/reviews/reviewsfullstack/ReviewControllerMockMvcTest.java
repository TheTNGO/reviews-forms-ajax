package org.wecancodeit.reviews.reviewsfullstack;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerMockMvcTest {

	@InjectMocks
	private ReviewController reviewController;

	@Resource
	private MockMvc mvc;

	@Mock
	private Review testReview1;

	@Mock
	private Review testReview2;

	@Mock
	private Category category;
	
	@Mock
	private Model model;
	
	
	@MockBean
	private ReviewRepository reviewRepo;
	
	@MockBean
	private CategoryRepository categoryRepo;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

	}

	/* All Reviews View Tests */

	@Test
	public void serverShouldBeOkForAllReviewsPage() throws Exception {
		mvc.perform(get("/show-reviews")).andExpect(status().isOk());
	}

	@Test
	public void shouldRouteToAllReviewssView() throws Exception {
		mvc.perform(get("/show-reviews")).andExpect(view().name(is("reviews")));
	}

	@Test
	public void shouldPutAllReviewsIntoModel() throws Exception {
		Collection<Review> allReviews = asList(testReview1, testReview2);
		when(reviewRepo.findAll()).thenReturn(allReviews);

		mvc.perform(get("/show-reviews")).andExpect(model().attribute("reviews", is(allReviews)));
	}

	/* Single Review Tests */

	@Test
	public void shouldBeOkForSingleReview() throws Exception {
		long arbitraryReviewId = 1;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(testReview1));
		mvc.perform(get("/review?id=1")).andExpect(status().isOk());
	}

	@Test
	public void shouldRouteToSingleReviewView() throws Exception {
		long arbritraryReviewId = 1L;
		when(reviewRepo.findById(arbritraryReviewId)).thenReturn(Optional.of(testReview1));

		mvc.perform(get("/review?id=1")).andExpect(view().name(is("review")));
	}

	@Test
	public void shouldPutASingleReviewIntoModel() throws Exception {
		long arbritraryReviewId = 1L;
		when(reviewRepo.findById(arbritraryReviewId)).thenReturn(Optional.of(testReview1));

		reviewController.findOneReview(arbritraryReviewId, model);

		verify(model).addAttribute("review", testReview1);

	}
	
	/* All Categories Tests */
	
	@Test
	public void serverShouldBeOkayForAllCategories() throws Exception {
		mvc.perform(get("/show-categories")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldRouteToAllCategoriesView() throws Exception {
		mvc.perform(get("/show-categories")).andExpect(view().name(is("categories")));
	}
	
	@Test
	public void shouldPutAllCategoriesIntoModel() throws Exception {
		Collection<Review> allCategories = asList(testReview1, testReview2);
		when(reviewRepo.findAll()).thenReturn(allCategories);

		mvc.perform(get("/show-reviews")).andExpect(model().attribute("reviews", is(allCategories)));
	}
	
	/* Single Category Tests */

	@Test
	public void shouldBeOkayForSingleCategory() throws Exception {
		long arbitraryCategoryId = 1;
		when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=1")).andExpect(status().isOk());
	}

	@Test
	public void shouldRouteToSingleCategoryView() throws Exception {
		long arbritraryCategoryId = 1L;
		when(categoryRepo.findById(arbritraryCategoryId)).thenReturn(Optional.of(category));

		mvc.perform(get("/category?id=1")).andExpect(view().name(is("category")));
	}

	@Test
	public void shouldPutASingleCategoryIntoModel() throws Exception {
		long arbritraryCategoryId = 1L;
		when(categoryRepo.findById(arbritraryCategoryId)).thenReturn(Optional.of(category));

		reviewController.findOneCategory(arbritraryCategoryId, model);

		verify(model).addAttribute("category", category);

	}
}
