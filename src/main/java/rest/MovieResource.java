package rest;

import DTO.MovieDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Movie;
import utils.EMF_Creator;
import facades.MovieFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("movie")
public class MovieResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final MovieFacade FACADE =  MovieFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        long count = FACADE.getMovieCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMovies() {
        List<Movie> ls = FACADE.getAllMovies();
        List<MovieDTO> lsdto = new ArrayList<MovieDTO>();
        for (Movie e : ls) {
            lsdto.add(new MovieDTO(e));
        }
        return GSON.toJson(lsdto);
    }
    
    @GET
    @Path("/populate")
    @Produces({MediaType.APPLICATION_JSON})
    public String populate() {
        FACADE.populate();
        return "List of movies added";
    }
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String titleById(@PathParam("id") long id) {
        MovieDTO result = new MovieDTO (FACADE.findById(id)) ;
        return new Gson().toJson(result);
    }
    @GET
    @Path("/title/{title}")
    @Produces({MediaType.APPLICATION_JSON})
    public String titleByTitle(@PathParam("title") String title) {
        List<Movie> ls = FACADE.findByTitle(title);
        List<MovieDTO> lsdto = new ArrayList<MovieDTO>();
        for (Movie e : ls) {
            lsdto.add(new MovieDTO(e));
        }
        return GSON.toJson(lsdto);
    }
    
    
    
}