package adt.skipList;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import adt.skipList.SkipList;
import adt.skipList.SkipListImpl;
import adt.skipList.SkipListNode;

public class StudentSkipListTest {
	
	SkipList<String> skip;
	SkipListNode<String>[] array;

	@Before
	public void setUp() {
		skip = new SkipListImpl<String>(4);
	}

	@Test
	public void testInsert() {
		skip.insert(10, "A", 2);
		skip.insert(20, "B", 1);
		skip.insert(0, "C", 1);
		skip.insert(15, "D", 3);
		skip.insert(5, "E", 3);
		
		assertEquals(3, skip.height());

		assertEquals(5, skip.size());

		array = skip.toArray();
		assertEquals("[<ROOT,4,4>, <0,1>, <5,3>, <10,2>, <15,3>, <20,1>, <NIL,4>]", Arrays.toString(array));
		assertEquals(0, array[0].getForward(0).getKey());
		assertEquals(5, array[0].getForward(1).getKey());
		assertEquals(5, array[0].getForward(2).getKey());
		assertEquals(5, array[1].getForward(0).getKey());
		assertEquals(10, array[2].getForward(0).getKey());
		assertEquals(10, array[2].getForward(1).getKey());
		assertEquals(15, array[2].getForward(2).getKey());
		assertEquals(15, array[3].getForward(0).getKey());
		assertEquals(15, array[3].getForward(1).getKey());
		assertEquals(20, array[4].getForward(0).getKey());
		assertEquals(Integer.MAX_VALUE, array[4].getForward(1).getKey());
		assertEquals(Integer.MAX_VALUE, array[4].getForward(2).getKey());
		assertEquals(Integer.MAX_VALUE, array[5].getForward(0).getKey());

		List<String> aux = new ArrayList<>();
		for (int i = 1; i < array.length - 1; ++i)
			aux.add(array[i].getValue());

		assertEquals("[C, E, A, D, B]", aux.toString());

		skip.insert(0, "A", 1);
		skip.insert(5, "B", 3);
		skip.insert(10, "C", 2);
		skip.insert(15, "D", 3);
		skip.insert(20, "E", 1);

		assertEquals(5, skip.size());

		array = skip.toArray();
		assertEquals("[<ROOT,4,4>, <0,1>, <5,3>, <10,2>, <15,3>, <20,1>, <NIL,4>]", Arrays.toString(array));
		assertEquals(0, array[0].getForward(0).getKey());
		assertEquals(5, array[0].getForward(1).getKey());
		assertEquals(5, array[0].getForward(2).getKey());
		assertEquals(5, array[1].getForward(0).getKey());
		assertEquals(10, array[2].getForward(0).getKey());
		assertEquals(10, array[2].getForward(1).getKey());
		assertEquals(15, array[3].getForward(0).getKey());
		assertEquals(20, array[4].getForward(0).getKey());
		assertEquals(Integer.MAX_VALUE, array[4].getForward(1).getKey());
		assertEquals(Integer.MAX_VALUE, array[5].getForward(0).getKey());

		aux.clear();
		for (int i = 1; i < array.length - 1; ++i)
			aux.add(array[i].getValue());

		assertEquals("[A, B, C, D, E]", aux.toString());
	}

	@Test
	public void testSearch() {
		skip.insert(10, "A", 2);
		skip.insert(20, "B", 1);
		skip.insert(0, "C", 1);
		skip.insert(15, "D", 3);
		skip.insert(5, "E", 2);

		assertEquals("A", skip.search(10).getValue());
		assertEquals("B", skip.search(20).getValue());
		assertEquals("C", skip.search(0).getValue());
		assertEquals("D", skip.search(15).getValue());
		assertEquals("E", skip.search(5).getValue());

		assertEquals(null, skip.search(-10));
		assertEquals(null, skip.search(30));
		assertEquals(null, skip.search(9));
	}

	@Test
	public void testRemove() {
		skip.insert(10, "A", 1);
		skip.insert(20, "B", 2);
		skip.insert(0, "C", 2);
		skip.insert(15, "D", 3);
		skip.insert(5, "E", 1);

		skip.insert(-10, "F", 1);
		skip.insert(30, "G", 3);
		skip.insert(9, "H", 2);
		skip.insert(17, "I", 2);
		skip.insert(-2, "J", 1);

		assertEquals(10, skip.size());

		skip.remove(10);
		skip.remove(20);
		skip.remove(0);
		skip.remove(15);
		skip.remove(5);

		assertEquals(5, skip.size());

		array = skip.toArray();
		assertEquals("[<ROOT,4,4>, <-10,1>, <-2,1>, <9,2>, <17,2>, <30,3>, <NIL,4>]", Arrays.toString(array));
		assertEquals(-10, array[0].getForward(0).getKey());
		assertEquals(9, array[0].getForward(1).getKey());
		assertEquals(30, array[0].getForward(2).getKey());
		assertEquals(-2, array[1].getForward(0).getKey());
		assertEquals(9, array[2].getForward(0).getKey());
		assertEquals(17, array[3].getForward(0).getKey());
		assertEquals(17, array[3].getForward(1).getKey());
		assertEquals(30, array[4].getForward(0).getKey());
		assertEquals(30, array[4].getForward(1).getKey());
		assertEquals(Integer.MAX_VALUE, array[5].getForward(0).getKey());
		assertEquals(Integer.MAX_VALUE, array[5].getForward(1).getKey());
		assertEquals(Integer.MAX_VALUE, array[5].getForward(2).getKey());

		skip.remove(-10);
		skip.remove(30);
		skip.remove(9);
		skip.remove(17);
		skip.remove(-2);

		assertEquals(0, skip.size());

		array = skip.toArray();
		assertEquals("[<ROOT,4,4>, <NIL,4>]", Arrays.toString(array));
		assertEquals(Integer.MAX_VALUE, array[0].getForward(0).getKey());
	}
	
	@Test
    public void testArrayLevel(){
		SkipListImpl<String> skipList = new SkipListImpl<String>(5);
        skipList.insert(8, "A", 4);
        skipList.insert(14, "B", 4);
        skipList.insert(21, "C", 4);
        skipList.insert(4, "D", 3);
        skipList.insert(12, "E", 3);
        skipList.insert(17, "F", 2);
        skipList.insert(18, "G", 3);
        skipList.insert(25, "H", 2);
        skipList.insert(1, "H", 5);
       
        /*
         * Faça um algoritmo na skip list que imprime os nós da skip list por altura e ordem crescente. Por exemplo,
            se uam skip list possui nós com altura 4 (8, 14, 21) e nós com altura 3 (4, 12, 18) e nós com altura 2 (17,25):
            [(4,3),(8,4),(12,3),(14,4),(17,2),(18,3),(21,4),(25,2), imprimiria a sequencia 8,14,21,4,12,18,17,25.
         */
       
        array = skipList.toArrayLevel();
        assertEquals("[<1,5>, <8,4>, <14,4>, <21,4>, <4,3>, <12,3>, <18,3>, <17,2>, <25,2>]", Arrays.toString(skipList.toArrayLevel()));
    }
	
	@Test
    public void testChangeLevel(){
		SkipListImpl<String> skipList = new SkipListImpl<String>(5);
        skipList.insert(8, "8", 3);
        skipList.insert(7, "7", 1);
        skipList.insert(1, "1", 1);
        skipList.insert(4, "4", 2);
       
        skipList.changeHeight(7, 2);
        array = skipList.toArrayLevel();
        assertEquals("[<8,3>, <4,2>, <7,2>, <1,1>]", Arrays.toString(array));
        skipList.changeHeight(7, 1);
        array = skipList.toArrayLevel();
        assertEquals("[<8,3>, <4,2>, <1,1>, <7,1>]", Arrays.toString(array));
       
        skipList.changeHeight(4, 1);
        array = skipList.toArrayLevel();
        assertEquals("[<8,3>, <1,1>, <4,1>, <7,1>]", Arrays.toString(array));
       
        skipList.changeHeight(4, 3);
        array = skipList.toArrayLevel();
        assertEquals("[<4,3>, <8,3>, <1,1>, <7,1>]", Arrays.toString(array));
       
        skipList.changeHeight(8, 1);
        array = skipList.toArrayLevel();
        assertEquals("[<4,3>, <1,1>, <7,1>, <8,1>]", Arrays.toString(array));
       
        skipList.changeHeight(4, 1);
        array = skipList.toArrayLevel();
        assertEquals("[<1,1>, <4,1>, <7,1>, <8,1>]", Arrays.toString(array));
    }
}
