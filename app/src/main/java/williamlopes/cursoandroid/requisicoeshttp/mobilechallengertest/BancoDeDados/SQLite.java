package williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.BancoDeDados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite extends SQLiteOpenHelper {

    // The database name
    private static final String DATABASE_NAME = "github.db"; //nome

    // If you change the database schema, you must increment the database version
    private static final int DATABASE_VERSION = 1;

    // Constructor
    public SQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create a table to hold waitlist data
        final String SQL_CREATE_ITEMS_TABLE = "CREATE TABLE " + GithubContract.ItemsEntry.tabelaNome + " (" +
                GithubContract.ItemsEntry.colunaId + " INTEGER PRIMARY KEY," +
                GithubContract.ItemsEntry.colunaName + " TEXT NOT NULL, " +
                GithubContract.ItemsEntry.colunaDescription + " TEXT " +
                "); ";

        db.execSQL(SQL_CREATE_ITEMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GithubContract.ItemsEntry.tabelaNome);
        onCreate(db);
    }
}
