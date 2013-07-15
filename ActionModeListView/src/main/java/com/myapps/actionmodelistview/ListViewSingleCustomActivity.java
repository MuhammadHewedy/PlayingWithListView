package com.myapps.actionmodelistview;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

/**
 * Created by mohammad_abdullah on 7/15/13.
 */
public class ListViewSingleCustomActivity extends ListActivity {

    private ListView mListView;
    private MyArrayAdapter mAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_single_custom);

        mListView = getListView();

        MyModel[] dataList = new MyModel[]{new MyModel("Khalid", "Annajar"), new MyModel("Alla", "Khalil"),
                new MyModel("Muhammad", "Hewedy")};
        mAdapter = new MyArrayAdapter(this, R.layout.my_simple_list_item_single_choice, dataList);
        setListAdapter(mAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mAdapter.setSelectedIndex(position);
        mAdapter.notifyDataSetChanged();
    }

    public void buttonClicked(View view) {
        Toast.makeText(this, mListView.getCheckedItemPosition() + "", Toast.LENGTH_SHORT).show();
    }

    static class MyArrayAdapter extends ArrayAdapter<MyModel> {

        private final int mTextViewResourceId;
        private int mSelectedIndex = -1;

        public MyArrayAdapter(Context context, int textViewResourceId, MyModel[] objects) {
            super(context, 0, objects);
            this.mTextViewResourceId = textViewResourceId;
        }

        public void setSelectedIndex(int i) {
            mSelectedIndex = i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(mTextViewResourceId, parent, false);

                holder = new ViewHolder();
                holder.textView = (TextView) convertView.findViewById(R.id.textView);
                holder.textView2 = (TextView) convertView.findViewById(R.id.textView2);
                holder.radioButton = (RadioButton) convertView.findViewById(R.id.radioButton);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            MyModel myModel = getItem(position);

            holder.textView.setText(myModel.firstName);
            holder.textView2.setText(myModel.lastName);
            holder.radioButton.setChecked(position == mSelectedIndex);

            return convertView;
        }

        static class ViewHolder {
            TextView textView, textView2;
            RadioButton radioButton;
        }
    }

    class MyModel {
        String firstName, lastName;

        MyModel(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }
}