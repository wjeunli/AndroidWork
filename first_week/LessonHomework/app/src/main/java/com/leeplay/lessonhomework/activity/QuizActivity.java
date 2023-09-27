package com.leeplay.lessonhomework.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.leeplay.lessonhomework.R;
import com.leeplay.lessonhomework.model.Question;
import com.leeplay.lessonhomework.utils.Constants;


public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = "QuizActivity Class";
    private static final String KEY_INDEX = "index";
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_java, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    private int mCurrentIndex = 0;
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;

    private TextView mQuestionTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Log.d(LOG_TAG, "onCreate() called");

        Intent intent = getIntent();
        if (null != intent) {
            Bundle extra = intent.getExtras();
            if (null != extra) {
                String userName = extra.getString(Constants.LOGIN_EXTRA_KEYS);
                getSupportActionBar().setSubtitle(userName);
                TextView greeting = findViewById(R.id.greetingQuestionView);
                greeting.setText("Hello " + userName + ", here is some Questions for you");
            }
        }

        if (null != savedInstanceState) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
        }



        mTrueButton = findViewById(R.id.trueButton);
        mFalseButton = findViewById(R.id.falseButton);
        mNextButton = findViewById(R.id.nextButton);
        mPrevButton = findViewById(R.id.prevButton);

        mQuestionTextView = findViewById(R.id.questionTextView);
        int questionId = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(questionId);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswers(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswers(false);
                /*Toast msg = Toast.makeText(QuizActivity.this, R.string.incrrect_tost, Toast.LENGTH_SHORT);
                msg.setGravity(Gravity.CENTER | Gravity.TOP,0,0);
                msg.show();*/
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getTextResId());
            }
        });

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex - 1) < 0 ? mQuestionBank.length - 1 : mCurrentIndex - 1;
                mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getTextResId());
            }
        });

        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getTextResId());
            }
        });



    }

    private void checkAnswers(boolean userChoice) {
        boolean theAnswer = mQuestionBank[mCurrentIndex].isAnswerTrue();
        if (theAnswer == userChoice) {
            Toast.makeText(QuizActivity.this, R.string.quiz_correct_toast, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(QuizActivity.this, R.string.quiz_incorrect_toast, Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(LOG_TAG, "onSaveInstanceState() called");

        outState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.actionLogout) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy() called");
    }

    @Override
    public void onClick(View view) {

    }


}
