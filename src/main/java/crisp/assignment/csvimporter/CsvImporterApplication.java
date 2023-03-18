package crisp.assignment.csvimporter;


import crisp.assignment.csvimporter.configuration.Transformer;

public class CsvImporterApplication  {
    public static void main(String[] args) {
        Transformer transformer = Transformer.getInstance("D:\\Lesson\\project\\csv-importer\\src\\main\\resources\\csv-reader.properties");
        transformer.load("D://order.csv");
    }

}
