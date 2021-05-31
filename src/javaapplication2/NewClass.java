/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;
import javaapplication2.comp;



/**
 *
 * @author bbmeg
 */
public class NewClass {
    
    public static void main(String[] args) {
        comp w= new comp("samsung");
        w.find();
        System.out.println(w.name);
        System.out.println(w.minPrice);
    }
   
   
    
}
