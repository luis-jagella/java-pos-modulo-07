# Aula 08 — Virtual Threads

## Ideia central

Uma thread de plataforma ocupa uma thread do sistema operacional durante sua execução. Uma **thread virtual** é gerenciada pela plataforma Java e executa sobre uma *carrier thread* quando está ativa. Em muitas operações bloqueantes compatíveis, ela pode ser desmontada da carrier enquanto espera. Isso torna viável o modelo **uma thread virtual por tarefa** em aplicações com muito I/O.

As threads virtuais foram finalizadas no **Java 21**. Podem ser criadas com `Thread.startVirtualThread(...)` ou `Executors.newVirtualThreadPerTaskExecutor()`. Elas mantêm o estilo de código bloqueante e os recursos familiares de stack trace e depuração.

## Onde ajudam e onde não ajudam

- Ajudam na **vazão de tarefas I/O-bound**: durante a espera, a carrier pode executar outra tarefa. Não fazem uma consulta individual ao banco terminar mais rápido por si só.
- Não criam núcleos de CPU. Para cálculos pesados, mais threads virtuais podem apenas aumentar a disputa por CPU.
- Não se deve criar um pool pequeno de threads virtuais para “economizá-las”. Limite recursos escassos com pool de conexões, semáforo, fila ou controle de taxa.
- Código nativo/foreign calls e outras operações que prendam a carrier merecem observação. O comportamento de bloqueio em `synchronized` melhorou no JDK 24; não repita sem contexto a regra antiga de que todo bloqueio em monitor sempre causa pinning.
- A Lei de Little relaciona concorrência média, taxa de chegada e tempo médio no sistema sob condições estáveis: `L = λ × W`. Não é promessa de throughput nem justificativa para aceitar fila ilimitada.

`Structured Concurrency` aparece no material como tema complementar. A API e o estado de preview variam por JDK; este projeto usa apenas recursos estáveis de Java 25.

## Para fixar

Execute 100 tarefas que esperam brevemente em threads virtuais e compare com um pool pequeno de threads de plataforma. Observe o tempo total, mas não extrapole o resultado de `sleep` para banco de dados sem medir o serviço real.

Fonte: videoaula 08, material autoral, slides 08 e legenda automática.
