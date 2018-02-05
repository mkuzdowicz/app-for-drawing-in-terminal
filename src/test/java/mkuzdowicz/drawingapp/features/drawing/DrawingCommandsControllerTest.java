package mkuzdowicz.drawingapp.features.drawing;

import mkuzdowicz.drawingapp.features.canvas.CanvasProvider;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.spy;
import static org.junit.Assert.assertEquals;

public class DrawingCommandsControllerTest {

    private CanvasProvider canvasProviderSpy;
    private DrawingCommandsController drawingCommandsController;

    @Before
    public void prepare() {
        canvasProviderSpy = spy(new CanvasProvider());
        drawingCommandsController = new DrawingCommandsController(canvasProviderSpy);
    }

    @Test
    public void executeCommandAndGetResult_should_fire_create_canvas_action_and_bind_correct_arguments_for_command_type_with_ignore_Case() {

        drawingCommandsController.executeCommandAndGetResult("C 100 300");
        // check actions expected to fire
        verify(canvasProviderSpy, times(1)).createNewCanvas(100, 300);
        verify(canvasProviderSpy, times(1)).getCanvasAsPrintableString();
        // check actions expected NOT to fire
        verify(canvasProviderSpy, times(0)).drawLineOnCanvas(1, 1, 1, 1);
        verify(canvasProviderSpy, times(0)).drawRectangleOnCanvas(1, 1, 1, 1);
        verify(canvasProviderSpy, times(0)).fillCanvas(1, 1, "u");
    }

    @Test
    public void executeCommandAndGetResult_should_fire_create_canvas_action_and_bind_correct_arguments() {

        drawingCommandsController.executeCommandAndGetResult("c 5 10");
        // check actions expected to fire
        verify(canvasProviderSpy, times(1)).createNewCanvas(5, 10);
        verify(canvasProviderSpy, times(1)).getCanvasAsPrintableString();
        // check actions expected NOT to fire
        verify(canvasProviderSpy, times(0)).drawLineOnCanvas(1, 1, 1, 1);
        verify(canvasProviderSpy, times(0)).drawRectangleOnCanvas(1, 1, 1, 1);
        verify(canvasProviderSpy, times(0)).fillCanvas(1, 1, "u");
    }

    @Test
    public void executeCommandAndGetResult_should_fire_drawLine_action_and_return_error_message_for_no_supported_horizontal_and_vertical_lines() {
        canvasProviderSpy.createNewCanvas(5, 10);
        assertEquals(drawingCommandsController.ONLY_VERTICAL_AND_HORIZONTAL_LINE_SUPPORT, drawingCommandsController.executeCommandAndGetResult("l 1 5 2 6"));

    }

    @Test
    public void executeCommandAndGetResult_should_fire_drawLine_action_and_bind_correct_arguments() {
        // init canvas in spy
        canvasProviderSpy.createNewCanvas(5, 10);
        // check actions expected to fire
        drawingCommandsController.executeCommandAndGetResult("l 1 5 1 6");
        verify(canvasProviderSpy, times(1)).drawLineOnCanvas(1, 5, 1, 6);
        verify(canvasProviderSpy, times(1)).getCanvasAsPrintableString();
        // check actions expected NOT to fire
        verify(canvasProviderSpy, times(0)).createNewCanvas(5, 5);
        verify(canvasProviderSpy, times(0)).drawRectangleOnCanvas(1, 1, 1, 1);
        verify(canvasProviderSpy, times(0)).fillCanvas(1, 1, "u");
    }

    @Test
    public void executeCommandAndGetResult_should_fire_drawRectangle_action_and_bind_correct_arguments() {
        // init canvas in spy
        canvasProviderSpy.createNewCanvas(20, 30);
        // check actions expected to fire
        drawingCommandsController.executeCommandAndGetResult("r 5 6 10 15");
        verify(canvasProviderSpy, times(1)).drawRectangleOnCanvas(5, 6, 10, 15);
        verify(canvasProviderSpy, times(1)).getCanvasAsPrintableString();
        // check actions expected NOT to fire
        verify(canvasProviderSpy, times(0)).createNewCanvas(5, 5);
        verify(canvasProviderSpy, times(0)).drawLineOnCanvas(1, 1, 1, 1);
        verify(canvasProviderSpy, times(0)).fillCanvas(1, 1, "u");
    }

    @Test
    public void executeCommandAndGetResult_should_fire_fill_action_and_bind_correct_arguments() {
        // init canvas in spy
        canvasProviderSpy.createNewCanvas(20, 30);
        // check actions expected to fire
        drawingCommandsController.executeCommandAndGetResult("b 7 9 o");
        verify(canvasProviderSpy, times(1)).fillCanvas(7, 9, "o");
        verify(canvasProviderSpy, times(1)).getCanvasAsPrintableString();
        // check actions expected NOT to fire
        verify(canvasProviderSpy, times(0)).createNewCanvas(5, 5);
        verify(canvasProviderSpy, times(0)).drawLineOnCanvas(1, 1, 1, 1);
        verify(canvasProviderSpy, times(0)).drawRectangleOnCanvas(1, 3, 2, 4);
    }

    @Test
    public void executeCommandAndGetResult_should_fire_quit_action() {
        // check actions expected NOT to fire
        final String result = drawingCommandsController.executeCommandAndGetResult("q");
        verify(canvasProviderSpy, times(0)).drawLineOnCanvas(1, 5, 2, 6);
        verify(canvasProviderSpy, times(0)).getCanvasAsPrintableString();
        verify(canvasProviderSpy, times(0)).createNewCanvas(5, 5);
        verify(canvasProviderSpy, times(0)).drawRectangleOnCanvas(1, 1, 1, 1);
        verify(canvasProviderSpy, times(0)).fillCanvas(1, 1, "u");

        assertEquals("exit", result);
    }

}