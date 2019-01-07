package sample;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

public class Event {
    private TextField tfUsername = new TextField();
    private Button btAddUser = new Button("Add Events");
    public void EditEvent(GridPane calendar1){
        for(Node node : calendar1.getChildren()) {
            if (node instanceof ScrollPane){
                ScrollPane b = (ScrollPane) node;
                VBox view1 = (VBox)b.getContent();
                for (int i = 1; i < view1.getChildren().size(); i++) {
                    Label lbl1 = (Label)view1.getChildren().get(i);
                    lbl1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                                if(mouseEvent.getClickCount() == 2){
                                    GridPane gridPane = new GridPane();
                                    Button deletebut = new Button("Delete this event");
                                    gridPane.add(tfUsername,0,0);
                                    gridPane.add(btAddUser, 0, 1);
                                    gridPane.add(deletebut, 1, 1);
                                    btAddUser.setOnAction(e -> {
                                        try {
                                            if (SystemTray.isSupported()) {
                                                TryIconDemo td = new TryIconDemo();
                                                td.displayTray( "Событие "+lbl1.getText()+" Отредактировано на "+tfUsername.getText());
                                            } else {
                                                System.err.println("System tray not supported!");
                                            }}
                                        catch (Exception ex){}
                                        lbl1.setText(tfUsername.getText());
                                        btAddUser.getScene().getWindow().hide();
                                    });
                                    deletebut.setOnAction(event -> {view1.getChildren().remove(lbl1);
                                        try {
                                            if (SystemTray.isSupported()) {
                                                TryIconDemo td = new TryIconDemo();
                                                td.displayTray(lbl1.getText()+" Событие Удалено");
                                            } else {
                                                System.err.println("System tray not supported!");
                                            }}
                                            catch (Exception ex){}
                                        deletebut.getScene().getWindow().hide();
                                    });
                                    Scene scene = new Scene(gridPane, 300, 150);
                                    Stage stage = new Stage();
                                    stage.setTitle("Add Event");
                                    stage.setScene(scene);
                                    stage.show();
                                }
                            }
                        }
                    });
                }
            }
        }
    }
    public void AddEvent(GridPane calendar1){
        for (Node node : calendar1.getChildren()){
            if (node instanceof ScrollPane){
                ScrollPane b = (ScrollPane) node;
                VBox view1 = (VBox)b.getContent();
                ((Label)view1.getChildren().get(0)).prefWidthProperty().bind(calendar1.widthProperty());
                view1.getChildren().get(0).setOnMouseClicked(event -> {
                    GridPane gridPane = new GridPane();
                    gridPane.add(tfUsername,0,0);
                    gridPane.add(btAddUser, 0, 1);
                    btAddUser.setOnAction(e -> {
                        Label label = new Label(tfUsername.getText());
                        label.setPrefWidth(b.getWidth()-2);
                        label.setStyle("-fx-background-color: yellow");
                        label.setMaxHeight(17.6);
                        try {
                        if (SystemTray.isSupported()) {

                            TryIconDemo td = new TryIconDemo();
                            td.displayTray(label.getText()+" Добавлено событие");
                        } else {
                            System.err.println("System tray not supported!");
                        }}
                        catch (Exception ex){};
                        label.prefWidthProperty().bind(calendar1.widthProperty());
                        view1.getChildren().add(label);
                        b.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                        b.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                        EditEvent(calendar1);
                        btAddUser.getScene().getWindow().hide();
                    });
                    Scene scene = new Scene(gridPane, 300, 150);
                    Stage stage = new Stage();
                    stage.setTitle("Add Event");
                    stage.setScene(scene);
                    stage.show();
                });
            }}
    }
}
