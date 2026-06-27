package br.uniube.structurejava.node;

/**
 * No de uma estrutura simplesmente encadeada.
 *
 * <p>Armazena um valor e uma unica referencia para o proximo no. E a unidade
 * basica de alocacao dinamica usada por {@code SinglyLinkedList},
 * {@code Stack} e {@code Queue}.</p>
 *
 * @param <T> tipo do valor armazenado
 */
public class SinglyNode<T> {

    /** Valor (carga util) guardado neste no. */
    private T value;

    /** Referencia para o proximo no; {@code null} indica fim da cadeia. */
    private SinglyNode<T> next;

    /**
     * Cria um no isolado, sem sucessor.
     *
     * @param value valor a armazenar
     */
    public SinglyNode(T value) {
        this.value = value;
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
     * Retorna o proximo no da cadeia.
     *
     * @return proximo no, ou {@code null} se este for o ultimo
     */
    public SinglyNode<T> getNext() {
        return next;
    }

    /**
     * Define o proximo no da cadeia.
     *
     * @param next no sucessor
     */
    public void setNext(SinglyNode<T> next) {
        this.next = next;
    }
}
