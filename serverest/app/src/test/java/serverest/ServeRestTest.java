package serverest;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

public class ServeRestTest {

    @Test
    public void listUsers (){
        when()
                .get("http://localhost:3000/usuarios")
        .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void addUser() {
        given().body(
                "{\n" +
                "  \"nome\": \"Fulano da Silva\",\n" +
                "  \"email\": \"beltrano1@qa.com.br\",\n" +
                "  \"password\": \"teste\",\n" +
                "  \"administrador\": \"true\"\n" +
                "}")
                .contentType(ContentType.JSON)
        .when()
                .post("http://localhost:3000/usuarios")
        .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("message", is("Cadastro realizado com sucesso"));
    }

    @Test
    public void validateDupUser() {
        given().body(
                        "{\n" +
                                "  \"nome\": \"Fulano da Silva\",\n" +
                                "  \"email\": \"beltrano@qa.com.br\",\n" +
                                "  \"password\": \"teste\",\n" +
                                "  \"administrador\": \"true\"\n" +
                                "}")
                .contentType(ContentType.JSON)
        .when()
                .post("http://localhost:3000/usuarios")
        .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", is("Este email já está sendo usado"));
    }
}
