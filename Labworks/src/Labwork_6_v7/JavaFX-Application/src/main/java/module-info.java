module com.example.payrollsystemjavafx {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.controlsfx.controls;
  requires com.dlsc.formsfx;
  requires lombok;
  requires com.fasterxml.jackson.core;
  requires com.fasterxml.jackson.databind;
  requires android.json;
  requires java.net.http;
  requires com.google.gson;

  opens bsuir.coursework to javafx.fxml;
  exports bsuir.coursework ;
  exports bsuir.coursework.controllers ;
  opens bsuir.coursework.controllers to javafx.fxml;
  exports bsuir.coursework.utils ;
  opens bsuir.coursework.utils to javafx.fxml;
  opens bsuir.coursework.models.dto to javafx.base, com.google.gson;
}
