package sample;

import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.YearMonth;

public class Calendar {
    ScrollPane scrollPane;
    VBox view;
    public void fillcalendar(YearMonth yearMonth, GridPane s){
        s.getChildren().remove(1,s.getChildren().size());
        int nday = 1;
        LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
        int i = 0;
        switch ((""+calendarDate.getDayOfWeek()).toLowerCase()){
            case "monday": i = 1;break;
            case "tuesday": i = 2;break;
            case "wednesday": i = 3;break;
            case "thursday": i = 4;break;
            case "friday": i = 5;break;
            case "saturday": i = 6;break;
            case "sunday": i = 7;break;
        }
        int u = i;


        while(u<8){
            scrollPane = new ScrollPane();
            view = new VBox();
            Label dates = new Label(""+nday);
            dates.setPrefWidth(s.getColumnConstraints().get(0).getPrefWidth());
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            view.getChildren().add(dates);

            GridPane.setValignment(view,VPos.TOP);
            scrollPane.setContent(view);
            scrollPane.setStyle("-fx-background-color: none");
            s.add(scrollPane,u-1, 0);
            u++;
            nday++;
        }
        for (int row = 1; row < 4; row++) {
            for (int column = 0; column < 7; column++) {
                scrollPane = new ScrollPane();
                view = new VBox();
                Label dates = new Label(""+nday);
                dates.setPrefWidth(s.getColumnConstraints().get(0).getPrefWidth());
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                dates.setMaxHeight(17.6);
                view.getChildren().add(dates);
                scrollPane.setContent(view);
                scrollPane.setStyle("-fx-background-color: none");
                GridPane.setValignment(view,VPos.TOP);
                s.add(scrollPane,column,row);
                nday++;
            }
        }
    }
}
