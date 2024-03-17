package main;

import ec.Evolve;

public class MainRegression {
	public static void main(String[] args) {
		String pathToFiles = "H:\\BrockUniversityThridYear\\Second Semester\\COSC 4P82(GP)\\GP\\SymbolicRegressionProblemWithGP\\bin\\results";
		
		int numOfJobs = 10;
		
//		String statisticType = "ec.gp.koza.KozaShortStatistics";
		
		String[] runConfig = new String[] {
				Evolve.A_FILE, "src/main/regression.params",
//				"-p", ("stat="+statisticType), 
				"-p", ("stat.file=$" + pathToFiles + "out.stat"),
				"-p", ("jobs=" + numOfJobs)
		};
		Evolve.main(runConfig);
		
	}
}
