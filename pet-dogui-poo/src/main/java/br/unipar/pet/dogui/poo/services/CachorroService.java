package br.unipar.pet.dogui.poo.services;


import br.unipar.pet.dogui.poo.domain.Cachorro;
import br.unipar.pet.dogui.poo.exceptions.NegocioException;
import br.unipar.pet.dogui.poo.repositories.CachorroRepository;
import java.sql.SQLException;
import java.util.ArrayList;

public class CachorroService {

    public Cachorro insert(Cachorro cachorro) throws SQLException, NegocioException {
        validate(cachorro);
        CachorroRepository cachorroRepository = new CachorroRepository();
        return cachorroRepository.insert(cachorro);
    }

    public Cachorro update(Cachorro cachorro) throws SQLException, NegocioException {
        validate(cachorro);
        validateUpdate(cachorro);
        CachorroRepository cachorroRepository = new CachorroRepository();
        return cachorroRepository.update(cachorro);
    }

    public void delete(int id) throws SQLException {
        CachorroRepository cachorroRepository = new CachorroRepository();
        cachorroRepository.delete(id);
    }

    public Cachorro findById(int id) throws SQLException {
        CachorroRepository cachorroRepository = new CachorroRepository();
        return cachorroRepository.findById(id);
    }

    public ArrayList<Cachorro> findAll() throws SQLException {
        CachorroRepository cachorroRepository = new CachorroRepository();
        return cachorroRepository.findAll();
    }

    private void validate(Cachorro cachorro) throws NegocioException {
        if (cachorro.getNome() == null || cachorro.getNome().isBlank()) {
            throw new NegocioException("O nome do Cachorro deve ser informado.");
        }
        if (cachorro.getNome().length() > 60) {
            throw new NegocioException("O nome do Cachorro não deve possuir mais do que 60 caracteres.");
        }
        if (cachorro.getTamanho() == null || cachorro.getTamanho() <= 0) {
            throw new NegocioException("O tamanho do Cachorro deve ser informado e deve ser maior que zero.");
        }
        if (cachorro.getDtNascimento() == null) {
            throw new NegocioException("A data de nascimento do Cachorro deve ser informada.");
        }
        if (cachorro.getRaca() == null || cachorro.getRaca().getId() == 0) {
            throw new NegocioException("A raça do Cachorro deve ser informada.");
        }
        if (cachorro.getPelagem() == null || cachorro.getPelagem().getId() == 0) {
            throw new NegocioException("A pelagem do Cachorro deve ser informada.");
        }
        if (cachorro.getCor() == null || cachorro.getCor().getId() == 0) {
            throw new NegocioException("A cor do Cachorro deve ser informada.");
        }
    }

    private void validateUpdate(Cachorro cachorro) throws NegocioException {
        if (cachorro.getId() == 0) {
            throw new NegocioException("Informe um código válido para atualização do cachorro.");
        }
    }
}
