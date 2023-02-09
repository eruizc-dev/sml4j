package dev.eruizc.sml4j;

class Transition<State extends Enum<State>, Action extends Enum<Action>> {
	private final State from;
	private final Action action;
	private final State resultingState;

	public Transition(State state, Action action, State resultingState) {
		this.from = state;
		this.action = action;
		this.resultingState = resultingState;
	}

	public State getResultingState() {
		return resultingState;
	}

	public boolean matches(State from, Action action) {
		return this.from == from && this.action == action;
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
