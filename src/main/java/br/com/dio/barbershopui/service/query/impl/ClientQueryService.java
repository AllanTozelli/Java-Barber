package br.com.dio.barbershopui.service.query.impl;

import br.com.dio.barbershopui.entity.ClientEntity;
import br.com.dio.barbershopui.exception.EmailInUseException;
import br.com.dio.barbershopui.exception.NotFoundException;
import br.com.dio.barbershopui.exception.PhoneInUseException;
import br.com.dio.barbershopui.repository.IClientRepository;
import br.com.dio.barbershopui.service.IClientService;
import br.com.dio.barbershopui.service.query.IClientQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ClientQueryService implements IClientQueryService {

    private IClientRepository repository;

    @Override
    public ClientEntity findById(long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Não foi encontrado o cliente de id" + id));
    }

    @Override
    public List<ClientEntity> list() {
        return repository.findAll();
    }

    @Override
    public void verifyPhone(String phone) {
        if (repository.existsByPhone(phone)) {
            var message = "O telefone " + phone + " Já está cadastrado";
            throw new PhoneInUseException(message);
        }
    }

    @Override
    public void verifyPhone(long id, String phone) {
        var optional = repository.findByPhone(phone);

        if (optional.isPresent() && !Objects.equals(optional.get().getPhone(), phone)) {
            var message = "O telefone " + phone + " Já está cadastrado em outro cliente";
            throw new PhoneInUseException(message);

        }
    }


    @Override
    public void verifyEmail(final String email) {

    }


    @Override
    public void verifyEmail(long id, String email) {
        var optional = repository.findByPhone(email);

        if (optional.isPresent() && !Objects.equals(optional.get().getEmail(), email)) {
            var message = "O email " + email + " Já está cadastrado em outro cliente";
            throw new EmailInUseException(message);
        }
    }
}
