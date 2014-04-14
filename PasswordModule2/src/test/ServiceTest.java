package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import inner.AccountEntry;
import inner.AnalysisResult;
import inner.Importance;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import dao.ApplicationConfig;
import dao.ImportanceRepository;
import dao.PsdRepository;
import service.SecurityCheck;

public class ServiceTest {
	static SecurityCheck sc;
	
	@BeforeClass
	public static void prepare(){
		sc = new SecurityCheck();
	}
	
/*
	@Test
	public void test() {
		System.out.println("---------------------------------------------into test");
		AccountEntry sample = new AccountEntry();
		sample.setDomain("www.citi.com");
		sample.setPassword("12345");
		sample.setUsername("user1");
		System.out.println("evaluate threat result------ "+sc.checkSecurity(sample).threatLevel);
	}
*/
	/*
	@Test
	public void testGetValue(){
		System.out.println("getValue() is "+sc.getValue(5, 5));
	}
*/
	/*
	@Test
	public void test2(){
		ConfigurableApplicationContext context = SpringApplication.run(ApplicationConfig.class);
		PsdRepository psdRepository = context.getBean(PsdRepository.class);
		
		AccountEntry ae1 = psdRepository.findByDomain("www.citi.com").get(0);
		//AccountEntry ae1 = new AccountEntry("user1", "context1","www.citi.com", "bbq", "789");
		ae1.setPassword("6789");
		psdRepository.save(ae1);
		context.close();
	}
	*/
		
	/*
	@Test
	public void test3(){
		ConfigurableApplicationContext context = SpringApplication.run(ApplicationConfig.class);
		ImportanceRepository importanceRepository = context.getBean(ImportanceRepository.class);
		Importance ip1 = new Importance("www.apple.com", 1);
		Importance ip2 = new Importance("www.citi.com", 1);
		Importance ip3 = new Importance("www.microsoft.com", 1);
		Importance ip4 = new Importance("www.facebook.com", 1);	
		Importance ip5 = new Importance("www.starcraft.com", 1);	
		Importance ip6 = new Importance("www.thiagi.com", 1);
		importanceRepository.save(ip1);
		importanceRepository.save(ip2);
		importanceRepository.save(ip3);
		importanceRepository.save(ip4);
		importanceRepository.save(ip5);
		importanceRepository.save(ip6);
		context.close();
	}
	*/
	

	@Test
	public void test4(){
		AccountEntry aEntry = new AccountEntry("user1", null, "www.thiagi.com", "btn", "6789");
		AnalysisResult result = sc.check(aEntry);
		System.out.println("The threat is "+ result.threatLevel);
		System.out.print("relative domain is :  ");
		for(String domain: result.domainsWithSamePsd){
			System.out.print(domain + "  ");
		}
		System.out.println();
	}

	
	
	
}
