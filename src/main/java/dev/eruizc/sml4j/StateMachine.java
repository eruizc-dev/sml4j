package dev.eruizc.sml4j;

import java.util.Set;

/**
 * A finite State Machine
 */
public class StateMachine<S extends Enum<S>, A extends Enum<A>> {
	private final Set<Transition<S, A>> transitions;
	private S state;

	StateMachine(S initialState, Set<Transition<S, A>> transitions) {
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
		var transition = transitions.stream()
				.filter(t -> t.matches(state, action))
				.findFirst()
				.orElseThrow(() -> new IllegalTransitionException(this.state, action));
		this.state = transition.getTo();
	}
}
