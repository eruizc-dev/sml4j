package dev.eruizc.sml4j;

public class IllegalTransitionException extends Exception {
	private Enum<?> state;
	private Enum<?> action;

	public IllegalTransitionException(Enum<?> state, Enum<?> action) {
		super("Illegal state transition from state <" + state + "> with action <" + action + ">");
		this.state = state;
		this.action = action;
	}

	public Enum<?> getState() {
		return this.state;
	}

	public Enum<?> getAction() {
		return this.action;
	}
}
