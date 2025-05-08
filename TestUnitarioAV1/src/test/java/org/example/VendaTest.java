package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class VendaTest {
    Produto produto;
    Produto produtoEstoqueZero;
    Venda venda;

    Produto produto1;
    Produto produto2;
    Venda venda1;
    Venda venda2;

    @BeforeEach
    public void setUp() {
        produto = new Produto("Produto Exemplo", 10.0, 100);
        produtoEstoqueZero = new Produto("Produto Exemplo", 10.0, 0);
        produto1 = new Produto("Produto 1", 10.0, 100);
        produto2 = new Produto("Produto 2", 20.0, 150);
    }

    @Test
    public void testarVendaComQuantidadeMenorQueEstoqueDisponivel() {
        venda = new Venda(produto, 50);
        boolean resultado = venda.realizarVenda();
        Assertions.assertTrue(resultado);
        Assertions.assertEquals(500.0, venda.getTotalVenda());
        Assertions.assertEquals(50, produto.getEstoque());
    }

    @Test
    public void testarVendaComQuantidadeIgualAoEstoqueDisponivel() {
        venda = new Venda(produto, 100);
        boolean resultado = venda.realizarVenda();
        Assertions.assertTrue(resultado);
        Assertions.assertEquals(1000.0, venda.getTotalVenda());
        Assertions.assertEquals(0, produto.getEstoque());
    }

    @Test
    public void testarVendaComQuantidadeMaiorQueEstoqueDisponivel() {
        venda = new Venda(produto, 150);
        boolean resultado = venda.realizarVenda();
        Assertions.assertFalse(resultado);
        Assertions.assertEquals(100, produto.getEstoque());
    }

    @Test
    public void testarCalculoTotalVendaCorretamente() {
        venda = new Venda(produto, 30);
        venda.realizarVenda();
        Assertions.assertEquals(300.0, venda.getTotalVenda());
    }

    @Test
    public void testarDiminuicaoEstoqueProdutoAposVendaBemSucedida() {
        venda = new Venda(produto, 30);
        boolean resultado = venda.realizarVenda();
        Assertions.assertTrue(resultado);
        Assertions.assertEquals(70, produto.getEstoque());
    }

    @Test
    public void testarRealizarVendaDeProdutoQueNaoExiste() {
        Produto produtoInexistente = new Produto("Produto Inexistente", 10.0, 0);
        venda = new Venda(produtoInexistente, 10);
        boolean resultado = venda.realizarVenda();
        Assertions.assertFalse(resultado);
    }

    @Test
    public void testarAlteracaoEstoqueAposTentativaDeVendaComEstoqueInsuficiente() {
        venda = new Venda(produto, 150);
        boolean resultado = venda.realizarVenda();
        Assertions.assertFalse(resultado);
        Assertions.assertEquals(100, produto.getEstoque());
    }

    @Test
    public void testarCriacaoDeVariosProdutosERealizarVendasComEstoqueCompartilhado() {
        venda1 = new Venda(produto1, 30);
        venda2 = new Venda(produto2, 50);
        venda1.realizarVenda();
        venda2.realizarVenda();
        Assertions.assertEquals(70, produto1.getEstoque());
        Assertions.assertEquals(100, produto2.getEstoque());
    }

    @Test
    public void testarCalcularTotalVendaQuandoPrecoAlteradoAntesDaVenda() {
        produto.setPreco(15.0);
        venda = new Venda(produto, 10);
        venda.realizarVenda();
        Assertions.assertEquals(150.0, venda.getTotalVenda());
    }

    @Test
    public void testarComportamentoDaVendaQuandoEstoqueInicialEhZero() {
        venda = new Venda(produtoEstoqueZero, 1);
        boolean resultado = venda.realizarVenda();
        Assertions.assertFalse(resultado);
        Assertions.assertEquals(0, produtoEstoqueZero.getEstoque());
    }

    @Test
    public void testarAumentoEstoqueProdutoAposVenda() {
        venda = new Venda(produto, 30);
        venda.realizarVenda();
        produto.aumentarEstoque(20);
        Assertions.assertEquals(90, produto.getEstoque());
    }

    @Test
    public void testarCriacaoVendaComQuantidadeNegativa() {
        venda = new Venda(produto, -10);
        boolean resultado = venda.realizarVenda();
        Assertions.assertFalse(resultado);
    }

    @Test
    public void testarAumentoDoEstoqueAposReposicaoEVendaBemSucedida() {
        venda = new Venda(produto, 30);
        boolean resultadoVendaInicial = venda.realizarVenda();
        Assertions.assertTrue(resultadoVendaInicial);
        int estoqueApósVenda = produto.getEstoque();
        Assertions.assertEquals(70, estoqueApósVenda);
        produto.aumentarEstoque(30);
        venda = new Venda(produto, 30);
        boolean resultadoVendaFinal = venda.realizarVenda();
        Assertions.assertTrue(resultadoVendaFinal);
        int estoqueFinal = produto.getEstoque();
        Assertions.assertEquals(70, estoqueFinal);
    }
}
