package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    int locationOfCorrectAnswer;
    TextView resultTextView;
    TextView scoreTextView;
    TextView sumTextView;
    TextView timerTextView;
    int score;//to keep score of correct answers
    int noOfQuestion=0;//total number of question
    ArrayList<Integer> answers=new ArrayList<Integer>();//create array list of answers

    public  void  chooseAnswer(View view)//function called on clicking canswer buttons
    {
        Log.i("Tag",view.getTag().toString());
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer)))
        {
            resultTextView.setText("Correct! ");
            score++;
            noOfQuestion++;
            scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(noOfQuestion));//setting score
        }
        else
        {
            resultTextView.setText("Wrong! :(");
            noOfQuestion++;
            scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(noOfQuestion));//setting score
        }
        newQuestion();//after one question calling next question
    }
    public void start(View view)
    {
        goButton.setVisibility(View.INVISIBLE);//intially set visibility of go button invisible
        playAgain(findViewById(R.id.timerTextView));//any view
        timerTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
        resultTextView.setText(" ");
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
    }
    public  void newQuestion()//creating new question
    {
        Random rand=new Random();//create random integer
        int a=rand.nextInt(21);//generate random number between 0-20
        int b=rand.nextInt(21);
        sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));//setting a and b to display in question

        locationOfCorrectAnswer=rand.nextInt(4);
        answers.clear();
        for(int i=0;i<4;i++)
        {
            if(i==locationOfCorrectAnswer)//add correct answer
            {
                answers.add(a+b);
            }
            else//add wrong answer
            {
                int wrongAnswers=rand.nextInt(41);
                while(wrongAnswers==a+b)//if generated number is eqaual to answer
                {
                    wrongAnswers=rand.nextInt(41);
                }
                answers.add(wrongAnswers);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));//set answers on the 4 buttons
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void playAgain(View view)
    {

        score=0;
        noOfQuestion=0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(noOfQuestion));//upating score to0/0
        playAgainButton.setVisibility(View.INVISIBLE);

            newQuestion();

        new CountDownTimer(30000,1000)
        {
            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {

                resultTextView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0=(Button) findViewById(R.id.button0);
        button1=(Button) findViewById(R.id.button1);
        button2=(Button) findViewById(R.id.button2);
        button3=(Button) findViewById(R.id.button3);
        playAgainButton=(Button)findViewById(R.id.playAgainButton);
        resultTextView=(TextView)findViewById(R.id.resultTextView) ;
        scoreTextView=(TextView)findViewById(R.id.scoreTextView);
        sumTextView=(TextView)findViewById(R.id.sumTextView);
        timerTextView=(TextView)findViewById(R.id.timerTextView);
        goButton=findViewById(R.id.goButton);
        goButton.setVisibility(View.VISIBLE);


        timerTextView.setVisibility(View.INVISIBLE);
        scoreTextView.setVisibility(View.INVISIBLE);
        sumTextView.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);
        button0.setVisibility(View.INVISIBLE);
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);

    }
}
