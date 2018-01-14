package com.cs.dal.minesweeper;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import static com.cs.dal.minesweeper.loadGridPage.mygameplay;

/**
 * Created by rohitgs on 10/16/17.
 */

 public class gridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return gamePlay.row_number * gamePlay.column_number;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return mygameplay.getCellAt(position);
        }
    }

