package dev.eruizc.sml4j;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

/*
 * State machine of a card trhrough a Scrum board
 *
 * A card starts in the backlog
 * Can be started
 * Once finished you request a review
 * Once reviewed it can be approved or rejected
 * If approved, goes to done
 * If rejected, goes back to wip
 * */
public class ScrumTest {
	StateMachine<Column, Actions> card;

	static enum Column {
		BACKLOG, WIP, REVIEW, DONE;
	}

	static enum Actions {
		START, REQUEST_REVIEW, APPROVE, REJECT;
	}

	@BeforeEach
	void beforeEach() {
		card = new StateMachine<>(
				Column.BACKLOG,
				new Transition<>(Column.BACKLOG, Actions.START, Column.WIP),
				new Transition<>(Column.WIP, Actions.REQUEST_REVIEW, Column.REVIEW),
				new Transition<>(Column.REVIEW, Actions.APPROVE, Column.DONE),
				new Transition<>(Column.REVIEW, Actions.REJECT, Column.WIP)
		);
	}

	@Test
	void completeFlow() {
		assertEquals(Column.BACKLOG, card.getState());

		card.transition(Actions.START);
		assertEquals(Column.WIP, card.getState());

		card.transition(Actions.REQUEST_REVIEW);
		assertEquals(Column.REVIEW, card.getState());

		card.transition(Actions.REJECT);
		assertEquals(Column.WIP, card.getState());

		card.transition(Actions.REQUEST_REVIEW);
		assertEquals(Column.REVIEW, card.getState());

		card.transition(Actions.APPROVE);
		assertEquals(Column.DONE, card.getState());
	}

	@Test
	void canBeRejectedManyTimes() {
		card.transition(Actions.START);
		card.transition(Actions.REQUEST_REVIEW);

		for (int i = 0; i < 69; i++) {
			card.transition(Actions.REJECT);
			assertEquals(Column.WIP, card.getState());

			card.transition(Actions.REQUEST_REVIEW);
			assertEquals(Column.REVIEW, card.getState());
		}

		card.transition(Actions.APPROVE);
		assertEquals(Column.DONE, card.getState());
	}
}
