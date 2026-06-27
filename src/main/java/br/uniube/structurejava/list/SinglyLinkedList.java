package br.uniube.structurejava.list;

import br.uniube.structurejava.node.SinglyNode;
import br.uniube.structurejava.util.Comparator;

/**
 * Lista linear <strong>simplesmente encadeada</strong> e generica.
 *
 * <p>Cada elemento e armazenado em um {@link SinglyNode} alocado dinamicamente.
 * A lista mantem referencias para o primeiro no ({@code head}) e para o ultimo
 * no ({@code tail}), alem do tamanho corrente, garantindo insercao nas pontas
 * em tempo constante.</p>
 *
 * <p><strong>Imunidade a excecoes:</strong> nenhum metodo publico lanca
 * excecao. Acessos invalidos (lista vazia ou indice fora dos limites) retornam
 * {@code null} ou {@code false}, conforme documentado.</p>
 *
 * @param <T> tipo dos elementos
 */
public class SinglyLinkedList<T> {

    /** Primeiro no da lista; {@code null} quando vazia. */
    private SinglyNode<T> head;

    /** Ultimo no da lista; {@code null} quando vazia. */
    private SinglyNode<T> tail;

    /** Quantidade de elementos. */
    private int size;

    /** Cria uma lista vazia. */
    public SinglyLinkedList() {
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
     * Insere um elemento no inicio da lista. Complexidade {@code O(1)}.
     *
     * @param value elemento a inserir
     */
    public void addFirst(T value) {
        SinglyNode<T> node = new SinglyNode<>(value);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            node.setNext(head);
            head = node;
        }
        size++;
    }

    /**
     * Insere um elemento no fim da lista. Complexidade {@code O(1)}.
     *
     * @param value elemento a inserir
     */
    public void addLast(T value) {
        SinglyNode<T> node = new SinglyNode<>(value);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
        size++;
    }

    /**
     * Insere um elemento na posicao indicada (0 a {@code size}).
     *
     * <p>Indices fora do intervalo valido sao ignorados, e o metodo retorna
     * {@code false} sem alterar a lista (imunidade a excecoes).</p>
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
        SinglyNode<T> prev = nodeAt(index - 1);
        SinglyNode<T> node = new SinglyNode<>(value);
        node.setNext(prev.getNext());
        prev.setNext(node);
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
        SinglyNode<T> node = nodeAt(index);
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
        SinglyNode<T> removed = head;
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        removed.setNext(null);
        size--;
        return removed.getValue();
    }

    /**
     * Remove e retorna o ultimo elemento. Complexidade {@code O(n)} (lista
     * simplesmente encadeada nao possui ponteiro para o antecessor).
     *
     * @return elemento removido, ou {@code null} se a lista estiver vazia
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (size == 1) {
            return removeFirst();
        }
        SinglyNode<T> prev = nodeAt(size - 2);
        SinglyNode<T> removed = prev.getNext();
        prev.setNext(null);
        tail = prev;
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
        if (index < 0 || index >= size) {
            return null;
        }
        if (index == 0) {
            return removeFirst();
        }
        SinglyNode<T> prev = nodeAt(index - 1);
        SinglyNode<T> removed = prev.getNext();
        prev.setNext(removed.getNext());
        if (removed == tail) {
            tail = prev;
        }
        removed.setNext(null);
        size--;
        return removed.getValue();
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
        SinglyNode<T> current = head;
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

    /**
     * Esvazia a lista, liberando as ligacoes entre os nos para o coletor de
     * lixo (evita vazamento de memoria).
     */
    public void clear() {
        SinglyNode<T> current = head;
        while (current != null) {
            SinglyNode<T> next = current.getNext();
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
     * <p>Insere {@code value} mantendo a lista em ordem crescente segundo o
     * {@link Comparator} informado. Pressupoe que a lista ja esteja ordenada;
     * percorre ate achar a posicao correta. Complexidade {@code O(n)}.</p>
     *
     * @param value      elemento a inserir
     * @param comparator criterio de ordem (se {@code null}, insere no fim)
     */
    public void insertSorted(T value, Comparator<T> comparator) {
        if (comparator == null) {
            addLast(value);
            return;
        }
        if (isEmpty() || comparator.compare(value, head.getValue()) <= 0) {
            addFirst(value);
            return;
        }
        SinglyNode<T> current = head;
        while (current.getNext() != null
                && comparator.compare(value, current.getNext().getValue()) > 0) {
            current = current.getNext();
        }
        SinglyNode<T> node = new SinglyNode<>(value);
        node.setNext(current.getNext());
        current.setNext(node);
        if (node.getNext() == null) {
            tail = node;
        }
        size++;
    }

    /**
     * <strong>Operacao avancada — Inversao.</strong>
     *
     * <p>Inverte a ordem dos elementos <em>in place</em>, reapontando as
     * ligacoes {@code next} de cada no. Nao aloca novos nos. Complexidade
     * {@code O(n)}, memoria {@code O(1)}.</p>
     */
    public void reverse() {
        SinglyNode<T> prev = null;
        SinglyNode<T> current = head;
        tail = head;
        while (current != null) {
            SinglyNode<T> next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        head = prev;
    }

    /**
     * <strong>Operacao avancada — Concatenacao.</strong>
     *
     * <p>Anexa ao fim desta lista todos os nos de {@code other}, transferindo a
     * posse dos nos (sem copiar valores). Ao final, {@code other} fica vazia.
     * Complexidade {@code O(1)}.</p>
     *
     * @param other lista cujos nos serao incorporados (ignorada se {@code null}
     *              ou vazia)
     */
    public void concat(SinglyLinkedList<T> other) {
        if (other == null || other.isEmpty() || other == this) {
            return;
        }
        if (isEmpty()) {
            head = other.head;
            tail = other.tail;
        } else {
            tail.setNext(other.head);
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
        SinglyNode<T> current = head;
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
     * Localiza o no em uma posicao, com checagem de limites.
     *
     * @param index posicao desejada
     * @return no correspondente, ou {@code null} se o indice for invalido
     */
    private SinglyNode<T> nodeAt(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        SinglyNode<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }
}
