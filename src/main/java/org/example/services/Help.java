package org.example.services;

import org.example.usecase.Command;

public class Help implements Command {
    @Override
    public void execute() {
        System.out.println("""
                help : show all commands
                info : show count elements
                show : show all elements
                add {element} : add new element
                update id {element} : update element by id
                remove_by_id id : remove element by id
                clear : clear elements
                exit : shutdown
                remove_last : remove last element
                add_if_max {element} : add new element if it value biggest then max
                reorder : sort elements by dragon age
                average_of_age : show average age value
                count_equals_that_type type : show count element whose type equal entered
                print_field_ascending_color : show type field for all elements""");
    }
}
