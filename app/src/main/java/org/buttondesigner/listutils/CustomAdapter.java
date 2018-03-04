package org.buttondesigner.listutils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.buttondesigner.R;

import java.util.ArrayList;

/**
 * Created by adma9717 on 03/03/18.
 *
 * This class is for create a custom adapter for the list view in the main activity
 */

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ListItem> listItems;

    public CustomAdapter(Context context, ArrayList<ListItem> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    /**
     * Inflate the custom appearance for each item in the list
     *
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //instance the custom item in the list
        ListItem item = (ListItem) getItem(i);

        //inflate the custom appearance item
        view = LayoutInflater.from(context).inflate(R.layout.list_items, null);

        //reference the attr of each item
        ImageView upload_icon = view.findViewById(R.id.uploaded_layout);
        TextView layoutName = view.findViewById(R.id.layout_name);
        TextView layoutDescription = view.findViewById(R.id.layout_description);

        //put the title and description of the item
        layoutName.setText(item.getLayoutName());
        layoutDescription.setText(item.getLayoutDescription());

        return view;
    }
}
