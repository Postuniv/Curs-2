package com.ubb.postuniv;

import com.ubb.postuniv.domain.Prajitura;
import com.ubb.postuniv.domain.Tranzactie;
import com.ubb.postuniv.domain.TranzactieValidator;
import com.ubb.postuniv.repository.IRepository;
import com.ubb.postuniv.repository.IUpsertRepository;
import com.ubb.postuniv.repository.InMemoryRepository;
import com.ubb.postuniv.repository.InMemoryUpsertRepository;
import com.ubb.postuniv.service.PrajituraService;
import com.ubb.postuniv.service.TranzactieService;
import com.ubb.postuniv.service.UpsertRepositoryForClassicalRepositoryAdapter;
import com.ubb.postuniv.userInterface.Console;

public class Main {

    public static void main(String[] args) {
        IUpsertRepository<Prajitura> prajituraRepository = new InMemoryUpsertRepository<>();
        IUpsertRepository<Tranzactie> tranzactieRepository = new InMemoryUpsertRepository<>();
        TranzactieValidator tranzactieValidator = new TranzactieValidator();

        UpsertRepositoryForClassicalRepositoryAdapter<Prajitura> prajituraRepositoryAdapter = new UpsertRepositoryForClassicalRepositoryAdapter<>(prajituraRepository);
        PrajituraService prajituraService = new PrajituraService(prajituraRepositoryAdapter);

        UpsertRepositoryForClassicalRepositoryAdapter<Tranzactie> tranzactieRepositoryAdapter = new UpsertRepositoryForClassicalRepositoryAdapter<>(tranzactieRepository);
        TranzactieService tranzactieService = new TranzactieService(
                tranzactieRepositoryAdapter,
                prajituraRepositoryAdapter,
                tranzactieValidator);

        prajituraRepositoryAdapter.create(new Prajitura("1", "ecler", "zahar, frisca", 200, 20, false));
        prajituraRepositoryAdapter.create(new Prajitura("2", "inghetata", "zahar, vanilie, ciocolata", 240, 26, false));
        prajituraRepositoryAdapter.create(new Prajitura("3", "amandina", "ciocolata", 250, 21, true));

        tranzactieService.addTranzactie("1", "2", 342, "24.05.2020", "f523gsd", 24);
        tranzactieService.addTranzactie("2", "1", 352, "24.05.2020", "fg523s3d", 12);
        tranzactieService.addTranzactie("3", "2", 34253, "24.05.2020", "fg12sd", 23);
        tranzactieService.addTranzactie("4", "2", 3542, "24.05.2020", "fgs 4 d", 11);
        tranzactieService.addTranzactie("5", "3", 3422, "24.05.2020", "  234 23", 12);
        tranzactieService.addTranzactie("6", "1", 378842, "24.05.2020", " 324 32", 5);
        tranzactieService.addTranzactie("7", "2", 342352, "24.05.2020", "21132", 3);
        tranzactieService.addTranzactie("8", "3", 343462, "24.05.2020", "32 2", 9);

        Console console = new Console(prajituraService, tranzactieService);
        console.runConsole();
    }
}
