package io.dpenKdcha.movieinfoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.dpenKdcha.movieinfoservice.config.RestBuilder;
import io.dpenKdcha.movieinfoservice.model.MovieDto;
import io.dpenKdcha.movieinfoservice.model.MovieSummaryDto;

@RestController()
@RequestMapping("/api/v1")
public class MovieInfoController {
	
	@Autowired
	private RestBuilder restBuilder;
	
	@Value("$(api.key)")
	private String apiKey;
	
	@PostMapping("/movies")
	public MovieDto getMovieInfo(@RequestBody MovieDto movie) {
		return new MovieDto(movie.getMovieId(), "abc", "xyz");
		
	}

	@PostMapping("/movieDB")
	public MovieDto getMovieDB(@RequestBody String movieId) {
		MovieSummaryDto movieDB = restBuilder.getWebClientBuilder()
				.get()
				.uri("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=3e4b6bd39ec2511270bf56bdf8cd9186")
				.retrieve()
				.bodyToMono(MovieSummaryDto.class)
				.block();

		return new MovieDto(movieDB.getId(), movieDB.getTitle(), movieDB.getOverview());
	}
}
