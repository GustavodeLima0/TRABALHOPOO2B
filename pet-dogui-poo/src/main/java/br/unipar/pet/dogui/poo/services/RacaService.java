package br.unipar.pet.dogui.poo.services;

import br.unipar.pet.dogui.poo.domain.Raca;
import br.unipar.pet.dogui.poo.exceptions.NegocioException;
import br.unipar.pet.dogui.poo.respositories.RacaRepository;
import java.sql.SQLException;
import java.util.ArrayList;

public class RacaService {

    public Raca insert(Raca raca) throws SQLException, NegocioException {
        validate(raca);
        RacaRepository racaRepository = new RacaRepository();
        return racaRepository.insert(raca);
    }

    public Raca update(Raca raca) throws SQLException, NegocioException {
        validate(raca);
        validateUpdate(raca);
        RacaRepository racaRepository = new RacaRepository();
        return racaRepository.update(raca);
    }

    public void delete(int id) throws SQLException {
        RacaRepository racaRepository = new RacaRepository();
        racaRepository.delete(id);
    }

    public Raca findById(int id) throws SQLException {
        RacaRepository racaRepository = new RacaRepository();
        return racaRepository.findById(id);
    }

    public ArrayList<Raca> findAll() throws SQLException {
        RacaRepository racaRepository = new RacaRepository();
        return racaRepository.findAll();
    }

    private void validate(Raca raca) throws NegocioException {
        if (raca.getDescricao() == null || raca.getDescricao().isBlank()) {
            throw new NegocioException("A descrição da Raça deve ser informada.");
        }
        if (raca.getDescricao().length() <= 4) {
            throw new NegocioException("A descrição da Raça deve possuir mais de 4 caracteres.");
        }
        if (raca.getDescricao().length() > 60) {
            throw new NegocioException("A descrição da Raça não deve possuir mais do que 60 caracteres.");
        }
    }

    private void validateUpdate(Raca raca) throws NegocioException {
        if (raca.getId() == 0) {
            throw new NegocioException("Informe um código válido para atualização da raça.");
        }
    }
}
