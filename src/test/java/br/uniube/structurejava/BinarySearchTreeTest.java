package br.uniube.structurejava;

import br.uniube.structurejava.tree.BinarySearchTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BinarySearchTreeTest {

    private BinarySearchTree<Integer> arvore() {
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
        for (int v : new int[]{50, 30, 70, 20, 40, 60, 80}) {
            t.insert(v);
        }
        return t;
    }

    @Test
    void inOrderRetornaOrdenado() {
        assertEquals("[20, 30, 40, 50, 60, 70, 80]", arvore().inOrder().toString());
    }

    @Test
    void ignoraDuplicatasENull() {
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
        assertTrue(t.insert(10));
        assertFalse(t.insert(10)); // duplicata
        assertFalse(t.insert(null)); // null seguro
        assertEquals(1, t.size());
    }

    @Test
    void containsMinMaxAltura() {
        BinarySearchTree<Integer> t = arvore();
        assertTrue(t.contains(60));
        assertFalse(t.contains(99));
        assertEquals(20, t.min());
        assertEquals(80, t.max());
        assertEquals(2, t.height());
    }

    @Test
    void removeFolhaUmFilhoEDoisFilhos() {
        BinarySearchTree<Integer> t = arvore();
        assertTrue(t.remove(20));  // folha
        assertEquals("[30, 40, 50, 60, 70, 80]", t.inOrder().toString());
        assertTrue(t.remove(30));  // um filho (40)
        assertEquals("[40, 50, 60, 70, 80]", t.inOrder().toString());
        assertTrue(t.remove(50));  // raiz, dois filhos
        assertEquals("[40, 60, 70, 80]", t.inOrder().toString());
        assertFalse(t.remove(999)); // ausente
    }

    @Test
    void arvoreVaziaImuneAExcecoes() {
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
        assertNull(t.min());
        assertNull(t.max());
        assertFalse(t.remove(1));
        assertFalse(t.contains(1));
        assertEquals(-1, t.height());
        assertTrue(t.inOrder().isEmpty());
    }
}
