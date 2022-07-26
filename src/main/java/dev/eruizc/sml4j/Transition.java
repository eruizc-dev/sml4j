package dev.eruizc.sml4j;

public class Transition<State, Action> {
	private final State from;
	private final Action action;
	private final State to;

	public Transition(State from, Action action, State to) {
		this.from = from;
		this.action = action;
		this.to = to;
	}

	public Action getAction() {
		return action;
	}

	public State getTo() {
		return to;
	}

	public boolean matches(State from, Action action) {
		return this.from.equals(from) && this.action.equals(action);
	}
}
