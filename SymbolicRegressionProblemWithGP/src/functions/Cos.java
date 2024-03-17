package functions;

import ec.*;
import ec.gp.*;
import main.*;

@SuppressWarnings("Serial")
public class Cos extends GPNode{
	
	public int expectedChildren() {
		return 1;
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
		
		children[0].eval(state, thread, input, stack, individual, problem);
		rd.x = (float) Math.cos(rd.x);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "cos";
	}
	
}
