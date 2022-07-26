package dev.eruizc.sml4j;

import java.util.stream.*;

public class StateMachine<State, Action> {
	private final Transition<State, Action>[] transitions;
	private State state;

	@SafeVarargs
	public StateMachine(State initialState, Transition<State, Action>... transitions) {
		this.transitions = transitions;
		this.state = initialState;
	}

	public State getState() {
		return this.state;
	}

	public void transition(Action action) {
		var transition = Stream.of(transitions)
			.filter(t -> t.matches(state, action))
			.findFirst()
			.orElseThrow(() -> new IllegalStateException("Illegal state transition from " + this.state + " with action " + action));
		this.state = transition.getTo();
	}
}
