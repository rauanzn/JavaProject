package sample;
import java.time.YearMonth;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
public class Controller implements Command{
    private DoubleProperty fontSize = new SimpleDoubleProperty(10);
    @FXML
    AnchorPane root;
    @FXML
    private GridPane days;
    @FXML
    private GridPane calendar1;
    @FXML
    private Pane months;
    private Calendar cl = new Calendar();
    private Event ev = new Event();
    @FXML
    public void initialize() {
        root.setMinSize(300,300);
        properties();
        int a =0;
        for (Node tabPane : months.getChildren()){
            ((Label)tabPane).prefHeightProperty().bind(months.heightProperty().divide(10));
            ((Label)tabPane).prefWidthProperty().bind(months.widthProperty().divide(3));
            fontSize.bind(months.widthProperty().multiply(0.065));
            ((Label)tabPane).layoutYProperty().bind(months.heightProperty().divide(15).add(months.heightProperty().divide(15).multiply(a)));
            ((Label)tabPane).styleProperty().bind(Bindings.concat("-fx-font-size: ",fontSize.asString(),";"));
            a++;
            tabPane.setOnMouseClicked(event -> {
                switch (tabPane.getId()){
                    case "jan": cl.fillcalendar(YearMonth.of(2018,1),calendar1);ev.AddEvent(calendar1);break;
                    case "feb": cl.fillcalendar(YearMonth.of(2018,2),calendar1);ev.AddEvent(calendar1);break;
                    case "mar": cl.fillcalendar(YearMonth.of(2018,3),calendar1);ev.AddEvent(calendar1);break;
                    case "apr": cl.fillcalendar(YearMonth.of(2018,4),calendar1);ev.AddEvent(calendar1);break;
                    case "may": cl.fillcalendar(YearMonth.of(2018,5),calendar1);ev.AddEvent(calendar1);break;
                    case "jun": cl.fillcalendar(YearMonth.of(2018,6),calendar1);ev.AddEvent(calendar1);break;
                    case "jul": cl.fillcalendar(YearMonth.of(2018,7),calendar1);ev.AddEvent(calendar1);break;
                    case "aug": cl.fillcalendar(YearMonth.of(2018,8),calendar1);ev.AddEvent(calendar1);break;
                    case "sep": cl.fillcalendar(YearMonth.of(2018,9),calendar1);ev.AddEvent(calendar1);break;
                    case "oct": cl.fillcalendar(YearMonth.of(2018,10),calendar1);ev.AddEvent(calendar1);break;
                    case "nov": cl.fillcalendar(YearMonth.of(2018,11),calendar1);ev.AddEvent(calendar1);break;
                    case "dec": cl.fillcalendar(YearMonth.of(2018,12),calendar1);ev.AddEvent(calendar1);break;

                }
            }
            );
        }
    }
    private void properties(){
        months.prefWidthProperty().bind(root.widthProperty().divide(3.5));
        for (Node lbl:months.getChildren()) {
            ((Label)lbl).layoutXProperty().bind(months.widthProperty().divide(2.5));
        }
        months.prefHeightProperty().bind(root.heightProperty());
        calendar1.prefWidthProperty().bind(root.widthProperty().subtract(months.widthProperty()));
        calendar1.prefHeightProperty().bind(root.heightProperty().subtract(days.getPrefHeight()));
        days.prefWidthProperty().bind(root.widthProperty().subtract(months.widthProperty()));
    }
}