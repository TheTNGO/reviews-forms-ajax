package org.wecancodeit.reviews.reviewsfullstack;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReviewsPopulator implements CommandLineRunner {

	@Resource
	private ReviewRepository reviewRepo;

	@Resource
	private CategoryRepository categoryRepo;
	
	@Resource
	private TagRepository tagRepo;

	@Override
	public void run(String... args) throws Exception {
		

		Category mice = categoryRepo.save(new Category("Mice", "generic-mouse.png"));
		Category keyboards = categoryRepo.save(new Category("Keyboards", "generic-keyboard.png"));
		
		categoryRepo.save(mice);
		categoryRepo.save(keyboards);

		Review logitechG303 = new Review("Logitech G303", "Perfectly Niche", mice,
				"Very nice ambidextrous mouse. Sensor is very accurate. Diamond shape may not be for everyone ergonimically, but fits perfectly for me. Very reliable. Test it before committing to purchase, otherwise recommended.",
				"3/15/2016", "g303.jpg");

		Review logitechGPro = new Review("Logitech G Pro", "Great mouse for new shoppers", mice,
				"Great mouse overall. Love the LED color options availble. Great shape that is nice for smaller hands, although hard to lift up at times with low sensitivities. Still comes highly recomended",
				"3/15/2016", "gpro.jpg");

		Review corsairK65 = new Review("Corsair K65", "Great space saving mechanical keyboard.", keyboards,
				"If you're looking for a space saving mechanical keyboard with slick looks, this one is for you. Cherry MX Red switches feel great, although Shift key is a bit hard to use at times. Brushed metal frame and great build quality go hand in hand. Current daily-driver gaming keyboard.",
				"3/15/2016", "k65.jpg");

		Review quickfireRapid = new Review("CM Storm Quickfire Rapid", "Good mech keyboard for first time buyers.",
				keyboards,
				"Pretty standard Cherry Red mech board. All switches feel great, courtesy of Cherry MX. Ten-Keyless design is great for low mouse sensitivity gamers. Keycaps and switches are very easy to switch out due to standard size. Two thumbs up.",
				"3/15/2016", "quickfirerapid.jpg");
		
		reviewRepo.save(logitechG303);
		reviewRepo.save(logitechGPro);
		reviewRepo.save(corsairK65);
		reviewRepo.save(quickfireRapid);
		
		Tag logitech = new Tag("Logitech", logitechG303, logitechGPro);
		Tag corsair = new Tag("Corsair", corsairK65);
		Tag cmStorm = new Tag("CM Storm", quickfireRapid);
		Tag keyboard = new Tag("keyboard", corsairK65, quickfireRapid);
		
		tagRepo.save(logitech);
		tagRepo.save(corsair);
		tagRepo.save(cmStorm);
		tagRepo.save(keyboard);


	}

}
