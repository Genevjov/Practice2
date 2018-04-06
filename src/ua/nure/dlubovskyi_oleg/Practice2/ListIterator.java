package ua.nure.dlubovskyi_oleg.Practice2;

import java.util.Iterator;

public interface ListIterator extends Iterator<Object> {

	boolean hasPrevious();

	Object previous();

	void set(Object e);

	void remove();

}
