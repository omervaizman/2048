package omervaizman.com;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button[][] buttons = new Button[4][4];
    TableLayout tableLayout;float x1, y1, x2, y2, deltaX, deltaY;
    int sum = 0;
    Cube[][] cubes = new Cube[4][4];
    TextView tvScore;
    Button btnRestart, btnPlay, btnUp, btnDown, btnLeft, btnRight;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttons[0][0] = (Button) findViewById(R.id.b00);
        tableLayout = (TableLayout) findViewById(R.id.TableLayout);
        for(int i=0; i<4; i++) {
            for (int j = 0; j < 4; j++) {
                cubes[i][j] = new Cube(0);
                String buttonID = "b" + i + "" + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = ((Button) findViewById(resID));

            }
        }
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                cubes[i][j] = new Cube(0); }
        tvScore = (TextView) findViewById(R.id.tvScore);
        btnDown = (Button) findViewById(R.id.btnDown);
        btnLeft = (Button) findViewById(R.id.btnLeft);
        btnRight = (Button) findViewById(R.id.btnRight);
        btnUp = (Button) findViewById(R.id.btnUP);
        btnUp.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        btnLeft.setOnClickListener(this);
        btnDown.setOnClickListener(this);
        btnRestart = (Button) findViewById(R.id.btnRestart);
        btnRestart.setOnClickListener(this);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(this);
        btnRight.setClickable(false);
        btnUp.setClickable(false);
        btnLeft.setClickable(false);
        btnDown.setClickable(false);
    }

    @Override
    public void onClick(View v) {
        if (v == btnPlay) {
            Play();
            btnDown.setClickable(true);
            btnLeft.setClickable(true);
            btnRight.setClickable(true);
            btnUp.setClickable(true);
            btnPlay.setClickable(false);
            btnRestart.setClickable(true);
        }
        if (v == btnRestart) {
            Restart();
            btnPlay.setClickable(true);
        }

        if (v == btnDown) {
            for (int j = 0; j < 4; j++) {
                Down(j);
            }
            RandCube();
            if (isLost()) {
                Toast.makeText(this, "you lost", Toast.LENGTH_LONG).show();
            }
        }
        if (v == btnLeft) {
            for (int i = 0; i < 4; i++) {
                Left(i);
            }
            RandCube();
            if (isLost()) {
                Toast.makeText(this, "you lost", Toast.LENGTH_LONG).show();
            }
        }
        if (v == btnRight) {
            for (int i = 0; i < 4; i++) {
                Right(i);
            }
            RandCube();
            if (isLost()) {
                Toast.makeText(this, "you lost", Toast.LENGTH_LONG).show();
            }
        }
        if (v == btnUp) {
            for (int j = 0; j < 4; j++) {
                Up(j);
            }
            RandCube();
            if (isLost()) {
                Toast.makeText(this, "you lost", Toast.LENGTH_LONG).show();
            }
        }

    }

    public void Play() {
        RandCube();
        RandCube();
    }

    public void Restart() {
        ScoreSum(-sum);
        sum = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cubes[i][j].setValue(0);
                backgroundToButton(i, j);
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    public void backgroundToButton(int i, int j) {
        if (cubes[i][j].getValue() == 0) {
            buttons[i][j].setBackgroundResource(R.drawable.shaped_button);
            buttons[i][j].setText("");
        }
        if (cubes[i][j].getValue() == 2) {
            buttons[i][j].setBackgroundResource(R.drawable.v2);
            buttons[i][j].setText("" + cubes[i][j].getValue() + "");
        }
        if (cubes[i][j].getValue() == 4) {
            buttons[i][j].setBackgroundResource(R.drawable.v4);
            buttons[i][j].setText("" + cubes[i][j].getValue() + "");
        }
        if (cubes[i][j].getValue() == 8) {
            buttons[i][j].setBackgroundResource(R.drawable.v8);
            buttons[i][j].setText("" + cubes[i][j].getValue() + "");
        }
        if (cubes[i][j].getValue() == 16) {
            buttons[i][j].setBackgroundResource(R.drawable.v16);
            buttons[i][j].setText("" + cubes[i][j].getValue() + "");
        }
        if (cubes[i][j].getValue() == 32) {
            buttons[i][j].setBackgroundResource(R.drawable.v32);
            buttons[i][j].setText("" + cubes[i][j].getValue() + "");
        }
        if (cubes[i][j].getValue() == 64) {
            buttons[i][j].setBackgroundResource(R.drawable.v64);
            buttons[i][j].setText("" + cubes[i][j].getValue() + "");
        }
        if (cubes[i][j].getValue() == 128) {
            buttons[i][j].setBackgroundResource(R.drawable.v128);
            buttons[i][j].setText("" + cubes[i][j].getValue() + "");
        }
        if (cubes[i][j].getValue() == 256) {
            buttons[i][j].setBackgroundResource(R.drawable.v256);
            buttons[i][j].setText("" + cubes[i][j].getValue() + "");
        }
        if (cubes[i][j].getValue() == 512) {
            buttons[i][j].setBackgroundResource(R.drawable.v512);
            buttons[i][j].setText("" + cubes[i][j].getValue() + "");
        }
        if (cubes[i][j].getValue() == 1024) {
            buttons[i][j].setBackgroundResource(R.drawable.v1024);
            buttons[i][j].setText("" + cubes[i][j].getValue() + "");
        }
        if (cubes[i][j].getValue() == 2048) {
            buttons[i][j].setBackgroundResource(R.drawable.v2048);
            buttons[i][j].setText("" + cubes[i][j].getValue() + "");
        }
    }

    public void RandCube() {
        int[] two_four = new int[2];
        two_four[0] = 2;
        two_four[1] = 4;
        int value, i, j;
        Random random = new Random();
        int num = random.nextInt(2 - 0);
        value = two_four[num];
        i = random.nextInt(4 - 0);
        j = random.nextInt(4 - 0);
        if (cubes[i][j].getValue() != 0) {
            while (cubes[i][j].getValue() != 0) {
                i = random.nextInt(3 - 0);
                j = random.nextInt(3 - 0);
            }
        }
        cubes[i][j].setValue(value);
        backgroundToButton(i, j);


    }

    public boolean isLost() {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (cubes[i][j].getValue() == 0)
                    return false;
            }
        }
        return true;
    }

    public void Down(int j) {
        if (cubes[3][j].getValue() == cubes[2][j].getValue()) {
            cubes[3][j].setValue((cubes[3][j].getValue()) * 2);
            cubes[2][j].setValue(0);
            ScoreSum((cubes[3][j].getValue()));
        } else if (cubes[2][j].getValue() == cubes[1][j].getValue()) {
            cubes[2][j].setValue((cubes[2][j].getValue()) * 2);
            cubes[1][j].setValue(0);
            ScoreSum((cubes[2][j].getValue()));
        } else if (cubes[1][j].getValue() == cubes[0][j].getValue()) {
            cubes[1][j].setValue((cubes[1][j].getValue()) * 2);
            cubes[0][j].setValue(0);
            ScoreSum((cubes[1][j].getValue()));
        } else if (cubes[1][j].getValue() == cubes[3][j].getValue() && cubes[2][j].getValue() == 0) {
            cubes[3][j].setValue((cubes[3][j].getValue()) * 2);
            cubes[1][j].setValue(0);
            ScoreSum((cubes[3][j].getValue()));
        } else if (cubes[0][j].getValue() == cubes[2][j].getValue() && cubes[1][j].getValue() == 0) {
            cubes[2][j].setValue((cubes[2][j].getValue()) * 2);
            cubes[0][j].setValue(0);
            ScoreSum((cubes[2][j].getValue()));
        } else if (cubes[0][j].getValue() == cubes[3][j].getValue() && cubes[1][j].getValue() == 0 && cubes[2][j].getValue() == 0) {
            cubes[3][j].setValue((cubes[3][j].getValue()) * 2);
            cubes[0][j].setValue(0);
            ScoreSum((cubes[3][j].getValue()));
        }
        for (int i = 0; i < 4; i++) {
            if (cubes[3][j].getValue() == 0 && cubes[2][j].getValue() != 0) {
                cubes[3][j].setValue(cubes[2][j].getValue());
                cubes[2][j].setValue(0);
            }
            if (cubes[2][j].getValue() == 0 && cubes[1][j].getValue() != 0) {
                cubes[2][j].setValue(cubes[1][j].getValue());
                cubes[1][j].setValue(0);
            }
            if (cubes[1][j].getValue() == 0 && cubes[0][j].getValue() != 0) {
                cubes[1][j].setValue(cubes[0][j].getValue());
                cubes[0][j].setValue(0);
            }

        }
        for (int i = 0; i < 4; i++) {
            backgroundToButton(i, j);
        }


    }

    public void Left(int i) {
        if (cubes[i][1].getValue() == cubes[i][0].getValue()) {
            cubes[i][0].setValue((cubes[i][0].getValue()) * 2);
            cubes[i][1].setValue(0);
            ScoreSum((cubes[i][0].getValue()));
        } else if (cubes[i][2].getValue() == cubes[i][1].getValue()) {
            cubes[i][1].setValue((cubes[i][1].getValue()) * 2);
            cubes[i][2].setValue(0);
            ScoreSum((cubes[i][1].getValue()));
        } else if (cubes[i][3].getValue() == cubes[i][2].getValue()) {
            cubes[i][2].setValue((cubes[i][2].getValue()) * 2);
            cubes[i][3].setValue(0);
            ScoreSum((cubes[i][2].getValue()));
        }


        //**************************************************************
        else if (cubes[i][1].getValue() == cubes[i][3].getValue() && cubes[i][2].getValue() == 0) {
            cubes[i][1].setValue((cubes[i][1].getValue()) * 2);
            cubes[i][3].setValue(0);
            ScoreSum((cubes[i][1].getValue()));
        } else if (cubes[i][0].getValue() == cubes[i][2].getValue() && cubes[i][1].getValue() == 0) {
            cubes[i][0].setValue((cubes[i][0].getValue()) * 2);
            cubes[i][2].setValue(0);
            ScoreSum((cubes[i][0].getValue()));
        } else if (cubes[i][0].getValue() == cubes[i][3].getValue() && cubes[i][1].getValue() == 0 && cubes[i][2].getValue() == 0) {
            cubes[i][0].setValue((cubes[i][0].getValue()) * 2);
            cubes[i][3].setValue(0);
            ScoreSum((cubes[i][0].getValue()));
        }
        //******************************************************************
        for (int k = 0; k < 4; k++) {
            if (cubes[i][0].getValue() == 0 && cubes[i][1].getValue() != 0) {
                cubes[i][0].setValue(cubes[i][1].getValue());
                cubes[i][1].setValue(0);
            }
            if (cubes[i][1].getValue() == 0 && cubes[i][2].getValue() != 0) {
                cubes[i][1].setValue(cubes[i][2].getValue());
                cubes[i][2].setValue(0);
            }
            if (cubes[i][2].getValue() == 0 && cubes[i][3].getValue() != 0) {
                cubes[i][2].setValue(cubes[i][3].getValue());
                cubes[i][3].setValue(0);
            }

        }
        for (int j = 0; j < 4; j++) {
            backgroundToButton(i, j);
        }

    }

    public void Right(int i) {
        if (cubes[i][3].getValue() == cubes[i][2].getValue()) {
            cubes[i][3].setValue((cubes[i][3].getValue()) * 2);
            cubes[i][2].setValue(0);
        } else if (cubes[i][2].getValue() == cubes[i][1].getValue()) {
            cubes[i][2].setValue((cubes[i][2].getValue()) * 2);
            cubes[i][1].setValue(0);
        } else if (cubes[i][1].getValue() == cubes[i][0].getValue()) {
            cubes[i][1].setValue((cubes[i][1].getValue()) * 2);
            cubes[i][0].setValue(0);
        }
        //**************************************************************
        else if (cubes[i][1].getValue() == cubes[i][3].getValue() && cubes[i][2].getValue() == 0) {
            cubes[i][3].setValue((cubes[i][3].getValue()) * 2);
            cubes[i][1].setValue(0);
        } else if (cubes[i][0].getValue() == cubes[i][2].getValue() && cubes[i][1].getValue() == 0) {
            cubes[i][2].setValue((cubes[i][2].getValue()) * 2);
            cubes[i][0].setValue(0);
        } else if (cubes[i][0].getValue() == cubes[i][3].getValue() && cubes[i][1].getValue() == 0 && cubes[i][2].getValue() == 0) {
            cubes[i][3].setValue((cubes[i][3].getValue()) * 2);
            cubes[i][0].setValue(0);
        }
        //******************************************************************
        for (int k = 0; k < 4; k++) {
            if (cubes[i][3].getValue() == 0 && cubes[i][2].getValue() != 0) {
                cubes[i][3].setValue(cubes[i][2].getValue());
                cubes[i][2].setValue(0);
            }
            if (cubes[i][2].getValue() == 0 && cubes[i][1].getValue() != 0) {
                cubes[i][2].setValue(cubes[i][1].getValue());
                cubes[i][1].setValue(0);
            }
            if (cubes[i][1].getValue() == 0 && cubes[i][0].getValue() != 0) {
                cubes[i][1].setValue(cubes[i][0].getValue());
                cubes[i][0].setValue(0);
            }
        }
        for (int j = 0; j < 4; j++) {
            backgroundToButton(i, j);
        }
    }

    public void Up(int j) {
        if (cubes[1][j].getValue() == cubes[0][j].getValue()) {
            cubes[0][j].setValue((cubes[0][j].getValue()) * 2);
            cubes[1][j].setValue(0);
            ScoreSum((cubes[0][j].getValue()));
        } else if (cubes[2][j].getValue() == cubes[1][j].getValue()) {
            cubes[1][j].setValue((cubes[1][j].getValue()) * 2);
            cubes[2][j].setValue(0);
            ScoreSum((cubes[1][j].getValue()));
        } else if (cubes[3][j].getValue() == cubes[2][j].getValue()) {
            cubes[2][j].setValue((cubes[2][j].getValue()) * 2);
            cubes[3][j].setValue(0);
            ScoreSum((cubes[2][j].getValue()));
        }


        //**************************************************************************************
        else if (cubes[1][j].getValue() == cubes[3][j].getValue() && cubes[2][j].getValue() == 0) {
            cubes[1][j].setValue((cubes[1][j].getValue()) * 2);
            cubes[3][j].setValue(0);
            ScoreSum((cubes[1][j].getValue()));
        } else if (cubes[0][j].getValue() == cubes[2][j].getValue() && cubes[1][j].getValue() == 0) {
            cubes[0][j].setValue((cubes[0][j].getValue()) * 2);
            cubes[2][j].setValue(0);
            ScoreSum((cubes[0][j].getValue()));
        } else if (cubes[0][j].getValue() == cubes[3][j].getValue() && cubes[1][j].getValue() == 0 && cubes[2][j].getValue() == 0) {
            cubes[0][j].setValue((cubes[0][j].getValue()) * 2);
            cubes[3][j].setValue(0);
            ScoreSum((cubes[0][j].getValue()));
        }
        //**************************************************************************************
        for (int i = 0; i < 4; i++) {
            if (cubes[0][j].getValue() == 0 && cubes[1][j].getValue() != 0) {
                cubes[0][j].setValue(cubes[1][j].getValue());
                cubes[1][j].setValue(0);
            }
            if (cubes[1][j].getValue() == 0 && cubes[2][j].getValue() != 0) {
                cubes[1][j].setValue(cubes[2][j].getValue());
                cubes[2][j].setValue(0);
            }
            if (cubes[2][j].getValue() == 0 && cubes[3][j].getValue() != 0) {
                cubes[2][j].setValue(cubes[3][j].getValue());
                cubes[3][j].setValue(0);
            }


        }
        //**************************************************************************************
        for (int i = 0; i < 4; i++) {
            backgroundToButton(i, j);
        }

    }

    public void ScoreSum(int n) {
        sum += n;
        tvScore.setText(" score:" + sum + "");
    }


}