package com.first.demoMongo.businessControllers;

import com.first.demoMongo.businessServices.RegionService;
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

    @Autowired
    private RegionService regionService;

    public MovieOutputDto getMovieById(String id) throws NotFoundException {
        return new MovieOutputDto(this.movieRepository.findById(id).orElseThrow(
                () -> new NotFoundException("No Film Found With the given ID:" + id)
        ));
    }

    public Page<Movie> getPage(int page, int size, String key, Sort.Direction dir) throws NotFoundException {
        Page<Movie> moviePage = this.movieRepository.findAll(PageRequest.of(page, size, dir, key));
        if (moviePage.getSize() == 0) {
            throw new NotFoundException("no movies found");
        }
        return moviePage;
    }

    public Page<MovieMinimumOutputDto> getMinimunMoviesDtoByName(String name, int page, int size) throws NotFoundException {
        return this.movieRepository.findBynameContaining(name, PageRequest.of(page, size))
                .orElseThrow(() -> new NotFoundException("No Film Found With The Given name:" + name));
    }


    public List<MovieOutputDto> getMoviesByName(String name) throws NotFoundException {
        return this.movieRepository.findBynameContaining(name)
                .orElseThrow(() -> new NotFoundException("No Film Found With The Given name:" + name));
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

    public Page<MovieMinimumOutputDto> getRelatedMovies(String id, int page, int size) throws NotFoundException {
        Movie movie = this.movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No film found with the given Id: " + id));

        return this.movieRepository.findRelatedByArtMovementOrGenre(
                id,
                movie.getArtMovement(),
                movie.getGenre()[0],
                PageRequest.of(page, size)
        );
    }

    public Page<MovieMinimumOutputDto> getMoviesByQueryDto(QueryMovieInputDto queryMovieInputDto, int page, int size) {
        Page<MovieMinimumOutputDto> movieMinimumOutputDtosPage;
        if (queryMovieInputDto.getGenre().length == 0) {
            movieMinimumOutputDtosPage = this.movieRepository.findByfiltersExceptGenre(
                    queryMovieInputDto.getArtMovement(),
                    queryMovieInputDto.getCountry(),
                    queryMovieInputDto.getRegion(),
                    queryMovieInputDto.getLanguage(),
                    queryMovieInputDto.getMinRuntime(),
                    queryMovieInputDto.getMaxRuntime(),
                    queryMovieInputDto.getColor(),
                    queryMovieInputDto.getSound(),
                    queryMovieInputDto.getStartYearAsLocalDate(),
                    queryMovieInputDto.getEndYearAsLocalDate(), PageRequest.of(page, size));
        } else {
            movieMinimumOutputDtosPage = this.movieRepository.findByfilters(
                    queryMovieInputDto.getArtMovement(),
                    queryMovieInputDto.getGenre(),
                    queryMovieInputDto.getCountry(),
                    queryMovieInputDto.getRegion(),
                    queryMovieInputDto.getLanguage(),
                    queryMovieInputDto.getMinRuntime(),
                    queryMovieInputDto.getMaxRuntime(),
                    queryMovieInputDto.getColor(),
                    queryMovieInputDto.getSound(),
                    queryMovieInputDto.getStartYearAsLocalDate(),
                    queryMovieInputDto.getEndYearAsLocalDate(), PageRequest.of(page, size));
        }

        return movieMinimumOutputDtosPage;

    }

    public MovieMinimumOutputDto createMovie(MovieInputDto movieInputDto) {
        Movie movie = new Movie(movieInputDto.getName(), movieInputDto.getGenre(),
                movieInputDto.getStoryline(), movieInputDto.getArtMovement(),
                movieInputDto.getDirector(), movieInputDto.getCountry(),
                this.regionService.getCountryRegion(movieInputDto.getCountry()),
                movieInputDto.getLanguage(), movieInputDto.getReleaseDate()
                , movieInputDto.getRuntime(), movieInputDto.getColor(), movieInputDto.getSound(),
                movieInputDto.getPoster(), new MovieLinks());
        if (movieInputDto.getMovieLinks() != null) {
            if (movieInputDto.getMovieLinks().getYoutubeId() != null) {
                movie.getMovieLinks().setYoutubeId(movieInputDto.getMovieLinks().getYoutubeId());
            }
            if (movieInputDto.getMovieLinks().getImdb() != null) {
                movie.getMovieLinks().setImdb(movieInputDto.getMovieLinks().getYoutubeId());
            }
        }

        this.movieRepository.save(movie);

        return new MovieMinimumOutputDto(movie);
    }

    public void updatePoster(String id, String poster) throws NotFoundException {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No Film Found With The Given ID:" + id));
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
        movie.setRegion(this.regionService.getCountryRegion(movieInputDto.getCountry()));
        movie.setGenre(movieInputDto.getGenre());
        movie.setLanguage(movieInputDto.getLanguage());
        movie.setRuntime(movieInputDto.getRuntime());
        movie.setReleaseDate(movieInputDto.getReleaseDate());
        if (movieInputDto.getMovieLinks() != null) {
            if (movieInputDto.getMovieLinks().getYoutubeId() != null) {
                movie.getMovieLinks().setYoutubeId(movieInputDto.getMovieLinks().getYoutubeId());
            }
            if (movieInputDto.getMovieLinks().getImdb() != null) {
                movie.getMovieLinks().setImdb(movieInputDto.getMovieLinks().getYoutubeId());
            }
        }

        movieRepository.save(movie);
    }

    public void deleteMovie(String id) {
        movieRepository.deleteById(id);
    }

}