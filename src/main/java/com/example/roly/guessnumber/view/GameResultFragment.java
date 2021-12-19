package com.example.roly.guessnumber.view;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.roly.guessnumber.R;
import com.example.roly.guessnumber.model.Answer;
import com.example.roly.guessnumber.model.GameResult;

public class GameResultFragment extends Fragment{

    public static final String TAG = "game_result_fragment_tag";

    private TextView gameResultTextView;
    private TextView correctAnswerTextView;
    private Button playAgainButton;

    private PlayAgainListener playAgainListener;

    private GameResult gameResult;
    private Answer correctAnswer;

    public static GameResultFragment newInstance(GameResult gameResult, Answer correctAnswer){
        GameResultFragment gameResultFragment = new GameResultFragment();
        gameResultFragment.gameResult = gameResult;
        gameResultFragment.correctAnswer = correctAnswer;

        return gameResultFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            playAgainListener = (PlayAgainListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement PlayAgainListener.");
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setUIComponents();

    }

    private void setUIComponents(){
        setUIComponentsInstances();
        updateViewByGameResult();

        updateAndSetActionOfPlayAgainButton();
    }

    private void setUIComponentsInstances(){
        Activity activity = getActivity();
        gameResultTextView = activity.findViewById(R.id.gameResultTextView);
        correctAnswerTextView = activity.findViewById(R.id.correctAnswerTextView);
        playAgainButton = activity.findViewById(R.id.playAgainButton);
    }

    private void updateViewByGameResult(){
        updateGameResultTextView();
        updateCorrectAnswerTextView();
    }

    private void updateGameResultTextView(){

        if(gameResult.isLostGame()){
            gameResultTextView.setText(R.string.lost_game);
            gameResultTextView.setTextAppearance(R.style.resultGameTextLoser);
        }
        else if(gameResult.isWonGame()){
            gameResultTextView.setText(R.string.won_game);
            gameResultTextView.setTextAppearance(R.style.resultGameTextWinner);
        }

    }

    private void updateCorrectAnswerTextView(){
        String correctAnswerString = getActivity().getResources().getString(R.string.correct_answer_msg);

        correctAnswerTextView.setText(correctAnswerString + " " + correctAnswer.getNumber());
    }

    private void updateAndSetActionOfPlayAgainButton(){
        playAgainButton.setText(R.string.play_again);
        playAgainButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                playAgainListener.onPlayAgainPressed();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.game_result, container, false);
    }



    public interface PlayAgainListener{


        void onPlayAgainPressed();
    }
}
