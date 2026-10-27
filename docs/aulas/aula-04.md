# Aula 04 — Synchronizers

## Ideia central

Além de proteger uma variável, programas concorrentes precisam **coordenar fases e acesso a recursos**. `java.util.concurrent` oferece sincronizadores com contratos diferentes.

| Ferramenta | O que coordena | Regra importante |
| --- | --- | --- |
| `CountDownLatch` | Esperar N eventos concluírem | Contagem só diminui; não é reutilizável. |
| `CyclicBarrier` | Fazer N participantes se encontrarem | Reutilizável; timeout, interrupção ou falha podem quebrar a barreira. |
| `Semaphore` | Limitar acessos simultâneos | Libere no `finally`, somente após adquirir a permissão. |
| `Phaser` | Fases com participantes dinâmicos | Registro e desregistro precisam ser balanceados para não deixar alguém aguardando para sempre. |

`CountDownLatch` é adequado para uma inicialização que precisa de várias etapas independentes. `CyclicBarrier` cabe quando trabalhadores repetem rodadas sincronizadas. `Semaphore` limita conexões ou chamadas simultâneas, inclusive quando as tarefas rodam em threads virtuais. `Phaser` permite fases e participantes variáveis.

Nenhum deles substitui automaticamente a proteção de estado compartilhado. Se os trabalhadores escrevem no mesmo objeto, ainda é preciso um contrato de acesso seguro.

## Para fixar

Crie três trabalhadores e uma `CountDownLatch(3)`. Faça cada um chamar `countDown()` no `finally` e a thread principal chamar `await()` com timeout. O que ocorre se um trabalhador falha antes de decrementar?

Fonte: videoaula 04, material autoral, slides 04 e legenda automática.
