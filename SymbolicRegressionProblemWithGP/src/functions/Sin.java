package functions;

import ec.*;
import ec.gp.*;
import main.*;

public class Sin extends GPNode {

	@Override
	public void eval(final EvolutionState state,
			final int thread,
			final GPData input,
			final ADFStack stack,
			final GPIndividual individual,
			final Problem problem) {
		// TODO Auto-generated method stub
	
		FloatData rd = ((FloatData)(input)); 
		
		children[0].eval(state, thread, input, stack, individual, problem); //???? understand this
		rd.x = (float) Math.sin(rd.x);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "sin";
	}
	
	public int expectedChildren() {
		return 1;
	}
	
}
