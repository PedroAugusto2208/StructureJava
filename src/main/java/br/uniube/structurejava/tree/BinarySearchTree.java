package br.uniube.structurejava.tree;

import br.uniube.structurejava.list.SinglyLinkedList;
import br.uniube.structurejava.node.TreeNode;

/**
 * Arvore Binaria de Busca (<em>Binary Search Tree</em>) generica.
 *
 * <p>Esta e a <strong>estrutura adicional escolhida pelo grupo</strong>. Para
 * cada no, todos os valores da subarvore esquerda sao menores e todos os da
 * direita sao maiores, mantendo o conjunto sempre ordenado. Isso permite busca,
 * insercao e remocao em tempo medio {@code O(log n)} (e {@code O(n)} no pior
 * caso, quando a arvore degenera em lista).</p>
 *
 * <p>O tipo {@code T} deve implementar {@link Comparable} para definir a ordem.
 * Valores duplicados sao ignorados. Valores {@code null} sao rejeitados com
 * seguranca (sem lancar excecao), preservando a imunidade a excecoes.</p>
 *
 * @param <T> tipo dos elementos, que deve ser comparavel
 */
public class BinarySearchTree<T extends Comparable<T>> {

    /** Raiz da arvore; {@code null} quando vazia. */
    private TreeNode<T> root;

    /** Quantidade de elementos. */
    private int size;

    /** Cria uma arvore vazia. */
    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Insere um valor mantendo a propriedade de ordenacao. Duplicatas e
     * {@code null} sao ignorados. Complexidade media {@code O(log n)}.
     *
     * @param value valor a inserir
     * @return {@code true} se inserido; {@code false} se duplicado ou {@code null}
     */
    public boolean insert(T value) {
        if (value == null) {
            return false;
        }
        int before = size;
        root = insertRec(root, value);
        return size > before;
    }

    /**
     * Insercao recursiva auxiliar.
     *
     * @param node  raiz da subarvore corrente
     * @param value valor a inserir
     * @return raiz (possivelmente nova) da subarvore
     */
    private TreeNode<T> insertRec(TreeNode<T> node, T value) {
        if (node == null) {
            size++;
            return new TreeNode<>(value);
        }
        int cmp = value.compareTo(node.getValue());
        if (cmp < 0) {
            node.setLeft(insertRec(node.getLeft(), value));
        } else if (cmp > 0) {
            node.setRight(insertRec(node.getRight(), value));
        }
        // cmp == 0: duplicata ignorada.
        return node;
    }

    /**
     * Verifica se um valor pertence a arvore. Complexidade media {@code O(log n)}.
     *
     * @param value valor procurado
     * @return {@code true} se presente
     */
    public boolean contains(T value) {
        if (value == null) {
            return false;
        }
        TreeNode<T> current = root;
        while (current != null) {
            int cmp = value.compareTo(current.getValue());
            if (cmp == 0) {
                return true;
            }
            current = cmp < 0 ? current.getLeft() : current.getRight();
        }
        return false;
    }

    /**
     * Remove um valor, tratando os tres casos classicos (no folha, no com um
     * filho e no com dois filhos — substituido pelo sucessor in-order).
     * Complexidade media {@code O(log n)}.
     *
     * @param value valor a remover
     * @return {@code true} se removido; {@code false} se ausente ou {@code null}
     */
    public boolean remove(T value) {
        if (value == null) {
            return false;
        }
        int before = size;
        root = removeRec(root, value);
        return size < before;
    }

    /**
     * Remocao recursiva auxiliar.
     *
     * @param node  raiz da subarvore corrente
     * @param value valor a remover
     * @return raiz (possivelmente nova) da subarvore
     */
    private TreeNode<T> removeRec(TreeNode<T> node, T value) {
        if (node == null) {
            return null;
        }
        int cmp = value.compareTo(node.getValue());
        if (cmp < 0) {
            node.setLeft(removeRec(node.getLeft(), value));
        } else if (cmp > 0) {
            node.setRight(removeRec(node.getRight(), value));
        } else {
            // No encontrado.
            if (node.getLeft() == null) {
                size--;
                return node.getRight();
            }
            if (node.getRight() == null) {
                size--;
                return node.getLeft();
            }
            // Dois filhos: copia o menor valor da subarvore direita (sucessor).
            TreeNode<T> successor = min(node.getRight());
            node.setValue(successor.getValue());
            node.setRight(removeRec(node.getRight(), successor.getValue()));
        }
        return node;
    }

    /**
     * Retorna o menor valor da arvore.
     *
     * @return menor valor, ou {@code null} se a arvore estiver vazia
     */
    public T min() {
        return isEmpty() ? null : min(root).getValue();
    }

    /**
     * Retorna o maior valor da arvore.
     *
     * @return maior valor, ou {@code null} se a arvore estiver vazia
     */
    public T max() {
        if (isEmpty()) {
            return null;
        }
        TreeNode<T> current = root;
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return current.getValue();
    }

    /**
     * Localiza o no de menor valor de uma subarvore.
     *
     * @param node raiz da subarvore
     * @return no mais a esquerda
     */
    private TreeNode<T> min(TreeNode<T> node) {
        TreeNode<T> current = node;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    /**
     * Retorna a altura da arvore (numero de arestas do caminho mais longo da
     * raiz a uma folha). Arvore vazia tem altura {@code -1}.
     *
     * @return altura da arvore
     */
    public int height() {
        return heightRec(root);
    }

    private int heightRec(TreeNode<T> node) {
        if (node == null) {
            return -1;
        }
        int left = heightRec(node.getLeft());
        int right = heightRec(node.getRight());
        return 1 + (left > right ? left : right);
    }

    /**
     * Percorre a arvore <em>in-order</em> (esquerda, raiz, direita), produzindo
     * os elementos em ordem crescente.
     *
     * @return lista encadeada com os valores em ordem crescente
     */
    public SinglyLinkedList<T> inOrder() {
        SinglyLinkedList<T> result = new SinglyLinkedList<>();
        inOrderRec(root, result);
        return result;
    }

    private void inOrderRec(TreeNode<T> node, SinglyLinkedList<T> out) {
        if (node == null) {
            return;
        }
        inOrderRec(node.getLeft(), out);
        out.addLast(node.getValue());
        inOrderRec(node.getRight(), out);
    }

    /**
     * Informa se a arvore nao possui elementos.
     *
     * @return {@code true} se vazia
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Retorna a quantidade de elementos.
     *
     * @return tamanho da arvore
     */
    public int size() {
        return size;
    }

    /** Esvazia a arvore (a raiz e descartada e coletada pelo GC). */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Representacao textual dos elementos em ordem crescente, formato
     * {@code [a, b, c]}.
     *
     * @return string com os elementos in-order
     */
    @Override
    public String toString() {
        return inOrder().toString();
    }
}
