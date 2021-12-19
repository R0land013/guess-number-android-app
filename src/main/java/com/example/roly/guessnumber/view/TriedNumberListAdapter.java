package com.example.roly.guessnumber.view;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.roly.guessnumber.R;
import com.example.roly.guessnumber.model.Answer;

import java.util.List;

/**
 * Created by Roly on 14/12/2021.
 */

public class TriedNumberListAdapter extends BaseAdapter{

    private List<Answer> answerList;
    private Activity currentActivity;
    private ListView listView;

    public TriedNumberListAdapter(List<Answer> answers, ListView listView, Activity currentActivity){
        answerList = answers;
        this.listView = listView;
        this.currentActivity = currentActivity;
    }


    @Override
    public int getCount() {
        return answerList.size();
    }

    @Override
    public Object getItem(int position) {

        return answerList.get((answerList.size() - 1) - position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = currentActivity.getLayoutInflater().inflate(R.layout.tried_number_list_item, listView, false);
        }

        Answer currentAnswer = (Answer) getItem(position);
        updateView(convertView, currentAnswer);

        return convertView;
    }

    private void updateView(View convertView, Answer currentAnswer){
        updateIntentNumber(convertView, currentAnswer);
        updateTriedNumberTextView(convertView, currentAnswer);
        updateNumberCategorizationTextView(convertView, currentAnswer);
    }

    private void updateIntentNumber(View convertView, Answer currentAnswer){
        String attemptString = currentActivity.getResources().getString(R.string.attempt_number);

        ((TextView) convertView.findViewById(R.id.intentNumberTextView)).setText(attemptString + currentAnswer.getAttemptNumber());
    }

    private void updateTriedNumberTextView(View convertView, Answer currentAnswer){
        String triedNumberString = currentActivity.getResources().getString(R.string.tried_number);

        ((TextView) convertView.findViewById(R.id.triedNumberTextView)).setText(triedNumberString + currentAnswer.getNumber());
    }

    private void updateNumberCategorizationTextView(View convertView, Answer currentAnswer){
        int categorizationStringRes = 0;
        if(currentAnswer.isTooLowAnswer()){
            categorizationStringRes = R.string.too_low_number;
        }
        else if(currentAnswer.isTooHighAnswer()){
            categorizationStringRes = R.string.too_high_number;
        }
        else{
            categorizationStringRes = R.string.correct_number;
        }

        ((TextView) convertView.findViewById(R.id.numberCategorizationTextView)).setText(categorizationStringRes);
    }

}
