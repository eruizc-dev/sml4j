package dev.eruizc.sml4j;

import java.util.HashMap;

/**
 * Builder for {@link StateMachine}
 */
public class StateMachineBuilder<S extends Enum<S>, A extends Enum<A>> {
	private final HashMap<Integer, S> transitions = new HashMap<>();
	private boolean deterministic = true;

	/**
	 * @param state Initial state
	 * @param action Allowed action given the initial state
	 * @param resultingState Resulting state
	 * @return Builder
	 */
	public StateMachineBuilder<S, A> allowTransition(S state, A action, S resultingState) {
		var hash = StateMachine.hash(state, action);
		deterministic &= this.transitions.put(hash, resultingState) == null;
		return this;
	}

	/**
	 * @throws UnsupportedOperationException The transitions result in a Non-Deterministic State Machine, which are not supported by this library
	 * @return The built State Machine
	 */
	public StateMachine<S, A> build() {
		if (!deterministic) {
			throw new UnsupportedOperationException("Transition defines a Non-Deterministic State Machine, which are not supported by sml4j");
		}
		return new StateMachine<>(initialState, transitions);
	}
}
