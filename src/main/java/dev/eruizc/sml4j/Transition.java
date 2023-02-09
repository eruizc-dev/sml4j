package dev.eruizc.sml4j;

class Transition<State extends Enum<State>, Action extends Enum<Action>> {
	private final State from;
	private final Action action;
	private final State to;

	public Transition(State from, Action action, State to) {
		this.from = from;
		this.action = action;
		this.to = to;
	}

	public State getTo() {
		return to;
	}

	public boolean matches(State from, Action action) {
		return this.from.equals(from) && this.action.equals(action);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		var other = (Transition<?, ?>) obj;
		return this.from == other.from && this.action == other.action;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + from.hashCode();
		result = prime * result + action.hashCode();
		return result;
	}
}
