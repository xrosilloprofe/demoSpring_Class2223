package es.ieslaverda.demoSpring.repository;

import es.ieslaverda.demoSpring.repository.model.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface IUsuarioRepository {

    Usuario addUsuario(Usuario usuario) throws SQLException;
    Usuario updateUsuario(Usuario usuario) throws SQLException;
    boolean deleteUsuario(int id) throws SQLException;
    Usuario getUsuarioById(int id) throws SQLException;
    List<Usuario> getAllUsuarios() throws SQLException;
}
