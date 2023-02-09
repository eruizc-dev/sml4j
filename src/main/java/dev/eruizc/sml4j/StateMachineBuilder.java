package dev.eruizc.sml4j;

import java.util.*;

/**
 * Builder for {@link StateMachine}
 */
public class StateMachineBuilder<S extends Enum<S>, A extends Enum<A>> {
	private final HashSet<Transition<S, A>> transitions;
	private final S initialState;
	private boolean deterministic;

	/**
	 * @param initialState The initial state for the State Machine
	 */
	public StateMachineBuilder(S initialState) {
		this.initialState = initialState;
		this.transitions = new HashSet<>();
		this.deterministic = true;
	}

	/**
	 * @param state Initial state
	 * @param action Allowed action given the initial state
	 * @param resultingState Resulting state
	 * @return Builder
	 */
	public StateMachineBuilder<S, A> allowTransition(S state, A action, S resultingState) {
		var transition = new Transition<>(state, action, resultingState);
		deterministic &= this.transitions.add(transition);
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
