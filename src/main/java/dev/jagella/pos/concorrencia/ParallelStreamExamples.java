package dev.jagella.pos.concorrencia;

import java.util.List;

/** Aula 07: redução pura, sem mutação de estado externo. */
public final class ParallelStreamExamples {
    private ParallelStreamExamples() {
    }

    public static long somaDosQuadrados(List<Integer> valores) {
        return List.copyOf(valores).parallelStream()
                .mapToLong(valor -> (long) valor * valor)
                .sum();
    }
}
