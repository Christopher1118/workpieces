package test;

import java.util.List;

import inner.Importance;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import dao.ApplicationConfig;
import dao.ImportanceRepository;


public class JPATest2 {
	public static void main(String[] args){
        ConfigurableApplicationContext context = SpringApplication.run(ApplicationConfig.class);
        ImportanceRepository importanceRepository = context.getBean(ImportanceRepository.class);
		Importance imp = new Importance();
		imp.setDomain("onestart.iu.edu");
		imp.setImportanceLevel(5);
		importanceRepository.save(imp);
		long num = importanceRepository.count();
		Iterable<Importance> impList = importanceRepository.findAll();
		
		for(Importance ip:impList){
			System.out.println("The domain is "+ip.getDomain());
		}
		
		System.out.println("number of tuples is " + num);
		context.close();
	}
}
