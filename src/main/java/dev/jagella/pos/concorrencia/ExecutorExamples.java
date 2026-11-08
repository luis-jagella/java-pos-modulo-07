package dev.jagella.pos.concorrencia;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/** Aula 02: tarefas CPU-bound em um pool limitado de threads de plataforma. */
public final class ExecutorExamples {
    private ExecutorExamples() {
    }

    public static long somar(List<Integer> valores, int trabalhadores)
            throws InterruptedException, ExecutionException {
        if (trabalhadores < 1) {
            throw new IllegalArgumentException("trabalhadores deve ser positivo");
        }
        List<Integer> entrada = List.copyOf(valores);
        try (ExecutorService executor = Executors.newFixedThreadPool(trabalhadores)) {
            List<Future<Long>> resultados = new ArrayList<>();
            for (int inicio = 0; inicio < entrada.size(); inicio += 100) {
                int de = inicio;
                int ate = Math.min(inicio + 100, entrada.size());
                resultados.add(executor.submit(() -> {
                    long parcial = 0;
                    for (int i = de; i < ate; i++) {
                        parcial += entrada.get(i);
                    }
                    return parcial;
                }));
            }
            long total = 0;
            for (Future<Long> resultado : resultados) {
                total += resultado.get();
            }
            return total;
        }
    }
}
