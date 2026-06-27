package br.uniube.structurejava;

import br.uniube.structurejava.queue.Queue;
import br.uniube.structurejava.stack.Stack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StackQueueTest {

    @Test
    void pilhaRespeitaLifo() {
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(2);
        s.push(3);
        assertEquals(3, s.peek());
        assertEquals(3, s.pop());
        assertEquals(2, s.pop());
        assertEquals(1, s.pop());
        assertTrue(s.isEmpty());
    }

    @Test
    void pilhaVaziaRetornaNull() {
        Stack<Integer> s = new Stack<>();
        assertNull(s.pop());
        assertNull(s.peek());
        assertEquals(0, s.size());
    }

    @Test
    void filaRespeitaFifo() {
        Queue<Integer> q = new Queue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        assertEquals(1, q.peek());
        assertEquals(1, q.dequeue());
        assertEquals(2, q.dequeue());
        assertEquals(3, q.dequeue());
        assertTrue(q.isEmpty());
    }

    @Test
    void filaVaziaRetornaNull() {
        Queue<Integer> q = new Queue<>();
        assertNull(q.dequeue());
        assertNull(q.peek());
        assertEquals(0, q.size());
    }

    @Test
    void filaReutilizaAposEsvaziar() {
        Queue<Integer> q = new Queue<>();
        q.enqueue(1);
        q.dequeue();
        q.enqueue(2); // rear deve ter sido zerado corretamente
        assertEquals(2, q.peek());
        assertEquals(1, q.size());
    }
}
