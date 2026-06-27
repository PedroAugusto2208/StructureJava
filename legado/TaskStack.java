public class TaskStack {
    private String[] tarefas;
    private int topo;
    private int capacidade;

    public TaskStack(int tamanho) {
        this.capacidade = tamanho;
        this.tarefas = new String[tamanho];
        this.topo = -1;
    }

    /**
     * Insere uma tarefa no topo da pilha.
     *
     * CORREÇÃO 1 — Gestão de Transbordamento (Stack Overflow):
     *   Antes de incrementar 'topo', verificamos se a pilha já está cheia
     *   (topo == capacidade - 1). Isso evita o ArrayIndexOutOfBoundsException
     *   que travava o app após 10 inserções.
     *
     * Complexidade: O(1) — acesso direto por índice, sem laços ou realocação.
     */
    public void push(String tarefa) {
        // CORREÇÃO: guarda de limite ANTES de incrementar
        if (topo == capacidade - 1) {
            System.out.println("Erro: Pilha cheia. Não é possível adicionar: " + tarefa);
            return;
        }
        topo++;
        tarefas[topo] = tarefa;
        System.out.println("Tarefa adicionada: " + tarefa);
    }

    /**
     * Remove e retorna a tarefa do TOPO da pilha (LIFO).
     *
     * CORREÇÃO 2 — Lógica LIFO:
     *   Substituímos 'tarefas[0]' (comportamento de fila/FIFO) por
     *   'tarefas[topo]', que aponta para o último elemento inserido.
     *   Em seguida, decrementamos 'topo', descartando a posição logicamente
     *   sem precisar reorganizar o array — O(1) garantido.
     *
     * Complexidade: O(1) — remoção direta pelo índice 'topo'.
     */
    public String pop() {
        if (topo == -1) {
            return "Erro: Pilha Vazia";
        }
        // CORREÇÃO: retira do topo, não do início
        String item = tarefas[topo];
        tarefas[topo] = null; // libera a referência para o GC (boa prática)
        topo--;
        return item;
    }

    /**
     * Exibe as tarefas da base ao topo.
     * Complexidade: O(n) — percorre todos os elementos presentes.
     */
    public void imprimir() {
        if (topo == -1) {
            System.out.println("Pilha vazia.");
            return;
        }
        for (int i = 0; i <= topo; i++) {
            System.out.print(tarefas[i] + " | ");
        }
        System.out.println();
    }

    // -------------------------------------------------------------------------
    // ANÁLISE DE EFICIÊNCIA — Justificativa Técnica
    // -------------------------------------------------------------------------
    //
    // 1. COMPLEXIDADE DAS OPERAÇÕES DE INSERÇÃO (push):
    //    O(1) — tempo constante.
    //    A inserção utiliza apenas aritmética de índice (topo++) e uma
    //    atribuição direta no array. Não há laços, buscas ou cópias de dados.
    //    Isso se mantém verdadeiro independentemente do número de elementos
    //    já presentes na pilha.
    //
    // 2. POR QUE A CORREÇÃO GARANTE INTEGRIDADE SOB ALTA CARGA:
    //
    //    a) Guarda de overflow no push():
    //       Antes de qualquer escrita, verificamos (topo == capacidade - 1).
    //       Se verdadeiro, a operação é abortada com mensagem de erro, nunca
    //       acessando um índice fora dos limites do array. Isso elimina o
    //       ArrayIndexOutOfBoundsException que travava o sistema.
    //
    //    b) Remoção correta no pop():
    //       Ao usar tarefas[topo] e decrementar topo em seguida, garantimos
    //       a semântica LIFO: o último item inserido é sempre o primeiro a
    //       sair. O campo 'tarefas[topo] = null' após a remoção libera a
    //       referência do objeto para o Garbage Collector, prevenindo
    //       vazamento de memória (memory leak) em execuções prolongadas.
    //
    //    c) Verificação de underflow no pop():
    //       A checagem (topo == -1) impede leituras em índice negativo quando
    //       a pilha está vazia, retornando uma mensagem de erro controlada.
    //
    // -------------------------------------------------------------------------

    // Método main para demonstração e teste
    public static void main(String[] args) {
        TaskStack pilha = new TaskStack(10);

        pilha.push("Tarefa A");
        pilha.push("Tarefa B");
        pilha.push("Tarefa C");

        System.out.print("Estado atual: ");
        pilha.imprimir(); // Tarefa A | Tarefa B | Tarefa C |

        System.out.println("Desfazer (pop): " + pilha.pop()); // deve ser Tarefa C
        System.out.println("Desfazer (pop): " + pilha.pop()); // deve ser Tarefa B

        System.out.print("Estado após desfazer 2x: ");
        pilha.imprimir(); // Tarefa A |

        // Teste de overflow
        TaskStack pequena = new TaskStack(2);
        pequena.push("X");
        pequena.push("Y");
        pequena.push("Z"); // deve imprimir erro de pilha cheia

        // Teste de underflow
        TaskStack vazia = new TaskStack(3);
        System.out.println(vazia.pop()); // deve imprimir erro de pilha vazia
    }
}