package org.wecancodeit.reviews.reviewsfullstack;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tags-json")
public class TagRestController {
	
	@Resource
	TagRepository tagRepo;
	
	@Resource
	ReviewRepository reviewRepo;
	
	@RequestMapping("")
	public Iterable<Tag> findAllTags(){
		return tagRepo.findAll();
	}
	
	// May not be necessary	
	@RequestMapping("/{id}")
	public Optional<Tag> findOneTag(@PathVariable long id){
		return tagRepo.findById(id);
	}

}
