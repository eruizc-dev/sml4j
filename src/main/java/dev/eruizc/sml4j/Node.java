package dev.eruizc.sml4j;

import java.util.*;

class Node<V, T> {
	private V value;
	private Set<Node<V, T>> parents;
	private Map<T, Node<V, T>> childs;

	Node(V value) {
		this.value = value;
		this.parents = new HashSet<>();
		this.childs = new HashMap<>();
	}

	V getValue() {
		return this.value;
	}

	Set<T> getChilds() {
		return childs.keySet();
	}

	/**
	 * @throws UnsupportedOperationException If the graph becomes non-deterministic
	 */
	void addChild(T transition, Node<V, T> child) {
		if (childs.containsKey(transition)) {
			throw new UnsupportedOperationException("Transition defines a Non-Deterministic State Machine, which are not supported by sml4j");
		}
		child.parents.add(this);
		childs.put(transition, child);
	}

	Node<V, T> next(T transition) throws IllegalTransitionException {
		var next = childs.get(transition);
		if (next == null) {
			throw new IllegalTransitionException(value, transition);
		}
		return next;
	}
}
