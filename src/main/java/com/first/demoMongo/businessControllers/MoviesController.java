package com.first.demoMongo.businessControllers;

import com.first.demoMongo.documents.Movie;
import com.first.demoMongo.documents.MovieLinks;
import com.first.demoMongo.dtos.MovieInputDto;
import com.first.demoMongo.dtos.MovieMinimumOutputDto;
import com.first.demoMongo.dtos.MovieOutputDto;
import com.first.demoMongo.dtos.QueryMovieInputDto;
import com.first.demoMongo.exceptions.NotFoundException;
import com.first.demoMongo.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MoviesController {

    @Autowired
    private MovieRepository movieRepository;

    public MovieOutputDto getMovieById(String id) throws NotFoundException{
      return new MovieOutputDto(this.movieRepository.findById(id).orElseThrow(
                () -> new NotFoundException("No Film Found With the given ID:"+id)
        ));
    }

    public Page<Movie> getPage(int page, int size, String key, Sort.Direction dir) {
        return this.movieRepository.findAll(PageRequest.of(page, size, dir, key));
    }

    public List<MovieOutputDto> getMoviesByName(String name) throws NotFoundException {
        return this.movieRepository.findBynameContaining(name).orElseThrow(() -> new NotFoundException("No Film Found With The Given name:" + name));
    }

    public List<String[]> getByName(String name) throws NotFoundException {
        int page = 0;
        int size = 5;
        List<String[]> names = new ArrayList<>();
        this.movieRepository.findBynameContaining(name, PageRequest.of(page, size))
                .ifPresent(movies -> movies.forEach(
                        movie -> names.add(movie.getName())
                        )
                );
        if (names.isEmpty()) {
            throw new NotFoundException("no movie found with name: " + name);
        }
        return names;
    }

    public Page<MovieMinimumOutputDto> getMoviesByQueryDto(QueryMovieInputDto queryMovieInputDto, int page, int size) throws NotFoundException {
        Page<MovieMinimumOutputDto> movieMinimumOutputDtosPage;
        if (queryMovieInputDto.getGenre().length == 0) {
            movieMinimumOutputDtosPage = this.movieRepository.findByfiltersExceptGenre(
                    queryMovieInputDto.getArtMovement(),
                    queryMovieInputDto.getCountry(),
                    queryMovieInputDto.getLanguage(),
                    queryMovieInputDto.getMinRuntime(),
                    queryMovieInputDto.getMaxRuntime(),
                    queryMovieInputDto.getColor(),
                    queryMovieInputDto.getSound(),
                    queryMovieInputDto.getStartDate(),
                    queryMovieInputDto.getEndDate(), PageRequest.of(page, size));
        } else {
            movieMinimumOutputDtosPage = this.movieRepository.findByfilters(
                    queryMovieInputDto.getArtMovement(),
                    queryMovieInputDto.getGenre(),
                    queryMovieInputDto.getCountry(),
                    queryMovieInputDto.getLanguage(),
                    queryMovieInputDto.getMinRuntime(),
                    queryMovieInputDto.getMaxRuntime(),
                    queryMovieInputDto.getColor(),
                    queryMovieInputDto.getSound(),
                    queryMovieInputDto.getStartDate(),
                    queryMovieInputDto.getEndDate(), PageRequest.of(page, size));
        }
        return movieMinimumOutputDtosPage;

    }

    public MovieMinimumOutputDto createMovie(MovieInputDto movieInputDto) {
        Movie movie = new Movie(movieInputDto.getName(), movieInputDto.getGenre(),
                movieInputDto.getStoryline(), movieInputDto.getArtMovement(),
                movieInputDto.getDirector(), movieInputDto.getCountry(),
                movieInputDto.getLanguage(), movieInputDto.getReleaseDate()
                , movieInputDto.getRuntime(), movieInputDto.getColor(), movieInputDto.getSound(),
                movieInputDto.getPoster(), new MovieLinks(movieInputDto.getMovieLinksDto().getYoutubeId(),movieInputDto.getMovieLinksDto().getImdb()));

        this.movieRepository.save(movie);

        return new MovieMinimumOutputDto(movie);
    }

    public void updatePoster(String id, String poster) throws NotFoundException {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new NotFoundException("No Film Found With The Given ID:" + id));
        movie.setPoster(poster);
        movieRepository.save(movie);
    }

    public void updateMovie(String id, MovieInputDto movieInputDto) throws NotFoundException {
        Movie movie = this.movieRepository.findById(id).orElseThrow(() -> new NotFoundException("No Film Found"));

        movie.setName(movieInputDto.getName());
        movie.setDirector(movieInputDto.getDirector());
        movie.setStoryline(movieInputDto.getStoryline());
        movie.setArtMovement(movieInputDto.getArtMovement());
        movie.setColor(movieInputDto.getColor());
        movie.setSound(movieInputDto.getSound());
        movie.setCountry(movieInputDto.getCountry());
        movie.setGenre(movieInputDto.getGenre());
        movie.setLanguage(movieInputDto.getLanguage());
        movie.setRuntime(movieInputDto.getRuntime());
        movie.setReleaseDate(movieInputDto.getReleaseDate());
        if(movie.getMovieLinks()==null){
            movie.setMovieLinks(new MovieLinks(movieInputDto.getMovieLinksDto().getYoutubeId(),movieInputDto.getMovieLinksDto().getImdb()));
        }else{
            movie.getMovieLinks().setYoutubeId(movieInputDto.getMovieLinksDto().getYoutubeId());
            movie.getMovieLinks().setImdb(movieInputDto.getMovieLinksDto().getImdb());
        }

        movieRepository.save(movie);
    }

    public void deleteMovie(String id) {
        movieRepository.deleteById(id);
    }

}