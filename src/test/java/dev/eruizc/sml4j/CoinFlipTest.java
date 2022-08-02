package dev.eruizc.sml4j;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CoinFlipTest {
	@Test
	void notSupported() {
		assertThrows(UnsupportedOperationException.class, () -> new CoinFlip());
	}

	public static class CoinFlip {
		private final StateMachine<Face, Action> sm = new StateMachineBuilder<Face, Action>()
			.initialState(Face.UNKNOWN)
			.allowTransition(Face.UNKNOWN, Action.FLIP, Face.HEADS)
			.allowTransition(Face.UNKNOWN, Action.FLIP, Face.TAILS)
			.build();

		public Face getFace() {
			return sm.getState();
		}

		public void flip() {
			sm.transition(Action.FLIP);
		}

		public enum Face {
			UNKNOWN, HEADS, TAILS;
		}

		public enum Action {
			FLIP;
		}
	}
}
