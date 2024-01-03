package serverest.control;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import serverest.model.ProdutoDTO;
import serverest.util.Endpoint;
import serverest.util.Environment;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

public class Produto {

    public void listarProduto(){
        when()
                .get(Environment.localhost + Endpoint.produtos)
        .then()
                .statusCode(HttpStatus.SC_OK);
    }

    public String cadastrarProduto (ProdutoDTO produtoDTO, Integer statusCode, String mensagem, String token){
        String productId = given()
                .header("authorization", token)
                .body(
                        "{\n" +
                                "  \"nome\": \""+produtoDTO.getNome()+"\",\n" +
                                "  \"preco\": "+produtoDTO.getPreco()+",\n" +
                                "  \"descricao\": \""+produtoDTO.getDescricao()+"\",\n" +
                                "  \"quantidade\": "+produtoDTO.getQuantidade()+"\n" +
                                "}")
                .contentType(ContentType.JSON)
        .when()
                .post(Environment.localhost + Endpoint.produtos)
        .then()
                .statusCode(statusCode)
                .body ("message", is(mensagem))
                .extract().path("_id");

        return productId;
    }

    public void verificarEstoque (String prodId, Integer quantidadeEsperada) {
        given()
                .pathParam("_id", prodId)
        .when()
                .get(Environment.localhost + Endpoint.produtosId)
        .then()
                .body("quantidade", is(quantidadeEsperada));

    }
}
