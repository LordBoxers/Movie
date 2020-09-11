package facades;

import entities.Movie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private MovieFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //TODO Remove/Change this before use
    public long getMovieCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(r) FROM Movie r").getSingleResult();
            return renameMeCount;
        }finally{  
            em.close();
        }
        
    }
    public List<Movie> getAllMovies() {
        EntityManager em = getEntityManager();
        try{
            TypedQuery<Movie> tp = em.createNamedQuery("Movie.getAll",Movie.class);
            List<Movie> ls = tp.getResultList();
            return ls;
        }finally{
            em.close();
        }
    }
    public void populate() {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Movie(1982, "Conan the Barbarian", new String[]{"Arnold Schwarzennegger","James Earl Jones", "Sandahl Bergman"}));
            em.persist(new Movie(1984, "Conan the Destroyer", new String[]{"Arnold Schwarzennegger","Grace Jones", "Wilt Chamberlain"}));
            em.persist(new Movie(1984, "Terminator", new String[]{"Arnold Schwarzennegger","Michael Biehn", "Linda Hamilton"}));
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }

}
