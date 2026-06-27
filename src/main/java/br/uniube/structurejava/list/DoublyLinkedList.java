package br.uniube.structurejava.list;

import br.uniube.structurejava.node.DoublyNode;
import br.uniube.structurejava.util.Comparator;

/**
 * Lista linear <strong>duplamente encadeada</strong> e generica.
 *
 * <p>Cada elemento e um {@link DoublyNode} com ligacoes para o antecessor e o
 * sucessor. As referencias {@code head} e {@code tail} permitem insercao e
 * remocao em <em>ambas</em> as pontas em tempo constante {@code O(1)} — vantagem
 * sobre a lista simplesmente encadeada no {@code removeLast}.</p>
 *
 * <p><strong>Imunidade a excecoes:</strong> acessos invalidos retornam
 * {@code null}/{@code false} em vez de lancar excecao.</p>
 *
 * @param <T> tipo dos elementos
 */
public class DoublyLinkedList<T> {

    /** Primeiro no; {@code null} quando vazia. */
    private DoublyNode<T> head;

    /** Ultimo no; {@code null} quando vazia. */
    private DoublyNode<T> tail;

    /** Quantidade de elementos. */
    private int size;

    /** Cria uma lista vazia. */
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Informa se a lista nao possui elementos.
     *
     * @return {@code true} se vazia
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Retorna a quantidade de elementos.
     *
     * @return tamanho da lista
     */
    public int size() {
        return size;
    }

    /**
     * Insere um elemento no inicio. Complexidade {@code O(1)}.
     *
     * @param value elemento a inserir
     */
    public void addFirst(T value) {
        DoublyNode<T> node = new DoublyNode<>(value);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            node.setNext(head);
            head.setPrev(node);
            head = node;
        }
        size++;
    }

    /**
     * Insere um elemento no fim. Complexidade {@code O(1)}.
     *
     * @param value elemento a inserir
     */
    public void addLast(T value) {
        DoublyNode<T> node = new DoublyNode<>(value);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            node.setPrev(tail);
            tail.setNext(node);
            tail = node;
        }
        size++;
    }

    /**
     * Insere um elemento na posicao indicada (0 a {@code size}).
     *
     * @param index posicao desejada
     * @param value elemento a inserir
     * @return {@code true} se inserido; {@code false} se o indice for invalido
     */
    public boolean add(int index, T value) {
        if (index < 0 || index > size) {
            return false;
        }
        if (index == 0) {
            addFirst(value);
            return true;
        }
        if (index == size) {
            addLast(value);
            return true;
        }
        DoublyNode<T> succ = nodeAt(index);
        DoublyNode<T> pred = succ.getPrev();
        DoublyNode<T> node = new DoublyNode<>(value);
        node.setPrev(pred);
        node.setNext(succ);
        pred.setNext(node);
        succ.setPrev(node);
        size++;
        return true;
    }

    /**
     * Retorna o elemento na posicao indicada.
     *
     * @param index posicao (0 a {@code size - 1})
     * @return elemento, ou {@code null} se o indice for invalido
     */
    public T get(int index) {
        DoublyNode<T> node = nodeAt(index);
        return node == null ? null : node.getValue();
    }

    /**
     * Remove e retorna o primeiro elemento. Complexidade {@code O(1)}.
     *
     * @return elemento removido, ou {@code null} se a lista estiver vazia
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        DoublyNode<T> removed = head;
        head = head.getNext();
        if (head == null) {
            tail = null;
        } else {
            head.setPrev(null);
        }
        removed.setNext(null);
        size--;
        return removed.getValue();
    }

    /**
     * Remove e retorna o ultimo elemento. Complexidade {@code O(1)} — graca a
     * ligacao {@code prev}.
     *
     * @return elemento removido, ou {@code null} se a lista estiver vazia
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        DoublyNode<T> removed = tail;
        tail = tail.getPrev();
        if (tail == null) {
            head = null;
        } else {
            tail.setNext(null);
        }
        removed.setPrev(null);
        size--;
        return removed.getValue();
    }

    /**
     * Remove e retorna o elemento na posicao indicada.
     *
     * @param index posicao (0 a {@code size - 1})
     * @return elemento removido, ou {@code null} se o indice for invalido
     */
    public T remove(int index) {
        DoublyNode<T> target = nodeAt(index);
        if (target == null) {
            return null;
        }
        if (target == head) {
            return removeFirst();
        }
        if (target == tail) {
            return removeLast();
        }
        DoublyNode<T> pred = target.getPrev();
        DoublyNode<T> succ = target.getNext();
        pred.setNext(succ);
        succ.setPrev(pred);
        target.setPrev(null);
        target.setNext(null);
        size--;
        return target.getValue();
    }

    /**
     * Verifica se um elemento esta presente. Trata {@code null} com seguranca.
     *
     * @param value elemento procurado
     * @return {@code true} se encontrado
     */
    public boolean contains(T value) {
        return indexOf(value) >= 0;
    }

    /**
     * Retorna a posicao da primeira ocorrencia de um elemento.
     *
     * @param value elemento procurado
     * @return indice da ocorrencia, ou {@code -1} se ausente
     */
    public int indexOf(T value) {
        DoublyNode<T> current = head;
        int i = 0;
        while (current != null) {
            T v = current.getValue();
            if (v == null ? value == null : v.equals(value)) {
                return i;
            }
            current = current.getNext();
            i++;
        }
        return -1;
    }

    /** Esvazia a lista, liberando as ligacoes entre os nos. */
    public void clear() {
        DoublyNode<T> current = head;
        while (current != null) {
            DoublyNode<T> next = current.getNext();
            current.setPrev(null);
            current.setNext(null);
            current = next;
        }
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * <strong>Operacao avancada — Insercao ordenada.</strong>
     *
     * <p>Insere mantendo a ordem crescente definida pelo {@link Comparator}.
     * Complexidade {@code O(n)}.</p>
     *
     * @param value      elemento a inserir
     * @param comparator criterio de ordem (se {@code null}, insere no fim)
     */
    public void insertSorted(T value, Comparator<T> comparator) {
        if (comparator == null || isEmpty()) {
            addLast(value);
            return;
        }
        DoublyNode<T> current = head;
        while (current != null && comparator.compare(value, current.getValue()) > 0) {
            current = current.getNext();
        }
        if (current == null) {
            addLast(value);
        } else if (current == head) {
            addFirst(value);
        } else {
            DoublyNode<T> pred = current.getPrev();
            DoublyNode<T> node = new DoublyNode<>(value);
            node.setPrev(pred);
            node.setNext(current);
            pred.setNext(node);
            current.setPrev(node);
            size++;
        }
    }

    /**
     * <strong>Operacao avancada — Inversao.</strong>
     *
     * <p>Inverte a lista <em>in place</em>, trocando as ligacoes {@code prev} e
     * {@code next} de cada no. Complexidade {@code O(n)}, memoria {@code O(1)}.</p>
     */
    public void reverse() {
        DoublyNode<T> current = head;
        DoublyNode<T> temp = null;
        while (current != null) {
            temp = current.getPrev();
            current.setPrev(current.getNext());
            current.setNext(temp);
            current = current.getPrev();
        }
        temp = head;
        head = tail;
        tail = temp;
    }

    /**
     * <strong>Operacao avancada — Concatenacao.</strong>
     *
     * <p>Anexa ao fim desta lista todos os nos de {@code other}, ajustando as
     * ligacoes duplas. Ao final, {@code other} fica vazia. Complexidade
     * {@code O(1)}.</p>
     *
     * @param other lista a incorporar (ignorada se {@code null}, vazia ou a
     *              propria lista)
     */
    public void concat(DoublyLinkedList<T> other) {
        if (other == null || other.isEmpty() || other == this) {
            return;
        }
        if (isEmpty()) {
            head = other.head;
            tail = other.tail;
        } else {
            tail.setNext(other.head);
            other.head.setPrev(tail);
            tail = other.tail;
        }
        size += other.size;
        other.head = null;
        other.tail = null;
        other.size = 0;
    }

    /**
     * Representacao textual no formato {@code [a, b, c]}.
     *
     * @return string com os elementos da cabeca a cauda
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        DoublyNode<T> current = head;
        while (current != null) {
            sb.append(current.getValue());
            if (current.getNext() != null) {
                sb.append(", ");
            }
            current = current.getNext();
        }
        return sb.append("]").toString();
    }

    /**
     * Localiza o no em uma posicao, percorrendo da ponta mais proxima.
     *
     * @param index posicao desejada
     * @return no correspondente, ou {@code null} se o indice for invalido
     */
    private DoublyNode<T> nodeAt(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        DoublyNode<T> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.getPrev();
            }
        }
        return current;
    }
}
