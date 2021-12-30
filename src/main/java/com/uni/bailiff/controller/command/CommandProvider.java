package com.uni.bailiff.controller.command;

import com.uni.bailiff.controller.command.impl.create.CreateNewBailiff;
import com.uni.bailiff.controller.command.impl.create.CreateNewClient;
import com.uni.bailiff.controller.command.impl.create.CreateNewOperation;
import com.uni.bailiff.controller.command.impl.create.CreateNewOrder;
import com.uni.bailiff.controller.command.impl.delete.DeleteBailiff;
import com.uni.bailiff.controller.command.impl.delete.DeleteClient;
import com.uni.bailiff.controller.command.impl.delete.DeleteOperation;
import com.uni.bailiff.controller.command.impl.delete.DeleteOrder;
import com.uni.bailiff.controller.command.impl.page.*;
import com.uni.bailiff.controller.command.impl.sort.SortByBailiff;
import com.uni.bailiff.controller.command.impl.sort.SortByClient;
import com.uni.bailiff.controller.command.impl.sort.SortByOperation;
import com.uni.bailiff.controller.command.impl.update.UpdateBailiff;
import com.uni.bailiff.controller.command.impl.update.UpdateClient;
import com.uni.bailiff.controller.command.impl.update.UpdateOperation;
import com.uni.bailiff.controller.command.impl.update.UpdateOrder;
import com.uni.bailiff.controller.command.impl.utils.Clean;
import com.uni.bailiff.controller.command.impl.utils.Search;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider(){
        commands.put(CommandName.GOTOHOMEPAGE, new GoToHomePage());
        commands.put(CommandName.SEARCH, new Search());
        commands.put(CommandName.CLEAN, new Clean());
        commands.put(CommandName.GOTOBAILIFFPAGE, new GoToBailiffPage());
        commands.put(CommandName.GOTOCLIENTPAGE, new GoToClientPage());
        commands.put(CommandName.GOTOOPERATIONPAGE, new GoToOperationPage());
        commands.put(CommandName.SORTBYBAILIFF, new SortByBailiff());
        commands.put(CommandName.SORTBYCLIENT, new SortByClient());
        commands.put(CommandName.SORTBYOPERATION, new SortByOperation());
        commands.put(CommandName.UPDATEBAILIFF, new UpdateBailiff());
        commands.put(CommandName.UPDATECLIENT, new UpdateClient());
        commands.put(CommandName.UPDATEORDER, new UpdateOrder());
        commands.put(CommandName.UPDATEOPERATION, new UpdateOperation());
        commands.put(CommandName.DELETEBAILIFF, new DeleteBailiff());
        commands.put(CommandName.DELETECLIENT, new DeleteClient());
        commands.put(CommandName.DELETEOPERATION, new DeleteOperation());
        commands.put(CommandName.DELETEORDER, new DeleteOrder());
        commands.put(CommandName.CREATENEWBAILIFF, new CreateNewBailiff());
        commands.put(CommandName.CREATENEWCLIENT, new CreateNewClient());
        commands.put(CommandName.CREATENEWOPERATION, new CreateNewOperation());
        commands.put(CommandName.CREATENEWORDER, new CreateNewOrder());
        commands.put(CommandName.GOTOUPDATEBAILIFFPAGE, new GoToUpdateBailiffPage());
        commands.put(CommandName.GOTOUPDATECLIENTPAGE, new GoToUpdateClientPage());
        commands.put(CommandName.GOTOUPDATEOPERATIONPAGE, new GoToUpdateOperationPage());
        commands.put(CommandName.GOTOUPDATEORDERPAGE, new GoToUpdateOrderPage());

    }

    public Command takeCommand(String name){

        CommandName commandName;
        commandName = CommandName.valueOf(name.toUpperCase());

        return commands.get(commandName);
    }
}
