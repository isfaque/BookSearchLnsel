package apps.lnsel.com.booksearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by apps2 on 7/25/2017.
 */
public class AddActivity extends AppCompatActivity {

    EditText et_name,et_author,et_description;
    Button btn_submit, btn_cancel;

    DatabaseHandler DB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Add Book");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        DB=new DatabaseHandler(AddActivity.this);

        et_name = (EditText) findViewById(R.id.activity_add_et_book_name);
        et_author = (EditText) findViewById(R.id.activity_add_et_author_name);
        et_description = (EditText) findViewById(R.id.activity_add_et_description);

        btn_cancel = (Button) findViewById(R.id.activity_add_btn_cancel);
        btn_submit = (Button) findViewById(R.id.activity_add_btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_name.getText().toString().isEmpty()){
                    Toast.makeText(AddActivity.this, "Please Enter Book Name", Toast.LENGTH_SHORT).show();
                }else if(et_author.getText().toString().isEmpty()){
                    Toast.makeText(AddActivity.this, "Please Enter Author Name", Toast.LENGTH_SHORT).show();
                }else if(et_description.getText().toString().isEmpty()){
                    Toast.makeText(AddActivity.this, "Please Enter Description", Toast.LENGTH_SHORT).show();
                }else{
                    submitBook();
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    public void submitBook(){
        String bId = "";
        String bName = et_name.getText().toString().trim();
        String bAuthor = et_name.getText().toString().trim();
        String bDescription = et_name.getText().toString().trim();
        BooksData books = new BooksData(bId,bName,bAuthor,bDescription);
        BooksModel.addBook(DB,books);

        Intent intent = new Intent(AddActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
