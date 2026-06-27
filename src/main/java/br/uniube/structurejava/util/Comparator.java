package br.uniube.structurejava.util;

/**
 * Contrato funcional de comparacao entre dois elementos do mesmo tipo.
 *
 * <p>Esta interface e uma reimplementacao propria, criada para que a biblioteca
 * <strong>StructureJava</strong> seja totalmente independente do pacote
 * {@code java.util} (que contem o {@code java.util.Comparator}). Ela permite que
 * estruturas genericas realizem ordenacao sem exigir que o tipo {@code T}
 * implemente {@link java.lang.Comparable}.</p>
 *
 * @param <T> tipo dos elementos comparados
 */
@FunctionalInterface
public interface Comparator<T> {

    /**
     * Compara dois elementos quanto a ordem.
     *
     * @param a primeiro elemento
     * @param b segundo elemento
     * @return valor negativo se {@code a} precede {@code b}; zero se sao
     *         equivalentes; valor positivo se {@code a} sucede {@code b}
     */
    int compare(T a, T b);

    /**
     * Cria um comparador que usa a ordem natural de tipos {@link Comparable}.
     *
     * <p>Util quando o tipo {@code T} ja sabe se comparar (ex.: {@code Integer},
     * {@code String}). Trata {@code null} de forma segura, posicionando valores
     * nulos antes dos demais, evitando {@code NullPointerException}.</p>
     *
     * @param <T> tipo {@link Comparable}
     * @return comparador baseado na ordem natural
     */
    static <T extends Comparable<T>> Comparator<T> natural() {
        return (a, b) -> {
            if (a == b) {
                return 0;
            }
            if (a == null) {
                return -1;
            }
            if (b == null) {
                return 1;
            }
            return a.compareTo(b);
        };
    }

    /**
     * Retorna um comparador com a ordem invertida deste.
     *
     * @return comparador reverso
     */
    default Comparator<T> reversed() {
        return (a, b) -> compare(b, a);
    }
}
