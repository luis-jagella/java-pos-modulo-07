package dev.jagella.pos.concorrencia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;

final class ExemplosTest {
    @Test
    void poolFixoSomaTodosOsBlocos() throws Exception {
        List<Integer> numeros = new ArrayList<>();
        for (int i = 1; i <= 1_000; i++) {
            numeros.add(i);
        }
        assertEquals(500_500, ExecutorExamples.somar(numeros, 4));
        assertEquals(0, ExecutorExamples.somar(List.of(), 2));
    }

    @Test
    void poolRejeitaQuantidadeInvalida() {
        assertThrows(IllegalArgumentException.class,
                () -> ExecutorExamples.somar(List.of(1), 0));
    }

    @Test
    void mapaNaoPerdeAtualizacoesConcorrentes() throws Exception {
        List<String> palavras = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            palavras.add("java");
        }
        palavras.add("loom");
        assertEquals(Map.of("java", 200, "loom", 1),
                ConcurrentCollectionsExamples.contar(palavras));
    }

    @Test
    void latchEsperaTodasAsEtapas() throws Exception {
        AtomicInteger etapas = new AtomicInteger();
        SynchronizerExamples.aguardarEtapas(List.of(
                etapas::incrementAndGet, etapas::incrementAndGet, etapas::incrementAndGet));
        assertEquals(3, etapas.get());
    }

    @Test
    void latchLiberaMesmoSeUmaEtapaFalhar() {
        assertThrows(ExecutionException.class, () -> SynchronizerExamples.aguardarEtapas(
                List.of(() -> { throw new IllegalStateException("falha esperada"); }, () -> {})));
    }

    @Test
    void forkJoinPreservaResultadoComLimiaresDiferentes() {
        long[] numeros = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertEquals(45, ForkJoinExamples.somar(numeros, 1));
        assertEquals(45, ForkJoinExamples.somar(numeros, 100));
        assertEquals(0, ForkJoinExamples.somar(new long[0], 1));
        assertThrows(IllegalArgumentException.class,
                () -> ForkJoinExamples.somar(numeros, 0));
    }

    @Test
    void completableFutureCombinaECompoe() {
        try (var executor = Executors.newFixedThreadPool(2)) {
            assertEquals(7, CompletableFutureExamples
                    .somarResultados(() -> 3, () -> 4, executor).join());
        }
        assertEquals("JAVA", CompletableFutureExamples
                .buscarEFormatar(() -> CompletableFuture.completedFuture("java")).join());
    }

    @Test
    void completableFuturePropagaFalha() {
        try (var executor = Executors.newSingleThreadExecutor()) {
            assertThrows(java.util.concurrent.CompletionException.class,
                    () -> CompletableFutureExamples.somarResultados(
                            () -> { throw new IllegalStateException("falha esperada"); },
                            () -> 2, executor).join());
        }
    }

    @Test
    void streamParaleloTemMesmoResultadoQueSequencial() {
        List<Integer> numeros = List.of(1, 2, 3, 4, 5);
        long esperado = numeros.stream().mapToLong(n -> (long) n * n).sum();
        assertEquals(esperado, ParallelStreamExamples.somaDosQuadrados(numeros));
    }

    @Test
    void threadsVirtuaisRespeitamLimiteDoRecurso() throws Exception {
        AtomicInteger ativos = new AtomicInteger();
        AtomicInteger maximo = new AtomicInteger();
        List<Callable<Integer>> chamadas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int valor = i;
            chamadas.add(() -> {
                assertTrue(Thread.currentThread().isVirtual());
                int atual = ativos.incrementAndGet();
                maximo.accumulateAndGet(atual, Math::max);
                try {
                    Thread.sleep(5);
                    return valor;
                } finally {
                    ativos.decrementAndGet();
                }
            });
        }
        List<Integer> resultados = VirtualThreadExamples.executarComLimite(chamadas, 3);
        assertEquals(20, resultados.size());
        assertEquals(0, resultados.get(0));
        assertEquals(19, resultados.get(19));
        assertEquals(0, ativos.get());
        assertTrue(maximo.get() <= 3);
        assertFalse(resultados.isEmpty());
    }

    @Test
    void threadsVirtuaisRejeitamLimiteInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> VirtualThreadExamples.executarComLimite(List.of(), 0));
    }
}
