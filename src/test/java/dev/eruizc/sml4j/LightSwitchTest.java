package dev.eruizc.sml4j;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

/*
 * Simple state machine with only two states
 *
 * Starts off
 * When off: can be turned on
 * When on: can be turned off
 * */
public class LightSwitchTest {
	StateMachine<Status, Actions> lightSwitch;

	@BeforeEach
	void beforeEach() {
		lightSwitch = new StateMachineBuilder<Status, Actions>()
				.initialState(Status.OFF)
				.allowTransition(Status.OFF, Actions.TURN_ON, Status.ON)
				.allowTransition(Status.ON, Actions.TURN_OFF, Status.OFF)
				.build();
	}

	@Test
	void startsOff() {
		assertEquals(Status.OFF, lightSwitch.getState());
	}

	@Test
	void turnsOn() {
		lightSwitch.transition(Actions.TURN_ON);
		assertEquals(Status.ON, lightSwitch.getState());
	}

	@Test
	void turnsOnAndOff() {
		lightSwitch.transition(Actions.TURN_ON);
		lightSwitch.transition(Actions.TURN_OFF);
		assertEquals(Status.OFF, lightSwitch.getState());
	}

	@Test
	void cannotTurnOff() {
		assertThrows(IllegalStateException.class, () -> lightSwitch.transition(Actions.TURN_OFF));
	}

	@Test
	void cannotTurnOnAndoffTwice() {
		lightSwitch.transition(Actions.TURN_ON);
		lightSwitch.transition(Actions.TURN_OFF);
		assertThrows(IllegalStateException.class, () -> lightSwitch.transition(Actions.TURN_OFF));
	}

	static enum Status {
		ON, OFF;
	}

	static enum Actions {
		TURN_ON, TURN_OFF;
	}
}
