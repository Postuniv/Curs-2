package com.ubb.postuniv;

import com.ubb.postuniv.domain.Prajitura;
import com.ubb.postuniv.domain.Tranzactie;
import com.ubb.postuniv.domain.TranzactieValidator;
import com.ubb.postuniv.repository.IRepository;
import com.ubb.postuniv.repository.InMemoryRepository;
import com.ubb.postuniv.service.TranzactieService;
import com.ubb.postuniv.userInterface.Console;

public class Main {

    public static void main(String[] args) {
        IRepository<Prajitura> prajituraRepository = new InMemoryRepository<>();
        IRepository<Tranzactie> tranzactieRepository = new InMemoryRepository<>();
        TranzactieValidator tranzactieValidator = new TranzactieValidator();

        TranzactieService tranzactieService = new TranzactieService(
                tranzactieRepository,
                prajituraRepository,
                tranzactieValidator);

        prajituraRepository.create(new Prajitura("1", "ecler", "zahar, frisca", 200, 20, false));
        prajituraRepository.create(new Prajitura("2", "inghetata", "zahar, vanilie, ciocolata", 240, 26, false));
        prajituraRepository.create(new Prajitura("3", "amandina", "ciocolata", 250, 21, true));

        tranzactieService.addTranzactie("1", "2", 342, "fg gdf ds", "f523gsd", 24);
        tranzactieService.addTranzactie("2", "1", 352, "fg gdfds", "fg523s3d", 12);
        tranzactieService.addTranzactie("3", "2", 34253, "fg gfdds", "fg12sd", 23);
        tranzactieService.addTranzactie("4", "2", 3542, "fgds fgddgf", "fgs 4 d", 11);
        tranzactieService.addTranzactie("5", "3", 3422, "f gfgds", "  234 23", 12);
        tranzactieService.addTranzactie("6", "1", 378842, "fg gfdds", " 324 32", 5);
        tranzactieService.addTranzactie("7", "2", 342352, "fgds dfg", "21132", 3);
        tranzactieService.addTranzactie("8", "3", 343462, "gdf dfgfgds", "32 2", 9);

        Console console = new Console(tranzactieService);
        console.runConsole();
    }
}
