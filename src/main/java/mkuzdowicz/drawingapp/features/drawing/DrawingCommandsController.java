package mkuzdowicz.drawingapp.features.drawing;

import mkuzdowicz.drawingapp.features.canvas.CanvasProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class DrawingCommandsController {

    private final Map<String, Function<List<String>, String>> commandsRepository;
    private final DrawingCommandsValidator validator = new DrawingCommandsValidator();
    private final CanvasProvider canvasProvider;

    protected final String ONLY_VERTICAL_AND_HORIZONTAL_LINE_SUPPORT = "\nprogram supports currenntly only horizontal and vertical lines";

    public DrawingCommandsController(final CanvasProvider cp) {
        this.canvasProvider = cp;
        this.commandsRepository = new HashMap<>();

        commandsRepository.put("c", (commandArgs) -> {
            final int width = Integer.parseInt(commandArgs.get(0));
            final int height = Integer.parseInt(commandArgs.get(1));
            canvasProvider.createNewCanvas(width, height);
            return canvasProvider.getCanvasAsPrintableString();
        });
        commandsRepository.put("l", (commandArgs) -> {
            final int x1 = Integer.parseInt(commandArgs.get(0));
            final int y1 = Integer.parseInt(commandArgs.get(1));
            final int x2 = Integer.parseInt(commandArgs.get(2));
            final int y2 = Integer.parseInt(commandArgs.get(3));
            canvasProvider.drawLineOnCanvas(x1, y1, x2, y2);
            return canvasProvider.getCanvasAsPrintableString();
        });
        commandsRepository.put("r", (commandArgs) -> {
            final int x1 = Integer.parseInt(commandArgs.get(0));
            final int y1 = Integer.parseInt(commandArgs.get(1));
            final int x2 = Integer.parseInt(commandArgs.get(2));
            final int y2 = Integer.parseInt(commandArgs.get(3));
            canvasProvider.drawRectangleOnCanvas(x1, y1, x2, y2);
            return canvasProvider.getCanvasAsPrintableString();
        });
        commandsRepository.put("b", (commandArgs) -> {
            final int x = Integer.parseInt(commandArgs.get(0));
            final int y = Integer.parseInt(commandArgs.get(1));
            final String colour = commandArgs.get(2);
            canvasProvider.fillCanvas(x, y, colour);
            return canvasProvider.getCanvasAsPrintableString();
        });
        commandsRepository.put("q", (commandArgs) -> "exit");
    }

    public String executeCommandAndGetResult(final String command) {

        try {
            if (command == null || command.equals(""))
                return "";

            final DrawCommandDTO commandDTO = new DrawCommandDTO(command);

            if (validator.commandIsNotValidFor(canvasProvider.currentCanvasState(), commandDTO)) {
                return validator.message();
            }

            return commandsRepository.get(commandDTO.getCommandType()).apply(commandDTO.getArguments());

        } catch (UnsupportedOperationException e) {
            return ONLY_VERTICAL_AND_HORIZONTAL_LINE_SUPPORT;
        } catch (Exception e) {
            return validator.genericErrorMessage();
        }
    }

}
