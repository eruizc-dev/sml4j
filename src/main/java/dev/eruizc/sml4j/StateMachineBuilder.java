package dev.eruizc.sml4j;

import java.util.*;

public class StateMachineBuilder<S, A> {
	private final List<Transition<S, A>> transitions = new LinkedList<>();
	private S initialState;

	public StateMachineBuilder<S, A> initialState(S initialState) {
		this.initialState = initialState;
		return this;
	}

	public StateMachineBuilder<S, A> allowTransition(S from, A action, S to) {
		var transition = new Transition<>(from, action, to);
		transitions.add(transition);
		return this;
	}

	public StateMachine<S, A> build() {
		return new StateMachine<S, A>(initialState, transitions);
	}
}
