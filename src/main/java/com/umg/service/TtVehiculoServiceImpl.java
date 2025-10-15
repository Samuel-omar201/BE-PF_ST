package com.umg.service;

import com.umg.data.bo.TtVehiculo;
import com.umg.data.repository.TtClienteRepository;
import com.umg.data.repository.TtVehiculoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TtVehiculoServiceImpl implements TtVehiculoService {

    private final TtVehiculoRepository repository;
    private final TtClienteRepository clienteRepository;

    public TtVehiculoServiceImpl(TtVehiculoRepository repository, TtClienteRepository clienteRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<TtVehiculo> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<TtVehiculo> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public TtVehiculo save(TtVehiculo vehiculo) {
        // ✅ Si viene el ID del cliente, buscar y establecer la relación
        if (vehiculo.getTtClienteIdCliente() != null) {
            clienteRepository.findById(vehiculo.getTtClienteIdCliente())
                    .ifPresent(vehiculo::setCliente);
        }
        if (vehiculo.getFechaRegistro() == null) {
            vehiculo.setFechaRegistro(LocalDateTime.now());
        }
        if (vehiculo.getEstadoRegistro() == null) {
            vehiculo.setEstadoRegistro("1");
        }
        return repository.save(vehiculo);
    }

    public TtVehiculo update(TtVehiculo vehiculo) {
        // ✅ Si viene el ID del cliente, buscar y establecer la relación
        if (vehiculo.getTtClienteIdCliente() != null) {
            clienteRepository.findById(vehiculo.getTtClienteIdCliente())
                    .ifPresent(vehiculo::setCliente);
        }

        return repository.save(vehiculo);
    }

    @Override
    public TtVehiculo deleteById(Integer id) {
        Optional<TtVehiculo> entity = repository.findById(id);
        if (entity.isPresent()) {
            repository.deleteById(id);
            return entity.get();
        }
        return null;
    }

    // ✅ Eliminación lógica: cambia el estado a '0'
    @Transactional
    @Override
    public TtVehiculo deleteLogically(Integer id) {
        Optional<TtVehiculo> vehiculoOpt = repository.findById(id);
        if (vehiculoOpt.isPresent()) {
            TtVehiculo vehiculo = vehiculoOpt.get();
            vehiculo.setEstadoRegistro("0");
            return repository.save(vehiculo);
        }
        return null;
    }

    @Transactional
    @Override
    public List<TtVehiculo> findAllWithCliente() {
        return repository.findAllWithCliente();
    }

}
