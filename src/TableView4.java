import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TableView4 extends Application{
    private final TableView<ProductDetail> table = new TableView<>();
    private final ObservableList<ProductDetail> data = FXCollections.observableArrayList(
            new ProductDetail("Black Beauty", "15%","Yes", "No"),
            new ProductDetail("Facewreck Haze", "21%", "No", "Yes"),
            new ProductDetail("Ft. Collins Cough", "20%", "No", "Yes"),
            new ProductDetail("Black Cherry Soda", "17%", "No", "Yes"),
            new ProductDetail("Sour Apple", "20%", "No", "Yes"),
            new ProductDetail("Holy Grail Kush", "23%", "Yes", "No"),
            new ProductDetail("Ice", "19%", "Yes", "No")
    );




    public static void main(String[] args) {
        launch(args);
    }
    @Override public void start(Stage stage){


        final  Label label = new Label("Indica & Sativa Strain List");
        label.setFont(new Font("Futura",30));

        table.setEditable(true);

        //Table Columns
        TableColumn firstColumn = new TableColumn("Strain Name");
        firstColumn.setMinWidth(200);
        firstColumn.setCellValueFactory(
                new PropertyValueFactory<>("strainName"));

        TableColumn secondColumn = new TableColumn("THC Level");
        secondColumn.setMinWidth(50);
        secondColumn.setCellValueFactory(new PropertyValueFactory<>("THCLevel"));

        TableColumn thirdColumn = new TableColumn("Indica");
        thirdColumn.setMinWidth(50);
        thirdColumn.setCellValueFactory(new PropertyValueFactory<>("indica"));

        TableColumn fourthColumn = new TableColumn("Sativa");
        fourthColumn.setMinWidth(50);
        fourthColumn.setCellValueFactory(new PropertyValueFactory<>("sativa"));

        table.setItems(data);
        table.getColumns().addAll(firstColumn, secondColumn, thirdColumn,fourthColumn);

        //Textfields for Buttons
        final TextField addStrainName = new TextField();
        addStrainName.setPromptText("Strain Name");
//        addStrainName.setMaxWidth(firstColumn.getPrefWidth());

        final TextField addTHCLevel = new TextField();
        addTHCLevel.setPromptText("THC Level");
//        addTHCLevel.setMaxWidth(secondColumn.getPrefWidth());

        final TextField addIndicaType = new TextField();
        addIndicaType.setPromptText("Indica "+" \'Y/N\'");
        //This will resize the columns, making them smaller
//        addIndicaType.setMaxWidth(thirdColumn.getPrefWidth());

        final TextField addSativaType = new TextField();
        addSativaType.setPromptText("Sativa Y/N");

        final Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            data.add(new ProductDetail(
                    addStrainName.getText(),
                    addTHCLevel.getText(),
                    addIndicaType.getText(),
                    addSativaType.getText()
            ));
            addStrainName.clear();
            addTHCLevel.clear();
            addIndicaType.clear();
            addSativaType.clear();
        });

        final Button deleteButton = new Button("Remove");
        deleteButton.setOnAction(e -> {
            table.getItems().clear();
        });

        final HBox hb = new HBox();
        hb.getChildren().addAll(addStrainName,addTHCLevel,addIndicaType,addSativaType,addButton,deleteButton);
        hb.setSpacing(3);
        hb.setPadding(new Insets(10,0,0,0));


        final  VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10,0,0,10));
        vbox.getChildren().addAll(label,table,hb);
        vbox.setStyle("-fx-border-color: green;"+
                "-fx-border-radius:2");

        Scene scene = new Scene(vbox);
        //REMOVES the ADDITIONAL BLANK COLUMN:
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        stage.setScene(scene);
        stage.setTitle("Tazzie's Marijuana Strain List");
        stage.setWidth(820);
        stage.setHeight(550);
        stage.show();

        //List of Java  Font
//        System.out.println("\n"+javafx.scene.text.Font.getFamilies());
    }

    //Can use static for nested classes.
    public static class ProductDetail{
        private final SimpleStringProperty strainName;
        private final SimpleStringProperty THCLevel;
        private final SimpleStringProperty Indica;
        private final SimpleStringProperty Sativa;

        private ProductDetail(String strainName, String THCLevel, String indica, String sativa){
            this.strainName =  new SimpleStringProperty(strainName);
            this.THCLevel = new SimpleStringProperty(THCLevel);
            this.Indica = new SimpleStringProperty(indica);
            this.Sativa = new SimpleStringProperty(sativa);
        }

        public String getStrainName(){
            return strainName.get();
        }

        public void setStrainName(String strainName){
            this.strainName.set(strainName);
        }

        public String getTHCLevel(){
            return THCLevel.get();
        }

        public void setTHCLevel(String THCLevel){
            this.THCLevel.set(THCLevel);
        }

        public String getIndica(){
            return Indica.get();
        }

        public void setIndica(String indica){
            Indica.set(indica);
        }

        public String getSativa(){
            return Sativa.get();
        }

        public void setSativa(String sativa){
            Sativa.set(sativa);
        }

    }


}
