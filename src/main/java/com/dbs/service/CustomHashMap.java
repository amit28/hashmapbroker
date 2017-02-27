package com.dbs.service;

/*
 * This is a custom Hashmap implementation, it implements only basic methods to put,get and remove from map.
 */

public class CustomHashMap<K, V> {

	private int bucketSize = 16;
	KeyValuePair<K, V> bucket[] = new KeyValuePair[bucketSize];

	public CustomHashMap() {
	}

	public V get(Object key) {
		if (key == null)
			return bucket[0].getValue();

		KeyValuePair<K, V> node;
		int idx = hash(key);
		node = bucket[idx];
		if (node != null) {
			while (node != null) {
				if (key.equals(node.getKey())) {
					return node.getValue();
				}
				node = node.getNext();
			}
		}
		return null;
	}

	public V put(K key, V value) {
		int hashidx = hash(key);
		KeyValuePair<K, V> node = bucket[hashidx];
		if (node == null) {
			node = new KeyValuePair<K, V>(key, value);
			bucket[hashidx] = node;
			return null;
		} else {
			while (node != null) {
				K existingKey = node.getKey();
				if (existingKey.equals(key)) {
					V prevValue = node.getValue();
					node.setValue(value);
					return prevValue;
				}
				node = node.getNext();
			}
			node.setNext(new KeyValuePair<K, V>(key, value));
			return null;

		}
	}

	public V remove(Object key) {
		V value;
		if (bucket[hash(key)] != null) {
			KeyValuePair<K, V> node = bucket[hash(key)];
			if (key.equals(node.getKey()) && node.next == null) {
				value = node.getValue();
				bucket[hash(key)] = null;
				return value;
			} else if (key.equals(node.getKey()) && node.next != null) {
				KeyValuePair<K, V> nxtNode = node.getNext();
				node.next = null;
				value = node.getValue();
				bucket[hash(key)] = nxtNode;
				return value;
			} else {
				KeyValuePair<K, V> curr = node.next, prev = node;
				while (curr != null) {
					if (key.equals(curr.key)) {
						value = curr.getValue();
						prev.next = curr.next;
						curr.next = null;
						return value;
					}
					prev = curr;
					curr = curr.next;
				}
			}
		}
		return null;
	}

	public Boolean containsKey(Object key) {
		if (bucket[hash(key)] != null) {
			KeyValuePair<K, V> node = bucket[hash(key)];
			while (node.next != null) {
				if (key.equals(node.key)) {
					return true;
				}
				node = node.getNext();
			}
		}
		return false;
	}

	private int hash(Object key) {
		return (key.hashCode()) % bucketSize;
	}

	class KeyValuePair<K, V> {
		K key;
		V value;
		KeyValuePair<K, V> next = null;

		public KeyValuePair(K key, V value) {
			super();
			this.key = key;
			this.value = value;
			this.next = null;
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}

		public KeyValuePair<K, V> getNext() {
			return next;
		}

		public void setNext(KeyValuePair<K, V> next) {
			this.next = next;
		}

	}
}
