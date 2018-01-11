package resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import dao.FilmDAO;
import model.Film;

@Path("/Films")
public class FilmResource {

	
	FilmDAO dao = new FilmDAO();
	
	
	//getFilmByID
	
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Film getFilmByID(int ID) {
		
		Film film = dao.getFilmByID(ID);
		
		if(film==null) {
			throw new RuntimeException("Get: Film with " + ID +  " not found");
		}
		
		return film;
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public Film getFilmByIDBrowser(int ID) {
		
		Film film = dao.getFilmByID(ID);
		
		if(film==null) {
			throw new RuntimeException("Get: Film with " + ID +  " not found");
		}
		
		return film;
	}
	
	
	//getAllFilms
	
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public ArrayList<Film> getAllFilms(){
		return dao.getAllFilms();
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public ArrayList<Film> getAllFilmsBrowser(){
		return dao.getAllFilms();
	}
	
	
	//getFilmsMatching
	
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public ArrayList<Film> getFilmsMatching(String search){
		
		ArrayList<Film> films = dao.getFilmsMatching(search);
		
		if(films==null) {
			throw new RuntimeException("Get: Film containing " + search +  " not found");
		}
			
		return films;
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public ArrayList<Film> getFilmsMatchingBrowser(String search){
		
		ArrayList<Film> films = dao.getFilmsMatching(search);
		
		if(films==null) {
			throw new RuntimeException("Get: Film containing " + search +  " not found");
		}
			
		return films;
	}
	
	
	//removeFilm
	
	
	@DELETE
	public void removeFilm(int ID) {
		int success = dao.removeFilmByID( ID );
		if(success == 0) {
			throw new RuntimeException("Delete: Film with " + ID +  " not found");
		}
			
	}

	
	//addFilm
	
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public int addFilm(
			@FormParam("title") String title,
			@FormParam("year") int year,
			@FormParam("director") String director,
			@FormParam("stars") String stars,
			@FormParam("review") String review) {
		
		int success = dao.addFilm(title, year, director, stars, review);
		
		return success;
	}
	
	

}