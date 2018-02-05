package mkuzdowicz.drawingapp.features.canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CanvasComponent {

    private final int widthWithBorder;
    private final int heightWithBorder;

    private final List<List<String>> pixelMatrix;

    private static final String BLANK_PIXEL = " ";
    private final static String VERTICAL_BORDER = "|";
    private final static String HORIZONTAL_BORDER = "-";
    private final static String CHARACTER_TO_DRAW_LINES = "x";

    public CanvasComponent(final int width, final int height) {
        requireNonNegativeAndNonZero(width);
        requireNonNegativeAndNonZero(height);

        this.widthWithBorder = width + 1;
        this.heightWithBorder = height + 1;
        this.pixelMatrix = buildPixelMatrix();

    }

    private List<List<String>> buildPixelMatrix() {
        final List<List<String>> newPixelMatrix = new ArrayList<>();
        IntStream.rangeClosed(0, heightWithBorder).forEach(colIdx -> {
            List<String> row = new ArrayList<>();
            IntStream.rangeClosed(0, widthWithBorder).forEach(rowIdx -> {
                if (colIdx == 0 || colIdx == heightWithBorder)
                    row.add(HORIZONTAL_BORDER);
                else if (rowIdx == 0 || rowIdx == widthWithBorder)
                    row.add(VERTICAL_BORDER);
                else
                    row.add(BLANK_PIXEL);
            });
            newPixelMatrix.add(row);
        });
        return newPixelMatrix;
    }

    public List<List<String>> canvas() {
        return this.pixelMatrix;
    }

    public String canvasAsPrintableString() {
        return canvas().stream()
                .map(row -> row.stream()
                        .collect(Collectors.joining()))
                .collect(Collectors
                        .joining("\n"));
    }

    public void drawLine(final int x1, final int y1, final int x2, final int y2) {
        requireNonNegativeAndNonZero(x1);
        requireNonNegativeAndNonZero(y1);
        requireNonNegativeAndNonZero(x2);
        requireNonNegativeAndNonZero(y2);

        if (y1 == y2)
            drawHorizontalLine(x1, x2, y1);

        else if (x1 == x2)
            drawVerticalLine(y1, y2, x1);
        else
            throw new UnsupportedOperationException();
    }

    private void drawHorizontalLine(final int x1, final int x2, final int y) {
        IntStream.rangeClosed(x1, x2).forEach(idx -> pixelMatrix.get(y).set(idx, CHARACTER_TO_DRAW_LINES));
    }

    private void drawVerticalLine(final int y1, final int y2, final int x) {
        IntStream.rangeClosed(y1, y2).forEach(idx -> pixelMatrix.get(idx).set(x, CHARACTER_TO_DRAW_LINES));
    }

    public void drawRectangle(final int x1, final int y1, final int x2, final int y2) {
        requireNonNegativeAndNonZero(x1);
        requireNonNegativeAndNonZero(y1);
        requireNonNegativeAndNonZero(x2);
        requireNonNegativeAndNonZero(y2);

        drawLine(x1, y1, x2, y1);
        drawLine(x1, y2, x2, y2);
        drawLine(x1, y1, x1, y2);
        drawLine(x2, y1, x2, y2);
    }

    public void fill(final int x, final int y, final String colour) {
        Objects.requireNonNull(colour);
        requireNonNegativeAndNonZero(x);
        requireNonNegativeAndNonZero(y);

        recursiveFloodFill(x, y, colour);
    }

    private void recursiveFloodFill(final int x, final int y, final String colour) {

        if (pointIsOutOfBounds(x, y))
            return;

        if (pixelAtGivenLocationIsNotBlank(x, y))
            return;

        pixelMatrix.get(y).set(x, colour);

        recursiveFloodFill(x + 1, y, colour);
        recursiveFloodFill(x - 1, y, colour);
        recursiveFloodFill(x, y + 1, colour);
        recursiveFloodFill(x, y - 1, colour);
    }

    private boolean pixelAtGivenLocationIsNotBlank(final int x, final int y) {
        return (!pixelMatrix.get(y).get(x).equals(BLANK_PIXEL));
    }

    public boolean pointIsOutOfBounds(final int x, final int y) {
        return (x < 0 || y < 0 || x > (widthWithBorder - 1) || y > (heightWithBorder - 1));
    }

    private void requireNonNegativeAndNonZero(int n) {
        if (n < 0) throw new IllegalArgumentException();
    }
}
