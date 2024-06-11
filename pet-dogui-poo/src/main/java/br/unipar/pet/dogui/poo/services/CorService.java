package br.unipar.pet.dogui.poo.services;

import br.unipar.pet.dogui.poo.domain.Cor;
import br.unipar.pet.dogui.poo.exceptions.NegocioException;
import br.unipar.pet.dogui.poo.respositories.CorRepository;
import java.sql.SQLException;
import java.util.ArrayList;

public class CorService {

    public Cor insert(Cor cor) throws SQLException, NegocioException {
        validate(cor);
        CorRepository corRepository = new CorRepository();
        return corRepository.insert(cor);
    }

    public Cor update(Cor cor) throws SQLException, NegocioException {
        validate(cor);
        validateUpdate(cor);
        CorRepository corRepository = new CorRepository();
        return corRepository.update(cor);
    }

    public void delete(int id) throws SQLException {
        CorRepository corRepository = new CorRepository();
        corRepository.delete(id);
    }

    public Cor findById(int id) throws SQLException {
        CorRepository corRepository = new CorRepository();
        return corRepository.findById(id);
    }

    public ArrayList<Cor> findAll() throws SQLException {
        CorRepository corRepository = new CorRepository();
        return corRepository.findAll();
    }

    private void validate(Cor cor) throws NegocioException {
        if (cor.getDescricao() == null || cor.getDescricao().isBlank()) {
            throw new NegocioException("A descrição da Cor deve ser informada.");
        }
        if (cor.getDescricao().length() <= 4) {
            throw new NegocioException("A descrição da Cor deve possuir mais de 4 caracteres.");
        }
        if (cor.getDescricao().length() > 60) {
            throw new NegocioException("A descrição da Cor não deve possuir mais do que 60 caracteres.");
        }
    }

    private void validateUpdate(Cor cor) throws NegocioException {
        if (cor.getId() == 0) {
            throw new NegocioException("Informe um código válido para atualização da cor.");
        }
    }
}
