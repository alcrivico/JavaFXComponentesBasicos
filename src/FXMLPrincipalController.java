import java.text.DecimalFormat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;

public class FXMLPrincipalController implements Initializable {

    @FXML
    private Label lbResultado;

    @FXML
    private TextField tfCantidad;

    @FXML
    private TextField tfCarrera;

    @FXML
    private Label lbCarrera;

    @FXML
    private ComboBox<String> cbCarreras;

    private final ObservableList<String> listaCarreras = FXCollections.observableArrayList();

    @FXML
    private Label lbOperacionIngresarCarrera;

    @FXML
    private Label lbSeleccionColor;

    @FXML
    private Pane paneFondo;

    @FXML
    private RadioButton rbAmarillo;

    @FXML
    private RadioButton rbAzul;

    @FXML
    private RadioButton rbVerde;

    @FXML
    private ToggleGroup toggleColores;

    @FXML
    private CheckBox cbJamon;

    @FXML
    private CheckBox cbPepperoni;

    @FXML
    private CheckBox cbPina;

    @FXML
    private CheckBox cbSalami;

    @FXML
    private CheckBox cbOtro;

    @FXML
    private TextField tfOtro;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarValoresCombo();
        cbCarreras.setItems(listaCarreras);
        cbCarreras.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String valorAnterior,
                    String valorActual) {
                lbCarrera.setText("El valor seleccionado es: " + valorActual);
            }

        });

        cambiarColorFondoPane();
    }

    @FXML
    private void btnCalcularIVA(ActionEvent event) {

        final float IVA = 1.16f;
        final DecimalFormat FORMATO_CENTESIMAL = new DecimalFormat("$###,###.00");

        String cantidad = tfCantidad.getText();

        try {
            float realCantidad = Float.parseFloat(cantidad);

            if (realCantidad > -1) {
                float cantidadXIVA = realCantidad * IVA;

                lbResultado.setText("La cantidad con IVA es: " + FORMATO_CENTESIMAL.format(cantidadXIVA));
            } else {
                lbResultado.setText("Por favor digita una cantidad positiva");
            }
        } catch (NumberFormatException e) {
            lbResultado.setText("Por favor digita una cifra numerica");
        }
    }

    @FXML
    private void btnIngresarCarrera(ActionEvent event) {
        String nuevaCarrera = tfCarrera.getText();

        if (!nuevaCarrera.isEmpty()) {
            if (!verificarExistenciaCarrera(nuevaCarrera)) {
                listaCarreras.add(nuevaCarrera);
                lbOperacionIngresarCarrera.setStyle("-fx-text-fill: #42a542");
                lbOperacionIngresarCarrera.setText("La carrera " + nuevaCarrera + " fue agregada a la lista");
            } else {
                lbOperacionIngresarCarrera.setStyle("-fx-text-fill: #FF4365");
                lbOperacionIngresarCarrera.setText("La carrera ya exite en la lista, ingresa una nueva");
            }

        } else {
            lbOperacionIngresarCarrera.setStyle("-fx-text-fill: #FF4365");
            lbOperacionIngresarCarrera.setText("Por favor ingresa una carrera para registrar");
        }
    }

    @FXML
    void btnLimpiarPane(ActionEvent event) {
        RadioButton[] rbs = { rbAzul, rbAmarillo, rbVerde };

        for (RadioButton rb : rbs) {
            if (rb.isSelected()) {
                rb.setSelected(false);
            }
        }

        paneFondo.setStyle(null);
        lbSeleccionColor.setText("");
    }

    @FXML
    private void btnVerOrden(ActionEvent event) {
        String ordenCreada = "";

        if (cbJamon.isSelected())
            ordenCreada += "\n - " + cbJamon.getText();

        if (cbPepperoni.isSelected())
            ordenCreada += "\n - " + cbPepperoni.getText();

        if (cbPina.isSelected())
            ordenCreada += "\n - " + cbPina.getText();

        if (cbSalami.isSelected())
            ordenCreada += "\n - " + cbSalami.getText();

        mostrarOrdenCreada(ordenCreada);
    }

    private boolean verificarExistenciaCarrera(String carrera) {
        for (String carreraExistente : listaCarreras) {
            if (carrera.toLowerCase().equals(carreraExistente.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    private void inicializarValoresCombo() {
        listaCarreras.add("Ingenieria de Software");
        listaCarreras.add("Tecnologias Computacionales");
        listaCarreras.add("Redes y Servicios de Computo");
        listaCarreras.add("Estadistica");
        listaCarreras.add("Derecho");
    }

    private void cambiarColorFondoPane() {
        toggleColores.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle antiguoValor, Toggle nuevoValor) {
                if (rbAzul.isSelected()) {
                    paneFondo.setStyle("-fx-background-color: #96C5F7");
                    lbSeleccionColor.setText(rbAzul.getText());
                    lbSeleccionColor.setStyle("-fx-text-fill: #545454");
                }

                if (rbVerde.isSelected()) {
                    paneFondo.setStyle("-fx-background-color: #b3e270");
                    lbSeleccionColor.setText(rbVerde.getText());
                    lbSeleccionColor.setStyle("-fx-text-fill: #545454");
                }

                if (rbAmarillo.isSelected()) {
                    paneFondo.setStyle("-fx-background-color: #ffe791");
                    lbSeleccionColor.setText(rbAmarillo.getText());
                    lbSeleccionColor.setStyle("-fx-text-fill: #545454");
                }
            }

        });
    }

    private void mostrarOrdenCreada(String orden) {
        if (!orden.isEmpty()) {
            Alert dialogoOrden = new Alert(Alert.AlertType.INFORMATION);

            dialogoOrden.setTitle("Orden Seleccionada");

            dialogoOrden.setHeaderText("Los ingredientes para la pizza son: ");
            dialogoOrden.setContentText(orden);
            dialogoOrden.showAndWait();
        } else {
            Alert dialogoOrden = new Alert(Alert.AlertType.WARNING);

            dialogoOrden.setTitle("Orden Seleccionada");

            dialogoOrden.setHeaderText(null);
            dialogoOrden.setContentText("Debes seleccionar al menos un ingrediente para crear tu orden");
            dialogoOrden.showAndWait();
        }
    }
}
