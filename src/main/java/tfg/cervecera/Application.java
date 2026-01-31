package tfg.cervecera;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.load();

        Map<String, Object> props = new HashMap<>();
        props.put("MYSQL_HOST", dotenv.get("MYSQL_HOST"));
        props.put("MYSQL_PORT", dotenv.get("MYSQL_PORT"));
        props.put("MYSQL_DB", dotenv.get("MYSQL_DB"));
        props.put("MYSQL_USER", dotenv.get("MYSQL_USER"));
        props.put("MYSQL_PASSWORD", dotenv.get("MYSQL_PASSWORD"));

        new SpringApplicationBuilder(Application.class)
                .properties(props)
                .run(args);
    }
}
