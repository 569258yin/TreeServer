package treeserver.mvc;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import treeserver.bean.Bean;
import treeserver.bean.HttpResult;
import treeserver.dao.MongodbDao;

import javax.annotation.Resource;

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
        String tree = StringUtils.trim(httpServletRequest.getParameter("tree"));
        if (StringUtils.isEmpty(tree)) {
            return HttpResult.error("参数不正确");
        }
        Bean bean = new Bean(tree);
        mongodbDao.saveBean(bean);
        logger.info("插入新的树,id="+bean.getId());
        return HttpResult.success(bean.getId());
    }

    @RequestMapping(value = "/tree/{id}", method = RequestMethod.GET)
    public HttpResult getTree() {
        String id = StringUtils.trim(httpServletRequest.getParameter("id"));
        if (StringUtils.isEmpty(id)) {
            return HttpResult.error("参数不正确");
        }
        Bean bean = mongodbDao.queryBeanById(id);
        return HttpResult.success(bean);
    }

    @RequestMapping(value = "/tree", method = RequestMethod.PUT)
    public HttpResult updateTree() {
        String id = StringUtils.trim(httpServletRequest.getParameter("id"));
        String tree = StringUtils.trim(httpServletRequest.getParameter("tree"));
        if (StringUtils.isAnyEmpty(id,tree)) {
            return HttpResult.error("参数不正确");
        }
        Bean originBean = mongodbDao.queryBeanById(id);
        if (originBean == null) {
            return HttpResult.error("未找到对应的数据");
        }
        if (mongodbDao.removeBeanById(id)){
            originBean.setJson(tree);
            mongodbDao.saveBean(originBean);
            return HttpResult.success();
        } else {
            return HttpResult.error("更新出错，请稍后重试");
        }
    }
    @RequestMapping(value = "/tree", method = RequestMethod.DELETE)
    public HttpResult deleteTree() {
        String id = StringUtils.trim(httpServletRequest.getParameter("id"));
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
