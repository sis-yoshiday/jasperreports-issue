import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import static java.util.Collections.singleton;

public class Main {

  private static final String TEXT_SHORT = "abc";
  private static final String TEXT_LONG = IntStream.range(0, 36).boxed()
      .map(i -> "abc\n")
      .collect(Collectors.joining(""));

  public static void main(String[] args) throws IOException, JRException {
    tryMinimumExample();// but not represent
    tryOriginalExample();
  }

  private static void tryMinimumExample() throws IOException, JRException {

    JasperReport report = compile("minimum_report.jrxml");

    output(report, new MinimumModel(TEXT_SHORT, Main.class.getResourceAsStream("duke.png")),
        "minimum_page_size_1.pdf");
    output(report, new MinimumModel(TEXT_LONG, Main.class.getResourceAsStream("duke.png")),
        "minimum_page_size_2.pdf");
  }

  private static void tryOriginalExample() throws IOException, JRException {

    JasperReport report = compile("original_report.jrxml");

    {
      // 6.4.3 duke is displayed
      // 6.5.1 duke is displayed
      // 6.6.0 duke is displayed
      OriginalModel model = new OriginalModel();
      model.setOtherDescription(TEXT_SHORT);
      model.setStamp(Main.class.getResourceAsStream("duke.png"));
      output(report, model, "original_page_size_1.pdf");
    }
    {
      // 6.4.3 duke is displayed
      // 6.5.1 duke is not displayed
      // 6.6.0 duke is not displayed
      OriginalModel model = new OriginalModel();
      model.setOtherDescription(TEXT_LONG);
      model.setStamp(Main.class.getResourceAsStream("duke.png"));
      output(report, model, "original_page_size_2.pdf");
    }
  }

  private static void output(JasperReport report, Object model, String fileName)
      throws IOException, JRException {

    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(singleton(model));

    JasperPrint jasperPrint = JasperFillManager.fillReport(report, new HashMap<>(), dataSource);

    JRPdfExporter exporter = new JRPdfExporter();

    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
    exporter.setExporterOutput(
        new SimpleOutputStreamExporterOutput(new FileOutputStream(fileName)));

    exporter.exportReport();
  }

  private static JasperReport compile(String path) throws JRException, IOException {

    try (InputStream is = Main.class.getResourceAsStream(path)) {
      return JasperCompileManager.compileReport(is);
    }
  }
}
