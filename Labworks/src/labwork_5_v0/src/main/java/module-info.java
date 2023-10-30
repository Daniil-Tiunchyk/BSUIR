module com.example.labwork_5_v0 {
  requires javafx.controls;
  requires javafx.fxml;
  requires lombok;

  opens com.example.labwork_5_v0 to javafx.fxml;
  exports com.example.labwork_5_v0 ;
  exports com.example.labwork_5_v0.controllers ;
  opens com.example.labwork_5_v0.controllers to javafx.fxml;
}
