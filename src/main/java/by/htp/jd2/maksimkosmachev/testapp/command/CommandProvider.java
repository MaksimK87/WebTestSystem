package by.htp.jd2.maksimkosmachev.testapp.command;

import by.htp.jd2.maksimkosmachev.testapp.command.impl.*;

import java.util.HashMap;
import java.util.Map;

final public class CommandProvider {
    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.AUTHORIZATION, new AuthorizationCommand());
        commands.put(CommandName.REGISTRATION,new RegistrationCommand());
        commands.put(CommandName.GO_TO_SIGN_IN_PAGE,new GoToSignInPage());
        commands.put(CommandName.GO_TO_MAIN_PAGE,new GoToMainPage());
        commands.put(CommandName.GO_TO_REGISTRATION_PAGE,new GoToRegistrationPage());
        commands.put(CommandName.GO_TO_ADD_TEST_PAGE, new GoToAddTestPage());
        commands.put(CommandName.ADD_TEST,new AddTest());
        commands.put(CommandName.CHANGE_LANGUAGE,new ChangeLanguage());
        commands.put(CommandName.SIGN_OUT,new SignOut());
        commands.put(CommandName.ADD_TEST_QUESTION,new AddTestQuestionCommand());
        commands.put(CommandName.GO_TO_COMPLETE_ADD_TEST_PAGE,new GoToCompleteAddTestPage());
        commands.put(CommandName.GO_TO_ADD_ANSWER,new AddAnswerCommand());
        commands.put(CommandName.GO_TO_ADD_TEST_QUESTION_PAGE, new GoToAddTestQuestionPage());
        commands.put(CommandName.GO_TO_ADD_TEST_ANSWER_PAGE,new GoToAddTestAnswerPage());
        commands.put(CommandName.GO_TO_SHOW_TEST,new GoToShowTest());
        commands.put(CommandName.SHOW_TEST_QUESTION,new ShowTestQuestionCommand());
        commands.put(CommandName.GO_TO_NEXT_QUESTION,new GoToNextQuestion());
    }

    public Command getCommand(String name) {
        CommandName commandName;
        commandName = CommandName.valueOf(name.toUpperCase());
        return commands.get(commandName);
    }
}
