# Aula 06 — CompletableFuture

## Ideia central

`CompletableFuture` representa um resultado futuro e permite compor etapas sem bloquear a thread chamadora a cada passo. É mais expressivo que um `Future` simples para transformação, combinação e recuperação de falhas.

| Método | Efeito |
| --- | --- |
| `runAsync` / `supplyAsync` | Inicia tarefa sem/com resultado; aceite executor explícito quando precisar isolar carga. |
| `thenApply` | Transforma valor `T → U`. |
| `thenCompose` | Encadeia função `T → CompletableFuture<U>` sem futuro aninhado. |
| `thenCombine` | Junta resultados de duas operações independentes. |
| `allOf` / `anyOf` | Espera todos / o primeiro a terminar, sem retornar automaticamente uma lista tipada. |
| `exceptionally` / `handle` | Recupera falha / trata sucesso e falha. |

Variantes `...Async` agendam estágio em executor, usando em geral o pool comum se nenhum for fornecido. Variantes sem `Async` podem executar na thread que concluiu a etapa anterior ou naquela que registra o estágio quando a anterior já está pronta; não assuma uma thread fixa.

`join()` e `get()` **bloqueiam quem chama** até o término. Defina timeout, cancelamento e propagação de erro no limite da aplicação. `handle` não é simplesmente um `finally`: ele recebe valor e exceção e produz um novo resultado, podendo converter falha em sucesso.

## Para fixar

Busque preço e estoque de forma independente, combine os resultados e forneça fallback apenas para a falha esperada. Quando você usaria `thenCompose` em vez de `thenApply`?

Fonte: videoaula 06, material autoral, slides 06 e legenda automática.
