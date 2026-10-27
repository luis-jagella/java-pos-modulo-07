# Aula 02 — Executors e Thread Pools

## Ideia central

`Executor` separa a submissão da tarefa de como ela é executada. `ExecutorService` acrescenta resultados (`Future`) e ciclo de vida. A fábrica `Executors` fornece implementações usuais, mas a escolha do pool precisa refletir a carga e os limites do sistema.

## Ferramentas

| Ferramenta | Uso típico | Atenção |
| --- | --- | --- |
| `newFixedThreadPool(n)` | Limitar concorrência de threads de plataforma | A fila padrão pode crescer sem limite; aplique controle de admissão quando necessário. |
| `newCachedThreadPool()` | Tarefas curtas e variáveis | Pode criar muitas threads de plataforma sob pico; não é um limite de capacidade. |
| `newSingleThreadExecutor()` | Serializar tarefas | Uma tarefa lenta atrasa as seguintes. |
| `newScheduledThreadPool(n)` | Agendar ou repetir | Defina política de falhas e encerramento. |
| `newVirtualThreadPerTaskExecutor()` | Muitas tarefas bloqueantes de I/O | A tarefa ainda disputa conexão, memória e serviço remoto; limite esses recursos separadamente. |

Em tarefas CPU-bound, comece medindo próximo da quantidade de núcleos efetivamente disponíveis. Em I/O-bound, mais concorrência pode ajudar, mas a fórmula baseada em tempo de espera é só estimativa inicial. **Medição, fila, latência e limites do serviço externo** decidem o ajuste real.

`shutdown()` para de aceitar tarefas e deixa as já aceitas terminarem; `shutdownNow()` solicita interrupção, sem prometer parada imediata. `awaitTermination()` espera após iniciar o encerramento. Verifique cancelamento e propague/restaure `InterruptedException` quando apropriado.

## Para fixar

O que muda entre `execute(Runnable)` e `submit(Callable<T>)`? Escreva um cálculo que retorna `Future<T>`, aguarde o resultado e garanta o fechamento do executor.

Fonte: videoaula 02, material autoral, slides 02 e legenda automática. As regras de dimensionamento foram registradas como heurísticas, não garantias.
