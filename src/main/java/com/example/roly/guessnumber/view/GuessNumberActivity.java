package com.example.roly.guessnumber.view;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.roly.guessnumber.R;
import com.example.roly.guessnumber.model.Answer;
import com.example.roly.guessnumber.model.GameResult;
import com.example.roly.guessnumber.presenter.MainPresenter;
import com.example.roly.guessnumber.view.exception.BadInputException;

import java.util.LinkedList;
import java.util.List;

public class GuessNumberActivity extends Activity implements GuessNumberView, GameResultFragment.PlayAgainListener{

    private MainPresenter presenter;

    private EditText numberEditText;
    private Button tryNumberButton;
    private ListView answerListView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if(item.getItemId() == R.id.aboutItem){
            openAboutActivity();
        }

        return super.onMenuItemSelected(featureId, item);
    }

    private void openAboutActivity(){
        Intent intent = new Intent();
        intent.setClass(this, AboutActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new MainPresenter(this);
        setUpView();

        presenter.startNewGame();
    }

    private void setUpView(){
        setUIComponentsInstances();
        setUIComponentsActions();
    }

    private void setUIComponentsInstances(){
        setContentView(R.layout.guess_activity);

        numberEditText = (EditText) findViewById(R.id.numberEditText);
        tryNumberButton = (Button) findViewById(R.id.tryNumberButton);
        answerListView = (ListView) findViewById(R.id.answerListView);
    }

    private void setUIComponentsActions(){

        setTryNumberButtonAction();
        setEmptyAnswerListViewActions();
    }

    private void setEmptyAnswerListViewActions(){
        List<Answer> emptyList = new LinkedList<>();
        TriedNumberListAdapter listAdapter = new TriedNumberListAdapter(emptyList, answerListView,this);
        answerListView.setAdapter(listAdapter);
    }

    private void setTryNumberButtonAction(){
        tryNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateInput()){
                    presenter.tryCurrentNumber();
                    updateGivenAnswersListView();
                    clearNumberEditText();
                }
            }
        });
    }

    private boolean validateInput(){

        int currentNumber = 0;

        try {
            currentNumber = getNumberFromUI();
        } catch (BadInputException e) {
            //TODO Colorear el campo para señalar el error o poner mensaje toast
            return false;
        }

        if(currentNumber < 1 || currentNumber > 100){
            return false;
        }
        return true;
    }

    @Override
    public int getNumberFromUI() throws BadInputException{
        if(numberEditText.getText().toString().isEmpty()){
            throw new BadInputException("number");
        }
        return Integer.valueOf(numberEditText.getText().toString());
    }

    public void updateGivenAnswersListView(){
        List<Answer> emptyList = presenter.getGivenAnswerList();
        TriedNumberListAdapter listAdapter = new TriedNumberListAdapter(emptyList, answerListView,this);
        answerListView.setAdapter(listAdapter);
    }

    private void clearNumberEditText(){
        numberEditText.setText("");
    }

    @Override
    public void openGameResultView(GameResult gameResult, Answer correctAnswer) {
        GameResultFragment fragment = GameResultFragment.newInstance(gameResult, correctAnswer);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.main_container, fragment, GameResultFragment.TAG).commit();
    }

    @Override
    public void onPlayAgainPressed() {
        removeGameResultFragment();
        presenter.startNewGame();
    }

    private void removeGameResultFragment(){
        GameResultFragment fragmentToRemove = (GameResultFragment) getFragmentManager().findFragmentByTag(GameResultFragment.TAG);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.remove(fragmentToRemove).commit();
    }

    @Override
    public void resetUIComponents() {
        setEmptyAnswerListViewActions();
        numberEditText.setText("");
    }
}
