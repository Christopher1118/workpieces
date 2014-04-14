package dao;

import java.util.List;
import inner.AccountEntry;
import org.springframework.data.repository.CrudRepository;


public interface PsdRepository extends CrudRepository<AccountEntry, Long>{
	public List<AccountEntry> findByUsernameAndPassword(String username, String password);
	public List<AccountEntry> findByDomain(String domain);
}
