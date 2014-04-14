package service;

import inner.AccountEntry;
import inner.AnalysisResult;
import inner.Importance;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import dao.ApplicationConfig;
import dao.ImportanceRepository;
import dao.PsdRepository;

public class SecurityCheck {
	// valueTable is one of the most important things that decide it evaluate
	// threat. It is a two-dimensional array that
	// describe the occasion that for a password, we have a set of domains that
	// use this password, given the domain highest
	// importance level ever found in this set(represented by first
	// dimension),and the threat level( represented
	// by the second dimensional), we give a maximum number of duplicated
	// domains number that fit this threat level(the value of
	// valueTable[highest_importance_level-1][threat_level])
	private int[][] valueTable;
	private int vtRow = 5;
	private int vtColumn = 4;

	private ConfigurableApplicationContext context = null;
	private ImportanceRepository importanceRepository = null;
	private PsdRepository psdRepository = null;

	public SecurityCheck() {
		this.valueTable = new int[5][4];
		valueTable[0][0] = 10;
		valueTable[0][1] = 20;
		valueTable[0][2] = Integer.MAX_VALUE;
		valueTable[0][3] = Integer.MAX_VALUE;

		valueTable[1][0] = 5;
		valueTable[1][1] = 10;
		valueTable[1][2] = 20;
		valueTable[1][3] = Integer.MAX_VALUE;

		valueTable[2][0] = 1;
		valueTable[2][1] = 5;
		valueTable[2][2] = 10;
		valueTable[2][3] = Integer.MAX_VALUE;

		valueTable[3][0] = 0;
		valueTable[3][1] = 1;
		valueTable[3][2] = 5;
		valueTable[3][3] = Integer.MAX_VALUE;

		valueTable[4][0] = 0;
		valueTable[4][1] = 0;
		valueTable[4][2] = 0;
		valueTable[4][3] = 1;

		context = SpringApplication.run(ApplicationConfig.class);
		importanceRepository = context.getBean(ImportanceRepository.class);
		psdRepository = context.getBean(PsdRepository.class);
	}

	public SecurityCheck(int[][] valueTable) throws ValueTableSizeException {
		if (valueTable.length != vtRow || valueTable[0].length != vtColumn) {
			throw new ValueTableSizeException();
		}
		this.valueTable = new int[vtRow][vtColumn];
		for (int i = 0; i < vtRow; i++) {
			System.arraycopy(valueTable[i], 0, this.valueTable[i], 0, vtColumn);
		}
	}
	

	public AnalysisResult check(AccountEntry item) {

		List<AccountEntry> acctEntries = psdRepository
				.findByUsernameAndPassword(item.getUsername(),
						item.getPassword());
		int threatLevel = 0;
		if (!acctEntries.isEmpty()) {
			threatLevel = evaluateThreat(item, acctEntries);
		} else {
			threatLevel = 0;
		}
		AnalysisResult ar = new AnalysisResult();
		ar.checkedItem = item;
		ar.threatLevel = threatLevel;
		for (AccountEntry ae : acctEntries) {
			ar.domainsWithSamePsd.add(ae.getDomain());
		}
		return ar;

	}

	public int evaluateThreat(AccountEntry item, List<AccountEntry> acctEntries) {
		int importanceLevelsNum = vtColumn+1;
		ArrayList<Integer> importanceList = new ArrayList<Integer>();
			Importance imp = importanceRepository.findOne(item.getDomain());
			if(imp!=null){
				int iLevel = imp.getImportanceLevel();
				importanceList.add(iLevel);
			}
			for (AccountEntry entry : acctEntries) {
				Importance ip = importanceRepository.findOne(entry.getDomain());
				if(ip!=null){
					importanceList.add(ip.getImportanceLevel());
				}
			}
			ArrayList<Integer> importanceHistogram = new ArrayList<Integer>();
			for (int i = 0; i < importanceLevelsNum; i++) {
				importanceHistogram.add(0);
			}
			for (int level : importanceList) {
				if (level > 0 && level <= importanceLevelsNum) {
					importanceHistogram.set(level - 1,
							importanceHistogram.get(level - 1) + 1);
				}
			}
			int dupSum = 0;
			int highestLevel = 0;
			int i = importanceLevelsNum - 1;
			while (i >= 0) {
				// Find the largest importance level and count how many other
				// domains that use the same password.
				if (importanceHistogram.get(i) > 0) {
					highestLevel = i + 1;
					for (int j = 0; j <= i; j++) {
						dupSum = dupSum + importanceHistogram.get(j);
					}
					dupSum = dupSum - 1;
					break;
				}
				i--;
			}
			return getValue(highestLevel, dupSum);
	}

	public int getValue(int highestLevel, int dupSum) {
		if(highestLevel<=0 || highestLevel>vtColumn+1){
			return Threat.SAFE;
		}
		int threat = vtColumn;
		for (int j = vtColumn - 1; j >= 0; j--) {
			if (dupSum <= valueTable[highestLevel - 1][j]) {
				threat--;
			} else {
				break;
			}
		}
		return threat;
	}

	@Override
	protected void finalize() {
		context.close();
		try {
			super.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
