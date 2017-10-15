package treeserver.dao;

import treeserver.bean.Bean;
import treeserver.bean.Tree;

/**
 * Created by kevinyin on 2017/10/15.
 */
public interface MongodbDao {

    void insertTree(Tree tree);

    Tree queryById(String id);

    void saveBean(Bean o);

    Bean queryBeanById(String id);

    boolean updateBean(Bean bean);

    boolean removeBeanById(String id);

}
