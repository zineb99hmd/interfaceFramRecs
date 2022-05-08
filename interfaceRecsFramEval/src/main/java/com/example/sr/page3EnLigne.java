package com.example.sr;


import algorithm.*;
import evaluation.Evaluator;
import evaluation.metrics.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sender.Sender;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class page3EnLigne implements Initializable {
    @FXML
    Pane rootpane;

    @FXML
    private Spinner<Integer> limites;
    @FXML
    private Spinner<Integer> temps;
    @FXML
    Integer limiteValue;

    @FXML
    Long timeValue;

    @FXML
    private MenuButton menusystem;
    @FXML
    private RadioMenuItem Random;
    @FXML
    private RadioMenuItem MostPopular;
    @FXML
    private RadioMenuItem RecentlyClicked;
    @FXML
    private RadioMenuItem RecentlyPopular;
    @FXML
    private RadioMenuItem CoOccurence;
    @FXML
    private RadioMenuItem ItemItemCF;
    @FXML
    private MenuButton menumetric;
    @FXML
    private RadioMenuItem ClickThroughRate ;
    @FXML
    private RadioMenuItem F1;

    @FXML
    private RadioMenuItem MAP;
    @FXML
    private RadioMenuItem MeanF1;

    @FXML
    private RadioMenuItem MRR;
    @FXML
    private RadioMenuItem NDCG;
    @FXML
    private TableView<table> tableMetric;
    @FXML
    private TableColumn<table,String> Metrique;
    @FXML
    private TableColumn<table,String> Resultats;
    @FXML
    private TableColumn<table, String> Algorithme;

    @FXML
    private TableView<table> AlgorithmTable;

    @FXML
    private TableColumn<table, String> resrecommandation;
    @FXML
    private RadioMenuItem PrecisionOrRecall;
    private SimpleStringProperty metrique;
    private SimpleStringProperty resultats;
    private SimpleStringProperty Alg;
    private SimpleStringProperty recommandation;


    public ObservableList<table> data_Metric;
    public ObservableList<table> data_Alg;

    List<table> metricTable=new ArrayList<>();
    List<table> AlgorithmTabl=new ArrayList<>();


     List<evaluation.metrics.Metric> metricsList=new ArrayList<>();

    private Scene scene;
    //Algorithm
    String Algorithm;
    Algorithm ReC = new RecentlyClicked();
    Algorithm rand = new Random();
    Algorithm MoP = new MostPopular();
    Algorithm RcP = new RecentlyPopular();
    Algorithm ItemCF = new ItemItemCF();
    Algorithm CoC = new FastSessionCoOccurrence();
    //MEtrics
    Metric  click=new ClickThroughRate();
    Metric f1=new F1();
    Metric map=new MAP();
    Metric ndcg=new NDCG();
    Metric mrr=new MRR();
    Metric precision=new PrecisionOrRecall();
    Metric meanf1=new MeanF1();

    @FXML
    private void Quit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void minimize(ActionEvent event) {

        Stage stage = (Stage) rootpane.getScene().getWindow();
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);

    }

    @FXML
    public void random(ActionEvent event) {
        if (Random.isSelected()) {
            menusystem.setText(Random.getText());
            Algorithm = Random.getText();
        }
    }

    @FXML
    public void mostPopular(ActionEvent event) {
        if (MostPopular.isSelected()) {
            menusystem.setText(MostPopular.getText());

            Algorithm = MostPopular.getText();
        }
    }

    @FXML
    public void recentlyClicked(ActionEvent event) {
        if (RecentlyClicked.isSelected()) {
            menusystem.setText(RecentlyClicked.getText());

            Algorithm = RecentlyClicked.getText();
        }
    }

    @FXML
    public void recentlyPopular(ActionEvent event) {
        if (RecentlyPopular.isSelected()) {
            menusystem.setText(RecentlyPopular.getText());

            Algorithm = RecentlyPopular.getText();
        }
    }

    @FXML
    public void coccurence(ActionEvent event) {
        if (CoOccurence.isSelected()) {
            menusystem.setText(CoOccurence.getText());

            Algorithm = CoOccurence.getText();
        }
    }

    @FXML
    public void itemItemCF(ActionEvent event) {
        if (ItemItemCF.isSelected()) {
            menusystem.setText(ItemItemCF.getText());

            Algorithm = ItemItemCF.getText();
        }
    }

    @FXML
    public void getlist( ActionEvent event) {
        if (ClickThroughRate.isSelected() && (!metricsList.contains(click))) {

            metricsList.add(click);

        }else{
            if (!ClickThroughRate.isSelected() && (metricsList.contains(click))) {
                metricsList.remove(click);
            }
        }
        if (F1.isSelected()&& (!metricsList.contains(f1))) {
            metricsList.add(f1);

        }else {
            if (!F1.isSelected() && (metricsList.contains(f1)))
                metricsList.remove(f1);


        }
        if (MAP.isSelected()&& (!metricsList.contains(map))) {
            metricsList.add(map);

        }else{if (!MAP.isSelected()&& (metricsList.contains(map)))
            metricsList.remove(map);

        }
        if (MRR.isSelected()&& (!metricsList.contains(mrr))) {
            metricsList.add(mrr);

        }else{
            if(!MRR.isSelected()&& (metricsList.contains(mrr)))
                metricsList.remove(mrr);

            }
        if (MeanF1.isSelected()&& (!metricsList.contains(meanf1))) {
            metricsList.add(meanf1);

        }else{
            if (!MeanF1.isSelected()&& (metricsList.contains(meanf1)))
                metricsList.remove(meanf1);
        }
        if (NDCG.isSelected()&& (!metricsList.contains(ndcg))) {
            metricsList.add(ndcg);

        }else{
            if (!NDCG.isSelected()&& (metricsList.contains(ndcg)))
                metricsList.remove(ndcg);

            }

        if (PrecisionOrRecall.isSelected()&& (!metricsList.contains(precision))) {
            metricsList.add(precision);

        }else{
            if (!PrecisionOrRecall.isSelected()&& (metricsList.contains(precision)))
                metricsList.remove(precision);
        }
        System.out.println("metrics list : "+metricsList);


    }
    @FXML
    private void start(ActionEvent event) {
        if (menusystem.getText().equals("Random")) {

            Sender.onlineStrategy(page2EnLigne.getLien_item(), page2EnLigne.getLien_event(), rand, getLimiteValue(), getTimeValue(),metricsList);


        } else {
            if (menusystem.getText().equals("MostPopular")) {

                Sender.onlineStrategy(page2EnLigne.getLien_item(), page2EnLigne.getLien_event(), MoP, getLimiteValue(), getTimeValue(), metricsList);


            } else {
                if (menusystem.getText().equals("Recently clicked")) {

                    Sender.onlineStrategy(page2EnLigne.getLien_item(),page2EnLigne.getLien_event(),ReC, getLimiteValue(), getTimeValue(), metricsList);


                } else {
                    if (menusystem.getText().equals("Recently Popular")) {

                        Sender.onlineStrategy(page2EnLigne.getLien_item(), page2EnLigne.getLien_event(), RcP, getLimiteValue(), getTimeValue(), metricsList);


                    } else {
                        if (menusystem.getText().equals("Co-Occurence")) {

                            Sender.onlineStrategy(page2EnLigne.getLien_item(), page2EnLigne.getLien_event(), CoC, getLimiteValue(), getTimeValue(), metricsList);


                        } else {
                            if (menusystem.getText().equals("Item-Item-CF")) {

                                Sender.onlineStrategy(page2EnLigne.getLien_item(), page2EnLigne.getLien_event(), ItemCF, getLimiteValue(), getTimeValue(), metricsList);


                            }
                        }
                    }
                }
            }
        }
        //Algorithms
        System.out.println("my list *******************"+Sender.RecList);
        for(int i=0;i<Sender.RecList.size();i++){



            AlgorithmTabl.add(new table(Algorithm,Sender.RecList.get(i),0.0));

        }
        data_Alg = FXCollections.observableArrayList(AlgorithmTabl);
        Algorithme.setCellValueFactory(new PropertyValueFactory<table,String>("Alg"));
        resrecommandation.setCellValueFactory(new PropertyValueFactory<table,String>("recommandation"));
        AlgorithmTable.setItems(data_Alg);

        //Metrics
        for(int i = 0; i< Evaluator.ResmetricsName.size(); i++){
            metricTable.add(new table(Evaluator.ResmetricsName.get(i),Evaluator.ResmetricsResult.get(i)));
        }
        System.out.println(Evaluator.ResmetricsName);
        System.out.println(Evaluator.ResmetricsResult);
        data_Metric = FXCollections.observableArrayList(metricTable);
        Metrique.setCellValueFactory(new PropertyValueFactory<table,String>("metrique"));
        Resultats.setCellValueFactory(new PropertyValueFactory<table,String>("resultats"));
        tableMetric.setItems(data_Metric);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SpinnerValueFactory<Integer> valueFactoryLimites = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000);
        valueFactoryLimites.setValue(1);

        limites.setValueFactory(valueFactoryLimites);
        limiteValue = (int) limites.getValue();
        limites.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                limites.setValueFactory(valueFactoryLimites);
                limiteValue = (int) limites.getValue();
            }
        });
        SpinnerValueFactory<Integer> valueFactoryTime = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000);
        valueFactoryTime.setValue(1);

        temps.setValueFactory(valueFactoryTime);
        timeValue = Long.valueOf(temps.getValue());
        temps.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                temps.setValueFactory(valueFactoryTime);
                timeValue = Long.valueOf(temps.getValue());
            }
        });
    }

    public Long getTimeValue() {
        return timeValue;
    }

    public Integer getLimiteValue() {
        return limiteValue;
    }
}
















