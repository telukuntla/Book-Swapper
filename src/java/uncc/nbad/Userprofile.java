package uncc.nbad;

import java.util.ArrayList;
import java.util.List;

public class Userprofile {
    public List<Useritem> useritems = new ArrayList<Useritem>();
    Useritem useritem;
    private String userId;

    public Userprofile() {}

    public void emptyProfile() {
        this.useritems.clear();
    }

    public void removeUserItems(Useritem useritem) {
        this.useritems.remove(useritem);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Useritem> getUserItems() {
        return this.useritems;
    }

    public List<Useritem> getUserItems(String userid) {
        return this.useritems;
    }

    public void setUserItems(List<Useritem> list) {
        this.useritems = list;
    }

/*  User ID – attribute used for user identification
    • User Items – attribute used for UserItem(s)
    • removeUserItem(Item) – removes any UserItem associated with the given Item.
    • getUserItems() – returns a List / Collection of UserItem from this user profile
    • emptyProfile() –clears the entire profile contents
    • … any other helper methods you might need to manage this list/collection */
}

