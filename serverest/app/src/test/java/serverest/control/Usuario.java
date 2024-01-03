package serverest.control;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import serverest.model.UsuarioDTO;
import serverest.util.Endpoint;
import serverest.util.Environment;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

public class Usuario {

    public void listarUsuario(){
        when()
                .get(Environment.localhost + Endpoint.usuarios)
        .then()
                .statusCode(HttpStatus.SC_OK);
    }

    public String cadastrarUsuario (UsuarioDTO usuario, Integer statusCode, String mensagem){
        String userId = given()
                .body(
                        "{\n" +
                                "  \"nome\": \"" + usuario.getNome() + "\",\n" +
                                "  \"email\": \"" + usuario.getEmail() + "\",\n" +
                                "  \"password\": \"" + usuario.getPassword() + "\",\n" +
                                "  \"administrador\": \"" +usuario.getAdministrador()+"\"\n" +
                                "}")
                .contentType(ContentType.JSON)
        .when()
                .post(Environment.localhost + Endpoint.usuarios)
        .then()
                .statusCode(statusCode)
                .body ("message", is(mensagem))
                .extract().path("_id");
        return userId;
    }

    public String autenticarUsuario (UsuarioDTO usuarioDTO, Integer statusCode, String mensagem){
        String token = given()
                .body("{\n" +
                        "  \"email\": \""+ usuarioDTO.getEmail() +"\",\n" +
                        "  \"password\": \""+ usuarioDTO.getPassword() +"\"\n" +
                        "}")
                .contentType(ContentType.JSON)
        .when()
                .post(Environment.localhost + Endpoint.login)
        .then()
                .statusCode(statusCode)
                .body ("message", is(mensagem))
                .extract().path("authorization");
        return token;
    }

    public void excluirUsuario (String userId, Integer statusCode, String mensagem) {
        given()
                .pathParam("_id", userId)
        .when()
                .delete(Environment.localhost + Endpoint.usuariosId)
        .then()
                .log().all()
                .statusCode(statusCode)
                .body("message", is(mensagem));
    }
}
