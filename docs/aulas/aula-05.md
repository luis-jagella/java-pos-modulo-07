# Aula 05 — Fork/Join Framework

## Ideia central

Fork/Join divide um trabalho CPU-bound em subtarefas, executa partes em paralelo e combina os resultados. O `ForkJoinPool` usa **work stealing** para manter trabalhadores ocupados: cada um dispõe de tarefas e pode roubar trabalho de outro.

- `RecursiveTask<V>` devolve valor; `RecursiveAction` não devolve.
- `compute()` precisa de um **caso base** e de um limiar que evite criar tarefas minúsculas.
- Padrão útil: `esquerda.fork()`, `direita.compute()`, `esquerda.join()`, então combine. Assim a thread atual também trabalha.
- `join()` espera o resultado da subtarefa; excesso de bloqueio e I/O no pool de CPU podem prejudicar vazão.
- O pool comum também pode ser usado por streams paralelos e `CompletableFuture` sem executor explícito. Trabalho pesado em um deles afeta os demais.

A escolha do limiar é empírica. Um número muito alto deixa núcleos ociosos; muito baixo faz o custo de agendar e combinar dominar. Compare com uma implementação sequencial antes de alegar ganho.

## Para fixar

Implemente soma de um vetor com `RecursiveTask<Long>`. Teste vetores vazios, pequenos e grandes; altere o limiar e confirme sempre o mesmo resultado antes de medir tempo.

Fonte: videoaula 05, material autoral, slides 05 e legenda automática.
