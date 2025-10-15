package com.umg.service;

import com.umg.data.bo.TtCliente;
import com.umg.data.bo.TtTecnico;
import com.umg.data.bo.TtUsuario;
import com.umg.data.repository.TtClienteRepository;
import com.umg.data.repository.TtTecnicoRepository;
import com.umg.data.repository.TtUsuarioRepository;
import com.umg.dto.CrearUsuarioDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TtUsuarioServiceImpl implements TtUsuarioService {

    private final TtUsuarioRepository repository;
    private final TtTecnicoRepository tecnicoRepository;

    private final TtClienteRepository clienteRepository;

    public TtUsuarioServiceImpl(TtUsuarioRepository repository, TtTecnicoRepository tecnicoRepository, TtClienteRepository clienteRepository) {
        this.repository = repository;
        this.tecnicoRepository = tecnicoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<TtUsuario> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<TtUsuario> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public TtUsuario update(TtUsuario ttUsuario) {
        return repository.save(ttUsuario);
    }

    @Override
    public TtUsuario deleteById(Integer id) {
        Optional<TtUsuario> entity = repository.findById(id);
        if (entity.isPresent()) {
            repository.deleteById(id);
            return entity.get();
        }
        return null;
    }

    /**
     * Crea un nuevo usuario (Cliente o Técnico)
     */
    @Transactional
    @Override
    public TtUsuario crearUsuario(CrearUsuarioDTO dto) {

        // Validar que el correo no esté registrado
        if (repository.existsByCorreoPrincipal(dto.getCorreoPrincipal())) {
            throw new RuntimeException("El correo electrónico ya está registrado");
        }

        // Validar que el nombre de usuario no esté registrado
        if (repository.existsByNombreUsuario(dto.getNombreUsuario())) {
            throw new RuntimeException("El nombre de usuario ya está registrado");
        }

        TtUsuario usuario = new TtUsuario();
        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setCorreoPrincipal(dto.getCorreoPrincipal());
        usuario.setCorreoSecundario(dto.getCorreoSecundario());
        usuario.setContraseña(dto.getContraseña()); // TODO: Encriptar en producción
        usuario.setFechaRegistro(LocalDateTime.now());
        usuario.setEstadoRegistro("1");

        // Crear según el tipo de usuario
        if ("cliente".equalsIgnoreCase(dto.getTipoUsuario())) {
            // Crear Cliente
            TtCliente cliente = new TtCliente();
            cliente.setPrimerNombre(dto.getClientePrimerNombre());
            cliente.setSegundoNombre(dto.getClienteSegundoNombre());
            cliente.setPrimerApellido(dto.getClientePrimerApellido());
            cliente.setSegundoApellido(dto.getClienteSegundoApellido());
            cliente.setFechaNacimiento(dto.getClienteFechaNacimiento());
            cliente.setTelefono(dto.getClienteTelefono());
            cliente.setDireccion(dto.getClienteDireccion());
            cliente.setDpi(dto.getClienteDpi());
            cliente.setFechaRegistro(LocalDateTime.now());
            cliente.setEstadoRegistro("1");

            TtCliente clienteGuardado = clienteRepository.save(cliente);
            usuario.setCliente(clienteGuardado);

        } else if ("tecnico".equalsIgnoreCase(dto.getTipoUsuario())) {
            // Crear Técnico
            TtTecnico tecnico = new TtTecnico();
            tecnico.setPrimerNombre(dto.getTecnicoPrimerNombre());
            tecnico.setSegundoNombre(dto.getTecnicoSegundoNombre());
            tecnico.setPrimerApellido(dto.getTecnicoPrimerApellido());
            tecnico.setSegundoApellido(dto.getTecnicoSegundoApellido());
            tecnico.setCarnetEmpleado(dto.getTecnicoCarnetEmpleado());
            tecnico.setTelefono(dto.getTecnicoTelefono());
            tecnico.setDpi(dto.getTecnicoDpi());
            tecnico.setFechaRegistro(LocalDateTime.now());
            tecnico.setEstadoRegistro("1");

            TtTecnico tecnicoGuardado = tecnicoRepository.save(tecnico);
            usuario.setTecnico(tecnicoGuardado);

        } else {
            throw new RuntimeException("Tipo de usuario inválido. Debe ser 'cliente' o 'tecnico'");
        }

        return repository.save(usuario);
    }

}
