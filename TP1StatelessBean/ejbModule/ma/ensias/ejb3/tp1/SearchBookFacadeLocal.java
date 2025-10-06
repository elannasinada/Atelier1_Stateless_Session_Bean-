package ma.ensias.ejb3.tp1;
import javax.ejb.Local;
import java.util.List;

@Local
public interface SearchBookFacadeLocal {
	List<String> bookSearch(String bookType);
	List<String> searchBookByCountry(String country);
}
