# Revisão ativa — Concorrência e Multithreading em Java

Tente responder sem olhar as notas; depois execute um exemplo e explique o resultado.

1. Qual a diferença entre concorrência e paralelismo?
2. Por que `volatile` não basta para um incremento composto?
3. Quando um pool fixo de threads de plataforma ajuda? Qual risco sua fila padrão traz?
4. Como encerrar um `ExecutorService` sem abandonar tarefas silenciosamente?
5. Quando `ConcurrentHashMap.merge` é melhor que `get` seguido de `put`?
6. Por que `CopyOnWriteArrayList` serve para listeners pouco alterados?
7. Como uma `BlockingQueue` limitada aplica backpressure?
8. Qual sincronizador espera N conclusões apenas uma vez?
9. Qual permite fases com participantes dinâmicos?
10. Por que um semáforo ainda é útil com threads virtuais?
11. Em Fork/Join, por que executar um ramo localmente pode evitar tarefas demais?
12. Qual a diferença entre `thenApply` e `thenCompose`?
13. O que `allOf` realmente devolve?
14. Quando `parallelStream()` pode piorar a vazão?
15. Por que threads virtuais ajudam I/O, mas não criam CPU?
16. Como distinguir uma otimização real de ruído de uma medição isolada?

## Exercício de síntese

Modele um serviço que consulta três APIs independentes, combina o resultado e grava um resumo. Indique: limites de chamadas simultâneas, timeout, cancelamento, executor escolhido, tratamento de falha parcial e teste que comprova o comportamento. Compare uma versão com `CompletableFuture` a uma versão com threads virtuais.
