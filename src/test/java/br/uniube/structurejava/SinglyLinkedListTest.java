package br.uniube.structurejava;

import br.uniube.structurejava.list.SinglyLinkedList;
import br.uniube.structurejava.util.Comparator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SinglyLinkedListTest {

    @Test
    void inserePontasEMantemTamanho() {
        SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
        l.addLast(2);
        l.addFirst(1);
        l.addLast(3);
        assertEquals(3, l.size());
        assertEquals("[1, 2, 3]", l.toString());
        assertEquals(2, l.get(1));
    }

    @Test
    void insercaoPorIndiceValidaLimites() {
        SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
        assertTrue(l.add(0, 10));
        assertTrue(l.add(1, 30));
        assertTrue(l.add(1, 20));
        assertEquals("[10, 20, 30]", l.toString());
        assertFalse(l.add(99, 1)); // indice invalido ignorado
        assertFalse(l.add(-1, 1));
    }

    @Test
    void removeRetornaNullEmListaVazia() {
        SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
        assertNull(l.removeFirst());
        assertNull(l.removeLast());
        assertNull(l.remove(0));
        assertNull(l.get(0));
    }

    @Test
    void insercaoOrdenadaMantemOrdem() {
        SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
        Comparator<Integer> c = Comparator.natural();
        for (int v : new int[]{5, 1, 4, 2, 3}) {
            l.insertSorted(v, c);
        }
        assertEquals("[1, 2, 3, 4, 5]", l.toString());
    }

    @Test
    void inversao() {
        SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
        for (int v : new int[]{1, 2, 3, 4}) {
            l.addLast(v);
        }
        l.reverse();
        assertEquals("[4, 3, 2, 1]", l.toString());
        l.addLast(0); // tail deve ter sido atualizado pela inversao
        assertEquals("[4, 3, 2, 1, 0]", l.toString());
    }

    @Test
    void concatenacaoEsvaziaOutra() {
        SinglyLinkedList<Integer> a = new SinglyLinkedList<>();
        a.addLast(1);
        a.addLast(2);
        SinglyLinkedList<Integer> b = new SinglyLinkedList<>();
        b.addLast(3);
        b.addLast(4);
        a.concat(b);
        assertEquals("[1, 2, 3, 4]", a.toString());
        assertTrue(b.isEmpty());
        assertEquals(0, b.size());
    }
}
