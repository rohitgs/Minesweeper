package com.cs.dal.minesweeper;


import android.content.Context;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import static com.cs.dal.minesweeper.loadGridPage.mygameplay;

/**
 * Created by rohitgs on 10/17/17.
 */


public class drawImageCell extends View implements View.OnClickListener , View.OnLongClickListener{

    private int i , j;
    private int position;
    private int value;
    private boolean isCellClicked;
    private boolean isFlagged;
    private boolean isBombPresent;
    private boolean isCellReveal;



    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        isBombPresent = false;
        isCellReveal = false;
        isCellClicked = false;
        isFlagged = false;

        if( value == -1 ){
            isBombPresent = true;
        }

        this.value = value;
    }

    public boolean isBombPresent() {
        return isBombPresent;
    }

    public void setBomb(boolean bomb) {
        isBombPresent = bomb;
    }

    public boolean isCellReveal() {
        return isCellReveal;
    }

    public void setReveal() {
        isCellReveal = true;
        invalidate();
    }

    public boolean isCellClicked() {
        return isCellClicked;
    }

    public void setClicked() {
        this.isCellClicked = true;
        this.isCellReveal = true;

        invalidate();
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public int getXPos() {
        return i;
    }

    public int getYPos() {
        return j;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition( int i , int j ){
        this.i = i;
        this.j = j;

        this.position = j * gamePlay.column_number + i;

        invalidate();
    }

    public drawImageCell( Context context , int i , int j ){
        super(context);

        setPosition(i,j);

        setOnClickListener(this);
        setOnLongClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    public void onClick(View v) {

        mygameplay.revealCell( getXPos(), getYPos() );
    }

    @Override
    public boolean onLongClick(View v) {
        mygameplay.flag( getXPos() , getYPos() );

        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawButtonImage(canvas);
        imageDecider(canvas);
    }

    private void drawBombExplodeImage( Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.explosion);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }
    private void drawFlagImage( Canvas canvas ){
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.smile);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawButtonImage(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.cover);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawNormalBomb(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb);
        drawable.setBounds(0, 0, getWidth(), getHeight());
        drawable.draw(canvas);
    }


    /**
     * method to set images
     */
    private void imageDecider(Canvas canvas) {
        if( isFlagged() )
        {
            drawFlagImage(canvas);
        }else if( isCellReveal() && isBombPresent() && !isCellClicked() )
        {
            drawNormalBomb(canvas);
        }else if ((!(isCellReveal() && isBombPresent() && !isCellClicked() )) && (!isFlagged()))
        {
            if( isCellClicked() )
            {
                if( getValue() == -1 )
                {
                    drawBombExplodeImage(canvas);
                }else
                {
                    setImagesOnGrid(canvas);
                }
            }else
            {
                drawButtonImage(canvas);
            }
        }
    }

    private void setImagesOnGrid(Canvas canvas) {
        Drawable drawable = null;

        switch (getValue()) {
            case 0:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.zero);
                break;
            case 1:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.one);
                break;
            case 2:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.two);
                break;
            case 3:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.three);
                break;
            case 4:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.four);
                break;
            case 5:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.five);
                break;
            case 6:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.six);
                break;
            case 7:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.seven);
                break;
            case 8:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.eight);
                break;
        }

        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

}