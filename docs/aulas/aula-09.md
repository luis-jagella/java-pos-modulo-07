# Aula 09 — Mão na Massa

## Ideia central

A última videoaula liga os conceitos das aulas 02–08 aos exemplos do [repositório do professor](https://github.com/eldermoraes/unipds/tree/main/Concorrencia-Multithreading). O material autoral organiza **21 exemplos** em sete pastas: quatro de executores, três de coleções, quatro de sincronizadores, dois de Fork/Join, quatro de `CompletableFuture`, três de streams paralelos e um de threads virtuais.

## Roteiro de execução

1. **Executors:** compare tarefas CPU-bound em pool fixo, I/O simulado em threads virtuais, agendamento e ciclo de vida. Não rode uma carga massiva de exemplo sem reduzir parâmetros ao hardware disponível.
2. **Coleções:** compare mapa sincronizado e concorrente com o mesmo trabalho; experimente lista copy-on-write e fila produtor-consumidor limitada.
3. **Sincronizadores:** observe o que cada ferramenta bloqueia e como lidar com falha, timeout e interrupção.
4. **Fork/Join:** verifique a soma sequencial e paralela para os mesmos dados e ajuste o limiar de divisão.
5. **`CompletableFuture`:** diferencie transformação (`thenApply`), composição (`thenCompose`), combinação (`thenCombine`/`allOf`) e tratamento de erros.
6. **Parallel Streams:** compare um caso CPU-bound com contraexemplos de I/O bloqueante e mutação de estado compartilhado.
7. **Threads virtuais:** aumente a quantidade de tarefas aos poucos e monitore memória, CPU, duração, fila e limites externos.

O repositório de apoio pode evoluir em relação ao vídeo: o material cita 100 mil tarefas no exemplo de threads virtuais e também registra uma versão publicada com 200 mil. Os exemplos próprios deste repositório usam cargas **pequenas e reproduzíveis**, com assertions de correção. Benchmark sério exige condições controladas e repetição.

## Para fixar

Escolha três exemplos que tratem problemas diferentes: CPU, I/O e coordenação. Para cada um, escreva a hipótese antes de executar e confirme o resultado funcional antes de comparar tempos.

Fonte: videoaula 09, material autoral, slides 09 e legenda automática. Os números de arquivos/versões refletem o material consultado, não uma promessa de estabilidade do repositório externo.
