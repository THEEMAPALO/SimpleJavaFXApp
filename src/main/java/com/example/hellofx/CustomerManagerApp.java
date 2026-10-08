package com.example.hellofx;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CustomerManagerApp extends Application {

    private final ObservableList<Customer> customers =
            FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {
        Label nameLabel = new Label("_Customer Name");
        nameLabel.setMnemonicParsing(true);
        TextField nameField = new TextField();
        nameLabel.setLabelFor(nameField);

        ComboBox<String> provinceBox = new ComboBox<>(
                FXCollections.observableArrayList(
                        "Central", "Copperbelt", "Eastern", "Luapula",
                        "Lusaka", "Muchinga", "Northern",
                        "North-Western", "Southern", "Western"));
        provinceBox.setPromptText("Select Province");

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        Button addButton = new Button("Add Customer");
        addButton.setDefaultButton(true);
        Button deleteButton = new Button("Delete Customer");

        TableView<Customer> table = new TableView<>(customers);
        TableColumn<Customer, String> nameCol = new TableColumn<>("Customer");
        nameCol.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getName()));
        TableColumn<Customer, String> provCol = new TableColumn<>("Province");
        provCol.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getProvince()));
        table.getColumns().addAll(nameCol, provCol);
        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        addButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            String province = provinceBox.getValue();
            if (name.isEmpty()) {
                errorLabel.setText("Please enter a name.");
                nameField.requestFocus();
            } else if (province == null) {
                errorLabel.setText("Please choose a province.");
                provinceBox.requestFocus();
            } else {
                customers.add(new Customer(name, province));
                errorLabel.setText("");
                nameField.clear();
                provinceBox.setValue(null);
                nameField.requestFocus();
            }
        });

        deleteButton.setOnAction(e -> {
            Customer selected = table.getSelectionModel().getSelectedItem();
            if (selected == null) {
                errorLabel.setText("Select a customer to delete.");
                return;
            }
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                    "Delete " + selected.getName() + "?",
                    ButtonType.YES, ButtonType.NO);
            confirm.setHeaderText("Confirm deletion");
            confirm.showAndWait().ifPresent(result -> {
                if (result == ButtonType.YES) {
                    customers.remove(selected);
                    errorLabel.setText("");
                }
            });
        });

        VBox root = new VBox(12, nameLabel, nameField, provinceBox,
                addButton, deleteButton, errorLabel, table);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(15));
        VBox.setVgrow(table, Priority.ALWAYS);

        stage.setTitle("Customer Manager");
        stage.setScene(new Scene(root, 500, 520));
        stage.show();
    }
}