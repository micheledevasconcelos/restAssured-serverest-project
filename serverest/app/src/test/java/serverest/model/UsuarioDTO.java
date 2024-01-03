package serverest.model;

import com.github.javafaker.Faker;

public class UsuarioDTO {
    private String nome;
    private String email;
    private String password;
    private String administrador;

    public UsuarioDTO(String admin) {
        Faker faker = new Faker();
        this.nome = faker.name().firstName();
        this.email = nome + "@contracttest.com";
        this.password = "password";
        this.administrador = admin;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdministrador() {
        return administrador;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }
}
