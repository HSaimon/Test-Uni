package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProdutoTest {
    Produto produto;

    @BeforeEach
    public void setUp() {
        produto = new Produto("Produto Exemplo", 10.0, 100);
    }

    @Test
    public void testarCriacaoProdutoComValoresValidos() {
        String nome = produto.getNome();
        double preco = produto.getPreco();
        int estoque = produto.getEstoque();

        Assertions.assertEquals("Produto Exemplo", nome);
        Assertions.assertEquals(10.0, preco);
        Assertions.assertEquals(100, estoque);
    }

    @Test
    public void testarCriacaoProdutoComPrecoNegativo() {
        produto = new Produto("Produto Inválido", -10.0, 100);

        Assertions.assertTrue(produto.getPreco() < 0, "O preço não pode ser negativo.");
    }

    @Test
    public void testarCriacaoProdutoComEstoqueNegativo() {
        produto = new Produto("Produto Inválido", 10.0, -5);

        Assertions.assertTrue(produto.getEstoque() < 0, "O estoque não pode ser negativo.");
    }

    @Test
    public void testarAlteracaoNomeProdutoParaValorValido() {
        produto.setNome("Produto Alterado");

        Assertions.assertEquals("Produto Alterado", produto.getNome());
    }

    @Test
    public void testarAlteracaoPrecoProdutoParaValorValido() {
        produto.setPreco(15.0);

        Assertions.assertEquals(15.0, produto.getPreco());
    }

    @Test
    public void testarAlteracaoEstoqueProdutoParaValorPositivo() {
        produto.aumentarEstoque(50);

        Assertions.assertEquals(150, produto.getEstoque());
    }

    @Test
    public void testarAlteracaoPrecoProdutoParaValorNegativo() {
        produto.setPreco(-5.0);

        Assertions.assertTrue(produto.getPreco() < 0, "O preço não pode ser negativo.");
    }
}
