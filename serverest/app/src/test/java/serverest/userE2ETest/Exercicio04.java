package serverest.userE2ETest;

import org.apache.http.HttpStatus;
import org.junit.Test;
import serverest.control.Carrinho;
import serverest.control.Produto;
import serverest.control.Usuario;
import serverest.model.ProdutoDTO;
import serverest.model.UsuarioDTO;
import serverest.util.Mensagens;

public class Exercicio04 {

    private Usuario usuario = new Usuario();
    private Produto produto = new Produto();
    private Carrinho carrinho = new Carrinho();

    @Test
    public void validarExclusaoUsuario(){
        //cadastrar user
        UsuarioDTO usuarioDTO = new UsuarioDTO("true");
        String userId = usuario.cadastrarUsuario(usuarioDTO, HttpStatus.SC_CREATED, Mensagens.cadastroSucesso);

        //autenticar user
        String token = usuario.autenticarUsuario(usuarioDTO, HttpStatus.SC_OK, Mensagens.loginSucesso);

        //criar produto
        ProdutoDTO produtoDTO = new ProdutoDTO();
        String prodId = produto.cadastrarProduto(produtoDTO, HttpStatus.SC_CREATED, Mensagens.cadastroSucesso, token);

        //cadastrar carrinho
        carrinho.cadastrarCarrinho(token, prodId, 1, HttpStatus.SC_CREATED, Mensagens.cadastroSucesso);

        //excluir usuario sem sucesso
        usuario.excluirUsuario(userId, HttpStatus.SC_BAD_REQUEST, Mensagens.usuarioComCarrinho);

        //concluir compra
        carrinho.concluirCompra(token, HttpStatus.SC_OK, Mensagens.compraConcluidaSucesso);

        //excluir usuario com sucesso
        usuario.excluirUsuario(userId, HttpStatus.SC_OK, Mensagens.usuarioExcluidoSucesso);
    }
}
