# StructureJava: implementação de uma biblioteca de estruturas de dados lineares e de árvore binária de busca em Java sem dependência do pacote `java.util`

**Autores:** Pedro Augusto Rodrigues Martins — RA: 5164033; Tiago Ribeiro Prissinoti Simari — RA: 5168616

**Instituição:** Universidade de Uberaba (UNIUBE) — Estrutura de Dados I — 2026/1

**Orientador:** Prof. Igor Junqueira

---

## Resumo

Este artigo descreve o projeto e a implementação da **StructureJava**, uma biblioteca Java empacotada como JAR independente que reúne implementações próprias das principais estruturas de dados lineares — listas simplesmente e duplamente encadeadas, pilha (LIFO) e fila (FIFO) — além de uma estrutura adicional, a Árvore Binária de Busca (BST). O objetivo foi produzir código eficiente, reutilizável e documentado, sem empregar as classes prontas do pacote `java.util`. Adotou-se programação genérica (`<T>`), alocação dinâmica baseada em nós encadeados e uma política de *imunidade a exceções*, na qual operações inválidas retornam valores sentinela (`null`/`false`) em vez de lançar exceções. As decisões de engenharia foram justificadas por análise de complexidade assintótica, e a corretude foi verificada por uma suíte de testes automatizados (JUnit 5) e por uma classe de demonstração (`Main`). Os resultados confirmam o comportamento esperado das operações — inserção e remoção nas pontas em tempo constante para as estruturas adequadas e travessia ordenada em tempo linear na BST —, evidenciando o ganho de desempenho da lista duplamente encadeada na remoção no fim (O(1)) frente à lista simples (O(n)).

**Palavras-chave:** Estruturas de dados. Listas encadeadas. Pilha e fila. Árvore binária de busca. Java.

## Abstract

This paper presents the design and implementation of **StructureJava**, a standalone Java library (JAR) that provides custom implementations of the main linear data structures — singly and doubly linked lists, stack (LIFO) and queue (FIFO) — plus an additional structure, the Binary Search Tree (BST). The goal was to deliver efficient, reusable and documented code without relying on the ready-made classes of the `java.util` package. We applied generic programming (`<T>`), dynamic allocation based on linked nodes and an *exception-immune* policy, in which invalid operations return sentinel values (`null`/`false`) instead of throwing exceptions. Engineering decisions were justified through asymptotic complexity analysis, and correctness was verified with an automated test suite (JUnit 5) and a demonstration class (`Main`). Results confirm the expected behavior of the operations and highlight the performance advantage of the doubly linked list for tail removal (O(1)) over the singly linked list (O(n)).

**Keywords:** Data structures. Linked lists. Stack and queue. Binary search tree. Java.

---

## 1 Introdução

Estruturas de dados são a base sobre a qual algoritmos eficientes são construídos. A escolha adequada de uma estrutura determina o custo computacional das operações fundamentais de inserção, remoção e busca, impactando diretamente o desempenho de qualquer sistema (CORMEN et al., 2012). Embora a plataforma Java ofereça, no pacote `java.util`, implementações maduras como `ArrayList`, `LinkedList`, `Stack` e `TreeMap`, o domínio profundo dessas estruturas exige reimplementá-las, compreendendo o gerenciamento explícito de nós e ponteiros.

Este trabalho apresenta a **StructureJava**, biblioteca desenvolvida como projeto final da disciplina Estrutura de Dados I. O requisito central foi construir, do zero, uma biblioteca reutilizável e documentada contendo as estruturas lineares clássicas e uma estrutura adicional escolhida pelo grupo — a Árvore Binária de Busca —, **sem utilizar as coleções prontas de `java.util`**.

O objetivo geral é demonstrar o domínio prático da alocação dinâmica encadeada. Como objetivos específicos, destacam-se: (i) implementar listas simples e duplamente encadeadas, pilha e fila com tipos genéricos; (ii) prover as operações avançadas de inserção ordenada, inversão e concatenação; (iii) garantir robustez por meio de uma política de imunidade a exceções; e (iv) justificar as decisões de projeto com análise de complexidade e verificá-las com testes automatizados.

Este artigo está organizado da seguinte forma: a Seção 2 apresenta a fundamentação teórica; a Seção 3 descreve a metodologia, o mapeamento de classes e os contratos dos Tipos Abstratos de Dados (TADs); a Seção 4 discute os resultados, a análise de complexidade e o desempenho observado; a Seção 5 relata os desafios de implementação; e a Seção 6 conclui o trabalho.

## 2 Fundamentação teórica

### 2.1 Tipos Abstratos de Dados (TAD)

Um Tipo Abstrato de Dados é uma especificação que define um conjunto de valores e as operações aplicáveis a eles, independentemente da implementação concreta (GOODRICH; TAMASSIA; GOLDWASSER, 2014). A separação entre contrato (o *que* a estrutura faz) e implementação (o *como*) é o que permite reutilização e substituição transparente. Na StructureJava, cada estrutura expõe um TAD por meio de seus métodos públicos documentados em JavaDoc.

### 2.2 Listas encadeadas

Uma lista encadeada armazena cada elemento em um **nó** que contém o dado e ao menos uma referência (ponteiro) a outro nó. Na variante **simplesmente encadeada**, cada nó referencia apenas o sucessor; na **duplamente encadeada**, referencia também o antecessor, permitindo percurso bidirecional e remoção no fim em tempo constante (ZIVIANI, 2011). Diferentemente de vetores, listas encadeadas usam alocação dinâmica e não exigem deslocamento de elementos na inserção/remoção interna, embora não ofereçam acesso aleatório em O(1).

### 2.3 Pilhas e filas

A **pilha** é uma estrutura LIFO (*Last In, First Out*): o último elemento inserido é o primeiro removido. A **fila** é FIFO (*First In, First Out*): o primeiro a entrar é o primeiro a sair. Ambas são restrições de acesso sobre uma coleção linear e, quando implementadas sobre nós encadeados, oferecem inserção e remoção em O(1) sem limite fixo de capacidade (SEDGEWICK; WAYNE, 2011).

### 2.4 Árvore binária de busca

A Árvore Binária de Busca (BST) é uma estrutura hierárquica na qual, para todo nó, os valores da subárvore esquerda são menores e os da direita são maiores que o valor do nó. Essa invariante permite busca, inserção e remoção em tempo médio O(log n), degradando para O(n) quando a árvore se torna desbalanceada (CORMEN et al., 2012). A travessia *in-order* produz os elementos em ordem crescente, propriedade explorada neste trabalho.

## 3 Metodologia

### 3.1 Abordagem e ferramentas

O desenvolvimento seguiu uma abordagem incremental: para cada estrutura, definiu-se o TAD, implementou-se a classe, escreveram-se testes e validou-se a complexidade. Utilizou-se a linguagem **Java (JDK 17+)**, o gerenciador de dependências e build **Apache Maven**, o framework **JUnit 5** para testes automatizados e o **Git/GitHub** para versionamento. A documentação técnica foi produzida com **JavaDoc** em todos os métodos públicos.

### 3.2 Decisões de engenharia

**(a) Programação genérica (`<T>`).** Todas as estruturas são parametrizadas por tipo, garantindo reutilização e segurança de tipos em tempo de compilação, eliminando *casts* manuais. A BST exige `T extends Comparable<T>` para dispor de uma relação de ordem.

**(b) Independência de `java.util`.** Como o pacote `java.util.Comparator` não pôde ser utilizado, criou-se a interface funcional própria `util.Comparator<T>`, com o método `compare(a, b)` e fábricas auxiliares (`natural()`, `reversed()`). Assim, a inserção ordenada funciona para qualquer tipo, com ou sem `Comparable`, mantendo a biblioteca 100% livre de coleções prontas.

**(c) Imunidade a exceções.** Em vez de lançar `NullPointerException` ou `IndexOutOfBoundsException`, toda operação inválida (acesso a estrutura vazia, índice fora dos limites, valor `null`) é tratada e retorna um valor sentinela documentado (`null` para consultas, `false` para operações booleanas). Essa decisão prioriza a previsibilidade e atende ao requisito de *rigor técnico* do projeto.

**(d) Gerenciamento de memória.** Nas operações de remoção e em `clear()`, as referências `next`/`prev` dos nós descartados são explicitamente anuladas. Isso evita o problema da *lapsed listener* / retenção acidental, permitindo que o coletor de lixo (Garbage Collector) recupere a memória prontamente.

**(e) Reúso de componentes.** Pilha e fila reutilizam o `SinglyNode`; a travessia *in-order* da BST devolve uma `SinglyLinkedList`, demonstrando a composição entre as estruturas da própria biblioteca.

### 3.3 Mapeamento de classes (diagrama)

A organização em pacotes reflete a responsabilidade de cada componente:

```
br.uniube.structurejava
├── node
│   ├── SinglyNode<T>        (value, next)
│   ├── DoublyNode<T>        (value, prev, next)
│   └── TreeNode<T>          (value, left, right)
├── util
│   └── Comparator<T>        «interface» compare(a,b)
├── list
│   ├── SinglyLinkedList<T>  usa SinglyNode
│   └── DoublyLinkedList<T>  usa DoublyNode
├── stack
│   └── Stack<T>             usa SinglyNode      (LIFO)
├── queue
│   └── Queue<T>             usa SinglyNode      (FIFO)
├── tree
│   └── BinarySearchTree<T>  usa TreeNode, retorna SinglyLinkedList
└── Main                     demonstração (Use Case)
```

Relações principais: as listas, a pilha e a fila **compõem** nós (relação *tem-um*); `SinglyLinkedList` e `DoublyLinkedList` recebem um `Comparator<T>` na inserção ordenada (dependência); `BinarySearchTree` depende de `SinglyLinkedList` para expor a travessia.

### 3.4 Descrição dos TADs (contratos)

**TAD Lista (Singly/Doubly)** — principais operações:

| Método | Contrato (entrada → saída) | Pós-condição |
|--------|----------------------------|--------------|
| `addFirst(v)` / `addLast(v)` | insere `v` na ponta | tamanho +1 |
| `add(i, v)` | insere em `i` válido → `boolean` | `false` se `i` inválido |
| `get(i)` | posição → elemento ou `null` | estrutura inalterada |
| `removeFirst()` / `removeLast()` | → elemento ou `null` | tamanho −1 se não vazia |
| `remove(i)` | índice → elemento ou `null` | religa ponteiros |
| `insertSorted(v, cmp)` | mantém ordem crescente | invariante de ordenação |
| `reverse()` | inverte *in place* | ordem invertida, `head`/`tail` trocados |
| `concat(outra)` | anexa nós de `outra` | `outra` esvaziada |

**TAD Pilha (LIFO):** `push(v)`, `pop()→v|null`, `peek()→v|null`, `isEmpty()`, `size()`, `clear()`.

**TAD Fila (FIFO):** `enqueue(v)`, `dequeue()→v|null`, `peek()→v|null`, `isEmpty()`, `size()`, `clear()`.

**TAD Árvore Binária de Busca:** `insert(v)→boolean` (ignora duplicata/`null`), `contains(v)→boolean`, `remove(v)→boolean` (trata os três casos de remoção), `min()`/`max()→v|null`, `height()→int`, `inOrder()→SinglyLinkedList<T>`.

### 3.5 Plano de testes

A verificação combinou duas frentes. A primeira, **testes unitários automatizados com JUnit 5**, cobre: inserção/remoção nas pontas e no meio; consistência de ponteiros após inversão e concatenação; preservação da invariante de ordenação; os três casos de remoção na BST (folha, um filho, dois filhos com substituição pelo sucessor *in-order*); e o comportamento de imunidade a exceções em estruturas vazias. A segunda, a classe **`Main`**, demonstra o uso integrado de todas as estruturas (Use Case) e serve de verificação visual. O critério de aceitação é a execução completa da suíte e da demonstração **sem qualquer exceção em tempo de execução**.

## 4 Resultados e discussão

### 4.1 Análise de complexidade

A Tabela 1 sintetiza a complexidade de tempo das operações implementadas.

**Tabela 1 — Complexidade assintótica (pior caso, salvo indicação).**

| Operação | SinglyLinkedList | DoublyLinkedList | Stack | Queue | BST (médio / pior) |
|----------|:---------------:|:----------------:|:-----:|:-----:|:------------------:|
| Inserir na ponta inicial | O(1) | O(1) | O(1) `push` | — | — |
| Inserir na ponta final | O(1) | O(1) | — | O(1) `enqueue` | — |
| Remover na ponta inicial | O(1) | O(1) | O(1) `pop` | O(1) `dequeue` | — |
| Remover na ponta final | **O(n)** | **O(1)** | — | — | — |
| Acesso por índice | O(n) | O(n)* | — | — | — |
| Busca por valor | O(n) | O(n) | O(1) `peek` | O(1) `peek` | O(log n) / O(n) |
| Inserção ordenada | O(n) | O(n) | — | — | O(log n) / O(n) |
| Inversão | O(n) | O(n) | — | — | — |
| Concatenação | O(1) | O(1) | — | — | — |
| Travessia *in-order* | — | — | — | — | O(n) |

\* Na lista dupla, `nodeAt` percorre a partir da ponta mais próxima do índice, reduzindo o percurso pela metade no caso médio (ainda O(n) assintótico).

Fonte: os autores.

O contraste mais relevante está na **remoção no fim**: O(n) na lista simples (é preciso percorrer até o penúltimo nó por falta de ponteiro ao antecessor) versus **O(1)** na lista dupla (acesso direto via `prev`). Esse foi o principal critério para incluir as duas variantes — cada uma é preferível conforme o padrão de acesso da aplicação. A concatenação em O(1) decorre da estratégia de *transferência de nós* (religam-se `tail.next` e `head.prev`), em vez de copiar elementos, ao custo de esvaziar a lista de origem.

### 4.2 Desempenho observado

A execução da classe `Main` confirmou empiricamente os contratos: a inserção ordenada produziu sequências crescentes (`[1, 3, 5, 7, 9]`); a inversão reverteu corretamente listas e atualizou `head`/`tail`; a concatenação anexou os elementos e zerou a lista de origem (`size = 0`); a pilha respeitou LIFO e a fila respeitou FIFO; e a BST devolveu a travessia *in-order* sempre ordenada (`[20, 30, 40, 50, 60, 70, 80]`), inclusive após remoções da raiz. Todos os cenários de borda — `pop`/`dequeue`/`removeFirst`/`min` sobre estruturas vazias — retornaram `null` sem lançar exceções, validando a política de imunidade. A suíte JUnit foi executada integralmente sem falhas durante o build (`mvn package`).

Quanto ao consumo de memória, a alocação é proporcional ao número de elementos, com sobrecarga constante por nó (uma referência na lista simples; duas na dupla e na árvore). A anulação explícita de ponteiros nas remoções evita retenção indevida de objetos.

## 5 Desafios de implementação

Os principais desafios enfrentados foram:

a) **Consistência de ponteiros na lista dupla.** Inserir e remover nós exige atualizar até quatro referências (`prev` e `next` de dois nós). Erros sutis nesse religamento causariam travessias infinitas ou perda de elementos; foram mitigados por testes específicos de remoção no meio e de inversão.

b) **Atualização de `tail` em operações estruturais.** Na inversão e na concatenação da lista simples, esquecer de reposicionar `tail` corromperia inserções subsequentes. Adicionou-se um teste que insere um elemento *após* a inversão para detectar essa falha.

c) **Remoção na BST com dois filhos.** O caso exige localizar o sucessor *in-order* (menor valor da subárvore direita), copiá-lo para o nó removido e então removê-lo recursivamente, preservando a invariante da árvore.

d) **Independência de `java.util`.** A proibição de usar `java.util.Comparator` motivou a criação de uma interface funcional própria, decisão que acabou tornando a biblioteca mais coesa e autossuficiente.

e) **Imunidade a exceções vs. semântica.** Definir valores sentinela claros (`null`/`false`) e documentá-los foi necessário para que a robustez não escondesse erros de uso do cliente.

## 6 Conclusão

A StructureJava cumpriu os objetivos propostos: entregou uma biblioteca JAR independente, genérica e documentada com listas simples e duplamente encadeadas, pilha, fila e árvore binária de busca, todas implementadas sem as coleções de `java.util`. As operações avançadas — inserção ordenada, inversão e concatenação — foram implementadas e verificadas, e a política de imunidade a exceções, somada ao gerenciamento explícito de nós, conferiu robustez ao código. A análise de complexidade fundamentou as decisões de projeto, com destaque para o ganho de O(n) para O(1) na remoção no fim ao se adotar o encadeamento duplo.

Como trabalhos futuros, sugere-se: a implementação de iteradores próprios para percurso desacoplado; o autobalanceamento da árvore (AVL ou rubro-negra) para garantir O(log n) no pior caso; e a inclusão de medições de tempo (*benchmarks*) com grandes volumes de dados para comparar empiricamente as variantes.

## Referências

CORMEN, T. H.; LEISERSON, C. E.; RIVEST, R. L.; STEIN, C. **Algoritmos: teoria e prática**. 3. ed. Rio de Janeiro: Elsevier, 2012.

GOODRICH, M. T.; TAMASSIA, R.; GOLDWASSER, M. H. **Data structures and algorithms in Java**. 6. ed. Hoboken: Wiley, 2014.

SEDGEWICK, R.; WAYNE, K. **Algorithms**. 4. ed. Boston: Addison-Wesley, 2011.

ZIVIANI, N. **Projeto de algoritmos: com implementações em Pascal e C**. 3. ed. São Paulo: Cengage Learning, 2011.

ORACLE. **Java Platform, Standard Edition & Java Development Kit: API Specification**. Disponível em: https://docs.oracle.com/en/java/javase/. Acesso em: 26 jun. 2026.
