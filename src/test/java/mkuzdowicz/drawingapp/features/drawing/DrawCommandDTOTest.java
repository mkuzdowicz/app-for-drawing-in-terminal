package mkuzdowicz.drawingapp.features.drawing;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DrawCommandDTOTest {

    @Test
    public void should_create_proper_commandType_and_arguments_fields_from_command_string_test1(){
        final String cmd = "L 2 4 20 100";
        final DrawCommandDTO dto = new DrawCommandDTO(cmd);

        assertEquals("l", dto.getCommandType());
        assertEquals(4, dto.getArguments().size());
        assertEquals("2", dto.getArguments().get(0));
        assertEquals("4", dto.getArguments().get(1));
        assertEquals("20", dto.getArguments().get(2));
        assertEquals("100", dto.getArguments().get(3));
    }

    @Test
    public void should_create_proper_commandType_and_arguments_fields_from_command_string_test2(){
        final String cmd = "c 20 100";
        final DrawCommandDTO dto = new DrawCommandDTO(cmd);

        assertEquals("c", dto.getCommandType());
        assertEquals(2, dto.getArguments().size());
        assertEquals("20", dto.getArguments().get(0));
        assertEquals("100", dto.getArguments().get(1));
    }

    @Test
    public void should_create_proper_commandType_and_arguments_fields_from_command_string_test3(){
        final String cmd = "B 20 190 i";
        final DrawCommandDTO dto = new DrawCommandDTO(cmd);

        assertEquals("b", dto.getCommandType());
        assertEquals(3, dto.getArguments().size());
        assertEquals("20", dto.getArguments().get(0));
        assertEquals("190", dto.getArguments().get(1));
        assertEquals("i", dto.getArguments().get(2));
    }

}