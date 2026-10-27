# Ressalvas técnicas para estudar com segurança

O material é uma boa trilha, mas algumas frases dos slides são atalhos didáticos. Para aplicar em produção:

- **Concorrência e desempenho:** executar tarefas concorrentemente não garante resposta mais rápida. Distingua vazão, latência e uso de CPU. Meça com carga representativa.
- **Dimensionamento de pools:** `núcleos + 1` e a fórmula de espera/serviço são pontos de partida. Considere limites do contêiner, bloqueio, memória, fila e capacidade do serviço chamado.
- **`ConcurrentHashMap`:** não usa apenas “um lock por bucket” em todos os caminhos nem fornece snapshot global ao iterar. Use operações compostas atômicas quando precisar atualizar com base no valor atual.
- **`CopyOnWriteArrayList`:** o array é copiado na escrita; leituras não tornam mutáveis os objetos armazenados magicamente imutáveis.
- **`CyclicBarrier`:** a última thread a chegar *dispara* a ação opcional e libera o grupo; interrupção/timeout podem quebrá-la. Não use “quebra” para descrever a chegada normal como se fosse falha.
- **`CompletableFuture`:** `handle` pode transformar o resultado ou recuperar falha; não equivale exatamente a `finally`. `allOf` retorna `CompletableFuture<Void>`; recolha resultados dos futuros originais.
- **Streams paralelos:** heurísticas como `N × Q > 10.000` não definem um limiar universal. O pool comum é compartilhado; lambdas com estado e I/O bloqueante exigem cuidado.
- **Threads virtuais:** são leves para muitas esperas, mas continuam consumindo memória e recursos externos. Não aumentam paralelismo de CPU além da capacidade disponível. O antigo problema de pinning em `synchronized` mudou no JDK 24; verifique a versão em uso.
- **Structured Concurrency:** o curso a menciona, mas o exemplo prático deste repositório evita APIs preview. Não a trate como recurso estável em qualquer JDK apenas pelo nome.
- **Legendas automáticas:** podem trocar termos de API, números e nomes. Em conflito, confronte slides, código compilável e documentação oficial da versão de Java usada.
