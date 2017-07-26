package apps.lnsel.com.booksearch;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by apps2 on 7/26/2017.
 */
public class DatabaseHandler extends SQLiteOpenHelper {


    public DatabaseHandler(Context context) {
        super(context, ConstantDB.DATABASE_NAME, null, ConstantDB.DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CHAT_TABLE = "CREATE TABLE " + ConstantDB.TABLE_BOOKS + "("
                + ConstantDB.BOOKS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + ConstantDB.BOOKS_NAME+ " VARCHAR," + ConstantDB.BOOKS_AUTHOR + " VARCHAR,"
                + ConstantDB.BOOKS_DESCRIPTION + " VARCHAR"+ ")";
        db.execSQL(CREATE_CHAT_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + ConstantDB.TABLE_BOOKS);
        // Create tables again
        onCreate(db);
    }
}
