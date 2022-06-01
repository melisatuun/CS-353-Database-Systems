package bilkent.dmgtv;

import bilkent.dmgtv.db.ReviewRequest;
import bilkent.dmgtv.dto.MovieDto;
import bilkent.dmgtv.dto.ReviewDto;
import bilkent.dmgtv.dto.UserDto;
import bilkent.dmgtv.repository.UserRepository;
import bilkent.dmgtv.service.MovieService;
import bilkent.dmgtv.service.ReviewService;
import bilkent.dmgtv.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
public class DmgtvApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmgtvApplication.class, args);
	}

//	@Bean
//	CommandLineRunner run(UserService userService,
//						  MovieService movieService,
//						  ReviewService reviewService,
//						  UserRepository userRepository) {
//		return args -> {
////			UserDto userDto1 = new UserDto("mertbarkıner", "password1",
////					"Mert Barkın Er", new Date(101,8,3));
////			UserDto userDto2 = new UserDto("gokberkbeydemir", "password2",
////					"Gökberk Beydemir", new Date(100,5,2));
////			UserDto userDto3 = new UserDto("dorukkantarcı", "password3",
////					"Doruk Kantarcı", new Date(101,6,12));
////			UserDto userDto4 = new UserDto("melisatun", "password4",
////					"Melis Atun", new Date(101,1,13));
////			UserDto userDto5 = new UserDto("erenpolat", "password5",
////					"Eren Polat", new Date(101,2,16));
////			UserDto userDto6 = new UserDto("berkeucar", "password6",
////					"Berke Ucar", new Date(101,9,18));
////			UserDto userDto7 = new UserDto("efeerturk", "password7",
////					"Efe Erturk", new Date(101,7,20));
////			UserDto userDto8 = new UserDto("emirmeliherdem", "password8",
////					"Emir Melih Erdem", new Date(101,4,7));
////			UserDto userDto9 = new UserDto("efebeydogan", "password9",
////					"Efe Beydogan", new Date(101,10,9));
////			UserDto userDto10 = new UserDto("ardaonal", "password10",
////					"Arda Onal", new Date(101,11,3));
////			userService.create(userDto1);
////			userService.create(userDto2);
////			userService.create(userDto3);
////			userService.create(userDto4);
////			userService.create(userDto5);
////			userService.create(userDto6);
////			userService.create(userDto7);
////			userService.create(userDto8);
////			userService.create(userDto9);
////			userService.create(userDto10);
////
////			MovieDto movieDto1 = new MovieDto("The Batman", 2022, 0,
////					10, 100, true, 8,0);
////			MovieDto movieDto2 = new MovieDto("Parasite", 2019, 0,
////					8, 80, false, 8.5,0);
////			MovieDto movieDto3 = new MovieDto("Begin Again", 2013, 0,
////					4, 40, false, 7.4,0);
////			MovieDto movieDto4 = new MovieDto("Begin Again", 2013, 0,
////					4, 40, false, 7.4,0);
////			MovieDto movieDto5 = new MovieDto("La La Land", 2016, 0,
////					7, 70, false, 8,0);
////			movieService.create(movieDto1);
////			movieService.create(movieDto2);
////			movieService.create(movieDto3);
////			movieService.create(movieDto4);
////			movieService.create(movieDto5);
////
////			ReviewDto reviewDto1 = new ReviewDto(userDto1, movieDto1, 4, "Great movie");
////			ReviewDto reviewDto2 = new ReviewDto(userDto2, movieDto1, 5, "Best movie of the year");
////			reviewService.create(reviewDto1);
////			reviewService.create(reviewDto2);
//
//		};
//	}
}
