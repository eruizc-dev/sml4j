package dev.eruizc.sml4j;

import java.util.HashMap;

/**
 * A finite State Machine
 */
public class StateMachine<S extends Enum<S>, A extends Enum<A>> {
	private final HashMap<Integer, S> transitions;
	private S state;

	StateMachine(S initialState, HashMap<Integer, S> transitions) {
		this.transitions = transitions;
		this.state = initialState;
	}

	/**
	 * Gets the current state of the State Machine
	 * @return current state
	 */
	public S getState() {
		return this.state;
	}

	/**
	 * @param action The action to be executed
	 * @throws IllegalTransitionException The requested action cannot be executed in the current state
	 */
	public void transition(A action) throws IllegalTransitionException {
		var resultingState = transitions.get(hash(state, action));
		if (resultingState == null) {
			throw new IllegalTransitionException(state, action);
		}
		this.state = resultingState;
	}

	static int hash(Enum<?> state, Enum<?> action) {
		return (3 * state.hashCode()) ^ (17 * action.hashCode());
	}
}
