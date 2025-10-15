package com.umg.web.controller;

import com.umg.data.bo.TtUsuario;
import com.umg.dto.CrearUsuarioDTO;
import com.umg.service.TtUsuarioService;
import com.umg.data.repository.TtUsuarioRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/service/Autex_M1/ttUsuario")
@CrossOrigin(origins = "*")
@Api(value = "Manejo de usuarios", protocols = "http")
public class TtUsuarioController {

    @Autowired
    private TtUsuarioRepository repository;

    @Autowired
    private TtUsuarioService service;

    @ApiOperation("Obtiene todos los usuarios")
    @GetMapping("/getAll")
    ResponseEntity<List<TtUsuario>> getAll() {
        List<TtUsuario> response = this.service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Agrega un nuevo usuario")
    @PostMapping("/save")
    ResponseEntity<TtUsuario> save(@RequestBody TtUsuario entity) {
        TtUsuario response = this.service.update(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Actualiza un usuario")
    @PutMapping("/update")
    ResponseEntity<TtUsuario> update(@RequestBody TtUsuario entity) {
        TtUsuario response = this.service.update(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Elimina un usuario")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("Crea un nuevo usuario (Cliente o Técnico)")
    @PostMapping("/crearUsuario")
    public ResponseEntity<Map<String, Object>> crearUsuario(@RequestBody CrearUsuarioDTO dto) {
        try {
            TtUsuario usuario = service.crearUsuario(dto);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Usuario creado exitosamente");
            response.put("idUsuario", usuario.getIdUsuario());
            response.put("nombreUsuario", usuario.getNombreUsuario());

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error interno del servidor");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
