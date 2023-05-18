package es.ieslaverda.demoSpring.service;

import es.ieslaverda.demoSpring.repository.model.Usuario;
import es.ieslaverda.demoSpring.repository.UsuarioDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioDBService {

    @Autowired
    public UsuarioDBRepository repository;

    public List<Usuario> getAllUsuarios() throws SQLException {
        return repository.getAllUsuarios();
    }
}
