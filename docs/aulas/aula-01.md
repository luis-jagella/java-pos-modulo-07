# Aula 01 — Overview

## Ideia central

Concorrência é lidar com várias tarefas em progresso no mesmo intervalo; paralelismo é executar trabalho simultaneamente em núcleos distintos. Uma aplicação pode ser concorrente em um único núcleo por alternância de execução. O ganho depende do tipo de trabalho, dos recursos disponíveis e do custo de coordenação.

## Conceitos

- **Processo:** execução com espaço de endereçamento e recursos próprios. **Thread:** fluxo de execução dentro do processo, compartilhando heap e outros recursos.
- Compartilhar memória facilita a comunicação, mas introduz condições de corrida, problemas de visibilidade e possíveis deadlocks.
- **Thread safety** significa preservar as invariantes mesmo com acesso simultâneo. `synchronized` e locks combinam exclusão mútua com regras de visibilidade; classes atômicas oferecem operações indivisíveis específicas; `volatile` dá visibilidade/ordenação, mas não torna `contador++` atômico.
- A evolução da plataforma passa de `Thread`/monitores para `java.util.concurrent`, Fork/Join, `CompletableFuture`, streams paralelos e threads virtuais.

## Decisão prática

Antes de criar threads, identifique se o gargalo é **CPU**, **espera de I/O** ou **coordenação de estado**. CPU intensa pode aproveitar núcleos adicionais; I/O concorrente tende a se beneficiar de mais tarefas em espera; estado compartilhado pede redução/encapsulamento e sincronização correta.

## Para fixar

Por que `volatile int contador` ainda perde incrementos quando várias threads executam `contador++`? Pense nas três etapas de leitura, soma e escrita. Experimente `AtomicInteger.incrementAndGet()` e compare.

Fonte: videoaula 01, material autoral da disciplina, slides 01 e legenda automática. A legenda é apoio de estudo, não transcrição revisada.
