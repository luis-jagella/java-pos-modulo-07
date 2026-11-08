package dev.jagella.pos.concorrencia;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/** Aula 04: a thread principal aguarda a conclusão de N etapas independentes. */
public final class SynchronizerExamples {
    private SynchronizerExamples() {
    }

    public static void aguardarEtapas(List<Runnable> etapas)
            throws InterruptedException, ExecutionException {
        List<Runnable> entrada = List.copyOf(etapas);
        CountDownLatch concluidas = new CountDownLatch(entrada.size());
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<?>> tarefas = new ArrayList<>();
            for (Runnable etapa : entrada) {
                tarefas.add(executor.submit(() -> {
                    try {
                        etapa.run();
                    } finally {
                        concluidas.countDown();
                    }
                }));
            }
            concluidas.await();
            for (Future<?> tarefa : tarefas) {
                tarefa.get();
            }
        }
    }
}
