package serverest.contractTest;

import com.github.javafaker.Faker;
import org.apache.http.HttpStatus;
import org.junit.Test;
import serverest.control.Usuario;
import serverest.model.UsuarioDTO;
import serverest.util.Mensagens;

public class UsuariosContractTest {

    private Usuario usuario = new Usuario();

    Faker faker = new Faker();

    @Test
    public void testListarUsuarios (){
        usuario.listarUsuario();
    }

    @Test
    public void testCadastrarUsuariosComSucesso (){
        UsuarioDTO usuarioDTO = new UsuarioDTO("true");
        usuario.cadastrarUsuario(usuarioDTO, HttpStatus.SC_CREATED, Mensagens.cadastroSucesso);
    }

    @Test
    public void testCadastrarUsuariosComEmailExistente (){
        UsuarioDTO usuarioDTO = new UsuarioDTO("true");
        usuario.cadastrarUsuario(usuarioDTO, HttpStatus.SC_CREATED, Mensagens.cadastroSucesso);
        usuario.cadastrarUsuario(usuarioDTO, HttpStatus.SC_BAD_REQUEST, Mensagens.emailJaCadastrado);
    }
}
