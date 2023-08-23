package dev.eruizc.sml4j;

import java.util.Set;

/**
 * A finite State Machine
 */
public class StateMachine<S extends Enum<S>, T extends Enum<T>> {
	private Node<S, T> current;

	public StateMachine(Node<S, T> tree) {
		this.current = tree;
	}

	/**
	 * Gets the current state of the State Machine
	 * @return current state
	 */
	public S getState() {
		return this.current.getValue();
	}

	/**
	 * Gets the available transitions for the current state
	 * @return available transitions
	 */
	public Set<T> getTransitions() {
		return this.current.getChilds();
	}

	/**
	 * @param transition To validate
	 * @throws IllegalTransitionException The requested action cannot be executed in the current state
	 */
	public void validateTransition(T transition) throws IllegalTransitionException {
		this.current.next(transition);
	}

	/**
	 * @param transition To move to
	 * @throws IllegalTransitionException The requested action cannot be executed in the current state
	 */
	public void transition(T transition) throws IllegalTransitionException {
		this.current = this.current.next(transition);
	}
}
