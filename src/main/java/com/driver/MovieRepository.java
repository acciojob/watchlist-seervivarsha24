package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {

    private HashMap<String,Movie> movies;
    private HashMap<String,Director> directors;
    private HashMap<String, List<String>> moviesanddirector;

    public MovieRepository() {
        this.movies=new HashMap<>();
        this.directors=new HashMap<>();
        this.moviesanddirector=new HashMap<>();
    }

    public void addMovie(Movie movie) {
        movies.put(movie.getName(),movie);
    }

    public void addDirector(Director director) {
        directors.put(director.getName(), director);
    }

    public void addMovieDirectorPair(String movie, String director) {
        if(movies.containsKey(movie) && directors.containsKey(director)){
            List<String> mo=new ArrayList<>();
            if(moviesanddirector.containsKey(director)) mo=moviesanddirector.get(director);
            mo.add(movie);
            moviesanddirector.put(director,mo);
        }
    }

    public Movie getMovieByName(String movie) {
        return movies.get(movie);
    }

    public Director getDirectorByName(String name) {
        return directors.get(name);
    }

    public List<String> getMoviesByDirectorName(String name) {
        return moviesanddirector.get(name);
    }

    public List<String> findAllMovies() {
        List<String> m=new ArrayList<>();
        for(String name:movies.keySet())
            m.add(name);
        return m;
    }

    public void deleteDirectorByName(String name) {
        if(moviesanddirector.containsKey(name)){
            List<String> m=moviesanddirector.get(name);
            moviesanddirector.remove(name);
            directors.remove(name);
            for (int i=0;i<m.size();i++){
                movies.remove(m.get(i));
            }
        }
    }

    public void deleteAllDirectors() {
        for(String name:moviesanddirector.keySet()){
            deleteDirectorByName(name);
        }
    }
}
