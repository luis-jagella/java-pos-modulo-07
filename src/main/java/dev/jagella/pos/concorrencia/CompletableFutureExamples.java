package dev.jagella.pos.concorrencia;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

/** Aula 06: duas tarefas independentes combinadas sem bloqueio intermediário. */
public final class CompletableFutureExamples {
    private CompletableFutureExamples() {
    }

    public static CompletableFuture<Integer> somarResultados(
            Supplier<Integer> primeira, Supplier<Integer> segunda, Executor executor) {
        CompletableFuture<Integer> a = CompletableFuture.supplyAsync(primeira, executor);
        CompletableFuture<Integer> b = CompletableFuture.supplyAsync(segunda, executor);
        return a.thenCombine(b, Integer::sum);
    }

    public static CompletableFuture<String> buscarEFormatar(
            Supplier<CompletableFuture<String>> busca) {
        return CompletableFuture.completedFuture("pedido")
                .thenCompose(ignorado -> busca.get())
                .thenApply(String::toUpperCase);
    }
}
