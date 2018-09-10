package controle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import algoritmogenetico.Genetico;
import algoritmogenetico.Individuo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class ControladorSimulacoes implements Initializable{

    @FXML
    Button btGerar;
    
    @FXML
    TextField tfForragemInicial, tfMediaAcumulo, tfPesoV, tfGanhoDiario, tfMaxAnimais, tfMaxDias;
    
    ObservableList<Individuo> lista = FXCollections.observableArrayList();
    
    @FXML
    TableView<Individuo> tvSimulacoes;
    
    @FXML
    TableColumn<Individuo, Integer> tcCenario;
    
    @FXML
    TableColumn<Individuo, Integer> tcQtdAnimais; 
   
    @FXML
    TableColumn<Individuo, Integer> tcDias;
   
    @FXML
    TableColumn<Individuo, Double> tcSaldo;
    
    @FXML
    private void gerar(){
        Genetico ag = new Genetico();
        ArrayList<Individuo> solucoes = new ArrayList<Individuo>();
        int cont = 1;
		for (int i = 0; i < 10; i++) {
			Individuo solucao = ag.executaAG(Double.parseDouble(tfForragemInicial.getText()),
					Double.parseDouble(tfMediaAcumulo.getText()), Double.parseDouble(tfPesoV.getText()),
					Double.parseDouble(tfGanhoDiario.getText()), Integer.parseInt(tfMaxAnimais.getText()),
					Integer.parseInt(tfMaxDias.getText()));
			
			if(!solucoes.contains(solucao)) {
				solucao.setIdIndividuo(cont++);
				solucoes.add(solucao);
			}
		}
		lista.clear();
		lista.addAll(solucoes);
		tvSimulacoes.setItems(lista);
		
		tcCenario.setCellValueFactory(new PropertyValueFactory<Individuo, Integer>("idIndividuo"));
		tcQtdAnimais.setCellValueFactory(new PropertyValueFactory<Individuo, Integer>("numeroAnimais"));
		tcDias.setCellValueFactory(new PropertyValueFactory<Individuo, Integer>("qtdDias"));
		tcSaldo.setCellValueFactory(new PropertyValueFactory<Individuo, Double>("aptidao"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
    
}
