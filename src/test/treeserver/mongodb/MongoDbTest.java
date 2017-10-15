package treeserver.mongodb;

import com.mongodb.MongoClient;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import treeserver.bean.Bean;
import treeserver.bean.Constans;
import treeserver.utils.JsonUtilsTest;

import java.io.File;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Created by kevinyin on 2017/10/15.
 */
public class MongoDbTest {



    @Test
    public void testSaveBean(){
        MongoOperations mongoOps = new MongoTemplate(new MongoClient(), "test");
        String json = JsonUtilsTest.txt2String(new File("flare.json"));
        Bean bean = new Bean(json);
        System.out.println(bean.getId());
        mongoOps.insert(bean, Constans.TREE_COLLECTION);

        Bean findBean = mongoOps.findOne(new Query(where("id").is(bean.getId())), Bean.class,Constans.TREE_COLLECTION);
        System.out.println(findBean.getId());
        mongoOps.dropCollection(Constans.TREE_COLLECTION);
    }
}
