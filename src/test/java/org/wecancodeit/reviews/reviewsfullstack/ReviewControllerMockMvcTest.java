package org.wecancodeit.reviews.reviewsfullstack;


import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;


import java.util.Collection;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.wecancodeit.reviews.reviewsfullstack.Review;
import org.wecancodeit.reviews.reviewsfullstack.ReviewController;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerMockMvcTest {
	
	@Resource
	private MockMvc mvc;
	
	@Mock
	private Review testReview1;

	@Mock
	private Review testReview2;

	@MockBean
	private ReviewRepository repository;
	
	
	/* All Reviews View Tests */
	
	@Test
	public void serverShouldBeOkForAllReviewsPage() throws Exception {
		mvc.perform(get("/show-reviews")).andExpect(status().isOk());
	}
	
	@Test 
	public void shouldRouteToAllCoursesView() throws Exception {
		mvc.perform(get("/show-reviews")).andExpect(view().name(is("reviews")));
	}
	
	@Test
	public void shouldPutAllCoursesIntoModel() throws Exception {
		Collection<Review> allReviews = asList(testReview1, testReview2);
		when(repository.findAll()).thenReturn(allReviews);
		
		mvc.perform(get("/show-reviews")).andExpect(model().attribute("reviews", is(allReviews)));
	}
	
	/* Single Review Tests */
	
	@Test
	public void shouldBeOkForSingleCourse() throws Exception{
		mvc.perform(get("/review?id=1")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldRouteToSingleCoursesView() throws Exception{
		mvc.perform(get("/review?id=1")).andExpect(view().name(is("review")));
	}
	
	@Test
	public void shouldPutASingleCourseIntoModel() throws Exception{
		when(repository.findOne(1L)).thenReturn(testReview1);
		
		mvc.perform(get("/review?id=1")).andExpect(model().attribute("reviews",  is(testReview1)));
	}
	
	


}
