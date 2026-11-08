package dev.jagella.pos.concorrencia;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

/** Aula 08: uma virtual thread por tarefa, com limite do recurso externo. */
public final class VirtualThreadExamples {
    private VirtualThreadExamples() {
    }

    public static <T> List<T> executarComLimite(List<Callable<T>> chamadas, int acessosSimultaneos)
            throws InterruptedException, ExecutionException {
        if (acessosSimultaneos < 1) {
            throw new IllegalArgumentException("acessosSimultaneos deve ser positivo");
        }
        List<Callable<T>> entrada = List.copyOf(chamadas);
        Semaphore limite = new Semaphore(acessosSimultaneos);
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<T>> tarefas = new ArrayList<>();
            for (Callable<T> chamada : entrada) {
                tarefas.add(executor.submit(() -> {
                    limite.acquire();
                    try {
                        return chamada.call();
                    } finally {
                        limite.release();
                    }
                }));
            }
            List<T> resultados = new ArrayList<>();
            for (Future<T> tarefa : tarefas) {
                resultados.add(tarefa.get());
            }
            return List.copyOf(resultados);
        }
    }
}
