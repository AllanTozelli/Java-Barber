package br.com.dio.barbershopui.service.query.impl;

import br.com.dio.barbershopui.entity.ScheduleEntity;
import br.com.dio.barbershopui.exception.NotFoundException;
import br.com.dio.barbershopui.exception.ScheduleInUseException;
import br.com.dio.barbershopui.repository.IScheduleRepository;
import br.com.dio.barbershopui.service.query.IScheduleQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class ScheduleQueryService implements IScheduleQueryService {

    private final IScheduleRepository repository;

    @Override
    public ScheduleEntity findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Agendamento não encontrado")
        );
    }

    @Override
    public List<ScheduleEntity> findInMonth(OffsetDateTime startAt, OffsetDateTime endAt) {
        return repository.findByStartAtGreaterThanEqualAndEndAtLessThanEqualOrderByStartAtAscEndAtAsc(startAt, endAt);
    }

    @Override
    public void verifyIfScheduleExists(OffsetDateTime startAt, OffsetDateTime endAt) {

        if(repository.existsByStartAtAndEndAt(startAt, endAt)) {
            var message = "Já existe um cliente agendado no horario solicitado";

            throw new ScheduleInUseException(message);
        }

    }
}
