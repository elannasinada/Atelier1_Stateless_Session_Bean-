package ma.ensias.ejb3.tp1;
import javax.ejb.Remote;
import java.util.List; 

@Remote
public interface SearchBookFacadeRemote {
	List<String> bookSearch(String bookType);
	List<String> searchBookByCountry(String country);
}
