package Models;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

public class CategoryDAO {

	/**
	 * A DAO for the entity User is simply created by extending the
	 * CrudRepository interface provided by spring. The following methods are
	 * some of the ones available from such interface: save, delete, deleteAll,
	 * findOne and findAll. The magic is that such methods must not be
	 * implemented, and moreover it is possible create new query methods working
	 * only by defining their signature!
	 * 
	 * @source https://github.com/netgloo/spring-boot-samples/blob/master/spring-boot-mysql-springdatajpa-hibernate/src/main/java/netgloo/models/UserDao.java
	 * @author Lode Alaert
	 */

	@Transactional
	public interface UserDao extends CrudRepository<Category, Long> {

		// gets the category by ID
		public Category findCategoryByID(int id);

	}
}
