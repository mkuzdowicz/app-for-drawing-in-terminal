package mkuzdowicz.drawingapp.features.canvas;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class CanvasComponentTest {

    @Test
    public void constructor_should_create_a_proper_size_2d_array_test1() {

        final CanvasComponent canvasCmp = new CanvasComponent(2, 2);
        assertEquals(4, canvasCmp.canvas().size());
        assertEquals(4, canvasCmp.canvas().get(0).size());
    }

    @Test
    public void constructor_should_create_a_proper_size_2d_array_test2() {

        final CanvasComponent canvasCmp = new CanvasComponent(2, 10);
        assertEquals(12, canvasCmp.canvas().size());
        assertEquals(4, canvasCmp.canvas().get(0).size());
    }

    @Test
    public void constructor_should_create_a_proper_size_2d_array_test3() {

        final CanvasComponent canvasCmp = new CanvasComponent(10, 2);

        assertEquals(4, canvasCmp.canvas().size());
        assertEquals(12, canvasCmp.canvas().get(0).size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void canvas_constructor_should_throw_exception_if_width_is_a_negative_num_test() {
        new CanvasComponent(-2, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canvas_constructor_should_throw_exception_if_height_is_a_negative_num_test() {
        new CanvasComponent(2, -10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canvas_constructor_should_throw_exception_if_height_and_width_is_a_negative_num_test() {
        new CanvasComponent(-2, -10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void drawLine_method_should_throw_exception_if_any_of_arguments_is_a_negative_num_test1() {
        final CanvasComponent canvasCmp = new CanvasComponent(10, 3);
        canvasCmp.drawLine(-2, 1, 5, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void drawLine_method_should_throw_exception_if_any_of_arguments_is_a_negative_num_test2() {
        final CanvasComponent canvasCmp = new CanvasComponent(10, 3);
        canvasCmp.drawLine(2, -1, 5, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void drawLine_method_should_throw_exception_if_any_of_arguments_is_a_negative_num_test3() {
        final CanvasComponent canvasCmp = new CanvasComponent(10, 3);
        canvasCmp.drawLine(2, 1, -5, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void drawLine_method_should_throw_exception_if_any_of_arguments_is_a_negative_num_test4() {
        final CanvasComponent canvasCmp = new CanvasComponent(10, 3);
        canvasCmp.drawLine(2, 1, 5, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void drawRectangle_method_should_throw_exception_if_any_of_arguments_is_a_negative_num_test1() {
        final CanvasComponent canvasCmp = new CanvasComponent(10, 10);
        canvasCmp.drawRectangle(-5, 5, 7, 9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void drawRectangle_method_should_throw_exception_if_any_of_arguments_is_a_negative_num_test2() {
        final CanvasComponent canvasCmp = new CanvasComponent(10, 10);
        canvasCmp.drawRectangle(5, -5, 7, 9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void drawRectangle_method_should_throw_exception_if_any_of_arguments_is_a_negative_num_test3() {
        final CanvasComponent canvasCmp = new CanvasComponent(10, 10);
        canvasCmp.drawRectangle(5, 5, -7, 9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void drawRectangle_method_should_throw_exception_if_any_of_arguments_is_a_negative_num_test4() {
        final CanvasComponent canvasCmp = new CanvasComponent(10, 10);
        canvasCmp.drawRectangle(5, 5, 7, -9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fill_method_should_throw_exception_if_any_of_arguments_is_a_negative_num_test1() {
        final CanvasComponent canvasCmp = new CanvasComponent(10, 10);
        canvasCmp.drawRectangle(5, 5, 7, 9);
        canvasCmp.fill(-6, 6, "o");
    }

    @Test(expected = IllegalArgumentException.class)
    public void fill_method_should_throw_exception_if_any_of_arguments_is_a_negative_num_test2() {
        final CanvasComponent canvasCmp = new CanvasComponent(10, 10);
        canvasCmp.drawRectangle(5, 5, 7, 9);
        canvasCmp.fill(6, -6, "o");
    }

    @Test(expected = NullPointerException.class)
    public void fill_method_should_throw_exception_if_colour_argument_is_null_test1() {
        final CanvasComponent canvasCmp = new CanvasComponent(10, 10);
        canvasCmp.drawRectangle(5, 5, 7, 9);
        canvasCmp.fill(6, 6, null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void should_throw_exception_for_no_support_for_not_horizontal_or_vertical_lines() {
        final CanvasComponent canvasCmp = new CanvasComponent(10, 10);
        canvasCmp.drawLine(1, 2, 3, 7);
    }


    @Test
    public void should_draw_horizontal_Line_test1() {

        final CanvasComponent canvasCmp = new CanvasComponent(10, 3);
        canvasCmp.drawLine(2, 1, 5, 1);

        assertEquals(Arrays.asList("-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"), canvasCmp.canvas().get(0));
        assertEquals(Arrays.asList("|", " ", "x", "x", "x", "x", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(1));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(2));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(3));
        assertEquals(Arrays.asList("-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"), canvasCmp.canvas().get(4));
    }

    @Test
    public void should_draw_vertical_line_test1() {

        final CanvasComponent canvasCmp = new CanvasComponent(10, 10);
        canvasCmp.drawLine(2, 3, 2, 5);

        assertEquals(Arrays.asList("-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"), canvasCmp.canvas().get(0));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(1));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(2));
        assertEquals(Arrays.asList("|", " ", "x", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(3));
        assertEquals(Arrays.asList("|", " ", "x", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(4));
        assertEquals(Arrays.asList("|", " ", "x", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(5));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(6));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(7));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(8));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(9));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(10));
        assertEquals(Arrays.asList("-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"), canvasCmp.canvas().get(11));
    }

    @Test
    public void should_draw_vertical_and_horizontal_line_test() {

        final CanvasComponent canvasCmp = new CanvasComponent(10, 10);
        canvasCmp.drawLine(2, 3, 2, 5);
        canvasCmp.drawLine(2, 5, 7, 5);

        assertEquals(Arrays.asList("-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"), canvasCmp.canvas().get(0));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(1));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(2));
        assertEquals(Arrays.asList("|", " ", "x", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(3));
        assertEquals(Arrays.asList("|", " ", "x", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(4));
        assertEquals(Arrays.asList("|", " ", "x", "x", "x", "x", "x", "x", " ", " ", " ", "|"), canvasCmp.canvas().get(5));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(6));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(7));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(8));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(9));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(10));
        assertEquals(Arrays.asList("-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"), canvasCmp.canvas().get(11));
    }


    @Test
    public void should_draw_rectangle_test() {

        final CanvasComponent canvasCmp = new CanvasComponent(10, 10);
        canvasCmp.drawRectangle(5, 5, 7, 9);

        assertEquals(Arrays.asList("-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"), canvasCmp.canvas().get(0));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(1));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(2));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(3));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(4));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", "x", "x", "x", " ", " ", " ", "|"), canvasCmp.canvas().get(5));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", "x", " ", "x", " ", " ", " ", "|"), canvasCmp.canvas().get(6));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", "x", " ", "x", " ", " ", " ", "|"), canvasCmp.canvas().get(7));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", "x", " ", "x", " ", " ", " ", "|"), canvasCmp.canvas().get(8));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", "x", "x", "x", " ", " ", " ", "|"), canvasCmp.canvas().get(9));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(10));
        assertEquals(Arrays.asList("-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"), canvasCmp.canvas().get(11));
    }

    @Test
    public void should_fill_area_inside_rectangle_test() {

        final CanvasComponent canvasCmp = new CanvasComponent(10, 10);
        canvasCmp.drawRectangle(5, 5, 7, 9);
        canvasCmp.fill(6, 6, "o");

        assertEquals(Arrays.asList("-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"), canvasCmp.canvas().get(0));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(1));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(2));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(3));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(4));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", "x", "x", "x", " ", " ", " ", "|"), canvasCmp.canvas().get(5));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", "x", "o", "x", " ", " ", " ", "|"), canvasCmp.canvas().get(6));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", "x", "o", "x", " ", " ", " ", "|"), canvasCmp.canvas().get(7));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", "x", "o", "x", " ", " ", " ", "|"), canvasCmp.canvas().get(8));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", "x", "x", "x", " ", " ", " ", "|"), canvasCmp.canvas().get(9));
        assertEquals(Arrays.asList("|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"), canvasCmp.canvas().get(10));
        assertEquals(Arrays.asList("-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"), canvasCmp.canvas().get(11));
    }

    @Test
    public void should_fill_area_outside_rectangle_test() {

        final CanvasComponent canvasCmp = new CanvasComponent(10, 10);
        canvasCmp.drawRectangle(5, 5, 7, 9);
        canvasCmp.fill(3, 4, "s");

        assertEquals(Arrays.asList("-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"), canvasCmp.canvas().get(0));
        assertEquals(Arrays.asList("|", "s", "s", "s", "s", "s", "s", "s", "s", "s", "s", "|"), canvasCmp.canvas().get(1));
        assertEquals(Arrays.asList("|", "s", "s", "s", "s", "s", "s", "s", "s", "s", "s", "|"), canvasCmp.canvas().get(2));
        assertEquals(Arrays.asList("|", "s", "s", "s", "s", "s", "s", "s", "s", "s", "s", "|"), canvasCmp.canvas().get(3));
        assertEquals(Arrays.asList("|", "s", "s", "s", "s", "s", "s", "s", "s", "s", "s", "|"), canvasCmp.canvas().get(4));
        assertEquals(Arrays.asList("|", "s", "s", "s", "s", "x", "x", "x", "s", "s", "s", "|"), canvasCmp.canvas().get(5));
        assertEquals(Arrays.asList("|", "s", "s", "s", "s", "x", " ", "x", "s", "s", "s", "|"), canvasCmp.canvas().get(6));
        assertEquals(Arrays.asList("|", "s", "s", "s", "s", "x", " ", "x", "s", "s", "s", "|"), canvasCmp.canvas().get(7));
        assertEquals(Arrays.asList("|", "s", "s", "s", "s", "x", " ", "x", "s", "s", "s", "|"), canvasCmp.canvas().get(8));
        assertEquals(Arrays.asList("|", "s", "s", "s", "s", "x", "x", "x", "s", "s", "s", "|"), canvasCmp.canvas().get(9));
        assertEquals(Arrays.asList("|", "s", "s", "s", "s", "s", "s", "s", "s", "s", "s", "|"), canvasCmp.canvas().get(10));
        assertEquals(Arrays.asList("-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"), canvasCmp.canvas().get(11));
    }


    @Test
    public void should_fill_whole_canvas_test() {

        final CanvasComponent canvasCmp = new CanvasComponent(10, 10);
        canvasCmp.fill(6, 4, "o");

        assertEquals(Arrays.asList("-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"), canvasCmp.canvas().get(0));
        assertEquals(Arrays.asList("|", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "|"), canvasCmp.canvas().get(1));
        assertEquals(Arrays.asList("|", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "|"), canvasCmp.canvas().get(1));
        assertEquals(Arrays.asList("|", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "|"), canvasCmp.canvas().get(1));
        assertEquals(Arrays.asList("|", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "|"), canvasCmp.canvas().get(1));
        assertEquals(Arrays.asList("|", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "|"), canvasCmp.canvas().get(1));
        assertEquals(Arrays.asList("|", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "|"), canvasCmp.canvas().get(1));
        assertEquals(Arrays.asList("|", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "|"), canvasCmp.canvas().get(1));
        assertEquals(Arrays.asList("|", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "|"), canvasCmp.canvas().get(1));
        assertEquals(Arrays.asList("|", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "|"), canvasCmp.canvas().get(1));
        assertEquals(Arrays.asList("|", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "|"), canvasCmp.canvas().get(1));
        assertEquals(Arrays.asList("-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"), canvasCmp.canvas().get(11));
    }

    @Test
    public void should_return_canvas_as_printable_string_test() {

        final CanvasComponent canvasCmp = new CanvasComponent(10, 10);
        canvasCmp.drawLine(2, 3, 2, 5);
        canvasCmp.drawLine(2, 5, 7, 5);

        final StringBuilder sb = new StringBuilder();
        sb.append("------------\n");
        sb.append("|          |\n");
        sb.append("|          |\n");
        sb.append("| x        |\n");
        sb.append("| x        |\n");
        sb.append("| xxxxxx   |\n");
        sb.append("|          |\n");
        sb.append("|          |\n");
        sb.append("|          |\n");
        sb.append("|          |\n");
        sb.append("|          |\n");
        sb.append("------------");

        final String expected = sb.toString();
        final String actual = canvasCmp.canvasAsPrintableString();

        assert (!actual.isEmpty());
        assertEquals(expected.length(), actual.length());
        assert (actual.contains("\n"));
        assertEquals(expected, actual);
    }

    @Test
    public void should_return_true_for_pointIsOutOfBounds_method_if_position_is_out_test1() {
        final CanvasComponent canvasCmp = new CanvasComponent(10, 10);
        assertEquals(true, canvasCmp.pointIsOutOfBounds(11, 10));
        assertEquals(true, canvasCmp.pointIsOutOfBounds(10, 11));
        assertEquals(true, canvasCmp.pointIsOutOfBounds(-1, -1));
        assertEquals(true, canvasCmp.pointIsOutOfBounds(210, 100));
    }

    @Test
    public void should_return_true_for_pointIsOutOfBounds_method_if_position_is_out_test2() {
        final CanvasComponent canvasCmp = new CanvasComponent(100, 100);
        assertEquals(true, canvasCmp.pointIsOutOfBounds(200, 5));
    }

    @Test
    public void should_return_false_for_pointIsOutOfBounds_method_if_position_is_valid_test1() {
        final CanvasComponent canvasCmp = new CanvasComponent(10, 10);
        assertEquals(false, canvasCmp.pointIsOutOfBounds(9, 9));
        assertEquals(false, canvasCmp.pointIsOutOfBounds(10, 10));
        assertEquals(false, canvasCmp.pointIsOutOfBounds(1, 1));
        assertEquals(false, canvasCmp.pointIsOutOfBounds(0, 0));
    }

}