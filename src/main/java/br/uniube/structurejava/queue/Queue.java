package br.uniube.structurejava.queue;

import br.uniube.structurejava.node.SinglyNode;

/**
 * Fila generica com politica <strong>FIFO</strong> (First In, First Out).
 *
 * <p>Implementada sobre nos simplesmente encadeados alocados dinamicamente.
 * Mantem referencias para o inicio ({@code front}, de onde se remove) e para o
 * fim ({@code rear}, onde se insere), garantindo {@link #enqueue} e
 * {@link #dequeue} em tempo {@code O(1)}.</p>
 *
 * <p><strong>Imunidade a excecoes:</strong> {@link #dequeue} e {@link #peek} em
 * fila vazia retornam {@code null} em vez de lancar excecao.</p>
 *
 * @param <T> tipo dos elementos
 */
public class Queue<T> {

    /** No do inicio da fila (proximo a sair); {@code null} quando vazia. */
    private SinglyNode<T> front;

    /** No do fim da fila (ultimo inserido); {@code null} quando vazia. */
    private SinglyNode<T> rear;

    /** Quantidade de elementos. */
    private int size;

    /** Cria uma fila vazia. */
    public Queue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    /**
     * Insere um elemento no fim da fila. Complexidade {@code O(1)}.
     *
     * @param value elemento a enfileirar
     */
    public void enqueue(T value) {
        SinglyNode<T> node = new SinglyNode<>(value);
        if (isEmpty()) {
            front = node;
            rear = node;
        } else {
            rear.setNext(node);
            rear = node;
        }
        size++;
    }

    /**
     * Remove e retorna o elemento do inicio da fila. Complexidade {@code O(1)}.
     *
     * @return elemento removido, ou {@code null} se a fila estiver vazia
     */
    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        SinglyNode<T> removed = front;
        front = front.getNext();
        if (front == null) {
            rear = null;
        }
        removed.setNext(null);
        size--;
        return removed.getValue();
    }

    /**
     * Consulta o elemento do inicio sem remove-lo. Complexidade {@code O(1)}.
     *
     * @return elemento do inicio, ou {@code null} se a fila estiver vazia
     */
    public T peek() {
        return isEmpty() ? null : front.getValue();
    }

    /**
     * Informa se a fila nao possui elementos.
     *
     * @return {@code true} se vazia
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Retorna a quantidade de elementos.
     *
     * @return tamanho da fila
     */
    public int size() {
        return size;
    }

    /** Esvazia a fila, liberando os nos para o coletor de lixo. */
    public void clear() {
        SinglyNode<T> current = front;
        while (current != null) {
            SinglyNode<T> next = current.getNext();
            current.setNext(null);
            current = next;
        }
        front = null;
        rear = null;
        size = 0;
    }

    /**
     * Representacao textual do inicio para o fim, formato {@code [inicio, ..., fim]}.
     *
     * @return string com os elementos
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        SinglyNode<T> current = front;
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
