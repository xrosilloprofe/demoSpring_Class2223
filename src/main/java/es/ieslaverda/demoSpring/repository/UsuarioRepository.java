package es.ieslaverda.demoSpring.repository;

import es.ieslaverda.demoSpring.repository.model.MyDataSource;
import es.ieslaverda.demoSpring.repository.model.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        Usuario u = getUsuarioById(usuario.getId());
        if( u != null) {
            u.setNombre(usuario.getNombre());
            u.setApellidos(usuario.getApellidos());
        }
        return u;
    }

    @Override
    public boolean deleteUsuario(int id) {
        Usuario usuario = getUsuarioById(id);
        if (usuario==null)
            return false;
        usuarios.remove(usuario);
        return true;
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
        return usuarios;
    }

    public List<Usuario> getAllDBUsuarios() throws SQLException {
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
