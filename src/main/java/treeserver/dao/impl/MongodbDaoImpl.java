package treeserver.dao.impl;

import com.mongodb.WriteResult;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;
import treeserver.bean.Bean;
import treeserver.bean.Constans;
import treeserver.bean.Tree;
import treeserver.dao.MongodbDao;

import javax.annotation.Resource;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

/**
 * Created by kevinyin on 2017/10/15.
 */
@Repository
public class MongodbDaoImpl implements MongodbDao{

    @Resource
    private MongoOperations mongoOperations;


    @Override
    public void insertTree(Tree tree) {
        mongoOperations.insert(tree, Constans.TREE_COLLECTION);
    }

    @Override
    public Tree queryById(String id) {
        return null;
    }

    @Override
    public void saveBean(Bean o) {
        if (o == null) {
            return;
        }
        mongoOperations.insert(o, Constans.TREE_COLLECTION);
    }

    @Override
    public Bean queryBeanById(String id) {
        return mongoOperations.findOne(query(where("_id").is(id)),Bean.class,Constans.TREE_COLLECTION);
    }

    @Override
    public boolean updateBean(Bean bean) {
        if (bean == null) {
            return false;
        }
        WriteResult writeResult = mongoOperations.updateFirst(query(where("_id").is(bean.getId())),
                update("json",bean.getJson()),
                Bean.class, Constans.TREE_COLLECTION);
        return writeResult.getN() == 1;
    }

    @Override
    public boolean removeBeanById(String id) {
        WriteResult writeResult = mongoOperations.remove(query(where("_id").is(id)), Bean.class, Constans.TREE_COLLECTION);
        return writeResult.getN() == 1;
    }

    @Override
    public List<Bean> queryAllBeans() {
        return mongoOperations.findAll(Bean.class,Constans.TREE_COLLECTION);
    }
}
