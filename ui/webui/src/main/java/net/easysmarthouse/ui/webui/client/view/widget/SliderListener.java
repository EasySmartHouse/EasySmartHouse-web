/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.easysmarthouse.ui.webui.client.view.widget;

/**
 *
 * @author rusakovich
 */
public interface SliderListener{

    public void onStart(SliderEvent e);
    

    public boolean onSlide(SliderEvent e);
    

    public void onChange(SliderEvent e);
    

    public void onStop(SliderEvent e);
}
