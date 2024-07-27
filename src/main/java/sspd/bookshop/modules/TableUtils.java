package sspd.bookshop.modules;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class TableUtils {
    public static void resizeTableColumnsToFitContent(TableView<?> tableView) {
        tableView.getColumns().forEach(column -> {
            column.setPrefWidth(0);
            column.setMinWidth(0);
            column.setMaxWidth(Double.MAX_VALUE); // Allow column to grow


            double maxContentWidth = column.getColumns().stream()
                    .mapToDouble(subColumn -> {
                        double width = 0;
                        for (Object item : tableView.getItems()) {
                            width = Math.max(width, getCellWidth(subColumn, item));
                        }
                        return width;
                    }).max().orElse(100);

            column.setPrefWidth(maxContentWidth + 20); // Add some padding
        });
    }

    private static double getCellWidth(TableColumn<?, ?> column, Object item) {
        String cellText = column.getCellData((Integer) item).toString();
        return cellText.length() * 10;
    }
    public static void setColumnWidthsProportional(TableView<?> tableView) {
        if (tableView.getColumns().isEmpty() || tableView.getWidth() <= 0) {
            return;
        }


        double totalInitialWidth = tableView.getColumns().stream()
                .mapToDouble(TableColumn::getPrefWidth)
                .sum();

        tableView.getColumns().forEach(column -> {
            double proportion = column.getPrefWidth() / totalInitialWidth;
            column.setPrefWidth(tableView.getWidth() * proportion);
        });

        tableView.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            if (newWidth.doubleValue() > 0) {
                double newTotalWidth = newWidth.doubleValue();
                tableView.getColumns().forEach(column -> {
                    double proportion = column.getPrefWidth() / totalInitialWidth;
                    column.setPrefWidth(newTotalWidth * proportion);
                });
            }
        });
    }
}

