package com.example.testevaluation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TestEvaluation extends AppCompatActivity {
    int count;
    int count2;
    Assessment testEvaluation= new Assessment(10);
    boolean isOkay=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final   int[] rating= new int[10];
        rating[0]=5;
        rating[1]=5;
        rating[2]=3;
        rating[3]=5;
        rating[4]=3;
        rating[5]=2;
        rating[6]=1;
        rating[7]=3;
        rating[8]=1;
        rating[9]=1;
        final int maxScore=29;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_evaluation);
        final Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                Button button = (Button) findViewById(R.id.button);
                button.setText("Next");
                button.setVisibility(View.VISIBLE);
                TextView q1 = (TextView) findViewById(R.id.txt_question1);
                TextView q2 = (TextView) findViewById(R.id.txt_question2);
                TextView q3 = (TextView) findViewById(R.id.txt_question3);
                EditText a1 = (EditText) findViewById(R.id.txt_answer1);
                EditText a2 = (EditText) findViewById(R.id.txt_answer2);
                EditText a3 = (EditText) findViewById(R.id.txt_answer3);
                a1.getText().clear();
                a2.getText().clear();
                a3.getText().clear();
                a1.setInputType(InputType.TYPE_CLASS_NUMBER);
                a2.setInputType(InputType.TYPE_CLASS_NUMBER);
                a3.setInputType(InputType.TYPE_CLASS_NUMBER);
                a1.setVisibility(View.VISIBLE);
                a2.setVisibility(View.VISIBLE);
                a3.setVisibility(View.VISIBLE);
                q1.setVisibility(View.VISIBLE);
                q2.setVisibility(View.VISIBLE);
                q3.setVisibility(View.VISIBLE);
                q1.setText(testEvaluation.answers[0]+"("+rating[0]+")");
                q2.setText(testEvaluation.answers[1]+"("+rating[1]+")");
                q3.setText(testEvaluation.answers[2]+"("+rating[2]+")");
                q1.setTextSize(16);
                q2.setTextSize(16);
                q3.setTextSize(16);
                count2++;

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (count2 == 1) {
                            EditText a1 = (EditText) findViewById(R.id.txt_answer1);
                            EditText a2 = (EditText) findViewById(R.id.txt_answer2);
                            EditText a3 = (EditText) findViewById(R.id.txt_answer3);
                            a1.setInputType(InputType.TYPE_CLASS_NUMBER);
                            a2.setInputType(InputType.TYPE_CLASS_NUMBER);
                            a3.setInputType(InputType.TYPE_CLASS_NUMBER);
                            if (Integer.parseInt(a1.getText().toString())>rating[0]||Integer.parseInt(a2.getText().toString())>rating[1]||Integer.parseInt(a3.getText().toString())>rating[2]){
                                isOkay=false;
                                Toast.makeText(TestEvaluation.this, "You can not give rating more than the value in brackets.", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                testEvaluation.score = testEvaluation.score + (Integer.parseInt(a1.getText().toString()) + Integer.parseInt(a2.getText().toString()) + Integer.parseInt(a3.getText().toString()));
                                a1.getText().clear();
                                a2.getText().clear();
                                a3.getText().clear();
                                TextView q1 = (TextView) findViewById(R.id.txt_question1);
                                q1.setText(testEvaluation.answers[3] + "(" + rating[3] + ")");
                                TextView q2 = (TextView) findViewById(R.id.txt_question2);
                                q2.setText(testEvaluation.answers[4] + "(" + rating[4] + ")");
                                TextView q3 = (TextView) findViewById(R.id.txt_question3);
                                q3.setText(testEvaluation.answers[5] + "(" + rating[5] + ")");
                                q1.setTextSize(12);
                                q2.setTextSize(12);
                                q3.setTextSize(12);
                                count2++;
                                isOkay=true;
                            }
                        } else if (count2 == 2) {
                            EditText a1 = (EditText) findViewById(R.id.txt_answer1);
                            EditText a2 = (EditText) findViewById(R.id.txt_answer2);
                            EditText a3 = (EditText) findViewById(R.id.txt_answer3);
                            a1.setInputType(InputType.TYPE_CLASS_NUMBER);
                            a2.setInputType(InputType.TYPE_CLASS_NUMBER);
                            a3.setInputType(InputType.TYPE_CLASS_NUMBER);
                            if (Integer.parseInt(a1.getText().toString())>rating[3]||Integer.parseInt(a2.getText().toString())>rating[4]||Integer.parseInt(a3.getText().toString())>rating[5]){
                                isOkay=false;
                                Toast.makeText(TestEvaluation.this, "You can not give rating more than the value in brackets.", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                testEvaluation.score = testEvaluation.score + (Integer.parseInt(a1.getText().toString()) + Integer.parseInt(a2.getText().toString()) + Integer.parseInt(a3.getText().toString()));
                                a1.getText().clear();
                                a2.getText().clear();
                                a3.getText().clear();
                                TextView q1 = (TextView) findViewById(R.id.txt_question1);
                                q1.setText(testEvaluation.answers[6] + "(" + rating[6] + ")");
                                TextView q2 = (TextView) findViewById(R.id.txt_question2);
                                q2.setText(testEvaluation.answers[7] + "(" + rating[7] + ")");
                                TextView q3 = (TextView) findViewById(R.id.txt_question3);
                                q3.setText(testEvaluation.answers[8] + "(" + rating[8] + ")");
                                q1.setTextSize(16);
                                q2.setTextSize(16);
                                q3.setTextSize(16);
                                count2++;
                                isOkay=true;
                            }
                        } else if (count2 == 3) {
                            EditText a1 = (EditText) findViewById(R.id.txt_answer1);
                            EditText a2 = (EditText) findViewById(R.id.txt_answer2);
                            EditText a3 = (EditText) findViewById(R.id.txt_answer3);
                            a1.setInputType(InputType.TYPE_CLASS_NUMBER);
                            a2.setInputType(InputType.TYPE_CLASS_NUMBER);
                            a3.setInputType(InputType.TYPE_CLASS_NUMBER);
                            if (Integer.parseInt(a1.getText().toString())>rating[6]||Integer.parseInt(a2.getText().toString())>rating[7]||Integer.parseInt(a3.getText().toString())>rating[8]){
                                isOkay=false;
                                Toast.makeText(TestEvaluation.this, "You can not give rating more than the value in brackets.", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                testEvaluation.score = testEvaluation.score + (Integer.parseInt(a1.getText().toString()) + Integer.parseInt(a2.getText().toString()) + Integer.parseInt(a3.getText().toString()));
                                a1.getText().clear();
                                a2.getText().clear();
                                a3.getText().clear();
                                TextView q1 = (TextView) findViewById(R.id.txt_question1);
                                q1.setText(testEvaluation.answers[9] + "(" + rating[9] + ")");
                                q1.setTextSize(16);
                                count++;
                                TextView q2 = (TextView) findViewById(R.id.txt_question2);
                                TextView q3 = (TextView) findViewById(R.id.txt_question3);
                                q2.setVisibility(View.GONE);
                                a2.setVisibility(View.GONE);
                                q3.setVisibility(View.GONE);
                                a3.setVisibility(View.GONE);
                                count2++;
                                isOkay=true;
                            }
                        } else if (count2 == 4) {
                            TextView q1 = (TextView) findViewById(R.id.txt_question1);
                            TextView q2 = (TextView) findViewById(R.id.txt_question2);
                            EditText a1 = (EditText) findViewById(R.id.txt_answer1);
                            a1.setInputType(InputType.TYPE_CLASS_NUMBER);
                            if (Integer.parseInt(a1.getText().toString())>rating[9]){
                                isOkay=false;
                                Toast.makeText(TestEvaluation.this, "You can not give rating more than the value in brackets.", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                testEvaluation.score = testEvaluation.score + (Integer.parseInt(a1.getText().toString()));
                                q1.setVisibility(View.GONE);
                                a1.setVisibility(View.GONE);
                                q2.setVisibility(View.VISIBLE);
                                Button b1 = (Button) findViewById(R.id.button);
                                b1.setText("Finish");
                                q2.setText("Score: " + testEvaluation.score + "/" + maxScore);
                                count2++;
                                isOkay = true;
                            }
                        }
                        else if (count2==5){
                            finish();
                            System.exit(0);
                        }
                    }

                });
            }
        });

           final Button button = (Button) findViewById(R.id.button);
           button.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if (count == 0) {
                       TextView q1 = (TextView) findViewById(R.id.txt_question1);
                       TextView q2 = (TextView) findViewById(R.id.txt_question2);
                       TextView q3 = (TextView) findViewById(R.id.txt_question3);
                       testEvaluation.questions[0]=q1.getText().toString();
                       testEvaluation.questions[1]=q2.getText().toString();
                       testEvaluation.questions[2]=q3.getText().toString();
                       EditText a1 = (EditText) findViewById(R.id.txt_answer1);
                       EditText a2 = (EditText) findViewById(R.id.txt_answer2);
                       EditText a3 = (EditText) findViewById(R.id.txt_answer3);
                       if (a1.getText().length()==0 || a2.getText().length()==0 || a3.getText().length()==0){
                           isOkay=false;
                           Toast.makeText(TestEvaluation.this, "You need to answer all questions.", Toast.LENGTH_SHORT).show();
                       }
                       else {
                           testEvaluation.answers[0] = a1.getText().toString();
                           testEvaluation.answers[1] = a2.getText().toString();
                           testEvaluation.answers[2] = a3.getText().toString();
                           a1.getText().clear();
                           a2.getText().clear();
                           a3.getText().clear();
                           q1.setText("“I would like you to count backward from 100 by sevens.” (93, 86, 79,\n" +
                                   "72, 65, …)\n" +
                                   "Alternative: “Spell WORLD backwards.” (D-L-R-O-W)");
                           q2.setText("Earlier I told you the names of three things. Can you tell me what\n" +
                                   "those were?");
                           q3.setText("Show the patient two simple objects, such as a wristwatch and a pencil,\n" +
                                   "and ask the patient to name them.");
                           count++;
                           isOkay=true;
                       }
                   } else if (count == 1) {
                       TextView q1 = (TextView) findViewById(R.id.txt_question1);
                       TextView q2 = (TextView) findViewById(R.id.txt_question2);
                       TextView q3 = (TextView) findViewById(R.id.txt_question3);
                       testEvaluation.questions[3]=q1.getText().toString();
                       testEvaluation.questions[4]=q2.getText().toString();
                       testEvaluation.questions[5]=q3.getText().toString();
                       EditText a1 = (EditText) findViewById(R.id.txt_answer1);
                       EditText a2 = (EditText) findViewById(R.id.txt_answer2);
                       EditText a3 = (EditText) findViewById(R.id.txt_answer3);
                       if (a1.getText().length()==0 || a2.getText().length()==0 || a3.getText().length()==0){
                           isOkay=false;
                           Toast.makeText(TestEvaluation.this, "You need to answer all questions.", Toast.LENGTH_SHORT).show();
                       }
                       else {
                           testEvaluation.answers[3] = a1.getText().toString();
                           testEvaluation.answers[4] = a2.getText().toString();
                           testEvaluation.answers[5] = a3.getText().toString();
                           a1.getText().clear();
                           a2.getText().clear();
                           a3.getText().clear();
                           q1.setText("“Repeat the phrase: ‘No ifs, ands, or buts.’”");
                           q2.setText("Take the paper in your right hand, fold it in half, and put it on the floor.”\n" +
                                   "(The examiner gives the patient a piece of blank paper.)");
                           q3.setText("Please read this and do what it says.” (Written instruction is “Close\n" +
                                   "your eyes.”");
                        isOkay=true;
                           count++;
                       }
                   } else if (count == 2) {
                       TextView q1 = (TextView) findViewById(R.id.txt_question1);
                       TextView q2 = (TextView) findViewById(R.id.txt_question2);
                       TextView q3 = (TextView) findViewById(R.id.txt_question3);
                       testEvaluation.questions[6]=q1.getText().toString();
                       testEvaluation.questions[7]=q2.getText().toString();
                       testEvaluation.questions[8]=q3.getText().toString();
                       EditText a1 = (EditText) findViewById(R.id.txt_answer1);
                       EditText a2 = (EditText) findViewById(R.id.txt_answer2);
                       EditText a3 = (EditText) findViewById(R.id.txt_answer3);
                       if (a1.getText().length()==0 || a2.getText().length()==0 || a3.getText().length()==0){
                           isOkay=false;
                           Toast.makeText(TestEvaluation.this, "You need to answer all questions.", Toast.LENGTH_SHORT).show();
                       }
                       else {
                           testEvaluation.answers[6] = a1.getText().toString();
                           testEvaluation.answers[7] = a2.getText().toString();
                           testEvaluation.answers[8] = a3.getText().toString();
                           a1.getText().clear();
                           a2.getText().clear();
                           a3.getText().clear();
                           q1.setText("“Make up and write a sentence about anything.” (This sentence must\n" +
                                   "contain a noun and a verb.)");
                           q2.setVisibility(View.GONE);
                           a2.setVisibility(View.GONE);
                           q3.setVisibility(View.GONE);
                           a3.setVisibility(View.GONE);
                           Button b1 = (Button) findViewById(R.id.button);
                           b1.setText("Finish");
                           count++;
                           isOkay=true;
                       }
                   } else if (count == 3) {
                       TextView q1 = (TextView) findViewById(R.id.txt_question1);
                       testEvaluation.questions[9]=q1.getText().toString();
                       EditText a1 = (EditText) findViewById(R.id.txt_answer1);
                       if (a1.getText().length()==0){
                           isOkay=false;
                           Toast.makeText(TestEvaluation.this, "You need to answer all questions.", Toast.LENGTH_SHORT).show();
                       }
                       else {
                           testEvaluation.answers[9] = a1.getText().toString();
                           TextView q2 = (TextView) findViewById(R.id.txt_question2);
                           q1.setVisibility(View.GONE);
                           a1.setVisibility(View.GONE);
                           q2.setVisibility(View.VISIBLE);
                           button.setVisibility(View.GONE);
                           q2.setText("Thank you!");
                           q2.setTextSize(16);
                           isOkay=true;
                       }
                   }
               }
           });

    }
}