package com.cs.dal.minesweeper;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

import static com.cs.dal.minesweeper.loadGridPage.mygameplay;
import static com.cs.dal.minesweeper.gamePlay.*;
import static com.cs.dal.minesweeper.mainActivity.No_of_Bombs;

/**
 * Created by rohitgs on 10/13/17.
 */

public class Grid extends GridView {

    /**
     * Loads the gridview using Gridadapter
     */
    public Grid(Context context, AttributeSet attrs) {
        super(context, attrs);
        setNumColumns(row_number);
        setAdapter(new gridAdapter());
     }


}
