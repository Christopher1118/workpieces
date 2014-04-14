package inner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="importance")
public class Importance {
	
    @Id
    private String domain;
    private int importanceLevel;
    
    public Importance(String domain, int level){
    	this.domain = domain;
    	importanceLevel = level;
    }
    public Importance(){
    	
    }

	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public int getImportanceLevel() {
		return importanceLevel;
	}
	public int setImportanceLevel(int importanceLevel) {
		return this.importanceLevel = importanceLevel;
	}
    
    
    
}
