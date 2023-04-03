package com.exemplo.api.repository;

import com.exemplo.api.model.Livro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class LivroRepository {
    private int idCounter;

    private List<Livro> livros;

    public LivroRepository() {
        List<Livro> cadastrados = List.of(
                new Livro(++idCounter, "Clean Code", "12345678", 2013, "Autor Clean Code"),
                new Livro(++idCounter, "Arquitetura Limpa", "09876543", 2015, "Autor Arquitetura Limpa"),
                new Livro(++idCounter, "Pense simples, pense Java", "1112233", 2010, "Autor Pense Java")
        );

        this.livros = new ArrayList<>(cadastrados);
    }

    public List<Livro> findAll() {
        List<Livro> copia = new ArrayList<>(livros);

        copia.sort(Comparator.comparing(Livro::getId));

        return copia;
    }

    public Livro findById(Integer id) {
        return livros.stream().filter(l -> l.getId().equals(id))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public Livro insert(Livro novo) {
        novo.setId(++idCounter);

        livros.add(novo);

        return novo;
    }

    public Livro update(Livro alterado) {
        boolean isExcluido = livros.removeIf(l -> l.getId().equals(alterado.getId()));

        if (!isExcluido) {
            throw new IllegalArgumentException();
        }

        livros.add(alterado);

        return alterado;
    }

    public void delete(Integer id) {
        livros.removeIf(l -> l.getId().equals(id));
    }

}
