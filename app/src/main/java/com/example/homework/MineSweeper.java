package com.example.homework;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

public class MineSweeper extends Activity implements View.OnClickListener, View.OnLongClickListener {
    private int WIDTH = 11;
    private int HEIGHT = 17;
    private int mines = 25;
    private int score = 0;
    private int[][] field = new int[HEIGHT][WIDTH];
    private Button[][] cells;


    @Override
    protected void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
        setContentView(R.layout.cells);
        makeCells();

        generate();
    }


    void generate() {
        makeCells();
        int x;
        int y;
        score = 0;
        int tempCount;

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                field[i][j] = 0;
            }
        }

        for (int i = 0; i < mines; i++) {
            y = (int) (Math.random() * HEIGHT);
            x = (int) (Math.random() * WIDTH);
            if (field[y][x] == -1) {
                i--;
                continue;
            }
            field[y][x] = -1;
        }

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (field[i][j] == -1)
                    continue;
                tempCount = 0;
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        if (k == -1 || l == -1 || k == HEIGHT || l == WIDTH)
                            continue;
                        if (field[k][l] == -1)
                            tempCount++;
                    }
                }
                field[i][j] = tempCount;
            }
        }
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                cells[i][j].setBackgroundColor(Color.GRAY);
            }
        }
    }


    @Override
    public void onClick(View v) {

        Button tappedCell = (Button) v;

        int tappedX = getX(tappedCell);
        int tappedY = getY(tappedCell);
        if (((ColorDrawable) cells[tappedY][tappedX].getBackground()).getColor() == Color.GRAY) {
            if (field[tappedY][tappedX] == -1) {
                cells[tappedY][tappedX].setBackgroundColor(Color.RED);
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                dlgAlert.setMessage("Вы проиграли с счётом: " + score + "Начать заново?");
                dlgAlert.setTitle("Вы проиграли!");
                dlgAlert.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        generate();
                    }
                });
                dlgAlert.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                dlgAlert.setCancelable(true);

                dlgAlert.create().show();
            } else {
                score++;
                cells[tappedY][tappedX].setBackgroundColor(Color.WHITE);
                if (field[tappedY][tappedX] == 0) {
                    init(tappedY - 1, tappedX);
                    init(tappedY + 1, tappedX);
                    init(tappedY, tappedX - 1);
                    init(tappedY, tappedX + 1);
                    init(tappedY+1,tappedX+1);
                    init(tappedY+1,tappedX-1);
                    init(tappedY-1,tappedX+1);
                    init(tappedY-1,tappedX-1);
                } else
                    cells[tappedY][tappedX].setText(field[tappedY][tappedX] + "");
                if(score==WIDTH*HEIGHT-mines){
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                    dlgAlert.setMessage("Вы победили!Начать заново?");
                    dlgAlert.setTitle("Вы выиграли!");
                    dlgAlert.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            generate();
                        }
                    });
                    dlgAlert.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    dlgAlert.setCancelable(true);

                    dlgAlert.create().show();
                }
            }
        }
    }


    @Override
    public boolean onLongClick(View v) {

        Button tappedCell = (Button) v;

        int tappedX = getX(tappedCell);
        int tappedY = getY(tappedCell);

        if (((ColorDrawable) cells[tappedY][tappedX].getBackground()).getColor() == Color.GRAY) {
            cells[tappedY][tappedX].setBackgroundColor(Color.YELLOW);
        } else if (((ColorDrawable) cells[tappedY][tappedX].getBackground()).getColor() == Color.YELLOW) {
            cells[tappedY][tappedX].setBackgroundColor(Color.GRAY);
        }
        return false;
    }


    int getX(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[1]);
    }

    int getY(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[0]);
    }


    void makeCells() {
        cells = new Button[HEIGHT][WIDTH];
        GridLayout cellsLayout = (GridLayout) findViewById(R.id.CellsLayout);
        cellsLayout.removeAllViews();
        cellsLayout.setColumnCount(WIDTH);
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                cells[i][j] = (Button) inflater.inflate(R.layout.cell, cellsLayout, false);
                cells[i][j].setOnClickListener(this);
                cells[i][j].setOnLongClickListener(this);
                cells[i][j].setTag(i + "," + j);
                cellsLayout.addView(cells[i][j]);
            }
        }
    }


    void init(int y, int x) {
        if (!(y < 0 || x < 0 || x == WIDTH || y == HEIGHT)&&((ColorDrawable) cells[y][x].getBackground()).getColor() == Color.GRAY) {
            score++;
            if (field[y][x] > 0) {
                cells[y][x].setText(field[y][x] + "");
                cells[y][x].setBackgroundColor(Color.WHITE);
            }
            if (field[y][x] == 0) {
                cells[y][x].setBackgroundColor(Color.WHITE);
                init(y - 1, x);
                init(y + 1, x);
                init(y, x - 1);
                init(y, x + 1);
                init(y+1,x+1);
                init(y+1,x-1);
                init(y-1,x+1);
                init(y-1,x-1);
            }
        }
    }
}
