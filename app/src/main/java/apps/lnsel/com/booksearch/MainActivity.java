package apps.lnsel.com.booksearch;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by apps2 on 7/25/2017.
 */
public class MainActivity extends AppCompatActivity {

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    BooksAdapter adapter;

    FloatingActionButton fabtn_add_book;

    Button btn_clear;

    private ProgressDialog progress;

    ListView list;
    EditText et_book_search;

    DatabaseHandler DB;

    public static ArrayList<BooksData> arrayListBook;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        DB=new DatabaseHandler(MainActivity.this);

        fabtn_add_book = (FloatingActionButton) findViewById(R.id.activity_main_fabtn_add_book);
        list = (ListView) findViewById(R.id.list_view);
        et_book_search = (EditText) findViewById(R.id.activity_main_et_book_search);
        btn_clear = (Button) findViewById(R.id.activity_main_btn_clear);
        fabtn_add_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_book_search.setText("");
            }
        });


        getBooks();



    }

    public void getBooks(){
        arrayListBook = BooksModel.getAllBooks(DB);

        adapter=new BooksAdapter(this, arrayListBook);
        list.setAdapter(adapter);

        et_book_search.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                // When user changed the Text
                String text = et_book_search.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text, btn_clear);

            }
        });

    }

    public void deleteBook(String bId){
        BooksModel.deleteBooksById(DB, bId);
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        else { Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show(); }

        mBackPressed = System.currentTimeMillis();
    }


}
