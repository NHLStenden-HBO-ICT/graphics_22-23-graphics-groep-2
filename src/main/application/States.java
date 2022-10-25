package main.application;

//the different valid program states our program can be in
public enum States {
    STARTING,       //starting up the program
    LOADING_MODEL,  //loading a model
    RENDERING,      //rendering
    IDLE            //doing nothing
}
