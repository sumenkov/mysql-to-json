package ru.sumenkov.mysqltojson;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;

public class LaunchOptions {

    public Options launchOptions() {
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(new Option("rt", "readtable", true,
                "Вычитать таблицу <Table name>"));
        optionGroup.addOption(new Option("rd", "readday", true,
                "Вычитать за день <dd.MM.yyyy>"));
        optionGroup.addOption(new Option("rm", "readmonth", true,
                "Вычитать за месяц <dd.MM.yyyy>"));
        optionGroup.addOption(new Option("rp", "readperiod", true,
                "Вычитать за период <dd.MM.yyyy-dd.MM.yyyy> (ограничено, в рамках одного года)"));

        Options options = new Options();
        options.addOptionGroup(optionGroup);

        return options;
    }
}
