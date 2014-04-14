package dao;

import java.util.List;

import inner.Importance;

import org.springframework.data.repository.CrudRepository;


public interface ImportanceRepository extends CrudRepository<Importance, String>{
	public List<Importance> findByDomain(String domain);
	public List<Importance> findByImportanceLevel(int iLevel);
	//public List<Importance> findAll();
}
