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


            Cor cor = new Cor(0, "Marrom");
            Pelagem pelagem = new Pelagem(0, "Curta");
            Raca raca = new Raca(0, "Labrador");

            cor = corService.insert(cor);
            pelagem = pelagemService.insert(pelagem);
            raca = racaService.insert(raca);

            Cachorro cachorro = new Cachorro(0, "Rex", raca, pelagem, cor, 50.0, true, new Date());
            cachorro = cachorroService.insert(cachorro);

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
        }
    }
}
