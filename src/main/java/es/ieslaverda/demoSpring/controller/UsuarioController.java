package es.ieslaverda.demoSpring.controller;

import es.ieslaverda.demoSpring.repository.model.Usuario;
import es.ieslaverda.demoSpring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
//    public UsuarioController(UsuarioService usuarioService){
//        this.usuarioService = usuarioService;
//    }

    @GetMapping("/usuarios/{id}")
    public Usuario getById(@PathVariable("id") int id){
        return usuarioService.getUsuarioById(id);
    }

    @PostMapping("/usuarios/")
    public ResponseEntity<?> addUsuario(@RequestBody Usuario usuario){
        Usuario u = usuarioService.addUsuario(usuario);
        if (u==null)
            return new ResponseEntity<>("No se pudo insertar", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(u,HttpStatus.OK);
    }

    @PutMapping("/usuarios/")
    public Usuario updateUsuario(@RequestBody Usuario usuario){
        return usuarioService.updateUsuario(usuario);
    }

    @DeleteMapping("usuarios/{id}")
    public boolean deleteUsuario(@PathVariable("id") int id){
        return usuarioService.deleteUsuario(id);
    }

    @GetMapping("/usuarios/")
    public List<Usuario> getAllUsuarios(){
        return usuarioService.getAllUsuarios();
    }


}
