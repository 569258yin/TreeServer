package treeserver.mvc;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import treeserver.bean.Bean;
import treeserver.bean.HttpResult;
import treeserver.dao.MongodbDao;
import treeserver.utils.JsonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by kevinyin on 2017/10/15.
 */
@RestController
@RequestMapping("/api")
public class TreeController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(TreeController.class);

    @Resource
    private MongodbDao mongodbDao;

    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    public HttpResult createTree() {
        String tree = StringUtils.trimToEmpty(httpServletRequest.getParameter("tree"));
        if (StringUtils.isEmpty(tree)) {
            return HttpResult.error("参数不正确");
        }
        Bean bean = new Bean(tree);
        mongodbDao.saveBean(bean);
        logger.info("插入新的树,id="+bean.getId());
        return HttpResult.success(bean.getId());
    }

    @RequestMapping(value = "/tree/{id}", method = RequestMethod.GET)
    public String getTree(@PathVariable String id) {
        if (StringUtils.isEmpty(id)) {
            return JsonUtils.generateJson(HttpResult.error("参数不正确"));
        }
        Bean bean = mongodbDao.queryBeanById(id);
        return JsonUtils.dealChars(JsonUtils.generateBeanJson(bean));
    }

    @RequestMapping(value = "/trees", method = RequestMethod.GET)
    public String getTrees() {
        List<Bean> beans = mongodbDao.queryAllBeans();
        return JsonUtils.dealChars(JsonUtils.generateJson(HttpResult.success(beans)));
    }

    @RequestMapping(value = "/tree", method = RequestMethod.PUT)
    public HttpResult updateTree() {
        String id = StringUtils.trimToEmpty(httpServletRequest.getParameter("id"));
        String tree = StringUtils.trimToEmpty(httpServletRequest.getParameter("tree"));
        if (StringUtils.isAnyEmpty(id,tree)) {
            return HttpResult.error("参数不正确");
        }
        Bean bean = new Bean();
        bean.setId(id);
        bean.setJson(tree);
        boolean result = mongodbDao.updateBean(bean);
        if (result) {
            return HttpResult.success();
        } else {
            return HttpResult.error("更新出错，请稍后重试");
        }
    }
    @RequestMapping(value = "/tree", method = RequestMethod.DELETE)
    public HttpResult deleteTree() {
        String id = StringUtils.trimToEmpty(httpServletRequest.getParameter("id"));
        if (StringUtils.isEmpty(id)) {
            return HttpResult.error("参数不正确");
        }
        Bean originBean = mongodbDao.queryBeanById(id);
        if (originBean == null) {
            return HttpResult.error("未找到对应的数据");
        }
        boolean result = mongodbDao.removeBeanById(id);
        if (result) {
            return HttpResult.success();
        } else {
            return HttpResult.error("删除出错，请稍后重试");
        }
    }

}
