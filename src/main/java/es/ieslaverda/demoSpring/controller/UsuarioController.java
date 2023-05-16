package es.ieslaverda.demoSpring.controller;

import es.ieslaverda.demoSpring.repository.model.Usuario;
import es.ieslaverda.demoSpring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Usuario addUsuario(@RequestBody Usuario usuario){
        return usuarioService.addUsuario(usuario);
    }

    @PutMapping("/usuarios/")
    public Usuario updateUsuario(@RequestBody Usuario usuario){
        return usuarioService.updateUsuario(usuario);
    }

    @DeleteMapping("usuarios/{id}")
    public boolean deleteUsuario(@PathVariable("id") int id){
        return usuarioService.deleteUsuario(id);
    }


}
