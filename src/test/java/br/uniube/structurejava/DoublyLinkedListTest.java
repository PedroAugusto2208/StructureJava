package br.uniube.structurejava;

import br.uniube.structurejava.list.DoublyLinkedList;
import br.uniube.structurejava.util.Comparator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DoublyLinkedListTest {

    @Test
    void inserePontasERemoveAmbas() {
        DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
        l.addLast(2);
        l.addFirst(1);
        l.addLast(3);
        assertEquals("[1, 2, 3]", l.toString());
        assertEquals(1, l.removeFirst());
        assertEquals(3, l.removeLast());
        assertEquals("[2]", l.toString());
    }

    @Test
    void removeMeioReligaPonteiros() {
        DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
        for (int v : new int[]{1, 2, 3, 4, 5}) {
            l.addLast(v);
        }
        assertEquals(3, l.remove(2));
        assertEquals("[1, 2, 4, 5]", l.toString());
        assertEquals(4, l.size());
    }

    @Test
    void removeRetornaNullEmListaVazia() {
        DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
        assertNull(l.removeFirst());
        assertNull(l.removeLast());
        assertNull(l.remove(5));
    }

    @Test
    void insercaoOrdenadaEInversao() {
        DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
        Comparator<Integer> c = Comparator.natural();
        for (int v : new int[]{30, 10, 20}) {
            l.insertSorted(v, c);
        }
        assertEquals("[10, 20, 30]", l.toString());
        l.reverse();
        assertEquals("[30, 20, 10]", l.toString());
        // ponteiros consistentes apos inversao:
        assertEquals(30, l.removeFirst());
        assertEquals(10, l.removeLast());
    }

    @Test
    void concatenacaoEsvaziaOutra() {
        DoublyLinkedList<String> a = new DoublyLinkedList<>();
        a.addLast("x");
        DoublyLinkedList<String> b = new DoublyLinkedList<>();
        b.addLast("y");
        b.addLast("z");
        a.concat(b);
        assertEquals("[x, y, z]", a.toString());
        assertTrue(b.isEmpty());
    }
}
