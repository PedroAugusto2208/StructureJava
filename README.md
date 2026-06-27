# StructureJava

Biblioteca Java (JAR) independente com implementacoes proprias das principais
estruturas de dados lineares e de uma arvore binaria de busca, **sem utilizar as
classes prontas do pacote `java.util`** (como `ArrayList` ou `LinkedList`).

> Projeto Final — Estrutura de Dados I — UNIUBE 2026/1 — Prof. Igor Junqueira.

## Estruturas implementadas

| Estrutura | Classe | Politica | Destaques |
|-----------|--------|----------|-----------|
| Lista simplesmente encadeada | `list.SinglyLinkedList<T>` | — | insercao ordenada, inversao, concatenacao |
| Lista duplamente encadeada | `list.DoublyLinkedList<T>` | — | `removeLast` em O(1), insercao ordenada, inversao, concatenacao |
| Pilha | `stack.Stack<T>` | LIFO | alocacao dinamica (sem overflow por capacidade) |
| Fila | `queue.Queue<T>` | FIFO | alocacao dinamica, O(1) nas duas pontas |
| Arvore Binaria de Busca *(extra)* | `tree.BinarySearchTree<T>` | — | insert/contains/remove, in-order, min/max, altura |

Apoio: `node.SinglyNode`, `node.DoublyNode`, `node.TreeNode` e a interface
funcional propria `util.Comparator<T>` (substitui `java.util.Comparator` para
manter a biblioteca 100% livre de `java.util`).

## Requisitos atendidos

- ✅ Listas dinamicas simplesmente e duplamente encadeadas.
- ✅ Estruturas LIFO e FIFO baseadas em alocacao dinamica.
- ✅ Operacoes avancadas: **insercao ordenada**, **inversao** e **concatenacao**.
- ✅ Estrutura extra escolhida pelo grupo: **Arvore Binaria de Busca**.
- ✅ **Imune a `Exception`**: acessos invalidos retornam `null`/`false`, nunca lancam.
- ✅ Gerencia correta dos nos (ligacoes anuladas no `clear`/remocao para o GC).
- ✅ **JavaDoc** em todos os metodos publicos.
- ✅ `Main` demonstrando todas as estruturas + testes JUnit 5.

## Como compilar e executar

Pre-requisitos: **JDK 17+** e **Maven 3.9+**.

```bash
# Compilar, rodar os testes e gerar o JAR em target/
mvn clean package

# Executar a demonstracao (Use Case) de todas as estruturas
java -jar target/StructureJava-1.0.0.jar

# Rodar apenas os testes
mvn test

# Gerar a documentacao JavaDoc em target/site/apidocs/index.html
mvn javadoc:javadoc
```

## Uso como biblioteca

```java
import br.uniube.structurejava.list.SinglyLinkedList;
import br.uniube.structurejava.tree.BinarySearchTree;
import br.uniube.structurejava.util.Comparator;

SinglyLinkedList<Integer> lista = new SinglyLinkedList<>();
lista.insertSorted(3, Comparator.natural());
lista.insertSorted(1, Comparator.natural());
lista.insertSorted(2, Comparator.natural());
lista.reverse();                 // [3, 2, 1]

BinarySearchTree<String> arv = new BinarySearchTree<>();
arv.insert("banana");
arv.insert("abacaxi");
System.out.println(arv.inOrder()); // [abacaxi, banana]
```

## Estrutura do projeto

```
src/main/java/br/uniube/structurejava/
├── node/      SinglyNode, DoublyNode, TreeNode
├── util/      Comparator (interface propria)
├── list/      SinglyLinkedList, DoublyLinkedList
├── stack/     Stack (LIFO)
├── queue/     Queue (FIFO)
├── tree/      BinarySearchTree
└── Main.java  (demonstracao)
src/test/java/br/uniube/structurejava/   (testes JUnit 5)
docs/ARTIGO.md                            (artigo completo)
```

## Analise de complexidade (resumo)

| Operacao | Singly | Doubly | Stack | Queue | BST (medio) |
|----------|:------:|:------:|:-----:|:-----:|:-----------:|
| Inserir na ponta | O(1) | O(1) | O(1) push | O(1) enqueue | — |
| Remover na ponta | O(1)/O(n)¹ | O(1) | O(1) pop | O(1) dequeue | — |
| Buscar / acessar | O(n) | O(n) | O(1) peek | O(1) peek | O(log n) |
| Insercao ordenada | O(n) | O(n) | — | — | O(log n) |
| Inverter | O(n) | O(n) | — | — | — |
| Concatenar | O(1) | O(1) | — | — | — |

¹ `removeLast` da lista simples e O(n) por nao haver ponteiro ao antecessor; na
lista dupla e O(1).

## Autores

- *(preencher: Nome — RA)*
- *(preencher: Nome — RA)*

## Licenca

Uso academico — UNIUBE 2026/1.
