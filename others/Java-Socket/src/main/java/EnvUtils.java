import io.github.cdimascio.dotenv.Dotenv;

public class EnvUtils {

    public static final Dotenv dotenv = Dotenv.configure().load();

    public static String get(String key) {
        return dotenv.get(key);
    }

}
