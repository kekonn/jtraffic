/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RushHour;

/**
 *
 * @author chris
 */
public class AiMove {

    private Voertuig voertuig;
    private int richting;

    public AiMove(Voertuig voertuig,int richting){
        this.voertuig = voertuig;
        this.richting = richting;
    }
    public int getRichting(){
        return richting;
    }
    public Voertuig getVoertuig(){
        return voertuig;
    }
}
