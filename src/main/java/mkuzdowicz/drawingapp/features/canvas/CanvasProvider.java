package mkuzdowicz.drawingapp.features.canvas;


public class CanvasProvider {

    private CanvasComponent canvasComponent;

    public void createNewCanvas(final int width, final int height) {
        canvasComponent = new CanvasComponent(width, height);
    }

    public CanvasComponent currentCanvasState() {
        return this.canvasComponent;
    }

    public void drawLineOnCanvas(final int x1, final int y1, final int x2, final int y2) {
        canvasComponent.drawLine(x1, y1, x2, y2);
    }

    public void drawRectangleOnCanvas(final int x1, final int y1, final int x2, final int y2) {
        canvasComponent.drawRectangle(x1, y1, x2, y2);
    }

    public void fillCanvas(final int x, final int y, final String colour) {
        canvasComponent.fill(x, y, colour);
    }

    public String getCanvasAsPrintableString(){
        return currentCanvasState().canvasAsPrintableString();
    }
}
