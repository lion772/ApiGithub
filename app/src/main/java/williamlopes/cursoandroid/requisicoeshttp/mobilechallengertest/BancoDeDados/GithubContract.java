package williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.BancoDeDados;

import android.provider.BaseColumns;

public class GithubContract {

    public static final class ItemsEntry implements BaseColumns {
        public static final String tabelaNome = "item";
        public static final String colunaName = "name";
        public static final String colunaDescription = "description";
        public static final String colunaId = "id";
        public static final String colunaIdOwner = "idOwner";
        public static final String colunaLanguage = "language";
        public static final String colunaOpenIssues = "open_issues";
        public static final String colunaCreatedAt = "created_at";
        public static final String colunaStars = "stargazers_count";
        public static final String colunaForks = "forks";
        public static final String colunaClosedIssues = "closed_issues";
        public static final String colunaHtmlUrl = "html_url";
    }

    public static final class OwnerEntry implements BaseColumns {
        public static final String tabelaNome = "owner";
        public static final String colunaLogin = "login";
        public static final String colunaAvatar = "avatar_url";
        public static final String colunaId = "id";
    }
}
