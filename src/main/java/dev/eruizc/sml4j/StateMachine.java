package dev.eruizc.sml4j;

import java.util.List;

public class StateMachine<S, A> {
	private final List<Transition<S, A>> transitions;
	private S state;

	StateMachine(S initialState, List<Transition<S, A>> transitions) {
		this.transitions = transitions;
		this.state = initialState;
	}

	public S getState() {
		return this.state;
	}

	public void transition(A action) {
		var transition = transitions.stream()
				.filter(t -> t.matches(state, action))
				.findFirst()
				.orElseThrow(() -> new IllegalStateException("Illegal state transition from " + this.state + " with action " + action));
		this.state = transition.getTo();
	}
}
