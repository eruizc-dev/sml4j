package dev.eruizc.sml4j;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class StateMachineBuilderTest {
	@Test
	void cannotSetInitialTwice() {
		assertThrows(UnsupportedOperationException.class, () -> {
			new StateMachineBuilder<>()
				.initialState(State.INITIAL)
				.initialState(State.FINAL);
		});
	}

	@Test
	void cannotBuildWithoutInitialState() {
		assertThrows(IllegalStateException.class, () -> {
			new StateMachineBuilder<>().build();
		});
	}

	public enum State { INITIAL, FINAL }
	public enum Action { DO_SOMETHING }
}
