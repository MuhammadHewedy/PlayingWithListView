package com.myapps.actionmodelistview;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Implementation for ActionMode for ListView
 */
public class ListViewWithActionModeActivity extends ListActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_action_mode);

        mListView = getListView();
        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, new String[]{"One",
                "Two", "Three", "Four", "Five", "Six", "Seven", "Eight"}));
        mListView.setMultiChoiceModeListener(new MyMultiChoiceModeListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void buttonClicked(View view) {
        Toast.makeText(this, mListView.getCheckedItemPosition() + "", Toast.LENGTH_SHORT).show();
    }

    class MyMultiChoiceModeListener implements AbsListView.MultiChoiceModeListener {

        @Override
        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater menuInflater = mode.getMenuInflater();
            menuInflater.inflate(R.menu.menu_action_mode, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    }

}
