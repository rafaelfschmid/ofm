/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.exception;

import javafx.scene.control.Alert;

/**
 *
 * @author rafael
 */
public class AlertDialog {

    public static void SaveParameters() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Parametros não salvos");
        alert.setHeaderText("Por favor, salve os parâmetros antes de prosseguir.");

        alert.showAndWait();
    }

    public static void IncorrectTypeParameters(String ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Tipo incorreto");
        alert.setHeaderText("Por favor, verifique os parâmetros.");
        alert.setContentText(ex);

        alert.showAndWait();
    }

    public static void DeviceSelection() {
        // Nada selecionado.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nenhuma seleção");
        alert.setHeaderText("Nenhum dispositivo foi selecionado");
        alert.setContentText("Por favor, selecione um dispositivo.");

        alert.showAndWait();
    }

    public static boolean DeviceDeletion(String device) {
        // Nada selecionado.
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Exclusão de dispositivo");
        alert.setHeaderText("Deseja excluir o dispositivo: " + device + "?");

        alert.showAndWait();

        if (alert.getResult() == null) {
            return false;
        }

        return true;
    }

    public static void NothingToExport() {
        // Nada selecionado.
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Nada a ser exportado.");
        alert.setHeaderText("Os objetos estão vazios. Execute antes de exportar.");

        alert.showAndWait();
    }

    public static void ExportSuccess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Exportação.");
        alert.setHeaderText("Dados exportados com sucesso.");
        alert.setContentText("Arquivo 'DadosExportados.txt' gerado com sucesso.");

        alert.showAndWait();
    }

    public static void NothingToReference() {
        // Nada selecionado.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nada a ser referenciado.");
        alert.setHeaderText("Realize uma execucao antes de ter uma referencia!");

        alert.showAndWait();

    }

    public static void IncorrectField(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Campo Incorreto");
        alert.setHeaderText("Por favor, verifique o campo " + text + ".");

        alert.showAndWait();
    }

    public static void DeviceNotFound() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Device Not Found");
        alert.setHeaderText("Por favor, adicione um dispositivo antes de continuar.");

        alert.showAndWait();
    }

    public static void IncorrectRangeField(String text, int min, int max) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Campo Incorreto");
        alert.setHeaderText("O campo " + text + " deve estar entre " + min + " e " + max);

        alert.showAndWait();
    }

    public static void somethingGoingWrong(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Por favor, informe o administrador do software sobre o problema.");
        alert.setContentText(text);

        alert.showAndWait();
    }

    public static void timeOut(String ip) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Time Out");
        alert.setHeaderText("O endereço " + ip + " não pôde ser alcançado.");
        alert.setContentText("Corrija o IP, ou tente novamente mais tarde.");

        alert.showAndWait();
    }

    public static void exception(Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception");
        alert.setHeaderText(ex.getMessage());
        if (ex.getCause() != null) {
            alert.setContentText(ex.getCause().getMessage());
        }
        alert.showAndWait();
    }

    public static void referenceMissing() {
        // Nada selecionado.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aviso!.");
        alert.setHeaderText("Defina uma referencia antes de usar o modo MONITOR!");

        alert.showAndWait();
    }
}
