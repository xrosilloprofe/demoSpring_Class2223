package es.ieslaverda.demoSpring.repository;

import es.ieslaverda.demoSpring.repository.IUsuarioRepository;
import es.ieslaverda.demoSpring.repository.model.MyDataSource;
import es.ieslaverda.demoSpring.repository.model.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioDBRepository implements IUsuarioRepository {

    @Override
    public Usuario addUsuario(Usuario usuario) throws SQLException {

        String query = "INSERT INTO usuarios VALUES(?,?,?)";
        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, usuario.getId());
            preparedStatement.setString(2, usuario.getNombre());
            preparedStatement.setString(3, usuario.getApellidos());
            preparedStatement.executeUpdate();
            return usuario;
        }
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) throws SQLException {
        String query = "UPDATE usuarios set nombre = ?, apellidos = ? where id = ?";

        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getApellidos());
            preparedStatement.setInt(3,usuario.getId());

            preparedStatement.executeUpdate();
            return usuario;
        }
    }

    @Override
    public boolean deleteUsuario(int id) throws SQLException {
        boolean deletion = false;
        String sql = " {? = call borrarUsuario(?)}";

        try (Connection con = MyDataSource.getMySQLDataSource().getConnection();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(2, id);
            cs.execute();
            int borrados = cs.getInt(1);
            System.out.println(borrados);
            if (borrados == 1)
                deletion = true;
        }
        return deletion;
    }

    @Override
    public Usuario getUsuarioById(int id) throws SQLException {
        Usuario usuario;
        String sql = " {call obtenUsuario(?)}";

        try (Connection con = MyDataSource.getMySQLDataSource().getConnection();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            rs.next();
            usuario = Usuario.builder().id(rs.getInt(1)).nombre(rs.getString(2)).apellidos(rs.getString(3)).build();
        }
        return usuario;
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
