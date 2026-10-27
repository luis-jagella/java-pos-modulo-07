# Módulo 07 — Concorrência e Multithreading em Java

Estudos da pós-graduação **Java Applied Engineering**. O portal apresenta um único **Módulo 01**, com nove videoaulas, um podcast e uma avaliação.

## Organização

| Branch | Conteúdo |
| --- | --- |
| `main` | Índice, resumos e revisão da disciplina |
| `modulo-01-concorrencia-multithreading` | Exemplos executáveis das aulas e testes |

Os checks abaixo indicam **anotações registradas no Git**, não certificam que o vídeo foi assistido integralmente nem alteram o progresso do portal.

## Módulo 01

- [x] [01 — Overview](docs/aulas/aula-01.md)
- [x] [02 — Executors e Thread Pools](docs/aulas/aula-02.md)
- [x] [03 — Concurrent Collections](docs/aulas/aula-03.md)
- [x] [04 — Synchronizers](docs/aulas/aula-04.md)
- [x] [05 — Fork/Join Framework](docs/aulas/aula-05.md)
- [x] [06 — CompletableFuture](docs/aulas/aula-06.md)
- [x] [07 — Parallel Streams](docs/aulas/aula-07.md)
- [x] [08 — Virtual Threads](docs/aulas/aula-08.md)
- [x] [09 — Mão na Massa](docs/aulas/aula-09.md)
- [x] [Podcast 01 — Polêmicas e opiniões dev](docs/podcast-01.md)

Veja a [revisão para praticar](docs/revisao.md) e as [correções e ressalvas técnicas](docs/ressalvas.md).

## Como estudar

1. Leia o resumo da aula e responda à pergunta de revisão sem consultar a resposta.
2. Execute o exemplo correspondente na branch do Módulo 01.
3. Altere um parâmetro, faça uma previsão e compare o resultado.
4. Volte ao vídeo ou às fontes primárias quando houver dúvida.

## Exemplos executáveis

Na branch `modulo-01-concorrencia-multithreading`, instale **JDK 21+** e Maven 3.9+, então execute:

```bash
mvn verify
java -cp target/classes dev.jagella.pos.concorrencia.App
```

Os exemplos cobrem pool fixo, mapa concorrente, `CountDownLatch`, Fork/Join, composição assíncrona, stream paralelo e threads virtuais com semáforo. São cargas pequenas para estudo, sem pretensão de benchmark ou de simular um serviço de produção. Os testes verificam resultados, propagação de falha e limite de acesso simultâneo.

## Fontes e limites

Os títulos e a estrutura foram conferidos no portal. Os resumos foram escritos a partir do **material autoral**, dos **slides da disciplina** e das **legendas automáticas do player**; as legendas podem conter erros de reconhecimento. Os exemplos são adaptações próprias, não cópias do [repositório apresentado pelo professor](https://github.com/eldermoraes/unipds/tree/main/Concorrencia-Multithreading). Arquivos de mídia, PDFs e endereços temporários de sessão ficam somente na máquina local.

Alguns slides simplificam regras de concorrência e estimativas de desempenho. As ressalvas estão registradas para evitar decorar aproximações como garantias.

## Avaliação

A avaliação do portal é uma etapa separada. Este repositório não registra nota nem entrega sem confirmação do aluno.
