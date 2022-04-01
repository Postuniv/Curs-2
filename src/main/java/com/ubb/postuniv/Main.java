package com.ubb.postuniv;

import com.google.gson.Gson;
import com.ubb.postuniv.domain.Prajitura;
import com.ubb.postuniv.domain.Tranzactie;
import com.ubb.postuniv.domain.TranzactieValidator;
import com.ubb.postuniv.repository.*;
import com.ubb.postuniv.service.PrajituraService;
import com.ubb.postuniv.service.TranzactieService;
import com.ubb.postuniv.service.UpsertRepositoryForClassicalRepositoryAdapter;
import com.ubb.postuniv.userInterface.Console;

public class Main {

    public static void main(String[] args) {
//        IUpsertRepository<Prajitura> prajituraRepository = new InMemoryUpsertRepository<>();
//        IUpsertRepository<Tranzactie> tranzactieRepository = new InMemoryUpsertRepository<>();
        TranzactieValidator tranzactieValidator = new TranzactieValidator();

        IRepository<Prajitura> prajituraRepository = new JsonFileRepository<>("prajituri.txt", Prajitura.class);
        IRepository<Tranzactie> tranzactieRepository = new JsonFileRepository<>("tranzactii.txt", Tranzactie.class);

//        UpsertRepositoryForClassicalRepositoryAdapter<Prajitura> prajituraRepositoryAdapter = new UpsertRepositoryForClassicalRepositoryAdapter<>(prajituraRepository);
        PrajituraService prajituraService = new PrajituraService(prajituraRepository);

//        UpsertRepositoryForClassicalRepositoryAdapter<Tranzactie> tranzactieRepositoryAdapter = new UpsertRepositoryForClassicalRepositoryAdapter<>(tranzactieRepository);
        TranzactieService tranzactieService = new TranzactieService(
                tranzactieRepository,
                prajituraRepository,
                tranzactieValidator);


        Console console = new Console(prajituraService, tranzactieService);
        console.runConsole();
    }
}
