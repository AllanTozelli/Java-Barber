package br.com.dio.barbershopui.service.impl;

import br.com.dio.barbershopui.entity.ScheduleEntity;
import br.com.dio.barbershopui.repository.IScheduleRepository;
import br.com.dio.barbershopui.service.IScheduleService;
import br.com.dio.barbershopui.service.query.IScheduleQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@AllArgsConstructor
public class ScheduleService implements IScheduleService {

    private final IScheduleRepository repository;
    private final IScheduleQueryService queryService;


    @Override
    public ScheduleEntity save(ScheduleEntity entity) {
        queryService.verifyIfScheduleExists(entity.getStartAt(), entity.getEndAt());

        return repository.save(entity);

    }

    @Override
    public void delete(long id) {
        queryService.findById(id);
        repository.deleteById(id);

    }
}
