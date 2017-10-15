package treeserver.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * Created by kevinyin on 2017/10/15.
 */
public class Tree {
    @JsonIgnore
    private String id;
    private String name;
    private Integer size;
    private List<Tree> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }
}
