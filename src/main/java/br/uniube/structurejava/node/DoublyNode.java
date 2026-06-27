package br.uniube.structurejava.node;

/**
 * No de uma estrutura duplamente encadeada.
 *
 * <p>Mantem referencias para o no anterior e para o proximo, permitindo
 * percurso e remocao em ambos os sentidos em tempo {@code O(1)} quando o no
 * ja e conhecido. Usado por {@code DoublyLinkedList}.</p>
 *
 * @param <T> tipo do valor armazenado
 */
public class DoublyNode<T> {

    /** Valor (carga util) guardado neste no. */
    private T value;

    /** Referencia para o no anterior; {@code null} indica inicio da cadeia. */
    private DoublyNode<T> prev;

    /** Referencia para o proximo no; {@code null} indica fim da cadeia. */
    private DoublyNode<T> next;

    /**
     * Cria um no isolado, sem antecessor nem sucessor.
     *
     * @param value valor a armazenar
     */
    public DoublyNode(T value) {
        this.value = value;
        this.prev = null;
        this.next = null;
    }

    /**
     * Retorna o valor armazenado.
     *
     * @return valor do no
     */
    public T getValue() {
        return value;
    }

    /**
     * Define o valor armazenado.
     *
     * @param value novo valor
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Retorna o no anterior.
     *
     * @return no anterior, ou {@code null} se este for o primeiro
     */
    public DoublyNode<T> getPrev() {
        return prev;
    }

    /**
     * Define o no anterior.
     *
     * @param prev no antecessor
     */
    public void setPrev(DoublyNode<T> prev) {
        this.prev = prev;
    }

    /**
     * Retorna o proximo no.
     *
     * @return proximo no, ou {@code null} se este for o ultimo
     */
    public DoublyNode<T> getNext() {
        return next;
    }

    /**
     * Define o proximo no.
     *
     * @param next no sucessor
     */
    public void setNext(DoublyNode<T> next) {
        this.next = next;
    }
}
