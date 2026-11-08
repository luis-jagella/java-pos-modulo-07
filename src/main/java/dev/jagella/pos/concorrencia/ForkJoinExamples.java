package dev.jagella.pos.concorrencia;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/** Aula 05: dividir uma soma em tarefas CPU-bound e combinar resultados. */
public final class ForkJoinExamples {
    private ForkJoinExamples() {
    }

    public static long somar(long[] valores, int limiar) {
        if (limiar < 1) {
            throw new IllegalArgumentException("limiar deve ser positivo");
        }
        long[] entrada = valores.clone();
        try (ForkJoinPool pool = new ForkJoinPool()) {
            return pool.invoke(new Soma(entrada, 0, entrada.length, limiar));
        }
    }

    private static final class Soma extends RecursiveTask<Long> {
        private final long[] valores;
        private final int inicio;
        private final int fim;
        private final int limiar;

        private Soma(long[] valores, int inicio, int fim, int limiar) {
            this.valores = valores;
            this.inicio = inicio;
            this.fim = fim;
            this.limiar = limiar;
        }

        @Override
        protected Long compute() {
            if (fim - inicio <= limiar) {
                long total = 0;
                for (int i = inicio; i < fim; i++) {
                    total += valores[i];
                }
                return total;
            }
            int meio = inicio + (fim - inicio) / 2;
            Soma esquerda = new Soma(valores, inicio, meio, limiar);
            Soma direita = new Soma(valores, meio, fim, limiar);
            esquerda.fork();
            long resultadoDireito = direita.compute();
            return esquerda.join() + resultadoDireito;
        }
    }
}
