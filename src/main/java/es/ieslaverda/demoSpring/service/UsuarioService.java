package es.ieslaverda.demoSpring.service;

import es.ieslaverda.demoSpring.repository.UsuarioRepository;
import es.ieslaverda.demoSpring.repository.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario getUsuarioById(int id){
        return usuarioRepository.getUsuarioById(id);
    }

    public Usuario addUsuario(Usuario usuario){
        return usuarioRepository.addUsuario(usuario);
    }

    public Usuario updateUsuario(Usuario usuario){
        return usuarioRepository.updateUsuario(usuario);
    }

    public boolean deleteUsuario(int id){
        return usuarioRepository.deleteUsuario(id);
    }


    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.getAllUsuarios();
    }

}
