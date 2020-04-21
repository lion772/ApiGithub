package williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.BancoDeDados;

import android.provider.BaseColumns;

public class GithubContract {

    public static final class ItemsEntry implements BaseColumns {
        public static final String tabelaNome = "item";
        public static final String colunaName = "name";
        public static final String colunaDescription = "description";
        public static final String colunaId = "id";
    }

    public static final class OwnerEntry implements BaseColumns {
        public static final String tabelaNome = "owner";
        public static final String colunaLogin = "login";
        public static final String colunaAvatar = "avatar";
        public static final String colunaId = "id";
    }
}
