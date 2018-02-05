package mkuzdowicz.drawingapp.features.drawing;

import mkuzdowicz.drawingapp.features.canvas.CanvasProvider;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class DrawingCommandsValidatorTest {

    private CanvasProvider canvasProvider;
    private DrawingCommandsValidator validator;

    private final boolean NOT_VALID_EXPECTED_RES = true;
    private final boolean VALID_EXPECTED_RES = false;

    @Before
    public void prepare() {
        canvasProvider = new CanvasProvider();
        validator = new DrawingCommandsValidator();
    }

    @Test
    public void should_return_not_valid_for_not_correct_count_of_arguments_in_command_test1() {
        final DrawCommandDTO dto = new DrawCommandDTO("c 5");
        validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto);
        assertEquals(NOT_VALID_EXPECTED_RES, validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto));
    }

    @Test
    public void should_return_valid_for_correct_count_of_arguments_in_command_test2() {
        final DrawCommandDTO dto = new DrawCommandDTO("c 5 10");
        validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto);
        assertEquals(VALID_EXPECTED_RES, validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto));
    }

    @Test
    public void should_return_not_valid_for_not_correct_count_of_arguments_in_command_test3() {
        final DrawCommandDTO dto = new DrawCommandDTO("l 5 10");
        validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto);
        assertEquals(NOT_VALID_EXPECTED_RES, validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto));
    }

    @Test
    public void should_return_not_valid_for_not_correct_count_of_arguments_in_command_test4() {
        final DrawCommandDTO dto = new DrawCommandDTO("l 5 ");
        validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto);
        assertEquals(NOT_VALID_EXPECTED_RES, validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto));
    }

    @Test
    public void should_return_valid_for_correct_count_of_arguments_in_command_test5() {
        canvasProvider.createNewCanvas(100, 100);
        final DrawCommandDTO dto = new DrawCommandDTO("l 5 10 5 60");
        validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto);
        assertEquals(VALID_EXPECTED_RES, validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto));
    }

    @Test
    public void should_return_valid_for_correct_count_of_arguments_in_command_test6() {
        canvasProvider.createNewCanvas(100, 100);
        final DrawCommandDTO dto = new DrawCommandDTO("r 5 10 40 60");
        validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto);
        assertEquals(VALID_EXPECTED_RES, validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto));
    }

    @Test
    public void should_return_not_valid_for_not_correct_count_of_arguments_in_command_test7() {
        canvasProvider.createNewCanvas(100, 100);
        final DrawCommandDTO dto = new DrawCommandDTO("r 5 10 40 ");
        validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto);
        assertEquals(NOT_VALID_EXPECTED_RES, validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto));
    }

    @Test
    public void should_return_not_valid_for_not_correct_count_of_arguments_in_command_test8() {
        canvasProvider.createNewCanvas(100, 100);
        final DrawCommandDTO dto = new DrawCommandDTO("r 5  ");
        validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto);
        assertEquals(NOT_VALID_EXPECTED_RES, validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto));
    }

    @Test
    public void should_return_not_valid_for_not_correct_count_of_arguments_in_command_test9() {
        canvasProvider.createNewCanvas(100, 100);
        final DrawCommandDTO dto = new DrawCommandDTO("b 5 10 40 60");
        validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto);
        assertEquals(NOT_VALID_EXPECTED_RES, validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto));
    }

    @Test
    public void should_return_valid_for_correct_count_of_arguments_in_command_test10() {
        canvasProvider.createNewCanvas(100, 100);
        final DrawCommandDTO dto = new DrawCommandDTO("b 5 10 o ");
        validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto);
        assertEquals(VALID_EXPECTED_RES, validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto));
    }

    @Test
    public void should_return_not_valid_for_not_correct_count_of_arguments_in_command_test11() {
        canvasProvider.createNewCanvas(100, 100);
        final DrawCommandDTO dto = new DrawCommandDTO("b 5  ");
        validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto);
        assertEquals(NOT_VALID_EXPECTED_RES, validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto));
    }

    @Test
    public void should_return_valid_for_correct_count_of_arguments_in_command_test12() {
        canvasProvider.createNewCanvas(100, 100);
        final DrawCommandDTO dto = new DrawCommandDTO("b 5 10 i");
        validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto);
        assertEquals(VALID_EXPECTED_RES, validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto));
    }

    @Test
    public void should_return_not_valid_for_negative_numeric_val_in_arguments_in_command_test1() {
        canvasProvider.createNewCanvas(100, 100);
        final DrawCommandDTO dto = new DrawCommandDTO("r 5 -10 40 60");
        validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto);
        assertEquals(NOT_VALID_EXPECTED_RES, validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto));
    }

    @Test
    public void should_return_not_valid_for_negative_numeric_val_in_arguments_in_command_test2() {
        canvasProvider.createNewCanvas(100, 100);
        final DrawCommandDTO dto = new DrawCommandDTO("l 5 -10 5 -80");
        validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto);
        assertEquals(NOT_VALID_EXPECTED_RES, validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto));
    }

    @Test
    public void should_return_not_valid_for_position_that_is_out_of_bounds_of_created_canvasfor_drawLine_command_test1() {
        canvasProvider.createNewCanvas(100, 100);
        final DrawCommandDTO dto = new DrawCommandDTO("l 5 200 5 300");
        validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto);
        assertEquals(NOT_VALID_EXPECTED_RES, validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto));
    }

    @Test
    public void should_return_not_valid_for_position_that_is_out_of_bounds_of_created_for_drawLine_command_test2() {
        canvasProvider.createNewCanvas(100, 100);
        final DrawCommandDTO dto = new DrawCommandDTO("l 200 5 300 5");
        validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto);
        assertEquals(NOT_VALID_EXPECTED_RES, validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto));
    }

    @Test
    public void should_return_not_valid_for_position_that_is_out_of_bounds_of_created_canvas_for_drawRectangle_command_test1() {
        canvasProvider.createNewCanvas(100, 100);
        final DrawCommandDTO dto = new DrawCommandDTO("r 5 200 o");
        validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto);
        assertEquals(NOT_VALID_EXPECTED_RES, validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto));
    }

    @Test
    public void should_return_not_valid_for_position_that_is_out_of_bounds_of_created_canvas_for_drawRectangle_command_test2() {
        canvasProvider.createNewCanvas(100, 100);
        final DrawCommandDTO dto = new DrawCommandDTO("r 200 5 i");
        validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto);
        assertEquals(NOT_VALID_EXPECTED_RES, validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto));
    }

    @Test
    public void should_not_validate_position_for_create_canvas_command() {
        final DrawCommandDTO dto = new DrawCommandDTO("c 1000 500");
        validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto);
        assertEquals(VALID_EXPECTED_RES, validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto));
    }

    @Test
    public void should_not_validate_position_for_quit_command() {
        final DrawCommandDTO dto = new DrawCommandDTO("q");
        validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto);
        assertEquals(VALID_EXPECTED_RES, validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), dto));
    }

}