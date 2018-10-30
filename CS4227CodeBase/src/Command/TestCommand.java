/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

/**
 *
 * @author hmaug
 */
public class TestCommand implements Command {

    @Override
    public void execute() {
        System.out.println("execute");
    }

    @Override
    public void undo() {
        System.out.println("undo");
    }
    
}
