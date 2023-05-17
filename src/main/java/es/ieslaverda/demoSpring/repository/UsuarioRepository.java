package es.ieslaverda.demoSpring.repository;

import es.ieslaverda.demoSpring.repository.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository implements IUsuarioRepository{

    private List<Usuario> usuarios;

    public UsuarioRepository(){
        usuarios = new ArrayList<>();
        usuarios.add(Usuario.builder().id(1).nombre("Xavi").apellidos("Rosillo").build());
        usuarios.add(Usuario.builder().id(2).nombre("Blanca").apellidos("Romero").build());
    }

    @Override
    public Usuario addUsuario(Usuario usuario) {
        if (!usuarios.contains(usuario)) {
            usuarios.add(usuario);
            return usuario;
        }
        return null;
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) {
        return null;
    }

    @Override
    public boolean deleteUsuario(int id) {
        return false;
    }

    @Override
    public Usuario getUsuarioById(int id) {
        Optional<Usuario> usuarioOptional = usuarios.stream()
                .filter(usuario -> usuario.getId()==id)
                .findFirst();
        if (usuarioOptional.isPresent())
            return usuarioOptional.get();
        return null;
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return null;
    }
}
