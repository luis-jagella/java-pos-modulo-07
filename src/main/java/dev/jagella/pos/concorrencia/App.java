package dev.jagella.pos.concorrencia;

import java.util.List;
import java.util.concurrent.Executors;

/** Aula 09: pequena demonstração dos exemplos, sem criar carga massiva. */
public final class App {
    private App() {
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Pool fixo: " + ExecutorExamples.somar(List.of(1, 2, 3, 4), 2));
        System.out.println("Mapa concorrente: "
                + ConcurrentCollectionsExamples.contar(List.of("java", "loom", "java")));
        System.out.println("Fork/Join: " + ForkJoinExamples.somar(new long[]{1, 2, 3, 4}, 2));
        try (var executor = Executors.newFixedThreadPool(2)) {
            System.out.println("CompletableFuture: " + CompletableFutureExamples
                    .somarResultados(() -> 2, () -> 3, executor).join());
        }
        System.out.println("Stream paralelo: "
                + ParallelStreamExamples.somaDosQuadrados(List.of(1, 2, 3)));
        System.out.println("Threads virtuais com limite: "
                + VirtualThreadExamples.executarComLimite(
                List.of(() -> "A", () -> "B", () -> "C"), 2));
    }
}
