package mkuzdowicz.drawingapp.features.app;

import mkuzdowicz.drawingapp.features.canvas.CanvasProvider;
import mkuzdowicz.drawingapp.features.drawing.DrawingCommandsController;

public class DrawApplicationCTX {

    final CanvasProvider canvasProvider;
    final DrawingCommandsController drawingCommandsController;

    public DrawApplicationCTX() {
        this.canvasProvider = new CanvasProvider();
        this.drawingCommandsController = new DrawingCommandsController(canvasProvider);
    }

    public DrawingCommandsController controller() {
        return this.drawingCommandsController;
    }
}
