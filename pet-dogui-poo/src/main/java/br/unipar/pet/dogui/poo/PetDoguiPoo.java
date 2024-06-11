package br.unipar.pet.dogui.poo;

import br.unipar.pet.dogui.poo.domain.Cachorro;
import br.unipar.pet.dogui.poo.domain.Cor;
import br.unipar.pet.dogui.poo.domain.Pelagem;
import br.unipar.pet.dogui.poo.domain.Raca;
import br.unipar.pet.dogui.poo.exceptions.NegocioException;
import br.unipar.pet.dogui.poo.services.CachorroService;
import br.unipar.pet.dogui.poo.services.CorService;
import br.unipar.pet.dogui.poo.services.PelagemService;
import br.unipar.pet.dogui.poo.services.RacaService;

import java.sql.SQLException;
import java.util.Date;

public class PetDoguiPoo {

    public static void main(String[] args) {
        try {
            CorService corService = new CorService();
            PelagemService pelagemService = new PelagemService();
            RacaService racaService = new RacaService();
            CachorroService cachorroService = new CachorroService();

            // Inserindo dados de Cor
            Cor cor1 = new Cor(0, "Marrom");
            Cor cor2 = new Cor(0, "Preto");

            cor1 = corService.insert(cor1);
            cor2 = corService.insert(cor2);

            // Inserindo dados de Pelagem
            Pelagem pelagem1 = new Pelagem(0, "Curta");
            Pelagem pelagem2 = new Pelagem(0, "Longa");

            pelagem1 = pelagemService.insert(pelagem1);
            pelagem2 = pelagemService.insert(pelagem2);

            // Inserindo dados de Raca
            Raca raca1 = new Raca(0, "Labrador");
            Raca raca2 = new Raca(0, "Poodle");

            raca1 = racaService.insert(raca1);
            raca2 = racaService.insert(raca2);

            // Inserindo dados de Cachorro
            Cachorro cachorro1 = new Cachorro(0, "Rex", raca1, pelagem1, cor1, 10.5, true, new Date());
            Cachorro cachorro2 = new Cachorro(0, "Bella", raca2, pelagem2, cor2, 8.20, false, new Date());

            cachorro1 = cachorroService.insert(cachorro1);
            cachorro2 = cachorroService.insert(cachorro2);

            // Listando dados inseridos
            System.out.println("Cores:");
            for (Cor c : corService.findAll()) {
                System.out.println(c);
            }

            System.out.println("Pelagens:");
            for (Pelagem p : pelagemService.findAll()) {
                System.out.println(p);
            }

            System.out.println("Raças:");
            for (Raca r : racaService.findAll()) {
                System.out.println(r);
            }

            System.out.println("Cachorros:");
            for (Cachorro c : cachorroService.findAll()) {
                System.out.println(c);
            }
        } catch (SQLException | NegocioException ex) {
            ex.printStackTrace();
        }
    }
}
