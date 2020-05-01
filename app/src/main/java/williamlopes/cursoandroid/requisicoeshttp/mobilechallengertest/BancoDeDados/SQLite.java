package williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.BancoDeDados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite extends SQLiteOpenHelper {

    // The database name
    private static final String DATABASE_NAME = "github.db"; //nome

    // If you change the database schema, you must increment the database version
    private static final int DATABASE_VERSION = 8; //quando alterar o onCreate, altere a versão

    // Constructor
    public SQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create a table to hold data
        final String SQL_CREATE_ITEMS_TABLE = "CREATE TABLE " + GithubContract.ItemsEntry.tabelaNome + " (" +
                GithubContract.ItemsEntry.colunaId + " INTEGER PRIMARY KEY," +
                GithubContract.ItemsEntry.colunaName + " TEXT NOT NULL, " +
                GithubContract.ItemsEntry.colunaDescription + " TEXT ," +
                GithubContract.ItemsEntry.colunaIdOwner + " INTEGER ," +
                GithubContract.ItemsEntry.colunaLanguage + " TEXT ," +
                GithubContract.ItemsEntry.colunaOpenIssues + " INTEGER ," +
                GithubContract.ItemsEntry.colunaCreatedAt + " TEXT ," +
                GithubContract.ItemsEntry.colunaStars + " INTEGER ," +
                GithubContract.ItemsEntry.colunaForks + " INTEGER ," +
                GithubContract.ItemsEntry.colunaClosedIssues + " INTEGER ," +
                GithubContract.ItemsEntry.colunaHtmlUrl + " TEXT " +
                "); ";

        db.execSQL(SQL_CREATE_ITEMS_TABLE);

        // Create a table to hold data
        final String SQL_CREATE_OWNER_TABLE = "CREATE TABLE " + GithubContract.OwnerEntry.tabelaNome + " (" +
                GithubContract.OwnerEntry.colunaId + " INTEGER PRIMARY KEY," +
                GithubContract.OwnerEntry.colunaLogin + " TEXT NOT NULL, " +
                GithubContract.OwnerEntry.colunaAvatar + " TEXT " +
                "); ";

        db.execSQL(SQL_CREATE_OWNER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GithubContract.ItemsEntry.tabelaNome);
        db.execSQL("DROP TABLE IF EXISTS " + GithubContract.OwnerEntry.tabelaNome);
        onCreate(db);
    }
}
