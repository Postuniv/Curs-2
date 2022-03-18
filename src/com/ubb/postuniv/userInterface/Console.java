package com.ubb.postuniv.userInterface;

import com.ubb.postuniv.service.PrajituraService;
import com.ubb.postuniv.service.TranzactieService;

import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Console {

    private TranzactieService tranzactieService;
    private PrajituraService prajituraService;
    private Scanner scanner = new Scanner(System.in);

    public Console(PrajituraService prajituraService, TranzactieService tranzactieService) {
        this.prajituraService = prajituraService;
        this.tranzactieService = tranzactieService;
    }

    private void showOptions() {

        System.out.println("""
            Adaugare prajitura: addPr <id>,<nume>,<ingrediente>,<calorii:float>,<pret:float>,<faraZahar:bool>
            Adaugare tranzactie: addTr <id>,<idPrajitura>,<cardClient:int>,<data>,<ora>,<numarBucati:int>
            Afisare prajituri ordonate dupa numarul de tranzactii: prajOrdTranz
            Afisarea tuturor prajiturilor: showPraji
            Afisarea tuturor tranzactiilor: showTranz
            Generare prajituri: genPraji <nr:int>
            Generare tranzactii: genTranz <nr:int>
            Exit: exit""");
    }

    public void runConsole() {
        boolean run = true;
        while (run) {
            this.showOptions();

            String option = scanner.nextLine();
            run = this.processCommand(option);
        }
    }

    private boolean processCommand(String command) {
        try {
            String[] arguments = command.split(" ");
            switch (arguments[0]) {
                case "addPr":
                    this.handleAddPrajitura(arguments[1].split(","));
                    return true;
                case "prajOrdTranz":
                    this.showAll(this.tranzactieService.getPrajituriOrdonateDescDupaNrTranzactii());
                    return true;
                case "showPraji":
                    this.showAll(this.prajituraService.getAll());
                    return true;
                case "showTranz":
                    this.showAll(this.tranzactieService.getAll());
                    return true;
                case "genPraji":
                    this.prajituraService.generateRandom(Integer.parseInt(arguments[1]));
                    return true;
                case "genTranz":
                    this.tranzactieService.generateRandom(Integer.parseInt(arguments[1]));
                    return true;
                case "exit":
                    return false;
                default:
                    System.out.println("Comanda invalida!");
                    return true;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return true;

    }

    private void handleAddPrajitura(String[] arguments) {
        try {
            this.prajituraService.addPrajitura(
                    arguments[0],
                    arguments[1],
                    arguments[2],
                    Float.parseFloat(arguments[3]),
                    Float.parseFloat(arguments[4]),
                    Boolean.parseBoolean(arguments[5]));
        } catch (RuntimeException rex) {
            System.out.println("Eroare: " + rex.getMessage());
        }
    }

    private void showAll(List<?> entities) {
        for (Object obj: entities) {
            System.out.println(obj);
        }
    }
}
