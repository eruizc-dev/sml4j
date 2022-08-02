package dev.eruizc.sml4j;

import java.util.*;

/**
 * Builder for {@link StateMachine}
 */
public class StateMachineBuilder<S, A> {
	private final List<Transition<S, A>> transitions = new LinkedList<>();
	private S initialState;

	/**
	 * @param initialState The initial state for the State Machine
	 * @return Builder
	 */
	public StateMachineBuilder<S, A> initialState(S initialState) {
		this.initialState = initialState;
		return this;
	}

	/**
	 * @param from Initial state
	 * @param action Allowed action given the initial state
	 * @param to Resulting state
	 * @return Builder
	 */
	public StateMachineBuilder<S, A> allowTransition(S from, A action, S to) {
		var transition = new Transition<>(from, action, to);
		transitions.add(transition);
		return this;
	}

	/**
	 * @return The built State Machine
	 */
	public StateMachine<S, A> build() {
		return new StateMachine<S, A>(initialState, transitions);
	}
}
