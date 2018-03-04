package org.buttondesigner.listutils;

/**
 * Created by adma9717 on 03/03/18.
 *
 * This class create an object(item) for a list, with two attr (name and description)
 */

public class ListItem {

    private String layoutName;
    private String layoutDescription;

    public ListItem(String layoutName, String layoutDescription) {
        this.layoutName = layoutName;
        this.layoutDescription = layoutDescription;
    }

    public String getLayoutName() {
        return layoutName;
    }

    public String getLayoutDescription() {
        return layoutDescription;
    }
}
