package ua.nure.dlubovskyi_oleg.Practice2;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 
 * @author Dlubovskyi Oleg Implemented logic of MyList
 *
 */
public class MyListImpl implements MyList, ListIterable {
	private int criticalSize;
	private int size;
	private Object[] array;

	public MyListImpl() {
		criticalSize = 5;
		size = 0;
		array = new Object[criticalSize];
	}

	/**
	 * Appends the specified element to the end of this list.
	 */
	@Override
	public void add(Object e) {
		if (size < criticalSize) {
			array[size] = e;
			size++;
		} else {
			Object[] tempArray = new Object[++criticalSize];
			for (int i = 0; i < size; i++) {
				tempArray[i] = array[i];
			}
			array = tempArray;
			array[size] = e;
			++size;
		}
	}

	/**
	 * Removes all of the elements from this list.
	 */
	@Override
	public void clear() {
		criticalSize = 5;
		array = new Object[criticalSize];
		size = 0;

	}

	/**
	 * Removes the first occurrence of the specified element.
	 */
	@Override
	public boolean remove(Object o) {
		for (int i = 0; i < size; i++) {
			if (array[i].equals(o)) {
				for (int j = i; j < size; j++) {
					array[j] = array[j + 1];
				}
				--size;
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence. ​
	 */
	@Override
	public Object[] toArray() {
		return Arrays.copyOf(array, size);
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns true if this list contains the specified element.
	 */
	@Override
	public boolean contains(Object o) {
		for (int i = 0; i < size; i++) {
			if (o == null && array[i] == null) {
				return true;
			} else if (array[i].equals(o)) {
				return true;

			}
		}
		return false;
	}

	/**
	 * Returns true if this list contains all of the elements of the specified list.
	 */
	@Override
	public boolean containsAll(MyList c) {
		Object[] paramArray = c.toArray();

		for (int i = 0; i < c.size(); i++) {
			if (!contains(paramArray[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Convert array to string.
	 */
	@Override
	public String toString() {
		return Arrays.toString(Arrays.copyOf(array, size));
	}

	private class IteratorImpl implements Iterator<Object> {
		/**
		 * Returns true if the iteration has more elements.
		 */
		protected int iteratorPosition = -1;
		private int lastRemovedPosition = -1;

		public boolean hasNext() {
			return iteratorPosition < size - 1;
		}

		/**
		 * Returns the next element in the iteration.
		 */
		public Object next() {
			return array[++iteratorPosition];
		}

		/**
		 * Removes from the underlying collection the last element returned by this
		 * iterator.
		 */
		public void remove() {
			if (iteratorPosition == -1 || lastRemovedPosition == iteratorPosition + 1) {
				throw new IllegalStateException();
			} else {
				for (int j = iteratorPosition; j < size; j++) {
					array[j] = array[j + 1];
				}
				lastRemovedPosition = iteratorPosition;
				iteratorPosition -= 1;
				--size;

			}
		}
	}

	/**
	 * Returns {@link IteratorImpl} object.
	 * 
	 * @see ListIteratorImpl
	 * 
	 */
	@Override
	public Iterator<Object> iterator() {
		return new IteratorImpl();
	}

	private class ListIteratorImpl extends IteratorImpl implements ListIterator {
		private int lastChangedElementPosition = -1;

		/*
		 * Returns true if this list iterator has more elements when traversing the list
		 * in the reverse direction.
		 */
		@Override
		public boolean hasPrevious() {

			return ((iteratorPosition >= 0) && (iteratorPosition <= size - 1));
		}

		/**
		 * Returns the previous element in the list and moves the cursor position
		 * backwards.
		 */
		@Override
		public Object previous() {
			lastChangedElementPosition = -1;
			return array[iteratorPosition--];

		}

		/**
		 * Replaces the last element returned by next or previous with the specified
		 * element.
		 */
		@Override
		public void set(Object e) {
			if (lastChangedElementPosition != -1) {
				throw new IllegalStateException();
			}
			array[iteratorPosition] = e;
		}

		/**
		 * @see IteratorImpl
		 */
		@Override
		public void remove() {
			super.remove();
		}
	}

	/**
	 * Returns {@link ListIteratorImpl} object.
	 * 
	 * @return ListIteratorImpl
	 */
	@Override
	public ListIterator listIterator() {

		return new ListIteratorImpl();
	}

}
