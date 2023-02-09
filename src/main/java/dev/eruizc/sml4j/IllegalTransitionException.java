package dev.eruizc.sml4j;

public class IllegalTransitionException extends Exception {
    public IllegalTransitionException(Enum<?> state, Enum<?> action) {
	super("Illegal state transition from state <" + state + "> with action <" + action + ">");
    }
}
