package apps.lnsel.com.booksearch;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by apps2 on 7/26/2017.
 */
public class BooksModel {

    public static int getBookCount(DatabaseHandler DB) {
        String countQuery = "SELECT  * FROM " + ConstantDB.TABLE_BOOKS;
        SQLiteDatabase db = DB.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();
    }

    public static void addBook(DatabaseHandler DB,BooksData books) {
        SQLiteDatabase db = DB.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ConstantDB.BOOKS_NAME, books.bName);
        values.put(ConstantDB.BOOKS_AUTHOR, books.bAuthor);
        values.put(ConstantDB.BOOKS_DESCRIPTION,books.bDescription);
        // Inserting Row
        db.insert(ConstantDB.TABLE_BOOKS, null, values);
        db.close(); // Closing database connection
    }


    // Getting All Contacts
    public static ArrayList<BooksData> getAllBooks(DatabaseHandler DB) {
        ArrayList<BooksData> books_list_data=new ArrayList<>();
        String selectQuery = "SELECT * FROM " + ConstantDB.TABLE_BOOKS;

        SQLiteDatabase db = DB.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String bId=cursor.getString(0);
                String bName=cursor.getString(1);
                String bAuthor=cursor.getString(2);
                String bDescription= cursor.getString(3);

                BooksData books = new BooksData(bId,bName,bAuthor,bDescription);
                books_list_data.add(books);

            } while (cursor.moveToNext());
        }

        return books_list_data;

    }

    public static void deleteBooksById(DatabaseHandler DB,String bId){
        SQLiteDatabase db = DB.getWritableDatabase();
        db.execSQL("delete from "+ ConstantDB.TABLE_BOOKS+" WHERE "+ConstantDB.BOOKS_ID+"="+bId);
    }




    //update message status by recId..............................................................
    public static void updateBooksById(DatabaseHandler DB,BooksData books,String bId) {
        SQLiteDatabase db = DB.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ConstantDB.BOOKS_NAME, books.bName);
        values.put(ConstantDB.BOOKS_AUTHOR, books.bAuthor);
        values.put(ConstantDB.BOOKS_DESCRIPTION, books.bDescription);

        db.update(ConstantDB.TABLE_BOOKS,values,ConstantDB.BOOKS_ID + "=?", new String[]{bId});
        db.close(); // Closing database connection
    }

    public static void deleteBooks(DatabaseHandler DB){
        SQLiteDatabase db = DB.getWritableDatabase();
        db.execSQL("delete from "+ ConstantDB.TABLE_BOOKS);
    }
}
