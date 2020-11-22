package SmartAttendanceSystem;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class Modules implements Initializable {

    @FXML
    GridPane ModuleView;
    @FXML
    ImageView testImage;
    @FXML
    Label title_;

    String Batch = "19.2";

    List<ViewItem> Modules;

    MainPage mainPage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Load the modules
        Modules = ModulesDAO.ModuleList;

        int r=0, c=0;
        for (ViewItem module:Modules) {
            c++;
            Node crModule = module.CreateModuleItem();

            EventHandler<MouseEvent> ItemClickedEvent = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    try {
                        ItemClickedF(module);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            };

            crModule.addEventFilter(MouseEvent.MOUSE_CLICKED, ItemClickedEvent);



            ModuleView.add(crModule, c, r);
            ModuleView.setMargin(crModule, new Insets(0,40,20,0));
            if(c>1) {
                r++;
                c=0;
            }

        }

    }
    public void setMainPage(MainPage mainPage){
        this.mainPage = mainPage;
    }

    public void ItemClickedF(ViewItem ClickedItem) throws IOException {
        FXMLLoader loder = new FXMLLoader();
        loder.setLocation(getClass().getResource("SessionSelection.fxml"));
        AnchorPane page = loder.load();
        SessionSelection Mpage = loder.getController();
        Mpage.getBase(mainPage);
        Mpage.setModule(ClickedItem);
        mainPage.base.getChildren().clear();
        mainPage.base.getChildren().setAll(page);


    }
}
