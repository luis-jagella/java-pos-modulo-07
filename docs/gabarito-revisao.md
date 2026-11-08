# Gabarito comentado — Concorrência e Multithreading em Java

Use este material depois de tentar responder às perguntas de [revisão ativa](revisao.md). As respostas sintetizam os conceitos; detalhes de API podem variar conforme a versão do JDK.

1. **Concorrência e paralelismo:** concorrência é estruturar várias tarefas para avançarem durante períodos sobrepostos; paralelismo é executá-las literalmente ao mesmo tempo, normalmente em núcleos distintos. Uma aplicação concorrente pode alternar tarefas em um único núcleo.

2. **`volatile` e incremento:** `volatile` torna leituras/escritas visíveis entre threads e impõe regras de ordenação, mas `contador++` continua sendo uma sequência de leitura, soma e escrita. Duas threads podem ler o mesmo valor e sobrescrever o incremento uma da outra. Use `AtomicInteger.incrementAndGet()` ou proteção apropriada para a invariante.

3. **Pool fixo:** é útil quando se quer limitar o número de threads de plataforma concorrentes, especialmente para trabalho CPU-bound. A fábrica `newFixedThreadPool` usa uma fila sem limite predefinido; se produtores submeterem tarefas mais rápido do que o pool as processa, a fila pode crescer e consumir memória. Em serviços, defina também admissão, capacidade de fila e política de rejeição.

4. **Encerramento de `ExecutorService`:** pare de aceitar novas tarefas com `shutdown()`, aguarde por um período com `awaitTermination()` e, se o prazo expirar, solicite cancelamento/interrupção com `shutdownNow()`. Trate a interrupção restaurando o estado (`Thread.currentThread().interrupt()`) ou propagando `InterruptedException`; interrupção é um pedido cooperativo, não uma parada forçada garantida.

5. **`ConcurrentHashMap.merge`:** combina a leitura, decisão e atualização do valor para uma chave como uma operação atômica. É preferível a `get` seguido de `put` quando várias threads podem atualizar simultaneamente, pois a sequência separada perde atualizações. A função de remapeamento deve ser curta e não fazer efeitos externos complexos.

6. **`CopyOnWriteArrayList`:** cada escrita cria uma cópia do array interno, então leituras e iterações são simples e estáveis, mas escritas são caras. Serve para coleções pequenas, com muitas leituras e poucas alterações, como listas de listeners; não é uma boa escolha para escrita frequente ou listas grandes.

7. **`BlockingQueue` limitada:** quando a fila atinge sua capacidade, `put` bloqueia o produtor até haver espaço (ou `offer` pode falhar/esperar por um tempo). Isso propaga a pressão para quem produz em vez de acumular trabalho indefinidamente. Também é preciso definir como tratar lentidão, timeout e encerramento dos consumidores.

8. **Esperar N conclusões uma vez:** `CountDownLatch` é adequado quando um conjunto de tarefas decrementa a contagem e uma ou mais threads aguardam que ela chegue a zero. Depois de liberado, não pode ser reiniciado; use timeout e trate interrupção se o fluxo precisar evitar espera indefinida.

9. **Fases com participantes dinâmicos:** `Phaser` permite registrar e remover participantes e coordenar a passagem por fases. `CyclicBarrier` também pode ser reutilizado por grupos fixos, mas não é tão flexível para alterar participantes durante o trabalho.

10. **Semáforo com threads virtuais:** threads virtuais tornam barato representar muitas tarefas bloqueantes, mas não aumentam a capacidade de um banco, API, pool de conexões ou outro recurso limitado. Um `Semaphore` pode limitar acessos simultâneos a esse recurso. O limite deve corresponder à capacidade real, não ao número de threads disponíveis.

11. **Fork/Join e trabalho local:** criar tarefas pequenas demais aumenta custo de agendamento, alocação e junção. Dividir recursivamente até um limiar razoável e executar um dos ramos na própria thread reduz tarefas pendentes e deixa o pool roubar trabalho quando necessário. O limiar ideal depende da carga e deve ser medido.

12. **`thenApply` e `thenCompose`:** `thenApply` transforma um resultado `T` em `U`. `thenCompose` transforma `T` em outro `CompletionStage<U>` e achata o resultado, evitando `CompletionStage<CompletionStage<U>>`; é útil quando a próxima etapa já é assíncrona.

13. **`allOf`:** produz um `CompletableFuture<Void>` que termina quando todos os futuros fornecidos terminam. Não reúne automaticamente seus valores tipados; depois da conclusão, leia os resultados dos futuros originais. Uma falha também pode tornar o composto excepcional.

14. **Quando stream paralelo piora:** para conjuntos pequenos, operações baratas, trabalho bloqueante, contenção em estado compartilhado ou quando a ordem importa, o custo de particionar e combinar pode superar o ganho. Streams paralelos também usam infraestrutura compartilhada; compare com uma versão sequencial sob carga representativa.

15. **Threads virtuais:** são apropriadas para muitas tarefas concorrentes que passam tempo bloqueadas, como I/O, e reduzem o custo de manter uma thread por tarefa. Não criam núcleos nem aceleram, por si só, trabalho CPU-bound; limite recursos externos e considere a memória e a carga total.

16. **Otimização versus ruído:** repita medições com entradas e ambiente controlados, aqueça a JVM quando relevante, compare a mesma saída funcional e acompanhe latência, vazão, CPU e memória. Uma execução isolada, especialmente com tarefas curtas, não é evidência confiável. Para microbenchmarks de JVM, use ferramentas apropriadas como JMH.

## Exemplo de síntese: consultar três APIs

- **Concorrência limitada:** defina limite explícito para chamadas em andamento, por API ou por serviço, em vez de assumir que mais tarefas sempre melhoram a vazão.
- **Executor:** para operações bloqueantes, threads virtuais podem simplificar o modelo; para composição não bloqueante, `CompletableFuture` pode representar dependências. Em ambos os casos, limite conexões e chamadas simultâneas separadamente.
- **Prazo e cancelamento:** aplique timeout por chamada e um prazo total para a operação. Ao cancelar, propague o cancelamento quando possível; confirme que cliente HTTP e servidor remoto respeitam o encerramento.
- **Falha parcial:** decida por requisito se uma API é obrigatória. Se for aceitável, represente explicitamente resultado ausente/erro parcial; não converta falhas em valores aparentemente válidos sem sinalizá-las.
- **Gravação:** grave apenas depois de validar e combinar o que for necessário. Use idempotência/transação conforme a semântica da operação.
- **Teste de comportamento:** use dependências falsas controláveis por `CompletableFuture`/`CountDownLatch` para verificar limite simultâneo, timeout, cancelamento, falha parcial e que a gravação não ocorre quando falta dado obrigatório. Evite `sleep` como mecanismo principal de sincronização.

Os exemplos do repositório são didáticos, não benchmarks nem uma receita universal de produção. Consulte as [ressalvas técnicas](ressalvas.md) antes de generalizar resultados.
