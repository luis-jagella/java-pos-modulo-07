# Aula 07 — Parallel Streams

## Ideia central

`parallelStream()` ou `.parallel()` pode dividir operações de stream entre trabalhadores do `ForkJoinPool.commonPool()`. A sintaxe é simples; a decisão de usá-la depende do custo de dividir, processar e combinar os dados.

Use como candidato quando houver coleção suficientemente grande e bem divisível, operação **CPU-bound** significativa por elemento, funções sem estado mutável compartilhado e redução associativa. Para dados pequenos ou operações baratas, o overhead pode vencer. O valor `N × Q > 10.000` apresentado nos slides é apenas heurística didática, não uma regra confiável de desempenho.

Evite fazer chamadas bloqueantes de rede ou disco no pool comum. Evite `forEach` escrevendo em `ArrayList` externa: a escrita é uma condição de corrida. Prefira operações de redução/`collect` adequadas. A ordem de execução não é garantida; se a ordem de saída importa, use as operações que preservam o *encounter order* e meça seu custo.

Um benchmark útil inclui aquecimento da JVM, entradas representativas, controle do ambiente e validação de que o resultado paralelo é igual ao sequencial. Tempo de uma única execução não demonstra ganho.

## Para fixar

Compare a soma sequencial e paralela de muitos números com redução associativa. Em seguida substitua por uma operação barata e observe como o custo relativo muda, sem inferir desempenho a partir de apenas uma rodada.

Fonte: videoaula 07, material autoral, slides 07 e legenda automática.
