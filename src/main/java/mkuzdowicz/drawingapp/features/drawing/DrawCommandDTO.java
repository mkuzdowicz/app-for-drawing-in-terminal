package mkuzdowicz.drawingapp.features.drawing;


import java.util.Arrays;
import java.util.List;

public class DrawCommandDTO {

    private final String EMPTY_SPACE = " ";

    private final String commandType;
    private final List<String> arguments;

    public DrawCommandDTO(String commandString) {
        final String commandWithLowerCase = commandString.toLowerCase();
        this.commandType = commandWithLowerCase.charAt(0) + "";
        this.arguments = commandWithLowerCase.length() >= 2 ? Arrays.asList(commandWithLowerCase.substring(2).toLowerCase().split(EMPTY_SPACE)) : Arrays.asList();
    }

    public String getCommandType() {
        return commandType;
    }

    public List<String> getArguments() {
        return arguments;
    }
}
