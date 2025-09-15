package com.fawry.movieapp.service.movie;
import com.fawry.movieapp.exception.movie.MovieNotFoundException;
import com.fawry.movieapp.model.dto.request.MovieRequest;
import com.fawry.movieapp.model.entity.Movie;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.client.RestTemplate;
import com.fawry.movieapp.mapper.MovieMapper;
import com.fawry.movieapp.model.dto.response.MovieResponse;
import com.fawry.movieapp.repository.movie.MovieRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovieServiceImp implements MovieService {

    @Value("${omdb.api.url}")
    private  String API_URL;

    @Value("${omdb.api.key}")
    private  String API_KEY;

    private final MovieRepository movieRepository;

    private final RestTemplate restTemplate;
    public MovieServiceImp(MovieRepository movieRepository ,RestTemplate restTemplate) {
        this.movieRepository = movieRepository;
        this.restTemplate =    restTemplate;
    }

    //to be tested later also
    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(MovieMapper::toResponseDto)
                .toList();
    }

    @Override
    public Object getMovies(String title) {
        String url = String.format("%s/?s=%s&apikey=%s", API_URL, title, API_KEY);
        return restTemplate.getForEntity(url,Object.class);
    }

    @Override
    public MovieResponse getMovieData(int id) {
       return MovieMapper.toResponseDto(movieRepository.findById(id)
               .orElseThrow(() -> new MovieNotFoundException("Movie with ID " + id + " not found")));
    }

    @Override
    public Page<MovieResponse> getAllMoviesPaged(int page, int size) {

        return movieRepository.findAll(PageRequest.of(page, size)).
                map(MovieMapper::toResponseDto);
    }

    @Override
    public List<Movie> searchMovies(String title) {
         return movieRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    @Transactional
    public MovieResponse addMovie(MovieRequest movieRequest) {
       Movie movie = MovieMapper.toEntity(movieRequest);
       Movie savedMovie = movieRepository.save(movie);
       return MovieMapper.toResponseDto(savedMovie);
    }

    @Override
    @Transactional
    public void deleteMovieById(int id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() ->
                        new MovieNotFoundException("Movie with id " + id + " not found"));
        movieRepository.delete(movie);
    }

    @Override
    @Transactional
    public List<MovieResponse> addMovies(List<MovieRequest> movieRequests) {
        List<Movie>moviesList = movieRequests.stream().
                map(MovieMapper::toEntity)
                .toList();

      return movieRepository.saveAll(moviesList).stream()
              .map(MovieMapper::toResponseDto).toList();
    }

    @Override
    @Transactional
    public void deleteMoviesByIds (List<Integer> moivesIds)
    {
        List<Movie> moviesToDelete = movieRepository.findAllById(moivesIds);

        if (moviesToDelete.isEmpty()) {
            throw new MovieNotFoundException("No movies found for the given IDs");
        }

        movieRepository.deleteAll(moviesToDelete);
    }


}
