package com.rong.rectcut.model.bean;

import java.util.AbstractCollection;
import java.util.List;

/**
 * Created by wenming on 2016/3/16.
 */
public class ImageFolder {
    public String name;
    public String path;
    public List<String> images;

    @Override
    public boolean equals(Object o) {
        try {
            ImageFolder other = (ImageFolder) o;
            return this.path.equalsIgnoreCase(other.path);
        }catch (ClassCastException e){
            e.printStackTrace();
        }
        return super.equals(o);
    }

    public List<String> getImages() {
        return images;
    }
}
