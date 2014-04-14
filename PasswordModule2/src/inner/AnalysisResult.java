package inner;

import java.util.ArrayList;

public class AnalysisResult {
	public AccountEntry checkedItem;
	
	// 0 means no threat, 1 means low threat, 2 means medium threat, 3 means high threat, 4 means very high threat.
	public int threatLevel;
	public ArrayList<String> domainsWithSamePsd;
	
	public AnalysisResult(AccountEntry checkedItem, int threatLevel, ArrayList<String> domainWithSamePsd){
		this.checkedItem = checkedItem;
		this.threatLevel = threatLevel;
		this.domainsWithSamePsd = domainWithSamePsd;
	}
	
	public AnalysisResult(){
		domainsWithSamePsd = new ArrayList<String>();
	}
}
