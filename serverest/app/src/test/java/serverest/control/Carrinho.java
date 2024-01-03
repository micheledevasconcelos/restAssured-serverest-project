package serverest.control;

import io.restassured.http.ContentType;
import org.checkerframework.checker.units.qual.C;
import serverest.util.Endpoint;
import serverest.util.Environment;
import serverest.util.Mensagens;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

public class Carrinho {

    public void cadastrarCarrinho(String token, String prodId, Integer quantidade, Integer statusCode, String mensagem){
        given()
                .header("Authorization", token)
                .body("{\n" +
                        "  \"produtos\": [\n" +
                        "    {\n" +
                        "      \"idProduto\": \""+prodId+"\",\n" +
                        "      \"quantidade\": "+quantidade+"\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}")
                .contentType(ContentType.JSON)
        .when()
                .post(Environment.localhost + Endpoint.carrinhos)
        .then()
                .statusCode(statusCode)
                .body("message", is(mensagem));
    }

    public void cancelarCompra (String token, Integer statusCode, String mensagem){
        given()
                .header("Authorization", token)
        .when()
                .delete(Environment.localhost + Endpoint.carrinhosCancelarCompra)
        .then()
                .statusCode(statusCode)
                .body("message", is(mensagem));
    }

    public void concluirCompra (String token, Integer statusCode, String mensagem){
        given()
                .header("Authorization", token)
        .when()
                .delete(Environment.localhost + Endpoint.carrinhosConcluirCompra)
        .then()
                .statusCode(statusCode)
                .body("message", is(mensagem));

    }
}
