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
	 * @throws UnsupportedOperationException Can only define initial state once
	 */
	public StateMachineBuilder<S, A> initialState(S initialState) {
		if (this.initialState != null) {
			throw new UnsupportedOperationException("Can only define initial state once");
		}
		this.initialState = initialState;
		return this;
	}

	/**
	 * @param from Initial state
	 * @param action Allowed action given the initial state
	 * @param to Resulting state
	 * @return Builder
	 * @throws UnsupportedOperationException The transition defines a Non-Deterministic State Machine which are not supported by this library yet
	 */
	public StateMachineBuilder<S, A> allowTransition(S from, A action, S to) {
		if (this.transitions.stream().anyMatch(t -> t.matches(from, action))) {
			throw new UnsupportedOperationException("Transition defines a Non-Deterministic State Machine which are not supported by sml4j yet");
		}
		var transition = new Transition<>(from, action, to);
		this.transitions.add(transition);
		return this;
	}

	/**
	 * @return The built State Machine
	 * @throws IllegalStateException Initial state cannot be null
	 */
	public StateMachine<S, A> build() {
		if (this.initialState == null) {
			throw new IllegalStateException("Initial state cannot be null");
		}
		return new StateMachine<S, A>(initialState, transitions);
	}
}
