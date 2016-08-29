/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unirn.exemplofx;

import br.edu.unirn.exemplofx.entity.Contato;
import br.edu.unirn.exemplofx.model.Pessoa;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author romulofc
 */
public class FXMLDocumentController extends AbstractController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private TextField txtNome;

    @FXML
    private ComboBox<Pessoa> cmbPessoa;

    @FXML
    private TableColumn<Pessoa, String> colNome;

    @FXML
    private TableColumn<Pessoa, String> colTelefone;

    @FXML
    private TableView<Pessoa> tblPessoa;

    public ObservableList<Pessoa> listaPessoa = FXCollections.observableArrayList();

    @FXML
    public TextField txtContatoNome;

    @FXML
    public TextField txtContatoEmail;

    @FXML
    private TableColumn<Contato, Long> colContatoID;

    @FXML
    private TableColumn<Contato, String> colContatoNome;

    @FXML
    private TableColumn<Contato, String> colContatoEmail;

    @FXML
    private TableColumn<Contato, Date> colContatoDataCadastro;

    @FXML
    private TableView<Contato> tblContato;

    public ObservableList<Contato> listaContato = FXCollections.observableArrayList();

    @FXML
    public void cadastrar(ActionEvent event) {
        EntityManager em = PrincipalFX.getEntityManager().createEntityManager();
        em.getTransaction().begin();
        Contato c = new Contato();
        c.setNome(txtContatoNome.getText());
        c.setEmail(txtContatoEmail.getText());
        em.persist(c);
        em.getTransaction().commit();
        txtContatoEmail.setText("");
        txtContatoNome.setText("");
        listaContato.add(c);
    }

    private void listar() {
        EntityManager em = PrincipalFX.getEntityManager().createEntityManager();
        List<Contato> listaContatoDB = (ArrayList<Contato>) em
                .createQuery("from Contato")
                .getResultList();
        listaContato.clear();
        listaContato.addAll(listaContatoDB);
    }

    @FXML
    public void exibirGraficos(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLGraficos.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Gráfico");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbPessoa.getItems().add(new Pessoa("Fulano"));
        cmbPessoa.getItems().add(new Pessoa("Sicrano"));
        cmbPessoa.getItems().add(new Pessoa("Zezinho"));
        //cmbPessoa.getItems().addAll
        //cmbPessoa.getSelectionModel().getSelectedItem();

        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        loadData();

        colContatoID.setCellValueFactory(new PropertyValueFactory<>("id"));
        setColumn(colContatoNome, "nome");
        setColumn(colContatoEmail, "email");
        setColumn(colContatoDataCadastro, "dataCadastro");
        tblContato.setItems(listaContato);
        listar();
    }

    private void loadData() {
        //JPA - Listagem dos valores
        listaPessoa.add(new Pessoa("Fulano"));
        listaPessoa.add(new Pessoa("Sicrano"));
        listaPessoa.add(new Pessoa("Luizinho"));
        tblPessoa.setItems(listaPessoa);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @FXML
    private void showMessage(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message Here...");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText("I have a great message for you! " + txtNome.getText());
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
    }

}
