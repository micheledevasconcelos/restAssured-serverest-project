package serverest.userE2ETest;

import org.apache.http.HttpStatus;
import org.junit.Test;
import serverest.control.Carrinho;
import serverest.control.Produto;
import serverest.control.Usuario;
import serverest.model.ProdutoDTO;
import serverest.model.UsuarioDTO;
import serverest.util.Mensagens;

public class Exercicio03 {

    private Usuario usuario = new Usuario();
    private Produto produto = new Produto();
    private Carrinho carrinho = new Carrinho();

    @Test
    public void validarEstoque(){
        //cadastrar user
        UsuarioDTO usuarioDTO = new UsuarioDTO("true");
        String userId = usuario.cadastrarUsuario(usuarioDTO, HttpStatus.SC_CREATED, Mensagens.cadastroSucesso);

        //autenticar user
        String token = usuario.autenticarUsuario(usuarioDTO, HttpStatus.SC_OK, Mensagens.loginSucesso);

        //criar produto
        ProdutoDTO produtoDTO = new ProdutoDTO();
        String prodId = produto.cadastrarProduto(produtoDTO, HttpStatus.SC_CREATED, Mensagens.cadastroSucesso, token);

        //verificar estoque produto
        Integer estoqueInit = produtoDTO.getQuantidade();
        produto.verificarEstoque(prodId, estoqueInit);

        //cadastrar carrinho
        carrinho.cadastrarCarrinho(token, prodId, 1, HttpStatus.SC_CREATED, Mensagens.cadastroSucesso);

        //verificar estoque produto (prod--)
        produto.verificarEstoque(prodId, estoqueInit-1);

        //cancelar compra
        carrinho.cancelarCompra(token, HttpStatus.SC_OK, Mensagens.compraCanceladaSucesso);

        //verificar estoque (prod++)
        produto.verificarEstoque(prodId, estoqueInit);
    }
}
