module com.example.javafx_essai1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;
    requires com.jfoenix;
    requires MaterialFX;
    requires VirtualizedFX;
    requires org.kordamp.ikonli.javafx;
    requires junit;
    requires java.desktop;
    requires org.apache.logging.log4j;

    opens com.example.javafx_essai1 to javafx.fxml;
    exports com.example.javafx_essai1;
}