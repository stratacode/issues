import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.stream.Stream;
import java.util.stream.Collectors;

public class ComplexTry {
    public void foo() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("test"), "utf-8"));
             ByteArrayOutputStream baos = new ByteArrayOutputStream();
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(baos, "utf-8"));) {
        } catch (Exception e) {
        }
    }

    public void bar() {
        Stream s1 = Stream.of(1, 2);
        Stream s2 = Stream.of(3, 4);
        try (Stream s3 = Stream.concat(s1, s2)) {
            s3.collect(Collectors.toList());
        }
    }
}
