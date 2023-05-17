package es.ieslaverda.demoSpring.controller;

import es.ieslaverda.demoSpring.repository.model.Usuario;
import es.ieslaverda.demoSpring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
//    public UsuarioController(UsuarioService usuarioService){
//        this.usuarioService = usuarioService;
//    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        Usuario u = usuarioService.getUsuarioById(id);
        if (u==null)
            return new ResponseEntity<>("El usuario no existe", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(u,HttpStatus.OK);
    }

    @PostMapping("/usuarios/")
    public ResponseEntity<?> addUsuario(@RequestBody Usuario usuario){
        Usuario u = usuarioService.addUsuario(usuario);
        if (u==null)
            return new ResponseEntity<>("No se pudo insertar", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(u,HttpStatus.OK);
    }

    @PutMapping("/usuarios/")
    public ResponseEntity<?> updateUsuario(@RequestBody Usuario usuario){
        Usuario u = usuarioService.updateUsuario(usuario);
        if (u==null)
            return new ResponseEntity<>("No se pudo actualizar", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(u,HttpStatus.OK);
    }

    @DeleteMapping("usuarios/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable("id") int id){

        if(usuarioService.deleteUsuario(id))
            return new ResponseEntity<>("Borrado",HttpStatus.OK);
        else
            return new ResponseEntity<>("No se pudo borrar", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/usuarios/")
    public List<Usuario> getAllUsuarios(){
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/usuarios/db")
    public ResponseEntity<?> getAllDBUsuarios(){
        try {
            return new ResponseEntity<>(usuarioService.getAllDBUsuarios(),HttpStatus.OK);
        } catch(SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
