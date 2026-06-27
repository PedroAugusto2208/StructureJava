package br.uniube.structurejava;

import br.uniube.structurejava.list.DoublyLinkedList;
import br.uniube.structurejava.list.SinglyLinkedList;
import br.uniube.structurejava.queue.Queue;
import br.uniube.structurejava.stack.Stack;
import br.uniube.structurejava.tree.BinarySearchTree;
import br.uniube.structurejava.util.Comparator;

/**
 * Classe de demonstracao (Use Case) da biblioteca <strong>StructureJava</strong>.
 *
 * <p>Exercita todas as estruturas implementadas, incluindo as operacoes
 * avancadas (insercao ordenada, inversao e concatenacao) e os casos de borda
 * (estruturas vazias), evidenciando a imunidade a excecoes.</p>
 */
public final class Main {

    private Main() {
    }

    /**
     * Ponto de entrada da demonstracao.
     *
     * @param args nao utilizados
     */
    public static void main(String[] args) {
        titulo("StructureJava - Demonstracao Completa");

        demoSinglyLinkedList();
        demoDoublyLinkedList();
        demoStack();
        demoQueue();
        demoBinarySearchTree();
        demoImunidadeExcecoes();

        System.out.println();
        System.out.println(">> Demonstracao concluida sem nenhuma excecao.");
    }

    private static void demoSinglyLinkedList() {
        titulo("1) Lista Simplesmente Encadeada");
        SinglyLinkedList<Integer> lista = new SinglyLinkedList<>();
        lista.addLast(10);
        lista.addLast(20);
        lista.addFirst(5);
        lista.add(2, 15); // 5, 10, 15, 20
        System.out.println("Lista............: " + lista);
        System.out.println("get(2)...........: " + lista.get(2));
        System.out.println("indexOf(20)......: " + lista.indexOf(20));
        System.out.println("removeFirst......: " + lista.removeFirst());
        System.out.println("removeLast.......: " + lista.removeLast());
        System.out.println("Apos remocoes....: " + lista);

        System.out.println("-- Insercao ordenada --");
        SinglyLinkedList<Integer> ordenada = new SinglyLinkedList<>();
        Comparator<Integer> crescente = Comparator.natural();
        int[] entrada = {7, 3, 9, 1, 5};
        for (int v : entrada) {
            ordenada.insertSorted(v, crescente);
        }
        System.out.println("Ordenada.........: " + ordenada);

        System.out.println("-- Inversao --");
        ordenada.reverse();
        System.out.println("Invertida........: " + ordenada);

        System.out.println("-- Concatenacao --");
        SinglyLinkedList<Integer> a = new SinglyLinkedList<>();
        a.addLast(1);
        a.addLast(2);
        SinglyLinkedList<Integer> b = new SinglyLinkedList<>();
        b.addLast(3);
        b.addLast(4);
        a.concat(b);
        System.out.println("a apos concat....: " + a);
        System.out.println("b (esvaziada)....: " + b + " | size=" + b.size());
    }

    private static void demoDoublyLinkedList() {
        titulo("2) Lista Duplamente Encadeada");
        DoublyLinkedList<String> lista = new DoublyLinkedList<>();
        lista.addLast("B");
        lista.addLast("C");
        lista.addFirst("A");
        System.out.println("Lista............: " + lista);
        System.out.println("removeLast (O(1)): " + lista.removeLast());
        System.out.println("Apos removeLast..: " + lista);

        System.out.println("-- Insercao ordenada (alfabetica) --");
        DoublyLinkedList<String> ord = new DoublyLinkedList<>();
        Comparator<String> alfabetica = Comparator.natural();
        String[] nomes = {"Maria", "Ana", "Pedro", "Bruno"};
        for (String n : nomes) {
            ord.insertSorted(n, alfabetica);
        }
        System.out.println("Ordenada.........: " + ord);

        System.out.println("-- Inversao --");
        ord.reverse();
        System.out.println("Invertida........: " + ord);

        System.out.println("-- Concatenacao --");
        DoublyLinkedList<String> x = new DoublyLinkedList<>();
        x.addLast("um");
        DoublyLinkedList<String> y = new DoublyLinkedList<>();
        y.addLast("dois");
        y.addLast("tres");
        x.concat(y);
        System.out.println("x apos concat....: " + x + " | y.size=" + y.size());
    }

    private static void demoStack() {
        titulo("3) Pilha (LIFO)");
        Stack<String> pilha = new Stack<>();
        pilha.push("Tarefa A");
        pilha.push("Tarefa B");
        pilha.push("Tarefa C");
        System.out.println("Pilha (topo->base): " + pilha);
        System.out.println("peek.............: " + pilha.peek());
        System.out.println("pop..............: " + pilha.pop());
        System.out.println("pop..............: " + pilha.pop());
        System.out.println("Apos 2 pops......: " + pilha + " | size=" + pilha.size());
    }

    private static void demoQueue() {
        titulo("4) Fila (FIFO)");
        Queue<String> fila = new Queue<>();
        fila.enqueue("Cliente 1");
        fila.enqueue("Cliente 2");
        fila.enqueue("Cliente 3");
        System.out.println("Fila (inicio->fim): " + fila);
        System.out.println("peek.............: " + fila.peek());
        System.out.println("dequeue..........: " + fila.dequeue());
        System.out.println("dequeue..........: " + fila.dequeue());
        System.out.println("Apos 2 dequeues..: " + fila + " | size=" + fila.size());
    }

    private static void demoBinarySearchTree() {
        titulo("5) Arvore Binaria de Busca (estrutura extra)");
        BinarySearchTree<Integer> arvore = new BinarySearchTree<>();
        int[] valores = {50, 30, 70, 20, 40, 60, 80, 30};
        for (int v : valores) {
            arvore.insert(v);
        }
        System.out.println("Insercoes........: 50,30,70,20,40,60,80,30(dup)");
        System.out.println("In-order.........: " + arvore.inOrder());
        System.out.println("size.............: " + arvore.size());
        System.out.println("altura...........: " + arvore.height());
        System.out.println("min/max..........: " + arvore.min() + " / " + arvore.max());
        System.out.println("contains(60).....: " + arvore.contains(60));
        System.out.println("contains(99).....: " + arvore.contains(99));
        System.out.println("remove(30).......: " + arvore.remove(30));
        System.out.println("remove(50)(raiz).: " + arvore.remove(50));
        System.out.println("In-order final...: " + arvore.inOrder());
    }

    private static void demoImunidadeExcecoes() {
        titulo("6) Imunidade a Excecoes (operacoes em estruturas vazias)");
        System.out.println("SinglyList.removeFirst(): " + new SinglyLinkedList<Integer>().removeFirst());
        System.out.println("SinglyList.get(99)......: " + new SinglyLinkedList<Integer>().get(99));
        System.out.println("DoublyList.removeLast().: " + new DoublyLinkedList<Integer>().removeLast());
        System.out.println("Stack.pop().............: " + new Stack<Integer>().pop());
        System.out.println("Queue.dequeue().........: " + new Queue<Integer>().dequeue());
        System.out.println("BST.min()...............: " + new BinarySearchTree<Integer>().min());
        System.out.println("BST.remove(1)...........: " + new BinarySearchTree<Integer>().remove(1));
        System.out.println("(Nenhuma excecao lancada.)");
    }

    private static void titulo(String t) {
        System.out.println();
        System.out.println("==================================================");
        System.out.println("  " + t);
        System.out.println("==================================================");
    }
}
