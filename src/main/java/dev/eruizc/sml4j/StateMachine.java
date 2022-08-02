package dev.eruizc.sml4j;

import java.util.List;

/**
 * A finite State Machine
 */
public class StateMachine<S, A> {
	private final List<Transition<S, A>> transitions;
	private S state;

	StateMachine(S initialState, List<Transition<S, A>> transitions) {
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
	 * @throws IllegalStateException Action cannot be executed in the current state
	 */
	public void transition(A action) {
		var transition = transitions.stream()
				.filter(t -> t.matches(state, action))
				.findFirst()
				.orElseThrow(() -> new IllegalStateException("Illegal state transition from " + this.state + " with action " + action));
		this.state = transition.getTo();
	}
}
