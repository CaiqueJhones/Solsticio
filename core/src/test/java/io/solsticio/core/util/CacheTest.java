package io.solsticio.core.util;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import org.junit.Before;
import org.junit.Test;

public class CacheTest {
	
	private static final int MAX = 10;
	private Cache<Object> cache;

	@Before
	public void setUp() throws Exception {
		cache = new Cache<>(MAX);
	}
	
	@Test
	public void testEmptyCahe() throws Exception {
		assertNull(cache.current());
	}

	@Test
	public void addOneObject() {
		Object Object = mock(Object.class);
		cache.add(Object);
		assertEquals(Object, cache.current());
	}

	@Test
	public void addTwoObjectAndDown() {
		Object ObjectA = mock(Object.class);
		Object ObjectB = mock(Object.class);
		cache.add(ObjectA); cache.add(ObjectB);
		assertEquals(ObjectA, cache.down());
	}
	
	@Test
	public void addTwoObjectAndDownUp() {
		Object ObjectA = mock(Object.class);
		Object ObjectB = mock(Object.class);
		cache.add(ObjectA); cache.add(ObjectB);
		cache.down();
		assertEquals(ObjectB, cache.up());
	}
	
	@Test
	public void addMaxObjects() throws Exception {
		int hash = 0;
		for (int i = 0; i < MAX; i++) {
			Object Object = mock(Object.class);
			if (i == 1)
				hash = Object.hashCode();
			cache.add(Object);
		}
		cache.add(mock(Object.class));
		assertEquals(hash, cache.first().hashCode());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addNullObject() throws Exception {
		cache.add(null);
	}
	
	@Test
	public void downWithReturnNull() throws Exception {
		cache.add(mock(Object.class));
		assertNull(cache.down());
	}
	
	@Test
	public void upWithReturnNull() throws Exception {
		cache.add(mock(Object.class));
		assertNull(cache.up());
	}
}
