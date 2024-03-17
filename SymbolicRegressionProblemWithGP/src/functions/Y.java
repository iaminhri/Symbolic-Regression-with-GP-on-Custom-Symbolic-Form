package functions;

import ec.*;
import ec.gp.*;
import main.*;

@SuppressWarnings("Serial")
public class Y extends GPNode{
	
	public int expectedChildren() {
		return 0;
	}

	@Override
	public void eval(final EvolutionState state,
			final int thread, 
			final GPData input,
			final ADFStack stack,
			final GPIndividual individual,
			final Problem problem) {
		// TODO Auto-generated method stub
		
		FloatData rd = ((FloatData)(input));
		
		/** evaluates currentX, these Y.java terminal has no leave nodes, since they are the leave nodes.
		 *  Meaning, There are no children, this function returns currentX 
		 *  when evaluated to the RegressionProblem Class which stores 
		 *  the evaluated value of currentX meaning the current node of the tree.
		 */
		rd.x = ((RegressionProblem)problem).currentY;
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "y";
	}
	
}
