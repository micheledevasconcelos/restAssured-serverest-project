package serverest.contractTest;

import org.apache.http.HttpStatus;
import org.junit.Test;
import serverest.control.Produto;
import serverest.model.ProdutoDTO;
import serverest.util.Mensagens;

public class ProdutosContractTest {

    private Produto produto = new Produto();

    @Test
    public void testListarProdutos (){
        produto.listarProduto();
    }
}
