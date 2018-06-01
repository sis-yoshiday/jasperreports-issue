import java.io.InputStream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MinimumModel {

  private String text;

  private InputStream image;
}
