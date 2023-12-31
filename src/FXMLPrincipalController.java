import java.text.DecimalFormat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FXMLPrincipalController implements Initializable {

    private File imagenSeleccionada;

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

    @FXML
    private ImageView ivFoto;

    @FXML
    private Pane paneBackgroundFoto;

    @FXML
    private TextField tfApellidoMaterno;

    @FXML
    private TextField tfApellidoPaterno;

    @FXML
    private TextField tfNoPersonal;

    @FXML
    private TextField tfNombrePerfil;

    @FXML
    private TabPane tabContenedor;

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
    private void seleccionarOtroIngrediente(ActionEvent e) {
        if (cbOtro.isSelected()) {
            tfOtro.setVisible(true);
            tfOtro.setEditable(true);
        } else {
            tfOtro.setText("");
            tfOtro.setVisible(false);
            tfOtro.setEditable(false);
        }
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

        if (cbOtro.isSelected())
            ordenCreada += "\n - " + tfOtro.getText();

        mostrarOrdenCreada(ordenCreada);
    }

    @FXML
    void btnSeleccionarFoto(ActionEvent event) throws Exception {
        FileChooser dialogoSeleccion = new FileChooser();
        dialogoSeleccion.setTitle("Selecciona la foto del Usuario");
        String etiquetatipoDato = "Archivos PNG  (*.png)";
        String extensionArchivo = "*.PNG";
        Stage scenarioActual = (Stage) ivFoto.getScene().getWindow();
        FileChooser.ExtensionFilter filtroSeleccion = new FileChooser.ExtensionFilter(etiquetatipoDato,
                extensionArchivo);
        dialogoSeleccion.getExtensionFilters().add(filtroSeleccion);
        imagenSeleccionada = dialogoSeleccion.showOpenDialog(scenarioActual);

        if (imagenSeleccionada != null) {
            try {
                BufferedImage buffered = ImageIO.read(imagenSeleccionada);
                WritableImage image = SwingFXUtils.toFXImage(buffered, null);
                ivFoto.setImage(image);
                paneBackgroundFoto.setVisible(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void btnVerPerfil(ActionEvent event) {
        LinkedHashMap<String, String> datos = new LinkedHashMap<>();

        if (!tfNombrePerfil.getText().isEmpty()) {
            datos.put("nombre", tfNombrePerfil.getText());
        }

        if (!tfNoPersonal.getText().isEmpty()) {
            datos.put("noPersonal", tfNoPersonal.getText());
        }

        if (!tfApellidoPaterno.getText().isEmpty()) {
            datos.put("apellidoPaterno", tfApellidoPaterno.getText());
        }

        if (!tfApellidoMaterno.getText().isEmpty()) {
            datos.put("apellidoMaterno", tfApellidoMaterno.getText());
        }

        if (imagenSeleccionada != null) {
            datos.put("rutaImagen", imagenSeleccionada.getPath());
        }

        if (datos.size() == 5) {
            mostrarFormacionInfoPersonal(datos);
        } else {
            mostrarErrorInfoPersonal();
        }
    }

    private boolean verificarExistenciaCarrera(String carrera) {
        for (String carreraExistente : listaCarreras) {
            if (carrera.toLowerCase().equals(carreraExistente.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    @FXML
    void menuIrFinal(ActionEvent event) {
        tabContenedor.getSelectionModel().selectLast();
    }

    @FXML
    void menuIrInicio(ActionEvent event) {
        tabContenedor.getSelectionModel().select(0);
    }

    @FXML
    void menuSalirPrograma(ActionEvent event) {
        Stage stagePrincipal = (Stage) tabContenedor.getScene().getWindow();
        stagePrincipal.close();
    }

    @FXML
    void menuVerAcercaDe(ActionEvent event) {
        String acercaDe = "Alumno: Albhieri Cristoff Villa Contreras\n" +
                "Experiencia Educativa: Principios de Construccion de Software\n" +
                "Facultad de Estadistica e Informatica\n" +
                "Universidad Veracruzana";

        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);

        dialogoInfo.setTitle("Acerca De");

        dialogoInfo.setHeaderText("Sobre mi:");
        dialogoInfo.setContentText(acercaDe);
        dialogoInfo.showAndWait();
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
        if (cbOtro.isSelected() && tfOtro.getText().isEmpty()) {
            Alert dialogoOrden = new Alert(Alert.AlertType.ERROR);

            dialogoOrden.setTitle("Orden Seleccionada");

            dialogoOrden.setHeaderText(null);
            dialogoOrden.setContentText("Debes escribir otro ingrediente o deseleccionarlo para crear tu orden");
            dialogoOrden.showAndWait();
        } else if (!orden.isEmpty()) {
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

    private void mostrarFormacionInfoPersonal(LinkedHashMap<String, String> datos) {
        String info = String.format(
                "No. Personal: %s\nNombre: %s\nApellido Paterno: %s\nApellido Materno: %s\nRuta de Imagen: %s",
                datos.get("noPersonal"), datos.get("nombre"), datos.get("apellidoPaterno"),
                datos.get("apellidoMaterno"), datos.get("rutaImagen"));

        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);

        dialogoInfo.setTitle("Informacion Personal");

        dialogoInfo.setHeaderText(null);
        dialogoInfo.setContentText(info);
        dialogoInfo.showAndWait();
    }

    private void mostrarErrorInfoPersonal() {
        Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);

        dialogoInfo.setTitle("No ingresaste todos los datos");

        dialogoInfo.setHeaderText(null);
        dialogoInfo.setContentText("No ingresaste todos los datos");
        dialogoInfo.showAndWait();
    }
}
