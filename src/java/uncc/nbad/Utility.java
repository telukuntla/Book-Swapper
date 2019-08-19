
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package uncc.nbad;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author teluk
 */
public class Utility {
    public static List<Item> excludeUserItems(Userprofile profile, List<Item> items) {
        List<Item> custom_list = new ArrayList<Item>();

        for (Item i : items) {
            custom_list.add(i);
        }

        List<Useritem> existing_availablelist = profile.getUserItems();

        for (Item it : items) {
            for (Useritem it1 : existing_availablelist) {
                if (it1.getUserItem().equalsIgnoreCase(it.getItemcode())) {
                    custom_list.remove(it);
                }
            }
        }

        return custom_list;
    }
}
