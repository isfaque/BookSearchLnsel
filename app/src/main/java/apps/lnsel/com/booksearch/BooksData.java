package apps.lnsel.com.booksearch;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by apps2 on 7/26/2017.
 */
public class BooksData {

    public String bId;
    public String bName;
    public String bAuthor;
    public String bDescription;

    public BooksData(String bId,String bName,String bAuthor, String bDescription){
        this.bId = bId;
        this.bName = bName;
        this.bAuthor = bAuthor;
        this.bDescription = bDescription;
    }


    public String getbId() {
        return this.bId;
    }
    public String getbName() {
        return this.bName;
    }
    public String getbAuthor() {
        return this.bAuthor;
    }
    public String getbDescription() {
        return this.bDescription;
    }
}
