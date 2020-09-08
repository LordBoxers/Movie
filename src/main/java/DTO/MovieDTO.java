/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.Movie;

/**
 *
 * @author Mibsen
 */
public class MovieDTO {
    private int year;
    private String title;
    private String[] actors;
    
    public MovieDTO(Movie m) {
        this.year = m.getYear();
        this.title = m.getTitle();
        this.actors = m.getActors();
    }
    public MovieDTO() {
        
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }
    
}
