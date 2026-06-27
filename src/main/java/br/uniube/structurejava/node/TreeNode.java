package br.uniube.structurejava.node;

/**
 * No de uma arvore binaria.
 *
 * <p>Guarda um valor e referencias para as subarvores esquerda e direita.
 * Usado por {@code BinarySearchTree}.</p>
 *
 * @param <T> tipo do valor armazenado
 */
public class TreeNode<T> {

    /** Valor (chave) guardado neste no. */
    private T value;

    /** Subarvore esquerda (valores menores). */
    private TreeNode<T> left;

    /** Subarvore direita (valores maiores). */
    private TreeNode<T> right;

    /**
     * Cria um no folha, sem filhos.
     *
     * @param value valor a armazenar
     */
    public TreeNode(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
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
     * Retorna a subarvore esquerda.
     *
     * @return filho esquerdo, ou {@code null} se inexistente
     */
    public TreeNode<T> getLeft() {
        return left;
    }

    /**
     * Define a subarvore esquerda.
     *
     * @param left filho esquerdo
     */
    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    /**
     * Retorna a subarvore direita.
     *
     * @return filho direito, ou {@code null} se inexistente
     */
    public TreeNode<T> getRight() {
        return right;
    }

    /**
     * Define a subarvore direita.
     *
     * @param right filho direito
     */
    public void setRight(TreeNode<T> right) {
        this.right = right;
    }
}
