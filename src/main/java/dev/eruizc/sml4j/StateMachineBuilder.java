package dev.eruizc.sml4j;

import java.util.*;

/**
 * Builder for {@link StateMachine}
 */
public class StateMachineBuilder<S extends Enum<S>, T extends Enum<T>> {
	private final Map<S, Node<S, T>> transitions = new HashMap<>();

	/**
	 * @param state Initial state
	 * @param transition Allowed action given the initial state
	 * @param resultingState Resulting state
	 * @return Builder
	 */
	public StateMachineBuilder<S, T> allowTransition(S state, T transition, S resultingState) {
		var parent = getOrCreate(state);
		var child = getOrCreate(resultingState);
		parent.addChild(transition, child);
		return this;
	}

	/**
	 * @param initialState Initial State for the sate machine
	 * @throws UnsupportedOperationException The transitions result in a Non-Deterministic State Machine, which are not supported by this library
	 * @return The built State Machine
	 */
	public StateMachine<S, T> buildFrom(S initialState) {
		return new StateMachine<>(transitions.get(initialState));
	}

	private Node<S, T> getOrCreate(S state) {
		if (!transitions.containsKey(state)) {
			transitions.put(state, new Node<S, T>(state));
		}
		return transitions.get(state);
	}
}
