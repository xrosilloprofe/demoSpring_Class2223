package es.ieslaverda.demoSpring.repository;

import es.ieslaverda.demoSpring.repository.IUsuarioRepository;
import es.ieslaverda.demoSpring.repository.model.MyDataSource;
import es.ieslaverda.demoSpring.repository.model.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioDBRepository implements IUsuarioRepository {

    @Override
    public Usuario addUsuario(Usuario usuario) throws SQLException {
        return null;
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) throws SQLException {
        return null;
    }

    @Override
    public boolean deleteUsuario(int id) throws SQLException {
        return false;
    }

    @Override
    public Usuario getUsuarioById(int id) throws SQLException {
        return null;
    }

    public List<Usuario> getAllUsuarios() throws SQLException {
        ArrayList<Usuario> usuariosDB = new ArrayList<>();
        String query = "SELECT * FROM usuarios";

        try(Connection connection = MyDataSource.getMySQLDataSource().getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query)){

            while(rs.next()){
                usuariosDB.add(Usuario.builder().id(rs.getInt(1)).nombre(rs.getString(2)).apellidos(rs.getString(3)).build());
            }
        }

        return usuariosDB;
    }

}
