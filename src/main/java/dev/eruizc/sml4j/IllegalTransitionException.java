package dev.eruizc.sml4j;

public class IllegalTransitionException extends Exception {
	private Object state;
	private Object action;

	public IllegalTransitionException(Object state, Object action) {
		super("Illegal state transition from state <" + state + "> with action <" + action + ">");
		this.state = state;
		this.action = action;
	}

	public Object getState() {
		return this.state;
	}

	public Object getAction() {
		return this.action;
	}
}
