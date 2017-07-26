package apps.lnsel.com.booksearch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by apps2 on 7/26/2017.
 */
public class BooksAdapter extends BaseAdapter {
    Context context;
    private static LayoutInflater inflater=null;

    private List<BooksData> booksList = null;
    private ArrayList<BooksData> arraylist;

    public BooksAdapter(Activity context, List<BooksData> booksList) {
        this.context = context;

        this.booksList = booksList;
        this.arraylist = new ArrayList<BooksData>();
        this.arraylist.addAll(booksList);

        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public View getView(final int position, final View view, ViewGroup parent) {
        View rowView=inflater.inflate(R.layout.cardview_book, null,true);

        TextView tv_name = (TextView) rowView.findViewById(R.id.cardview_books_tv_name);
        TextView tv_author = (TextView) rowView.findViewById(R.id.cardview_books_tv_author);
        TextView tv_description = (TextView) rowView.findViewById(R.id.cardview_books_tv_description);
        ImageButton ib_delete = (ImageButton) rowView.findViewById(R.id.cardview_book_ib_delete_contact);

        tv_name.setText(booksList.get(position).getbName());
        tv_author.setText(booksList.get(position).getbAuthor());
        tv_description.setText(booksList.get(position).getbDescription());

        ib_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bId = booksList.get(position).getbId();
                ((MainActivity)context).deleteBook(bId);
            }
        });



        return rowView;

    }


    // Filter Class
    public void filter(String charText, View btn_clear) {
        charText = charText.toLowerCase(Locale.getDefault());
        booksList.clear();
        if (charText.length() == 0||charText.equalsIgnoreCase("")) {
            booksList.addAll(arraylist);
            btn_clear.setVisibility(View.GONE);
        }
        else
        {
            for (BooksData wp : arraylist)
            {
                if (wp.getbName().toLowerCase(Locale.getDefault()).contains(charText)||
                        wp.getbAuthor().toLowerCase(Locale.getDefault()).contains(charText)||
                        wp.getbDescription().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    booksList.add(wp);
                }
            }
            btn_clear.setVisibility(View.VISIBLE);
        }
        notifyDataSetChanged();
    }





    @Override
    public int getCount() {
        return booksList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
