package com.alexlionne.apps.avatars.objects;

/**
 * Created by root on 21/03/16.
 */
public class Options {

    private boolean checkbox;
    private boolean state;
    private boolean colorchooser;

    public Options(){
    }

    public Options( boolean checkbox,boolean state, boolean colorchooser){
        this.checkbox = checkbox;
        this.state = state;
        this.colorchooser = colorchooser;
    }
    public void setCheckbox(boolean b){
        this.checkbox = b;
    }
    public void setState(boolean b){
        this.state = b;
    }
    public void setColorChooser(boolean b){
        this.colorchooser = b;
    }




    public boolean getCheckBox(){
        return this.checkbox;
    }
    public boolean getState(){
        return this.state;
    }
    public boolean getColorChooser(){
        return this.colorchooser;
    }
    public Options checkboxNDiasable(){
        Options options =  new Options();
        options.setState(false);
        options.setCheckbox(true);
        options.setColorChooser(false);
        return options;

    }
    public Options checkboxNEnable(){

       Options options =  new Options();
        options.setState(true);
        options.setCheckbox(true);
        options.setColorChooser(false);
        return options;

    }
    public Options colorChooser(){
        Options options =  new Options();
        options.setState(false);
        options.setCheckbox(false);
        options.setColorChooser(true);
        return options;
    }
    public Options null_options(){
        Options options =  new Options();
        options.setState(false);
        options.setCheckbox(false);
        options.setColorChooser(false);
        return options;
    }
}
