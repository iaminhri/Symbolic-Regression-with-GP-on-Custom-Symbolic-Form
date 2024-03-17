package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import ec.EvolutionState;
import ec.Individual;
import ec.gp.GPProblem;
import ec.gp.koza.KozaFitness;
import ec.simple.SimpleProblemForm;
import ec.gp.*;
import ec.util.Parameter;

@SuppressWarnings("Serial")
public class RegressionProblem extends GPProblem implements SimpleProblemForm{
	/**
	 * 
	 */
	public static final String P_DATA = "data";
	
	public float currentX;
	public float currentY;
	public ArrayList<Float> inputX = new ArrayList<Float>();
	public ArrayList<Float> inputY = new ArrayList<Float>();
	
	
	/**
	 * 
	 * This section describes when to read the values so that ECJ can use them?
	 * ECJ has the method setup, which gets called before any GP stuff gets involved.
	 * That is where you should instantiate all of your data.
	 * @setup function is called before any ECJ classes or methods call
	 * 
	 */
	public void setup(final EvolutionState state, final Parameter base) {
		/**
		 * Super class setup takes two parameters
		 * @state -> EvaluationState state
		 * @base -> Parameter base
		 * 
		 */
		super.setup(state, base);
		
		/**
		 * Verifies if our input is the right class or subclasses from it.
		 * Meaning, if we are working with FloatData then check if input is an instance of FloatData
		 * that is created in the FloatData Class.
		 * 
		 * @GPProblem -> defines an instance of input
		 * verifies our input is of correct class
		 */
		if(!(input instanceof FloatData)) {
			state.output.fatal("GPData class must subclass from " + FloatData.class,
					base.push(P_DATA), null);
		}
		readFromFile(); // reads from file 
	}
	
	/**
	 * fitness evaluation function, ECJ provides us with the handy function called evaluate,
	 * and it will be called for each individual in population and sets each individuals fitness
	 * 
	 */

	@Override
	public void evaluate(final EvolutionState state, final Individual ind, final int subpopulation, final int threadnum) {
		// TODO Auto-generated method stub
		
		// if individual is evaluated then don't bother reevaluating it.
		if(!ind.evaluated) {
			FloatData input = (FloatData)(this.input);
			
			int hits = 0;
			float sum = 0;
			float expectedResult;
			float result;
			
			/**
			 * @currentX -> takes inputX from data.txt
			 * @expectedResult -> takes inputY from data.txt 
			 * based on inputX the output is associated as inputY
			 * for example if inputX(0) = 2 then input(1) = 4
			 * some math expression that comes with 4
			 * now, if the expected result - input.x <= 0.1 then we say it is a good individual
			 * else not. this Koza-Fitness Evaluation.
			 * Based on the evaluation ECJ knows the individuals fitness
			 */
			
			for(int i = 0; i < inputX.size(); i++) {
				currentX = inputX.get(i);
//				currentY = inputY.get(i);
				
//				currentX = state.random[threadnum].nextFloat();
//				currentY = state.random[threadnum].nextFloat();
//				
//				expectedResult = currentX * currentX * currentY + currentX * currentY + currentY;
				expectedResult = inputY.get(i);
				
				((GPIndividual)ind).trees[0].child.eval(state, threadnum, input, stack, ((GPIndividual)ind), this);
								
				result = Math.abs(expectedResult - input.x);
				if(result <= 0.01) {
					hits++;
				}
				sum += result;
			}
			
			// Instantiating KozaFitness class --> this better be Koza-fitness
			KozaFitness f = ((KozaFitness)ind.fitness);
			f.setStandardizedFitness(state, sum);
			f.hits = hits;
			ind.evaluated = true;
		}
	}
	
	private void readFromFile() {
		Scanner scan;
		File file = new File("src/main/data3.txt");
		
		try {
			scan = new Scanner(file);
			int indexOfNumberInFile = 0;
			while(scan.hasNextFloat()) {
				float floatNumber = scan.nextFloat();
				/**
				 * from the data.txt
				 * every even number is stored in the inputX list
				 * every odd number is stored in the inputY list
				 */
				if(indexOfNumberInFile % 2 == 0) {
					inputX.add(floatNumber);
				}
				else {
					inputY.add(floatNumber);
				}
				indexOfNumberInFile++;
			}
		}catch (FileNotFoundException e1){
			e1.printStackTrace();
		}
	}
	
	
}
