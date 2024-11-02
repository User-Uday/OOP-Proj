import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import com.inven.Inventory;

public class InventoryApp extends Application {
    public static Inventory inventory;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Inventory Statistics");

        int totalGoods = inventory.totalStockByType("good");
        int totalCargo = inventory.totalStockByType("cargo");

        Label goodsLabel = new Label("Total Goods Stock: " + totalGoods);
        Label cargoLabel = new Label("Total Cargo Stock: " + totalCargo);

        VBox vbox = new VBox(10, goodsLabel, cargoLabel);
        Scene scene = new Scene(vbox, 300, 200);

        Button toggleButton = new Button("Show Statistics");
        toggleButton.setOnAction(e -> {
            if (vbox.isVisible()) {
                vbox.setVisible(false);
                toggleButton.setText("Show Statistics");
            } else {
                vbox.setVisible(true);
                toggleButton.setText("Hide Statistics");
            }
        });

        VBox mainLayout = new VBox(10, toggleButton, vbox);
        primaryStage.setScene(new Scene(mainLayout, 300, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
