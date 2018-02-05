package mkuzdowicz.drawingapp.features.drawing;

import mkuzdowicz.drawingapp.features.canvas.CanvasComponent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class DrawingCommandsValidator {

    private final String PLEASE_CREATE_CANVAS_MSG = "\nplease create a CANVAS first.\nexample command: 'c 10 20' (it will create CANVAS 10 width, 20 height) ";
    private final String NOT_VALID_ARGUMENTS_COUNT_MSG = "\nnot valid arguments count";
    private final String NO_NEGATIVE_NUMBER_ALLOWED_ERROR_MSG = "\nnot valid numeric arguments, drawing app supports only positive integers, try again";
    private final String COMMAND_WITH_POSITION_OUT_OF_BOUNDS_ERROR_MSG = "\nyou provide a position that is out of bonds of created canvas, try again";
    private final String GENERIC_ERROR_MSG = "\nSomething went wrong please provide a valid command";

    private String validationMsg = "";

    public boolean commandIsNotValidFor(final CanvasComponent currentCanvasState, final DrawCommandDTO dto) {
        final String cmdType = dto.getCommandType();
        boolean isNotExitCommand = (!cmdType.equalsIgnoreCase("q"));
        boolean isNotCreateCanvasCommand = !(cmdType.equalsIgnoreCase("c"));
        if (currentCanvasState == null && isNotExitCommand && isNotCreateCanvasCommand) {
            setCurrentValidationMsg(PLEASE_CREATE_CANVAS_MSG);
            return true;
        }

        if (argumentsCountIsNotValidFor(dto)) {
            setCurrentValidationMsg(NOT_VALID_ARGUMENTS_COUNT_MSG);
            return true;
        }
        if (someOfNumericArgumentsAreNegative(dto.getArguments())) {
            setCurrentValidationMsg(NO_NEGATIVE_NUMBER_ALLOWED_ERROR_MSG);
            return true;
        }
        if (providedPositionIsOutOfBounds(currentCanvasState, dto)) {
            setCurrentValidationMsg(COMMAND_WITH_POSITION_OUT_OF_BOUNDS_ERROR_MSG);
            return true;
        }
        return false;
    }

    public String message() {
        return this.validationMsg;
    }

    public String genericErrorMessage() {
        return GENERIC_ERROR_MSG;
    }

    private void setCurrentValidationMsg(final String msg) {
        this.validationMsg = msg;
    }


    private boolean argumentsCountIsNotValidFor(final DrawCommandDTO drawCommandDTO) {
        final Map<String, Function<DrawCommandDTO, Boolean>> commandSpecificStrategies = new HashMap<>();
        commandSpecificStrategies.put("c", (dto) -> dto.getArguments().size() != 2);
        commandSpecificStrategies.put("l", (dto) -> dto.getArguments().size() != 4);
        commandSpecificStrategies.put("r", (dto) -> dto.getArguments().size() != 4);
        commandSpecificStrategies.put("b", (dto) -> dto.getArguments().size() != 3);
        commandSpecificStrategies.put("q", (dto) -> dto.getArguments().size() != 0);
        return commandSpecificStrategies.get(drawCommandDTO.getCommandType()).apply(drawCommandDTO);
    }

    private boolean someOfNumericArgumentsAreNegative(final List<String> args) {
        return args.stream().filter(a -> isNumeric(a)).filter(n -> Integer.parseInt(n) < 0).count() > 0;
    }

    private boolean providedPositionIsOutOfBounds(final CanvasComponent currentCanvasState, final DrawCommandDTO dto) {
        final String cmdType = dto.getCommandType();
        final List<String> dtoArgs = dto.getArguments();
        if (cmdType.equalsIgnoreCase("l") || cmdType.equalsIgnoreCase("r")) {
            final int x1 = Integer.parseInt(dtoArgs.get(0));
            final int y1 = Integer.parseInt(dtoArgs.get(1));
            final int x2 = Integer.parseInt(dtoArgs.get(2));
            final int y2 = Integer.parseInt(dtoArgs.get(3));
            return currentCanvasState.pointIsOutOfBounds(x1, y1) || currentCanvasState.pointIsOutOfBounds(x2, y2);
        }

        if (cmdType.equalsIgnoreCase("b")) {
            final int x = Integer.parseInt(dtoArgs.get(0));
            final int y = Integer.parseInt(dtoArgs.get(1));
            return currentCanvasState.pointIsOutOfBounds(x, y);
        }
        return false;
    }

    private boolean isNumeric(final String s) {
        return s.matches("[-+]?\\d");
    }

}
