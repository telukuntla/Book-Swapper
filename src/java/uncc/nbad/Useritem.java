package uncc.nbad;

import java.io.Serializable;

public class Useritem implements Serializable {
    private String userItem;
    private String rating;
    private String status;
    private String swapItem;
    private String swapItemRating;
    private String itemName;
    private String category;
    private String swapperRating;

    public Useritem() {}

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSwapItem() {
        return swapItem;
    }

    public void setSwapItem(String swapItem) {
        this.swapItem = swapItem;
    }

    public String getSwapItemRating() {
        return swapItemRating;
    }

    public void setSwapItemRating(String swapItemRating) {
        this.swapItemRating = swapItemRating;
    }

    public String getSwapperRating() {
        return swapperRating;
    }

    public void setSwapperRating(String swapperRating) {
        this.swapperRating = swapperRating;
    }

    public String getUserItem() {
        return userItem;
    }

    public void setUserItem(String userItem) {
        this.userItem = userItem;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
