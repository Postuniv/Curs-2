package com.ubb.postuniv.userInterface;

import com.ubb.postuniv.service.TranzactieService;

import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Console {

    private TranzactieService tranzactieService;
    private Scanner scanner = new Scanner(System.in);

    public Console(TranzactieService tranzactieService) {
        this.tranzactieService = tranzactieService;
    }

    private void showOptions() {
        System.out.println("Adaugare tranzactie: addTr <id>,<idPrajitura>,<cardClient:int>,<data>,<ora>,<numarBucati:int>");
        System.out.println("Afisare prajituri ordonate dupa numarul de tranzactii: prajOrdTranz");
        System.out.println("Afisarea tuturor tranzactiilor: showTranz");
        System.out.println("Exit: exit");
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
                case "prajOrdTranz":
                    this.showAll(this.tranzactieService.getPrajituriOrdonateDescDupaNrTranzactii());
                    return true;
                case "showTranz":
                    this.showAll(this.tranzactieService.getAll());
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

    private void showAll(List<?> entities) {
        for (Object obj: entities) {
            System.out.println(obj);
        }
    }
}
