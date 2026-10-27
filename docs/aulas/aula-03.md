# Aula 03 — Concurrent Collections

## Ideia central

Uma coleção comum pode ficar inconsistente sob acesso simultâneo. As coleções concorrentes oferecem contratos específicos; escolher a estrutura certa evita um lock global desnecessário e reduz código manual.

## Principais estruturas

- `ConcurrentHashMap` aceita leituras/escritas concorrentes, não permite chave nem valor `null` e oferece operações compostas como `compute`, `merge` e `putIfAbsent`. `get` seguido de `put` não vira atômico só porque o mapa é concorrente.
- Seus iteradores são **fracamente consistentes**: podem refletir parte das alterações concorrentes. Isso é diferente de prometer um snapshot instantâneo.
- `CopyOnWriteArrayList` copia o array em mutações e fornece iteração sobre um snapshot. É útil para listas pequenas, lidas muitas vezes e alteradas raramente, como listeners. Escrita custa cópia e memória.
- `BlockingQueue` implementa coordenação produtor-consumidor. Uma fila **limitada** cria backpressure: `put` espera espaço e `take` espera item. Decida como encerrar consumidores e lidar com interrupção.
- `Collections.synchronizedMap` sincroniza chamadas individuais. Para iterar com segurança, ainda é preciso seguir a regra de sincronização externa da API; sequências de operações podem exigir um bloco único.

## Para fixar

Implemente uma contagem de palavras concorrente com `ConcurrentHashMap.merge`. Depois teste uma fila limitada com mais produtores que consumidores: qual operação espera quando ela enche?

Fonte: videoaula 03, material autoral, slides 03 e legenda automática.
