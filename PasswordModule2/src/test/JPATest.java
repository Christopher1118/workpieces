package test;

import static org.junit.Assert.*;
import inner.Importance;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;










import dao.ApplicationConfig;
import dao.ImportanceRepository;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class JPATest {
	@Autowired
	ImportanceRepository importanceRepository;
	
	  
	@Test
	public void testSave() {
		Importance imp = new Importance();
		imp.setDomain("onestart.iu.edu");
		imp.setImportanceLevel(5);
		importanceRepository.save(imp);
		long num = importanceRepository.count();
		System.out.println(num);
	}

}
