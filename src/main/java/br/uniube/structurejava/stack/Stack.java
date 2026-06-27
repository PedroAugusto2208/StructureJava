package br.uniube.structurejava.stack;

import br.uniube.structurejava.node.SinglyNode;

/**
 * Pilha generica com politica <strong>LIFO</strong> (Last In, First Out).
 *
 * <p>Implementada sobre nos simplesmente encadeados alocados dinamicamente —
 * sem vetor de tamanho fixo, portanto <em>nunca</em> ocorre transbordamento
 * (stack overflow) por capacidade. O topo da pilha e sempre o {@code head} da
 * cadeia, o que torna {@link #push} e {@link #pop} operacoes {@code O(1)}.</p>
 *
 * <p><strong>Imunidade a excecoes:</strong> {@link #pop} e {@link #peek} em
 * pilha vazia retornam {@code null} em vez de lancar excecao.</p>
 *
 * @param <T> tipo dos elementos
 */
public class Stack<T> {

    /** No do topo da pilha; {@code null} quando vazia. */
    private SinglyNode<T> top;

    /** Quantidade de elementos. */
    private int size;

    /** Cria uma pilha vazia. */
    public Stack() {
        this.top = null;
        this.size = 0;
    }

    /**
     * Empilha um elemento no topo. Complexidade {@code O(1)}.
     *
     * @param value elemento a empilhar
     */
    public void push(T value) {
        SinglyNode<T> node = new SinglyNode<>(value);
        node.setNext(top);
        top = node;
        size++;
    }

    /**
     * Desempilha e retorna o elemento do topo. Complexidade {@code O(1)}.
     *
     * @return elemento removido, ou {@code null} se a pilha estiver vazia
     */
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        SinglyNode<T> removed = top;
        top = top.getNext();
        removed.setNext(null);
        size--;
        return removed.getValue();
    }

    /**
     * Consulta o elemento do topo sem remove-lo. Complexidade {@code O(1)}.
     *
     * @return elemento do topo, ou {@code null} se a pilha estiver vazia
     */
    public T peek() {
        return isEmpty() ? null : top.getValue();
    }

    /**
     * Informa se a pilha nao possui elementos.
     *
     * @return {@code true} se vazia
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Retorna a quantidade de elementos.
     *
     * @return tamanho da pilha
     */
    public int size() {
        return size;
    }

    /** Esvazia a pilha, liberando os nos para o coletor de lixo. */
    public void clear() {
        SinglyNode<T> current = top;
        while (current != null) {
            SinglyNode<T> next = current.getNext();
            current.setNext(null);
            current = next;
        }
        top = null;
        size = 0;
    }

    /**
     * Representacao textual do topo para a base, no formato {@code [topo, ..., base]}.
     *
     * @return string com os elementos
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        SinglyNode<T> current = top;
        while (current != null) {
            sb.append(current.getValue());
            if (current.getNext() != null) {
                sb.append(", ");
            }
            current = current.getNext();
        }
        return sb.append("]").toString();
    }
}
