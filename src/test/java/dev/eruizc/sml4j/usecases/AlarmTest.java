package dev.eruizc.sml4j.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.eruizc.sml4j.*;
import org.junit.jupiter.api.*;

/*
 * State machine of an alarm that can be triggered ON or OFF
 *
 * This test ensures circular actions work: an action that is allowed, but doesn't change the current state
 * */
public class AlarmTest {
	StateMachine<State, Action> alarm;

	static enum State {
		ON, OFF;
	}

	static enum Action {
		TURN_ON, TURN_OFF;
	}

	@BeforeEach
	void beforeEach() {
		alarm = new StateMachineBuilder<State, Action>(State.OFF)
			.allowTransition(State.OFF, Action.TURN_ON, State.ON)
			.allowTransition(State.OFF, Action.TURN_OFF, State.OFF)
			.allowTransition(State.ON, Action.TURN_ON, State.ON)
			.allowTransition(State.ON, Action.TURN_OFF, State.OFF)
			.build();
	}

	@Test
	void completeFlow() {
		assertEquals(State.OFF, alarm.getState());

		alarm.transition(Action.TURN_OFF);
		assertEquals(State.OFF, alarm.getState());

		alarm.transition(Action.TURN_ON);
		assertEquals(State.ON, alarm.getState());

		alarm.transition(Action.TURN_ON);
		assertEquals(State.ON, alarm.getState());

		alarm.transition(Action.TURN_OFF);
		assertEquals(State.OFF, alarm.getState());

		alarm.transition(Action.TURN_ON);
		assertEquals(State.ON, alarm.getState());

		alarm.transition(Action.TURN_OFF);
		assertEquals(State.OFF, alarm.getState());
	}
}
