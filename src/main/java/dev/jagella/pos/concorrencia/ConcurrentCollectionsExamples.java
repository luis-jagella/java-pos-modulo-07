package dev.jagella.pos.concorrencia;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/** Aula 03: atualizações compostas atômicas em um mapa concorrente. */
public final class ConcurrentCollectionsExamples {
    private ConcurrentCollectionsExamples() {
    }

    public static Map<String, Integer> contar(List<String> palavras)
            throws InterruptedException, ExecutionException {
        List<String> entrada = List.copyOf(palavras);
        ConcurrentHashMap<String, Integer> contagem = new ConcurrentHashMap<>();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<?>> tarefas = new ArrayList<>();
            for (String palavra : entrada) {
                tarefas.add(executor.submit(() -> contagem.merge(palavra, 1, Integer::sum)));
            }
            for (Future<?> tarefa : tarefas) {
                tarefa.get();
            }
        }
        return Map.copyOf(contagem);
    }
}
