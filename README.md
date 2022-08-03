# State Machine Library For Java

State Machines done easy, in a convenient Java package. Here's an example,
you can find more in the [testing suite](https://github.com/eruizc-dev/sml4j/tree/master/src/test/java/dev/eruizc/sml4j/usecases):

```java
import dev.eruizc.sml4j.StateMachine;
import dev.eruizc.sml4j.StateMachineBuilder;

public class LightSwitch {
	private final StateMachine<State, Action> sm = new StateMachineBuilder<State, Action>()
		.initialState(State.OFF)				// Starts OFF
		.allowTransition(State.OFF, Action.TURN_ON, State.ON)	// When off, allow it to turn_on, to change state to on
		.allowTransition(State.ON, Action.TURN_OFF, State.OFF)	// When on, allow turn_off, to change state to off
		.build();

	public boolean isOn() {
		return sm.getState().equals(State.ON);
	}

	public void turnOn() {
		sm.transition(Action.TURN_ON);
	}

	public void turnOff() {
		sm.transition(Action.TURN_OFF);
	}

	enum State { ON, OFF };
	enum Action { TURN_ON, TURN_OFF };
}
```

### Installing

```xml
<!-- pom.xml -->
<dependency>
  <groupId>dev.eruizc</groupId>
  <artifactId>sml4j</artifactId>
  <version>0.2.0</version>
</dependency>
```

```groovy
// build.gradle
dependencies {
  implementation('dev.eruizc:sml4j:0.2.0')
}

```

### Versioning

This project uses [Semantic Versioning](https://semver.org/), keep in mind:
 - No breaking changes between MINOR and PATCH versions
 - Versions below 1.0.0 are not considered stable, previously mentioned
   rules might not apply

### Roadmap

 - Thread safety
 - Support for Non-Deterministic Finite State Machines
