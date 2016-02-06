import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ComplexTry {
    public void foo() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("test"), "utf-8"));
             ByteArrayOutputStream baos = new ByteArrayOutputStream();
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(baos, "utf-8"));) {
        } catch (Exception e) {
        }
    }
}
